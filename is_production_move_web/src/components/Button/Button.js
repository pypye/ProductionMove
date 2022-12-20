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
            type="button"
            className={`btn ${props.type} ` + (props.validation() ? '' : 'disabled')}
            onClick={(e) => {
                e.preventDefault();
                props.onClick()
            }}
            disabled={!props.validation()}
        >
            {props.children}
        </button>
    );
}
