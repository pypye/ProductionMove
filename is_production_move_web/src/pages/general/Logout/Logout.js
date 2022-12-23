import React from "react";

function Logout() {
    React.useEffect(() => {
        localStorage.removeItem('auth');
        window.location.href = '/login';
    }, [])
} export default Logout;