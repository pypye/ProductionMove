import './style.css'
import { Button } from '../Button/Button';
import { Input } from '../Input/Input';


/*
* @description: Form custom component
*/
function Form(props) {
    return (
        <form className={"form " + (props.noContainer ? "" : "form-container")} style={{ width: props.width }}>
            {props.children}
        </form>
    )
}

Form.Split = function FormSplit(props) {
    return (
        <div className={"form-split " + props.format}>
            {props.children}
        </div>
    )
}

Form.SplitFlex = function FormSplitFlex(props) {
    return (
        <div className={"form-split-flex " + props.format}>
            {props.children}
        </div>
    )
}

Form.Title = function FormTitle(props) {
    return (
        <h2 className="form-title">{props.content}</h2>
    )
}

Form.Subtitle = function FormSubtitle(props) {
    return (
        <h3 className="form-title">{props.content}</h3>
    )
}

Form.Notify = function FormNotify(props) {
    return (
        props.enabled && <p className="form-notify">{props.content}{props.children}</p>
    )
}

Form.Warning = function FormNotify(props) {
    return (
        props.enabled && <p className="form-warning">{props.content}{props.children}</p>
    )
}

Form.Error = function FormError(props) {
    return (
        props.enabled && <p className="form-error">{props.content}</p>
    )
}

Form.Input = Input;

Form.Submit = function FormSubmit(props) {
    return (
        <Button type={props.type ? props.type : "primary"} onClick={props.onClick} validation={props.validation}>{props.content}</Button>
    )
}

Form.Link = function FormLink(props) {
    return (
        <a className="form-link" href={props.href}>{props.content}</a>
    )
}

export { Form }