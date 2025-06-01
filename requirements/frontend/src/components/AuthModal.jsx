import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import GenreSelectDropdown from "./GenreSelectDropdown";


function AuthModal({ isSignup, setIsSignup, setShowModal }) {
    const [formData, setFormData] = useState({
        username: "",
        password: "",
        passwordConfirm: "",
        nickname: "",
    });
    const [genres, setGenres] = useState([]);
    const [selectedGenres, setSelectedGenres] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        if (isSignup) {
            fetchGenres();
        }
    }, [isSignup]);

    const fetchGenres = async () => {
        try {
            const res = await fetch("/api/genres");
            const data = await res.json();
            setGenres(data);
        } catch (err) {
            console.error("장르 불러오기 실패:", err);
        }
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const handleGenreChange = (e) => {
        const genreId = parseInt(e.target.value);
        setSelectedGenres((prev) =>
            e.target.checked
                ? [...prev, genreId]
                : prev.filter((id) => id !== genreId)
        );
    };

    // 로그인 처리 함수
    const handleLogin = async () => {
        try {
            const res = await fetch("/api/users/signin", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    username: formData.username,
                    password: formData.password,
                }),
            });

            if (!res.ok) {
                const error = await res.json();
                throw new Error(error.message || "로그인 실패");
            }

            const user = await res.json();
            alert(`로그인 성공! 환영합니다, ${user.nickname}님`);
            setFormData({
                username: "",
                password: "",
                passwordConfirm: "",
                nickname: "",
            });
            setShowModal(false);
            navigate("/main");
        } catch (err) {
            alert(err.message);
            console.error("로그인 오류:", err);
        }
    };

    const handleSignup = async () => {
        const { username, password, passwordConfirm, nickname } = formData;

        console.log("회원가입 시 입력값 확인:", formData, selectedGenres);

        if (!username.trim() || !password.trim() || !passwordConfirm.trim() || !nickname.trim()) {
            alert("모든 항목을 입력해주세요.");
            return;
        }

        if (password !== passwordConfirm) {
            alert("비밀번호가 일치하지 않습니다.");
            return;
        }

        if (selectedGenres.length === 0) {
            alert("선호 장르를 하나 이상 선택해주세요.");
            return;
        }

        try {
            const res = await fetch("/api/users/signup", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    username,
                    password,
                    nickname,
                    preferredGenres: selectedGenres,
                }),
            });

            if (!res.ok) {
                const errorData = await res.json();
                throw new Error(errorData.message || "회원가입 실패");
            }

            const data = await res.json();
            alert(`회원가입 성공! 환영합니다, ${data.nickname}님`);
            setFormData({
                username: "",
                password: "",
                passwordConfirm: "",
                nickname: "",
            });
            setSelectedGenres([]);
            setShowModal(false);
        } catch (err) {
            alert(err.message);
            console.error("회원가입 오류:", err);
        }
    };



    return (
        <div className="modal-overlay" onClick={() => setShowModal(false)}>
            <div className="modal" onClick={(e) => e.stopPropagation()}>
                <h2>{isSignup ? "회원가입" : "로그인"}</h2>
                <input
                    type="text"
                    name="username"
                    placeholder="아이디"
                    value={formData.username}
                    onChange={handleInputChange}
                />
                <input
                    type="password"
                    name="password"
                    placeholder="비밀번호"
                    value={formData.password}
                    onChange={handleInputChange}
                />
                {isSignup && (
                    <>
                        <input
                            type="password"
                            name="passwordConfirm"
                            placeholder="비밀번호 확인"
                            value={formData.passwordConfirm}
                            onChange={handleInputChange}
                        />
                        <input
                            type="text"
                            name="nickname"
                            placeholder="닉네임"
                            value={formData.nickname}
                            onChange={handleInputChange}
                        />
                        <GenreSelectDropdown
                            genres={genres}
                            selectedGenres={selectedGenres}
                            setSelectedGenres={setSelectedGenres}
                        />
                    </>
                )}
                <div className="modal-buttons">
                    {!isSignup ? (
                        <>
                            <button type="button" onClick={handleLogin}>로그인</button>
                            <button type="button" onClick={() => setIsSignup(true)}>회원가입</button>
                        </>
                    ) : (
                        <button type="button" onClick={handleSignup}>가입하기</button>
                    )}
                </div>
                <button className="close-btn" onClick={() => setShowModal(false)}>
                    닫기
                </button>
            </div>
        </div>
    );
}

export default AuthModal;
