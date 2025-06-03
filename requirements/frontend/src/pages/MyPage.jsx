import React, { useEffect, useState } from 'react';
import './MyPage.css';

  const handleLogout = () => {
    localStorage.removeItem("user");
    window.location.href = "/";
  };

const MyPage = () => {

  const [nickname, setNickname] = useState('');
    useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));
    if (user && user.nickname) {
      setNickname(user.nickname);
    }
  }, []);

  return (

    <div className="mypage-wrapper">

      <div className="mypage-header">
        <div className="platform-title">영화 리뷰 플랫폼</div>
            <div className="logout-text" onClick={handleLogout} style={{ cursor: 'pointer' }}>
            로그아웃
            </div>
      </div>

      <div className="mypage-card">
        <div class="nickname-box"> <span className="nickname-text">{nickname || '닉네임'}</span> </div>
        <h5> 닉네임 수정 </h5>
        <div className="nickname-line"></div>

        <div className="genre-row">
          <h3>선호하는 장르</h3>
          <h5 className="edit-genre">선호하는 장르 수정</h5>
        </div>
        
        <div> <h3> 작성한 리뷰 목록 </h3> </div>
      </div> 

    </div>
  );
};

export default MyPage;
