package com.example.choi.controller;
import com.example.choi.domain.entity.UserInfo;
import com.example.choi.domain.repository.UserRepository;
import com.example.choi.dto.BillboardDto;
import com.example.choi.dto.FileDto;
import com.example.choi.service.BillboardService;
import com.example.choi.service.FileService;
import com.example.choi.service.UserService;
import com.example.choi.util.MD5Generator;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

@Controller
public class BillboardController {
    private BillboardService billboardService;
    private FileService fileService;

    private final HttpSession httpSession;
    private final UserRepository userRepository;



    public BillboardController(BillboardService billboardService, FileService fileService, HttpSession httpSession, UserRepository userRepository) {
        this.billboardService = billboardService;
        this.fileService = fileService;
        this.httpSession = httpSession;
        this.userRepository = userRepository;
    }

    @GetMapping("/bbs/list/{bbstype}") //게시판 리스트 이동
    public String list(@PathVariable("bbstype") String bbstype,Model model) {
        List<BillboardDto> billboardDtoList = billboardService.getBillboardList(bbstype);

        model.addAttribute("postList", billboardDtoList);
        model.addAttribute("bbstype", bbstype);

        return "billboard/boardlist";
    }

    @GetMapping("bbs/post/{bbstype}")  //작성페이지 이동
    public String post(@PathVariable("bbstype") String strType, Model model) {
        model.addAttribute("bbstype",strType);
        return "billboard/boardwrite";
    }

    @PostMapping("bbs/post" )  // 게시물 저장
    public String write(@RequestParam("file") MultipartFile files, BillboardDto billboardDto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //auth..getDetails()
        String userNick = "";
        String userid = "";

        String userRole = String.valueOf(auth.getAuthorities());
        if(auth instanceof UsernamePasswordAuthenticationToken) {
            Object oUser = auth.getPrincipal();
            userNick = ((UserInfo)oUser).getNickname();
            userid = ((UserInfo)oUser).getUserid();
        }
        else if(auth instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = (OAuth2User) auth.getPrincipal();
            Map<String, Object> attributes = (Map<String, Object>) oAuth2User.getAttributes();
            Map<String, Object> lhm = (LinkedHashMap)attributes.get("properties");
            if(lhm == null){
                userNick = (String) attributes.get("name");
            }else {
                userNick = (String) lhm.get("nickname");
            }
            userid = oAuth2User.getName();
        }
        else {
            return "/login";
        }
        billboardDto.setUserid(userid);
        billboardDto.setUsername(userNick);
        String bbstype = billboardDto.getBbstype();
        try {
            String origFilename = files.getOriginalFilename();
            if(origFilename != ""){
                String filename = new MD5Generator(origFilename).toString();
                /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
                String savePath = System.getProperty("user.dir") + "\\files";
                /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
                if (!new File(savePath).exists()) {
                    try{
                        new File(savePath).mkdir();
                    }
                    catch(Exception e){
                        e.getStackTrace();
                    }
                }
                String filePath = savePath + "\\" + filename;
                files.transferTo(new File(filePath));

                FileDto fileDto = new FileDto();
                fileDto.setOrigFilename(origFilename);
                fileDto.setFilename(filename);
                fileDto.setFilePath(filePath);

                Long fileId = fileService.saveFile(fileDto);
                billboardDto.setFileId(fileId);
            }
            billboardService.savePost(billboardDto);

        } catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/bbs/list/"+bbstype;
    }

    @GetMapping("bbs/view/{id}") // 게시물 상세보기
    public String detail(@PathVariable("id") Long id, Model model) {
        BillboardDto billboardDto = billboardService.getPost(id);
        if (billboardDto.getModifiedDate() == null) {

        }
        if(billboardDto.getFileId() != null) {
            FileDto fileDto = fileService.getFile(billboardDto.getFileId());
            model.addAttribute("filename", fileDto.getOrigFilename());
        }
        model.addAttribute("post", billboardDto);
        billboardService.updateView(id); // views ++

        String usrid = SecurityContextHolder.getContext().getAuthentication().getName().toString();

        if(billboardDto.getUserid().equals(usrid))
        {
            model.addAttribute("usreq", "" );
        }else {
            model.addAttribute("usreq", "display:none" );
        }

        return "billboard/boardview";
    }

    @GetMapping("bbs/modify/{id}") //게시물 수정페이지 이동
    public String edit(@PathVariable("id") Long id, Model model) {
        BillboardDto billboardDto = billboardService.getPost(id);
        model.addAttribute("post", billboardDto);
        return "billboard/boardmodify";
    }

    @PutMapping("bbs/modify/{id}") //게시물 수정 저장
    public String update(BillboardDto billboardDto) {
        billboardService.modifyPost(billboardDto);
        return "redirect:/bbs/list/"+billboardDto.getBbstype();
    }

    @GetMapping("/bbs/post-del/{id}") //게시물 삭제
    public String delete(@PathVariable("id") Long id, BillboardDto billboardDto) {
        billboardDto = billboardService.getPost(id);
        String bbstype = billboardDto.getBbstype();
        billboardService.deletePost(id);
        return "redirect:/bbs/list/"+bbstype;
    }
    @GetMapping("bbs/download/{fileId}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {
        FileDto fileDto = fileService.getFile(fileId);
        Path path = Paths.get(fileDto.getFilePath());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/operation_info")
    public String Operation_Info(){
        return "board/operation_information";
    }
    // 작물질병정보 컨트롤러
    @GetMapping("/Notification_info")
    public String Notification_info(){
        return "board/Notification_information";
    }

    @GetMapping("/Sale_list")
    public String Sale_list(){
        return "board/Sale_list3";
    }
    // 작물질병정보 컨트롤러

}
