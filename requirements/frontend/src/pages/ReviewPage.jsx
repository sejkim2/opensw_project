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

    // 🎬 더미 영화 데이터
    useEffect(() => {
        const dummyMovies = [
            { id: 1, title: "인셉션" },
            { id: 2, title: "인터스텔라" },
            { id: 3, title: "라라랜드" },
        ];
        setMovies(dummyMovies);
    }, []);

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

        console.log("📤 등록 요청 데이터:", review);

        try {
            const res = await fetch("/api/reviews", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(review),
            });

            const result = await res.json();
            console.log("📥 응답 상태코드:", res.status, "📥 응답 내용:", result);

            if (res.status === 201) {
                alert("리뷰가 등록되었습니다!");
                setContent("");
                setRating("");
                setSelectedMovieId("");
                setShowModal(false);

                // ✅ 새 리뷰를 목록에 바로 추가
                setReviews((prevReviews) => [...prevReviews, result]);
            } else if (res.status === 409) {
                alert(`리뷰 등록 실패: ${result.message}`);
            } else {
                alert(`리뷰 등록 실패: ${result.message}`);
            }
        } catch (err) {
            console.error("리뷰 등록 중 오류:", err);
            alert("서버 오류로 리뷰 등록에 실패했습니다.");
        }
    };

    const getMovieTitleById = (id) => {
        const movie = movies.find((m) => m.id === id);
        return movie ? movie.title : `영화 ID ${id}`;
    };

    return (
        <div className="review-container">
            <h2>📝 영화 리뷰</h2>
            <p>보고 싶은 영화에 대한 리뷰를 작성해보세요.</p>

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
                            {Array.isArray(movies) &&
                                movies.map((movie) => (
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

            {/* ✅ 리뷰 목록 출력 */}
            <div className="review-list">
                <h3>📚 등록된 리뷰 목록</h3>
                {reviews.length === 0 ? (
                    <p>아직 등록된 리뷰가 없습니다.</p>
                ) : (
                    reviews.map((r) => (
                        <div key={r.id} className="review-item">
                            <strong>{getMovieTitleById(r.movieId)}</strong> - 평점: {r.rating}<br />
                            <span>{r.content}</span>
                        </div>
                    ))
                )}
            </div>
        </div>
    );
};

export default ReviewPage;
