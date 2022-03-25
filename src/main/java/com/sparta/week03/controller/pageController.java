package com.sparta.week03.controller;

import com.sparta.week03.domain.Blog;
import com.sparta.week03.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class pageController {

    private final BlogRepository blogRepository;

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id,Model model){
        Blog blog = blogRepository.findById(id).orElseThrow(
                ()->new NullPointerException()
        );
        model.addAttribute("response",blog);
        return "detail";
    }
}
