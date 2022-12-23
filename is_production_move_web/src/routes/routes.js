import { useRoutes } from 'react-router-dom';
import Login from '../pages/general/Login/Login';
import Layout from '../layouts/Layout';
import { ForgotPassword, Logout, ResetPassword } from '../pages';
import { UseAuth } from '../utils';
import { LoginLayout } from '../layouts';

export default function Router() {
    const routes = useRoutes([
        {
            element: <UseAuth.Auth element={<Layout />} roles={['admin']} />,
            children: [
                { path: '/', element: <div></div> },
                { path: '/logout', element: <Logout /> },
                { path: '/management/user', element: <div>user</div> },
            ]
        },
        {
            element: <UseAuth.Auth element={<LoginLayout />} roles={['all']} />,
            children: [
                { path: '/login', element: <Login /> },
                { path: '/forgot-password', element: <ForgotPassword /> },
                { path: '/reset-password', element: <ResetPassword /> },
            ]
        },
        {
            path: '*',
            element: <UseAuth.Auth element={<div>Not Found</div>} roles={['all']} />,
        }
    ]);

    return routes;
}