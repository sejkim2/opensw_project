import React, { useState } from 'react';
import './ReviewPage.css';

const MovieReview = () => {
    const [reviews, setReviews] = useState([
        { title: '오펜하이머', content: '정말 깊이 있는 전개와 압도적인 사운드! 추천합니다!' },
        { title: '라라랜드', content: 'OST와 감성이 정말 좋아요. 몇 번을 봐도 질리지 않아요.' },
    ]);

    const [showModal, setShowModal] = useState(false);
    const [newTitle, setNewTitle] = useState('');
    const [newContent, setNewContent] = useState('');

    const handleAddReview = () => {
        if (!newTitle || !newContent) {
            alert('제목과 내용을 모두 입력해주세요.');
            return;
        }

        const newReview = { title: newTitle, content: newContent };
        setReviews([newReview, ...reviews]);
        setNewTitle('');
        setNewContent('');
        setShowModal(false);
    };

    return (
        <div className="review-container">
            <h2>📝 영화 리뷰</h2>
            <p>내가 남긴 리뷰 또는 최신 리뷰들을 볼 수 있어요.</p>

            <div className="dummy-review">
                {reviews.map((review, index) => (
                    <div key={index}>
                        <h3>🎬 <strong>{review.title}</strong></h3>
                        <p>{review.content}</p>
                        <hr />
                    </div>
                ))}
            </div>

            <button onClick={() => setShowModal(true)} className="open-form-btn">
                ✍ 리뷰 작성하기
            </button>

            {showModal && (
                <div className="modal-overlay-review" onClick={() => setShowModal(false)}>
                    <div className="modal-review" onClick={(e) => e.stopPropagation()}>
                        <h3>✍ 리뷰 작성</h3>
                        <input
                            type="text"
                            placeholder="영화 제목"
                            value={newTitle}
                            onChange={(e) => setNewTitle(e.target.value)}
                        />
                        <textarea
                            placeholder="리뷰 내용"
                            value={newContent}
                            onChange={(e) => setNewContent(e.target.value)}
                        />
                        <div style={{ display: 'flex', gap: '10px' }}>
                            <button onClick={handleAddReview}>등록</button>
                            <button onClick={() => setShowModal(false)}>취소</button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default MovieReview;