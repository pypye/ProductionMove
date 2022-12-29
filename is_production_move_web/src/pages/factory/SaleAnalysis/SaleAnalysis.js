import React, { useEffect, useMemo } from 'react';
import Chart from 'react-apexcharts'
import { Option, Section } from '../../../components';
import { UseFetch, UsePreprocessChart } from '../../../utils';

/*
* @description: sale analysis feature
*/
function SaleAnalysis(props) {
    const [data, setData] = React.useState({ options: {}, series: [] });
    const posOption = useMemo(() => ({ 'Tháng': 0, 'Quý': 1, 'Năm': 2 }), []);
    const [option, setOption] = React.useState('Tháng');

    useEffect(() => {
        UseFetch(`/backend/factory/sale/analysis/${posOption[option]}`, 'GET').then((res) => {
            if (res.status.code === 'SUCCESS') {
                if (Object.keys(res.data).length === 0) {
                    setData({ options: {}, series: [] });
                    return;
                }
                let _data = UsePreprocessChart.Mix(res.data, 'Số lượng sản phẩm', 'Doanh thu', posOption[option])
                setData(_data);
            }
        });
    }, [option, posOption]);

    return <Section title="Thống kê số lượng sản phẩm đã bán">
        <React.Fragment>
            <Option title='Phân loại theo' value={option} onChange={setOption}>
                <Option.Item value='Tháng' />
                <Option.Item value='Quý' />
                <Option.Item value='Năm' />
            </Option>
            {
                data.series.length === 0 ? <div className="text-center">Không có dữ liệu</div> : <Chart options={data.options} series={data.series} type="line" height={'150%'} />
            }
        </React.Fragment>
    </Section>
} export { SaleAnalysis };