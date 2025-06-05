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
        Genre horror = findByName(allGenres, "Horror");
        Genre fantasy = findByName(allGenres, "Fantasy");
        Genre comedy = findByName(allGenres, "Comedy");
        Genre animation = findByName(allGenres, "Animation");
        Genre thriller = findByName(allGenres, "Thriller");
        Genre other = findByName(allGenres, "Other");

        List<Movie> movies = Arrays.asList(
                Movie.builder()
                        .title("극한직업")
                        .description("A mind-bending sci-fi thriller by Christopher Nolan.")
                        .imageUrl("/images/movie1.jpeg") 
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(comedy)))
                        .build(),

                Movie.builder()
                        .title("내안의 그놈")
                        .description("A heartfelt musical romance drama.")
                        .imageUrl("/images/movie2.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(other)))
                        .build(),

                Movie.builder()
                        .title("태극기 휘날리며")
                        .description("An epic journey through space and time.")
                        .imageUrl("/images/movie3.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(other)))
                        .build(),

                Movie.builder()
                        .title("수상한 그녀")
                        .description("A team of heroes saving the world.")
                        .imageUrl("/images/movie4.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(drama, comedy)))
                        .build(),

                Movie.builder()
                        .title("범죄도시1")
                        .description("A tragic romance on the ill-fated ship.")
                        .imageUrl("/images/movie5.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(thriller, comedy)))
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
