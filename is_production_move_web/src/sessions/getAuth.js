function getAuth() {
    const auth = localStorage.getItem('auth')
    if (auth) {
        return JSON.parse(auth)
    }
    return null
    
}
export default getAuth