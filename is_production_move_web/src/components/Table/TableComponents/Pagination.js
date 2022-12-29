import { TableComponent } from ".";
import { Option } from "../../Option/Option";

/*
* @description: Table Pagination custom component
*/
function Pagination(props) {
    function onChangePagePaginationSize(value) {
        props.setTablePage(t => ({ page: Math.min(Math.max(1, Math.ceil(t.page * t.pageSize / value)), Math.ceil(props.tableData.data.length / value)), pageSize: value }));
    }

    function onChangePagePagination(value) {
        if (props.tablePage.page + value > 0 && props.tablePage.page + value <= Math.ceil(props.tableData.data.length / props.tablePage.pageSize)) {
            props.setTablePage(t => ({ ...t, page: props.tablePage.page + value }));
        }
    }

    return (
        <div className='page-pagination'>
            <Option title='Số hàng:' onChange={onChangePagePaginationSize}>
                <Option.Item value={10} />
                <Option.Item value={25} />
                <Option.Item value={50} />
            </Option>
            {(props.tablePage.page - 1) * props.tablePage.pageSize + 1}-{Math.min((props.tablePage.page - 1) * props.tablePage.pageSize + props.tablePage.pageSize, props.data.length)} trên {props.data.length}
            <div onClick={() => onChangePagePagination(-1)}> <TableComponent.Icon.PaginationPrev /></div>
            <div onClick={() => onChangePagePagination(1)}> <TableComponent.Icon.PaginationNext /></div>
        </div>
    )
}
export { Pagination }