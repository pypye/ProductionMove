import { UseTranslator } from "./UseTranslator";

const UsePreprocessChart = {}
UsePreprocessChart.Line = function (data, name, type) {
    const key = Object.keys(data);
    const firstVal = parseInt(key[0].split('-')[1]);
    const lastVal = parseInt(key[key.length - 1].split('-')[1]);
    const newDataKey = [];
    const newDataValue = [];
    if (type === 0) {
        for (let year = firstVal; year <= lastVal; year++) {
            for (let month = 1; month <= 12; month++) {
                const key = `${month}-${year}`;
                newDataKey.push(key);
                if (!data[key]) {
                    newDataValue.push(0);
                } else {
                    newDataValue.push(data[key]);
                }
            }
        }
        return {
            options: { xaxis: { categories: newDataKey } },
            series: [{ name: name, data: newDataValue }]
        }
    } else if (type === 1) {
        for (let year = firstVal; year <= lastVal; year++) {
            for (let quarter = 1; quarter <= 4; quarter++) {
                const key = `${quarter}-${year}`;
                newDataKey.push(key);
                if (!data[key]) {
                    newDataValue.push(0);
                } else {
                    newDataValue.push(data[key]);
                }
            }
        }
        return {
            options: { xaxis: { categories: newDataKey } },
            series: [{ name: name, data: newDataValue }]
        }
    } else {
        for (let year = parseInt(key[0]); year <= parseInt(key[key.length - 1]); year++) {
            const key = `${year}`;
            newDataKey.push(key);
            if (!data[key]) {
                newDataValue.push(0);
            } else {
                newDataValue.push(data[key]);
            }
        }

        return {
            options: { xaxis: { categories: newDataKey } },
            series: [{ name: name, data: newDataValue }]
        }
    }
}

UsePreprocessChart.Pie = function (data) {
    let key = Object.keys(data);
    let value = Object.values(data);

    key = key.map((item) => {
        return UseTranslator.translate(item);
    })

    return {
        options: {
            labels: key, responsive: [{
                breakpoint: 768,
                options: {
                    legend: {
                        position: 'bottom'
                    }
                }
            }]
        },
        series: value,
    }
}
export { UsePreprocessChart };