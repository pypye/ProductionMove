import { Form, Option, Table } from "../../../components"
import { UseFetch } from "../../../utils"
import React from "react"

function ProductList() {
    const [data, setData] = React.useState([])
    const [categoryData, setCategoryData] = React.useState([])
    // const [dataDescription, setDataDescription] = React.useState({})
    const [loading, setLoading] = React.useState(true)

    React.useEffect(() => {
        UseFetch("/backend/product/all", "GET", null).then(data => {
            let dataDescription = {}
            console.log(data.data)
            if (data.status.code === "SUCCESS") {
                data.data.map((item) => {
                    item.category = item.category.category
                    item.customer = 'N/A'
                    dataDescription[item.productCode] = item.description
                    item.description = 'N/A'
                    item.location = 'N/A'
                    item.status = item.status.status
                    if(!item.salesTime) item.salesTime = 'N/A'
                    // item.description = <a href="#">Xem chi tiết</a>
                    return item
                })

                setData(data.data)
                setLoading(false)
            }
        })

    }, [])

    React.useEffect(() => {
        UseFetch("/backend/category/all", "GET", null).then(data => {
            if (data.status.code === "SUCCESS") {
                setCategoryData(data.data)
                setLoading(false)
            }
        })
    }, [])

    if (loading || !data.length || !categoryData.length) return <React.Fragment />

    return (
        <React.Fragment>
            <Table title='Danh sách sản phẩm' data={data} addRow={
                <Form>
                    <Form.Title content="Nhập sản phẩm mới" />
                    <Form.Input label="productName" type="text" />
                    <Form.Input label="price" type="text" />
                    <Option title='category' maxWidth>
                        {categoryData.map((item) => {
                            return <Option.Item key={item.category} value={item.category} />
                        })}
                    </Option>

                    <Form.Submit content="Nhập sản phẩm" />
                </Form>
            } />
        </React.Fragment>
    )
}
export { ProductList }