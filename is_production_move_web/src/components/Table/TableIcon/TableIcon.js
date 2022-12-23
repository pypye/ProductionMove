import React from 'react'
import './style.css'
function Icon(props, icon) {

    const btn_ref = React.useRef(null);

    React.useEffect(() => {
        var btn = btn_ref.current;
        var label = btn.children[1].innerHTML;
        if (label === "") {
            btn_ref.current.classList.add("icon-only");
            btn_ref.current.removeChild(btn.children[1]);
        }
    }, [])

    return (
        <button className="icon-btn" ref={btn_ref}>
            <svg className="icon" focusable="false" aria-hidden="true" viewBox="0 0 24 24">
                {icon}
            </svg>
            <div className="icon-label">{props.label}</div>
        </button>
    )
}
let TableIcon = {}
TableIcon.SortAsc = (props) => Icon(props, <path d="M4 12l1.41 1.41L11 7.83V20h2V7.83l5.58 5.59L20 12l-8-8-8 8z"></path>)
TableIcon.SortDesc = (props) => Icon(props, <path d="M20 12l-1.41-1.41L13 16.17V4h-2v12.17l-5.58-5.59L4 12l8 8 8-8z"></path>)
TableIcon.Column = (props) => Icon(props, <path d="M6 5H3c-.55 0-1 .45-1 1v12c0 .55.45 1 1 1h3c.55 0 1-.45 1-1V6c0-.55-.45-1-1-1zm14 0h-3c-.55 0-1 .45-1 1v12c0 .55.45 1 1 1h3c.55 0 1-.45 1-1V6c0-.55-.45-1-1-1zm-7 0h-3c-.55 0-1 .45-1 1v12c0 .55.45 1 1 1h3c.55 0 1-.45 1-1V6c0-.55-.45-1-1-1z"></path>)
TableIcon.Filter = (props) => Icon(props, <path d="M10 18h4v-2h-4v2zM3 6v2h18V6H3zm3 7h12v-2H6v2z"></path>)
TableIcon.Export = (props) => Icon(props, <path d="M19 12v7H5v-7H3v7c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2v-7h-2zm-6 .67l2.59-2.58L17 11.5l-5 5-5-5 1.41-1.41L11 12.67V3h2z"></path>)
TableIcon.PaginationNext = (props) => Icon(props, <path d="M8.59 16.34l4.58-4.59-4.58-4.59L10 5.75l6 6-6 6z"></path>)
TableIcon.PaginationPrev = (props) => Icon(props, <path d="M15.41 16.09l-4.58-4.59 4.58-4.59L14 5.5l-6 6 6 6z"></path>)
export { TableIcon }