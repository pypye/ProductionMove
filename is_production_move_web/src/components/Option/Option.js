import React from "react"
import './style.css'

/*
* @description: Option custom component
*/
function Option(props) {
    return (
        <div className={"option-wrapper " + (props.maxWidth ? "max-width" : "")}>
            <span className="option-title">{props.title}</span>
            <select className="option-select" value={props.value} onChange={(e) => {
                if (props.onChange) props.onChange(e.target.value)
            }
            }>
                {props.children}
            </select>
        </div>
    )
}

Option.Item = function OptionItem(props) {
    return (
        <option className="option-item" value={props.value}>{props.value}</option>
    )
}
export { Option }