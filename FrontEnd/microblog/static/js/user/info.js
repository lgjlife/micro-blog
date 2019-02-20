var info={
    
    "requestUrl":{
    },
    "request":{
        "":"",
    },
    
}

 $(function(){
            
    //修改头像
    $("#modify-head").click(function(){
       console.log("#modify-head")
       $("#head-file-input").click();  
    })
     
    $("#head-file-input").change(function(){
        
        console.log("#head-file-input onchange");
        
        var files = $("#head-file-input").prop('files');
        console.log("files length = " + files.length);
        
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
     
    $("#btn").click(function () {
        $.ajaxFileUpload({
            url: '/imgUpload',
            fileElementId:'file',
            dataType:'txt',
            secureuri : false,
            success: function (data){
                
            },
            error:function(data,status,e){
                alert(e);
            }
        });
    });
     
     
})