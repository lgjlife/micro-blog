var header={

    "handler":{
        "userInfoHandler":{
            "excutor":{
                "success":function (data) {

                    console.log("header-userInfoHandler:"+JSON.stringify(data));
                    if(data.code == returnCode.success){

                        header.display.displayUserInfo(data.data);

                    }
                    else if(data.code == returnCode.fail ){
                        console.log(data.message);

                        $("#top-nav-login").hide();
                        $("#top-nav-unlogin").show();
                        $("#index-content-user").hide();

                    }


                },
                "error": function () {

                },

            },
        },
    },
    
    "display":{
        "displayUserInfo":function (userInfo) {
            $("#index-content-user").show();

            $("#top-nav-unlogin").hide();
            $("#top-nav-login").show();

            $("#head-info-btn").text(userInfo.nickName);

        }  
    },
}

//初始
$(function () {

    // cache.remove(cache.key.loginUserInfo);
    // 用户信息
    var loginUserInfo = cache.get(cache.key.loginUserInfo);
    if( loginUserInfo != null){
       //缓存中有用户信息
        console.log("header-缓存中有用户信息");
        header.display.displayUserInfo(loginUserInfo);
        

    }else {
        //缓存中没有用户信息，向后端请求
        console.log("header-缓存中没有用户信息，向后端请求");
        info.request.queryUserInfo(header.handler.userInfoHandler);
    }

    //搜索框

    var searchString = cache.get(cache.key.searchString);
    if( searchString != null){
        //缓存中有用户信息
        $("#head-search-input").val(searchString);


    }else {
        //缓存中没有用户信息，向后端请求
        $("#head-search-input").val("sds");
    }

})

//按键操作
$(function () {

    //退出登录操作
    $("#head-logout-btn").click(function () {

    })

    //搜索操作
    $("#head-search-btn").click(function () {
        var searchString = $("#head-search-input").val();
        cache.set(cache.key.searchString,searchString,1000*20);

        window.parent.location.replace("/search/search.html");
    })

})