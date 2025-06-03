import React, { useEffect, useState } from "react";
import './ReviewPage.css';

const ReviewPage = ({ userId }) => {
    const [movies, setMovies] = useState([]);
    const [selectedMovieId, setSelectedMovieId] = useState("");
    const [content, setContent] = useState("");
    const [rating, setRating] = useState(0);
    const [showModal, setShowModal] = useState(false);

    useEffect(() => {
        // 백엔드 API가 없으므로 임시 하드코딩
        setMovies([
            { id: 1, title: "Interstellar" },
            { id: 2, title: "Inception" },
            { id: 3, title: "La La Land" },
        ]);
    }, []);

    const handleSubmit = async () => {
        if (!selectedMovieId || !content || rating < 0 || rating > 5) {
            alert("모든 정보를 정확히 입력하세요!");
            return;
        }

        const review = {
            userId: userId,
            movieId: Number(selectedMovieId),
            content: content,
            rating: parseFloat(rating),
        };

        try {
            const res = await fetch("/api/reviews", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(review),
            });

            if (res.status === 201) {
                alert("리뷰가 등록되었습니다!");
                setContent("");
                setRating(0);
                setSelectedMovieId("");
                setShowModal(false);
            } else if (res.status === 409) {
                const error = await res.json();
                alert(`리뷰 등록 실패: ${error.message}`);
            } else {
                const error = await res.json();
                alert(`리뷰 등록 실패: ${error.message}`);
            }
        } catch (err) {
            console.error("리뷰 등록 중 오류:", err);
        }
    };

    return (
        <div className="review-container">
            <h2>📝 영화 리뷰</h2>
            <p>보고 싶은 영화에 대한 리뷰를 작성해보세요.</p>

            <button onClick={() => setShowModal(true)} className="open-form-btn">
                ✍ 리뷰 작성하기
            </button>

            {showModal && (
                <div className="modal-overlay" onClick={() => setShowModal(false)}>
                    <div className="modal" onClick={(e) => e.stopPropagation()}>
                        <h3>✍ 리뷰 작성</h3>

                        <select
                            value={selectedMovieId}
                            onChange={(e) => setSelectedMovieId(e.target.value)}
                        >
                            <option value="">영화를 선택하세요</option>
                            {movies.map((movie) => (
                                <option key={movie.id} value={movie.id}>
                                    {movie.title}
                                </option>
                            ))}
                        </select>

                        <textarea
                            placeholder="리뷰 내용을 입력하세요"
                            value={content}
                            onChange={(e) => setContent(e.target.value)}
                        />

                        <input
                            type="number"
                            placeholder="평점 (0.0 ~ 5.0)"
                            value={rating}
                            onChange={(e) => setRating(e.target.value)}
                            step="0.1"
                            min="0"
                            max="5"
                        />

                        <div style={{ display: 'flex', gap: '10px' }}>
                            <button onClick={handleSubmit}>등록</button>
                            <button onClick={() => setShowModal(false)}>취소</button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default ReviewPage;
