package com.example.app.movie.dto;

import com.example.app.review.dto.ReviewSummaryDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
@Builder
public class MovieDetailResponseDto {
    private Long id;
    private String title;
    private String description;
    private Double averageRating;
    private String imageUrl;
    private Set<String> genres;
    private List<ReviewSummaryDto> reviews;
}
