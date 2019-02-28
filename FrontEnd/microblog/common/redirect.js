function  redirectHandle(xhr) {

    var url = xhr.getResponseHeader("redirectUrl");
    console.log("redirectUrl = " + url);

    var enable = xhr.getResponseHeader("enableRedirect");

    if((enable == "true") && (url != "")){
        var win = window;
        while(win != win.top){
            win = win.top;
        }
        win.location.href = url;
    }

}

$(function () {

    $(document).ajaxComplete(function (event, xhr, settings) {
        console.log("ajaxComplete  adffdafadsaf")
        redirectHandle(xhr);
    })
})