import "../CSS/quiz.css";
import { useState } from "react";
import { Question } from "./Question";

export function Quiz() {
    const [selectedQuestion, setSelectedQuestion] = useState<string | null>(null);
    const [questionCount, setQuestionCount] = useState(0);

    const handleQuestionComplete = () => {
        if (questionCount >= 4) {
            // TODO: Implement statistics
            setQuestionCount(0);
            setSelectedQuestion(null);
        } else {
            setQuestionCount(prev => prev + 1);
            setSelectedQuestion(null);
        }
    };

    return (
        <div className="quiz-container">
            {selectedQuestion === null ? (
                <>
                    <h3 className="question-counter">Frage {questionCount + 1} von 5</h3>
                    <ol className="quiz-list">
                        <li className="quiz-item" onClick={() => setSelectedQuestion("Prozentanteil")}>
                            Prozentanteil
                        </li>
                        <li className="quiz-item" onClick={() => setSelectedQuestion("Erstellungsjahr")}>
                            Erstellungsjahr
                        </li>
                        <li className="quiz-item" onClick={() => setSelectedQuestion("Tageskonsum")}>
                            Tageskonsum
                        </li>
                    </ol>
                </>
            ) : (
                <Question 
                    question={selectedQuestion} 
                    onBack={handleQuestionComplete}
                />
            )}
        </div>
    );
}
