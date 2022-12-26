import React, { useMemo } from "react"
import { Form, Option } from "../../../components"
import { UseFetch, UseValidation } from "../../../utils";

function AddProduct(props) {
    const [loading, setLoading] = React.useState(true);
    const [category, setCategory] = React.useState("");

    const [name, setName] = React.useState("");
    const [type, setType] = React.useState("");
    const [price, setPrice] = React.useState("");
    const [warranty, setWarranty] = React.useState("");

    const [cpu, setCpu] = React.useState("");
    const [hdd, setHdd] = React.useState("");
    const [ram, setRam] = React.useState("");
    const [screen, setScreen] = React.useState("");
    const [gpu, setGpu] = React.useState("");
    const [port, setPort] = React.useState("");
    const [os, setOs] = React.useState("");
    const [design, setDesign] = React.useState("");
    const [weight, setWeight] = React.useState("");


    const description = useMemo(() => {
        return {
            "CPU:": cpu,
            "RAM:": ram,
            "Ổ cứng:": hdd,
            "Màn hình:": screen,
            "Card màn hình:": gpu,
            "Cổng kết nối:": port,
            "Hệ điều hành:": os,
            "Thiết kế:": design,
            "Kích thước, trọng lượng:": weight,
            "Thời điểm ra mắt:": "2022"
        }
    }, [cpu, ram, hdd, screen, gpu, port, os, design, weight]);

    const onValidation = () => {
        return UseValidation.companyName(name).state && UseValidation.price(price).state && UseValidation.warrantTime(warranty).state
    }

    const onReset = () => {
        setName("");
        setType(category[0].category);
        setPrice("");
        setWarranty("");
        setCpu("");
        setHdd("");
        setRam("");
        setScreen("");
        setGpu("");
        setPort("");
        setOs("");
        setDesign("");
        setWeight("");
    }

    React.useEffect(() => {
        UseFetch("/backend/category/all", "GET", null).then(data => {
            if (data.status.code === "SUCCESS") {
                setCategory(data.data)
                setType(data.data[0].category)
                setLoading(false)
            }
        })
    }, [])

    const onAddProduct = () => {
        const data = {
            "productName": name,
            "category_id": category.find(item => item.category === type).id,
            "price": price,
            "warrantTime": warranty,
            "description": JSON.stringify(description)
        }
        UseFetch("/backend/factory/product/add", "POST", data).then(data => {
            if (data.status.code === "SUCCESS") {
                alert("Thêm sản phẩm thành công")
                onReset()
            } else {
                alert("Thêm sản phẩm thất bại")
            }
        })
    }


    if (loading) return <React.Fragment />

    return (
        <React.Fragment>
            <Form>
                <Form.Title content="Thêm sản phẩm" />
                <Form.Split>
                    <Form.Input label="Tên sản phẩm" reference={[name, setName, UseValidation.productName]} />
                    <Option title='Loại sản phẩm' maxWidth value={type} onChange={setType}>
                        {
                            category.map((item, index) => {
                                return <Option.Item key={index} value={item.category} />
                            })
                        }
                    </Option>
                </Form.Split>
                <Form.Split>
                    <Form.Input label="Giá sản phẩm" reference={[price, setPrice, UseValidation.price]} />
                    <Form.Input label="Thời gian bảo hành" reference={[warranty, setWarranty, UseValidation.warrantTime]} />
                </Form.Split>

                <Form.Subtitle content="Thông tin sản phẩm" />
                <Form.Notify enabled content="RAM, ổ cứng, màn hình đã có sẵn đơn vị. Ví dụ: 4GB RAM chỉ cần ghi 4 thay vì 4GB" />
                <Form.Split>
                    <Form.Input label="CPU" reference={[cpu, setCpu]} />
                    <Form.Input label="RAM (GB)" reference={[ram, setRam]} />
                    <Form.Input label="Ổ cứng (GB)" reference={[hdd, setHdd]} />
                    <Form.Input label="Màn hình (inch)" reference={[screen, setScreen]} />
                </Form.Split>
                <Form.Split>
                    <Form.Input label="GPU" reference={[gpu, setGpu]} />
                    <Form.Input label="Cổng kết nối" reference={[port, setPort]} />
                    <Form.Input label="Hệ điều hành" reference={[os, setOs]} />
                </Form.Split>
                <Form.Input label="Thiết kế" reference={[design, setDesign]} />
                <Form.Input label="Kích thước, trọng lượng" reference={[weight, setWeight]} />

                <Form.Submit content="Thêm sản phẩm" validation={onValidation} onClick={onAddProduct} />
            </Form>
        </React.Fragment>
    )
}

export { AddProduct }