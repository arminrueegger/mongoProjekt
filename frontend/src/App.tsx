import "./App.css";
import { useState } from "react";
import { Login } from "./components";
import { Quiz } from "./components";

function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    return (
        <div className="app-container">
            <h1 className="app-header">Alkohol Quiz</h1>

            <div className="app-content">
                {!isLoggedIn ? (
                    <Login onSubmit={() => setIsLoggedIn(true)} />
                ) : (
                    <Quiz />
                )}
            </div>
        </div>
    );
}

export default App;
