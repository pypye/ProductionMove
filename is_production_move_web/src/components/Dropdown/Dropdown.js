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
    })

    const onDropDownClose = (event) => {
        if (container.current && !container.current.contains(event.target)) {
            setIsOpen(false)
        }
    }


    const toggleDropdown = () => {
        setIsOpen(!isOpen)
    }

    return (
        <div className="dropdown-wrapper" ref={container} onClick={toggleDropdown}>
            {props.children[0]}
            {isOpen && { ...props.children[1] }}
        </div>
    )
}

Dropdown.Main = function DropdownMain(props) {
    return (
        <div className="dropdown-main">
            {props.item}
        </div>
    )
}

Dropdown.Menu = function DropdownMenu(props) {
    return (
        <div className='dropdown-menu-wrapper'>
            <div className="dropdown-menu">
                {props.children}
            </div>
        </div>

    )
}

Dropdown.Item = function DropdownItem(props) {
    const onClick = () => {
        console.log("clicked")
    }

    return (
        <div className="dropdown-item" onClick={onClick}>
            {props.label}
        </div>
    )
}

export { Dropdown }