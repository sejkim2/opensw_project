import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from './pages/Home';
import MainPage from './pages/MainPage';

function App() {
    return (
        <BrowserRouter>
            <div className="App">
            <Routes>
                <Route path="/" element={<Home />}/>
                <Route path="/main" element={<MainPage />}/>

            </Routes>

            </div>
        </BrowserRouter>
    );
}

export default App;