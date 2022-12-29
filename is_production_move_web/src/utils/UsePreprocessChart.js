import { UseTranslator } from "./UseTranslator";
/*
* @description: Preprocess data for chart
*/
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

UsePreprocessChart.Bar = function (data, name, arr) {
    const key = arr;
    const newDataKey = [];
    const newDataValue = [];
    for (let i = 0; i < key.length; i++) {
        newDataKey.push(key[i]);
        if (!data[key[i]]) {
            newDataValue.push(0);
        } else {
            newDataValue.push(data[key[i]]);
        }

    }

    return {
        options: { chart: {}, xaxis: { categories: newDataKey },  },
        series: [{ name: name, data: newDataValue }]
    }
}

UsePreprocessChart.Mix = function (data, name1, name2, type) {
    const key = Object.keys(data);
    const firstVal = parseInt(key[0].split('-')[1]);
    const lastVal = parseInt(key[key.length - 1].split('-')[1]);
    const newDataKey = [];
    const newDataValue1 = [];
    const newDataValue2 = [];
    console.log(data, name1, name2, type)
    if (type === 0) {
        for (let year = firstVal; year <= lastVal; year++) {
            for (let month = 1; month <= 12; month++) {
                const key = `${month}-${year}`;
                newDataKey.push(key);
                if (!data[key]) {
                    newDataValue1.push(0);
                    newDataValue2.push(0);
                } else {
                    newDataValue1.push(data[key].numberOfProduct);
                    newDataValue2.push(data[key].revenue);
                }
            }
        }
        return {
            options: { xaxis: { categories: newDataKey }, yaxis: [{}, { opposite: true }] },
            series: [{ name: name1, type: "column", data: newDataValue1 }, { name: name2, type: "line", data: newDataValue2 }]
        }
    } else if (type === 1) {
        for (let year = firstVal; year <= lastVal; year++) {
            for (let quarter = 1; quarter <= 4; quarter++) {
                const key = `${quarter}-${year}`;
                newDataKey.push(key);
                if (!data[key]) {
                    newDataValue1.push(0);
                    newDataValue2.push(0);
                } else {
                    newDataValue1.push(data[key].numberOfProduct);
                    newDataValue2.push(data[key].revenue);
                }
            }
        }
        return {
            options: { xaxis: { categories: newDataKey } },
            series: [{ name: name1, type: "column", data: newDataValue1 }, { name: name2, type: "line", data: newDataValue2 }]
        }
    } else {
        for (let year = parseInt(key[0]); year <= parseInt(key[key.length - 1]); year++) {
            const key = `${year}`;
            newDataKey.push(key);
            newDataKey.push(key);
            if (!data[key]) {
                newDataValue1.push(0);
                newDataValue2.push(0);
            } else {
                newDataValue1.push(data[key].numberOfProduct);
                newDataValue2.push(data[key].revenue);
            }

        }
        return {
            options: { xaxis: { categories: newDataKey } },
            series: [{ name: name1, type: "column", data: newDataValue1 }, { name: name2, type: "line", data: newDataValue2 }]
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