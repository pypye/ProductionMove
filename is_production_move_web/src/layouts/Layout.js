import React from 'react';
import { Outlet } from 'react-router-dom';
import { Dropdown, Icon } from '../components';
import { Navigation } from './Navigation/Navigation';
import { ResponsiveNavigation } from './Navigation/ResponsiveNavigation';
import './style.css'

function Layout(props) {
    const onLogout = () => {
        localStorage.removeItem('auth');
        window.location.href = '/login';
    }

    return (
        <div className="container">
            <nav>
                <NavigationComponent type={props.type} companyName={props.companyName} />
            </nav>
            <main>
                <header>
                    <div className='left-header'>
                        <ResponsiveNavigation>
                            <ResponsiveNavigation.Trigger><Icon.Navigation /></ResponsiveNavigation.Trigger>
                            <ResponsiveNavigation.Content>
                                <NavigationComponent type={props.type} companyName={props.companyName} />
                            </ResponsiveNavigation.Content>
                        </ResponsiveNavigation>

                    </div>
                    <div className='right-header'>
                        <Dropdown>
                            <Dropdown.Main item={<Icon.AvatarBox><img src="https://i.stack.imgur.com/dRFs4.png" alt="" /></Icon.AvatarBox>} />
                            <Dropdown.Menu right zIndex={5}>
                                <Dropdown.Info userName={props.companyName} userEmail={props.email} />
                                <Dropdown.Item label="Đổi mật khẩu" />
                                <Dropdown.Item label="Đăng xuất" onClick={onLogout} />
                            </Dropdown.Menu>
                        </Dropdown>
                    </div>
                </header>
                <section>
                    <Outlet />
                </section>
            </main>
        </div>
    );
} export { Layout };

function NavigationComponent(props) {
    return (
        <React.Fragment>
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
            <div className='navigation'>
                <Navigation>
                    <Navigation.Category label="Chung" role={['admin', 'factory', 'warranty', 'agency']} type={props.type}>
                        <Navigation.Item label={props.type === 'admin' ? "Danh sách sản phẩm" : "Danh sách sản phẩm trong kho"} link='/product' />
                    </Navigation.Category>

                    <Navigation.Category label="Ban quản lý" role={['admin']} type={props.type}>
                        <Navigation.Item label="Quản lý dòng sản phẩm" link='/admin/category' />
                        <Navigation.Item label="Quản lý người dùng" link='/admin/user' />
                    </Navigation.Category>
                    <Navigation.Category label="Quản lý nhà máy" role={['factory']} type={props.type}>
                        <Navigation.Item label="Nhập sản phẩm" link='/factory/add-product' />
                        <Navigation.Item label="Xuất sản phẩm tới đại lý" link='/factory/send-to-agency' />
                        <Navigation.Item label="Nhận sản phẩm lỗi từ TTBH" link='/factory/get-from-warranty' />
                    </Navigation.Category>
                    <Navigation.Category label="Quản lý đại lý" role={['agency']} type={props.type}>
                        <Navigation.Item label="Nhập sản phẩm từ nhà máy" link='/agency/get-from-factory' />
                        <Navigation.Item label="Bán sản phẩm" link='/agency/sale-product' />
                        <Navigation.Item label="Lấy sản phẩm cần bảo hành từ khách hàng" link='/agency/get-from-customer' />
                        <Navigation.Item label="Lấy sản phẩm từ TTBH" link='/agency/get-from-warranty' />
                        <Navigation.Item label="Sản phẩm không thể sửa" link='/agency/notify-error-product' />
                        <Navigation.Item label="Thu hồi sản phẩm" link='/agency/recall-product' />
                    </Navigation.Category>
                    <Navigation.Category label="Quản lý trung tâm bảo hành" role={['warranty']} type={props.type}>
                        <Navigation.Item label="Lấy sản phầm từ đại lý" link='/warranty/get-from-agency' />
                        <Navigation.Item label="Trả sản phẩm về đại lý" link='/warranty/send-to-agency' />
                        <Navigation.Item label="Chuyển sản phẩm về cơ sở sản xuất" link='/warranty/send-to-factory' />
                    </Navigation.Category>
                </Navigation>
            </div>
        </React.Fragment>
    );
}