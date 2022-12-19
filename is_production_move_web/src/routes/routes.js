import { Navigate, useRoutes, } from 'react-router-dom';
import Login from '../pages/Login/Login';
import Layout from '../layouts/Layout';
import { ForgotPassword, Logout, ResetPassword } from '../pages';

export default function Router(props) {
    const routes = useRoutes([
        {
            path: '/dashboard',
            element: props.auth ? <Layout /> : <Navigate to="/login" />,
        },
        {
            path: '/login',
            element: props.auth ? <Navigate to="/dashboard" /> : <Login />,
        },
        {
            path: '/logout',
            element: <Logout />,
        },
        {
            path: '/forgot-password',
            element: props.auth ? <Navigate to="/dashboard" /> : <ForgotPassword />,
        },
        {
            path: '/reset-password',
            element: props.auth ? <Navigate to="/dashboard" /> : <ResetPassword />,
        },
        {
            path: '/',
            element: props.auth ? <Navigate to="/dashboard" /> : <Navigate to="/login" />,
        }
    ]);

    return routes;
}