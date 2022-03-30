package com.sparta.week03.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentRequestDto {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;
}
