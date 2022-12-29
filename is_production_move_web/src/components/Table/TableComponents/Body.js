import React from "react";
import { TableComponent } from ".";
import { Checkbox } from "../../Checkbox/Checkbox";
import { Dropdown } from "../../Dropdown/Dropdown";
import { Form } from "../../Form/Form";
import { Popup } from "../../Popup/Popup";


/*
* @description: Table body component
*/
function Body(props) {
    const dropdownRef = React.useRef(props.tableData.data.map(() => React.createRef()))

    function onSelectRow(rowIndex) {
        var selected = props.tableData.selected;
        var index = selected.indexOf(rowIndex);
        if (index > -1) selected.splice(index, 1);
        else selected.push(rowIndex);
        props.setTableData({ data: props.tableData.data, selected: selected });
    }

    function onCloseOther(i) {
        dropdownRef.current[i].current.forceDropdownClose();
    }
    
    return (
        <tbody>
            {
                props.tableData.data.map((v, i) => {
                    if (i >= (props.tablePage.page - 1) * props.tablePage.pageSize && i < props.tablePage.page * props.tablePage.pageSize) {
                        return (
                            <tr key={i} className={props.tableData.selected.includes(v['__data_order']) ? 'selected-row' : undefined}>
                                {props.checkbox && <td><Checkbox checked={props.tableData.selected.includes(v['__data_order']) ? 1 : 0} onClick={() => onSelectRow(v['__data_order'])} /></td>}
                                {
                                    Object.keys(v).map((v2, i2) => {
                                        if (v2 === 'id' && props.displayColumn[i2 - 1]) return <td key={v2}>{i + 1}</td>;
                                        if (v2 === 'warrantTime' && props.displayColumn[i2 - 1]) return <td key={v2}>{v[v2] + ' tháng'}</td>;
                                        if (v2 === 'option' && props.displayColumn[i2 - 1] && props.optionPopup && v[v2] === "N/A") return <td key={v2}>
                                            <Popup ref={props.optionPopupRef}>
                                                <Popup.Trigger onClick={() => props.onFetchOptionPopup(v)}><a href="#/">{props.optionPopupTitle}</a></Popup.Trigger>
                                                <Popup.Content>
                                                    {props.optionPopup}
                                                </Popup.Content>
                                            </Popup>
                                        </td>;
                                        if (v2 !== '__data_order' && props.displayColumn[i2 - 1]) return <td key={v2}>{v[v2]}</td>;
                                        else return null;
                                    })
                                }
                                {props.noOption ? null :
                                    <td className='option'>
                                        {v.role && v.role === 'admin' ? null :
                                            <Dropdown ref={dropdownRef.current[i]}>
                                                <Dropdown.Main item={<TableComponent.Icon.Option />} />
                                                <Dropdown.Menu right bottom={i >= Math.min(props.tablePage.page * props.tablePage.pageSize, props.data.length) - 2} zIndex={2}>
                                                    <Popup ref={props.editRowRef} onReset={props.onReset} onCloseOther={() => onCloseOther(i)}>
                                                        <Popup.Trigger onClick={() => props.onFetchEditRow(v, i + 1)}><Dropdown.Item label="Sửa" /></Popup.Trigger>
                                                        <Popup.Content>
                                                            {props.editRow}
                                                        </Popup.Content>
                                                    </Popup>
                                                    <Popup>
                                                        <Popup.Trigger><Dropdown.Item label="Xoá" color='red' /></Popup.Trigger>
                                                        <Popup.Content>
                                                            <Form noContainer>
                                                                <Form.Title content='Xác nhận xoá' />
                                                                <Form.Warning content='Bạn có chắc chắn muốn xoá?' enabled />
                                                                <Form.Split>
                                                                    <Form.Submit content='Hủy' onClick={() => onCloseOther(i)} />
                                                                    <Form.Submit type="error" content='Xoá' onClick={() => {
                                                                        props.onDelete(v);
                                                                        onCloseOther(i);
                                                                    }} />
                                                                </Form.Split>
                                                            </Form>
                                                        </Popup.Content>
                                                    </Popup>
                                                </Dropdown.Menu>
                                            </Dropdown>}
                                    </td>
                                }
                            </tr>
                        )
                    } else {
                        return null;
                    }
                })
            }
        </tbody>
    )
}
export { Body }