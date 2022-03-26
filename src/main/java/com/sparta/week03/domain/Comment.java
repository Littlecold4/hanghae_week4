package com.sparta.week03.domain;


import com.sparta.week03.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Comment {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long blogId;

    @Column(nullable = false)
    private Long userId;

    public Comment(CommentRequestDto requestDto, Long blogId, Long userId) {
// 관심상품을 등록한 회원 Id 저장
        this.blogId = blogId;
        this.userId = userId;
        this.name = requestDto.getName();
        this.content = requestDto.getContent();
    }
    }
