function username(input) {
    const usernamePattern = /^(?=[a-zA-Z0-9._]{7,20}$)(?!.*[_.]{2})[^_.].*[^_.]$/;
    if (input.length === 0) {
        return { state: false, content: 'Tên đăng nhập không được để trống' };
    } else if (!usernamePattern.test(input)) {
        return { state: false, content: 'Tên đăng nhập phải có độ dài từ 7 đến 20 kí tự và không chứa kí tự đặc biệt' };
    }
    return { state: true };
}

function password(input) {
    const passwordPattern = /^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{7,20}$/;
    if (input.length === 0) {
        return { state: false, content: 'Mật khẩu không được để trống' };
    } else if (!passwordPattern.test(input)) {
        return { state: false, content: 'Mật khẩu phải có ít nhất 1 chữ hoa, 1 chữ thường, 1 số và có độ dài từ 7 đến 20 kí tự' };
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
    const emailPattern = /^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$/;
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

function phone(input) {
    const phonePattern = /(84|0[1-9])+([0-9]{8})\b/;
    if (input.length === 0) {
        return { state: false, content: 'Số điện thoại không được để trống' };
    } else if (!phonePattern.test(input)) {
        return { state: false, content: 'Số điện thoại không hợp lệ' };
    }
    return { state: true };
}

function address(input) {
    const addressPattern = /^[0-9a-zA-Z ,/ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹý]+$/
    if (input.length === 0) {
        return { state: false, content: 'Địa chỉ không được để trống' };
    } else if (!addressPattern.test(input)) {
        return { state: false, content: 'Địa chỉ không được chứa ký tự đặc biệt' };
    }
    return { state: true };
}

function companyName(input) {
    const companyNamePattern = /^[0-9a-zA-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹý]+$/;
    if (input.length === 0) {
        return { state: false, content: 'Tên công ty không được để trống' };
    } else if (!companyNamePattern.test(input)) {
        return { state: false, content: 'Tên công ty chỉ được chứa số, chữ cái và khoảng trắng' };
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
    phone,
    address,
    companyName,
};

export { UseValidation };
