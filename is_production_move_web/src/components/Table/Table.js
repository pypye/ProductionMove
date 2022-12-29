import './style.css'
import React, { forwardRef, useImperativeHandle } from 'react'
import { TableComponent } from './TableComponents';


/*
* @description: Table custom component
*/
const Table = forwardRef((props, ref) => {
    const tableRef = React.useRef(null);
    const addRowRef = React.useRef(null);
    const editRowRef = React.useRef(null);

    const [sort, setSort] = React.useState({ sortOrder: 0, sortColumn: null });
    const [tableData, setTableData] = React.useState({ data: [...props.data.map((s, i) => Object.assign({}, { "__data_order": i }, s))], selected: [] });
    const [tablePage, setTablePage] = React.useState({ page: 1, pageSize: 10 });
    const [displayColumn, setDisplayColumn] = React.useState([...Object.keys(props.data.length ? props.data[0] : []).map(() => true)]);

    useImperativeHandle(ref, () => ({
        getTableData() {
            return tableData;
        },
        updateTable(newRow, method) {
            var index, newData;
            if (method === "add") {
                var newRowObject = Object.assign({}, { "__data_order": tableData.data.length }, newRow);
                setTableData({ data: [...tableData.data, newRowObject], selected: tableData.selected });
                setTablePage({ page: Math.ceil(props.data.length / tablePage.pageSize), pageSize: tablePage.pageSize });
            } else if (method === "edit") {
                index = tableData.data.findIndex(item => item.id === newRow.id);
                newData = [...tableData.data];
                newData[index] = Object.assign({}, { "__data_order": index }, newRow);
                setTableData({ data: newData, selected: tableData.selected });
            } else if (method === "delete") {
                index = tableData.data.findIndex(item => item.id === newRow.id);
                newData = [...tableData.data];
                newData.splice(index, 1);
                setTableData({ data: newData, selected: tableData.selected });
            }
        },
        updateAllTable(newData) {
            setTableData({ data: newData.map((s, i) => Object.assign({}, { "__data_order": i }, s)), selected: [] });
            setTablePage({ page: 1, pageSize: tablePage.pageSize });
            setDisplayColumn([...Object.keys(newData.length ? newData[0] : []).map(() => true)]);
        },

        forceAddRowClose() {
            addRowRef.current.forceDropdownClose();
        },

        forceEditRowClose() {
            editRowRef.current.forcePopupClose();
        }
    }));

    React.useEffect(() => {
        if (props.data.length === 0) return;

        const sortTable = (col, order) => {
            //order = 1: ascending, 2: descending, 0: reset 
            if (order === 0) {
                setTableData({ data: [...props.data.map((s, i) => Object.assign({}, { "__data_order": i }, s))], selected: tableData.selected });
            } else {
                var sorted = tableData.data.sort((a, b) => {
                    if (a[col] < b[col]) return -1;
                    if (a[col] > b[col]) return 1;
                    return 0;
                });
                if (order === 2) sorted.reverse();
                setTableData({ data: sorted, selected: tableData.selected });
            }
        }
        var table = tableRef.current;
        var headerRow = table.rows[0].children;
        for (var i = 2; i < headerRow.length - 1; i++) {
            headerRow[i].onclick = function () {
                var nextOrder;
                if (sort.sortColumn && this.innerText === sort.sortColumn.innerText) {
                    nextOrder = (sort.sortOrder + 1) % 3;
                } else {
                    nextOrder = 1;
                }
                setSort({ sortOrder: nextOrder, sortColumn: this });
                sortTable(this.innerText, nextOrder);
                setTablePage({ page: 1, pageSize: tablePage.pageSize });
            }
        }
    }, [sort, tableData, tablePage, props.data]);

    return (
        <div className='table-container' style={{ width: props.width, height: props.height }}>
            {props.title && <h2 className='table-title'>{props.title}</h2>}
            {props.multiTitle && <div className='table-multi-title'>{props.multiTitle}</div>}
            {props.data.length === 0 ? <div className='no-data'>Không có dữ liệu để hiển thị</div> : <React.Fragment>
                <TableComponent.Navigation>
                    {props.noAddRow ? null : <TableComponent.Navigation.AddRow
                        refs={addRowRef}
                        addRow={props.addRow}
                    />}
                    <TableComponent.Navigation.Setting
                        columns={Object.keys(props.data.length ? props.data[0] : [])}
                        displayColumn={{ displayColumn, setDisplayColumn }}
                    />
                    <TableComponent.Navigation.Filter
                        tableData={tableData}
                        tablePage={tablePage}
                        setTableData={setTableData}
                        setTablePage={setTablePage}
                        data={props.data}
                    />
                </TableComponent.Navigation>
                <div className='table-wrapper'>
                    <table className="table" ref={tableRef}>
                        <TableComponent.Header
                            tableData={tableData}
                            sort={sort}
                            displayColumn={displayColumn}
                            setTableData={setTableData}
                            data={props.data}
                            noOption={props.noOption}
                            checkbox={props.checkbox}
                        />
                        <TableComponent.Body
                            tableData={tableData}
                            tablePage={tablePage}
                            displayColumn={displayColumn}
                            editRowRef={editRowRef}
                            setTableData={setTableData}
                            data={props.data}
                            editRow={props.editRow}
                            onDelete={props.onDelete}
                            onFetchEditRow={props.onFetchEditRow}
                            onReset={props.onReset}
                            noOption={props.noOption}
                            checkbox={props.checkbox}
                            optionPopup={props.optionPopup}
                            optionPopupRef={props.optionPopupRef}
                            optionPopupTitle={props.optionPopupTitle}
                            onFetchOptionPopup={props.onFetchOptionPopup}
                        />
                    </table>
                </div>
                <TableComponent.Pagination
                    tableData={tableData}
                    tablePage={tablePage}
                    setTablePage={setTablePage}
                    data={props.data}
                />
            </React.Fragment>}
        </div>
    );
})
export { Table };