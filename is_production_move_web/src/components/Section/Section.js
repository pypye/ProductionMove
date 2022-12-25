import './style.css'

function Section(props) {
    return (
        <div className={'section-wrapper ' + (props.noContainer ? "" : "section-container")}>
            <h2 className='section-title'>{props.title}</h2>
            {props.children}
        </div>
    )
} export { Section }