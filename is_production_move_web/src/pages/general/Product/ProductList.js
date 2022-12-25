import { Form, Option, Popup, Section, Table } from "../../../components"
import { UseFetch } from "../../../utils"
import React from "react"

function ProductList() {
    const [data, setData] = React.useState(null)
    const [categoryData, setCategoryData] = React.useState([])
    const [loading, setLoading] = React.useState(true)

    React.useEffect(() => {
        UseFetch("/backend/product/all", "GET", null).then(res => {
            if (res.status.code === "SUCCESS") {
                var _res = res.data.map((item) => {
                    var _item = {
                        id: item.id,
                        productCode: item.productCode,
                        category: item.category.category,
                        price: item.price,
                        status: item.status.status,
                        location: item.location.companyName,
                        warrantTime: item.warrantTime,
                        description: <Popup>
                            <Popup.Trigger><a href="#/">Xem thêm</a></Popup.Trigger>
                            <Popup.Content>
                                <Section title="Thông tin sản phẩm" noContainer>
                                    <div><strong>Mã sản phẩm:</strong> {item.productCode}</div>
                                    {
                                        Object.entries(JSON.parse(item.description.replaceAll("'", '"'))).map(([key, value]) => {
                                            return <div key={key}><strong>{key}</strong> {value}</div>
                                        })
                                    }
                                </Section>
                            </Popup.Content>
                        </Popup>,
                        customer: item.customer ? <Popup>
                            <Popup.Trigger><a href="#/">Xem thêm</a></Popup.Trigger>
                            <Popup.Content>
                                <Section title="Thông tin khách hàng" noContainer>
                                    <div><strong>Mã sản phẩm:</strong> {item.productCode}</div>
                                    <div><strong>Tên khách hàng:</strong> {item.customer.name}</div>
                                    <div><strong>Địa chỉ:</strong> {item.customer.address}</div>
                                    <div><strong>Số điện thoại:</strong> {item.customer.phone}</div>
                                    <div><strong>Thời gian bán:</strong> {item.salesTime}</div>
                                    <div><strong>Số lần bảo hành:</strong> {item.numberOfWarranty}</div>
                                </Section>
                            </Popup.Content>
                        </Popup> : "N/A"
                    }
                    return _item
                })
                setData(_res)
                setLoading(false)
            }
        })
    }, [])

    React.useEffect(() => {
        UseFetch("/backend/category/all", "GET", null).then(res => {
            if (res.status.code === "SUCCESS") {
                setCategoryData(res.data);
                setLoading(false);
            }
        })
    }, [])

    if (loading || !data) return <React.Fragment />

    return (
        <React.Fragment>
            <Table title='Danh sách sản phẩm' data={data} noOption addRow={
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