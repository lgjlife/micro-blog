function g_alert(type,cont,url){
    //定义弹出层
    var html = '<div id="g_box" style="z-index:9999; position:fixed"><div id="g_title">提示</div><div id="g_cont"><img width="50px" height="50px" style="vertical-align: middle;"><span id="g_msg">'+cont+'</span></div><div id="g_buttom"><div id="g_button"><a class="butt" id="ok">确定</a> <a class="butt" id="false">取消</a></div></div></div>';
    var html2 ='<div id="g_box2"style="z-index:9999; position:fixed" class="g_cont"><img width="25px" height="25px" style="vertical-align: middle;"><span id="g_msg" style="margin-left: 10px;font-size: 16px;height: 25px;line-height: 25px;vertical-align: middle;: block">'+cont+'</span></div>';
    //定义CSS
    var css = "<style id='g_css'>#g_title{height:60px;background:#fff;border-radius:10px 10px 0 0; border-bottom:1px solid #eef0f1;line-height:60px;padding-left:25px;font-size:18px; font-weight:700; color:#535e66}";
    css += "#g_cont{padding:30px 0; text-align:center;}";
    css += "#g_msg{font-size:16px;margin-left:20px;}";
    css += "#g_buttom{height:60px;border-top:1px solid #eef0f1; border-radius:0 0 10px 10px;line-height:60px;}";
    css += "#g_button{width:200px; height:100%; margin-right:10px; float:right;}";
    css += ".butt{display:block;margin-top:12px;cursor:pointer; float:left;width:95px;height:35px;line-height:35px;text-align:center;color:#fff;border-radius:5px;}"
    css += "#ok{background:#0095d9; float:right;}";
    css += "#false{background:#546a79; float:left;}";
    css +=".g_cont{padding:10px 20px;;border-radius:10px;;text-align:center;border: 1px solid #bbb;background:#fff;}";
    css += "#g_box{width:550px;border:1px solid #bbb;border-radius:10px;background:#fff;}</style>";
    //插入CSS
    $('head').append(css);
    //正角提示
    if(type == 'success'){
        $('body').append(html);
        //$('#g_cont img').attr('src',bloghost+'zb_users/plugin/YtUser/js/images/ok.png');
        $('#false').hide();
    }
    //错误提示
    if(type == 'error'){
        $('body').append(html);
       // $('#g_cont img').attr('src',bloghost+'zb_users/plugin/YtUser/js/images/fail.png');
        $('#false').hide();
    }
    //警告提示
    if(type == 'warn'){
        $('body').append(html);
       // $('#g_cont img').attr('src',bloghost+'zb_users/plugin/YtUser/js/images/warn.png');
        $('#false').hide();
    }
    //询问提示
    if(type == 'confirm'){
        $('body').append(html);
       // $('#g_cont img').attr('src',bloghost+'zb_users/plugin/YtUser/js/images/confirm.png');
    }
    //正确提示，没有事件，3秒后消失
    if(type == 'skipok'){
        $('body').append(html2);
        $('.g_cont img').attr('src',bloghost+'zb_users/plugin/YtUser/js/images/sok.png');
        setTimeout(removediv, 3000);
    }
    //错误提示，没有事件，3秒后消失
    if(type == 'skipwarn'){
        $('body').append(html2);
      //  $('.g_cont img').attr('src',bloghost+'zb_users/plugin/YtUser/js/images/swarn.png');
        setTimeout(removediv, 3000);
    }
    //弹出层消失函数
    function removediv(){
        $('#g_all').remove();
        $('#g_box').remove();
        $('#g_css').remove();
        $('#g_box2').remove();
        return false;
    }
    //确定按钮事件
    $('#ok').click(function(){
        removediv();
        if(url){
            window.location = url;
        }
        return true;
    });
    //取消按钮事件
    $('#false').click(function(){
        removediv();
        if(type != 'confirm'){
            if(url){
                window.location = url;
            }}
        return false;
    });
    //定位弹出层在窗口的位置
    var _widht = document.documentElement.clientWidth;
    var _height = document.documentElement.clientHeight;
    var boxWidth = $("#g_box").width();
    var boxHeight = $("#g_box").height();
    var boxWidth2 = $("#g_box2").width();
    var boxHeight2 = $("#g_box2").height();
    $("#g_box").css({ top: (_height - boxHeight) / 4 + "px", left: (_widht - boxWidth) / 2 + "px" });
    $("#g_box2").css({ top: (_height - boxHeight2) / 2 + "px", left: (_widht - boxWidth2) / 2 + "px" });
}