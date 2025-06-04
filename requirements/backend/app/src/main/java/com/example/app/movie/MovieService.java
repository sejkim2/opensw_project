package com.example.app.movie;

import com.example.app.movie.dto.MovieDetailResponseDto;
import com.example.app.movie.dto.PopularMovieResponseDto;
import com.example.app.review.dto.ReviewSummaryDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
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

                return MovieDetailResponseDto.builder()
                                .id(movie.getId())
                                .title(movie.getTitle())
                                .description(movie.getDescription())
                                .imageUrl(movie.getImageUrl())
                                .averageRating(movie.getAverageRating())
                                .genres(movie.getGenres().stream()
                                                .map(genre -> genre.getName())
                                                .collect(Collectors.toSet()))
                                .reviews(movie.getReviews().stream()
                                                .map(ReviewSummaryDto::fromEntity)
                                                .collect(Collectors.toList()))
                                .build();
        }

        public List<PopularMovieResponseDto> getPopularMovies(int limit) {
                List<Movie> movies = movieRepository.findTopPopularMovies(PageRequest.of(0, limit));
                return movies.stream().map(movie -> PopularMovieResponseDto.builder()
                                .id(movie.getId())
                                .title(movie.getTitle())
                                .averageRating(movie.getAverageRating())
                                .imageUrl(movie.getImageUrl())
                                .genres(movie.getGenres().stream().map(g -> g.getName()).toList())
                                .build())
                                .toList();
        }
}
