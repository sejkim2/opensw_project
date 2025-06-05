package com.example.app.movie;

import com.example.app.genre.Genre;
import com.example.app.genre.GenreRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class MovieDataInitializer {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    @PostConstruct
    public void initDummyMovies() {
        if (movieRepository.count() > 0) return;

        List<Genre> allGenres = genreRepository.findAll();

        Genre action = findByName(allGenres, "Action");
        Genre drama = findByName(allGenres, "Drama");
        Genre romance = findByName(allGenres, "Romance");
        Genre sciFi = findByName(allGenres, "Science Fiction");

        List<Movie> movies = Arrays.asList(
                Movie.builder()
                        .title("Inception")
                        .description("A mind-bending sci-fi thriller by Christopher Nolan.")
                        .imageUrl("/images/movie1.jpeg") 
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(action, sciFi)))
                        .build(),

                Movie.builder()
                        .title("La La Land")
                        .description("A heartfelt musical romance drama.")
                        .imageUrl("/images/movie2.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(romance, drama)))
                        .build(),

                Movie.builder()
                        .title("Interstellar")
                        .description("An epic journey through space and time.")
                        .imageUrl("/images/movie3.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(drama, sciFi)))
                        .build(),

                Movie.builder()
                        .title("Avengers")
                        .description("A team of heroes saving the world.")
                        .imageUrl("/images/movie4.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(action, sciFi)))
                        .build(),

                Movie.builder()
                        .title("Titanic")
                        .description("A tragic romance on the ill-fated ship.")
                        .imageUrl("/images/movie5.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(romance, drama)))
                        .build()
        );

        movieRepository.saveAll(movies);
    }

    private Genre findByName(List<Genre> genres, String name) {
        return genres.stream()
                .filter(g -> g.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Genre not found: " + name));
    }
}
