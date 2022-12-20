import './style.css'
import React from 'react';
import { Form } from "../../components";
import { useNavigate } from 'react-router-dom';
import { UseFetch, UseValidation } from '../../utils';
import { LoginLayout } from '../../layouts';

function ForgotPassword() {
    const [forgotUsername, setForgotUsername] = React.useState('');
    const [forgotEmail, setForgotEmail] = React.useState('');
    const forgotInfo = React.useMemo(() => ({ username: forgotUsername, email: forgotEmail }), [forgotUsername, forgotEmail]);
    const [error, setError] = React.useState("");
    const navigate = useNavigate();

    const validForgot = () => {
        return UseValidation.username(forgotUsername).state && UseValidation.email(forgotEmail).state;
    }

    const onForgot = () => {
        UseFetch('/backend/auth/forgot', 'POST', forgotInfo).then(data => {
            if (data.status.code === 'SUCCESS') {
                navigate('/reset-password', { state: { email: forgotEmail } })
            } else {
                setError("Tên đăng nhập và email không khớp");
            }
        })

    }

    return (
        <LoginLayout>
            <Form width='25rem'>
                <Form.Title content="Quên mật khẩu" />
                <Form.Notify enabled={true} content="Hãy nhập tên tài khoản và email liên kết với tài khoản đó. Chúng tôi sẽ gửi một email cho bạn để đặt lại mật khẩu." />
                <Form.Input label="Tên đăng nhập" type="text" reference={[forgotUsername, setForgotUsername, UseValidation.username]} />
                <Form.Input label="Email" type="email" reference={[forgotEmail, setForgotEmail, UseValidation.email]} />
                <Form.Error enable={error !== ""} content={error} />
                <Form.Submit content="Gửi yêu cầu" onClick={(e) => onForgot(e)} validation={validForgot} />
                <Form.Link content="Quay lại đăng nhập" to="/login" />
            </Form>
        </LoginLayout>
    )
} export default ForgotPassword