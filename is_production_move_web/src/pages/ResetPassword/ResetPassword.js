import './style.css'
import React from 'react';
import { Form } from "../../components";
import { Button } from "../../components";
import { useLocation, useNavigate } from 'react-router-dom';
import { fetch } from '../../sessions';

function ResetPassword(props) {
    const [resetInfo, setResetInfo] = React.useState({ token: '', password: '', retypePassword: '' });
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

    const validToken = (input) => {
        if (input.length === 0) {
            return { state: false, content: 'Mã xác minh không được để trống' };
        }
        return { state: true };
    }
    const validPassword = (input) => {
        if (input.length === 0) {
            return { state: false, content: 'Mật khẩu không được để trống' };
        }
        return { state: true };
    }
    const validRetypePassword = (input) => {
        if (input.length === 0) {
            return { state: false, content: 'Mật khẩu không được để trống' };
        } if(input !== resetInfo.password){
            return { state: false, content: 'Mật khẩu không khớp' };
        }
        return { state: true };
    }

    const validReset = () => {
        if (validToken(resetInfo.token).state === false || validPassword(resetInfo.password).state === false || validRetypePassword(resetInfo.retypePassword).state === false) {
            return true;
        }
        return false;
    }

    const onReset = (e) => {
        e.preventDefault();
        fetch('/backend/auth/reset', 'POST', resetInfo).then(data => {
            if(data.status.code === 'SUCCESS'){
                navigate('/login', {state: {notify: true}});
            } else {
                setError("Mã xác minh không chính xác");
            }
        })
    }


    return (
        <div className='forgot-container'>
            <Form width='25rem'>
                <h2>Gửi yêu cầu thành công</h2>
                <p className='notify'>Chúng tôi đã gửi mã xác minh gồm 6 chữ số tới email <span style={{ color: 'green' }}>{email}</span>. Hãy nhập mã đó để xác minh email của bạn</p>
                <Form.Input label="Mã xác minh" type="text" validation={validToken} value={resetInfo.token} onChange={(e) => setResetInfo({ ...resetInfo, token: e })} />
                <Form.Input label="Mật khẩu mới" type="password" validation={validPassword} value={resetInfo.password} onChange={(e) => setResetInfo({ ...resetInfo, password: e })} />
                <Form.Input label="Nhập lại mật khẩu mới" type="password" validation={validRetypePassword} value={resetInfo.retypePassword} onChange={(e) => setResetInfo({ ...resetInfo, retypePassword: e })} />
                <p style={{ "display": error === "" ? "none" : "block" }} className="login-error">{error}</p>
                <Button onClick={(e) => onReset(e)} validation={validReset}>Cập nhật mật khẩu</Button>
                <div>Bạn không nhận được mã? Gửi lại mã</div>
                <a href='/login' className='login-back'>Quay lại đăng nhập</a>
            </Form>
        </div>
    )
} export default ResetPassword