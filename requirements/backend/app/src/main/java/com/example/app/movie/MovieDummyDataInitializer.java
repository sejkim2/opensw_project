package com.example.app.movie;

import com.example.app.genre.Genre;
import com.example.app.genre.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MovieDummyDataInitializer implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    @Override
    public void run(String... args) {
        if (movieRepository.count() == 0) {
            Genre sciFi = genreRepository.findByName("Science Fiction").orElseThrow();
            Genre action = genreRepository.findByName("Action").orElseThrow();
            Genre drama = genreRepository.findByName("Drama").orElseThrow();
            Genre romance = genreRepository.findByName("Romance").orElseThrow();
            Genre music = genreRepository.findByName("Animation").orElseThrow(); // 대체 장르로 사용

            Movie inception = Movie.builder()
                    .title("인셉션")
                    .description("꿈속의 꿈을 탐험하는 액션 SF 영화")
                    .imageUrl("https://example.com/inception.jpg")
                    .averageRating(0.0)
                    .genres(Set.of(sciFi, action))
                    .build();

            Movie interstellar = Movie.builder()
                    .title("인터스텔라")
                    .description("시간과 공간을 초월한 우주 탐험 이야기")
                    .imageUrl("https://example.com/interstellar.jpg")
                    .averageRating(0.0)
                    .genres(Set.of(sciFi, drama))
                    .build();

            Movie lalaland = Movie.builder()
                    .title("라라랜드")
                    .description("음악과 사랑이 흐르는 아름다운 도시 이야기")
                    .imageUrl("https://example.com/lalaland.jpg")
                    .averageRating(0.0)
                    .genres(Set.of(romance, music))
                    .build();

            movieRepository.saveAll(List.of(inception, interstellar, lalaland));

            System.out.println("🎬 영화 + 장르 연결 더미 데이터 삽입 완료");
        }
    }
}
