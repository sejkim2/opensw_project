import React, { useState } from "react";
import AuthModal from "../components/AuthModal";
import "./Home.css";

function Home() {
  const [showModal, setShowModal] = useState(false);
  const [isSignup, setIsSignup] = useState(false);

  return (
    <div className="home-container">
      <div className="container">
        <h1>🎬 영화 리뷰 플랫폼</h1>
        <p>당신이 사랑한 장면을 다시 느껴보세요</p>
        <button
          onClick={() => {
            setShowModal(true);
            setIsSignup(false);
          }}
        >
          영화 보러 가기 🎥
        </button>
      </div>

      {showModal && (
        <AuthModal
          isSignup={isSignup}
          setIsSignup={setIsSignup}
          setShowModal={setShowModal}
        />
      )}
    </div>
  );
}

export default Home;
