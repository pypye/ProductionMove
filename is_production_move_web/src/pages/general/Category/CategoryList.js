import { Form, Table } from "../../../components"
import { UseFetch, UseValidation } from "../../../utils"
import React from "react"

function CategoryList(props) {
    const ref = React.useRef(null)
    const [data, setData] = React.useState([])
    const [loading, setLoading] = React.useState(true)
    const [category, setCategory] = React.useState("")

    const onValidCategory = () => {
        return UseValidation.category(category).state
    }

    const onAddCategory = () => {
        UseFetch("/backend/admin/category/add", "POST", { name: category }).then(res => {
            if (res.status.code === "SUCCESS") {
                var newRow = { id: (parseInt(data[data.length - 1].id) + 1).toString(), category: category };
                setData(prev => {
                    prev.push(newRow)
                    return prev
                })
                setCategory("")
                ref.current.updateTable(newRow)
            }
        })
    }


    React.useEffect(() => {
        UseFetch("/backend/category/all", "GET", null).then(data => {
            if (data.status.code === "SUCCESS") {
                setData(data.data)
                setLoading(false)
            }
        })
    }, [])

    if (loading || !data.length) return <React.Fragment />

    return (
        <React.Fragment>
            <Table title='Danh sách dòng sản phẩm' ref={ref} data={data} addRow={
                <Form>
                    <Form.Title content="Thêm dòng sản phẩm mới" />
                    <Form.Input label="Category name" type="text" reference={[category, setCategory, UseValidation.category]} />
                    <Form.Submit content="Thêm dòng sản phẩm" validation={onValidCategory} onClick={onAddCategory} />
                </Form>
            } />
        </React.Fragment>
    )
} export { CategoryList }