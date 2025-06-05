package com.example.app.movie;

import com.example.app.movie.dto.MovieDetailResponseDto;
import com.example.app.movie.dto.MovieSummaryResponseDto;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/{movieId}")
    public ResponseEntity<?> getMovieDetail(@PathVariable Long movieId) {
        try {
            MovieDetailResponseDto response = movieService.getMovieDetail(movieId);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(
                    Map.of(
                            "status", 404,
                            "error", "Not Found",
                            "message", "Movie not found",
                            "path", "/api/movies/" + movieId
                    )
            );
        }
    }

    @GetMapping("/recommended/{userId}")
    public ResponseEntity<?> getRecommendedMovies(@PathVariable Long userId) {
        try {
            List<MovieSummaryResponseDto> response = movieService.getRecommendedMovies(userId);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(
                    Map.of(
                            "status", 404,
                            "error", "Not Found",
                            "message", "User not found or has no preferred genres",
                            "path", "/api/movies/recommended/" + userId));
        }
    }

}
