import './style.css'
import React, { forwardRef, useImperativeHandle } from 'react'

/*
* @description: Dropdown custom component
*/
const Dropdown = forwardRef((props, ref) => {
    const container = React.useRef(null)
    const [isOpen, setIsOpen] = React.useState(false)
    
    useImperativeHandle(ref, () => ({
        forceDropdownOpen() {
            setIsOpen(true);
        },
        forceDropdownClose() {
            setIsOpen(false);
            if (props.onReset) props.onReset();
            if (props.onCloseOther) props.onCloseOther();
        }
    }))

    React.useEffect(() => {
        document.addEventListener("mousedown", onDropDownClose);
        return () => {
            document.removeEventListener("mousedown", onDropDownClose);
        }
    })

    const onDropDownClose = (event) => {
        if (container.current && !container.current.contains(event.target)) {
            setIsOpen(false);
            if (props.onReset) props.onReset();
            if (props.onCloseOther) props.onCloseOther();
        }
    }

    const toggleDropdown = () => {
        if (isOpen) {
            if (props.onReset) props.onReset();
            if (props.onCloseOther) props.onCloseOther();
        }
        setIsOpen(!isOpen);
    }

    return (
        <div className="dropdown-wrapper" ref={container}>
            {React.cloneElement(props.children[0], { onClick: toggleDropdown })}
            {isOpen && { ...props.children[1] }}
        </div>
    )
})

Dropdown.Main = function DropdownMain(props) {
    return (
        <div className="dropdown-main" onClick={props.onClick}>
            {props.item}
        </div>
    )
}
Dropdown.MenuWrapper = function DropdownMenuWrapper(props) {
    return (
        <div className='dropdown-menu-wrapper' style={{ width: props.width, right: props.right ? 0 : null, bottom: props.bottom ? 0 : null, zIndex: props.zIndex }}>
            {props.children}
        </div>
    )
}

Dropdown.MenuWrapperResponsive = function DropdownMenuWrapperResponsive(props) {
    return (
        <div className={'dropdown-menu-wrapper-responsive ' + (props.limitToBorder ? 'dropdown-menu-wrapper-responsive-last' : null)}>
            {props.children}
        </div>
    )
}

Dropdown.Menu = function DropdownMenu(props) {
    return (
        <Dropdown.MenuWrapper width={props.width} right={props.right} bottom={props.bottom} zIndex={props.zIndex}>
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