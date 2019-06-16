var index={
    "return":{
        "fail": 0,
        "success":1,
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
                    console.log("queryUserInfo 返回 : "+JSON.stringify(data));

                    if(data.code == index.return.success){
                        $("#index-content-user").show();
                        console.log(data.message);

                        $("#top-nav-unlogin").hide();
                        $("#top-nav-login").show();

                        $("#head-info-btn").text(data.data.nickName);
                        $("#index-header-img").attr("src","/"+data.data.headerUrl);
                        $("#index-content-user-name").text(data.data.nickName);
                        
                    }
                    else if(data.code == index.return.fail ){
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

$(function () {

    //顶部搜索按钮
    $("#head-search-btn").click(function () {

        var searchVal = $("#head-search-input").val();
        if((searchVal != null)&&(searchVal != '')){
            storage.setItem(storage.constants.currentQueryString,searchVal);
        }
       $(location).attr('href', '/search/search.html');
    })
    //顶部登录按钮
    $("#head-login-btn").click(function () {
        console.log("head-login-btn");
        $(location).attr('href', '../user/login.html');
    })
    //顶部注册按钮
    $("#head-register-btn").click(function () {
        console.log("head-register-btn");
        $(location).attr('href', '../user/register.html');
    })

    $("#head-info-btn").click(function () {
        $(location).attr('href', '../user/info.html');
    })

    $(" #index-content-user-name").click(function () {
        $(location).attr('href', '../user/info.html');
    })



})
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

    //启动的时候

    /**
     *  退出登录操作 ，移除token，并刷新页面
     */
    $("#head-logout-btn").click(function(){
        console.log("logout");
        tokenUtil.removeToken();
        window.location.reload();
    })
})

