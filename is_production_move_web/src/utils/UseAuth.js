import React from "react";
import { Navigate, Outlet } from "react-router-dom"
import { UseFetch } from "./UseFetch";

function get() {
    const auth = localStorage.getItem('auth')
    if (auth) {
        return JSON.parse(auth)
    }
    return null
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
        UseFetch("/backend/user/info", "GET", null).then(data => {
            if (data.status.code === "SUCCESS") {
                console.log(data.data)
                setAuth(data.data)
                setLoading(false)
            }
        })
    }, [])

    if (props.roles.includes('all')) {
        if (auth && auth.type) {
            return <Navigate to="/" />
        } else {
            return props.element
        }
    }

    return (
        <>
            {loading ? <div></div> :
                (auth && auth.type && props.roles.includes(auth.type)) ? props.element : <Navigate to="/login" />
            }
        </>
    )

    if (auth && auth.type) {
        if (auth.type.find(role => props.roles.includes(role))) {
            return props.element
        } else {
            <Navigate to="/login" />
        }
    }
    return <Navigate to="/login" />
}

const UseAuth = {
    get,
    set,
    Auth
}
export { UseAuth };