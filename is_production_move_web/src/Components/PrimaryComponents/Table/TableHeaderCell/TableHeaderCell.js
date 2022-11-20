import React from 'react';
import { TableIcon } from '../TableIcon/TableIcon';
import './style.css'
const fakeIconComponent = function () {
    return (
        <div className='th-fake-icon'>
            <TableIcon.SortAsc />
        </div>
    )
}
function TableHeaderCell(props) {
    const thRef = React.useRef(null);

    const displayFakeIcon = (isMouseOver) => {
        var fakeIcon = thRef.current.querySelector('.th-fake-icon');
        if (fakeIcon) {
            if (isMouseOver) fakeIcon.style.opacity = 0.5;
            else fakeIcon.style.opacity = 0;
        }
    }

    return (
        <th ref={thRef} onMouseOver={() => displayFakeIcon(true)} onMouseOut={() => displayFakeIcon(false)}>
            <span>{props.label}</span>
            <div className="th-icon">
                {(props.order.sortColumn === thRef.current ? (
                    props.order.sortOrder === 1 ? <TableIcon.SortAsc /> : (
                        props.order.sortOrder === 2 ? <TableIcon.SortDesc /> : fakeIconComponent()
                    ))
                    : fakeIconComponent()
                )}
            </div>
        </th>
    );
} export { TableHeaderCell };