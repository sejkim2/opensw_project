import React, { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import './ReviewPage.css';

const ReviewPage = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const movieId = location.state?.movieId;

    const user = JSON.parse(localStorage.getItem("user"));
    const userId = user?.id;

    const [content, setContent] = useState('');
    const [rating, setRating] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        const parsedRating = parseFloat(rating);
        if (isNaN(parsedRating) || parsedRating < 0.0 || parsedRating > 5.0) {
            alert("평점은 0.0 이상 5.0 이하의 숫자여야 합니다.");
            return;
        }

        try {
            const response = await fetch('/api/reviews', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    userId,
                    movieId,
                    content,
                    rating: parsedRating,
                }),
            });

            if (response.status === 201) {
                alert("리뷰가 성공적으로 등록되었습니다!");
                navigate('/');
            } else {
                const error = await response.json();
                alert(error.message || "리뷰 등록 중 오류 발생");
            }
        } catch (error) {
            console.error("리뷰 등록 실패:", error);
            alert("리뷰 등록 중 오류가 발생했습니다.");
        }
    };

    if (!movieId) {
        return <div>영화 정보가 없습니다. 올바른 접근인지 확인해주세요.</div>;
    }

    return (
        <div className="review-form-container">
            <h2>🎬 리뷰 작성</h2>
            <form onSubmit={handleSubmit}>
                <textarea
                    placeholder="리뷰 내용을 입력하세요"
                    value={content}
                    onChange={(e) => setContent(e.target.value)}
                    rows="6"
                    required
                />
                <input
                    type="number"
                    step="0.1"
                    min="0"
                    max="5"
                    value={rating}
                    onChange={(e) => setRating(e.target.value)}
                    placeholder="평점 (0.0 ~ 5.0)"
                    required
                />
                <button type="submit">리뷰 등록</button>
            </form>
        </div>
    );
};

export default ReviewPage;
