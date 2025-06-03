package com.example.app.review;

import com.example.app.movie.Movie;
import com.example.app.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUser(User user);
    List<Review> findByMovie(Movie movie);
    Optional<Review> findByUserAndMovie(User user, Movie movie);
    boolean existsByUserIdAndMovieId(Long userId, Long movieId);

    @Query("SELECT COALESCE(AVG(r.rating), 0.0) FROM Review r WHERE r.movie.id = :movieId")
    double findAverageRatingByMovieId(@Param("movieId") Long movieId);
}