// src/MainPage.js
import { useNavigate } from 'react-router-dom';
import "./MainPage.css";

const MainPage = () => {
    const navigate = useNavigate();

    return (
        <div className="main-container">
            <nav className="navbar">
                <h2 className="logo">🎬 리뷰플랫폼</h2>
                <ul className="menu">
                    {/* <li onClick={() => navigate('/search')}>영화 검색</li> */}
                    <li onClick={() => navigate('/review')}>리뷰 쓰기</li>
                    {/* <li onClick={() => navigate('/popular')}>인기 영화</li>
          <li onClick={() => navigate('/mypage')}>마이페이지</li> */}
                    {/* <li className="logout" onClick={() => navigate('/')}>로그아웃</li> */}
                </ul>
            </nav>

            <div className="welcome">
                <h1>환영합니다! 👋</h1>
                <p>지금 가장 핫한 영화를 검색하고 리뷰를 남겨보세요.</p>

            </div>
        </div>
    );
};

export default MainPage;