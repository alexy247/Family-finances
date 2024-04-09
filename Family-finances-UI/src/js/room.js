// Неизвестно как юзера хранить, Нужно на беке
const NIKITA = getUserName();

console.log(NIKITA);
function onDelete(name) {
    const error = $('.js-error-delete');

    console.log("onDelete " + name);

    deleteRoom(name)
        .then(() => {
            document.location.href = "/room"; 
        })
        .catch((res) => {
            console.log("res " + res);
            error.removeClass("invisible");
        });
}

function closeLayer() {
    const layer = $('.room-block-layer');
    layer.addClass('invisible');
}

function onEnter(name){
    console.log("onEnter");

    const layer = $('.js-room-password-layer');
    layer.removeClass('invisible');

    const input = $('.js-room-password');
    const error = $('.js-error-password');

    const button = $('.js-room-password-submit');

    button.click(() => {
        console.log(name + "  " + input.val())
    
        enterRoom(name, input.val(), NIKITA)
            .then(() => {
                setRoomName(name);
                document.location.href = "/finance"; 
            })
            .catch((res) => {
                console.log("catch " + JSON.stringify(res));
                error.removeClass('invisible');
            })
    });
}

function onCardClick(event) {
    console.log("onCardClick " + $(event.target));
    
    const name = $(event.target).parent('.room-card').find('.room-card_name').text();
    console.log("name " + $(event.target).hasClass('.room-card_name'));
    console.log("name " + name);
    if ($(event.target).hasClass('room-card_delete')) {
        return onDelete(name);
    } else {
        return onEnter(name);
    }
}

function createNewRoom() {
    const error = $('.js-error-layer');
    const name = $('.js-name').val();
    const password = $('.js-password').val();

    createRoom(name, password, NIKITA)
        .then((res) => {
            console.log("then result " + res);
            setRoomName(name);
            document.location.href = "/finance"; 
        })
        .catch((res) => {
            console.log("res " + res);
            error.removeClass("invisible");
        });
}

function showLayer() {
    console.log("showLayer");
    const layer = $('.room-block-layer');
    layer.removeClass('invisible');
    const form = $('.room-block-layer_form');
    form.submit(createNewRoom);
}

$(document).ready(function () {
    const container = $('.room-block-list');
    const error = $('.js-error');

    const createBtn = $('.room-block_create-btn');
    createBtn.click(showLayer);

    const closeBtn = $('.js-btn-close');
    closeBtn.click(closeLayer);

    getUserRooms(NIKITA)
        .then((res) => {
            res.forEach(element => {
                container.append(
                    // "<div class='room-block-list_item room-card'><div class='room-card_name'>" +
                    //     element.name + "</div><div class='room-card_time'>" +
                    //     element.date + "</div><div class='room-card_delete'>x</div></div>"

                        createRoomCard(element)
                );
            });
            container.find('.room-card').on('click', onCardClick);
        })
        .catch((res) => {
            console.log("res " + res);
            error.removeClass("invisible");
        });
});