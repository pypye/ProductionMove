import { UseAuth } from "./UseAuth";

export async function UseFetch(urls, method, body) {
    const baseUrl = "http://localhost:3001";
    const token = UseAuth.get();

    const response = await fetch(baseUrl + urls, {
        method: method,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: body === null ? null : JSON.stringify(body)
    })
    return response.json();
}