/*
document.write("<script language=javascript src='/static/js/jquery/jquery-3.3.1.js'></script>");
document.write("<script language=javascript src='/alter/alter.js'></script>");
*/



var blogDisplayTemplate={

    "likeType":{
        "blogLike":"blogLike",
        "blogUnlike":"blogUnlike",
        "commentLike":"commentLike",
        "commentUnlike":"commentUnlike",
    },
    "url":{
        //提交评论
        "commentSubmitUrl": "/blog/comment/create",
        "queryCommentUrl":  "/blog/comment/list",
        "deleteComment":  "/blog/comment/delete",
        "requestBlogLikeUrl": "/blog/like",
    },
    "request":{
        /**
         * 提交评论
         * @param comment 评论pojo ,json格式
         */
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
                        //评论成功

                    }
                    else if(data.code == returnCode.fail){
                        //评论失败
                    }

                },
                error:function(xhr,status,error){
                    console.log("request error  + " + status);
                    $("#display").html("");
                    $("#display").append("错误提示： " + xhr.status + " " + xhr.statusText);
                }

            })
        },
        /***
         * 删除评论
         * @param commentId 评论ID
         */
        "deleteComment":function (commentId) {
            $.ajax({
                url: blogDisplayTemplate.url.deleteComment,
                type: 'DELETE',
                cache: false,
                data: {
                    "commentId": commentId,
                },
                success:function(data,status){
                    console.log(data.message);
                    if(data.code == returnCode.success){
                        //评论成功

                    }
                    else if(data.code == returnCode.fail){
                        //评论失败
                    }

                },
                error:function(xhr,status,error){
                    console.log("request error  + " + status);
                    $("#display").html("");
                    $("#display").append("错误提示： " + xhr.status + " " + xhr.statusText);
                }

            })
        },

        /**
         * 查询评论
         * @param blogId  微博Id
         * @param page    页码
         * @param pageCount  每页的数目
         * @param commentDisplayDom 评论模块的顶层 div节点
         */
        "queryComment":function (blogId,page,pageCount,commentDisplayDom) {
            $.ajax({
                url: blogDisplayTemplate.url.queryCommentUrl,
                type: 'GET',
                cache: false,
                data: {
                    "blogId":blogId,
                    "page":page,
                    "pageCount":pageCount,
                },
                success:function(data,status){
                    console.log(data.message);
                    if(data.code == returnCode.success){
                        console.log("查询到的评论:"+JSON.stringify(data.data));
                        blogDisplayTemplate.commentDisplay(commentDisplayDom,data.data);
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
        },
        /**
         * 点赞请求
         * @param likeDom
         * @param blogId
         * @param type
         */
        "likeRequest":function(likeDom,id,type){

            console.log("点赞请求参数:"+"type = " + type + "  id = "+id);
            $.ajax({
                type: "post",
                url : blogDisplayTemplate.url.requestBlogLikeUrl,
                data : {
                    "id":id,
                    "type": type,
                },
                success:function(data,status){

                    console.log(JSON.stringify(data));
                    if(data.code == returnCode.success){
                        if((type==blogDisplayTemplate.likeType.blogLike)
                        ||(type==blogDisplayTemplate.likeType.commentLike)){
                            
                            likeDom.children("span").eq(0).html("取消点赞");
                            likeDom.children("span").eq(1).html(data.data);
                        }
                        else if((type==blogDisplayTemplate.likeType.blogUnlike)
                        ||((type==blogDisplayTemplate.likeType.commentUnlike))){

                            likeDom.children("span").eq(0).html("点赞");
                            likeDom.children("span").eq(1).html(data.data);
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

    },



    "getBlogDisplayTemplate":function(){
        return $("#blog-content-list-template").html();
    },
    /**
     * 显示博客内容
     * @param blogInfos
     * @param blogContentUlDom
     * @param htmlDom
     */
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
            blogContentListTemplate = blogContentListTemplate.replace('{likeNumber}',blogInfo.likeNum);

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
     *
     * @param commentDisplayDom 评论模块的顶层节点
     * @param comments 评论对象
     */
    "commentDisplay":function (commentDisplayDom,comments) {

        for(var parentIndex = 0 ; parentIndex < comments.length;parentIndex++){

            console.log("comments.length = " + comments.length);

            var  commentListHtml = $("#blog-comment-list-template").html();
           // console.log("commentListHtml = " + commentListHtml);

            //一级回复
            commentListHtml = commentListHtml.replace("{userHeaderUrl}","/"+comments[parentIndex].headerUrl);
            commentListHtml = commentListHtml.replace(/{userId}/g,comments[parentIndex].userId);
            commentListHtml = commentListHtml.replace(/{nickName}/g,comments[parentIndex].nickName);
            commentListHtml = commentListHtml.replace("{blogId}",comments[parentIndex].blogId);
            commentListHtml = commentListHtml.replace("{cid}",comments[parentIndex].cid);
            commentListHtml = commentListHtml.replace("{pid}",comments[parentIndex].pid);
            commentListHtml = commentListHtml.replace("{content}",comments[parentIndex].content);
            commentListHtml = commentListHtml.replace("{ctime}",comments[parentIndex].ctime);

            commentListHtml = commentListHtml.replace('{likeNumber}',comments[parentIndex].likeNum);
            //检测自己是否已经点赞
            if(comments[parentIndex].likeFlag == true){
                commentListHtml = commentListHtml.replace('{likeWord}',"取消点赞");
            }
            else {
                commentListHtml = commentListHtml.replace('{likeWord}',"点赞");
            }


            var userId = cache.get(cache.key.loginUserInfo).userId;
            console.log("userId = " + userId + "  commentUserId= " + comments[parentIndex].userId);
            if(userId == comments[parentIndex].userId){
                //当前登录的用户和评论是同一个人
                console.log("当前登录的用户和评论是同一个人");
                commentListHtml = commentListHtml.replace('{displayType}',"inline");

            }else
            {
                console.log("当前登录的用户和评论不是同一个人");
                commentListHtml = commentListHtml.replace('{displayType}',"none");
            }



            //二级回复
            var allChildCommentHtml = "";
            if(comments[parentIndex].child.length != 0){


                var child = comments[parentIndex].child;
                for(var childIndex = 0 ; childIndex < child.length;childIndex++){
                    console.log("child.length = " + child.length);
                    var childCommentHtml = $("#blog-comment-list-child-template").html();
                    childCommentHtml = childCommentHtml.replace("{userHeaderUrl}","/"+child[childIndex].headerUrl);
                    childCommentHtml = childCommentHtml.replace(/{userId}/g,child[childIndex].userId);
                    childCommentHtml = childCommentHtml.replace(/{nickName}/g,child[childIndex].nickName);
                    childCommentHtml = childCommentHtml.replace("{blogId}",child[childIndex].blogId);
                    childCommentHtml = childCommentHtml.replace("{cid}",child[childIndex].cid);
                    childCommentHtml = childCommentHtml.replace("{pid}",child[childIndex].pid);
                    childCommentHtml = childCommentHtml.replace("{content}",child[childIndex].content);
                    childCommentHtml = childCommentHtml.replace("{ctime}",child[childIndex].ctime);

                    childCommentHtml = childCommentHtml.replace('{likeNumber}',child[childIndex].likeNum);
                    //检测自己是否已经点赞
                    if(child[childIndex].likeFlag == true){
                        childCommentHtml = childCommentHtml.replace('{likeWord}',"取消点赞");
                    }
                    else {
                        childCommentHtml = childCommentHtml.replace('{likeWord}',"点赞");
                    }


                    var userId = cache.get(cache.key.loginUserInfo).userId;
                    console.log("userId = " + userId + "  commentUserId= " + comments[parentIndex].userId);
                    if(userId == comments[parentIndex].userId){
                        //当前登录的用户和评论是同一个人
                        console.log("当前登录的用户和评论是同一个人");
                        childCommentHtml = childCommentHtml.replace('{displayType}',"inline");

                    }else
                    {
                        console.log("当前登录的用户和评论不是同一个人");
                        childCommentHtml = childCommentHtml.replace('{displayType}',"none");
                    }
                    if(child[childIndex].replyUserNickName != null){
                        //当前登录的用户和评论是同一个人
                        console.log("===============");
                        childCommentHtml = childCommentHtml.replace('{replyDisplayType}',"inline");
                        childCommentHtml = childCommentHtml.replace('{replyUserId}',child[childIndex].replyUserId);
                        childCommentHtml = childCommentHtml.replace('{replyUserNickName}',child[childIndex].replyUserNickName);

                    }else
                    {
                        childCommentHtml = childCommentHtml.replace('{replyDisplayType}',"none");
                    }


                    allChildCommentHtml += childCommentHtml;
                }


                commentListHtml = commentListHtml.replace("{childComment}",allChildCommentHtml);
            }else {
                commentListHtml = commentListHtml.replace("{childComment}","");
            }

            commentDisplayDom.find(".comment-display-ul").append(commentListHtml);
        }
        blogDisplayTemplate.addEvent.addFirstLevelReplyAndLike();
        blogDisplayTemplate.addEvent.addSecondLevelReplyAndLike();
        blogDisplayTemplate.addEvent.addFirstLevelDelete();
        blogDisplayTemplate.addEvent.addSecondLevelDelete();
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
         * 添加点击评论事件
         */
        "addCommentEvent":function () {
            $(".comment-select").on("click",function () {
                console.log("评论");

                var commentDisplayDom = $(this).parent().parent().parent().find(".comment-display");

                //commentDisplayDom.parent().attr("blockId")
                var blogId = commentDisplayDom.parent().attr("blogId");
                commentDisplayDom.attr("blogId",blogId);

                console.log("display = " + commentDisplayDom.css("display"));
                //关闭->打开
                if(commentDisplayDom.css("display") == "none"){
                    commentDisplayDom.show();
                    console.log("display = " + commentDisplayDom.css("display"));
                    var user = cache.get(cache.key.loginUserInfo);
                    console.log(JSON.stringify(user));

                    var commentHtml = $("#blog-comment-template").html();

                    commentHtml = commentHtml.replace('{curUserHeaderUrl}',"/"+user.headerUrl);

                    //请求评论内容
                    blogDisplayTemplate.request.queryComment(blogId,0,10,commentDisplayDom);


                  //  console.log("commentHtml = " +  commentHtml);

                    //清空内容
                    commentDisplayDom.html("");
                    commentDisplayDom.append(commentHtml);
                    blogDisplayTemplate.addEvent.addFirstLevelCommentSubmitEvent();

                    //
                }
                //打开->关闭
               else if(commentDisplayDom.css("display") == "block"){
                    commentDisplayDom.hide();
                    console.log("display = " + commentDisplayDom.css("display"));
                }

            })
        },


        /**
         * 微博添加点赞事件
         */
        "addLikeEvent":function () {
            $(".like-select").on("click",function () {
                console.log("微博点赞");

                var likeDom = $(this);
                var blogId = $(this).parent().parent().attr("blogId");
                var type="";

                if(likeDom.children("span").eq(0).html() == "点赞"){
                    console.log("当前为点赞");
                    type = blogDisplayTemplate.likeType.blogLike;
                }
                else {
                    console.log("当前为取消点赞");
                    type = blogDisplayTemplate.likeType.blogUnlike;
                }

                console.log("blogId = " + blogId + ";  type =  " + type);
                blogDisplayTemplate.request.likeRequest(likeDom,blogId,type);
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
                comment.pid = 0;
                comment.content = $(this).parent().parent().find(".comment-input").val();

                console.log("提交评论内容 =  " + JSON.stringify(comment));
                blogDisplayTemplate.request.commentSubmit(JSON.stringify(comment));

            })
        },
        //一级回复/点赞事件
        "addFirstLevelReplyAndLike":function () {

            $(".first-reply").on("click",function () {
                console.log("一级评论");

                var firstLevelCommentListDom = $(this).parent();
                var firstLevelCommentInputDivDom = firstLevelCommentListDom.find(".first-comment-input-div");
                var firstLevelCommentInputTextareaDom = firstLevelCommentInputDivDom.find("textarea");

                //展开关闭一级评论的输入框
                if(firstLevelCommentInputDivDom.css("display") == "none"){
                    console.log("展示对话框");
                    firstLevelCommentInputDivDom.show();
                }
                else {
                    console.log("关闭对话框");
                    firstLevelCommentInputDivDom.hide();
                }

                $(".firstLevelCommentSubmit").off("click");
                $(".firstLevelCommentSubmit").on("click",function () {

                    var firstLevelCommentListDom = $(this).parent().parent();
                    var firstLevelCommentInputDivDom = firstLevelCommentListDom.find(".first-comment-input-div");
                    var firstLevelCommentInputTextareaDom = firstLevelCommentInputDivDom.find("textarea");

                    var comment ={"id":"","blogId":"","userId":"","pid":"","replyId":"","publishTime":"","content":""};

                    comment.blogId = firstLevelCommentListDom.attr("blogId");
                    comment.userId = cache.get(cache.key.loginUserInfo).userId;
                    comment.replyId = firstLevelCommentListDom.attr("cid");
                    comment.pid = firstLevelCommentListDom.attr("cid");
                    comment.content = firstLevelCommentInputTextareaDom.val();

                    console.log("提交评论内容 =  " + JSON.stringify(comment));
                    blogDisplayTemplate.request.commentSubmit(JSON.stringify(comment));



                })


            })
            //一级评论点赞
            $(".first-like").on("click",function () {
                console.log("一级点赞");
                var likeDom = $(this);
                var cid = $(this).parent().attr("cid");
                var type;

                if(likeDom.children("span").eq(0).html() == "点赞"){
                    type = blogDisplayTemplate.likeType.commentLike;
                }
                else {
                    type = blogDisplayTemplate.likeType.commentUnlike;
                }

                console.log("cid = " + cid + ";  type =  " + type);
                blogDisplayTemplate.request.likeRequest(likeDom,cid,type);

            })
        },
        //添加二级评论的回复和喜欢事件
        "addSecondLevelReplyAndLike":function () {

            $(".second-reply").on("click",function () {
                console.log("二级评论");

                var secondLevelCommentListDom = $(this).parent();
                var secondLevelCommentInputDivDom = secondLevelCommentListDom.find(".second-comment-input-div");
                var secondLevelCommentInputTextareaDom = secondLevelCommentInputDivDom.find("textarea");

                //展开关闭一级评论的输入框
                if(secondLevelCommentInputDivDom.css("display") == "none"){
                    console.log("展示对话框");
                    secondLevelCommentInputDivDom.show();
                }
                else {
                    console.log("关闭对话框");
                    secondLevelCommentInputDivDom.hide();
                }

                //提交评论按钮绑定事件
                $(".secondLevelCommentSubmit").off("click");
                $(".secondLevelCommentSubmit").on("click",function () {

                    var secondLevelCommentListDom = $(this).parent().parent();
                    var secondLevelCommentInputDivDom = secondLevelCommentListDom.find(".second-comment-input-div");
                    var secondLevelCommentInputTextareaDom = secondLevelCommentInputDivDom.find("textarea");

                    var comment ={"id":"","blogId":"","userId":"","pid":"","replyId":"","publishTime":"","content":""};

                    comment.blogId = secondLevelCommentListDom.attr("blogId");
                    comment.userId = cache.get(cache.key.loginUserInfo).userId;
                    comment.replyId = secondLevelCommentListDom.attr("cid");
                    comment.pid = secondLevelCommentListDom.attr("pid");
                    comment.content = secondLevelCommentInputTextareaDom.val();

                    console.log("提交评论内容 =  " + JSON.stringify(comment));
                    blogDisplayTemplate.request.commentSubmit(JSON.stringify(comment));

                })


            })

            //二级评论点赞
            $(".second-like").on("click",function () {
                console.log("二级点赞");
                var likeDom = $(this);
                var blogId = $(this).parent().attr("cid");
                var type;

                if(likeDom.children("span").eq(0).html() == "点赞"){
                    type = blogDisplayTemplate.likeType.commentLike;
                }
                else {
                    type = blogDisplayTemplate.likeType.commentUnlike;
                }

                console.log("cid = " + blogId + ";  type =  " + type);
                blogDisplayTemplate.request.likeRequest(likeDom,blogId,type);
            })
        },
        //一级评论删除
        "addFirstLevelDelete":function () {

            $(".first-delete").on("click",function () {
                console.log("一级删除");

                var commentId = $(this).parent().attr("cid");
                console.log("删除评论："+commentId);
                blogDisplayTemplate.request.deleteComment(commentId);
            })
        },
        //二级评论删除
        "addSecondLevelDelete":function () {

            $(".second-delete").on("click",function () {
                console.log("二级删除");
                var commentId = $(this).parent().attr("cid");
                console.log("删除评论："+commentId);
                blogDisplayTemplate.request.deleteComment(commentId);
            })
        },




    },

    
}

