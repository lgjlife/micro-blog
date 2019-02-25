var edit = {
    "requestUrl":{
        "submitBlogUrl": "/blog/submit"
    },

    "request":{

        /**
         * 上传文件请求
         */
        "submitBlog":function(formData){
            $.ajax({
                url: edit.requestUrl.submitBlogUrl,
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

                },
                success:function(data,status){
                    console.log("request error  + " + status);


                }

            })
        },


    },

    /*
      给## 添加连接
     */
    "addTextHref":function(text){
        var resultText = text;

        var start= -1;
        var end = -1;

        for(var i = 0 ; i < text.length;i++){

            if((text.charAt(i) ==  "#")&&(start ==  -1)){
                start = i;
            }
            else if((text.charAt(i) ==  "#")&&(start !=  -1) && (end ==  -1)){
                end = i;
                break;
            }

        }
       // console.log("start = " + start + "   end = " + end);
        if((start != -1)&&(end != -1)&&(end > start)){

            var text1="";
            if(start != 0){
                 text1 = text.substring(0,start-1);
            }

            var text2 = text.substring(start,end+1);
            var text3 = text.substring(end+2,text.length-1);
            //console.log("text2 = " + text2);
            text2 = "<a href='javascript:void(0)' style='color:red'>" + text2 + "</a>";

            resultText = text1 +  text2 + text3;

        }


        return resultText;

    },

    "IFrameResize":function(){
        //alert(this.document.body.scrollHeight); //弹出当前页面的高度
        var obj = parent.document.getElementById("blog-edit"); //取得父页面IFrame对象
        //alert(obj.height); //弹出父页面中IFrame中设置的高度
        obj.height = document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
    },

}

$(function(){

    //发布按钮按下
    $("#publish-btn").click(function(){

        console.log("publish-btn click");

        //1. blog-img  博客图片
        //2. blog-text  博客内容
        //3. blog-type  博客类型 公开 ， 朋友圈 ，私有 ，组别


        var formData = new FormData();
        var files = $("#blog-edit-img-input")[0].files;
        for(var i = 0 ; i < files.length ; i ++){
            formData.append('blog-img',files[i]);
        }

        // 处理字符串，给##添加样式
        var text = $("#blog-edit-input").val();
        text = edit.addTextHref(text);
        $("#display").append(text);
        console.log("text = " + text);
        formData.append('blog-text',text);

        var type = $("#blog-type-select").val();
        console.log("博客类型 type = " + type);

        formData.append('blog-type',type);

        edit.request.submitBlog(formData);

    })

    $("#blog-edit-img-select").click(function(){
        console.log("#blog-edit-img-select click")
        $("#blog-edit-img-input").click();

    })
    //图片选择之后
    $("#blog-edit-img-input").change(function(){

        console.log("#blog-edit-img-input onchange");

        var files = $("#blog-edit-img-input").prop('files');

        console.log("files = " + files);

        console.log("上传的文件个数 = " + files.length);
        console.log("files.val = " + files[0].type);
        console.log("files.val = " + files[0].name);

        if(!files) return;

        $("#img-preview").show();
        for(var i = 0; i < files.length ; i++){

            file = files[i];

            if(file.size > 1024 * 1024 * 2) {
                alert('图片大小不能超过 2MB!');
                return false;
            }

            var URL = window.URL || window.webkitURL;

            var imgURL = URL.createObjectURL(file);

           // $("#img-change").attr("src",imgURL);

            console.log("imgURL = " + imgURL);
            var imgli = "<li>"
              //  + "<button class='delete-img-btn'>删除</button>"
                + "<img src='" + imgURL +   "' width='40px' height='40px'   class='blog-img-list'>"
                + "<span>" + file.name + "</span>"
                + "</li>";

            $("#blog-edit-img-list-ul").append(imgli);
        }

        console.log("delete文件个数 = " + files.length);


        //打开预览窗口
        $("#head-file-preview").show();

        edit.IFrameResize();
    })


    edit.IFrameResize();
})