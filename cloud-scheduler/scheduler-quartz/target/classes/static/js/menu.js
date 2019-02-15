var menu={

}

/**
 * 认证标题按下
 */
$(function () {
    $("#admin-menu-security-head").click(function () {
        console.log("(\"#admin-menu-security-head\").click....")
        if($("#admin-menu-security-nva").css("display") == "none"){
            $("#admin-menu-security-nva").css("display","block");
        }
        else if($("#admin-menu-security-nva").css("display") == "block"){
            $("#admin-menu-security-nva").css("display","none");
        }
    })
});

/**
 * 认证标题按下
 */
$(function () {
    $("#admin-menu-sys-head").click(function () {
        console.log("(\"#admin-menu-sys-head\").click....")
        if($("#admin-menu-sys-nva").css("display") == "none"){
            $("#admin-menu-sys-nva").css("display","block");
        }
        else if($("#admin-menu-sys-nva").css("display") == "block"){
            $("#admin-menu-sys-nva").css("display","none");
        }
    })
});

/**
 * 认证标题按下
 */
$(function () {
    $("#admin-menu-task-head").click(function () {
        console.log("(\"#admin-menu-task-head\").click....")
        if($("#admin-menu-task-nva").css("display") == "none"){
            $("#admin-menu-task-nva").css("display","block");
        }
        else if($("#admin-menu-task-nva").css("display") == "block"){
            $("#admin-menu-task-nva").css("display","none");
        }
    })
});