import React from "react"
import './style.css'

function Option(props) {
    return (
        <div className={"option-wrapper " + (props.maxWidth ? "max-width" : "")}>
            <span className="option-title">{props.title}</span>
            <select className="option-select" onChange={props.onChange}>
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