import React from "react";

function GenreCheckboxes({ genres, selectedGenres, handleGenreChange }) {
    return (
        <div className="genre-checkboxes">
            <p>선호 장르 선택:</p>
            {genres.map((genre) => (
                <label key={genre.id} style={{ marginRight: "10px" }}>
                    <input
                        type="checkbox"
                        value={genre.id}
                        checked={selectedGenres.includes(genre.id)}
                        onChange={handleGenreChange}
                    />
                    {genre.name}
                </label>
            ))}
        </div>
    );
}

export default GenreCheckboxes;
