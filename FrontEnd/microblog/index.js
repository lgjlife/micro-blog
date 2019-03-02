var index={
    "return":{
        "QUERY_USER_INFO_SUCCESS":{
            "code": "1082",
            "message": "查询用户信息成功",
        },
        "QUERY_USER_INFO_FAIL":{
            "code": "1083",
            "message": "查询用户信息失败",
        },
    },
    "requestUrl":{
       "queryUserInfoUrl":"/user/user/info",
       "requestLogoutUrl":"/user/logout", 
    },
    "request":{
        
        "queryUserInfo":function(){
            $.ajax({
                type: "GET",
                url: index.requestUrl.queryUserInfoUrl,
                success: function(data,status){
                    console.log("queryUserInfo 返回 status : "+status)
                    if(data.code == index.return.QUERY_USER_INFO_SUCCESS.code ){
                        $("#index-content-user").show();
                        console.log(data.message);

                        $("#top-nav-unlogin").hide();
                        $("#top-nav-login").show();

                        $("#top-nav-login-name").text(data.data.userId + " phone = " + data.data.phoneNum);
                        $("#index-header-img").attr("src","static"+data.data.headerUrl);
                        $("#index-content-user-name").text(data.data.nickName);
                        
                    }
                    else if(data.code == index.return.QUERY_USER_INFO_FAIL.code ){
                        console.log(data.message);

                        $("#top-nav-login").hide();
                        $("#top-nav-unlogin").show();
                        $("#index-content-user").hide();
                        
                    }

                },
                error:function(data,status){
                     console.log("queryUserInfo 返回 status"+status);  
                }
                
            });
            
        },
        "requestLogout":function(){
            $.ajax({
                type: "post",
                url: index.requestUrl.requestLogoutUrl,
                headers: {'Authorization': "i am a token"},
                success: function(data,status){
                    console.log("logout: "+data.message);               

                },
                error:function(data,status){
                     console.log("queryUserInfo 返回 status"+status);  
                }
                
            });
            
        },
    }, 
        
}


$(function(){

    if(token.getToken() != ""){
        console.log("token = " + token.getToken())
        index.request.queryUserInfo();

    }

    
    $("#logout").click(function(){
        console.log("logout");
        index.request.requestLogout();
        
    })

    $("#Header-TOKEN").click(function () {
        $.ajax({
            type: "GET",
            url: "/auth/token/check",
            headers: {'Authorization': "Header-TOKEN  - i am a token"},
            success: function(data,status){
                console.log("/auth/token/check 返回 status : "+status)


            },
            error:function(data,status){
                console.log("/token/check 返回 status : "+status);
            }

        });
    })

    $("#GET-TOKEN").click(function () {
        $.ajax({
            type: "GET",
            url: "/auth/token/check?token=GET-TOKEN-123456789",
            success: function(data,status){
                console.log("/token/check 返回 status : "+status)


            },
            error:function(data,status){
                console.log("/token/check 返回 status : "+status);
            }

        });
    })

    $("#POST-TOKEN").click(function () {
        $.ajax({
            type: "post",
            url: "/auth/token/check",
            headers: {'Authorization': "POST-TOKEN-i am a token"},
            success: function(data,status){
                console.log("/token/check 返回 status : "+status)


            },
            error:function(data,status){
                console.log("/token/check 返回 status : "+status);
            }

        });
    })

        $("#NON-TOKEN").click(function () {
        $.ajax({
            type: "post",
            url: "/auth/token/check",
            success: function(data,status){
                console.log("/token/check 返回 status : "+status)


            },
            /*complete:function(XHR, TS){
                console.log("complete");
                var url = XHR.getResponseHeader("redirectUrl");
                console.log("redirectUrl = " + url);

                var enable = XHR.getResponseHeader("enableRedirect");

                if((enable == "true") && (url != "")){
                    var win = window;
                    while(win != win.top){
                        win = win.top;
                    }
                    win.location.href = url;
                }
                console.log("enableRedirect = " + enable);


             //


            },*/
            error:function(data,status){

                var jsonData = JSON.stringify(data);
                console.log(jsonData);
                console.log("/token/check 返回 status : "+status);
            }

        });
    })

    $("#UNAUTH").click(function () {
        $.ajax({
            type: "post",
            url: "/unauth",
            success: function(data,status){
                console.log("/token/check 返回 status : "+status)

                var win = window;
                while(win != win.top){
                    win = win.top;
                }
                win.location.href = "/user/login.html";

            },

            error:function(data,status){

                var jsonData = JSON.stringify(data);
                console.log(jsonData);
                console.log("/token/check 返回 status : "+status);
            }

        });
    })

    $("#NOTNEEDFILTER").click(function () {
        $.ajax({
            type: "post",
            url: "/chat/notNeedFilter",
            success: function(data,status){
                console.log("/chat/notNeedFilter 返回 status : "+status)

            },



        });
    })

    $("#NEEDFILTER").click(function () {
        $.ajax({
            type: "post",
            url: "/chat/needFilter",
            success: function(data,status){
                console.log("/chat/needFilter 返回 status : "+status)

            },



        });
    })




    /*  $("#headlogin").load(function(){
          console.log("headlogin onload;");
      })*/
})