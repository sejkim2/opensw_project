import React, { useEffect, useState } from "react";
import './ReviewPage.css';

const ReviewPage = ({ userId: propUserId }) => {
    const [movies, setMovies] = useState([]);
    const [reviews, setReviews] = useState([]);
    const [selectedMovieId, setSelectedMovieId] = useState("");
    const [content, setContent] = useState("");
    const [rating, setRating] = useState("");
    const [showModal, setShowModal] = useState(false);

    const storedUser = JSON.parse(localStorage.getItem("user"));
    const userId = propUserId || storedUser?.id;

    useEffect(() => {
        if (userId) {
            // 선호 장르 기반 영화 목록 불러오기
            fetch(`/api/movies/recommended/${userId}`)
                .then(res => res.json())
                .then(data => {
                    setMovies(data);
                    console.log("🎬 추천 영화 목록:", data);
                })
                .catch(err => {
                    console.error("추천 영화 목록 로딩 실패:", err);
                });

            // 기존 리뷰 불러오기
            fetchReviews(userId);
        }
    }, [userId]);

    const fetchReviews = async (uid) => {
        try {
            const res = await fetch(`/api/reviews/users/${uid}`);
            if (res.ok) {
                const data = await res.json();
                console.log("📥 리뷰 조회 성공:", data);
                setReviews(data);
            } else {
                console.warn("리뷰 불러오기 실패");
            }
        } catch (err) {
            console.error("리뷰 로딩 중 오류:", err);
        }
    };

    const handleSubmit = async () => {
        if (!userId || !selectedMovieId || !content.trim() || rating === "") {
            alert("모든 정보를 정확히 입력하세요!");
            return;
        }

        const review = {
            userId: Number(userId),
            movieId: Number(selectedMovieId),
            content: content.trim(),
            rating: parseFloat(rating),
        };

        console.log("📤 리뷰 전송 데이터:", review);

        try {
            const res = await fetch("/api/reviews", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(review),
            });

            const result = await res.json();
            console.log("📥 서버 응답 데이터:", result);

            if (res.status === 201) {
                alert("리뷰가 등록되었습니다!");
                setContent("");
                setRating("");
                setSelectedMovieId("");
                setShowModal(false);
                fetchReviews(userId); 
            } else {
                alert(`리뷰 등록 실패: ${result.message}`);
            }
        } catch (err) {
            console.error("리뷰 등록 중 오류:", err);
            alert("서버 오류로 리뷰 등록에 실패했습니다.");
        }
    };

    return (
        <div className="review-container">
            <h2>📝 영화 리뷰</h2>
            <p>선호 장르 기반 추천 영화에 대한 리뷰를 작성해보세요.</p>

            <div className="review-list">
                {reviews.length === 0 ? (
                    <p>아직 등록된 리뷰가 없습니다.</p>
                ) : (
                    reviews.map((r) => (
                        <div key={r.id} className="review-item">
                            <div className="review-header">
                                <strong>{r.movieTitle}</strong>
                                <span className="created-at">
                                    🗓 {r.createdAt ? new Date(r.createdAt).toLocaleDateString("ko-KR") : "오늘"}
                                </span>
                            </div>
                            <div className="rating">⭐ {r.rating}점</div>
                            <div className="review-content">{r.content}</div>
                        </div>
                    ))
                )}
            </div>

            <button onClick={() => setShowModal(true)} className="open-form-btn">
                ✍ 리뷰 작성하기
            </button>

            {showModal && (
                <div className="modal-overlay-review" onClick={() => setShowModal(false)}>
                    <div className="modal-review" onClick={(e) => e.stopPropagation()}>
                        <h3>✍ 리뷰 작성</h3>

                        <select
                            className="form-field"
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
                            className="form-field"
                            placeholder="리뷰 내용을 입력하세요"
                            value={content}
                            onChange={(e) => setContent(e.target.value)}
                        />

                        <select
                            className="form-field"
                            value={rating}
                            onChange={(e) => setRating(e.target.value)}
                        >
                            <option value="">평점을 선택하세요</option>
                            {[...Array(11)].map((_, i) => {
                                const value = (i * 0.5).toFixed(1);
                                return (
                                    <option key={value} value={value}>
                                        {value}
                                    </option>
                                );
                            })}
                        </select>

                        <div className="button-group">
                            <button onClick={handleSubmit} className="submit-btn">등록</button>
                            <button onClick={() => setShowModal(false)} className="cancel-btn">취소</button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default ReviewPage;
