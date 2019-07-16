/*
document.write("<script language=javascript src='/static/js/jquery/jquery-3.3.1.js'></script>");
document.write("<script language=javascript src='/alter/alter.js'></script>");
*/



var blogDisplayTemplate={
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



            blogContentListTemplate = blogContentListTemplate.replace('{blogId}',blogInfo.blogId);
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
       // blog.IFrameResize();
    },
}