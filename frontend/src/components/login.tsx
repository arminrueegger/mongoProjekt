import "../CSS/login.css";
import { useState } from "react";

export function Login({ onSubmit }) {
    const [inputText, setInputText] = useState("");

    const handleInput = (event) => {
        setInputText(event.target.value);
    };

    const handleSubmit = () => {
        if (inputText.trim() !== "") {
            onSubmit();
        }
    };

    return (
        <div className="login-container">
            <p className="login-text">Gib deinen Namen ein, um zu starten!</p>
            <input className="login-input" onChange={handleInput} value={inputText} />
            <button className="login-button" onClick={handleSubmit}>
                Spiel Starten
            </button>
        </div>
    );
}
