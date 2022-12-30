import React from "react"
import { Form, Popup, Section, Table } from "../../../components"
import { UseFetch, UseValidation } from "../../../utils"

/*
* @description: sell product
*/
function SaleProduct(props) {
    const ref = React.useRef()
    const [data, setData] = React.useState([])
    const [loading, setLoading] = React.useState(true)
    const [customerProductCode, setCustomerProductCode] = React.useState("")
    const [customerName, setCustomerName] = React.useState("")
    const [customerAddress, setCustomerAddress] = React.useState("")
    const [customerPhone, setCustomerPhone] = React.useState("")
    const [error, setError] = React.useState("")

    const validCustomerInfo = () => {
        return UseValidation.customerName(customerName).state && UseValidation.address(customerAddress).state && UseValidation.phone(customerPhone).state
    }

    const onFetchCustomerPopup = (row) => {
        setCustomerProductCode(row.productCode)
        setCustomerName("")
        setCustomerAddress("")
        setCustomerPhone("")
    }

    const onAddCustomerInfo = (productCode) => {
        UseFetch("/backend/agency/product/customer", "POST", { productCode: productCode, name: customerName, address: customerAddress, phone: customerPhone }).then(res => {
            if (res.status.code === "SUCCESS") {
                var _data = data.map(item => {
                    if (item && item.productCode === productCode) {
                        item.option = <Popup>
                            <Popup.Trigger><a href="#/">Xem chi tiết khách hàng</a></Popup.Trigger>
                            <Popup.Content>
                                <Section title="Thông tin sản phẩm" noContainer>
                                    <div><strong>Mã sản phẩm:</strong> {item.productCode}</div>
                                    <div>Sản phẩm đã bán cho khách hàng <strong>{customerName}</strong></div>
                                </Section>
                            </Popup.Content>
                        </Popup>
                    }
                    return item
                })

                setData(_data)
                ref.current.updateAllTable(_data)
                // _data = _data.filter(item => item.productCode !== productCode)
                // setData(_data)
                // ref.current.updateAllTable(_data)
            } else {
                setError("Thêm thông tin khách hàng thất bại")
            }
        })
    }

    React.useEffect(() => {
        UseFetch("/backend/agency/product/customer", "GET", null).then(res => {
            if (res.status.code === "SUCCESS") {
                var _res = res.data.map((item) => {
                    var _item = {
                        id: item.id,
                        productCode: item.productCode,
                        productName: item.productName,
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
                        option: "N/A"
                    }
                    return _item
                })
                setData(_res)
                if (ref.current) ref.current.updateAllTable(_res)
                setLoading(false)
            }
        })
    }, [])

    if (loading || !data) return <React.Fragment />

    return <React.Fragment>
        <Table title="Bán sản phẩm" ref={ref} data={data} noOption noAddRow optionPopup={
            <Form noContainer>
                <Form.Title content="Thông tin khách hàng" />
                <Form.Input label="Mã sản phẩm" reference={[customerProductCode]} disabled />
                <Form.Input label="Tên khách hàng" reference={[customerName, setCustomerName, UseValidation.customerName]} />
                <Form.Input label="Địa chỉ" reference={[customerAddress, setCustomerAddress, UseValidation.address]} />
                <Form.Input label="Số điện thoại" reference={[customerPhone, setCustomerPhone, UseValidation.phone]} />
                <Form.Error enabled={error !== ""} content={error} />
                <Form.Submit content="Bán sản phẩm" validation={validCustomerInfo} onClick={() => onAddCustomerInfo(customerProductCode)} />
            </Form>
        } optionPopupTitle="Bán sản phẩm này" onFetchOptionPopup={onFetchCustomerPopup} />
    </React.Fragment>
} export { SaleProduct }