import React from "react"
import { Form, Popup, Section, Table } from "../../../components"
import { UseFetch } from "../../../utils"

function NotifyErrorProduct(props) {
    const ref = React.useRef()
    const [data, setData] = React.useState([])
    const [loading, setLoading] = React.useState(true)
    const [customerProductCode, setCustomerProductCode] = React.useState("")
    const [newProductCode, setNewProductCode] = React.useState("")
    const [error, setError] = React.useState("")

    const onFetchCustomerPopup = (row) => {
        setCustomerProductCode(row.productCode)
        setNewProductCode("")
    }

    const onExchange = (productCode) => {
        UseFetch(`/backend/agency/product/warranty/error`, "POST", { productCode: customerProductCode, changedProductCode: newProductCode }).then(res => {
            if (res.status.code === "SUCCESS") {
                var _data = data.map(item => {
                    if (item && item.productCode === productCode) {
                        item.status = "Lỗi, cần bảo hành";
                        item.option = <Popup>
                            <Popup.Trigger><a href="#/">Đã đổi sản phẩm</a></Popup.Trigger>
                            <Popup.Content>
                                <Section title="Thông báo" noContainer>
                                    <div><strong>Mã sản phẩm:</strong> {customerProductCode}</div>
                                    <div>Sản phẩm đã được đổi cho khách hàng {item.customer.name} với sản phẩm mới là {newProductCode} </div>
                                </Section>
                            </Popup.Content>
                        </Popup>
                    }
                    return item
                })
                setData(_data)
                ref.current.updateAllTable(_data)
                _data = _data.filter(item => item.productCode === productCode)
                setData(_data)
                ref.current.updateAllTable(_data)
            } else {
                setError("Mã sản phẩm không tồn tại hoặc đã được sở hữu bởi khách hàng khác")
            }
        })
    }

    React.useEffect(() => {
        UseFetch("/backend/agency/product/warranty/error", "GET", null).then(res => {
            if (res.status.code === "SUCCESS") {
                var _res = res.data.map((item) => {
                    if (item.customer) {
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
                            </Popup> : "N/A",
                            option: (item.status.status === "Lỗi, cần bảo hành") ? <Popup>
                                <Popup.Trigger><a href="#/">Xem trạng thái bảo hành</a></Popup.Trigger>
                                <Popup.Content>
                                    <Section title="Thông tin bảo hành" noContainer>
                                        <div><strong>Mã sản phẩm:</strong> {item.productCode}</div>
                                        <div>Sản phẩm đã được gửi tới trung tâm bảo hành</div>
                                    </Section>
                                </Popup.Content>
                            </Popup>
                                : (item.status.status === "Đã bảo hành xong") ? <Popup>
                                    <Popup.Trigger><a href="#/">Xem trạng thái bảo hành</a></Popup.Trigger>
                                    <Popup.Content>
                                        <Section title="Thông tin bảo hành" noContainer>
                                            <div><strong>Mã sản phẩm:</strong> {item.productCode}</div>
                                            <div>Sản phẩm đã bảo hành xong, có thể lấy từ trung tâm bảo hành và gửi trả lại khách hàng</div>
                                        </Section>
                                    </Popup.Content>
                                </Popup> : "N/A"
                        }
                        return _item
                    }
                    return null
                })
                setData(_res)
                if (ref.current) ref.current.updateAllTable(_res)
                setLoading(false)
            }
        })
    }, [])

    if (loading || !data) return <React.Fragment />

    return <React.Fragment>
        <Table title="Sản phẩm bị lỗi và đổi trả" ref={ref} data={data} noOption noAddRow optionPopup={
            <Form noContainer>
                <Form.Title content="Đổi sản phẩm mới" />
                <Form.Input label="Mã sản phẩm" reference={[customerProductCode]} disabled />
                <Form.Input label="Mã sản phẩm cần đổi" reference={[newProductCode, setNewProductCode]} />
                <Form.Error enabled={error !== ""} content={error} />
                <Form.Submit content="Bảo hành sản phẩm" onClick={() => onExchange(customerProductCode)} />
            </Form>
        } optionPopupTitle="Đổi sản phẩm mới" onFetchOptionPopup={onFetchCustomerPopup} />
    </React.Fragment>
} export { NotifyErrorProduct }