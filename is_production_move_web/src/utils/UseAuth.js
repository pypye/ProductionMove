import React from "react";
import { Navigate } from "react-router-dom"
import { UseFetch } from "./UseFetch";

function get() {
    const auth = localStorage.getItem('auth')
    return auth ? JSON.parse(auth) : null
}

function set(info) {
    if (info) {
        localStorage.setItem("auth", JSON.stringify(info));
    } else {
        localStorage.removeItem("auth");
    }
}

function Auth(props) {
    const [loading, setLoading] = React.useState(true)
    const [auth, setAuth] = React.useState(null)

    React.useEffect(() => {
        if (get()) {
            UseFetch("/backend/user/info", "GET", null).then(data => {
                if (data.status.code === "SUCCESS") {
                    setAuth(data.data)
                    setLoading(false)
                } else {
                    setAuth(null)
                    setLoading(false)
                }
            })
        } else {
            setAuth(null)
            setLoading(false)
        }
    }, [])

    if (loading) {
        return <React.Fragment />
    }

    if (props.roles.includes('all')) {
        if (auth && auth.type) {
            return <Navigate to="/" />
        } else {
            return React.cloneElement(props.element, auth)
        }
    }

    if (auth && auth.type && props.roles.includes(auth.type)) {
        return React.cloneElement(props.element, auth)
    } else {
        return <Navigate to="/login" />
    }
}

const UseAuth = {
    get,
    set,
    Auth
}
export { UseAuth };