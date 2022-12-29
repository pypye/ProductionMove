import { Outlet } from "react-router-dom";

/*
* @description: Login layout
*/
export function LoginLayout(props) {
    return (
        <div className="login-container">
            <Outlet />
        </div>
    )
}