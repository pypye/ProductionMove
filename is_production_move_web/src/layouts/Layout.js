import React from 'react';
import { Outlet } from 'react-router-dom';
import { Dropdown, Icon } from '../components';
import { Navigation } from './Navigation/Navigation';
import { ResponsiveNavigation } from './Navigation/ResponsiveNavigation';
import './style.css'

/*
* @description: Layout for main page
*/
function Layout(props) {
    const onLogout = () => {
        localStorage.removeItem('auth');
        window.location.href = '/login';
    }

    const onForgotPassword = () => {
        window.location.href = "/change-password";
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
                            <Dropdown.Main item={<Icon.AvatarBox><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgf5GvxhVHYUqV9roWJ4I4xyszcLCUHxRpxXKfx6R-5gSQuxrApw2QADJwvxF6OLnM810&usqp=CAU" alt="" /></Icon.AvatarBox>} />
                            <Dropdown.Menu right zIndex={5}>
                                <Dropdown.Info userName={props.companyName} userEmail={props.email} />
                                <Dropdown.Item label="Đổi mật khẩu" onClick={onForgotPassword} />
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
                <a className='personal-info' href='/'>
                    <Icon.AvatarBox><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgf5GvxhVHYUqV9roWJ4I4xyszcLCUHxRpxXKfx6R-5gSQuxrApw2QADJwvxF6OLnM810&usqp=CAU" alt="" /></Icon.AvatarBox>
                    <div>
                        <div><strong>{props.companyName}</strong></div>
                        {/* <div>{props.type}</div> */}
                    </div>
                </a>
            </div>
            <div className='navigation'>
                <Navigation>
                    <Navigation.Category label="Ban quản lý" role={['admin']} type={props.type}>
                        <Navigation.Item label="Danh sách sản phẩm" link='/product' />
                        <Navigation.Item label="Quản lý dòng sản phẩm" link='/admin/category' />
                        <Navigation.Item label="Quản lý người dùng" link='/admin/user' />
                    </Navigation.Category>
                    <Navigation.Category label="Thống kê" role={['admin']} type={props.type}>
                        <Navigation.Item label="Thống kê sản phẩm toàn quốc" link='/admin/statistic-product' />
                    </Navigation.Category>

                    <Navigation.Category label="Quản lý nhà máy" role={['factory']} type={props.type}>
                        <Navigation.Item label="Danh sách sản phẩm trong kho" link='/product' />
                        <Navigation.Item label="Nhập sản phẩm" link='/factory/add-product' />
                        <Navigation.Item label="Xuất sản phẩm tới đại lý" link='/factory/send-to-agency' />
                        <Navigation.Item label="Nhận sản phẩm lỗi từ TTBH" link='/factory/get-from-warranty' />
                    </Navigation.Category>
                    <Navigation.Category label="Thống kê" role={['factory']} type={props.type}>
                        <Navigation.Item label="Thống kê sản phẩm theo từng loại" link='/factory/statistic-product' />
                        <Navigation.Item label="Thống kê sản phẩm đã bán" link='/factory/sale-analysis' />
                        <Navigation.Item label="Thống kê sản phẩm bị lỗi" link='/factory/error-analysis' />
                    </Navigation.Category>

                    <Navigation.Category label="Quản lý đại lý" role={['agency']} type={props.type}>
                        <Navigation.Item label="Danh sách sản phẩm trong kho" link='/product' />
                        <Navigation.Item label="Nhập sản phẩm từ nhà máy" link='/agency/get-from-factory' />
                        <Navigation.Item label="Bán sản phẩm" link='/agency/sale-product' />
                        <Navigation.Item label="Lấy sản phẩm cần bảo hành từ khách hàng" link='/agency/get-from-customer' />
                        <Navigation.Item label="Lấy sản phẩm từ TTBH" link='/agency/get-from-warranty' />
                        <Navigation.Item label="Sản phẩm không thể sửa" link='/agency/notify-error-product' />
                        <Navigation.Item label="Thu hồi sản phẩm" link='/agency/recall-product' />
                        <Navigation.Item label="Sản phẩm tồn kho" link='/agency/unsold-product' />
                    </Navigation.Category>
                    <Navigation.Category label="Thống kê" role={['agency']} type={props.type}>
                        <Navigation.Item label="Thống kê sản phẩm theo từng loại" link='/agency/statistic-product' />
                        <Navigation.Item label="Thống kê sản phẩm đã bán" link='/agency/sale-analysis' />
                    </Navigation.Category>

                    <Navigation.Category label="Quản lý trung tâm bảo hành" role={['warranty']} type={props.type}>
                        <Navigation.Item label="Danh sách sản phẩm trong kho" link='/product' />
                        <Navigation.Item label="Lấy sản phầm từ đại lý" link='/warranty/get-from-agency' />
                        <Navigation.Item label="Trả sản phẩm về đại lý" link='/warranty/send-to-agency' />
                        <Navigation.Item label="Chuyển sản phẩm về cơ sở sản xuất" link='/warranty/send-to-factory' />
                    </Navigation.Category>
                    <Navigation.Category label="Thống kê" role={['warranty']} type={props.type}>
                        <Navigation.Item label="Thống kê sản phẩm theo từng loại" link='/warranty/statistic-product' />
                    </Navigation.Category>

                </Navigation>
            </div>
        </React.Fragment>
    );
}