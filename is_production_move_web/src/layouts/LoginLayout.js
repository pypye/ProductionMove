import { Outlet } from "react-router-dom";

export function LoginLayout(props) {
    return (
        <div className="login-container">
            <Outlet />
        </div>
    )
}