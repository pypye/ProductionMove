import './style.css'
import React from 'react'
import { TableNavigation } from './TableNavigation/TableNavigation';
import { TableHeaderCell } from './TableHeaderCell/TableHeaderCell';

export default function Table() {
    const tableRef = React.useRef(null);
    const [sort, setSort] = React.useState({ sortOrder: 0, sortColumn: null });

    function sortTable(table, col, order) {
        var tbody = table.querySelector('tbody');
        var rows = Array.prototype.slice.call(tbody.querySelectorAll('tr'));
        rows.sort((a, b) => {
            if (order === 1) {
                return a.cells[col].textContent.localeCompare(b.cells[col].textContent);
            } else if (order === 2) {
                return b.cells[col].textContent.localeCompare(a.cells[col].textContent);
            } else {
                return a.getAttribute('data-order').localeCompare(b.getAttribute('data-order'));
            }

        });

        rows.forEach(v => tbody.appendChild(v));
    }

    React.useEffect(() => {
        var table = tableRef.current;
        for (var i = 1; i < table.rows.length; i++) {
            table.rows[i].setAttribute("data-order", i);
        }
    }, []);

    React.useEffect(() => {
        var table = tableRef.current;
        var headerRow = table.rows[0].children;
        for (var i = 0; i < headerRow.length; i++) {
            headerRow[i].onclick = function () {
                var nextOrder;
                if (this === sort.sortColumn) {
                    nextOrder = (sort.sortOrder + 1) % 3;
                } else {
                    nextOrder = 1;
                }
                setSort({ sortOrder: nextOrder, sortColumn: this });
                sortTable(table, this.cellIndex, nextOrder);
            }

        }
    }, [sort])

    return (
        <div>
            <h1>Table</h1>
            <TableNavigation />

            <table className="table" ref={tableRef}>
                <thead>
                    <tr>
                        <TableHeaderCell label="Column 1" order={sort}/>
                        <TableHeaderCell label="Column 2" order={sort}/>
                        <TableHeaderCell label="Column 3" order={sort}/>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Row 1</td>
                        <td>Row 1</td>
                        <td>Row 1</td>
                    </tr>
                    <tr>
                        <td>Row 2</td>
                        <td>Row 2</td>
                        <td>Row 3</td>
                    </tr>
                    <tr>
                        <td>Row 3</td>
                        <td>Row 3</td>
                        <td>Row 2</td>
                    </tr>
                </tbody>
            </table>

        </div>
    );
}