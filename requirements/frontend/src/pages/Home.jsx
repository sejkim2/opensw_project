import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./Home.css";

function Home() {
  const [message, setMessage] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [isSignup, setIsSignup] = useState(false);
  const [genres, setGenres] = useState([]); // 장르 목록
  const [selectedGenres, setSelectedGenres] = useState([]); // 선택된 장르 ID
  const [formData, setFormData] = useState({
    username: "",
    password: "",
    passwordConfirm: "",
    nickname: "",
  });

  const navigate = useNavigate();

  // 장르 목록 불러오기
  useEffect(() => {
    if (isSignup) {
      fetchGenres();
    }
  }, [isSignup]);

  const fetchGenres = async () => {
    try {
      const res = await fetch("/api/genres");
      const data = await res.json();
      setGenres(data); // [{id: 1, name: "액션"}, ...]
    } catch (err) {
      console.error("장르 불러오기 실패:", err);
    }
  };

  // 텍스트 입력 변경 핸들러
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  // 체크박스 변경 핸들러
  const handleGenreChange = (e) => {
    const genreId = parseInt(e.target.value);
    setSelectedGenres((prev) =>
      e.target.checked
        ? [...prev, genreId]
        : prev.filter((id) => id !== genreId)
    );
  };

  // 회원가입 요청
  const handleSignup = async () => {
    if (formData.password !== formData.passwordConfirm) {
      alert("비밀번호가 일치하지 않습니다.");
      return;
    }

    try {
      const response = await fetch("/api/users/signup", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username: formData.username,
          password: formData.password,
          nickname: formData.nickname,
          preferredGenres: selectedGenres,
        }),
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "회원가입 실패");
      }

      const data = await response.json();
      alert(`회원가입 성공! 환영합니다, ${data.nickname}님.`);
      setShowModal(false);
      setFormData({
        username: "",
        password: "",
        passwordConfirm: "",
        nickname: "",
      });
      setSelectedGenres([]);
    } catch (err) {
      alert(err.message);
      console.error("회원가입 오류:", err);
    }
  };

  const handleLogin = () => {
    alert("로그인 기능은 추후 구현 예정입니다.");
  };

  return (
    <div className="home-container">
      <div className="container">
        <h1>🎬 영화 리뷰 플랫폼</h1>
        <p>당신이 사랑한 장면을 다시 느껴보세요</p>
        <button
          onClick={() => {
            setShowModal(true);
            setIsSignup(false);
          }}
        >
          영화 보러 가기 🎥
        </button>
      </div>

      {showModal && (
        <div className="modal-overlay" onClick={() => setShowModal(false)}>
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <h2>{isSignup ? "회원가입" : "로그인 또는 회원가입"}</h2>
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
              </>
            )}
            <div className="modal-buttons">
              {!isSignup ? (
                <>
                  <button onClick={handleLogin}>로그인</button>
                  <button onClick={() => setIsSignup(true)}>회원가입</button>
                </>
              ) : (
                <button onClick={handleSignup}>가입하기</button>
              )}
            </div>
            <button className="close-btn" onClick={() => setShowModal(false)}>
              닫기
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default Home;
