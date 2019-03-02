var tokenUtil={
    "tokenKey":"microblogtoken",
    "setToken": function (token) {
        localStorage.setItem(tokenUtil.tokenKey,token);
    },
    "getToken": function () {
        return localStorage.getItem(tokenUtil.tokenKey);
    },

    "removeToken": function () {
        return localStorage.removeItem(tokenUtil.tokenKey);
    },
}
$(function () {

    $(document).ajaxSend(function (event, xhr, settings) {
        console.log("ajaxSend")
        var tokenVal = tokenUtil.getToken();
        if(tokenVal != ""){
            console.log("ajaxSend - token = "+ tokenVal);
            xhr.setRequestHeader("Authorization",tokenVal);
        }
    })
})