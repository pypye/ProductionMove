import './style.css'
import React from 'react';
import { Form } from "../../../components";
import { useLocation, useNavigate } from 'react-router-dom';
import { UseFetch, UseValidation } from '../../../utils';

function ResetPassword(props) {
    const [resetToken, setResetToken] = React.useState('');
    const [resetPassword, setResetPassword] = React.useState('');
    const [resetRetypePassword, setResetRetypePassword] = React.useState('');
    const resetInfo = React.useMemo(() => ({ token: resetToken, password: resetPassword, retypePassword: resetRetypePassword }), [resetToken, resetPassword, resetRetypePassword]);
    const [error, setError] = React.useState("");
    const [email, setEmail] = React.useState();
    const navigate = useNavigate();
    const { state } = useLocation();

    React.useEffect(() => {
        if (!state) {
            navigate('/forgot-password');
        } else {
            setEmail(state.email);
            window.history.replaceState({}, document.title)
        }
    }, [state, navigate])

    const validRetypePassword = (input) => {
        return UseValidation.retypePassword(input, resetInfo.password);
    }

    const validReset = () => {
        return UseValidation.token(resetToken).state && UseValidation.password(resetPassword).state && validRetypePassword(resetRetypePassword).state;

    }

    const onReset = () => {
        UseFetch('/backend/auth/reset', 'POST', resetInfo).then(data => {
            if (data.status.code === 'SUCCESS') {
                navigate('/login', { state: { notify: true } });
            } else {
                setError("Mã xác minh không chính xác");
            }
        })
    }


    return (
        <Form width='25rem'>
            <Form.Title content='Gửi yêu cầu thành công' />
            <Form.Notify enabled={true}>Chúng tôi đã gửi mã xác minh gồm 6 chữ số tới email <span style={{ color: 'green' }}>{email}</span>. Hãy nhập mã đó để xác minh email của bạn</Form.Notify>
            <Form.Input label="Mã xác minh" type="text" reference={[resetToken, setResetToken, UseValidation.token]} />
            <Form.Input label="Mật khẩu mới" type="password" reference={[resetPassword, setResetPassword, UseValidation.password]} />
            <Form.Input label="Nhập lại mật khẩu mới" type="password" reference={[resetRetypePassword, setResetRetypePassword, validRetypePassword]} />
            <Form.Error enable={error !== ""} content={error} />
            <Form.Submit content='Cập nhật mật khẩu' onClick={onReset} validation={validReset} />
            <div>Bạn không nhận được mã? Gửi lại mã</div>
            <Form.Link content='Quay lại đăng nhập' href='/login' />
        </Form>
    )
} export { ResetPassword }