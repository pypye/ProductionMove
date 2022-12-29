/*
* @description: Translate the key of object to Vietnamese
*/
const key = {
    "no.": "Stt.",
    "productName": "Tên sản phẩm",
    "productCode": "Mã sản phẩm",
    "category": "Dòng sản phẩm",
    "price": "Giá tiền",
    "status": "Trạng thái",
    "location": "Vị trí",
    "warrantTime": "Bảo hành",
    "description": "Chi tiết",
    "customer": "Khách hàng",
    "username": "Tên đăng nhập",
    "email": "Email",
    "role": "Chức vụ",
    "companyName": "Tên công ty",
    "address": "Địa chỉ",
    "phone": "Số điện thoại",
    "option": "Tùy chọn",
    "numberOfErrorProduct": "Số lượng sản phẩm lỗi",
    "numberOfRestProduct": "Số lượng sản phẩm không có lỗi",
}
const UseTranslator = {}

UseTranslator.translate = function (word) {
    return key[word] ? key[word] : word;
}

export { UseTranslator }