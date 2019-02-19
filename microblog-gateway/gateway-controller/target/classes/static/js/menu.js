var menu={

}

/**
 * 认证标题按下
 */
$(function () {
    $("#home-menu-auth-head").click(function () {
        console.log("(\"#home-menu-auth-head\").click....")
        if($("#home-menu-auth-nva").css("display") == "none"){
            $("#home-menu-auth-nva").css("display","block");
        }
        else if($("#home-menu-auth-nva").css("display") == "block"){
            $("#home-menu-auth-nva").css("display","none");
        }
    })
});

/**
 * 认证标题按下
 */
$(function () {
    $("#home-menu-sys-head").click(function () {
        console.log("(\"#home-menu-sys-head\").click....")
        if($("#home-menu-sys-nva").css("display") == "none"){
            $("#home-menu-sys-nva").css("display","block");
        }
        else if($("#home-menu-sys-nva").css("display") == "block"){
            $("#home-menu-sys-nva").css("display","none");
        }
    })
});

/**
 * 认证标题按下
 */
$(function () {
    $("#home-menu-task-head").click(function () {
        console.log("(\"#home-menu-task-head\").click....")
        if($("#home-menu-task-nva").css("display") == "none"){
            $("#home-menu-task-nva").css("display","block");
        }
        else if($("#home-menu-task-nva").css("display") == "block"){
            $("#home-menu-task-nva").css("display","none");
        }
    })
});