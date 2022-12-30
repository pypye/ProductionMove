import React from "react"
import { Form, Option, Popup, Section, Table } from "../../../components"
import { UseFetch } from "../../../utils"

/*
* @description: get product from customer
*/
function GetFromCustomer(props) {
    const ref = React.useRef()
    const [data, setData] = React.useState(null)
    const [loading, setLoading] = React.useState(true)
    const [customerProductCode, setCustomerProductCode] = React.useState("")
    const [warranty, setWarranty] = React.useState("")
    const [warrantyList, setWarrantyList] = React.useState([]);
    const [error, setError] = React.useState("")

    function formatDateTime(date){
        var _date = new Date(date);
        var _year = _date.getFullYear();
        var _month = _date.getMonth() + 1;
        var _day = _date.getDate();
        var _hour = _date.getHours();
        var _minute = _date.getMinutes();
        var _second = _date.getSeconds();
        return `${_day}/${_month}/${_year} ${_hour}:${_minute}:${_second}`;
    }    

    const onFetchCustomerPopup = (row) => {
        setCustomerProductCode(row.productCode)
        setWarranty("")
        UseFetch("/backend/user/account/3", "GET", null).then((res) => {
            if (res.status.code === "SUCCESS") {
                setWarranty(res.data[0].name);
                setWarrantyList(res.data);
            }
        });
    }

    const onSendToWarranty = (productCode) => {
        var _warranty = warrantyList.filter((item) => { return item.name === warranty })[0].id
        UseFetch(`/backend/agency/product/warranty/${productCode}/${_warranty}`, "POST", null).then(res => {
            if (res.status.code === "SUCCESS") {
                var _data = data.map(item => {
                    if (item && item.productCode === productCode) {
                        item.status = "Lỗi, cần bảo hành";
                        item.option = <Popup>
                            <Popup.Trigger><a href="#/">Xem trạng thái bảo hành</a></Popup.Trigger>
                            <Popup.Content>
                                <Section title="Thông tin bảo hành" noContainer>
                                    <div><strong>Mã sản phẩm:</strong> {item.productCode}</div>
                                    <div>Sản phẩm đã được gửi tới trung tâm bảo hành</div>
                                </Section>
                            </Popup.Content>
                        </Popup>
                    }
                    return item
                })

                setData(_data)
                ref.current.updateAllTable(_data)
            } else {
                setError("Thêm thông tin khách hàng thất bại")
            }
        })
    }

    React.useEffect(() => {
        UseFetch("/backend/product/all", "GET", null).then(res => {
            if (res.status.code === "SUCCESS") {
                var _res = res.data.map((item) => {
                    if (item.customer) {
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
                                                return <div key={key}><strong>{key}</strong> {value ? value : "N/A"}</div>
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
                                        <div><strong>Thời gian bán:</strong> {formatDateTime(item.salesTime)}</div>
                                        <div><strong>Số lần bảo hành:</strong> {item.numberOfWarranty}</div>
                                    </Section>
                                </Popup.Content>
                            </Popup> : "N/A",
                            option: (item.status.status === "Hết thời gian bảo hành") ? <div>
                            </div> : (item.status.status === "Lỗi, cần bảo hành") ? <Popup>
                                <Popup.Trigger><a href="#/">Xem trạng thái bảo hành</a></Popup.Trigger>
                                <Popup.Content>
                                    <Section title="Thông tin bảo hành" noContainer>
                                        <div><strong>Mã sản phẩm:</strong> {item.productCode}</div>
                                        <div>Sản phẩm đã được gửi tới trung tâm bảo hành</div>
                                    </Section>
                                </Popup.Content>
                            </Popup> : (item.status.status === "Đã bảo hành xong") ? <Popup>
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
                _res = _res.filter((item) => item !== null)
                setData(_res)
                if (ref.current) ref.current.updateAllTable(_res)
                setLoading(false)
            }
        })
    }, [])

    if (loading || !data) return <React.Fragment />

    return <React.Fragment>
        <Table title="Bảo hành sản phẩm" ref={ref} data={data} noOption noAddRow optionPopup={
            <Form noContainer>
                <Form.Title content="Thông tin bảo hành" />
                <Form.Input label="Mã sản phẩm" reference={[customerProductCode]} disabled />
                <Option title="Chọn TTBH" value={warranty} onChange={setWarranty}>
                    {warrantyList.map((item) => {
                        return <Option.Item key={item.id} value={item.name} />
                    })}
                </Option>
                <Form.Error enabled={error !== ""} content={error} />
                <Form.Submit content="Bảo hành sản phẩm" onClick={() => onSendToWarranty(customerProductCode)} />
            </Form>
        } optionPopupTitle="Bảo hành sản phẩm này" onFetchOptionPopup={onFetchCustomerPopup} />
    </React.Fragment>
} export { GetFromCustomer }