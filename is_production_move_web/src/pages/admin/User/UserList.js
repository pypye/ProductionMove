import { Form, Option, Table } from "../../../components"
import { UseFetch, UseValidation } from "../../../utils"
import React from "react"


/*
* @description: show and manage user list
*/
function UserList() {
    const rolePos = {
        "admin": 1,
        "factory": 2,
        "warranty": 3,
        "agency": 4,
    }

    const ref = React.useRef(null)
    const [data, setData] = React.useState([])
    const [loading, setLoading] = React.useState(true)
    const [id, setId] = React.useState("")
    const [userId, setUserId] = React.useState("")
    const [username, setUsername] = React.useState("")
    const [email, setEmail] = React.useState("")
    const [password, setPassword] = React.useState("")
    const [confirmPassword, setConfirmPassword] = React.useState("")
    const [phone, setPhone] = React.useState("")
    const [address, setAddress] = React.useState("")
    const [companyName, setCompanyName] = React.useState("")
    const [role, setRole] = React.useState("admin")
    const [error, setError] = React.useState("")

    const validRetypePassword = (input) => {
        return UseValidation.retypePassword(input, password);
    }
    const onValidUser = () => {
        return UseValidation.username(username).state && UseValidation.email(email).state && UseValidation.password(password).state && validRetypePassword(confirmPassword).state && UseValidation.phone(phone).state && UseValidation.address(address).state && UseValidation.companyName(companyName).state
    }

    React.useEffect(() => {
        UseFetch("/backend/admin/user/all", "GET", null).then(data => {
            if (data.status.code === "SUCCESS") {
                data.data.map((item) => {
                    item.role = item.role.role
                    return item
                })

                setData(data.data)
                setLoading(false)
            }
        })
    }, [])

    const onAddUser = () => {
        UseFetch("/backend/admin/user/sign-up", "POST", { username: username, email: email, password: password, retypePassword: confirmPassword, phone: phone, address: address, companyName: companyName, role_id: rolePos[role].toString() }).then(res => {
            if (res.status.code === "SUCCESS") {
                var newRow = {
                    id: (parseInt(data[data.length - 1].id) + 1).toString(),
                    username: username,
                    email: email,
                    role: role,
                    companyName: companyName,
                    address: address,
                    phone: phone,
                }
                setData(prev => {
                    prev.push(newRow)
                    return prev
                })
                onReset()
                ref.current.updateTable(newRow, 'add')
                ref.current.forceAddRowClose()
            } else if (res.status.code === "E-004") {
                setError("Username đã được đăng ký")
            }
            else if (res.status.code === "E-005") {
                setError("Email đã được đăng ký")
            } else if (res.status.code === "E-006") {
                setError("Tên công ty đã được đăng ký")
            } else {
                setError("Lỗi không xác định")
            }
        })
    }

    const onEditRow = () => {
        UseFetch(`/backend/admin/user/update/${userId}`, "PUT", { username: username, email: email, phone: phone, address: address, companyName: companyName, role_id: rolePos[role].toString() }).then(res => {
            if (res.status.code === "SUCCESS") {
                setData(prev => {
                    prev[id] = { id: userId, username: username, email: email, role: role, companyName: companyName, address: address, phone: phone }
                    return prev
                })
                onReset()
                ref.current.updateTable({ id: userId, username: username, email: email, role: role, companyName: companyName, address: address, phone: phone }, 'edit')
                ref.current.forceEditRowClose()
            } else if (res.status.code === "E-004") {
                setError("Username đã được đăng ký")
            }
            else if (res.status.code === "E-005") {
                setError("Email đã được đăng ký")
            } else if (res.status.code === "E-006") {
                setError("Tên công ty đã được đăng ký")
            } else {
                setError("Lỗi không xác định")
            }
        })
    }

    const onDeleteRow = (row) => {
        console.log(row)
    }

    const onFetchEditRow = (row, i) => {
        setId(i)
        setUserId(row.id)
        setUsername(row.username)
        setEmail(row.email)
        setPhone(row.phone)
        setAddress(row.address)
        setCompanyName(row.companyName)
        setRole(row.role)
    }

    const onReset = () => {
        setId("")
        setUsername("")
        setEmail("")
        setPassword("")
        setConfirmPassword("")
        setPhone("")
        setAddress("")
        setCompanyName("")
        setRole("admin")
        setError("")
    }


    if (loading || !data.length) return <React.Fragment />

    return (
        <React.Fragment>
            <Table title='Danh sách người dùng' ref={ref} data={data} addRow={
                <Form>
                    <Form.Title content="Thêm người dùng mới" />
                    <Form.Split>
                        <Form.Input label="Tên đăng nhập" type="text" reference={[username, setUsername, UseValidation.username]} />
                        <Form.Input label="Email" type="text" reference={[email, setEmail, UseValidation.email]} />
                    </Form.Split>
                    <Form.Split>
                        <Form.Input label="Mật khẩu" type="password" reference={[password, setPassword, UseValidation.password]} />
                        <Form.Input label="Nhập lại mật khẩu" type="password" reference={[confirmPassword, setConfirmPassword, validRetypePassword]} />
                    </Form.Split>
                    <Form.Split>
                        <Form.Input label="Số điện thoại" type="text" reference={[phone, setPhone, UseValidation.phone]} />
                        <Form.Input label="Tên công ty" type="text" reference={[companyName, setCompanyName, UseValidation.companyName]} />

                    </Form.Split>
                    <Form.Split>
                        <Form.Input label="Địa chỉ" type="text" reference={[address, setAddress, UseValidation.address]} />
                    </Form.Split>
                    <Form.Split>
                        <Option title='Chức vụ' value={role} onChange={setRole}>
                            <Option.Item value='admin' />
                            <Option.Item value='agency' />
                            <Option.Item value='factory' />
                            <Option.Item value='warranty' />
                        </Option>
                    </Form.Split>
                    <Form.Error enabled={error !== ""} content={error} />
                    <Form.Submit content="Thêm người dùng" validation={onValidUser} onClick={onAddUser} />
                </Form>
            } onDelete={onDeleteRow} editRow={
                <Form noContainer>
                    <Form.Title content="Sửa thông tin người dùng" />
                    <Form.Split>
                        <Form.Input label="Stt." type="text" reference={[id]} disabled />
                        <Form.Input label="Tên đăng nhập" type="text" reference={[username, setUsername, UseValidation.username]} />
                    </Form.Split>
                    <Form.Split>
                        <Form.Input label="Email" type="text" reference={[email, setEmail, UseValidation.email]} />
                        <Form.Input label="Số điện thoại" type="text" reference={[phone, setPhone, UseValidation.phone]} />
                    </Form.Split>
                    <Form.Split>
                        <Form.Input label="Tên công ty" type="text" reference={[companyName, setCompanyName, UseValidation.companyName]} />
                        <Form.Input label="Địa chỉ" type="text" reference={[address, setAddress, UseValidation.address]} />
                    </Form.Split>
                    <Form.Split>
                        <Option title='Chức vụ' value={role} onChange={setRole}>
                            <Option.Item value='admin' />
                            <Option.Item value='agency' />
                            <Option.Item value='factory' />
                            <Option.Item value='warranty' />
                        </Option>
                    </Form.Split>

                    <Form.Error enabled={error !== ""} content={error} />
                    <Form.Submit content="Cập nhật" onClick={onEditRow} />
                </Form>
            } onFetchEditRow={onFetchEditRow} onReset={onReset} />
        </React.Fragment>
    )

} export { UserList }