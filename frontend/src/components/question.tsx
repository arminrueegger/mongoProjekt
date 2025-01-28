import "../CSS/question.css";
import {useState} from "react";
import {Simulate} from "react-dom/test-utils";
import error = Simulate.error;

export function Question({ question, onBack }) {

    const [answers, setAnswers] = useState()

    fetch("").then((response) => {
        setAnswers(response);
    }).catch((error) => {
        console.error("Fehler beim Abrufen der Frage: ", error);
    })


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
            <div>
                {answers.map((answer, id) => {
                    <button key={id}>
                        {answer}
                    </button>
                })}
            </div>
            <button className="back-button" onClick={onBack}>
                Zurück zur Auswahl
            </button>
        </div>
    );
}
