package com.example.app.genre;

import com.example.app.genre.dto.GenreResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public List<GenreResponseDto> getAllGenres() {
        return genreRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .map(g -> new GenreResponseDto(g.getId(), g.getName()))
                .collect(Collectors.toList());
    }

}
