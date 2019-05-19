
$(function () {
    $("#btn").click(function(){
        console.log("sss");
        $(location).attr('href', '../user/login.html');
    })

    $("#head-login-btn").click(function () {
        console.log("head-login-btn");
        $(location).attr('href', '../user/login.html');
    })

    $("#head-register-btn").click(function () {
        console.log("head-register-btn");
        $(location).attr('href', '../user/register.html');
    })

    $("#head-info-btn").click(function () {
        $(location).attr('href', '../user/info.html');
    })

    $("#head-logout-btn").click(function () {

    })

})
