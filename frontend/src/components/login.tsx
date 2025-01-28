import "../CSS/login.css";
import { useState } from "react";

export function Login({ onSubmit }) {
    const [inputText, setInputText] = useState("");
    const [responseMessage, setResponseMessage] = useState("");

    const handleInput = (event: any) => {
        setInputText(event.target.value);
    };

    const handleSubmit = async () => {
        if (inputText.trim() !== "") {
            onSubmit();
        }

        try {
            const response = await fetch(`http://localhost:8080/user/set?username=${encodeURIComponent(inputText)}`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                }
            });

            if (!response.ok) {
                throw new Error("Fehler beim Speichern des Usernames.");
            } else {
                console.log(responseMessage)
            }

            const data = await response.text();
            setResponseMessage(data);
            onSubmit(inputText);
        } catch (error) {
            console.error("Fehler beim Senden des Usernames:", error);
            setResponseMessage("Fehler beim Speichern des Usernames.");
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
