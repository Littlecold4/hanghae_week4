package com.sparta.week03.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.week03.Service.KakaoUserService;
import com.sparta.week03.Service.UserService;
import com.sparta.week03.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login(@AuthenticationPrincipal UserDetailsImpl userDetails,
                        Model model) {
        if(userDetails !=null){
            model.addAttribute("loggedin",true);
            model.addAttribute("message","이미로그인하셨습니다");
        }else{
            model.addAttribute("loggedin", false);
        }
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/user/detail")
    public String detail() {
        return "detail";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(com.sparta.week03.dto.SignupRequestDto requestDto,
                               Model model) {
        try {
            userService.registerUser(requestDto);
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            String message =e.getMessage();
            model.addAttribute("msg",message);
            return "signup";
        }
        return "redirect:/user/login";
    }


    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }
}