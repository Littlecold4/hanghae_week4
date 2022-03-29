package com.sparta.week03.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String passwordCheck;
    private String email;
    private boolean admin = false;
    private String adminToken = "";

    public SignupRequestDto(String username, String password, String passwordCheck, String email) {
        this.username=username;
        this.password= password;
        this.passwordCheck= passwordCheck;
        this.email= email;
        this.admin= false;
        this.adminToken="";
    }
}