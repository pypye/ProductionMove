import { Login } from "./general/Login/Login";
import { Logout } from "./general/Logout/Logout";
import { ForgotPassword } from "./general/ForgotPassword/ForgotPassword";
import { ResetPassword } from "./general/ResetPassword/ResetPassword";
import { Product } from "./general/Product";
import { ProductAnalysis } from "./general/ProductAnalysis/ProductAnalysis";

import { User } from "./admin/User";
import { Category } from "./admin/Category";

import { SendToAgency } from "./factory/SendToAgency/SendToAgency";
import { AddProduct } from "./factory/AddProduct/AddProduct";
import { GetFromWarranty as FactoryGetFromWarranty } from "./factory/GetFromWarranty/GetFromWarranty";
import { SaleAnalysis as FactorySaleAnalysis } from "./factory/SaleAnalysis/SaleAnalysis";
import { ErrorAnalysis } from "./factory/ErrorAnalysis/ErrorAnalysis";

import { GetFromFactory } from "./agency/GetFromFactory/GetFromFactory";
import { SaleProduct } from "./agency/SaleProduct/SaleProduct";
import { GetFromCustomer } from "./agency/GetFromCustomer/GetFromCustomer";
import { GetFromWarranty } from "./agency/GetFromWarranty/GetFromWarranty";
import { NotifyErrorProduct } from "./agency/NotifyErrorProduct/NotifyErrorProduct";
import { RecallProduct } from "./agency/RecallProduct/RecallProduct";
import { SaleAnalysis } from "./agency/SaleAnalysis/SaleAnalysis";

import { GetFromAgency } from "./warranty/GetFromAgency/GetFromAgency";
import { SendToFactory } from "./warranty/SendToFactory/SendToFactory";
import { SendToAgency as WarrantySendToAgency } from "./warranty/SendToAgency/SendToAgency";

export {
    Login, Logout, ForgotPassword, ResetPassword, ProductAnalysis, Product,
    User, Category,
    SendToAgency, AddProduct, FactoryGetFromWarranty, FactorySaleAnalysis, ErrorAnalysis,
    GetFromFactory, SaleProduct, GetFromCustomer, GetFromWarranty, NotifyErrorProduct, RecallProduct, SaleAnalysis,
    GetFromAgency, WarrantySendToAgency, SendToFactory
};