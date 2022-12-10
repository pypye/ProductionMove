import './style.css'
import React from 'react';
import { Form } from "../../components";
import { Button } from "../../components";


export default function Login() {

    const [loginInfo, setLoginInfo] = React.useState({ username: '', password: '' });
    const [error, setError] = React.useState("");

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
        fetch('http://localhost:3001/backend/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                username: loginInfo.username,
                password: loginInfo.password
            })
        }).then(res => res.json()).then(data => {
            if (data.status.code === "SUCCESS") {
                console.log(data)
            } else if (data.status.code === "E-011") {
                setError("Tên đăng nhập hoặc mật khẩu không đúng")
            } else {
                alert("Lỗi không xác định")
                console.log(data)
            }
        })
    }

    return (
        <div className='login-container'>
            <Form width='25rem'>
                <h1>Hệ thống ProductionMove</h1>
                <Form.Input label="Tên đăng nhập" type="text" validation={validUsername} value={loginInfo.username} onChange={(e) => setLoginInfo({ ...loginInfo, username: e })} />
                <Form.Input label="Mật khẩu" type="password" validation={validPassword} value={loginInfo.password} onChange={(e) => setLoginInfo({ ...loginInfo, password: e })} />
                <p style={{"display": error === "" ? "none" : "block"}} className="login-error">{error}</p>
                <Button onClick={(e) => onLogin(e)} validation={validLogin}>Đăng nhập</Button>
            </Form>
        </div>
    )
}