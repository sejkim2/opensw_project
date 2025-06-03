import React, { useEffect, useState } from 'react';
import './MyPage.css';
import axios from 'axios';

const handleLogout = () => {
  localStorage.removeItem("user");
  window.location.href = "/"; 
};

const MyPage = () => {
  const [nickname, setNickname] = useState('');
  const [editing, setEditing] = useState(false);
  const [newNickname, setNewNickname] = useState('');
  const [userId, setUserId] = useState(null);
  const [user, setUser] = useState(null);

useEffect(() => {
  const user = JSON.parse(localStorage.getItem("user"));
  if (user && user.nickname) {
    setUser(user);
    setNickname(user.nickname);
    setNewNickname(user.nickname);
    setUserId(user.id);
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
        <div className="platform-title">영화 리뷰 플랫폼</div>
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

        <div className="genre-row">
          <h3>선호하는 장르</h3>
          <h5 className="edit-genre">선호하는 장르 수정</h5>
        </div>

        <div><h3> 작성한 리뷰 목록 </h3></div>
      </div>
    </div>
  );
};

export default MyPage;
