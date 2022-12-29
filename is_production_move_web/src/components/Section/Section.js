import './style.css'

/*
* @description: Section custom component
*/
function Section(props) {
    return (
        <div className={'section-wrapper ' + (props.noContainer ? "" : "section-container")}>
            <h2 className='section-title'>{props.title}</h2>
            {props.children}
        </div>
    )
}

Section.Div = function (props) {
    return (
        <div className={(props.inline ? "section-div-inline" : "section-div-flex")}>
            {props.children}
        </div>
    )
}
export { Section }