import './MyPage.css';

const MyPage = () => {
  return (
    <div className="mypage-wrapper">

      <div className="mypage-header">
        <div className="platform-title">영화 리뷰 플랫폼</div>
        <div className="logout-text">로그아웃</div>
      </div>



      <div className="mypage-card">
        <div class="nickname-box"> <span class="nickname-text">닉네임</span> </div>
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
