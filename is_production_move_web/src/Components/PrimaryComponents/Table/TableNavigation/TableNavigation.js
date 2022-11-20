import { TableIcon } from '../TableIcon/TableIcon';
import './style.css';
function TableNavigation() {
    return (
        <ul className="tool">
            <li>
                <TableIcon.Column label="Column" />
            </li>
            <li>
                <TableIcon.Filter label="Filter" />
            </li>
            <li>
                <TableIcon.Export label="Export" />
            </li>
        </ul>
    );
}
export { TableNavigation };