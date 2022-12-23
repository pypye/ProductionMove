import React from 'react';
import { Outlet } from 'react-router-dom';
import { Icon } from '../components/Icon';
import { Navigation } from './Navigation/Navigation';
import './style.css'

function Layout(props) {

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
                    <Navigation.Category label="Chung">
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="[get_category]" />
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="[get_product]" />
                    </Navigation.Category>
                    <Navigation.Category label="Quản lý">
                        <Navigation.Item icon={<Icon.NotificationRaw />} label="[get_user]" link='/management/user' />
                    </Navigation.Category>
                </Navigation>
            </nav>
            <main>
                <header>
                    <Icon.Notification />
                    <Icon.People />
                    <Icon.AvatarBox><img src="https://i.stack.imgur.com/dRFs4.png" alt="" /></Icon.AvatarBox>
                </header>
                <section>
                    <Outlet />
                </section>
            </main>
        </div>
    );
} export default Layout;