function showPieChart(dataChart) {
    // {"2021-06-01":[[120,"Продукты"],[654,"Кафе"],[1250,"Одежда"],[2000,"Косметика"],[1000,"Спорт"]]}
    var that = this;
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    const month = getMonth(dataChart);

    function drawChart() {
    
        var result = [["Категория", "Сумма"]];

        dataChart[month].forEach(function(item) {
            let b = [item[1], item[0]];
            result.push(b);
        });


        console.log(result);
        var data = new google.visualization.arrayToDataTable(
            result
        );

        var options = {
            title: 'Соотношение расходов по категориям в выбранном интервале'
            // hAxis: {title: 'Data',  titleTextStyle: {color: '#333'}},
            // vAxis: {minValue: 0, maxValue: 10}
        };

        var chart = new google.visualization.PieChart(document.getElementById('js-pie-analysis'));

        chart.draw(data, options);
    }
}

function getMonth(data) {
    var fields = [];

    for (key in data) {
        fields.push(key);
        }

    return fields;
}

function showBarChart(dataChart) {
    // {"06-2021":[[10200,"Доход"],[5024,"Расход"]],"05-2021":[[10010,"Доход"]],"05-2020":[[120,"Расход"]],"04-2021":[[129,"Расход"]]}
    var that = this;
    
    const budget = $('.js-budget').val();
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    const month = getMonth(dataChart);
    
    console.log("month " + month);

    function drawChart() {
    
        var result = [["Месяц", "Доход", "Расход", "Бюджет"]];

        month.forEach(function(item) {
            let a = [item, 0, 0, parseInt(budget)];
            dataChart[item].forEach(function(el) {
                if (el[1] == "Доход") {
                    a[1] += el[0];
                } else if (el[1] == "Расход") {
                    a[2] += el[0];
                }
            });
            result.push(a);
        });


        console.log(result);
        var data = new google.visualization.arrayToDataTable(
            result
        );

        var options = {
            title: 'Разница доходов и расходов',
            vAxis: {title: 'Cups'},
            hAxis: {title: 'Month'},
            seriesType: 'bars',
            series: {2: {type: 'line'}}
        };

        var chart = new google.visualization.ComboChart(document.getElementById('js-bar-analysis'));
        chart.draw(data, options);
    }
}