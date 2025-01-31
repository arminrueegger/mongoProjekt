import "../CSS/quiz.css";
import { useState } from "react";
import { Question } from "./question";

export function Quiz() {
    const [selectedCategory, setSelectedCategory] = useState<string | null>(null);
    const [questionCount, setQuestionCount] = useState(0);
    const [correctAnswers, setCorrectAnswers] = useState(0);
    const [showStats, setShowStats] = useState(false);

    const handleQuestionComplete = (wasCorrect: boolean) => {
        if (wasCorrect) {
            setCorrectAnswers(prev => prev + 1);
        }
        
        if (questionCount >= 4) {
            setShowStats(true);
        } else {
            setQuestionCount(prev => prev + 1);
        }
    };

    const handleNewQuiz = () => {
        setQuestionCount(0);
        setCorrectAnswers(0);
        setSelectedCategory(null);
        setShowStats(false);
    };

    return (
        <div className="quiz-container">
            {showStats ? (
                <div className="stats-container">
                    <h2 className="stats-title">Quiz beendet!</h2>
                    <p className="stats-text">Richtige Antworten: {correctAnswers} von 5</p>
                    <p className="stats-percentage">
                        {Math.round((correctAnswers / 5) * 100)}% richtig
                    </p>
                    <button className="new-quiz-button" onClick={handleNewQuiz}>
                        Neues Quiz starten
                    </button>
                </div>
            ) : selectedCategory === null ? (
                <>
                    <h3 className="category-title">Wähle eine Kategorie:</h3>
                    <ol className="quiz-list">
                        <li className="quiz-item" onClick={() => setSelectedCategory("Prozentanteil")}>
                            Prozentanteil
                        </li>
                        <li className="quiz-item" onClick={() => setSelectedCategory("Erstellungsjahr")}>
                            Erstellungsjahr
                        </li>
                        <li className="quiz-item" onClick={() => setSelectedCategory("Tageskonsum")}>
                            Tageskonsum
                        </li>
                    </ol>
                </>
            ) : (
                <>
                    <h3 className="question-counter">Frage {questionCount + 1} von 5</h3>
                    <Question 
                        question={selectedCategory} 
                        onBack={(wasCorrect) => handleQuestionComplete(wasCorrect)}
                    />
                </>
            )}
        </div>
    );
}
