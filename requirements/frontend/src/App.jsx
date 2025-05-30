import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from './pages/Home';
import TestPage from './pages/TestPage';

function App() {
    return (
        <BrowserRouter>
            <div className="App">
            <Routes>
                <Route path="/" element={<Home />}/>
                <Route path="/test" element={<TestPage />}/>

            </Routes>

            </div>
        </BrowserRouter>
    );
}

export default App;