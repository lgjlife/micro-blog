var info={
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
        "headerImgUploadUrl":"/user/user/info/header-img",
        "queryUserInfoUrl":"/user/user/info" ,
        "requestSettingUrl":"/user/user/info/setting" ,
    },
    "request":{
        /**
        * 上传文件请求
        */
        "fileUpload":function(formData){
            $.ajax({
                url: info.requestUrl.headerImgUploadUrl,
                type: 'POST',
                cache: false,
                data: formData,
                processData: false,
                contentType: false,
                success:function(data,status){
                   console.log(data.message);
                    console.log(data.data);

                    var jsonData = JSON.stringify(data);
                    console.log(jsonData);
                    $("#info-header-img").attr("src","../static"+data.data);
                    info.request.queryUserInfo();

               }
            })  
        },
        
        /**
        * 查询用户信息
        */
        "queryUserInfo":function(){
            $.ajax({
                type: "GET",
                url: info.requestUrl.queryUserInfoUrl,
                headers: {'Authorization': "i am a token"},
                success: function(data,status){
                    info.display.infoDisplay(data);

                },
                error:function(data,status){
                     console.log("queryUserInfo 返回 status"+status);  
                }
                
            });
            
        },
         /**
        *setting用户信息
        */
        "requestSetting":function(){
            
              
            var sendData ={"nickName":"","phoneNum":"","email":"",};
            sendData.nickName = $("#user-info-modify-nickName").val();
            sendData.phoneNum = $("#user-info-modify-phone").val();
            sendData.email = $("#user-info-modify-email").val();

            var jsonData = JSON.stringify(sendData);
            console.log("设置的用户信息:"+jsonData);

            $.ajax({
                type: "POST",
                url: info.requestUrl.requestSettingUrl,
                headers: {'Authorization': "i am a token"},
                contentType : "application/json;charset=utf-8",      
                dataType: "json",
                data: jsonData,
                success: function(data,status){
                    console.log("queryUserInfo 返回 status : "+status)


                },
                error:function(data,status){
                     console.log("queryUserInfo 返回 status : "+status);  
                }
                
            });
            
        }
        
    
        
    },
    display:{
        "infoDisplay":function (data) {
            if(data.code == info.return.QUERY_USER_INFO_SUCCESS.code ){

                ///////////////////////////////
                /*页面头*/
                $("#index-content-user").show();
                console.log(data.message);
                console.log(data.data);
                $("#top-nav-unlogin").hide();
                $("#top-nav-login").show();
                $("#head-info-btn").text(data.data.nickName);

                //////////////////////////////
                /*用户信息*/
                $("#user-info-header").attr("src","../static"+data.data.headerUrl);
                $("#user-info-nickName").text(data.data.nickName);
                $("#user-info-phone").text(data.data.phoneNum);
                $("#user-info-email").text(data.data.email);
                var registerDate = new Date(data.data.registerTime);
                var displayDate = registerDate.getFullYear()+"-"+registerDate.getMonth()
                    +"-"+registerDate.getDay()+" "
                    + registerDate.getHours() + ":" + registerDate.getMinutes();
                $("#user-info-register").text(displayDate);

                //////////////////////////////
                /*修改用户信息*/

                $("#user-info-modify-header").attr("src","../static"+data.data.headerUrl);
                $("#user-info-modify-nickName").val(data.data.nickName);
                $("#user-info-modify-phone").val(data.data.phoneNum);
                $("#user-info-modify-email").val(data.data.email);



            }
            else if(data.code == info.return.QUERY_USER_INFO_FAIL.code ){
                console.log(data.message);

                $("#top-nav-login").hide();
                $("#top-nav-unlogin").show();
                $("#index-content-user").hide();

            }
        }
    },
    
    /**
    * 判断是否图片
    */
    "isImgFile":function(feid){
             var img = document.getElementById(feid);
             var reg  = /.(jpg|png|gif|bmp)$/;
             if(reg.test(img.value)){
                 console.log("是图片格式")
                 return true;
             }
             else {
                 console.log("不是图片格式")
                 return false;
             }
            
    },
    
}

 $(function(){
            
    //修改头像点击
    $("#modify-head").click(function(){
       console.log("#modify-head")
       $("#head-file").click();  
      
    })
    //图片选择之后
    $("#head-file").change(function(){
        
        console.log("#head-file-input onchange");
        
        var files = $("#head-file").prop('files');
        console.log("上传的文件个数 = " + files.length);
        console.log("files.val = " + files[0].type);
        console.log("files.val = " + files[0].name);

        
         if (files && files.length > 0) {

            file = files[0];
            
            if(file.size > 1024 * 1024 * 2) {
                alert('图片大小不能超过 2MB!');
                return false;
            }

            var URL = window.URL || window.webkitURL;

            var imgURL = URL.createObjectURL(file);
             console.log("imgURL = " + imgURL);
            $("#img-change").attr("src",imgURL);
         } 
        //打开预览窗口
         $("#head-file-preview").show();
        
    })
     
    /**
    * 提交上传头像请求
    */
    $("#head-file-save-btn").click(function () {
        
        var formData = new FormData(); 
        var files = $("#head-file")[0].files;
        for(var i = 0 ; i < files.length ; i ++){
            formData.append('file',files[i]);
        }
        
        info.request.fileUpload(formData);
        
    }) 
     
   /**
    * 保存设置
    */
    $("#info-setting-btn").click(function () {
        console.log("保存设置");
        info.request.requestSetting();        
    })   
     
     
    info.request.queryUserInfo();
     
})

/*
左侧菜单选项
 */
$(function () {

    $("#user-info-page-btn").click(function () {
        console.log("user-info-page-btn click")
        $(".info-select-menu-item").parent().removeClass("item-select");
        $("#user-info-page-btn").parent().addClass("item-select");
        $(".user-info").hide();
        $("#user-info-page").show();

    })
    $("#modify-info-page-btn").click(function () {
        console.log("user-info-page-btn click")
        $(".info-select-menu-item").parent().removeClass("item-select");
        $("#modify-info-page-btn").parent().addClass("item-select")

        $(".user-info").hide();
        $("#user-info-modify-page").show();


    })
    $("#concern-page-btn").click(function () {
        console.log("user-info-page-btn click")
        $(".info-select-menu-item").parent().removeClass("item-select");
        $("#concern-page-btn").parent().addClass("item-select")

        $(".user-info").hide();
        $("#user-followee-page").show();

    })
    $("#fan-page-btn").click(function () {
        console.log("user-info-page-btn click")
        $(".info-select-menu-item").parent().removeClass("item-select");
        $("#fan-page-btn").parent().addClass("item-select")

        $(".user-info").hide();
        $("#user-follower-page").show();

    })
    $("#collect-page-btn").click(function () {
        console.log("user-info-page-btn click")
        $(".info-select-menu-item").parent().removeClass("item-select");
        $("#collect-page-btn").parent().addClass("item-select")

        $(".user-info").hide();
        $("#user-collect-page").show();

    })
})
