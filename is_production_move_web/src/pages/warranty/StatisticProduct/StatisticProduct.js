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
    const posStatus = React.useMemo(() => ({ 'Đang sửa chữa bảo hành': 5, 'Đã bảo hành xong': 6, 'Lỗi, cần trả về nhà máy': 8 }), []);
    const [status, setStatus] = React.useState('Đang sửa chữa bảo hành');

    React.useEffect(() => {
        UseFetch(`/backend/warranty/product/statistic/${posStatus[status]}/${posOption[option]}`, 'GET').then((res) => {
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
                    <Option.Item value='Đang sửa chữa bảo hành' />
                    <Option.Item value='Đã bảo hành xong' />
                    <Option.Item value='Lỗi, cần trả về nhà máy' />
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