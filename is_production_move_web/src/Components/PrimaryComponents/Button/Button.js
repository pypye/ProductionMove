import "./style.css";

/*@function Button
    @param {object} props - Properties for the component
        * @param {boolean} loading - Boolean to show a loading spinner
        * @param {string} label - Label for the button
        * @param {string} type - Type of button (primary, secondary, etc.)
        * @param {function} onClick - Function to be called when the button is clicked
*/

export default function Button(props) {
    return (
        <button
            className={`btn ${props.type}`}
            onClick={props.onClick}
        >
            {props.children}
        </button>
    );
}
