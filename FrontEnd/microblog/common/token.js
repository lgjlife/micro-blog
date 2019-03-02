var token={
    "setToken": function (token) {
        localStorage.setItem("token",token);
    },
    "getToken": function () {
        return localStorage.getItem("token");
    },
}
$(function () {

    $(document).ajaxSend(function (event, xhr, settings) {
        console.log("ajaxSend")
        var tokenVal = token.getToken();
        if(tokenVal != ""){
            console.log("ajaxSend - token = "+ tokenVal);
            xhr.setRequestHeader("Authorization",tokenVal);
        }
    })
})