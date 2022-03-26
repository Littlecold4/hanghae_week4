package com.sparta.week03.controller;

import com.sparta.week03.domain.Blog;
import com.sparta.week03.domain.User;
import com.sparta.week03.repository.BlogRepository;
import com.sparta.week03.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class pageController {

    private final BlogRepository blogRepository;

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id,
                         @AuthenticationPrincipal UserDetailsImpl userDetails,
                         Model model){
        Blog blog = blogRepository.findById(id).orElseThrow(
                ()->new NullPointerException()
        );
        User user = new User();
        if(userDetails != null){
            user = userDetails.getUser();
        }
        model.addAttribute("response",blog);
        model.addAttribute("user",user);
        return "detail";
    }
}
