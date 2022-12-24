import Checkbox from '../../Checkbox/Checkbox';
import { Dropdown } from '../../Dropdown/Dropdown';
import Form from '../../Form/Form';
import Input from '../../Input/Input';
import { Section } from '../../Section/Section';
import { TableIcon } from '../TableIcon/TableIcon';
import './style.css';
import React from 'react';

function TableNavigation(props) {

    return (
        <ul className="tool">
            {props.children.map((v, i) => {
                return <li key={i}>{v}</li>
            })}
        </ul>
    );
}

TableNavigation.AddRow = function TableNavigationAddRow(props) {
    return (
        <Dropdown>
            <Dropdown.Main item={<TableIcon.Add label="Thêm" />} />
            <Dropdown.MenuWrapper width='30rem' right={props.right}>
                {props.addRow}
            </Dropdown.MenuWrapper>
        </Dropdown>
    )
}

TableNavigation.Setting = function TableNavigationSetting(props) {
    const onDisplayColumn = (i) => {
        props.displayColumn.setDisplayColumn((prev) => {
            prev[i] = !prev[i];
            return [...prev];
        })
    }
    return (
        <Dropdown>
            <Dropdown.Main item={<TableIcon.Column label="Tuỳ chỉnh" />} />
            <Dropdown.MenuWrapper width='20rem' right={props.right}>
                <Section title="Chọn cột hiển thị">
                    {props.columns.map((v, i) => {
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

TableNavigation.Filter = function TableNavigationFilter(props) {
    const [filterText, setFilterText] = React.useState('');

    const onFilter = (text) => {
        setFilterText(text);
        props.onFilter(text);
    }

    return (
        <Dropdown>
            <Dropdown.Main item={<TableIcon.Filter label="Lọc" />} />
            <Dropdown.MenuWrapper width='20rem' right={props.right}>
                <Section title="Lọc">
                    <Input type="text" label="Giá trị cần lọc" reference={[filterText, onFilter]} />
                </Section>
            </Dropdown.MenuWrapper>
        </Dropdown>
    )
}

export { TableNavigation };