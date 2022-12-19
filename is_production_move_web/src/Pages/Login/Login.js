import './style.css'
import React from 'react';
import { Form } from "../../components";
import { Button } from "../../components";
import { fetch, setAuth } from '../../sessions';
import { useLocation } from 'react-router-dom';

export default function Login() {
    const [loginInfo, setLoginInfo] = React.useState({ username: '', password: '' });
    const [error, setError] = React.useState("");
    const [notify, setNotify] = React.useState(false);
    const { state } = useLocation();

    React.useEffect(() => {
        if (state) {
            setNotify(true);
            window.history.replaceState({}, document.title)
        }
    }, [state])


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

    const validLogin = () => {
        if (validUsername(loginInfo.username).state === false || validPassword(loginInfo.password).state === false) {
            return true;
        }
        return false;
    }

    const onLogin = (e) => {
        e.preventDefault();
        fetch('/backend/auth/login', 'POST', loginInfo).then(data => {
            if (data.status.code === "SUCCESS") {
                setAuth(data.data)
                window.location.href = "/"
            } else if (data.status.code === "E-003") {
                setError("Tên đăng nhập hoặc mật khẩu không đúng")
            } else {
                setError("Đã có lỗi xảy ra, vui lòng thử lại sau")
            }
        })
    }

    return (
        <div className='login-container'>
            <Form width='25rem'>
                <h2>Đăng nhập hệ thống ProductionMove</h2>
                {notify && <p className='notify'>Mật khẩu của bạn đã được đặt lại thành công. Bây giờ, hãy đăng nhập với mật khẩu mới</p>}
                <Form.Input label="Tên đăng nhập" type="text" validation={validUsername} value={loginInfo.username} onChange={(e) => setLoginInfo({ ...loginInfo, username: e })} />
                <Form.Input label="Mật khẩu" type="password" validation={validPassword} value={loginInfo.password} onChange={(e) => setLoginInfo({ ...loginInfo, password: e })} />
                <p style={{ "display": error === "" ? "none" : "block" }} className="login-error">{error}</p>
                <Button onClick={(e) => onLogin(e)} validation={validLogin}>Đăng nhập</Button>
                <a href='/forgot-password' className='forgot-password'>Quên mật khẩu?</a>
            </Form>
        </div>
    )
}