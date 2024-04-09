function loginUser() {
    const error = $('.js-error');
    const name = $('.js-name').val();
    const password = $('.js-password').val();

    console.log(name + " " + password);

    login(name, password)
        .then((res) => {
            console.log("then " + res);
            setUserName(name);
            document.location.href = "/room"; 
        })
        .catch((res) => {
            console.log("then " + res);
            error.removeClass("invisible");
        });
}

$(document).ready(function () {
    let el = $('.login-block-form');
    el.submit(loginUser);
});