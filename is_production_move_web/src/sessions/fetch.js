import getAuth from "./getAuth";

async function fetch(urls, method, body) {
    const baseUrl = "http://localhost:3001";
    const token = getAuth() === null ? null : getAuth().token;
    const response = await window.fetch(baseUrl + urls, {
        method: method,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: body === null ? null : JSON.stringify(body)
    }
    )
    return response.json();
} export default fetch