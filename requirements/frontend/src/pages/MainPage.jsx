import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./MainPage.css";

const MainPage = () => {
    const navigate = useNavigate();
    const [popularMovies, setPopularMovies] = useState([]);
    const [recommendedMovies, setRecommendedMovies] = useState([]);
    const user = JSON.parse(localStorage.getItem("user"));
    const userId = user?.id;

    useEffect(() => {
        axios.get('/api/movies/popular?limit=5')
            .then(res => setPopularMovies(res.data))
            .catch(err => console.error("인기 영화 불러오기 실패:", err));

        if (userId) {
            axios.get(`/api/movies/recommended/${userId}`)
                .then(res => setRecommendedMovies(res.data))
                .catch(err => {
                    console.warn("추천 영화 없음 또는 사용자 정보 없음");
                });
        }
    }, [userId]);

    const renderMovieCard = (movie) => (
        <div
            key={movie.id}
            className="movie-card"
            onClick={() => navigate(`/detail/${movie.id}`)}
            style={{ cursor: "pointer" }}
        >
            <img
                src={movie.imageUrl}
                alt={movie.title}
                className="movie-image"
            />
            <div className="movie-info">
                <h4>{movie.title}</h4>
                <p>⭐ {movie.averageRating}</p>
                <p>🎬 {movie.genres.join(", ")}</p>
            </div>
        </div>
    );


    return (
        <div className="main-container">
            <nav className="navbar">
                <h2 className="logo">🎬 리뷰보다</h2>
                <ul className="menu">
                    <li onClick={() => navigate('/review')}>리뷰 쓰기</li>
                    <li onClick={() => navigate('/mypage')}>마이페이지</li>
                    <li onClick={() => navigate('/')}>로그아웃</li>
                </ul>
            </nav>

            <div className="welcome">
                <section>
                    <h3>🔥 지금 인기 있는 영화 TOP 5</h3>
                    <div className="movie-list">
                        {popularMovies.length === 0
                            ? <p>인기 영화가 없습니다.</p>
                            : popularMovies.map(renderMovieCard)}
                    </div>
                </section>

                <section>
                    <h3>🎯 {user?.nickname || "회원"}님이 좋아할 영화</h3>
                    <div className="movie-list">
                        {recommendedMovies.length === 0
                            ? <p>선호 장르에 맞는 추천 영화가 없습니다.</p>
                            : recommendedMovies.map(renderMovieCard)}
                    </div>
                </section>
            </div>
        </div>
    );
};

export default MainPage;
