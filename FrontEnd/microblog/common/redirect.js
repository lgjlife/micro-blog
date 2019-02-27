function  redirectHandle(url) {

    var win = window;
    while(win != win.top){
        win = win.top;
    }
    win.location.href = url;
}