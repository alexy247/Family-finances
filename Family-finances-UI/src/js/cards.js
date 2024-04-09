function createCardFactory(item, symbol, classCss) {
    return '<div class="finance-item">' +
                '<image src="' + item[2] + '" class="finance-item_img"/>' +
                '<span class="finance-item_text">' + item[1] + '</span>' +
                '<span class="' + classCss + '" finance-item_amount">' + symbol + item[0] + '</span>' +
            '</div>';
}

function createMonthHeader(data) {
    return '<div class="finance-block-consumption-header">' + getMonth(data) + "</div>";
}

function createRoomCard(item) {
    return '<div class="room-card">' +
                    '<div class="room-card_name">' + item.name + '</div>' +
                    '<div class="room-card_date">' + item.date + '</div>' +
                    '<image class="room-card_delete" src="https://image.flaticon.com/icons/png/512/17/17047.png"/>' +
            '</div>';
}