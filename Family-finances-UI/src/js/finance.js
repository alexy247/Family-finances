function createNewFinance() {
    console.log('createNewFinance');

    const type = $('.js-type-input:checked').val();
    const category = $('.js-categories-list').val();

    const amount = $('.js-amount').val();
    const comment = $('.js-comment').val();
    const date = $('.js-date').val();

    const error = $('.js-error-layer');

    
    console.log(type);
    console.log(category);
    console.log(amount);
    console.log(comment);
    console.log(date);

    createFinance(getRoomName(), getUserName(), category, type, amount, date, comment)
        .then((res) => {
            console.log("createFinance then " + JSON.stringify(res));
            closeLayer();
            loadFinances(getRoomName());
        })
        .catch((res) => {
            console.log("createFinance catch " + res);
            error.removeClass('invisible');
        });
}

function addCategory() {
    console.log('addCategory');
    const form = $('.js-new-category-form');
    form.removeClass('invisible');

    $('.js-new-category-btn').on('click', () => {

        console.log("button val |" +  $('.js-new-category-name').val());

        const input = $('.js-new-category-name').val();

        if (input != null){
            addCategoryForRoom(getRoomName(), getUserName(), input)
                .then((res) => {
                    console.log("addCategoryForRoom then " + res);

                    const select = $('.js-categories-list');
                    select.append(
                        '<option class="finance-create-form_option js-category-option" value="' + input + '">' + input + '</option>'
                    );
                    form.addClass('invisible');
                })
                .catch((res) => {
                    console.log("addCategoryForRoom catch " + res);
                    $('.js-error-categories').removeClass('invisible');
                    form.addClass('invisible');
                });
        }
        
    });


}

function updateBudget() {
    console.log('updateBudget');

    const budget = $('.js-budget').val();
    const error = $('.js-error-budget');

    updateBudgetApi(getRoomName(), getUserName(), budget)
        .then((res) => {
            console.log("updateBudget then " + JSON.stringify(res));
        })
        .catch((res) => {
            console.log("updateBudget catch " + res);
            error.removeClass('invisible');
        });
}

function showLayer() {
    console.log("showLayer");
    const layer = $('.finance-block-layer');
    layer.removeClass('invisible');

    const categoryBtn = $('.js-add-category');
    categoryBtn.click(addCategory);

    const form = $('.finance-block-layer_form');
    form.submit(createNewFinance);
}

function closeLayer() {
    console.log("close layer");
    const layer = $('.finance-block-layer');
    layer.addClass('invisible');
}

function loadDataForForm(roomName) {
    const budget = $('.js-budget');
    const label = $('.js-budget-label');

    budget.on('input', function() {
      label.text(budget.val());
    });

    // загружаем установленный бюджет

    getBudget(roomName)
        .then((res) => {
            budget.val(res);
            label.text(budget.val())
        })
        .catch((res) => {
            console.log("getBudget catch " + res);
        });
        

    const select = $('.js-categories-list');

    // загружаем массив доступных категорий

    getCategories(getRoomName())
        .then((res) => {
            res.forEach((category) => {
                select.append(
                    '<option class="finance-create-form_option js-category-option" value="' + category.name + '">' + category.name + '</option>'
                )
            });
        })
        .catch((res) => {
            console.log("getCategories catch " + res);
        });
  

    const fieldset = $('.js-type-fieldset');

        // загружаем массив доступных типов

        getTypes()
            .then((res) => {
                res.forEach((type, i) => {
                    fieldset.append(
                        '<label class="finance-create-form_label">' +
                        // У первого должен быть реквайред
                            '<input type="radio" name="type-togler"' + (function r() {if (i == 0) {return " required ";} else {return "";}})() + ' value="' + type.name + '" class="finance-create-form_input js-type-input">' +
                                type.name +
                        '</label>'
                    )
                })
            })
            .catch((res) => {
                console.log("getTypes catch " + res);
            });
}

function renderConsumptionAnalysis(date) {
    getDataConsumptionByTime(getRoomName(), date)
        .then((res) => {
            // console.log("renderConsumptionAnalysis then Строим диаграмму" + JSON.stringify(res));
            showPieChart(res);
        })
        .catch((res) => {
            console.log("renderConsumptionAnalysis catch " + res);
        });

    getDataConsumptionAnalysis(getRoomName())
        .then((res) => {
            console.log("getDataConsumptionAnalysis then " + JSON.stringify(res));
            showBarChart(res);
        })
        .catch((res) => {
            console.log("getDataConsumptionAnalysis catch " + res);
        });
}

// Рендерим карды в расходах
function renderConsumptionSection(date) {
    const container = $('.js-card-list-consumption');

    getDataConsumptionByTime(getRoomName(), date)
        .then((res) => {
            container.empty();

             // {"2020-05":[[120,"Развлечения"]]}
            // {"2021-06":[[120,"Продукты"],[654,"Кафе"],[1250,"Одежда"],[2000,"Косметика"],[1000,"Спорт"]]}
            container.append(
                createMonthHeader(res)
            );

            res[getMonth(res)].forEach(function(element) {
                container.append(
                    createCardFactory(element, "-", "violet")
                );
            });
        })
        .catch((res) => {
            console.log("getDataConsumptionByTime catch " + res);
        });
}

// Рендерим карды в доходах
function renderIncomeSection(date) {
    const container = $('.js-card-list-income');

    getDataIncomeByTime(getRoomName(), date)
        .then((res) => {
            console.log(" getDataIncomeByTime res " + JSON.stringify(res));
            container.empty();

            container.append(
                createMonthHeader(res)
            );

            res[getMonth(res)].forEach(function(element) {
                container.append(
                    createCardFactory(element, "+", "greenTea")
                );
            });
        })
        .catch((res) => {
            console.log("getDataIncomeByTime catch " + res);
        });
}

function loadFinances(roomName) {
    const consumption = $('.js-consumption-select');
    const income = $('.js-income-select');
    const analysis = $('.js-analysis-select');

    getConsumptionByRoomFormated(roomName)
        .then((res) => {
            // console.log("getConsumptionByRoomFormated then " + JSON.stringify(res));
            res.forEach((type) => {
                consumption.append(
                    '<option class="finance-block-consumption_option js-consumption-option" value="' + type + '">' + type + '</option>'
                )

                analysis.append(
                    '<option class="finance-block-analysis_option js-analysis-option" value="' + type + '">' + type + '</option>'
                )
            });

            // consumption.change(() => {console.log("consumption change")});

            renderConsumptionSection(res[0]);

            consumption.on('change', function() {
                renderConsumptionSection(this.value);
                console.log(this.value);
            });

            renderConsumptionAnalysis(res[0]);
            analysis.on('change', function() {
                renderConsumptionAnalysis(this.value);
                console.log(this.value);
            });
        })
        .catch((res) => {
            console.log("getConsumptionByRoomFormated catch " + res);
        });

    getIncomeByRoomFormated(roomName)
        .then((res) => {
            // console.log("getIncomeByRoomFormated then " + JSON.stringify(res));
            res.forEach((type) => {
                income.append(
                    '<option class="finance-block-income_option js-income-option" value="' + type + '">' + type + '</option>'
                )
            });

            renderIncomeSection(res[0]);
            income.on('change', function() {
                renderIncomeSection(this.value);
                console.log(this.value);
            });
        })
        .catch((res) => {
            console.log("getIncomeByRoomFormated catch " + res);
        });
}

$(document).ready(function () {
    const roomName = getRoomName();

    console.log(roomName + " " + getUserName());
    
    loadDataForForm(roomName);
    loadFinances(roomName);

    const createBtn = $('.js-create-finance');
    createBtn.click(showLayer);

    const closeBtn = $('.js-btn-close');
    closeBtn.click(closeLayer);

    const formBud = $('.js-budget-create');
    formBud.click(updateBudget);
});