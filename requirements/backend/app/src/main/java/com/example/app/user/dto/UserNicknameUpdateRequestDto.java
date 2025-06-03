package com.example.app.user.dto;

import lombok.Getter;
import lombok.Setter;

// 클라이언트로부터 전달받은 새 닉네임을 담는 DTO
@Getter
@Setter
public class UserNicknameUpdateRequestDto {
    private String nickname;
}
