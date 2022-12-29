import './style.css'

function Error404() {
    return (
        <div className="error404">
            <h1>Lỗi 404</h1>
            <p>Không tìm thấy trang</p>
            <a href='/'> Quay lại trang chủ</a>
        </div>
    )
}
export { Error404 }