package com.example.choi.controller;

import com.example.choi.dto.UserInfoDto;
import com.example.choi.service.UserService;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.validation.Valid;

import java.util.Map;




@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String dispSignup(UserInfoDto infoDto) {
        return "user/signup";
    }


    @PostMapping("/user")
    public String signup(@Valid UserInfoDto infoDto, Errors errors, Model model) { // 회원 추가
        if (errors.hasErrors()) {
            // 회원가입 실패시, 입력 데이터를 유지
            model.addAttribute("infoDto", infoDto);

            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            return "user/signup";
        }

        userService.save(infoDto);

        return "redirect:/login";

    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

    @RequestMapping(value = "/IdChek")
    @ResponseBody
    public int checkId(String s_id) {
        return userService.checkId(s_id);
    }




}
