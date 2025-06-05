package com.example.app.review;

import com.example.app.review.dto.ReviewRequestDto;
import com.example.app.review.dto.ReviewResponseDto;
import com.example.app.review.dto.UserReviewDto;
import com.example.app.user.User;
import com.example.app.user.UserRepository;
import com.example.app.movie.Movie;
import com.example.app.movie.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewResponseDto createReview(ReviewRequestDto requestDto) {
        // 1. 평점 유효성 검사
        if (requestDto.getRating() < 0.0 || requestDto.getRating() > 5.0) {
            throw new IllegalArgumentException("유효하지 않은 평점입니다 (0.0 ~ 5.0 사이여야 함)");
        }

        // 2. 중복 리뷰 검사
        if (reviewRepository.existsByUserIdAndMovieId(requestDto.getUserId(), requestDto.getMovieId())) {
            throw new IllegalStateException("이미 해당 영화에 리뷰를 작성했습니다.");
        }

        // 3. 유저, 영화 조회
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다"));
        Movie movie = movieRepository.findById(requestDto.getMovieId())
                .orElseThrow(() -> new EntityNotFoundException("영화를 찾을 수 없습니다"));

        // 4. 리뷰 생성 및 저장
        Review review = Review.builder()
                .user(user)
                .movie(movie)
                .content(requestDto.getContent())
                .rating(requestDto.getRating())
                .build();

        Review saved = reviewRepository.save(review);

        // 5. 평균 평점 계산 및 반올림(소수 첫째 자리)
        double newAverage = reviewRepository.findAverageRatingByMovieId(movie.getId());
        double roundedAverage = Math.round(newAverage * 10) / 10.0;

        movie.setAverageRating(roundedAverage);
        movieRepository.save(movie);

        // 6. DTO 반환
        return ReviewResponseDto.builder()
                .id(saved.getId())
                .userId(saved.getUser().getId())
                .movieId(saved.getMovie().getId())
                .content(saved.getContent())
                .rating(saved.getRating())
                .createdAt(saved.getCreatedAt())
                .build();
    }


    // ✅ 사용자별 최신 리뷰 3개 조회 메서드
    @Transactional(readOnly = true)
    public List<ReviewResponseDto> getRecentReviewsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다"));

        return reviewRepository.findByUser(user).stream()
                .sorted(Comparator.comparing(Review::getCreatedAt).reversed())
                .limit(3)
                .map(ReviewResponseDto::fromEntity)

    public List<UserReviewDto> getReviewsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(NOT_FOUND, "User not found");
        }

        List<Review> reviews = reviewRepository.findByUserId(userId);
        return reviews.stream()
                .map(UserReviewDto::fromEntity)
                .collect(Collectors.toList());
    }
}

