import { Login } from "./general/Login/Login";
import { Logout } from "./general/Logout/Logout";
import { ForgotPassword } from "./general/ForgotPassword/ForgotPassword";
import { ResetPassword } from "./general/ResetPassword/ResetPassword";
import { ChangePassword } from "./general/ChangePassword/ChangePassword";
import { Product } from "./general/Product";
import { Welcome } from "./general/Welcome/Welcome";
import { Error404 } from "./general/Error404/Error404";

import { User } from "./admin/User";
import { Category } from "./admin/Category";
import { StatisticProduct as AdminStatisticProduct } from "./admin/StatisticProduct/StatisticProduct";

import { SendToAgency } from "./factory/SendToAgency/SendToAgency";
import { AddProduct } from "./factory/AddProduct/AddProduct";
import { GetFromWarranty as FactoryGetFromWarranty } from "./factory/GetFromWarranty/GetFromWarranty";
import { SaleAnalysis as FactorySaleAnalysis } from "./factory/SaleAnalysis/SaleAnalysis";
import { ErrorAnalysis } from "./factory/ErrorAnalysis/ErrorAnalysis";
import { StatisticProduct as FactoryStatisticProduct } from "./factory/StatisticProduct/StatisticProduct";

import { GetFromFactory } from "./agency/GetFromFactory/GetFromFactory";
import { SaleProduct } from "./agency/SaleProduct/SaleProduct";
import { GetFromCustomer } from "./agency/GetFromCustomer/GetFromCustomer";
import { GetFromWarranty } from "./agency/GetFromWarranty/GetFromWarranty";
import { NotifyErrorProduct } from "./agency/NotifyErrorProduct/NotifyErrorProduct";
import { RecallProduct } from "./agency/RecallProduct/RecallProduct";
import { SaleAnalysis } from "./agency/SaleAnalysis/SaleAnalysis";
import { UnsoldProduct } from "./agency/UnsoldProduct/UnsoldProduct";
import { StatisticProduct as AgencyStatisticProduct } from "./agency/StatisticProduct/StatisticProduct";

import { GetFromAgency } from "./warranty/GetFromAgency/GetFromAgency";
import { SendToFactory } from "./warranty/SendToFactory/SendToFactory";
import { SendToAgency as WarrantySendToAgency } from "./warranty/SendToAgency/SendToAgency";
import { StatisticProduct as WarrantyStatisticProduct } from "./warranty/StatisticProduct/StatisticProduct";

export {
    Login, Logout, ForgotPassword, ResetPassword, Product, Welcome, ChangePassword, Error404,
    User, Category, AdminStatisticProduct,
    SendToAgency, AddProduct, FactoryGetFromWarranty, FactorySaleAnalysis, ErrorAnalysis, FactoryStatisticProduct,
    GetFromFactory, SaleProduct, GetFromCustomer, GetFromWarranty, NotifyErrorProduct, RecallProduct, SaleAnalysis, UnsoldProduct, AgencyStatisticProduct,
    GetFromAgency, WarrantySendToAgency, SendToFactory, WarrantyStatisticProduct
};