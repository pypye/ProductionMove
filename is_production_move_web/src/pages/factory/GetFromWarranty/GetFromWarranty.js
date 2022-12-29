import React from "react";
import { Button, Popup, Section, Table } from "../../../components";
import { UseFetch } from "../../../utils"

/*
* @description: get product from warranty
*/
function GetFromWarranty(props) {
    const ref = React.useRef(null);
    const [data, setData] = React.useState(null);
    const [loading, setLoading] = React.useState(true);

    React.useEffect(() => {
        UseFetch(`/backend/factory/product/warranty`, "GET", null).then((res) => {
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
                    }
                    return _item
                })
                setData(_res)
                if (ref.current) ref.current.updateAllTable(_res)
                setLoading(false)
            }
        })
    }, [])

    const onRequestProduct = () => {
        var currentData = ref.current.getTableData()
        var _select = currentData.selected
        var _id = []
        for (var i = 0; i < _select.length; i++) {
            _id.push(currentData.data[_select[i]].id)
        }

        UseFetch(`/backend/factory/product/warranty`, "POST", { "product_id": _id }).then(res => {
            if (res.status.code === "SUCCESS") {
                var _data = currentData.data.filter((item) => {
                    return !_select.includes(currentData.data.indexOf(item))
                })
                _data = _data.map((item, index) => {
                    item['__data_order'] = index
                    return item
                })
                setData(_data)
                ref.current.updateAllTable(_data)
                alert("Lấy sản phẩm thành công")
            } else {
                alert("Lấy sản phẩm thất bại")
            }
        })
    }

    if (loading) return <React.Fragment />
    return (
        <React.Fragment>
            <Table title={
                <React.Fragment>
                    <span style={{ marginRight: '1rem' }}>Lấy sản phẩm lỗi từ TTBH</span>
                    <Button onClick={onRequestProduct}>Lấy sản phẩm đã chọn</Button>
                </React.Fragment>

            } ref={ref} data={data} noOption noAddRow checkbox />
        </React.Fragment>
    )
}
export { GetFromWarranty }