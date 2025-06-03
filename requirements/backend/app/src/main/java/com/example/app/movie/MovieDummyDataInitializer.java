package com.example.app.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieDummyDataInitializer implements CommandLineRunner {

    private final MovieRepository movieRepository;

    @Override
    public void run(String... args) {
        if (movieRepository.count() == 0) {
            List<Movie> movies = List.of(
                Movie.builder()
                        .title("인셉션")
                        .imageUrl("https://example.com/inception.jpg")
                        .description("꿈과 현실이 교차하는 놀라운 이야기")
                        .averageRating(0.0)
                        .build(),
                Movie.builder()
                        .title("인터스텔라")
                        .imageUrl("https://example.com/interstellar.jpg")
                        .description("시간과 공간을 초월한 인류의 여정")
                        .averageRating(0.0)
                        .build(),
                Movie.builder()
                        .title("라라랜드")
                        .imageUrl("https://example.com/lalaland.jpg")
                        .description("음악과 사랑이 흐르는 아름다운 도시 이야기")
                        .averageRating(0.0)
                        .build()
            );

            movieRepository.saveAll(movies);
            System.out.println("🎬 Movie 더미 데이터가 저장되었습니다.");
        }
    }
}
