import React from 'react';
import { Outlet } from 'react-router-dom';
import { Dropdown, Icon } from '../components';
import { Navigation } from './Navigation/Navigation';
import './style.css'

function Layout(props) {
    const onLogout = () => {
        localStorage.removeItem('auth');
        window.location.href = '/login';
    }

    return (
        <div className="container">
            <nav>
                <div className='info'>
                    <span>Hệ thống ProductionMove</span>
                    <div className='personal-info'>
                        <Icon.AvatarBox><img src="https://i.stack.imgur.com/dRFs4.png" alt="" /></Icon.AvatarBox>
                        <div>
                            <div><strong>{props.companyName}</strong></div>
                            <div>{props.type}</div>
                        </div>
                    </div>
                </div>
                <Navigation>
                    <Navigation.Category label="Chung" role={['admin', 'factory', 'warranty', 'agency']} type={props.type}>
                        <Navigation.Item icon={<Icon.NotificationRaw />} label={props.type === 'admin' ? "Danh sách sản phẩm" : "Danh sách sản phẩm trong kho"} link='/product' />
                    </Navigation.Category>

                    <Navigation.Category label="Ban quản lý" role={['admin']} type={props.type}>
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Quản lý dòng sản phẩm" link='/admin/category' />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Quản lý người dùng" link='/admin/user' />
                    </Navigation.Category>
                    <Navigation.Category label="Quản lý nhà máy" role={['factory']} type={props.type}>
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Nhập sản phẩm" link='/factory/add-product' />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Xuất sản phẩm tới đại lý" link='/factory/send-to-agency' />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Nhận sản phẩm lỗi từ TTBH" link='/factory/get-from-warranty' />
                    </Navigation.Category>
                    <Navigation.Category label="Quản lý đại lý" role={['agency']} type={props.type}>
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Nhập sản phẩm từ nhà máy" link='/agency/get-from-factory' />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Bán sản phẩm" link='/agency/sale-product' />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Lấy sản phẩm cần bảo hành từ khách hàng" link='/agency/get-from-customer' />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Lấy sản phẩm từ TTBH" link='/agency/get-from-warranty' />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Sản phẩm không thể sửa" link='/agency/notify-error-product' />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Thu hồi sản phẩm" link='/agency/recall-product' />
                    </Navigation.Category>
                    <Navigation.Category label="Quản lý trung tâm bảo hành" role={['warranty']} type={props.type}>
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Lấy sản phầm từ đại lý" link='/warranty/get-from-agency' />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Trả sản phẩm về đại lý" link='/warranty/send-to-agency' />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Chuyển sản phẩm về cơ sở sản xuất" link='/warranty/send-to-factory' />
                    </Navigation.Category>
                </Navigation>
            </nav>
            <main>
                <header>
                    <Icon.Notification />
                    <Icon.People />
                    <Dropdown>
                        <Dropdown.Main item={<Icon.AvatarBox><img src="https://i.stack.imgur.com/dRFs4.png" alt="" /></Icon.AvatarBox>} />
                        <Dropdown.Menu right zIndex={5}>
                            <Dropdown.Info userName={props.companyName} userEmail={props.email} />
                            <Dropdown.Item label="Đổi mật khẩu" />
                            <Dropdown.Item label="Đăng xuất" onClick={onLogout} />
                        </Dropdown.Menu>
                    </Dropdown>
                </header>
                <section>
                    <Outlet />
                </section>
            </main>
        </div>
    );
} export { Layout };