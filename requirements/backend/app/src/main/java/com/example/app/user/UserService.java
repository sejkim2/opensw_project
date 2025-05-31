package com.example.app.user;

import com.example.app.genre.Genre;
import com.example.app.genre.GenreRepository;
import com.example.app.user.dto.UserSigninRequestDto;
import com.example.app.user.dto.UserSigninResponseDto;
import com.example.app.user.dto.UserSignupRequestDto;
import com.example.app.user.dto.UserSignupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GenreRepository genreRepository;

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

    public UserSigninResponseDto signin(UserSigninRequestDto request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return new UserSigninResponseDto(user.getId(), user.getUsername(), user.getNickname());
    }
}
