import React from 'react';
import { Outlet } from 'react-router-dom';
import { Dropdown } from '../components';
import { Icon } from '../components/Icon';
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
                    <Navigation.Category label="Quản lý sản phẩm">
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Danh sách dòng sản phẩm" link='/category/list' />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Danh sách sản phẩm" link='/product/list' />
                    </Navigation.Category>
                    <Navigation.Category label="Quảy lý người dùng">
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Danh sách người dùng" link='/admin/user/list' />
                    </Navigation.Category>
                    <Navigation.Category label="Quản lý nhà máy">
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Nhập sản phẩm" link='/factory/product/add' />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Xuất sản phẩm tới đại lý" link='/factory/product/agency' />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Nhận sản phẩm lỗi từ TTBH" />
                    </Navigation.Category>
                    <Navigation.Category label="Quản lý đại lý">
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Nhập sản phẩm từ nhà máy" link='/agency/product/factory' />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Bán sản phẩm" />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Lấy sản phẩm cần bảo hành từ khách hàng" />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Lấy sản phẩm từ TTBH" />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Sản phẩm không thể sửa" />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Thu hồi sản phẩm" />
                    </Navigation.Category>
                    <Navigation.Category label="Quản lý trung tâm bảo hành">
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Lấy sản phầm từ đại lý" />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Trả sản phẩm về đại lý" />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="Chuyển sản phẩm về cơ sở sản xuất" />
                    </Navigation.Category>
                </Navigation>
            </nav>
            <main>
                <header>
                    <Icon.Notification />
                    <Icon.People />
                    <Dropdown>
                        <Dropdown.Main item={<Icon.AvatarBox><img src="https://i.stack.imgur.com/dRFs4.png" alt="" /></Icon.AvatarBox>} />
                        <Dropdown.Menu right>
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
} export default Layout;