var search={

    "url":{

    },
    "request":{

    },
    "return":{

    },
    "display":{

        "displayUserList":function (user) {
            console.log("displayUserList...");
            $("#search-content-display-user ul").text("");
            for(var i = 0; i < user.length ;i++){

                var text = "<li class=‘user-list’>"
                    + "<div>"
                    + "    <div class='user-list-header-block'>"
                    + "        <img src='" + user[i].img + "' class='user-header-img'>"
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
    $("#search-input").val(currentQueryString);
    $("#search-select-blog-btn").css("color","blue");
    $("#search-select-user-btn").css("color","black");

    search.display.displayUserList(search.testData.getUser());

})

/**
 * 选择搜索微博或者用户
 */
$(function () {
    $("#search-select-blog-btn").click(function () {
        console.log("search-select-blog-btn ....");
        $("#search-select-blog-btn").css("color","blue");
        $("#search-select-user-btn").css("color","black");
    })
    $("#search-select-user-btn").click(function () {
        console.log("search-select-user-btn ....");
        $("#search-select-blog-btn").css("color","black");
        $("#search-select-user-btn").css("color","blue");
    })
})

/*
$("body").load(function () {
    console.log("load.... ");
})*/
