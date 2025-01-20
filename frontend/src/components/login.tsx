import "../App.css"
import {useState} from "react";

export function Login(){
    const [inputText, setInputText] = useState("");

    const UserInput = (event) => {
        const newValue = event.target.value;
        setInputText(newValue);
        console.log(inputText);
    };

    return (
        <div className="start-input">
            <p className="paragraph">Gib deinen Namen ein um zu Starten!</p>
            <input className="input-field" onChange={UserInput}/>
            <button className="submit-button">Spiel Starten</button>
        </div>
    )
}

export default Login();
