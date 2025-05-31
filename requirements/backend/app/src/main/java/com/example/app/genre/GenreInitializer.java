package com.example.app.genre;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreInitializer {

    private final GenreRepository genreRepository;

    @PostConstruct
    public void initGenres() {
        if (genreRepository.count() > 0) return;

        List<String> genreNames = Arrays.asList(
                "Action",
                "Drama",
                "Romance",
                "Horror",
                "Fantasy",
                "Comedy",
                "Animation",
                "Science Fiction",
                "Thriller",
                "Other"
        );

        for (String name : genreNames) {
            genreRepository.save(Genre.builder().name(name).build());
        }

        System.out.println("Genre init data create finish.");
    }
}
