package com.sparta.week03.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NiceRequestDto {
    private String nice;
    private Long blogId;
    private String username;
}
