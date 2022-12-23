import React from "react";
import "./style.css"
/*@function Input
    @param {object} props - Properties for the component
        * @param {boolean} disabled - Boolean to disable the input
        * @param {string} label - Label for the input
        * @param {string} type - Type of input (text, password, etc.)
        * @param {string} value - Value for the input
        * @param {function} validation - Function to validate the input
*/
export default function Input(props) {
    const [error, setError] = React.useState("");

    const changeLabelPosition = (event, focus) => {
        var label = event.target.previousSibling;
        if (focus) {
            label.classList.add("focus");

        } else {
            if (event.target.value === '') {
                label.classList.remove("focus");
            }
        }
    }

    const displayValidation = (event, validation_function) => {
        var result = validation_function(event.target.value);
        var label = event.target.previousSibling;
        if (result.state === false) {
            label.classList.add("error");
            event.target.classList.add("error");
            setError(result.content);

        } else {
            label.classList.remove("error");
            event.target.classList.remove("error");
            setError('');
        }
    }

    return (
        <div className="input-wrapper">
            <label className="label" disabled={props.disabled}>{props.label}</label>
            <input
                className="input"
                type={props.type}
                value={props.reference[0]}
                disabled={props.disabled}
                onInput={(event) => {
                    displayValidation(event, props.reference[2])
                    props.reference[1](event.target.value)
                }}
                onFocus={(event) => changeLabelPosition(event, true)}
                onBlur={(event) => {
                    changeLabelPosition(event, false);
                    displayValidation(event, props.reference[2])
                }}
            />
            <p style={{ "display": error === "" ? "none" : "block" }} className="error">{error}</p>
        </div>

    );
}