var search={

    /**
     * 搜索类型
     */
    "searchType":"blog",
    "staticPath":"../static",

    /**
     * 请求路径
     */
    "url":{
        "searchUrl":"/search/query"
    },
    /**
     * 执行ajax请求
     */
    "request":{
        "searchData":{

            "data":{
                //查询类型　blog , user
                "type":"",
                //查询字符串
                "queryString":"",
            },

            "getData":function (type,queryString) {
                search.request.searchData.data.type =  type;
                search.request.searchData.data.queryString = queryString;

                return JSON.stringify(search.request.searchData.data);
            }
        },
        "search":function (type,queryString) {
        
            $.ajax({
                url: search.url.searchUrl,
                type: 'POST',
                // contentType : "application/json;charset=utf-8",
                // dataType: "json",
                data: {
                    //查询类型　blog , user
                    "type":type,
                    //查询字符串
                    "queryString":queryString,
                },
                success:function(data,status){
                    console.log("message = " + data.message);
                    search.display.displayUserList(data.data);
                },
                error(xhr,status,error){
                   // alert("出现异常，请重试！");
                   $(".user-list-ul").html("");
                   $(".search-content-display").html("");
                }

            })
        }

    },
    "return":{

    },
    /**
     * 根据数据进行显示
     */
    "display":{

        "displayUserList":function (users) {

            $(".user-list-ul").html("");
            for(var i = 0; i < users.length ;i++){
                var userTemplate = $("#user-template").html();
                userTemplate = userTemplate.replace('{userId}',users[i].userId);
                userTemplate = userTemplate.replace('{headerImgUrl}',"/"+users[i].headerUrl);
                userTemplate = userTemplate.replace('{userId}',users[i].userId);
                userTemplate = userTemplate.replace('{nickName}',users[i].nickName);
                userTemplate = userTemplate.replace('{concern}',11);
                userTemplate = userTemplate.replace('{fans}',23);
                userTemplate = userTemplate.replace('{blog}',44);
                userTemplate = userTemplate.replace('{desc}',"非凡的世界");
                $(".user-list-ul").append(userTemplate);
            }            
        },
        "displayBlogList":function (data) {
            console.log("displayBlogList....");
            $("#blog-content-ul").text("");
            for(var i in data){


                var imgDisplay = "";
                for(var j = 0; j < data[i].blogImg.length ;j++ ){
                    imgDisplay += "<img src='" + search.staticPath + data[i].blogImg[j] + "' class='blog-content-img' >";
                }


                console.log("imgDisplay = " + imgDisplay + " i = i");

                if(imgDisplay == ""){
                    imgDisplay = "<span></span>";
                }

                var blogText =  "<li class='blog-content-list'>"
                    + " <div class='blog-content'>"
                    //<!-- 用户信息显示 -->
                    + "<div class='blog-content-user-info'>"
                    + "<div  class='blog-content-header-img'>"
                    + "<img src=' "+ search.staticPath + data[i].headerUrl + "' height='120px' width='120px'>"
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
            

        }
        
    },

    /**
     * 测试数据
     */
    "testData":{
        "getUser":function () {

            var user = [{
                "img": "../static/img/header/0.jpg",
                "nickName": "李白",
                "concern": "120",
                "fans": "130",
                "blog": "140",
                "desc": "sdfdfsfad adsfdafadsfdfdafdas"
            },
                {
                    "img": "../static/img/header/0.jpg",
                    "nickName": "张飞",
                    "concern": "120",
                    "fans": "130",
                    "blog": "140",
                    "desc": "sdfdfsfad adsfdafadsfdfdafdas"
                },
            ];
            return user;

        },
        "getBlog":function () {
            var blog =[
                    {
                        "headerUrl":"/img/user/head/u=2855470066,1941636318&fm=26&gp=0.jpg",
                        "nickName":"无怨无悔",
                        "publishTime":"",
                        "content":"手机类外媒GSM ARENA发布了小米9的续航评测:小米9评测结果是91小时，超过了小米8、IPhone、华为P20 Pro、华为Mate 20Pro，跟华为Mate20只差1小时！可以说是续航能力在现在旗舰机里面非常强了！",
                        "blogImg":["/img/user/head/1.jpg",
                                   "/img/user/head/head.jpg",
                            "/img/user/head/head.jpg",
                            "/img/user/head/u=347365577,2691732738&fm=26&gp=0.jpg" ,
                            "/img/user/head/1.jpg",
                            "/img/user/head/head.jpg",
                            "/img/user/head/head.jpg",
                            "/img/user/head/u=347365577,2691732738&fm=26&gp=0.jpg",
                            "/img/user/head/u=347365577,2691732738&fm=26&gp=0.jpg"],
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
                ]  ;

            return blog;

        }
    },
}

/**
 * 进入页面触发
 */
$(function () {

    var currentQueryString = storage.getItem(storage.constants.currentQueryString);

    console.log("currentQueryString = " + currentQueryString);
    $("#search-search-input-block-input").val(currentQueryString);
    $("#search-select-blog-btn").css("color","blue");
    $("#search-select-user-btn").css("color","black");

   // search.display.displayUserList(search.testData.getUser());
    search.display.displayBlogList(search.testData.getBlog());
    var searchData = search.request.searchData.getData("blog",currentQueryString);
    search.request.search(searchData);

})

/**
 * 选择搜索微博或者用户
 */
$(function () {
    /**
     * 搜索博客
     */
    $("#search-select-blog-btn").click(function () {
        console.log("search-select-blog-btn ....");
        $("#search-select-blog-btn").css("color","blue");
        $("#search-select-user-btn").css("color","black");
        search.searchType="blog";
        displayBlockSetect(search.searchType);
        //执行搜索
        var currentQueryString = $("#search-search-input-block-input").val();
        storage.setItem(storage.constants.currentQueryString,currentQueryString);

        search.request.search(search.searchType,currentQueryString);

    })
    /**
     * 搜索用户
     */
    $("#search-select-user-btn").click(function () {
        console.log("search-select-user-btn ....");
        $("#search-select-blog-btn").css("color","black");
        $("#search-select-user-btn").css("color","blue");
        search.searchType="user";

        displayBlockSetect(search.searchType);
        //执行搜索
        var currentQueryString = $("#search-search-input-block-input").val();
        storage.setItem(storage.constants.currentQueryString,currentQueryString);
        search.request.search(search.searchType,currentQueryString);
    })

    $("#search-search-input-block-btn").click(function () {

        //执行搜索
        var currentQueryString = $("#search-search-input-block-input").val();
        storage.setItem(storage.constants.currentQueryString,currentQueryString);
        displayBlockSetect(search.searchType);

        search.request.search(search.searchType,currentQueryString);

    })

    /**
     * 根据类型判断显示的是用户列表还是博客列表
     * @param type
     */
    function displayBlockSetect(type) {

        if("blog" == type){
            $("#search-content-display-blog").show();
            $("#search-content-display-user").hide();
        }
        if("user" == type){
            $("#search-content-display-blog").hide();
            $("#search-content-display-user").show();
        }

    }


})
