package com.example.app.movie;

import com.example.app.movie.dto.MovieDetailResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.status(404).body(error(404, "Not Found", e.getMessage()));
        }
    }

    private static ErrorResponse error(int status, String error, String message) {
        return new ErrorResponse(status, error, message, "/api/movies");
    }

    record ErrorResponse(int status, String error, String message, String path) {}
}
