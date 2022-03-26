package com.sparta.week03.controller;

import com.sparta.week03.Service.CommentService;
import com.sparta.week03.domain.Comment;
import com.sparta.week03.dto.CommentRequestDto;
import com.sparta.week03.repository.CommentRepository;
import com.sparta.week03.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @GetMapping("/detail/api/comment/{blogId}")
    public List<Comment> readComment(@PathVariable Long blogId){
        return commentRepository.findAllByBlogId(blogId);
    }

    @PostMapping("/detail/api/comment/{blogId}")
    public Comment createComment(@RequestBody CommentRequestDto requestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails,
                                 @PathVariable Long blogId){
        Long userId = userDetails.getUser().getId();
        System.out.println(userId+blogId);

        Comment comment = commentService.createComment(requestDto, blogId,userId);
        return comment;
    }
}
