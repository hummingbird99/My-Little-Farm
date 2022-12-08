package com.example.choi.controller;


import com.example.choi.domain.entity.UserInfo;
import com.example.choi.dto.CalendarDto;
import com.example.choi.service.CalendarService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CalendarController {
    private CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/MyPage")
    public String list( Model model) {
        //List<CalendarDto> calendarDtoList = calendarService.getCalendarList();
        //model.addAttribute("postList", calendarDtoList);
        return "MyPage";
    }

    @ResponseBody
    @RequestMapping(value = "/MyPage-list")
    //@GetMapping("/MyPage-list")
    public List<CalendarDto> sublist( ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userid = "";

        String userRole = String.valueOf(auth.getAuthorities());
        if(auth instanceof UsernamePasswordAuthenticationToken) {
            Object oUser = auth.getPrincipal();
            userid = ((UserInfo)oUser).getUserid();
        }
        else if(auth instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = (OAuth2User) auth.getPrincipal();
            Map<String, Object> attributes = (Map<String, Object>) oAuth2User.getAttributes();
            userid = oAuth2User.getName();
        }


        List<CalendarDto> calendarDtoList = calendarService.getCalendarSubList(userid);
       // model.addAttribute("getList", calendarDtoList);
        //return "MyPage";
        return calendarDtoList;
    }

    @ResponseBody
    @PostMapping("/MyPage")
    public void write( CalendarDto calendarDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
          String userid = "";

        String userRole = String.valueOf(auth.getAuthorities());
        if(auth instanceof UsernamePasswordAuthenticationToken) {
            Object oUser = auth.getPrincipal();
            userid = ((UserInfo)oUser).getUserid();
        }
        else if(auth instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = (OAuth2User) auth.getPrincipal();
            Map<String, Object> attributes = (Map<String, Object>) oAuth2User.getAttributes();
            userid = oAuth2User.getName();
        }

        try {
            calendarDto.setCreator(userid);
            calendarService.savePost(calendarDto);
        } catch(Exception e) {
            System.out.print(e);
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/MyPage-del")
    public void delete( Long idx) {
        calendarService.deletePost(idx);
    }

}
