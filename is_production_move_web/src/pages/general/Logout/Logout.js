import React from "react";

/*
* @description: Logout
*/
function Logout() {
    React.useEffect(() => {
        localStorage.removeItem('auth');
        window.location.href = '/login';
    }, [])
} export { Logout };