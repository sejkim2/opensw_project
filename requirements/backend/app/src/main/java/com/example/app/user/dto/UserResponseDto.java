// 파일 경로: src/main/java/com/example/app/user/dto/UserResponseDto.java
package com.example.app.user.dto;

import java.util.List;

public class UserResponseDto {

    private Long id;
    private String username;
    private String nickname;
    private List<String> preferredGenres;

    public UserResponseDto() {}

    public UserResponseDto(Long id, String username, String nickname, List<String> preferredGenres) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.preferredGenres = preferredGenres;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public List<String> getPreferredGenres() {
        return preferredGenres;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPreferredGenres(List<String> preferredGenres) {
        this.preferredGenres = preferredGenres;
    }
}
