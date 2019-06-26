var menu_manager = {
    "url": {
        "apiDocUrl": "http://localhost:8005/swagger-ui.html",
    },
    /**
     *去除 --选项中的--
     * @param str
     * @returns {string | *}
     */
    "getMenuStr": function (str) {
        return str.substr(2);
    },

    /**
     * 处理菜单点击
     * @param text
     */
    "handlerMenuClick": function (text) {
        //右侧标题栏显示处理
        menu_manager.titleAddList(text);
        //
        menu_manager.handleMenuBody(text);
    },
    "handleMenuBody": function (text) {
        $(".admin-right-body-list").hide();

        switch (text) {
            case "接口文档":
                menu_manager.handleMenuBodySysApidoc();
                break;
            case "注册中心":
                menu_manager.handleMenuBodySysRegisterCenter();
                break;
            case "zipkin监控":
                menu_manager.handleMenuBodySysZipkin();
                break;
            case "服务监控":
                menu_manager.handleMenuBodySysAdmin();
                break;
            case "druid监控":
                menu_manager.handleMenuBodySysDruid();
                break;
            case "任务监控":
                menu_manager.handleMenuBodySysScheduler();
                break;
            default:
                break;
        }
    },
    /**
     * swagger api doc
     */
    "handleMenuBodySysApidoc": function () {
        $("#admin-right-body-sys-apidoc").show();
    },
    /**
     * 注册中心
     */
    "handleMenuBodySysRegisterCenter": function () {
        $("#admin-right-body-sys-registercenter").show();
    },
    /**
     * zipkin 监控
     */
    "handleMenuBodySysZipkin": function () {
        console.log("zipkin监控....")
        $("#admin-right-body-sys-zipkin").show();
    },

    /**
     * 服务监控 admin
     */
    "handleMenuBodySysAdmin": function () {
        console.log("zipkin监控....")
        $("#admin-right-body-sys-admin").show();
    },
    /**
     * druid监控
     * */
    "handleMenuBodySysDruid": function () {
        console.log("zipkin监控....")
        $("#admin-right-body-sys-druid").show();
    },
    /**
     * 任务监控
     * */
    "handleMenuBodySysScheduler": function () {
        console.log("任务监控....")
        $("#admin-right-body-sys-scheduler").show();
    },


    /**
     *  点击菜单列表处理
     * @param text
     */
    "titleAddList": function (text) {
        var eqflag = false;
        var val;
        var title_text;
        $("#admin-right-title-body ul li").each(function () {

            //  console.log("$(this) = " + $(this))
            title_text = $(this).find("span").text();
            //  console.log("title_text = " + title_text + "  text = " + text);

            if (title_text == text) {
                console.log("=======");
                eqflag = true;
                return;
            }
        })
        if (eqflag == false) {
            //    console.log("添加标题：" + text)
            menu_manager.titleDisplay(text);
        }


    },
    /**
     * 标题栏添加菜单选项
     * @param text
     */
    "titleDisplay": function (text) {
        $("#admin-right-title-body ul").append("<li><span>" + text + "</span><a  href=\"javascript:void(0);\">X</a></li>");
    },


    /**
     * 处理点击菜单 ajax请求
     * @param menu_text
     */
    "handlerMenuRequest": function (menu_text) {
        switch (menu_text) {
            case "接口文档":
                // menu_manager.apiDocRequest();
                break;
            default:
                break;
        }
    },
    /**
     * 处理获取文档api请求
     */
    "apiDocRequest": function () {
        $.ajax({
            url: menu_manager.url.apiDocUrl, success: function (result) {
                $("#admin-right-body-sys-apidoc").html(result);
            }
        });
    },

}
/**
 * 认证中心菜单选项点击处理
 */
$(function () {
    $("#admin-menu-security-nva").find("a").on("click", function (e) {
        $(".menu_nva_select").removeClass("menu_nva_select");
        $(this).toggleClass("menu_nva_select");
        console.log("当前菜单名称 = " + menu_manager.getMenuStr($(this).text()));
        menu_manager.handlerMenuClick(menu_manager.getMenuStr($(this).text()))
    })
})
/**
 *  删除标题栏的菜单操作
 */
$(function () {
    $("#admin-right-title-body ul").on("click", "li", function (e) {
        console.log("删除标题.....");
        console.log($(this));
        $(this).remove();
    })
})
/**
 * 系统监控菜单选项点击处理
 */
$(function () {
    $("#admin-menu-sys-nva").find("a").on("click", function (e) {
        $(".menu_nva_select").removeClass("menu_nva_select");
        $(this).toggleClass("menu_nva_select");
        console.log("当前菜单名称 = " + menu_manager.getMenuStr($(this).text()));
        menu_manager.handlerMenuClick(menu_manager.getMenuStr($(this).text()))


    })
})
/**
 * 任务中心菜单选项点击处理
 */
$(function () {
    $("#admin-menu-task-nva").find("a").on("click", function (e) {
        $(".menu_nva_select").removeClass("menu_nva_select");
        $(this).toggleClass("menu_nva_select");
        console.log("当前菜单名称 = " + menu_manager.getMenuStr($(this).text()));
        menu_manager.handlerMenuClick(menu_manager.getMenuStr($(this).text()))
    })
})