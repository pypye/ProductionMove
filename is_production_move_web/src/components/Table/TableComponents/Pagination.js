import { TableComponent } from ".";
import { Option } from "../../Option/Option";

function Pagination(props) {
    
    function onChangePagePaginationSize(event) {
        props.setTablePage(t => ({ page: Math.min(Math.max(1, Math.ceil(t.page * t.pageSize / event.target.value)), Math.ceil(props.tableData.data.length / event.target.value)), pageSize: event.target.value }));
    }

    function onChangePagePagination(value) {
        if (props.tablePage.page + value > 0 && props.tablePage.page + value <= Math.ceil(props.tableData.data.length / props.tablePage.pageSize)) {
            props.setTablePage(t => ({ ...t, page: props.tablePage.page + value }));
        }
    }
    return (
        <div className='page-pagination'>
            <Option title='Rows per page:' onChange={(e) => onChangePagePaginationSize(e)}>
                <Option.Item value={10} />
                <Option.Item value={25} />
                <Option.Item value={50} />
                <Option.Item value={100} />
            </Option>
            {(props.tablePage.page - 1) * props.tablePage.pageSize + 1}-{Math.min((props.tablePage.page - 1) * props.tablePage.pageSize + props.tablePage.pageSize, props.data.length)} of {props.data.length}
            <div onClick={() => onChangePagePagination(-1)}> <TableComponent.Icon.PaginationPrev /></div>
            <div onClick={() => onChangePagePagination(1)}> <TableComponent.Icon.PaginationNext /></div>
        </div>
    )
}
export { Pagination }