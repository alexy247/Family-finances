function registrationUser() {
    const error = $('.js-error');
    const name = $('.js-name').val();
    const password = $('.js-password').val();
    const date = $('.js-date').val();

    console.log(name + " " + password);

    registration(name, password, date)
        .then((res) => {
            console.log("then " + res);
            document.location.href = "/login"; 
        })
        .catch((res) => {
            console.log("then " + res);
            error.removeClass("invisible");
        });
}

$(document).ready(function () {
    let el = $('.registration-block-form');
    el.submit(registrationUser);
});