


var search={

    /**
     * 搜索类型
     */

    "searchType":{
        "blog":"blog",
        "user":"user",
    },
    "curSearchType":"blog",
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
        
            console.log("type = " + type + "  queryString =  " + queryString);
            $.ajax({
                url: search.url.searchUrl,
                type: 'GET',
                // contentType : "application/json;charset=utf-8",
                // dataType: "json",
                data: {
                    //查询类型　blog , user
                    "type":type,
                    //查询字符串
                    "queryString":queryString,
                },
                success:function(data,status){
                    console.log("搜索结果 = " + JSON.stringify(data));
                    if(type == "user"){
                        search.display.displayUserList(data.data);
                    }
                    else if(type == "blog"){

                       
                        search.display.displayBlogList(data.data);
                    }

                   
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
                userTemplate = userTemplate.replace('{headerImgUrl}',"/"+users[i].headerUrl);
                userTemplate = userTemplate.replace(/{userId}/g,users[i].userId);
                userTemplate = userTemplate.replace('{nickName}',users[i].nickName);
                userTemplate = userTemplate.replace('{concern}',11);
                userTemplate = userTemplate.replace('{fans}',23);
                userTemplate = userTemplate.replace('{blog}',44);
                userTemplate = userTemplate.replace('{desc}',"非凡的世界");
                $(".user-list-ul").append(userTemplate);
            }            
        },
            /**
         * 追加显示博客 "+ blog.staticPath + data.headerUrl + "
            **/
        
        "displayBlogList":function (blogInfos) {

            blogDisplayTemplate.displayBlog(blogInfos,$("#search-content-display-blog"),$("html"));       
        },
        
    },

}

/**
 * 进入页面触发
 */
$(function () {


    var currentQueryString = cache.get(cache.key.searchString);

    console.log("currentQueryString = " + currentQueryString);
    $("#search-search-input-block-input").val(currentQueryString);
    $("#search-select-blog-btn").css("color","blue");
    $("#search-select-user-btn").css("color","black");
    search.request.search(search.searchType.blog,currentQueryString);

})



/**
 * 选择搜索微博或者用户
 */
$(function () {

    function searchSubmit(handleDom){
        var searchType =handleDom.attr("searchType");
        //说明不是 微博或者用户按钮按下，而是搜索按钮或者输入框回车事件
        if(searchType == null){
            searchType = search.curSearchType;
        }
        else {
        //微博或者用户按钮按下
            $(".searchStart").css("color","black");
            handleDom.css("color","blue");
            search.curSearchType = searchType;
        }
        console.log("searchType = " + searchType);

        displayBlockSetect(searchType);

        var currentQueryString = $("#search-search-input-block-input").val();
        cache.set(cache.key.searchString,currentQueryString,1000000);
        cache.set(cache.key.searchType,searchType,1000000);
        //执行搜索
        search.request.search(searchType,currentQueryString);
    }

    /***
     * 搜索操作
     */
    $(".searchStart").on("click",function () {
        searchSubmit($(this));
    })

    /**
     * 输入框回车键事件
     */
    $("#search-search-input-block-input").keyup(function(event){
        console.log("event.keyCode" + event.keyCode)
        searchSubmit($(this));
    });



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
