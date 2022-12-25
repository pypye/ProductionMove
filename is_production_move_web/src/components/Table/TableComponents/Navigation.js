import './style_navigation.css';
import React from 'react';
import { Checkbox } from '../../Checkbox/Checkbox';
import { Dropdown } from '../../Dropdown/Dropdown';
import { Form } from '../../Form/Form';
import { Input } from '../../Input/Input';
import { Section } from '../../Section/Section';
import { TableComponent } from '.';

function Navigation(props) {
    return (
        <ul className="tool">
            {props.children.map((v, i) => {
                return <li key={i}>{v}</li>
            })}
        </ul>
    );
}

Navigation.AddRow = function NavigationAddRow(props) {
    return (
        <Dropdown ref={props.refs} onReset={props.onReset}>
            <Dropdown.Main item={<TableComponent.Icon.Add label="Thêm" />} />
            <Dropdown.MenuWrapper width='30rem' right={props.right}>
                {props.addRow}
            </Dropdown.MenuWrapper>
        </Dropdown>
    )
}

Navigation.Setting = function NavigationSetting(props) {
    const onDisplayColumn = (i) => {
        props.displayColumn.setDisplayColumn((prev) => {
            prev[i] = !prev[i];
            return [...prev];
        })
    }
    return (
        <Dropdown>
            <Dropdown.Main item={<TableComponent.Icon.Column label="Tuỳ chỉnh" />} />
            <Dropdown.MenuWrapper width='20rem' right={props.right}>
                <Section title="Chọn cột hiển thị">
                    {props.columns.map((v, i) => {
                        if (v === 'id') {
                            return <Form.Split key={i} format='dashed-bottom'>
                                <Checkbox checked={1} disabled={1}></Checkbox>
                                <span className='setting-column'>no.</span>
                            </Form.Split>
                        }
                        return <Form.Split key={i} format='dashed-bottom'>
                            <Checkbox checked={props.displayColumn.displayColumn[i] ? 1 : 0} onClick={() => onDisplayColumn(i)}></Checkbox>
                            <span className='setting-column'>{v}</span>
                        </Form.Split>
                    })}
                </Section>
            </Dropdown.MenuWrapper>
        </Dropdown>
    )
}

Navigation.Filter = function NavigationFilter(props) {
    const [filterText, setFilterText] = React.useState('');

    const onFilter = (text) => {
        setFilterText(text);
        var filtered = props.data.filter(s => {
            var x = Object.values(s).some(v => v.toString().toLowerCase().includes(text.toLowerCase()))
            return x;
        });
        props.setTableData({ data: [...filtered.map((s, i) => Object.assign({}, { "__data_order": i }, s))], selected: props.tableData.selected });
        props.setTablePage({ page: 1, pageSize: props.tablePage.pageSize });
    }

    return (
        <Dropdown>
            <Dropdown.Main item={<TableComponent.Icon.Filter label="Lọc" />} />
            <Dropdown.MenuWrapper width='20rem' right={props.right}>
                <Section title="Lọc">
                    <Input type="text" label="Giá trị cần lọc" reference={[filterText, onFilter]} />
                </Section>
            </Dropdown.MenuWrapper>
        </Dropdown>
    )
}

export { Navigation };