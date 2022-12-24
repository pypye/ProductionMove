function username(input) {
    if (input.length === 0) {
        return { state: false, content: 'Tên đăng nhập không được để trống' };
    } else if (input.length < 6) {
        return { state: false, content: 'Tên đăng nhập phải có tối thiểu 6 kí tự' };
    }
    return { state: true };
}

function password(input) {
    if (input.length === 0) {
        return { state: false, content: 'Mật khẩu không được để trống' };
    }
    return { state: true };
}

function retypePassword(input, password) {
    if (input.length === 0) {
        return { state: false, content: 'Mật khẩu không được để trống' };
    } if (input !== password) {
        return { state: false, content: 'Mật khẩu không khớp' };
    }
    return { state: true };
}

function email(input) {
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    if (input.length === 0) {
        return { state: false, content: 'Email không được để trống' };
    } else if (!emailPattern.test(input)) {
        return { state: false, content: 'Email không hợp lệ' };
    }
    return { state: true };
}

function token(input) {
    if (input.length === 0) {
        return { state: false, content: 'Mã xác nhận không được để trống' };
    }
    return { state: true };
}

function category(input) {
    if (input.length === 0) {
        return { state: false, content: 'Tên dòng sản phẩm không được để trống' };
    }
    return { state: true };
}

const UseValidation = {
    username,
    password,
    email,
    token,
    retypePassword,
    category,
};

export { UseValidation };
