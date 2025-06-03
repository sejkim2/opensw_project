package com.example.app.movie;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

import com.example.app.genre.Genre;
import com.example.app.review.Review;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Double averageRating = 0.0;

    @ManyToMany
    @JoinTable(
        name = "movie_genres",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
}

