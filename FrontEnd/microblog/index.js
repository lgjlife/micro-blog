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
       "queryUserInfoUrl":"/user/info", 
       "requestLogoutUrl":"/user/logout", 
    },
    "request":{
        
        "queryUserInfo":function(){
            $.ajax({
                type: "GET",
                url: index.requestUrl.queryUserInfoUrl,
                headers: {'Authorization': "i am a token"},
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
    console.log("queryUserInfo....")
    index.request.queryUserInfo();
    
    $("#logout").click(function(){
        console.log("logout");
        index.request.requestLogout();
        
    })


    
    
  /*  $("#headlogin").load(function(){
        console.log("headlogin onload;");
    })*/
})