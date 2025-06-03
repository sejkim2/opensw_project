package com.example.app.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import com.example.app.review.Review;

@Getter
@Builder
public class ReviewResponseDto {
    private Long id;
    private Long userId;
    private Long movieId;
    private String content;
    private Double rating;
    private LocalDateTime createdAt;

    public static ReviewResponseDto fromEntity(Review review) {
        return ReviewResponseDto.builder()
                .id(review.getId())
                .userId(review.getUser().getId())
                .movieId(review.getMovie().getId())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
