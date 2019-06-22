var monitor = {

    "refresh":{
        "refreshCenter":function () {

          //  document.getElementById('center-iframe').contentWindow.location.reload(true);

            $("#center-iframe").attr("src","https://www.baidu.com/")
        }

    }
}

$(function () {

    //注册中心
    $("#menu-center-select").click(function () {
        $(".display-module").hide();
        $("#display-center").show();
        monitor.refresh.refreshCenter();
    })

    $("#menu-swagger-select").click(function () {
        $(".display-module").hide();
        $("#display-swagger").show();
    })

    $("#menu-scheduler-select").click(function () {
        $(".display-module").hide();
        $("#display-scheduler").show();
    })

    $("#menu-system-select").click(function () {
        $(".display-module").hide();
        $("#system-minitor").show();
    })

})