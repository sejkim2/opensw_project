package com.example.app.user.dto;

import java.util.List;

public class UpdatePreferredGenresRequestDto {

    private List<Long> preferredGenres;

    public UpdatePreferredGenresRequestDto() {}

    public UpdatePreferredGenresRequestDto(List<Long> preferredGenres) {
        this.preferredGenres = preferredGenres;
    }

    public List<Long> getPreferredGenres() {
        return preferredGenres;
    }

    public void setPreferredGenres(List<Long> preferredGenres) {
        this.preferredGenres = preferredGenres;
    }
}
