import { Form, Option, Table } from "../../../components"
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

                setData(data.data)
                setLoading(false)
            }
        })
    }, [])


    if (loading || !data.length) return <React.Fragment />

    return (
        <React.Fragment>
            <Table title='Danh sách người dùng' data={data} addRow={
                <Form>
                    <Form.Title content="Thêm người dùng mới" />
                    <Form.Split>
                        <Form.Input label="Username" type="text" />
                        <Form.Input label="Email" type="text" />
                    </Form.Split>
                    <Form.Split>
                        <Form.Input label="Password" type="password" />
                        <Form.Input label="Confirm password" type="password" />
                    </Form.Split>
                    <Form.Split>
                        <Form.Input label="Phone" type="text" />
                        <Form.Input label="Address" type="text" />
                    </Form.Split>
                    <Form.Split>
                        <Form.Input label="Company name" type="text" />
                        <Option title='Role' maxWidth>
                            <Option.Item value='admin' />
                            <Option.Item value='agency' />
                            <Option.Item value='factory' />
                            <Option.Item value='warranty' />
                        </Option>
                    </Form.Split>
                    <Form.Submit content="Add" />
                </Form>
            } />
        </React.Fragment>
    )

} export { UserList }