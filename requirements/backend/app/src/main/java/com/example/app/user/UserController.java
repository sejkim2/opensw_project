package com.example.app.user;

import com.example.app.user.dto.UserSigninRequestDto;
import com.example.app.user.dto.UserSigninResponseDto;
import com.example.app.user.dto.UserSignupRequestDto;
import com.example.app.user.dto.UserSignupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserSignupRequestDto request) {
        try {
            UserSignupResponseDto response = userService.signup(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ErrorResponse(400, "Bad Request", e.getMessage(), "/api/users/signup")
            );
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody UserSigninRequestDto request) {
        try {
            UserSigninResponseDto response = userService.signin(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ErrorResponse(401, "Unauthorized", e.getMessage(), "/api/users/signin"));
        }
    }

    record ErrorResponse(int status, String error, String message, String path) {
    }
}
