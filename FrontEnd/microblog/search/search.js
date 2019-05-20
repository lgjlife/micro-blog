var search={

    /**
     * 搜索类型
     */
    "searchType":"blog",

    /**
     * 请求路径
     */
    "url":{
        "searchUrl":"/search/search/query"
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
        "search":function (jsonData) {
            console.log("搜索:" + jsonData);
            $.ajax({
                url: search.url.searchUrl,
                type: 'POST',
                contentType : "application/json;charset=utf-8",
                dataType: "json",
                data: jsonData,
                success:function(data,status){
                    console.log("message = " + data.message);
                    console.log("message = " + data.message);
                    search.display.displayUserList(data.data);
                },
                error(xhr,status,error){
                    alert("出现异常，请重试！");
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

        "displayUserList":function (user) {
            console.log("displayUserList...");
            $("#search-content-display-user ul").text("");


            for(var i = 0; i < user.length ;i++){

                var text = "<li class=‘user-list’>"
                    + "<div>"
                    + "    <div class='user-list-header-block'>"
                    + "        <img src='" + "../static/img/header/0.jpg" + "' class='user-header-img'>"
                    + "    </div>"
                    + "    <div>"
                    + "      <div>"
                    + "         <a href='javascript:void(0)' class='user-list-info-nickName'>" + user[i].nickName + "</a>"
                    + "         <button type='button' class='user-list-concern-btn'>关注</button>"
                    + "      </div>"
                    + "      <div>"
                    + "         <span >关注:</span><span class='user-concern-count'>" + user[i].concern + "</span>"
                    + "      </div>"
                    +  "     <div>"
                    + "         <span>粉丝:</span><span class='user-fans-count'>" + user[i].fans + "</span>"
                    + "      </div>"
                    + "      <div>"
                    + "         <span>微博:</span><span class='user-blog-count'>" + user[i].blog + "</span>"
                    + "      </div>"
                    + "    </div>"

                    + "    <div>"
                    + "        <span>简介:</span><span class='user-desc'>" + user[i].desc + "</span>"
                    + "    </div>"
                    + " </div>"
                    + " </li>"
                    + " <hr>";

                $("#search-content-display-user ul").append(text);
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

    search.display.displayUserList(search.testData.getUser());

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
        //执行搜索
        var currentQueryString = $("#search-search-input-block-input").val();
        storage.setItem(storage.constants.currentQueryString,currentQueryString);
        var searchData = search.request.searchData.getData(search.searchType,currentQueryString);
        search.request.search(searchData);

    })
    /**
     * 搜索用户
     */
    $("#search-select-user-btn").click(function () {
        console.log("search-select-user-btn ....");
        $("#search-select-blog-btn").css("color","black");
        $("#search-select-user-btn").css("color","blue");
        search.searchType="user";
        //执行搜索
        var currentQueryString = $("#search-search-input-block-input").val();
        storage.setItem(storage.constants.currentQueryString,currentQueryString);
        var searchData = search.request.searchData.getData(search.searchType,currentQueryString);
        search.request.search(searchData);

    })

    $("#search-search-input-block-btn").click(function () {

        //执行搜索
        var currentQueryString = $("#search-search-input-block-input").val();
        storage.setItem(storage.constants.currentQueryString,currentQueryString);
        var searchData = search.request.searchData.getData(search.searchType,currentQueryString);
        search.request.search(searchData);

    })


})

/*
$("body").load(function () {
    console.log("load.... ");
})*/
