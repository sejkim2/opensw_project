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
        // 이미 영화가 존재하면 실행하지 않음
        if (movieRepository.count() > 0) return;

        List<Genre> allGenres = genreRepository.findAll();

        // 이름으로 장르 매핑
        Genre action = findByName(allGenres, "Action");
        Genre drama = findByName(allGenres, "Drama");
        Genre romance = findByName(allGenres, "Romance");
        Genre sciFi = findByName(allGenres, "Science Fiction");

        // 더미 영화 리스트
        List<Movie> movies = Arrays.asList(
                Movie.builder()
                        .title("Inception")
                        .description("A mind-bending sci-fi thriller by Christopher Nolan.")
                        .imageUrl("https://example.com/inception.jpg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(action, sciFi)))
                        .build(),

                Movie.builder()
                        .title("La La Land")
                        .description("A heartfelt musical romance drama.")
                        .imageUrl("https://example.com/lalaland.jpg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(romance, drama)))
                        .build(),

                Movie.builder()
                        .title("Interstellar")
                        .description("An epic journey through space and time.")
                        .imageUrl("https://example.com/interstellar.jpg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(drama, sciFi)))
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
