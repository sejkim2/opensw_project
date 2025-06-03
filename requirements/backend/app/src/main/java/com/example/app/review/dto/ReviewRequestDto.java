package com.example.app.review.dto;

import lombok.Getter;

@Getter
public class ReviewRequestDto {
    private Long userId;
    private Long movieId;
    private String content;
    private Double rating;
}
