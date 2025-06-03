package com.example.app.movie.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewSummaryDto {
    private Long id;
    private Long userId;
    private String username;
    private String content;
    private Double rating;
}
