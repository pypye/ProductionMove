import './style.css'
import React from 'react'
import { TableNavigation } from './TableNavigation/TableNavigation';
import { TableHeaderCell } from './TableHeaderCell/TableHeaderCell';
import { Checkbox } from '..';
import { TableIcon } from './TableIcon/TableIcon';

export default function Table(props) {
    const tableRef = React.useRef(null);
    const [sort, setSort] = React.useState({ sortOrder: 0, sortColumn: null });
    const [tableData, setTableData] = React.useState({ data: [...props.data.map((s, i) => Object.assign({}, { "__data_order": i }, s))], selected: [] });
    const [tablePage, setTablePage] = React.useState({ page: 1, pageSize: 10 });


    React.useEffect(() => {
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
        for (var i = 1; i < headerRow.length; i++) {
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

    function onSelectRow(rowIndex) {
        var selected = tableData.selected;
        var index = selected.indexOf(rowIndex);
        if (index > -1) selected.splice(index, 1);
        else {
            selected.push(rowIndex);
        }
        setTableData({ data: tableData.data, selected: selected });
    }

    function onSelectAll() {
        var selected = [];
        if (tableData.selected.length === tableData.data.length) {
            setTableData({ data: tableData.data, selected: selected });
        } else {
            for (var i = 0; i < tableData.data.length; i++) selected.push(i);
            setTableData({ data: tableData.data, selected: selected });
        }
    }

    function onChangePagePaginationSize(event) {
        setTablePage(t => ({ ...t, pageSize: event.target.value }));
    }

    function onChangePagePagination(value) {
        if (tablePage.page + value > 0 && tablePage.page + value <= Math.ceil(tableData.data.length / tablePage.pageSize)) {
            setTablePage(t => ({ ...t, page: tablePage.page + value }));
        }
    }

    return (
        <div className='table-container' style={{ width: props.width, height: props.height }}>
            <h1 className='title'>Table</h1>
            <TableNavigation />
            <div className='table-wrapper'>
                <table className="table" ref={tableRef}>
                    <thead>
                        <tr>
                            <TableHeaderCell
                                checkbox
                                checked={tableData.selected.length === tableData.data.length ? 1 : (tableData.selected.length === 0 ? 0 : 2)}
                                onClick={() => onSelectAll()}
                            />
                            {
                                props.data.length > 0 && Object.keys(props.data[0]).map((v, i) => {
                                    if (v !== '__data_order') return <TableHeaderCell key={i} label={v} order={sort} />
                                    else return null;
                                })
                            }
                        </tr>
                    </thead>
                    <tbody>
                        {
                            tableData.data.map((v, i) => {
                                if (i >= (tablePage.page - 1) * tablePage.pageSize && i < tablePage.page * tablePage.pageSize) {
                                    return (
                                        <tr key={i} className={tableData.selected.includes(v['__data_order']) ? 'selected-row' : undefined}>
                                            <td><Checkbox checked={tableData.selected.includes(v['__data_order']) ? 1 : 0} onClick={() => onSelectRow(v['__data_order'])} /></td>
                                            {
                                                Object.keys(v).map((v2, i2) => {
                                                    if (v2 !== '__data_order') return <td key={i2}>{v[v2]}</td>
                                                    else return null;
                                                })
                                            }
                                        </tr>
                                    )
                                } else {
                                    return null;
                                }
                            })
                        }
                    </tbody>
                </table>
            </div>
            <div className='page-pagination'>
                Rows per page:
                <select onChange={(e) => onChangePagePaginationSize(e)}>
                    <option>10</option>
                    <option>25</option>
                    <option>50</option>
                    <option>100</option>
                </select>
                {(tablePage.page - 1) * tablePage.pageSize + 1}-{Math.min((tablePage.page - 1) * tablePage.pageSize + tablePage.pageSize, props.data.length)} of {props.data.length}
                <div onClick={() => onChangePagePagination(-1)}> <TableIcon.PaginationPrev /></div>
                <div onClick={() => onChangePagePagination(1)}> <TableIcon.PaginationNext /></div>
            </div>
        </div>
    );
}