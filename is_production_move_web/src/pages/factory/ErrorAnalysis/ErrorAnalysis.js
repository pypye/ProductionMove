import React, { useEffect } from "react";
import { Option, Section } from "../../../components";
import { UseFetch, UsePreprocessChart } from "../../../utils";
import Chart from "react-apexcharts";

/*
* @description: error analysis feature
*/
function ErrorAnalysis(props) {
    const [data, setData] = React.useState({ options: {}, series: [] });
    const [category, setCategory] = React.useState("");
    const [agency, setAgency] = React.useState("");
    const [categoryList, setCategoryList] = React.useState([]);
    const [agencyList, setAgencyList] = React.useState([]);

    useEffect(() => {
        UseFetch("/backend/user/account/4", "GET", null).then((res) => {
            if (res.status.code === "SUCCESS") {
                var _data = { id: 0, name: "Tất cả đại lý" }
                res.data.unshift(_data)
                setAgency(res.data[0].name);
                setAgencyList(res.data);
            }
        });
    }, []);

    useEffect(() => {
        UseFetch("/backend/category/all", "GET", null).then((res) => {
            if (res.status.code === "SUCCESS") {
                var _data = { id: 0, category: "Tất cả sản phẩm" }
                res.data.unshift(_data)
                setCategory(res.data[0].category);
                setCategoryList(res.data);
            }
        });
    }, []);

    useEffect(() => {
        var _category = categoryList.filter((item) => { return item.category === category })[0]?.id
        var _agency = agencyList.filter((item) => { return item.name === agency })[0]?.id
        UseFetch(`/backend/factory/error/analysis/${_category}/${_agency}`, 'GET').then((res) => {
            if (res.status.code === 'SUCCESS') {
                if (Object.keys(res.data).length === 0) {
                    setData({ options: {}, series: [] });
                    return;
                }
                let _data = UsePreprocessChart.Pie(res.data)
                setData(_data);
            }
        });
    }, [category, agency, categoryList, agencyList]);



    return <Section title="Thống kê tỉ lệ sản phẩm bị lỗi">
        <Section.Div inline>
            <Option title="Chọn loại sản phẩm" value={category} onChange={setCategory}>
                {
                    categoryList.map((item) => {
                        return <Option.Item key={item.id} value={item.category} />
                    })
                }
            </Option>
            <Option title="Chọn đại lý" value={agency} onChange={setAgency}>
                {
                    agencyList.map((item) => {
                        return <Option.Item key={item.id} value={item.name} />
                    })
                }
            </Option>
        </Section.Div>
        {
            (data.series.length === 0 || data.series[1] === 0) ? <div className="text-center">Không có dữ liệu</div> : <Chart options={data.options} series={data.series} type="pie" height={'150%'} />
        }
    </Section>
}
export { ErrorAnalysis }