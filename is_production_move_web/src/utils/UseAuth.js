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
const UseAuth = {
    get,
    set
}
export { UseAuth };