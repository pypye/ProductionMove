import './style.css'

function Section(props) {
    return (
        <div className='section-wrapper'>
            <h2 className='section-title'>{props.title}</h2>
            {props.children}
        </div>
    )
} export { Section }