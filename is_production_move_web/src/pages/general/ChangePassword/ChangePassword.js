import React from "react";
import { Form } from "../../../components";
import { UseFetch, UseValidation } from "../../../utils";

/*
* @description: Change password page
*/
function ChangePassword(props) {
    const [currentPassword, setCurrentPassword] = React.useState("");
    const [newPassword, setNewPassword] = React.useState("");
    const [confirmPassword, setConfirmPassword] = React.useState("");
    const [error, setError] = React.useState("");

    const validRetypePassword = (input) => {
        return UseValidation.retypePassword(input, newPassword);
    }

    const validChangẻPassword = () => {
        return UseValidation.loginPassword(currentPassword).state && UseValidation.password(newPassword).state && validRetypePassword(confirmPassword).state;
    }

    const onChangePassword = () => {
        UseFetch(`/backend/user/change_password`, "POST", { oldPassword: currentPassword, newPassword: newPassword, retypePassword: confirmPassword }).then((res) => {
            if (res.status.code === "SUCCESS") {
                alert("Đổi mật khẩu thành công");
                window.location.href = "/"
            } else {
                setError("Mật khẩu cũ không chính xác")
            }
        })
    }

    return <Form>
        <Form.Title content="Đổi mật khẩu" />
        <Form.Input label="Mật khẩu cũ" type="password" reference={[currentPassword, setCurrentPassword, UseValidation.loginPassword]} />
        <Form.Input label="Mật khẩu mới" type="password" reference={[newPassword, setNewPassword, UseValidation.password]} />
        <Form.Input label="Nhập lại mật khẩu mới" type="password" reference={[confirmPassword, setConfirmPassword, validRetypePassword]} />
        <Form.Error enabled={error !== ""} content={error} />
        <Form.Submit content="Đổi mật khẩu" validation={validChangẻPassword} onClick={onChangePassword} />
    </Form>
}
export { ChangePassword }