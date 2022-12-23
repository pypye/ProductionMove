import { Table } from "../../../components"
import { UseFetch } from "../../../utils"
import React from "react"

function UserList() {
    const [data, setData] = React.useState([])
    const [loading, setLoading] = React.useState(true)

    React.useEffect(() => {
        UseFetch("/backend/admin/user/all", "GET", null).then(data => {
            if (data.status.code === "SUCCESS") {
                data.data.map((item) => {
                    item.role = item.role.role
                    return item
                })
                data.data = data.data.concat(data.data)

                setData(data.data)
                setLoading(false)
            }
        })
    }, [])

    if (loading) return <React.Fragment />

    return (
        <React.Fragment>
            <Table title='User List' data={data} />
        </React.Fragment>
    )

} export { UserList }