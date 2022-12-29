import React from "react";
import { Option, Section } from "../../../components";
import { UseFetch, UsePreprocessChart } from "../../../utils";
import Chart from 'react-apexcharts'

/*
* @description: statistic product feature
*/
function StatisticProduct() {
    const [data, setData] = React.useState({ options: {}, series: [] });
    const posOption = React.useMemo(() => ({ 'Nhà máy': 2, 'TTBH': 3, 'Đại lý': 4 }), []);
    const [factoryList, setFactoryList] = React.useState([]);
    const [agencyList, setAgencyList] = React.useState([]);
    const [warrantyList, setWarrantyLList] = React.useState([]);
    const [option, setOption] = React.useState('Nhà máy');
    const posStatus = React.useMemo(() => ({
        'Mới sản xuất': 1,
        'Đưa về đại lý': 2,
        'Đã bán': 3,
        'Lỗi, cần bảo hành': 4,
        'Đang sửa chữa bảo hành': 5,
        'Đã bảo hành xong': 6,
        'Đã trả lại bảo hành cho khách hàng': 7,
        'Lỗi, cần trả về nhà máy': 8,
        'Lỗi, đã đưa về cơ sở sản xuất': 9,
        'Lỗi cần triệu hồi': 10,
        'Hết thời gian bảo hành': 11,
        'Trả lại cơ sở sản xuất': 12,
    }), []);
    const [status, setStatus] = React.useState('Mới sản xuất');

    React.useEffect(() => {
        UseFetch(`/backend/user/account/2`, 'GET').then((res) => {
            if (res.status.code === 'SUCCESS') {
                var _data = res.data.map((item) => {
                    return item.name;
                });
                setFactoryList(_data);
            }
        });
    }, []);
    React.useEffect(() => {
        UseFetch(`/backend/user/account/3`, 'GET').then((res) => {
            if (res.status.code === 'SUCCESS') {
                var _data = res.data.map((item) => {
                    return item.name;
                });
                setWarrantyLList(_data);
            }
        });
    }, []);
    React.useEffect(() => {
        UseFetch(`/backend/user/account/4`, 'GET').then((res) => {
            if (res.status.code === 'SUCCESS') {
                var _data = res.data.map((item) => {
                    return item.name;
                });
                setAgencyList(_data);
            }
        });
    }, []);


    React.useEffect(() => {
        UseFetch(`/backend/admin/product/statistic/${posStatus[status]}/${posOption[option]}`, 'GET').then((res) => {
            if (res.status.code === 'SUCCESS') {
                if (Object.keys(res.data).length === 0) {
                    setData({ options: {}, series: [] });
                    return;
                }
                if(posOption[option] === 2) {
                    let _data = UsePreprocessChart.Bar(res.data, 'Số lượng sản phẩm', factoryList);
                    setData(_data);
                } else if(posOption[option] === 3) {
                    let _data = UsePreprocessChart.Bar(res.data, 'Số lượng sản phẩm', warrantyList);
                    setData(_data);
                }
                else if(posOption[option] === 4) {
                    let _data = UsePreprocessChart.Bar(res.data, 'Số lượng sản phẩm', agencyList);
                    setData(_data);
                }
            }
        });
    }, [option, posOption, status, posStatus, factoryList, agencyList, warrantyList]);

    return (
        <Section title="Thống kê số lượng sản phẩm theo từng loại">
            <Section.Div inline>
                <Option title='Địa điểm' value={option} onChange={setOption}>
                    <Option.Item value='Nhà máy' />
                    <Option.Item value='TTBH' />
                    <Option.Item value='Đại lý' />
                </Option>
                <Option title='Trạng thái' value={status} onChange={setStatus}>
                    {
                        option === 'Nhà máy' ?
                            <>
                                <Option.Item value='Mới sản xuất' />
                                <Option.Item value='Đưa về đại lý' />
                                <Option.Item value='Lỗi, đã đưa về cơ sở sản xuất' />
                                <Option.Item value='Trả lại cơ sở sản xuất' />
                            </>
                            : (option === 'TTBH') ?
                                <>
                                    <Option.Item value='Đang sửa chữa bảo hành' />
                                    <Option.Item value='Đã bảo hành xong' />
                                    <Option.Item value='Lỗi, cần trả về nhà máy' />
                                </>
                                : (option === 'Đại lý') ?
                                    <>
                                        <Option.Item value='Đưa về đại lý' />
                                        <Option.Item value='Lỗi, cần bảo hành' />
                                        <Option.Item value='Đã bảo hành xong' />
                                        <Option.Item value='Đã trả lại bảo hành cho khách hàng' />
                                        <Option.Item value='Lỗi cần triệu hồi' />
                                        <Option.Item value='Trả lại cơ sở sản xuất' />
                                    </>
                                    : null
                    }
                </Option>
            </Section.Div>
            {
                data.series.length === 0 ? <div className="text-center">Không có dữ liệu</div> : <Chart options={data.options} series={data.series} type="bar" height={'150%'} />
            }
        </Section>
    )
}
export { StatisticProduct }