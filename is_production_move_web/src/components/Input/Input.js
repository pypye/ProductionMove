import React from "react";
import "./style.css"

/*
* @description: Input custom component
*/
function Input(props) {
    const ref = React.useRef(null);
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

    React.useEffect(() => {
        if (ref.current.value) ref.current.previousSibling.classList.add("focus");
    }, [ref])

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
                ref={ref}
                className="input"
                type={props.type}
                value={props.reference && props.reference[0]}
                disabled={props.disabled}
                onInput={(event) => {
                    if (props.reference && props.reference[2]) displayValidation(event, props.reference[2]);
                    if (props.reference && props.reference[1]) props.reference[1](event.target.value);
                }}
                onFocus={(event) => changeLabelPosition(event, true)}
                onBlur={(event) => {
                    changeLabelPosition(event, false);
                    if (props.reference && props.reference[2]) displayValidation(event, props.reference[2]);
                }}
            />
            <p style={{ "display": error === "" ? "none" : "block" }} className="error">{error}</p>
        </div>

    );
}
export { Input }            