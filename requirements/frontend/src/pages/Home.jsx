import React, { useState } from "react";
import axios from "axios";
import { BrowserRouter as Router, Routes, Route, Link, useNavigate } from "react-router-dom";
import './Home.css';

function Home() {
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const fetchMessage = async () => {
    try {
      const res = await axios.get("/api/test");
      setMessage(res.data.message);
    } catch (err) {
      console.error(err);
      setMessage("API 호출 실패");
    }
  };

  return (
    <div style={{ textAlign: "center", paddingTop: "50px" }}>
      <h1>React ↔ Spring 연결 테스트</h1>
      <button onClick={fetchMessage}>Spring API 호출</button>
      <p>{message}</p>

      <div className="container">
        <h1>react 실행 테스트</h1>
        <button onClick={() => navigate("/test")}>테스트 페이지로 이동</button>
      </div>
    </div>
  );
}

export default Home;

