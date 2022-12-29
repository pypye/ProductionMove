import "./style.css";

/*
* @description: Button custom component
*/
function Button(props) {
    return (
        <button
            type="button"
            className={`btn ${props.type} ` + (props.validation && !props.validation() ? 'disabled' : '')}
            onClick={(e) => {
                e.preventDefault();
                props.onClick();
            }}
            disabled={props.validation ? !props.validation() : false}
        >
            {props.children}
        </button>
    );
}
export { Button }