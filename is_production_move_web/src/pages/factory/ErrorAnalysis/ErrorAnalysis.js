import React, { useEffect } from "react";
import { Section } from "../../../components";
import { UseFetch, UsePreprocessChart } from "../../../utils";
import Chart from "react-apexcharts";

function ErrorAnalysis(props) {
    const [data, setData] = React.useState({ options: {}, series: [] });

    useEffect(() => {
        UseFetch(`/backend/factory/error/analysis/0/0`, 'GET').then((res) => {
            if (res.status.code === 'SUCCESS') {
                if (Object.keys(res.data).length === 0) return;
                let _data = UsePreprocessChart.Pie(res.data)
                setData(_data);
            }
        });
    }, []);

    return <Section title="Thống kê tỉ lệ sản phẩm bị lỗi">
        {
            (data.series.length === 0 || data.series[1] === 0) ? <div className="text-center">Không có dữ liệu</div> : <Chart options={data.options} series={data.series} type="pie" height={'200%'} />
        }
    </Section>
}
export { ErrorAnalysis }