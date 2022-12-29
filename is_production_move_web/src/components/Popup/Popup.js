import React, { forwardRef, useImperativeHandle } from "react";
import './style.css'

/*
* @description: Popup custom component
*/
const Popup = forwardRef((props, ref) => {
    const container = React.useRef(null);
    const [isOpen, setIsOpen] = React.useState(false);

    useImperativeHandle(ref, () => ({
        forcePopupOpen() {
            setIsOpen(true);
        },
        forcePopupClose() {
            console.log("forcePopupClose")
            setIsOpen(false);
            if (props.onReset) props.onReset()
            if (props.onCloseOther) props.onCloseOther();
        }
    }));

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

            {isOpen &&
                <div className="popup-wrapper">
                    <div className="popup-content" ref={container}>
                        {props.children[1]}
                    </div>
                </div>
            }

        </React.Fragment>
    )
});

Popup.Trigger = function PopupTrigger(props) {
    return (
        <div className="popup-trigger" onClick={() => {
            props.onToggle();
            if (props.onClick) props.onClick();
        }}>
            {props.children}
        </div>
    )
}
Popup.Content = function PopupContent(props) {
    return (
        <React.Fragment>
            {props.children}
        </React.Fragment>
    )
}
export { Popup }