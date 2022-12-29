import './style_navigation.css';
import React from 'react';
import { Checkbox } from '../../Checkbox/Checkbox';
import { Dropdown } from '../../Dropdown/Dropdown';
import { Form } from '../../Form/Form';
import { Input } from '../../Input/Input';
import { Section } from '../../Section/Section';
import { TableComponent } from '.';
import { UseTranslator } from '../../../utils';


/*
* @description: Table Navigation custom component
*/
function Navigation(props) {
    return (
        <ul className="tool">
            {props.children.map((v, i) => {
                if (v) return <li key={i}>{v}</li>
                return null;
            })}
        </ul>
    );
}

Navigation.AddRow = function NavigationAddRow(props) {
    return (
        <Dropdown ref={props.refs} onReset={props.onReset}>
            <Dropdown.Main item={<TableComponent.Icon.Add label="Thêm" />} />
            <Dropdown.MenuWrapperResponsive width='30rem' right={props.right}>
                {props.addRow}
            </Dropdown.MenuWrapperResponsive>
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
            <Dropdown.Main item={<TableComponent.Icon.Column label="Cài đặt" />} />
            <Dropdown.MenuWrapperResponsive limitToBorder>
                <Section title="Chọn cột hiển thị">
                    {props.columns.map((v, i) => {
                        if (v === 'id') {
                            return <Form.SplitFlex key={i} format='dashed-bottom'>
                                <Checkbox checked={1} disabled={1}></Checkbox>
                                <span className='setting-column'>Stt.</span>
                            </Form.SplitFlex>
                        }
                        return <Form.SplitFlex key={i} format='dashed-bottom'>
                            <Checkbox checked={props.displayColumn.displayColumn[i] ? 1 : 0} onClick={() => onDisplayColumn(i)}></Checkbox>
                            <span className='setting-column'>{UseTranslator.translate(v)}</span>
                        </Form.SplitFlex>
                    })}
                </Section>
            </Dropdown.MenuWrapperResponsive>
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
            <Dropdown.MenuWrapperResponsive limitToBorder>
                <Section title="Lọc">
                    <Input type="text" label="Giá trị cần lọc" reference={[filterText, onFilter]} />
                </Section>
            </Dropdown.MenuWrapperResponsive>
        </Dropdown>
    )
}
export { Navigation };