import React from "react";
import { Option, Section } from "../../../components";
import { UseFetch, UsePreprocessChart } from "../../../utils";
import Chart from 'react-apexcharts'

/*
* @description: statistic product feature
*/
function StatisticProduct() {
    const [data, setData] = React.useState({ options: {}, series: [] });
    const posOption = React.useMemo(() => ({ 'Tháng': 0, 'Quý': 1, 'Năm': 2 }), []);
    const [option, setOption] = React.useState('Tháng');
    const posStatus = React.useMemo(() => ({ 'Đưa về đại lý': 2, 'Lỗi, cần bảo hành': 4, 'Đã bảo hành xong': 6, 'Đã trả lại bảo hành cho khách hàng': 7, 'Lỗi cần triệu hồi': 10, 'Trả lại cơ sở sản xuất': 12 }), []);
    const [status, setStatus] = React.useState('Đưa về đại lý');

    React.useEffect(() => {
        UseFetch(`/backend/agency/product/statistic/${posStatus[status]}/${posOption[option]}`, 'GET').then((res) => {
            if (res.status.code === 'SUCCESS') {
                if (Object.keys(res.data).length === 0) {
                    setData({ options: {}, series: [] });
                    return;
                }
                let _data = UsePreprocessChart.Line(res.data, 'Số lượng sản phẩm', posOption[option])
                setData(_data);
            }
        });
    }, [option, posOption, status, posStatus]);

    return (
        <Section title="Thống kê số lượng sản phẩm theo từng loại">
            <Section.Div inline>
                <Option title='Phân loại theo' value={status} onChange={setStatus}>
                    <Option.Item value='Đưa về đại lý' />
                    <Option.Item value='Lỗi, cần bảo hành' />
                    <Option.Item value='Đã bảo hành xong' />
                    <Option.Item value='Đã trả lại bảo hành cho khách hàng' />
                    <Option.Item value='Lỗi cần triệu hồi' />
                    <Option.Item value='Trả lại cơ sở sản xuất' />
                </Option>
                <Option title='Phân loại theo' value={option} onChange={setOption}>
                    <Option.Item value='Tháng' />
                    <Option.Item value='Quý' />
                    <Option.Item value='Năm' />
                </Option>
            </Section.Div>
            {
                data.series.length === 0 ? <div className="text-center">Không có dữ liệu</div> : <Chart options={data.options} series={data.series} type="area" height={'150%'} />
            }
        </Section>
    )
}
export { StatisticProduct }