var info={
    
    "requestUrl":{
        "fileUploadUrl":"/user/user/info/head-img",
        "queryUserInfoUrl":"/user/user/info" ,
        "requestSettingUrl":"/user/user/info/setting" ,
    },
    "request":{
        /**
        * 上传文件请求
        */
        "fileUpload":function(formData){
            $.ajax({
                url: "/user/info/img",
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
                    console.log("queryUserInfo 返回 status : "+status)
                    var jsonData = JSON.stringify(data);
                    console.log(jsonData);
                    
                    
                    
                    $("#info-nick-name-input").val(data.data.nickName);
                    $("#info-phone-input").val(data.data.phoneNum);
                    $("#info-email-input").val(data.data.email);

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
            sendData.nickName = $("#info-nick-name-input").val(); 
            sendData.phoneNum = $("#info-phone-input").val();
            sendData.email = $("#info-email-input").val(); 

            var jsonData = JSON.stringify(sendData);
            console.log(jsonData);      

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
        $("#user-info-page-btn").parent().addClass("item-select")

    })
    $("#modify-info-page-btn").click(function () {
        console.log("user-info-page-btn click")
        $(".info-select-menu-item").parent().removeClass("item-select");
        $("#modify-info-page-btn").parent().addClass("item-select")

    })
    $("#concern-page-btn").click(function () {
        console.log("user-info-page-btn click")
        $(".info-select-menu-item").parent().removeClass("item-select");
        $("#concern-page-btn").parent().addClass("item-select")

    })
    $("#fan-page-btn").click(function () {
        console.log("user-info-page-btn click")
        $(".info-select-menu-item").parent().removeClass("item-select");
        $("#fan-page-btn").parent().addClass("item-select")

    })
    $("#collect-page-btn").click(function () {
        console.log("user-info-page-btn click")
        $(".info-select-menu-item").parent().removeClass("item-select");
        $("#collect-page-btn").parent().addClass("item-select")

    })
})