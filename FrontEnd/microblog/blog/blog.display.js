var blog={
    "staticPath":"../static",
    "paging":{
        "start": 0,
        "limit": 10,  
    },
    "type":{
        "PRIVATE": "PRIVATE",
        "ALL": "ALL",
    },
    "requestUrl": {
       "requestBlogUrl": "/blog/list",
    },
    
    "request":{
        "requestBlog":function(type){
            var sendData ={"start":"","limit":"","type":""};
            sendData.start = blog.paging.start; 
            sendData.limit = blog.paging.limit;
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
                  //  console.log(jsonData);
                   
                /*for(var dat in data.data){
                     console.log(data.data[dat]);
                }*/
                   
                   blog.displayBlog(data.data);
               },
                error:function(data,status){
                   console.log(status);
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
    "displayBlog":function(data){
      //  for(var i = 0; i < data.length ;i++ ){
            for(var i in data){
                
                
            var imgDisplay = "";
            /* for(var j = 0; j < data[i].blogImg.length ;j++ ){                 
                 imgDisplay += "<img src='" + blog.staticPath + data[i].blogImg[j] + "' class='blog-content-img' >";
             }*/
            
   
            console.log("imgDisplay = " + imgDisplay + " i = i");
            
            if(imgDisplay == ""){
                imgDisplay = "<span></span>";
            }
            
            var blogText =  "<li class='blog-content-list'>"
                        + " <div class='blog-content'>"
                            //<!-- 用户信息显示 -->
                            + "<div class='blog-content-user-info'>"
                               + "<div  class='blog-content-header-img'>" 
                                    + "<img src=' "+ blog.staticPath + data[i].headerUrl + "' height='120px' width='120px'>"
                                + "</div>"
                                + "<span  style='float: right'><a href='javascript:void(0);' onclick='$('.content-blog-menu').show();'>操作</a></span>"

                               + " <div>"
                                    + "<a href='#'>" +data[i].nickName + "</a>"
                                   + " <span>2月15日 23:23</span>"
                                + "</div>"
                                
                               + " <div id='content-blog-menu-show'>"
                                    
                                + "</div>"
                               + " <div style='display: none;' class='content-blog-menu'>"
                                   + " <a href='javascript:void(0);'>帮上头条</a>"
                                   + " <a href='javascript:void(0);'>屏蔽这条微博</a>"
                                   + "<a href='javascript:void(0);'>屏蔽<span>xxxx</span></a>"
                                   + "<a href='javascript:void(0);'>取消关注<span>xxxx</span></a>"
                                   + " <a href='javascript:void(0);'>投诉</a>"
                                   + " <button onclick='$('.content-blog-menu').hide();'>关闭</button>"
                               + " </div>"
                           + " </div>"
                            
                         ///  <!-- 微博内容显示 -->
                          + "  <div blog-content-word-div>"
                              + "  <span  class='blog-content-word'>"
                                + data[i].content
                               + ' </span>'
                           + " </div>"
                           //  <!-- 微博图片显示 -->                                  
                           + " <div class='blog-content-img-block'  >"
                                     + imgDisplay     
                           + " </div>"
                          //  <!-- 微博底部信息显示 --> 
                           + " <div class='blog-'>"
                             + "   <a href='javascript:void(0)'>收藏(<span>" + data[i].collectNum + "</span>)</a> " 
                              + "  <a href='javascript:void(0)'>转发(<span>" + data[i].repostNum + "</span>)</a>"  
                             + "   <a href='javascript:void(0)'>评论(<span>" + data[i].commentNum + "</span>)</a> " 
                             + "   <a href='javascript:void(0)'>点赞(<span>" + data[i].likeNum + "</span>)</a>  "
                          + "  </div>"
                       + " </div> "
                  + "  </li> "; 
            
            $("#blog-content-ul").append(blogText);
            
        }
        blog.IFrameResize();
    }
}

$(function(){
    
    blog.request.requestBlog(blog.type.ALL);
    
    console.log("test blog length = " ,blog.test.length);
   // blog.displayBlog(blog.test);
    
    $("#show-more").click(function(){
        blog.paging.start +=  blog.paging.limit;
        
        blog.request.requestBlog(blog.type.ALL);
    })
    
})
