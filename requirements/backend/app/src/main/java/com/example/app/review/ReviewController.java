package com.example.app.review;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.app.review.dto.ReviewRequestDto;
import com.example.app.review.dto.ReviewResponseDto;
import com.example.app.review.dto.UserReviewDto;

import jakarta.persistence.EntityNotFoundException;

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

    @GetMapping("users/{userId}")
    public ResponseEntity<List<UserReviewDto>> getReviewsByUserId(@PathVariable Long userId) {
        List<UserReviewDto> reviews = reviewService.getReviewsByUserId(userId);
        return ResponseEntity.ok(reviews); // reviews가 비어도 200 OK
    }

    record ErrorResponse(int status, String error, String message, String path) {
    }
}
