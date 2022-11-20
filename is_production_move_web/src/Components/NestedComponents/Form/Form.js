import { Input } from '../../PrimaryComponents';
import './style.css'
function Form(props){
    return (
        <form className="form" style={{width: props.width}}>
            {props.children}
        </form>
    )
}

Form.Input = Input;
export default Form;