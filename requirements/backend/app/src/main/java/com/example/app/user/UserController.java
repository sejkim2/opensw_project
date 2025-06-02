// 파일 경로: src/main/java/com/example/app/user/UserController.java

package com.example.app.user;

import com.example.app.user.dto.UserSignupRequestDto;
import com.example.app.user.dto.UserSignupResponseDto;
import com.example.app.user.dto.UserSigninRequestDto;
import com.example.app.user.dto.UserSigninResponseDto;
import com.example.app.user.dto.UpdatePreferredGenresRequestDto;
import com.example.app.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public UserSignupResponseDto signup(@RequestBody UserSignupRequestDto request) {
        return userService.signup(request);
    }

    // 로그인
    @PostMapping("/signin")
    public UserSigninResponseDto signin(@RequestBody UserSigninRequestDto request) {
        return userService.signin(request);
    }

    // 선호 장르 전체 수정
    @PatchMapping("/{id}/genres")
    public UserResponseDto updatePreferredGenres(
            @PathVariable Long id,
            @RequestBody UpdatePreferredGenresRequestDto request
    ) {
        return userService.updatePreferredGenres(id, request);
    }
}
