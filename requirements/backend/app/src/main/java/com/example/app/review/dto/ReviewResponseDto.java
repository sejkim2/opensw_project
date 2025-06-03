package com.example.app.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewResponseDto {
    private Long id;
    private Long userId;
    private Long movieId;
    private String content;
    private Double rating;
    private LocalDateTime createdAt;
}
