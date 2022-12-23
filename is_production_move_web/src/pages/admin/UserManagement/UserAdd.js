import React from 'react';
import { Form, Option } from '../../../components';
import { UseValidation } from '../../../utils';

function UserAdd() {
    const [loginUsername, setLoginUsername] = React.useState('');
    const [loginPassword, setLoginPassword] = React.useState('');
    const validLogin = () => {
        return true
    }

    return <React.Fragment>
        <Form>
            <Form.Title content="User Add" />
            <Form.Split>
                <Form.Input label="Tên đăng nhập" type="text" reference={[loginUsername, setLoginUsername, UseValidation.username]} />
                <Form.Input label="Email" type="text" reference={[loginUsername, setLoginUsername, UseValidation.username]} />
            </Form.Split>
            <Form.Split>
                <Form.Input label="Mật khẩu" type="password" reference={[loginPassword, setLoginPassword, UseValidation.password]} />
                <Form.Input label="Nhập lại mật khẩu" type="password" reference={[loginPassword, setLoginPassword, UseValidation.password]} />
            </Form.Split>
            <Form.Split>
                <Form.Input label="Số điện thoại" type="text" reference={[loginUsername, setLoginUsername, UseValidation.username]} />
                <Form.Input label="Địa chỉ" type="text" reference={[loginUsername, setLoginUsername, UseValidation.username]} />
            </Form.Split>
            <Form.Split>
                <Form.Input label="Tên công ty" type="text" reference={[loginUsername, setLoginUsername, UseValidation.username]} />
                <Option title='Chức vụ' maxWidth>
                    <Option.Item value='Admin' />
                    <Option.Item value='User' />
                </Option>

            </Form.Split>

            <Form.Submit validation={validLogin} content="Đăng nhập" />
        </Form>
    </React.Fragment>
}
export { UserAdd }