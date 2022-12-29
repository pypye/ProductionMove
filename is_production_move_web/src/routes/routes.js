import { useRoutes } from 'react-router-dom';
import { Login } from '../pages/general/Login/Login';
import { Layout } from '../layouts/Layout';
import {
    ForgotPassword, Product, Logout, ResetPassword, User, Category,
    GetFromFactory, SendToAgency, SaleProduct, AddProduct, GetFromCustomer, GetFromAgency, WarrantySendToAgency, GetFromWarranty, SendToFactory, FactoryGetFromWarranty, NotifyErrorProduct, RecallProduct, SaleAnalysis, FactorySaleAnalysis, ErrorAnalysis, Welcome, UnsoldProduct, Error404, FactoryStatisticProduct, ChangePassword, WarrantyStatisticProduct, AgencyStatisticProduct
} from '../pages';
import { UseAuth } from '../utils';
import { LoginLayout } from '../layouts';

export default function Router() {
    const routes = useRoutes([
        {
            element: <UseAuth.Auth element={<Layout />} roles={['admin', 'factory', 'warranty', 'agency']} />,
            children: [
                { path: '/', element: <Welcome /> },
                { path: '/product', element: <Product.ProductList /> },
                { path: '/change-password', element: <ChangePassword /> },
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
                { path: '/factory/send-to-agency', element: <SendToAgency /> },
                { path: '/factory/get-from-warranty', element: <FactoryGetFromWarranty /> },
                { path: '/factory/statistic-product', element: <FactoryStatisticProduct /> },
                { path: '/factory/sale-analysis', element: <FactorySaleAnalysis /> },
                { path: '/factory/error-analysis', element: <ErrorAnalysis /> },
            ]
        },
        {
            element: <UseAuth.Auth element={<Layout />} roles={['agency']} />,
            children: [
                { path: '/agency/get-from-factory', element: <GetFromFactory /> },
                { path: '/agency/sale-product', element: <SaleProduct /> },
                { path: '/agency/get-from-customer', element: <GetFromCustomer /> },
                { path: '/agency/get-from-warranty', element: <GetFromWarranty /> },
                { path: '/agency/notify-error-product', element: <NotifyErrorProduct /> },
                { path: '/agency/recall-product', element: <RecallProduct /> },
                { path: '/agency/unsold-product', element: <UnsoldProduct /> },
                { path: '/agency/statistic-product', element: <AgencyStatisticProduct /> },
                { path: '/agency/sale-analysis', element: <SaleAnalysis /> }
            ]
        },
        {
            element: <UseAuth.Auth element={<Layout />} roles={['warranty']} />,
            children: [
                { path: '/warranty/get-from-agency', element: <GetFromAgency /> },
                { path: '/warranty/send-to-agency', element: <WarrantySendToAgency /> },
                { path: '/warranty/send-to-factory', element: <SendToFactory /> },
                { path: '/warranty/statistic-product', element: <WarrantyStatisticProduct /> },
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
            element: <UseAuth.Auth element={<Error404 />} roles={['admin', 'factory', 'warranty', 'agency']} />,
        }
    ]);
    return routes;
}