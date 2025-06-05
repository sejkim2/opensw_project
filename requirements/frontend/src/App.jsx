// 이 위에 아무것도 두지 마세요 — 공백 줄, 주석 등
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from './pages/Home';
import MainPage from './pages/MainPage';
import ReviewPage from './pages/ReviewPage';
import MyPage from './pages/MyPage';
import DetailPage from './pages/DetailPage';

function App() {
    return (
        <BrowserRouter>
            <div className="App">
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/main" element={<MainPage />} />
                    <Route path="/review" element={<ReviewPage />} />
                    <Route path="/MyPage" element={<MyPage />} />
                    <Route path="/detail/:movieId" element={<DetailPage />} />
                </Routes>
            </div>
        </BrowserRouter>
    );
}

export default App;
