import React from "react";
import { Button, Popup, Section, Table } from "../../../components";
import { UseFetch } from "../../../utils"

/*
* @description: unsold product and return to factory
*/
function UnsoldProduct(props) {
    const ref = React.useRef(null);
    const [data, setData] = React.useState(null);
    const [loading, setLoading] = React.useState(true);

    React.useEffect(() => {
        UseFetch(`/backend/agency/product/unsold`, "GET", null).then((res) => {
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

        UseFetch(`/backend/agency/product/unsold`, "POST", { "product_id": _id }).then(res => {
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
            <Table title={
                <React.Fragment>
                    <span style={{ marginRight: '1rem' }}>Sản phẩm tồn kho quá 1 năm</span>
                    <Button onClick={onRequestProduct}>Chuyển sản phẩm đã chọn về nhà máy</Button>
                </React.Fragment>

            } ref={ref} data={data} noOption noAddRow checkbox />
        </React.Fragment>
    )
}
export { UnsoldProduct }