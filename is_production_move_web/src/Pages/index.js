import { Login } from "./general/Login/Login";
import { Logout } from "./general/Logout/Logout";
import { ForgotPassword } from "./general/ForgotPassword/ForgotPassword";
import { ResetPassword } from "./general/ResetPassword/ResetPassword";
import { Product } from "./general/Product";

import { User } from "./admin/User";
import { Category } from "./admin/Category";

import { SendToAgency } from "./factory/SendToAgency/SendToAgency";
import { AddProduct } from "./factory/AddProduct/AddProduct";
import { GetFromWarranty as FactoryGetFromWarranty } from "./factory/GetFromWarranty/GetFromWarranty";

import { GetFromFactory } from "./agency/GetFromFactory/GetFromFactory";
import { SaleProduct } from "./agency/SaleProduct/SaleProduct";
import { GetFromCustomer } from "./agency/GetFromCustomer/GetFromCustomer";
import { GetFromWarranty } from "./agency/GetFromWarranty/GetFromWarranty";
import { NotifyErrorProduct } from "./agency/NotifyErrorProduct/NotifyErrorProduct";
import { RecallProduct } from "./agency/RecallProduct/RecallProduct";

import { GetFromAgency } from "./warranty/GetFromAgency/GetFromAgency";
import { SendToFactory } from "./warranty/SendToFactory/SendToFactory";
import { SendToAgency as WarrantySendToAgency } from "./warranty/SendToAgency/SendToAgency";

export {
    Login, Logout, ForgotPassword, ResetPassword, User, Product, Category,
    SendToAgency, AddProduct, FactoryGetFromWarranty,
    GetFromFactory, SaleProduct, GetFromCustomer, GetFromWarranty, NotifyErrorProduct, RecallProduct,
    GetFromAgency, WarrantySendToAgency, SendToFactory
};