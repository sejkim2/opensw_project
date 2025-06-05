package com.example.app.review.dto;

import com.example.app.review.Review;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserReviewDto {
    private Long id;
    private String content;
    private Double rating;
    private Long movieId;
    private String movieTitle;

    public static UserReviewDto fromEntity(Review review) {
        return UserReviewDto.builder()
                .id(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .movieId(review.getMovie().getId())
                .movieTitle(review.getMovie().getTitle())
                .build();
    }
}
