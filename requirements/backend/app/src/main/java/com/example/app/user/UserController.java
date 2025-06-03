package com.example.app.user;

import com.example.app.user.dto.UserSignupRequestDto;
import com.example.app.user.dto.UserSignupResponseDto;
import com.example.app.user.dto.UserSigninRequestDto;
import com.example.app.user.dto.UserSigninResponseDto;
import com.example.app.user.dto.UserResponseDto;
import com.example.app.user.dto.UpdatePreferredGenresRequestDto;
import com.example.app.user.dto.UserNicknameUpdateRequestDto;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserSignupResponseDto> signup(@RequestBody UserSignupRequestDto requestDto) {
        UserSignupResponseDto responseDto = userService.signup(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 로그인
    @PostMapping("/signin")
    public ResponseEntity<UserSigninResponseDto> signin(@RequestBody UserSigninRequestDto requestDto) {
        UserSigninResponseDto responseDto = userService.signin(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 선호 장르 수정
    @PutMapping("/{id}/preferred-genres")
    public ResponseEntity<UserResponseDto> updatePreferredGenres(
            @PathVariable Long id,
            @RequestBody UpdatePreferredGenresRequestDto requestDto) {
        UserResponseDto responseDto = userService.updatePreferredGenres(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 닉네임 수정
    @PatchMapping("/{id}/nickname")
    public ResponseEntity<?> updateNickname(
            @PathVariable Long id,
            @RequestBody UserNicknameUpdateRequestDto requestDto) {
        try {
            User updatedUser = userService.updateNickname(id, requestDto);
            return ResponseEntity.ok(
                    Map.of(
                            "id", updatedUser.getId(),
                            "username", updatedUser.getUsername(),
                            "nickname", updatedUser.getNickname()
                    )
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(
                    Map.of(
                            "status", 404,
                            "error", "Not Found",
                            "message", e.getMessage(),
                            "path", "/api/users/" + id + "/nickname"
                    )
            );
        }
    }
}
