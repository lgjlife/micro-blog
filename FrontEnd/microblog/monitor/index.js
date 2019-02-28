


$(function () {
    
    $("#menu-center-select").click(function () {
        $(".display-module").hide();
        $("#display-center").show();
    })

    $("#menu-swagger-select").click(function () {
        $(".display-module").hide();
        $("#display-swagger").show();
    })

    $("#menu-admin-select").click(function () {
        $(".display-module").hide();
        $("#display-admin").show();
    })

    $("#menu-zipkin-select").click(function () {
        $(".display-module").hide();
        $("#display-zipkin").show();
    })

    $("#menu-druid-select").click(function () {
        $(".display-module").hide();
        $("#display-druid").show();
    })

})