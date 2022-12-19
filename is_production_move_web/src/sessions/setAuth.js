function setAuth(info) {
    if (info) {
        localStorage.setItem("auth", JSON.stringify(info));
        
    } else {
        localStorage.removeItem("auth");
    }
} export default setAuth