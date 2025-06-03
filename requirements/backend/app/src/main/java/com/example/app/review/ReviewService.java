package com.example.app.review;

import com.example.app.movie.Movie;
import com.example.app.movie.MovieRepository;
import com.example.app.review.dto.ReviewRequestDto;
import com.example.app.review.dto.ReviewResponseDto;
import com.example.app.user.User;
import com.example.app.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public ReviewResponseDto createReview(ReviewRequestDto requestDto) {
        // 평점 유효성 검사
        if (requestDto.getRating() < 0.0 || requestDto.getRating() > 5.0) {
            throw new IllegalArgumentException("유효하지 않은 평점입니다 (0.0 ~ 5.0 사이여야 함)");
        }

        // 중복 리뷰 검사
        if (reviewRepository.existsByUserIdAndMovieId(requestDto.getUserId(), requestDto.getMovieId())) {
            throw new IllegalStateException("이미 해당 영화에 리뷰를 작성했습니다.");
        }

        // 유저, 영화 조회
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다"));
        Movie movie = movieRepository.findById(requestDto.getMovieId())
                .orElseThrow(() -> new EntityNotFoundException("영화를 찾을 수 없습니다"));

        // 리뷰 생성 및 저장
        Review review = Review.builder()
                .user(user)
                .movie(movie)
                .content(requestDto.getContent())
                .rating(requestDto.getRating())
                .build();

        Review saved = reviewRepository.save(review);

        return ReviewResponseDto.builder()
                .id(saved.getId())
                .userId(saved.getUser().getId())
                .movieId(saved.getMovie().getId())
                .content(saved.getContent())
                .rating(saved.getRating())
                .createdAt(saved.getCreatedAt())
                .build();
    }
}
