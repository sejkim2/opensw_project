package com.example.app.movie;

import com.example.app.movie.dto.MovieDetailResponseDto;
import com.example.app.movie.dto.ReviewSummaryDto;
import com.example.app.review.Review;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public MovieDetailResponseDto getMovieDetail(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));

        List<ReviewSummaryDto> reviews = movie.getReviews().stream()
                .map(this::toReviewDto)
                .collect(Collectors.toList());

        List<String> genres = movie.getGenres().stream()
                .map(g -> g.getName())
                .collect(Collectors.toList());

        return MovieDetailResponseDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .averageRating(movie.getAverageRating())
                .imageUrl(movie.getImageUrl())
                .genres(genres)
                .reviews(reviews)
                .build();
    }

    private ReviewSummaryDto toReviewDto(Review review) {
        return ReviewSummaryDto.builder()
                .id(review.getId())
                .userId(review.getUser().getId())
                .username(review.getUser().getUsername())
                .content(review.getContent())
                .rating(review.getRating())
                .build();
    }
}
