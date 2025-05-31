package com.example.app.review;

import com.example.app.movie.Movie;
import com.example.app.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUser(User user);
    List<Review> findByMovie(Movie movie);
    Optional<Review> findByUserAndMovie(User user, Movie movie);
}