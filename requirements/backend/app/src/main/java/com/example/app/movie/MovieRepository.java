package com.example.app.movie;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.app.genre.Genre;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT DISTINCT m FROM Movie m JOIN m.genres g WHERE g IN :genres")
    List<Movie> findByGenresIn(@Param("genres") Set<Genre> genres);
}
