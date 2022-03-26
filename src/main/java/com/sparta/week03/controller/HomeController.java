package com.sparta.week03.controller;

import com.sparta.week03.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails == null){
            model.addAttribute("username", "로그인을 해주세요");
            return "index";
        }
        model.addAttribute("username", userDetails.getUsername());
        return "index";
    }
}