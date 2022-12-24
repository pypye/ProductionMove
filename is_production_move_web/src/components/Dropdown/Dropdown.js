import './style.css'
import React from 'react'

function Dropdown(props) {
    const container = React.useRef(null)
    const [isOpen, setIsOpen] = React.useState(false)

    React.useEffect(() => {

        document.addEventListener("mousedown", onDropDownClose)
        return () => {
            document.removeEventListener("mousedown", onDropDownClose)
        }
    }, [])

    const onDropDownClose = (event) => {
        if (container.current && !container.current.contains(event.target)) {
            setIsOpen(false)
        }
    }

    const toggleDropdown = () => {
        setIsOpen(!isOpen)
    }

    return (
        <div className="dropdown-wrapper" ref={container}>
            {React.cloneElement(props.children[0], { onClick: toggleDropdown })}
            {isOpen && { ...props.children[1] }}
        </div>
    )
}

Dropdown.Main = function DropdownMain(props) {
    return (
        <div className="dropdown-main" onClick={props.onClick}>
            {props.item}
        </div>
    )
}
Dropdown.MenuWrapper = function DropdownMenuWrapper(props) {
    return (
        <div className='dropdown-menu-wrapper' style={{ width: props.width, right: props.right ? 0 : null, zIndex: props.zIndex }}>
            {props.children}
        </div>
    )
}

Dropdown.Menu = function DropdownMenu(props) {
    return (
        <Dropdown.MenuWrapper width={props.width} right={props.right} zIndex={props.zIndex}>
            <div className="dropdown-menu">
                {props.children}
            </div>
        </Dropdown.MenuWrapper>
    )
}

Dropdown.Info = function DropdownInfo(props) {
    return (
        <div className="dropdown-info">
            <div className='dropdown-info-name'>{props.userName}</div>
            <div className='dropdown-info-email'>{props.userEmail}</div>
        </div>
    )
}

Dropdown.Item = function DropdownItem(props) {

    return (
        <div className="dropdown-item" onClick={props.onClick} style={{ color: props.color }}>
            {props.label}
        </div>
    )
}

export { Dropdown }