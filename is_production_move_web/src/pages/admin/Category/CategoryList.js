import { Form, Table } from "../../../components"
import { UseFetch, UseValidation } from "../../../utils"
import React from "react"

/*
* @description: show category list
*/
function CategoryList() {
    const ref = React.useRef(null)
    const [data, setData] = React.useState(null)
    const [loading, setLoading] = React.useState(true)
    const [id, setId] = React.useState("")
    const [rowId, setRowId] = React.useState("")
    const [category, setCategory] = React.useState("")
    const [error, setError] = React.useState("")

    const onReset = () => {
        setCategory("")
        setError("")
    }

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
                onReset()
                ref.current.updateTable(newRow, 'add')
                ref.current.forceAddRowClose()
            } else {
                setError('Dòng sản phẩm đã tồn tại.')
            }
        })
    }


    const onFetchEditRow = (row, i) => {
        setId(i)
        setRowId(row.id)
        setCategory(row.category)
    }

    const onDeleteRow = (row) => {
        UseFetch(`/backend/admin/category/delete/${row.id}`, "DELETE", null).then(res => {
            if (res.status.code === "SUCCESS") {
                setData(prev => {
                    var index = prev.findIndex(item => item.id === row.id)
                    prev.splice(index, 1)
                    return prev
                })
                ref.current.updateTable(row, 'delete')
            }
        })
    }

    const onEditRow = () => {
        UseFetch(`/backend/admin/category/update/${rowId}`, "PUT", { name: category }).then(res => {
            if (res.status.code === "SUCCESS") {
                setData(prev => {
                    prev[id].category = category
                    return prev
                })
                setError("")
                ref.current.updateTable({ id: id, category: category }, 'edit')
                ref.current.forceEditRowClose()
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

    if (loading || !data) return <React.Fragment />

    return (
        <React.Fragment>
            <Table title='Danh sách dòng sản phẩm' ref={ref} data={data} addRow={
                <Form>
                    <Form.Title content="Thêm dòng sản phẩm mới" />
                    <Form.Input label="Tên dòng sản phẩm" type="text" reference={[category, setCategory, UseValidation.category]} />
                    <Form.Error enabled={error !== ""} content={error} />
                    <Form.Submit content="Thêm dòng sản phẩm" validation={onValidCategory} onClick={onAddCategory} />
                </Form>
            } onDelete={onDeleteRow} editRow={
                <Form noContainer>
                    <Form.Title content="Sửa dòng sản phẩm" />
                    <Form.Input label="Stt." type="text" reference={[id]} disabled />
                    <Form.Input label="Tên dòng sản phẩm" type="text" reference={[category, setCategory, UseValidation.category]} />
                    <Form.Error enabled={error !== ""} content={error} />
                    <Form.Submit content="Cập nhật" validation={onValidCategory} onClick={onEditRow} />
                </Form>
            } onFetchEditRow={onFetchEditRow} onReset={onReset} />
        </React.Fragment>
    )
} export { CategoryList }