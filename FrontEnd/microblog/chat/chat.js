
var urlPrefix = "/chat";
var chat={
    "staticPath":"../static",
    "urlPrefix": "/chat",
    "requestUrl":{
        "userListUrl": urlPrefix + "/user/list",
        "sendMsgUrl": urlPrefix + "/send",
    },
    "request":{

        /**
         *  获取用户列表
         */
        "userListRequest": function () {
            $.get(chat.requestUrl.userListUrl,function (data,status) {

                var jsonData = JSON.stringify(data);
                console.log(jsonData);
                chat.displayUserList(data);
            });
        },
        /**
         *  发送聊天数据
         */
        "sendMsgRequest":function () {

        },
        
    },

    "displayUserList":function (data) {

        var text = "";
        var recData = data.data;
        console.log("recData.length = " + recData.length);
        for(var i = 0; i < recData.length ; i ++){
            text += "<li>"
                + " <img src='" + chat.staticPath + recData[i].headerUrl + "' height='40px' width='40px'>"
                + "<span>"
                + recData[i].nickName
                + "</span>"
                + "<button class='chat-start-btn'>私信</button>"
                + "</li>";
        }

        $("#chat-user-list-ul").append(text);
    },
    /**
     *  打开聊天窗口
     * @param name 聊天对象的名字
     */
    "startChat":function (name) {
        $("#chat-windows").show();
        $("#chat-name").text(name);
        $("#chat-windows-content").html("");
    },

    /**
     * 发送聊天数据
     */
    "sendMsg":function () {
        var content = $("#chat-edit-input").val();
        if(content == ""){return};
        var src = "../static/img/header/0.jpg";
        var sendDisVal = "<div class='chat-send-msg'>"
            + " <img src='" + src + "' height='40px' width='40px' class='send-img'>"
            + "<span class='chat-send-msg-content' style='float: right'>"
            + content
            + " </span>"
            + " </div></br>" ;
        $("#chat-windows-content").append(sendDisVal);
        $("#chat-edit-input").val("");
    },

    /**
     *   接受聊天数据并显示
     * @param content
     */
    "displayMsg":function (content) {

        var src = "../static/img/header/2.jpg";
        var sendDisVal = "<div class='chat-receiver-msg'>"
            + " <img src='" + src + "'  height='40px' width='40px'>"
            + "<span class='chat-rec-msg-content'>"
            + content
            + " </span>"
            + " </div></br>" ;

        $("#chat-windows-content").append(sendDisVal);
    },
    
}

$(function () {
    /**
     *  点击（私信）开始聊天按钮
     */
    $("#chat-user-list-ul").on("click",".chat-start-btn",function () {
        console.log(".chat-start-btn click");

        var name = $(this).parent().find("span").text();

        console.log("the span name = " + name);
        chat.startChat(name);
    })

    /**
     *  发送 按钮按下
     */
    $("#chat-send-btn").click(function () {
        console.log("chat-send-btn click");
        chat.sendMsg();
        chat.displayMsg("撒大萨斯撒大所打火机感到费解凯撒fhjkdaskhjf ");
    })

    /**
     *  获取用户列表
     */
    chat.request.userListRequest();
})