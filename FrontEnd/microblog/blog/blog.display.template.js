/*
document.write("<script language=javascript src='/static/js/jquery/jquery-3.3.1.js'></script>");
document.write("<script language=javascript src='/alter/alter.js'></script>");
*/



var blogDisplayTemplate={

    "url":{
        "commentSubmitUrl": "/comment/create"
    },
    "request":{
        "commentSubmit":function (comment) {
            $.ajax({
                url: blogDisplayTemplate.url.commentSubmitUrl,
                type: 'POST',
                cache: false,
                data: comment,
                contentType : "application/json;charset=utf-8",
                dataType: "json",
                success:function(data,status){
                    console.log(data.message);
                    if(data.code == returnCode.success){

                    }
                    else if(data.code == returnCode.fail){

                    }

                },
                error:function(xhr,status,error){
                    console.log("request error  + " + status);
                    $("#display").html("");
                    $("#display").append("错误提示： " + xhr.status + " " + xhr.statusText);
                }

            })
        }

    },



    "getBlogDisplayTemplate":function(){
        return $("#blog-content-list-template").html();
    },
    "displayBlog":function(blogInfos,blogContentUlDom,htmlDom){


        console.log("blogDisplayTemplate==================================");
        if(blogInfos == null){
            console.log("blog.display.js-displayBlog:" + "blogInfos is null");
            return;
        }
        blogContentUlDom.html("");

        for(let blogInfo of blogInfos){
            //var  blogContentListTemplate = $("#blog-content-list-template").html();

            console.log("blogInfo = "+JSON.stringify(blogInfo));

            console.log("html = " + $("html"));
            console.log("htmlDom = " + htmlDom);

            var ddd = htmlDom.find("#blog-content-list-template");
            var  blogContentListTemplate = htmlDom.find("#blog-content-list-template").html()

            console.log("publishTime"+blogInfo.publishTime);
         //   console.log("blogContentListTemplate = " +blogContentListTemplate);

           /* blogContentListTemplate = blogContentListTemplate.replace('{blogId}',blogInfo.blogId);
            blogContentListTemplate = blogContentListTemplate.replace('{userId}',blogInfo.userId);
            blogContentListTemplate = blogContentListTemplate.replace('{headerUrl}',"/"+blogInfo.headerUrl);
            console.log( blogContentListTemplate);*/



            blogContentListTemplate = blogContentListTemplate.replace(/{blogId}/g,blogInfo.blogId);
            blogContentListTemplate = blogContentListTemplate.replace(/{userId}/g,blogInfo.userId);
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
          //  return;
            var blogImgListTemplate = htmlDom.find("#blog-img-list-template").html();
            var imgs = "";
            for(let img of blogInfo.blogImg){
                imgs +=  blogImgListTemplate.replace('{blogImg}',"/"+img);
            }
            blogContentListTemplate = blogContentListTemplate.replace('{imgDisplay}',imgs)

            blogContentUlDom.append(blogContentListTemplate);




        }
        //添加事件
        //添加评论事件
        blogDisplayTemplate.addEvent.addCollectEvent();
        blogDisplayTemplate.addEvent.addRepostEvent();
        blogDisplayTemplate.addEvent.addCommentEvent();
        blogDisplayTemplate.addEvent.addLikeEvent();

       // blog.IFrameResize();
    },

    /**
     * 添加事件
     */
    "addEvent":{
        /**
         * 添加收藏事件
         */
          "addCollectEvent":function () {
              $(".collect-select").on("click",function () {
                  console.log("收藏");
              })
          },
        /**
         * 添加转发事件
         */
        "addRepostEvent":function () {
            $(".repost-select").on("click",function () {
                console.log("转发");
            })
        },
        /**
         * 添加评论事件
         */
        "addCommentEvent":function () {
            $(".comment-select").on("click",function () {
                console.log("评论");

                var commentDisplayDom = $(this).parent().parent().parent().find(".comment-display");

                //commentDisplayDom.parent().attr("blockId")
                commentDisplayDom.attr("blogId",commentDisplayDom.parent().attr("blogId"));

                console.log("display = " + commentDisplayDom.css("display"));
                //关闭->打开
                if(commentDisplayDom.css("display") == "none"){
                    commentDisplayDom.show();
                    console.log("display = " + commentDisplayDom.css("display"));
                    var user = cache.get(cache.key.loginUserInfo);
                    console.log(JSON.stringify(user));

                    var commentHtml = $("#blog-comment-template").html();

                    commentHtml = commentHtml.replace('{curUserHeaderUrl}',"/"+user.headerUrl);


                  //  console.log("commentHtml = " +  commentHtml);

                    //清空内容
                    commentDisplayDom.html("");
                    commentDisplayDom.append(commentHtml);
                    blogDisplayTemplate.addEvent.addFirstLevelCommentSubmitEvent();
                }
                //打开->关闭
               else if(commentDisplayDom.css("display") == "block"){
                    commentDisplayDom.hide();
                    console.log("display = " + commentDisplayDom.css("display"));
                }

            })
        },
        /**
         * 添加点赞事件
         */
        "addLikeEvent":function () {
            $(".like-select").on("click",function () {
                console.log("点赞");
            })
        },

        /**
         * 一级评论提交按钮
         */
        "addFirstLevelCommentSubmitEvent":function () {
            $(".first-level-comment-submit-btn").on("click",function () {

                console.log("html =  " + $(this).parent().parent().parent().html());
                var blogId = $(this).parent().parent().parent().attr("blogId");
                console.log("提交评论blogId =  " + blogId);


                var comment ={"id":"","blogId":"","userId":"","pid":"","replyId":"","publishTime":"","content":""};
                comment.blogId = blogId;
                comment.userId = cache.get(cache.key.loginUserInfo).userId;
                comment.replyId = 0;
                comment.content = $(this).parent().parent().find(".comment-input").val();

                console.log("提交评论内容 =  " + JSON.stringify(comment));
                blogDisplayTemplate.request.commentSubmit(JSON.stringify(comment));

            })
        },


    },

    
}

