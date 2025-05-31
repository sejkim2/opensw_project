package com.example.app.genre;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

import com.example.app.movie.Movie;
import com.example.app.user.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies = new HashSet<>();

    @ManyToMany(mappedBy = "preferredGenres")
    private Set<User> users = new HashSet<>();
}