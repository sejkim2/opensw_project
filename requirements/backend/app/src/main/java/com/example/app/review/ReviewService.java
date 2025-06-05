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
import java.util.Comparator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewResponseDto createReview(ReviewRequestDto requestDto) {
        if (requestDto.getRating() < 0.0 || requestDto.getRating() > 5.0) {
            throw new IllegalArgumentException("유효하지 않은 평점입니다 (0.0 ~ 5.0 사이여야 함)");
        }

        if (reviewRepository.existsByUserIdAndMovieId(requestDto.getUserId(), requestDto.getMovieId())) {
            throw new IllegalStateException("이미 해당 영화에 리뷰를 작성했습니다.");
        }

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다"));
        Movie movie = movieRepository.findById(requestDto.getMovieId())
                .orElseThrow(() -> new EntityNotFoundException("영화를 찾을 수 없습니다"));

        Review review = Review.builder()
                .user(user)
                .movie(movie)
                .content(requestDto.getContent())
                .rating(requestDto.getRating())
                .build();

        Review saved = reviewRepository.save(review);

        double newAverage = reviewRepository.findAverageRatingByMovieId(movie.getId());
        double roundedAverage = Math.round(newAverage * 10) / 10.0;

        movie.setAverageRating(roundedAverage);
        movieRepository.save(movie);

        return ReviewResponseDto.builder()
                .id(saved.getId())
                .userId(saved.getUser().getId())
                .movieId(saved.getMovie().getId())
                .content(saved.getContent())
                .rating(saved.getRating())
                .createdAt(saved.getCreatedAt())
                .build();
    }

    @Transactional(readOnly = true)
    public List<ReviewResponseDto> getRecentReviewsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다"));

        return reviewRepository.findByUser(user).stream()
                .sorted(Comparator.comparing(Review::getCreatedAt).reversed())
                .limit(3)
                .map(ReviewResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
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
