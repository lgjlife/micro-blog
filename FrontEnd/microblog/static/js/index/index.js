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
       "queryUserInfoUrl":"/user/info" 
    },
    "request":{
        
        "queryUserInfo":function(){
            $.get(index.requestUrl.queryUserInfoUrl,function(data,status){
                if(data.code == index.return.QUERY_USER_INFO_SUCCESS.code ){
                    console.log(data.message);
                    
                    $("#top-nav-unlogin").hide();
                    $("#top-nav-login").show();
                    
                    $("#top-nav-login-name").text(data.data.userId + " phone = " + data.data.phoneNum);
                }
                else if(data.code == index.return.QUERY_USER_INFO_FAIL.code ){
                    console.log(data.message);
                    
                    $("#top-nav-login").hide();
                    $("#top-nav-unlogin").show();
                }
                
            });  
        }
    },
    
    
        
}


$(function(){
    console.log("queryUserInfo....")
    index.request.queryUserInfo();
})