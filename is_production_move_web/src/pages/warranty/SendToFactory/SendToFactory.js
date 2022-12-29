import React from "react";
import { Button, Option, Popup, Section, Table } from "../../../components";
import { UseFetch } from "../../../utils"

/*
* @description: send product to factory
*/
function SendToFactory(props) {
    const ref = React.useRef(null);
    const [data, setData] = React.useState(null);
    const [loading, setLoading] = React.useState(true);
    const [factoryId, setFactoryId] = React.useState("2");
    const [factoryList, setFactoryList] = React.useState([]);

    React.useEffect(() => {
        UseFetch("/backend/user/account/2", "GET", null).then((res) => {
            if (res.status.code === "SUCCESS") {
                setFactoryId(res.data[0].name);
                setFactoryList(res.data);
            }
        });
    }, []);


    React.useEffect(() => {
        UseFetch(`/backend/warranty/product/all`, "GET", null).then((res) => {
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
                        </Popup>
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
        var _factoryId = factoryList.filter((item) => { return item.name === factoryId })[0].id
        UseFetch(`/backend/warranty/product/factory/error/${_factoryId}`, "POST", { "product_id": _id }).then(res => {
            if (res.status.code === "SUCCESS") {
                var _data = currentData.data.filter((item) => {
                    return !_select.includes(currentData.data.indexOf(item))
                })
                setData(_data)
                ref.current.updateAllTable(_data)
                alert("Chuyển sản phẩm về nhà máy thành công")
            } else {
                alert("Chuyển sản phẩm về nhà máy thất bại")
            }
        })
    }

    if (loading) return <React.Fragment />

    return (
        <React.Fragment>
            <Table multiTitle={
                <React.Fragment>
                    <h2>Sản phẩm không thể bảo hành</h2>
                    <Option title="Chọn nhà máy" value={factoryId} onChange={setFactoryId}>
                        {
                            factoryList.map((item) => {
                                return <Option.Item key={item.id} value={item.name} />
                            })
                        }
                    </Option>
                    <Button onClick={onRequestProduct}>Chuyển sản phẩm đã chọn về nhà máy</Button>
                </React.Fragment>

            } ref={ref} data={data} noOption noAddRow checkbox />
        </React.Fragment>
    )
}
export { SendToFactory }