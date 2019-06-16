var userHomeInfo={
    "infoUserId":"",
    "requestUrl":{
        "queryUserInfoUrl":"/user/user/info" ,
    },
    "request":{
        //查询用户信息
        "queryUserInfo":function(){
            $.ajax({
                type: "GET",
                url: userHomeInfo.requestUrl.queryUserInfoUrl,
                success: function(data,status){
                    if(data.code == returnCode.success){
                        console.log("request user info success;");
                    }
                    else {
                        console.log("request user info fail;");
                    }


                },
                error:function(data,status){
                    console.log("queryUserInfo 返回 status"+status);
                }

            });

        },
    },

    //display
    "display":{

        "userInfoDisplay":function (userInfo) {

        }
    },



    //function
    "getRequestUserId":function (search) {

        var patten = "u";


        var start = search.indexOf("?")+1;

        var paraString = search.substring(search.indexOf("?")+1).split("&");

        console.log("paraString =  " + paraString);
        for(var i = 0; i  < paraString.length ;i++){
            var requestData = paraString[i].split("=");
            if(requestData[0] == patten){

                console.log("result = "+requestData[1]) ;
                return requestData[1];
            }

        }


    }


}

$(function () {

    infoUserId = userHomeInfo.getRequestUserId(window.location.search);


})

