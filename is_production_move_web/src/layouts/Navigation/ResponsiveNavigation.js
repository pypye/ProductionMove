import React, { forwardRef } from 'react'
import { useImperativeHandle } from 'react'
import './style_responsive.css'

/*
* @description: Responsive Navigation component for small screen
*/
const ResponsiveNavigation = forwardRef((props, ref) => {
    const container = React.useRef(null);
    const [isOpen, setIsOpen] = React.useState(false);

    useImperativeHandle(ref, () => ({
        forcePopupOpen() {
            setIsOpen(true);
        },
        forcePopupClose() {
            setIsOpen(false);
            if (props.onReset) props.onReset()
            if (props.onCloseOther) props.onCloseOther();
        }
    }), [props]);

    React.useEffect(() => {
        document.addEventListener("mousedown", onPopupClose);
        return () => {
            document.removeEventListener("mousedown", onPopupClose);
        }
    });

    const onPopupClose = (event) => {
        if (container.current && !container.current.contains(event.target)) {
            setIsOpen(false);
            if (props.onReset) props.onReset()
            if (props.onCloseOther) props.onCloseOther();
        }
    }

    const togglePopup = () => {
        if (isOpen) {
            if (props.onReset) props.onReset()
            if (props.onCloseOther) props.onCloseOther();
        }
        setIsOpen(!isOpen);
    }

    return (
        <React.Fragment>
            {React.cloneElement(props.children[0], { onToggle: togglePopup })}
            <div className="navigation-wrapper" style={{ display: isOpen ? 'block' : 'none' }}>
                {isOpen &&
                    <div className="navigation-container" ref={container}>
                        {props.children[1]}
                    </div>
                }
            </div>
        </React.Fragment>
    )
});

ResponsiveNavigation.Trigger = function ResponsiveNavigationTrigger(props) {
    return (
        <div className="navigation-trigger" onClick={() => {
            props.onToggle();
            if (props.onClick) props.onClick();
        }}>
            {props.children}
        </div>
    )
}

ResponsiveNavigation.Content = function ResponsiveNavigationContent(props) {
    return (
        <div className='navigation-content'>
            {props.children}
        </div>
    )
}
export { ResponsiveNavigation }