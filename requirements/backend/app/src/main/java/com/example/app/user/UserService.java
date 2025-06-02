package com.example.app.user;

import com.example.app.genre.Genre;
import com.example.app.genre.GenreRepository;
import com.example.app.user.dto.UserSigninRequestDto;
import com.example.app.user.dto.UserSigninResponseDto;
import com.example.app.user.dto.UserSignupRequestDto;
import com.example.app.user.dto.UserSignupResponseDto;
import com.example.app.user.dto.UserResponseDto;
import com.example.app.user.dto.UpdatePreferredGenresRequestDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GenreRepository genreRepository;

    // 회원가입 메서드
    public UserSignupResponseDto signup(UserSignupRequestDto request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        Set<Genre> preferredGenres = new HashSet<>(genreRepository.findAllById(request.getPreferredGenres()));

        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .preferredGenres(preferredGenres)
                .build();

        User saved = userRepository.save(user);
        return new UserSignupResponseDto(saved.getId(), saved.getUsername(), saved.getNickname());
    }

    // 로그인 메서드
    public UserSigninResponseDto signin(UserSigninRequestDto request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return new UserSigninResponseDto(user.getId(), user.getUsername(), user.getNickname());
    }

    // 선호 장르 전체 수정 메서드
    public UserResponseDto updatePreferredGenres(Long userId, UpdatePreferredGenresRequestDto dto) {
        // 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // 기존 선호 장르 초기화
        user.getPreferredGenres().clear();

        // 새 장르 리스트 조회
        List<Genre> genres = genreRepository.findAllById(dto.getPreferredGenres());

        // 할당
        user.getPreferredGenres().addAll(genres);

        // 저장
        User updatedUser = userRepository.save(user);

        // 응답 DTO로 변환
        return new UserResponseDto(
                updatedUser.getId(),
                updatedUser.getUsername(),
                updatedUser.getNickname(),
                updatedUser.getPreferredGenres()
                        .stream()
                        .map(Genre::getName)
                        .collect(Collectors.toList())
        );
    }
}
