import './style.css'
import { Form } from "../../components/NestedComponents";
import { Button } from "../../components/PrimaryComponents";

export default function Login() {
    const validUsername = (input) => {
        if (input.length === 0) {
            return { state: false, content: 'Tên đăng nhập không được để trống' };
        } else if (input.length < 6) {
            return { state: false, content: 'Tên đăng nhập phải có tối thiểu 6 kí tự' };
        }
        return { state: true };
    }
    const validPassword = (input) => {
        if (input.length === 0) {
            return { state: false, content: 'Mật khẩu không được để trống' };
        } 
        return { state: true };
    }

    const onLogin = (e) => {
        e.preventDefault();
        fetch('http://25.38.98.79:8088/backend/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: "ductran",
                password: "12345"
            })
        })
        .then(res => res.json())
        // console.log('Login');
    }

    return (
        <div className='login-container'>
            <Form width='25rem'>
                <h1>Hệ thống ProductionMove</h1>
                <Form.Input label="Tên đăng nhập" type="text" validation={validUsername} />
                <Form.Input label="Mật khẩu" type="password" validation={validPassword} />
                <Button onClick={(e) => onLogin(e)}>Đăng nhập</Button>
            </Form>
        </div>
    )
}