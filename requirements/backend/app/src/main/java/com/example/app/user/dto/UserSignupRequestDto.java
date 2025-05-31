package com.example.app.user.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class UserSignupRequestDto {
    private String username;
    private String password;
    private String nickname;
    private List<Long> preferredGenres;
}
