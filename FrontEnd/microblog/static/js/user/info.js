var info={
    
    "requestUrl":{
        "fileUploadUrl":"/user/info/head-img",
    },
    "request":{
        "fileUpload":function(){
             $.ajaxFileUpload({
                //<!--   需要上传的文件域的ID，即input type="file"的ID。 -->
                url: info.requestUrl.fileUploadUrl,
                fileElementId: "head-file-input",
              //  dataType:'txt',
                //是否启用安全提交，默认为false。
                secureuri : false,
                type: 'post',   //当要提交自定义参数时，这个参数要设置成post
                success: function (data){
                    console.log(data.message);
                },
                error:function(data,status,e){
                    alert(e);
                }
            });
        },
    
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
    },
    
}

 $(function(){
            
    //修改头像点击
    $("#modify-head").click(function(){
       console.log("#modify-head")
       $("#head-file").click();  
       $("#head-file-preview").show();
    })
    //
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
        
        
    })
     
    $("#head-file-save-btn").click(function () {
        
        var formData = new FormData(); 
        var files = $("#head-file")[0].files;
        for(var i = 0 ; i < files.length ; i ++){
            formData.append('file',files[i]);
        }
        $.ajax({
            url: "/user/info/img",
            type: 'POST',
            cache: false,
            data: formData,
            processData: false,
            contentType: false,
            success:function(data,status){
               console.log(data.message);
           }
        })
        
    })     
     
})