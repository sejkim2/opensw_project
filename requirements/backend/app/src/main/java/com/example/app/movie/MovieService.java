package com.example.app.movie;

import com.example.app.genre.Genre;
import com.example.app.movie.dto.MovieDetailResponseDto;
import com.example.app.movie.dto.MovieSummaryResponseDto;
import com.example.app.review.dto.ReviewSummaryDto;
import com.example.app.user.User;
import com.example.app.user.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

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

    public List<MovieSummaryResponseDto> getRecommendedMovies(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Set<Genre> preferredGenres = user.getPreferredGenres();
        if (preferredGenres == null || preferredGenres.isEmpty()) {
            throw new EntityNotFoundException("User has no preferred genres");
        }

        List<Movie> movies = movieRepository.findByGenresIn(preferredGenres);

        return movies.stream()
                .map(MovieSummaryResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
