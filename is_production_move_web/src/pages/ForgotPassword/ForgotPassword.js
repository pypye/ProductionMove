import './style.css'
import React from 'react';
import { Form } from "../../components";
import { Button } from "../../components";
import { fetch } from '../../sessions';
import { useNavigate } from 'react-router-dom';

function ForgotPassword() {
    const [forgotInfo, setForgotInfo] = React.useState({ username: '', email: '' });
    const [error, setError] = React.useState("");
    const navigate = useNavigate();

    const validUsername = (input) => {
        if (input.length === 0) {
            return { state: false, content: 'Tên đăng nhập không được để trống' };
        } else if (input.length < 6) {
            return { state: false, content: 'Tên đăng nhập phải có tối thiểu 6 kí tự' };
        }
        return { state: true };
    }
    const validEmail = (input) => {
        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        if (input.length === 0) {
            return { state: false, content: 'Email không được để trống' };
        } else if (!emailPattern.test(input)) {
            return { state: false, content: 'Email không hợp lệ' };
        }
        return { state: true };
    }

    const validForgot = () => {
        if (validUsername(forgotInfo.username).state === false || validEmail(forgotInfo.email).state === false) {
            return true;
        }
        return false;
    }

    const onForgot = (e) => {
        e.preventDefault();
        fetch('/backend/auth/forgot', 'POST', forgotInfo).then(data => {
            if (data.status.code === 'SUCCESS') {
                console.log(data);
                console.log(forgotInfo.email)
                navigate('/reset-password', { state: { email: forgotInfo.email }})
            } else {
                setError("Tên đăng nhập và email không khớp");
            }
        })

    }

    return (
        <div className='forgot-container'>
            <Form width='25rem'>
                <h2>Quên mật khẩu?</h2>
                <p className='notify'>Hãy nhập tên tài khoản và email liên kết với tài khoản đó. Chúng tôi sẽ gửi một email cho bạn để đặt lại mật khẩu.</p>
                <Form.Input label="Tên đăng nhập" type="text" validation={validUsername} value={forgotInfo.username} onChange={(e) => setForgotInfo({ ...forgotInfo, username: e })} />
                <Form.Input label="Email" type="email" validation={validEmail} value={forgotInfo.email} onChange={(e) => setForgotInfo({ ...forgotInfo, email: e })} />
                <p style={{ "display": error === "" ? "none" : "block" }} className="login-error">{error}</p>
                <Button onClick={(e) => onForgot(e)} validation={validForgot}>Gửi yêu cầu</Button>
                <a href='/login' className='login-back'>Quay lại đăng nhập</a>
            </Form>
        </div>
    )
} export default ForgotPassword