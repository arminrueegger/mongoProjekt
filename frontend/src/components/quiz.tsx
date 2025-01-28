import "../CSS/quiz.css";
import { useState } from "react";
import { Question } from "./Question.tsx";

export function Quiz() {
    const [selectedQuestion, setSelectedQuestion] = useState(null);

    const handleClick = (category) => {
        setSelectedQuestion(category);
    };

    return (
        <div className="quiz-container">
            {selectedQuestion === null ? (
                <ol className="quiz-list">
                    <li className="quiz-item" onClick={() => handleClick("Prozentanteil")}>Prozentanteil</li>
                    <li className="quiz-item" onClick={() => handleClick("Erstellungsjahr")}>Erstellungsjahr</li>
                    <li className="quiz-item" onClick={() => handleClick("Tageskonsum")}>Tageskonsum</li>
                </ol>
            ) : (
                <Question question={selectedQuestion} onBack={() => setSelectedQuestion(null)} />
            )}
        </div>
    );
}
