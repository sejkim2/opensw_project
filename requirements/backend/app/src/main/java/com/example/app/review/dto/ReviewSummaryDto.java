package com.example.app.review.dto;

import com.example.app.review.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewSummaryDto {
    private Long id;
    private Long userId;
    private String username;
    private String content;
    private Double rating;
    private LocalDateTime createdAt;

    public static ReviewSummaryDto fromEntity(Review review) {
        return ReviewSummaryDto.builder()
                .id(review.getId())
                .userId(review.getUser().getId())
                .username(review.getUser().getUsername())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
