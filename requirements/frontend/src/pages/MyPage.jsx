import React, { useEffect, useState } from 'react';
import './MyPage.css';
import axios from 'axios';

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

    useEffect(() => {
        const userFromStorage = JSON.parse(localStorage.getItem("user"));
        if (userFromStorage && userFromStorage.nickname) {
            setUser(userFromStorage);
            setNickname(userFromStorage.nickname);
            setNewNickname(userFromStorage.nickname);
            setUserId(userFromStorage.id);

            // 1. 서버에서 선호 장르 요청
            axios
                .get(`/api/users/${userFromStorage.id}`)
                .then((res) => {
                    const genreNums = res.data.preferredGenres;
                    if (Array.isArray(genreNums)) {
                        const genres = genreNums.map((num) => genreOptions[num - 1]);
                        setPreferredGenreNames(genres);
                    }
                })
                .catch(() => {
                    // 2. 실패 시 localStorage에서 대체
                    const storedGenres = JSON.parse(localStorage.getItem("preferredGenres"));
                    if (Array.isArray(storedGenres)) {
                        const genres = storedGenres.map((num) => genreOptions[num - 1]);
                        setPreferredGenreNames(genres);
                    } else {
                        console.warn("선호 장르 정보를 불러오지 못했습니다.");
                    }
                });
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
            setEditing(false);
        } catch (error) {
            alert("닉네임 수정에 실패했습니다.");
        }
    };

    return (
        <div className="mypage-wrapper">
            <div className="mypage-header">
                <div className="platform-title">리뷰보다</div>
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
                        <button className="edit-button">선호하는 장르 수정</button>
                    </div>
                    <div className="genre-checklist">
                        {genreOptions.map((genre) => (
                            <label key={genre} className="genre-item">
                                <input
                                    type="checkbox"
                                    value={genre}
                                    checked={preferredGenreNames.includes(genre)}
                                    disabled
                                />
                                {genre}
                            </label>
                        ))}
                    </div>
                </div>

                <div><h3> 작성한 리뷰 목록 </h3></div>
            </div>
        </div>
    );
};

export default MyPage;
