import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from './pages/Home';
import MainPage from './pages/MainPage';
import MyPage from './pages/MyPage';

function App() {
    return (
        <BrowserRouter>
            <div className="App">
            <Routes>
                <Route path="/" element={<Home />}/>
                <Route path="/main" element={<MainPage />}/>
                <Route path="/MyPage" element={<MyPage />}/>

            </Routes>

            </div>
        </BrowserRouter>
    );
}

export default App;