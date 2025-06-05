import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './DetailPage.css';

const DetailPage = () => {
  const { movieId } = useParams();
  const [movie, setMovie] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get(`/api/movies/${movieId}`)
      .then(res => setMovie(res.data))
      .catch(err => {
        console.error('영화 정보 로딩 실패:', err);
        alert('해당 영화를 찾을 수 없습니다.');
        navigate('/');
      });
  }, [movieId, navigate]);

  if (!movie) return <div className="detail-loading">로딩 중...</div>;

  return (
    <div className="main-container">
      <nav className="navbar">
        <h2 className="logo" onClick={() => navigate('/main')} style={{ cursor: 'pointer' }}>
          🎬 리뷰보다
        </h2>
        <ul className="menu">
          <li onClick={() => navigate('/review')}>리뷰 쓰기</li>
          <li onClick={() => navigate('/mypage')}>마이페이지</li>
          <li onClick={() => navigate('/')}>로그아웃</li>
        </ul>
      </nav>

      <div className="detail-container">
        <img className="detail-image" src={movie.imageUrl} alt={movie.title} />
        <div className="detail-info">
          <h1> {movie.title} </h1>
          <p className="detail-description">{movie.description}</p>
          <p><strong>장르:</strong> {movie.genres.join(', ')}</p>
          <p><strong>평균 평점:</strong> ⭐ {movie.averageRating}</p>
        </div>
      </div>

      <div className="review-section">
        <h2>작성된 리뷰</h2>
        {movie.reviews.length === 0 ? (
          <p>아직 등록된 리뷰가 없습니다.</p>
        ) : (
          movie.reviews.map(review => (
            <div key={review.id} className="review-card">
              <p className="review-header">
                👤 {review.username} 님, <span className="review-rating">⭐ {review.rating} 점!!</span>
              </p>
              <p className="review-content"> <string> &gt;&gt; </string>{review.content}</p>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default DetailPage;
