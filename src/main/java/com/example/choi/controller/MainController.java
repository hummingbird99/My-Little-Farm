package com.example.choi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String Main() {
        return "index";
    }
    @GetMapping("/test")
    public String Test(){
        return "board/Sale_list3";
    }
}


