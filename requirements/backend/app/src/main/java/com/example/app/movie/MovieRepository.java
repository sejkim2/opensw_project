package com.example.app.movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.genres ORDER BY m.averageRating DESC")
    List<Movie> findTopPopularMovies(org.springframework.data.domain.Pageable pageable);
}
