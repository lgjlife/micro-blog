
$(function(){


    var url = 'ws://' + window.location.host + '/websocket' + '/2001';
    console.log("websocket url = " + url);

    console.log(""  + window)
    if ("WebSocket" in window){

         socket = new WebSocket(url);

        socket.onmessage = function (event) {
            var datas = event.data.split(",");
            console.log("服务器消息====" + datas);
            $("#chat-rec").val(datas);
        };
        socket.onclose = function()
        {
            // 关闭 websocket
            console.log("Web Socket 已连关闭：" + socket.readyState)
        };
        socket.onopen = function()
        {
            console.log("Web Socket 已连接上：" + socket.readyState)
        };



    }
    else{
        alert("浏览器不支持WebSocket!");
    }



})
//将发送人接收人的id和要发生的消息发送出去
function send() {
    console.log("send status = " + socket.readyState)
    console.log($("#chat-input").val())
    var data = $("#myid").val() + "," + $("#friendid").val() + "," + $("#chat-input").val()

    var dat = {"code":101,"msg":"dsfvadfvgsadsffds","name":"sfa "};
    socket.send(JSON.stringify(dat));
}

//登录事件
function login() {
    console.log("send status = " + socket.readyState)
    var data = $("#myid").val();
    // socket.send(data);
}