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
//
    //console.log("token = " + tokenUtil.getToken());
    /**
     *  如果存在token 则 获取用户信息
     */
    if(((tokenUtil.getToken() != null)) && (tokenUtil.getToken() != "") ){
        console.log("获取用户信息")
        index.request.queryUserInfo();
    }
    else{
        console.log("token not found");
    }

    /**
     *  退出登录操作 ，移除token，并刷新页面
     */
    $("#logout").click(function(){
        console.log("logout");
        tokenUtil.removeToken();
        window.location.reload();
    })
})