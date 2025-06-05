package com.example.app.movie.dto;

import com.example.app.movie.Movie;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder
public class MovieSummaryResponseDto {
    private Long id;
    private String title;
    private Double averageRating;
    private String imageUrl;
    private Set<String> genres;

    public static MovieSummaryResponseDto fromEntity(Movie movie) {
        return MovieSummaryResponseDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .averageRating(movie.getAverageRating())
                .imageUrl(movie.getImageUrl())
                .genres(movie.getGenres().stream()
                        .map(g -> g.getName())
                        .collect(Collectors.toSet()))
                .build();
    }
}
