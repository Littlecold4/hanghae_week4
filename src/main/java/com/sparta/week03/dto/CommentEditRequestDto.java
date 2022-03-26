package com.sparta.week03.dto;

import lombok.Getter;

import javax.persistence.Column;

@Getter
public class CommentEditRequestDto {
    @Column(nullable = false)
    private String content;
}
