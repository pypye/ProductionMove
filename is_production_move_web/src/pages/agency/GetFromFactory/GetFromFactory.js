import React from "react";
import { Button, Option, Popup, Section, Table } from "../../../components";
import { UseFetch } from "../../../utils"

/*
* @description: request product from factory
*/
function GetFromFactory(props) {
    const ref = React.useRef(null);
    const [factory, setFactory] = React.useState("");
    const [factoryList, setFactoryList] = React.useState([]);
    const [data, setData] = React.useState(null);

    React.useEffect(() => {
        UseFetch("/backend/user/account/2", "GET", null).then((res) => {
            if (res.status.code === "SUCCESS") {
                setFactory(res.data[0].name);
                setFactoryList(res.data);
            }
        });
    }, []);

    const onGetFactoryProduct = () => {
        var _factory = factoryList.filter((item) => { return item.name === factory })[0].id
        UseFetch(`/backend/agency/product/factory/${_factory}`, "GET", null).then((res) => {
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
                        </Popup> : "N/A"
                    }
                    return _item
                })
                setData(_res)
                if (ref.current) ref.current.updateAllTable(_res)
            }
        })
    }

    const onRequestProduct = () => {
        var currentData = ref.current.getTableData()
        var _select = currentData.selected
        var _id = []
        for (var i = 0; i < _select.length; i++) {
            _id.push(currentData.data[_select[i]].id)
        }
        var _factory = factoryList.filter((item) => { return item.name === factory })[0].id

        UseFetch(`/backend/agency/product/factory/${_factory}`, "POST", { "product_id": _id }).then(res => {
            if (res.status.code === "SUCCESS") {
                var _data = currentData.data.filter((item) => {
                    return !_select.includes(currentData.data.indexOf(item))
                })
                setData(_data)
                ref.current.updateAllTable(_data)
                alert("Yêu cầu nhập sản phẩm thành công")
            } else {
                alert("Yêu cầu nhập sản phẩm thất bại")
            }
        })
    }

    return (
        <React.Fragment>
            <Section title="Nhập sản phẩm từ">
                <Section.Div inline>
                    <Option title="Chọn nhà máy" value={factory} onChange={setFactory}>
                        {
                            factoryList.map((item) => {
                                return <Option.Item key={item.name} value={item.name} />
                            })
                        }
                    </Option>
                    <Button onClick={onGetFactoryProduct}>Lấy thông tin sản phẩm</Button>
                </Section.Div>
            </Section>
            {data && <Table title={
                <React.Fragment>
                    <span style={{ marginRight: '1rem' }}>Danh sách sản phẩm từ nhà máy {factory}</span>
                    <Button onClick={onRequestProduct}>Yêu cầu nhập sản phẩm</Button>
                </React.Fragment>
            } ref={ref} data={data} noOption noAddRow checkbox />}
        </React.Fragment>

    )
}
export { GetFromFactory }