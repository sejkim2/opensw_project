package com.example.app.movie;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.app.genre.Genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.genres ORDER BY m.averageRating DESC")
    List<Movie> findTopPopularMovies(org.springframework.data.domain.Pageable pageable);
  
    @Query("SELECT DISTINCT m FROM Movie m JOIN m.genres g WHERE g IN :genres")
    List<Movie> findByGenresIn(@Param("genres") Set<Genre> genres);

}
