import { useRoutes } from 'react-router-dom';
import { Login } from '../pages/general/Login/Login';
import { Layout } from '../layouts/Layout';
import { ForgotPassword, Product, Logout, ResetPassword, User, Category, GetFromFactory, SendToAgency, SaleProduct, AddProduct } from '../pages';
import { UseAuth } from '../utils';
import { LoginLayout } from '../layouts';

export default function Router() {
    const routes = useRoutes([
        {
            element: <UseAuth.Auth element={<Layout />} roles={['admin', 'factory', 'warranty', 'agency']} />,
            children: [
                { path: '/', element: <div></div> },
                { path: '/product', element: <Product.ProductList /> },
                { path: '/logout', element: <Logout /> },
            ]
        },
        {
            element: <UseAuth.Auth element={<Layout />} roles={['admin']} />,
            children: [
                { path: '/admin/category', element: <Category.CategoryList /> },
                { path: '/admin/user', element: <User.UserList /> },
            ]
        },
        {
            element: <UseAuth.Auth element={<Layout />} roles={['factory']} />,
            children: [
                { path: '/factory/add-product', element: <AddProduct /> },
                { path: '/factory/send-to-agency', element: <SendToAgency />},
                { path: '/factory/get-from-agency', element: <div>get-from-agency</div> },
            ]
        },
        {
            element: <UseAuth.Auth element={<Layout />} roles={['agency']} />,
            children: [
                { path: '/agency/get-from-factory', element: <GetFromFactory />},
                { path: '/agency/sale-product', element: <SaleProduct/> },
                { path: '/agency/get-from-customer', element: <div>get-from-customer</div> },
                { path: '/agency/get-from-warranty', element: <div>get-from-warranty</div> },
                { path: '/agency/notify-error-product', element: <div>notify-error-product</div> },
                { path: '/agency/recall-product', element: <div>recall-product</div> },
            ]
        },
        {
            element: <UseAuth.Auth element={<Layout />} roles={['warranty']} />,
            children: [
                { path: '/warranty/get-from-agency', element: <div>get-from-agency</div> },
                { path: '/warranty/send-to-agency', element: <div>send-to-agency</div> },
                { path: '/warranty/send-to-factory', element: <div>send-to-factory</div> },
            ]
        },
        {
            element: <UseAuth.Auth element={<LoginLayout />} roles={['guest']} />,
            children: [
                { path: '/login', element: <Login /> },
                { path: '/forgot-password', element: <ForgotPassword /> },
                { path: '/reset-password', element: <ResetPassword /> },
            ]
        },
        {
            path: '*',
            element: <UseAuth.Auth element={<div>Not Found</div>} roles={['admin']} />,
        }
    ]);

    return routes;
}