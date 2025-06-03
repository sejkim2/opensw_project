package com.example.app.movie.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MovieDetailResponseDto {
    private Long id;
    private String title;
    private String description;
    private Double averageRating;
    private String imageUrl;
    private List<String> genres;
    private List<ReviewSummaryDto> reviews;
}
