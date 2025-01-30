import "../CSS/question.css";
import { useState, useEffect } from "react";

interface QuestionProps {
    question: string;
    onBack: () => void;
}

interface QuestionData {
    name: string;
}

export function Question({ question, onBack }: QuestionProps) {
    const [answers, setAnswers] = useState<string[]>([]);
    const [error, setError] = useState<string>("");

    useEffect(() => {
        const category = question.toLowerCase();
        fetch(`http://localhost:8080/question/${category}`)
            .then((response) => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then((data: QuestionData[]) => {
                if (Array.isArray(data)) {
                    setAnswers(data.map(item => item.name));
                    setError("");
                } else {
                    throw new Error("Data is not in expected format");
                }
            })
            .catch((error) => {
                console.error("Fehler beim Abrufen der Frage: ", error);
                setError("Fehler beim Laden der Fragen");
            });
    }, [question]);

    return (
        <div className="question-container">
            <h2 className="question-title">{question}</h2>
            {question === "Prozentanteil"
                ? <p className="question-text">Welches Getränk hat den höchsten {question}?</p>
                : question === "Erstellungsjahr"
                    ? <p className="question-text">Welches Getränk hat das älteste {question}?</p>
                    : question === "Tageskonsum"
                        ? <p className="question-text">Welches Getränk hat den höchsten {question}?</p>
                        : <p>Error</p>
            }
            <div className="answers-container">
                {error ? (
                    <p className="error-message">{error}</p>
                ) : answers.length > 0 ? (
                    answers.map((answer, id) => (
                        <button key={id} className="answer-button">{answer}</button>
                    ))
                ) : (
                    <p>Lade Antworten...</p>
                )}
            </div>
            <button className="back-button" onClick={onBack}>
                Zurück zur Auswahl
            </button>
        </div>
    );
}
