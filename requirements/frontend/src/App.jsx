import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from './pages/Home';
import MainPage from './pages/MainPage';
<<<<<<< HEAD
import ReviewPage from './pages/ReviewPage';
=======
import MyPage from './pages/MyPage';
>>>>>>> origin/master

function App() {
    return (
        <BrowserRouter>
            <div className="App">
            <Routes>
                <Route path="/" element={<Home />}/>
                <Route path="/main" element={<MainPage />}/>
<<<<<<< HEAD
                <Route path="/review" element={<ReviewPage />}/>

=======
                <Route path="/MyPage" element={<MyPage />}/>
>>>>>>> origin/master

            </Routes>

            </div>
        </BrowserRouter>
    );
}

export default App;