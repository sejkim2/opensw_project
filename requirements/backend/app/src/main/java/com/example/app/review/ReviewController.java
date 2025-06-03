package com.example.app.review;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.app.review.dto.ReviewRequestDto;
import com.example.app.review.dto.ReviewResponseDto;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody ReviewRequestDto requestDto) {
        try {
            ReviewResponseDto response = reviewService.createReview(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error(400, "Bad Request", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error(409, "Conflict", e.getMessage()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error(404, "Not Found", e.getMessage()));
        }
    }

    private static ErrorResponse error(int status, String error, String message) {
        return new ErrorResponse(status, error, message, "/api/reviews");
    }

    record ErrorResponse(int status, String error, String message, String path) {}
}
