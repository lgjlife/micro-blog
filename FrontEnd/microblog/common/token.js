var token={
    "setToken": function (token) {
        localStorage.setItem("token",token);
    },
    "getToken": function () {
        return localStorage.getItem("token");
    },
}
$(function () {

    $(document).ajaxStart(function (event, xhr, settings) {
        console.log("ajaxStart")

    })

    $(document).ajaxSend(function (event, xhr, settings) {
        console.log("ajaxSend")
        var tokenVal = token.getToken();
        if(tokenVal != ""){
            console.log("ajaxSend - token = "+ tokenVal);
            xhr.setRequestHeader("Authorization",tokenVal);
        }
    })
})