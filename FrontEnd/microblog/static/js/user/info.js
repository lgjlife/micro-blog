var info={
    
    "requestUrl":{
        "fileUploadUrl":"/user/info/head-img",
        "queryUserInfoUrl":"/user/info" ,
        "requestSettingUrl":"/user/info/setting" ,
    },
    "request":{
        /**
        * 上传文件请求
        */
        "fileUpload":function(){
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
        * 查询用户信息
        */
        "requestSetting":function(){
            $.ajax({
                type: "POST",
                url: info.requestUrl.queryUserInfoUrl,
                headers: {'Authorization': "i am a token"},
                success: function(data,status){
                    console.log("queryUserInfo 返回 status : "+status)
                    $("#info-nick-name-input").val(data.data.nickName);
                    $("#info-phone-input").val(data.data.phoneNum);
                    $("#info-email-input").val(data.data.email);

                },
                error:function(data,status){
                     console.log("queryUserInfo 返回 status"+status);  
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
        
         if (files && files.length > 0) {

            file = files[0];
            
            if(file.size > 1024 * 1024 * 2) {
                alert('图片大小不能超过 2MB!');
                return false;
            }

            var URL = window.URL || window.webkitURL;

            var imgURL = URL.createObjectURL(file);

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
        
        info.request.fileUpload();
        
    }) 
     
   /**
    * 保存设置
    */
    $("#head-file-save-btn").click(function () {
        
        var formData = new FormData(); 
        var files = $("#head-file")[0].files;
        for(var i = 0 ; i < files.length ; i ++){
            formData.append('file',files[i]);
        }
        
        info.request.fileUpload();
        
    })   
     
     
    info.request.queryUserInfo();
     
})