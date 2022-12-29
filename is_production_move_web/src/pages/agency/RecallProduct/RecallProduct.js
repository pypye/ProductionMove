import React from "react";
import { Button, Option, Popup, Section, Table } from "../../../components";
import { UseFetch } from "../../../utils"

/*
* @description: recall product from customers
*/
function RecallProduct(props) {
    const ref = React.useRef(null);
    const [categoryId, setCategoryId] = React.useState("");
    const [categoryList, setCategoryList] = React.useState([]);
    const [warrantyId, setWarrantyId] = React.useState("");
    const [warrantyList, setWarrantyList] = React.useState([]);
    const [data, setData] = React.useState(null);

    React.useEffect(() => {
        UseFetch("/backend/user/account/3", "GET", null).then((res) => {
            if (res.status.code === "SUCCESS") {
                setWarrantyId(res.data[0].name);
                setWarrantyList(res.data);
            }
        });
    }, []);

    React.useEffect(() => {
        UseFetch("/backend/category/all", "GET", null).then((res) => {
            if (res.status.code === "SUCCESS") {
                setCategoryId(res.data[0].category);
                setCategoryList(res.data);
            }
        });
    }, []);


    const onRecallProduct = () => {
        var _categoryId = categoryList.filter((item) => { return item.category === categoryId })[0].id
        UseFetch(`/backend/agency/product/recall/${_categoryId}`, "POST", null).then((res) => {
            if (res.status.code === "SUCCESS") {
                alert("Thu hồi thành công")
            } else {
                alert("Thu hồi thất bại")
            }
        })
    }

    const onGetList = () => {
        var _categoryId = categoryList.filter((item) => { return item.category === categoryId })[0].id
        UseFetch(`/backend/product/${_categoryId}`, "GET", null).then((res) => {
            if (res.status.code === "SUCCESS") {
                var _res = res.data.filter(item => item.status.status !== 'Lỗi, cần bảo hành').map((item) => {
                    var _item = {
                        id: item.id,
                        productCode: item.productCode,
                        productName: item.productName,
                        category: item.category.category,
                        price: item.price,
                        status: 'Lỗi cần triệu hồi',
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


    const onTransferToWarranty = () => {
        var _warrantyId = warrantyList.filter((item) => { return item.name === warrantyId })[0].id
        UseFetch(`/backend/agency/product/recall/warranty/${_warrantyId}`, "POST", null).then((res) => {
            if (res.status.code === "SUCCESS") {
                var _data = data.map(item => {
                    if (item) {
                        item.status = "Lỗi, cần bảo hành";
                    }
                    return item
                })
                setData(_data)
                ref.current.updateAllTable(_data)
                alert("Chuyển về trung tâm bảo hành thành công")
            } else {
                alert("Chuyển về trung tâm bảo hành thất bại")
            }
        })
    }


    return (
        <React.Fragment>
            <Section title="Thu hồi sản phẩm">
                <Section.Div inline>
                    <Option title="Chọn loại sản phẩm" value={categoryId} onChange={setCategoryId}>
                        {
                            categoryList.map((item) => {
                                return <Option.Item key={item.id} value={item.category} />
                            })
                        }
                    </Option>
                    <Button onClick={() => {
                        onRecallProduct();
                        onGetList();
                    }}>Thu hồi</Button>
                </Section.Div>
            </Section>
            {data && <Table multiTitle={
                <React.Fragment>
                    <h2>Sản phẩm đã thu hồi</h2>
                    <Option title="Chọn TTBH" value={warrantyId} onChange={setWarrantyId}>
                        {warrantyList.map((item) => {
                            return <Option.Item key={item.id} value={item.name} />
                        })}
                    </Option>
                    <Button onClick={onTransferToWarranty}>Chuyển sản phẩm TTBH</Button>
                </React.Fragment>

            } ref={ref} data={data} noOption noAddRow />}
        </React.Fragment>

    )
}
export { RecallProduct }