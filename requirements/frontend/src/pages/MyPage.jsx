import React, { useEffect, useState } from 'react';
import './MyPage.css';
import axios from 'axios';
import { useNavigate } from "react-router-dom";

const handleLogout = () => {
    localStorage.removeItem("user");
    window.location.href = "/";
};

const genreOptions = [
    "Action", "Drama", "Romance", "Horror", "Fantasy",
    "Comedy", "Animation", "Science Fiction", "Thriller", "Other"
];

const MyPage = () => {
    const [nickname, setNickname] = useState('');
    const [editing, setEditing] = useState(false);
    const [newNickname, setNewNickname] = useState('');
    const [userId, setUserId] = useState(null);
    const [user, setUser] = useState(null);
    const [preferredGenreNames, setPreferredGenreNames] = useState([]);
    const navigate = useNavigate();
    const [genreEditing, setGenreEditing] = useState(false);
    const [selectedGenres, setSelectedGenres] = useState([]);
    const [recentReviews, setRecentReviews] = useState([]);

const handleGenreSave = async () => {
    if (selectedGenres.length === 0) {
        alert("선호하는 장르는 최소 1개 이상 선택해야 합니다.");
        return;
    }

    try {
        const res = await axios.put(`/api/users/${userId}/preferred-genres`, {
            preferredGenres: selectedGenres,
        });

        const genres = selectedGenres.map((id) => genreOptions[id - 1]);
        setPreferredGenreNames(genres);
        setGenreEditing(false);

        const key = `preferredGenres_${userId}`;
        localStorage.setItem(key, JSON.stringify(selectedGenres));

        const userFromStorage = JSON.parse(localStorage.getItem("user"));
        const updatedUser = { ...userFromStorage, preferredGenres: selectedGenres };
        localStorage.setItem("user", JSON.stringify(updatedUser));
    } catch (error) {
        console.error("장르 수정 실패:", error);
        alert("선호 장르 수정에 실패했습니다.");
    }
};

    useEffect(() => {
    const userFromStorage = JSON.parse(localStorage.getItem("user"));
    if (userFromStorage && userFromStorage.nickname) {
        setUser(userFromStorage);
        setNickname(userFromStorage.nickname);
        setNewNickname(userFromStorage.nickname);
        setUserId(userFromStorage.id);

        axios.get(`/api/reviews/users/${userFromStorage.id}/recent`)
            .then(res => setRecentReviews(res.data))
            .catch(err => console.error("리뷰 3개 불러오기 실패:", err));

        const key = `preferredGenres_${userFromStorage.id}`;
        const raw = localStorage.getItem(key);
        if (raw !== null && raw !== undefined && raw !== "undefined") {
            try {
                const storedGenres = JSON.parse(raw);
                if (Array.isArray(storedGenres)) {
                    const genres = storedGenres.map((num) => genreOptions[num - 1]);
                    setPreferredGenreNames(genres);
                    setSelectedGenres(storedGenres);
                } else {
                    console.warn("❗ localStorage에 저장된 장르가 배열이 아님:", storedGenres);
                }
            } catch (e) {
                console.error("📛 localStorage JSON parse 실패:", e);
            }
        } else {
            console.warn(`📛 localStorage에서 ${key} 데이터 없음 또는 undefined`);
        }
    }
}, []);

    const handleSave = async () => {
        try {
            const response = await axios.patch(`/api/users/${userId}/nickname`, {
                nickname: newNickname,
            });

            const updatedUser = {
                ...JSON.parse(localStorage.getItem("user")),
                nickname: response.data.nickname,
            };
            localStorage.setItem("user", JSON.stringify(updatedUser));
            setNickname(response.data.nickname);
            setUser(updatedUser);
            setEditing(false);
        } catch (error) {
            console.error("닉네임 수정 오류:", error);
            alert("닉네임 수정에 실패했습니다.");
        }
    };

    return (
        <div className="mypage-wrapper">
            <div className="mypage-header">
                <div className="platform-title" onClick={() => navigate("/main")} style={{ cursor: "pointer" }}>
                    리뷰보다
                </div>
                <div className="logout-text" onClick={handleLogout} style={{ cursor: 'pointer' }}>
                    로그아웃
                </div>
            </div>

            <div className="mypage-card">
                <div className="nickname-box">
                    <span className="nickname-text">
                        {user ? user.nickname : '닉네임'}
                    </span>
                </div>

                {!editing ? (
                    <h5 onClick={() => setEditing(true)} style={{ cursor: 'pointer' }}> 닉네임 수정 </h5>
                ) : (
                    <div className="nickname-edit-box">
                        <input
                            type="text"
                            value={newNickname}
                            onChange={(e) => setNewNickname(e.target.value)}
                        />
                        <button onClick={handleSave}>저장</button>
                    </div>
                )}

                <div className="nickname-line"></div>

                <div className="genre-section">
                    <div className="genre-title-row">
                        <h3>선호하는 장르</h3>
                        <button
                            className="edit-button"
                            onClick={() => {
                                if (genreEditing) {
                                    handleGenreSave();
                                } else {
                                    setGenreEditing(true);
                                }
                            }}
                        >
                            {genreEditing ? "저장" : "선호하는 장르 수정"}
                        </button>
                    </div>
                    <div className="genre-checklist">
                        {genreOptions.map((genre) => (
                            <label key={genre} className="genre-item">
                                <input
                                    type="checkbox"
                                    value={genre}
                                    checked={selectedGenres.includes(genreOptions.indexOf(genre) + 1)}
                                    readOnly={!genreEditing}
                                    onChange={(e) => {
                                        if (!genreEditing) return;
                                        const id = genreOptions.indexOf(genre) + 1;
                                        if (e.target.checked) {
                                            setSelectedGenres(prev => [...prev, id]);
                                        } else {
                                            setSelectedGenres(prev => prev.filter(g => g !== id));
                                        }
                                    }}
                                />
                                {genre}
                            </label>
                        ))}
                    </div>
                </div>

                <div className="review-section">
                    <div className="review-title-row">
                        <h3>작성한 리뷰 목록 <span style={{ color: 'aquamarine' }}>{recentReviews.length}</span></h3>
                    </div>

                    {recentReviews.length === 0 ? (
                        <p style={{ color: '#ccc'}}>
                            작성한 리뷰가 없습니다.
                        </p>
                    ) : (
                        recentReviews.map((review) => (
                            <div key={review.reviewId} className="review-summary">
                                <div className="review-date">
                                    {new Date(review.createdAt).toLocaleDateString()}
                                </div>
                                <div className="review-content-preview">
                                    {review.content.length > 50
                                        ? review.content.slice(0, 50) + '...'
                                        : review.content}
                                </div>
                            </div>
                        ))
                    )}
                </div>
            </div>
        </div>
    );
};

export default MyPage;