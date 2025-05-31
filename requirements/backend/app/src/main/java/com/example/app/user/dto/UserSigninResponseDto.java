package com.example.app.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSigninResponseDto {
    private Long id;
    private String username;
    private String nickname;
}
