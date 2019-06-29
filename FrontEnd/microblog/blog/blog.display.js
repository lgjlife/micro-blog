document.write("<script language=javascript src='/common/return.js'></script>");
document.write("<script language=javascript src='/alter/alter.js'></script>");

var blog={
    "staticPath":"../static",
    "paging":{
        "start": 1,
        "limit": 10,  
    },
    "type":{
        "PRIVATE": "PRIVATE",
        "ALL": "ALL",
        "PUBLIC": "PUBLIC",
    },
    "requestUrl": {
        "requestBlogUrl": "/blog/list",
        "requestBlogLikeUrl": "/blog/like",
        "requestBlogCollectUrl": "/blog/collect",
        "requestBlogRepostUrl": "/blog/repost",
        "requestBlogCommentUrl": "/blog/comment",
    },
    
    "request":{
        "requestBlog":function(type){
            var sendData ={"page":"","count":"","type":""};
            sendData.page = blog.paging.start;
            sendData.count = blog.paging.limit;
            sendData.type = type; 
                    
            var jsonData = JSON.stringify(sendData);
            console.log(jsonData);
            $.ajax({
               type: "post",
               url : blog.requestUrl.requestBlogUrl,
               contentType : "application/json;charset=utf-8",
               //数据格式是json串,传进一个person
               data : jsonData,
               dataType: "json",
               success:function(data,status){
                   console.log(data.message);
                   
                   var jsonData = JSON.stringify(data);
                   console.log(jsonData);
                   blog.displayBlog(data.data);
               },
                error:function(data,status){
                   console.log(status);
               },
                
            
            });  
        },
        /**
         * 点赞请求
         * @param likeDom
         * @param blogId
         * @param type
         */
        "likeRequest":function(likeDom,blogId,type){

            console.log("点赞请求参数:"+"type = " + type + "  blogId = "+blogId);
            $.ajax({
                type: "post",
                url : blog.requestUrl.requestBlogLikeUrl,
                data : {
                    "blogId":blogId,
                    "type": type,
                },
                success:function(data,status){

                    console.log(JSON.stringify(data));
                    if(data.code == returnCode.success){
                        if(type=="like"){
                            likeDom.find(".sp1").html("取消点赞");
                            likeDom.find(".sp2").html(data.data);
                        }
                        else if(type=="unlike"){
                            likeDom.find(".sp1").html("点赞")
                            likeDom.find(".sp2").html(data.data);
                        }
                    }
                    else if(data.code == returnCode.fail){
                        alert(data.message);
                    }
                },
                error:function(data,status){
                    alert(data.message);
                },


            });
        },
        /**
         * 收藏请求
         * @param collectDom
         * @param blogId
         * @param type
         */
        "collectRequest":function(collectDom,blogId){

            $.ajax({
                type: "post",
                url : blog.requestUrl.requestBlogCollectUrl,
                data : {
                    "blogId":blogId,
                },
                success:function(data,status){

                    console.log(JSON.stringify(data));
                    if(data.code == returnCode.success){
                        collectDom.find(".sp2").html(data.data);
                    }
                    else if(data.code == returnCode.fail){
                        alert(data.message);
                    }
                },
                error:function(data,status){
                    alert(data.message);
                },


            });
        },
        /**
         * 转发请求
         * @param repostDom
         * @param blogId
         * @param type
         */
        "repostRequest":function(repostDom,blogId,content){

            $.ajax({
                type: "post",
                url : blog.requestUrl.requestBlogRepostUrl,
                data : {
                    "blogId":blogId,
                    "content":content,
                },
                success:function(data,status){

                    console.log(JSON.stringify(data));
                    if(data.code == returnCode.success){
                        repostRequest.find(".sp2").html(data.data);
                    }
                    else if(data.code == returnCode.fail){
                        alert(data.message);
                    }
                },
                error:function(data,status){
                    alert(data.message);
                },


            });
        },

        "commentRequest":function(commentDom,blogId,content){

            $.ajax({
                type: "post",
                url : blog.requestUrl.requestBlogCommentUrl,
                data : {
                    "blogId":blogId,
                    "content":content,
                },
                success:function(data,status){

                    console.log(JSON.stringify(data));
                    if(data.code == returnCode.success){
                        commentDom.find(".sp2").html(data.data);
                    }
                    else if(data.code == returnCode.fail){
                        alert(data.message);
                    }
                },
                error:function(data,status){
                    alert(data.message);
                },


            });
        },

        
    },
    "IFrameResize":function(){
       //alert(this.document.body.scrollHeight); //弹出当前页面的高度
            var obj = parent.document.getElementById("blog-display"); //取得父页面IFrame对象
            //alert(obj.height); //弹出父页面中IFrame中设置的高度
            obj.height = document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度 
    },
    "test":[
        {
            "headerUrl":"/img/user/head/u=2855470066,1941636318&fm=26&gp=0.jpg",
            "nickName":"无怨无悔",
            "publishTime":"",
            "content":"手机类外媒GSM ARENA发布了小米9的续航评测:小米9评测结果是91小时，超过了小米8、IPhone、华为P20 Pro、华为Mate 20Pro，跟华为Mate20只差1小时！可以说是续航能力在现在旗舰机里面非常强了！",
            "blogImg":["/img/user/head/1.jpg","/img/user/head/head.jpg","/img/user/head/head.jpg","/img/user/head/u=347365577,2691732738&fm=26&gp=0.jpg"],
            "collectNum":"100",
            "repostNum":"120",
            "comment":"130",
            "like":"140",            
        },
        {
            "headerUrl":"/img/user/head/u=2157476683,2592973119&fm=26&gp=0.jpg",
            "nickName":"爱情走远了",
            "publishTime":"",
            "content":"手机类外媒GSM ARENA发布了小米9的续航评测:小米9评测结果是91小时，超过了小米8、IPhone、华为P20 Pro、华为Mate 20Pro，跟华为Mate20只差1小时！可以说是续航能力在现在旗舰机里面非常强了！",
            "blogImg":["/img/user/head/u=2157476683,2592973119&fm=26&gp=0.jpg","/img/user/head/head.jpg","/img/user/head/head.jpg","/img/user/head/u=347365577,2691732738&fm=26&gp=0.jpg"],
            "collectNum":"190",
            "repostNum":"1220",
            "comment":"10",
            "like":"40", 
        },
    ],
    /**
    * 追加显示博客 "+ blog.staticPath + data.headerUrl + "
    **/
    "displayBlog":function(blogInfos){

        if(blogInfos == null){
            console.log("blog.display.js-displayBlog:" + "blogInfos is null");
            return;
        }


        for(let blogInfo of blogInfos){
            var  blogContentListTemplate = $("#blog-content-list-template").html();

            console.log("publishTime"+blogInfo.publishTime);

            blogContentListTemplate = blogContentListTemplate.replace('{blogId}',blogInfo.blogId);
            blogContentListTemplate = blogContentListTemplate.replace('{headerUrl}',"/"+blogInfo.headerUrl);
            blogContentListTemplate = blogContentListTemplate.replace('{nickName}',blogInfo.nickName);
            blogContentListTemplate = blogContentListTemplate.replace('{publishTime}',blogInfo.publishTime);
            blogContentListTemplate = blogContentListTemplate.replace('{content}',blogInfo.content);
            blogContentListTemplate = blogContentListTemplate.replace('{collectNum}',blogInfo.collectNum);
            blogContentListTemplate = blogContentListTemplate.replace('{repostNum}',blogInfo.repostNum);
            blogContentListTemplate = blogContentListTemplate.replace('{commentNum}',blogInfo.commentNum);
            blogContentListTemplate = blogContentListTemplate.replace('{likeNum}',blogInfo.likeNum);

            //检测自己是否已经点赞
            if(blogInfo.likeFlag == true){
                blogContentListTemplate = blogContentListTemplate.replace('{likeWord}',"取消点赞");
            }
            else {
                blogContentListTemplate = blogContentListTemplate.replace('{likeWord}',"点赞");
            }

            var blogImgListTemplate =$("#blog-img-list-template").html();
            var imgs = "";
            for(let img of blogInfo.blogImg){
                imgs +=  blogImgListTemplate.replace('{blogImg}',"/"+img);
            }
            blogContentListTemplate = blogContentListTemplate.replace('{imgDisplay}',imgs)

            $('#blog-content-ul').append(blogContentListTemplate);
        }
        blog.IFrameResize();
    }
}

$(function(){
    
    blog.request.requestBlog(blog.type.PUBLIC);
    
    console.log("test blog length = " ,blog.test.length);
   // blog.displayBlog(blog.test);
    
    $("#show-more").click(function(){
        blog.paging.start +=  blog.paging.limit;
        
        blog.request.requestBlog(blog.type.ALL);
    })

    //图片绑定，单机放大
    $(document).on('click',".blog-content-img",function () {
        console.log("图片按下:"+$(this).attr("src"));
        var originSrc = $(this).attr("src");
        var maxImgTemplate =$("#maxImg-template").html();
        maxImgTemplate = maxImgTemplate.replace('{src}',originSrc);
        $("body").append(maxImgTemplate);

        var  top = $(this).offset().top;
        var  bottom = $(this).offset().bottom;
        console.log("top = " + top + " bottom =  "+ bottom);

        $(".maxImg").css("top",top-150);


    })

    //双击图片消失
    $(document).on('dblclick',".maxImg",function () {
        $(".maxImg").hide();
    })

    //收藏
    $(document).on('click',".collect-select",function () {

        var blogId = $(this).parent().parent().find(".blog-id").html();
        blog.request.collectRequest($(this),Number(blogId));
    })
    //转发
    $(document).on('click',".repost-select",function () {
        var blogId = $(this).parent().parent().find(".blog-id").html();
        blog.request.repostRequest($(this),Number(blogId),content);
    })
    //评论
    $(document).on('click',".comment-select",function () {

        var blogId = $(this).parent().parent().find(".blog-id").html();
        blog.request.commentRequest($(this),Number(blogId),content);
    })
    //点赞
    $(document).on('click',".like-select",function () {


        var type = "";

        console.log($(this).find(".sp1").html());
        if($(this).find(".sp1").html()  == "点赞"){
            console.log("点赞");
            type = "like";
        }
        else if($(this).find(".sp1").html()  == "取消点赞"){
            console.log("取消点赞");
            type = "unlike";
        }

        console.log($(this).parent());
        var blogId = $(this).parent().parent().find(".blog-id").html();


        blog.request.likeRequest($(this),Number(blogId),type);
    })





    
})
