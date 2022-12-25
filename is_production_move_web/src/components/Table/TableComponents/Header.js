import './style_header.css'
import React from 'react';
import { TableComponent } from '.';
import { Checkbox } from '../..';

const fakeIconComponent = function () {
    return (
        <div className='th-fake-icon'>
            <TableComponent.Icon.SortAsc />
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
        (props.checkbox) ?
            <th><Checkbox checked={props.checked} onClick={props.onClick} /></th>
            :
            <th ref={thRef} onMouseOver={() => displayFakeIcon(true)} onMouseOut={() => displayFakeIcon(false)}>
                <span>{props.label}</span>
                <div className="th-icon">
                    {((props.order && props.order.sortColumn === thRef.current) ? (
                        props.order.sortOrder === 1 ? <TableComponent.Icon.SortAsc /> : (
                            props.order.sortOrder === 2 ? <TableComponent.Icon.SortDesc /> : fakeIconComponent()
                        ))
                        : fakeIconComponent()
                    )}
                </div>
            </th>
    );
}

function TableHeader(props) {
    function onSelectAll() {
        var selected = [];
        if (props.tableData.selected.length === props.tableData.data.length) {
            props.setTableData({ data: props.tableData.data, selected: selected });
        } else {
            for (var i = 0; i < props.tableData.data.length; i++) selected.push(i);
            props.setTableData({ data: props.tableData.data, selected: selected });
        }
    }

    return (
        <thead>
            <tr>
                <TableHeaderCell checkbox checked={props.tableData.selected.length === props.tableData.data.length ? 1 : (props.tableData.selected.length === 0 ? 0 : 2)} onClick={() => onSelectAll()} />
                {
                    props.data.length > 0 && Object.keys(props.data[0]).map((v, i) => {
                        if (v === 'id' && props.displayColumn[i]) return <th key={i}>no.</th>
                        if (v !== '__data_order' && props.displayColumn[i]) return <TableHeaderCell key={i} label={v} order={props.sort} />
                        else return null;
                    })
                }
                <th></th>
            </tr>
        </thead>
    );
}


export { TableHeader };