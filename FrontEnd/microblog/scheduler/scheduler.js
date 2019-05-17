
var scheduler={

    "requestUrl":{

        "queryJobUrl": "/scheduler/scheduler/list",
        "queryJobClassUrl": "/scheduler/scheduler/job/class/list",
        "addJobClassUrl": "/scheduler/scheduler/job",
    },

    "request":{

        "jobList":function(){
            $.ajax({
                url: scheduler.requestUrl.queryJobUrl,
                type: 'GET',
                cache: false,
                processData: false,
                contentType: false,
                success:function(data,status){
                    console.log("data = " + data);
                    scheduler.display.jobList(data);
                }

            })
        },

        "jobClassList":function(){
            $.ajax({
                url: scheduler.requestUrl.queryJobClassUrl,
                type: 'GET',
                success:function(data,status){
                    console.log("data = " + data);
                    scheduler.display.jobClass(data);
                }

            })
        },
        "addJobSubmit":function(jsonData){
            $.ajax({
                url: scheduler.requestUrl.addJobClassUrl,
                type: 'PUT',
                contentType : "application/json;charset=utf-8",
                dataType: "json",
                data: jsonData,
                success:function(data,status){
                    console.log("data = " + data);
                }

            })
        },

    },
    "display":{
        "jobList":function (result) {

            if(result == null){
                return;
            }
            $(".scheduler-job-list").remove();
            for(var i = 0; i < result.length ;i++){

                console.log("name = " + result[i].name);

                var text = " <tr class=\"scheduler-job-list\">"
                    + "<td>" + result[i].id + "</td>"
                    + "<td>" + result[i].name + "</td>"
                    + "<td>" + result[i].description + "</td>"
                    + "<td>" + result[i].cron + "</td>"
                    + "<td>" + result[i].jobGroup + "</td>"
                    + "<td>" + result[i].jobClass + "</td>"
                    + "<td>" + result[i].status + "</td>"
                    + "<td>" + result[i].createTime + "</td>"
                    + "<td>" + result[i].createBy + "</td>"
                    + "</tr>"
                console.log(text);
                $("#scheduler-job-table").append(text);
            }
        },
        "jobClass":function (result) {

            if(result == null){
                return;
            }
            $(".job-class-list").remove();
            for(var i = 0; i < result.length ;i++){
                var text = "<li class='list-group-item job-class-list'>"
                    +　result[i]
                    +　"</li>";
                console.log(text);
                $("#scheduler-display-add-class-ul").append(text);
            }
        }
    }
}

$(function () {
    scheduler.request.jobList();

    $("#scheduler-get-jobClass-btn").click(function () {
       // var myCars=new Array("Saab","Volvo","BMW");
       // scheduler.display.jobClass(myCars);

        scheduler.request.jobClassList();
    })

    $("body").on("click",".job-class-list",function() {
        var text = $(this).text();
        console.log(text);
        $("#scheduler-add-jobClass").val(text);
    })

    $("#scheduler-add-submit").click(function () {
        var requestData = {"":"","":"","":"","":"","":"","":"","":""}
                scheduler.request.jobClassList();
    })




})

$(function () {
    
    $("#scheduler-job-list-btn").click(function () {
        $(".scheduler-job-nav").removeClass("active");
        $("#scheduler-job-list-btn").addClass("active");
        $(".scheduler-display").hide();
        $("#scheduler-display-list").show();
        scheduler.request.jobList();

    })

    $("#scheduler-job-add-btn").click(function () {
        $(".scheduler-job-nav").removeClass("active");
        $("#scheduler-job-add-btn").addClass("active");
        $(".scheduler-display").hide();
        $("#scheduler-display-add").show();

    })

    $("#scheduler-job-manager-btn").click(function () {
        $(".scheduler-job-nav").removeClass("active");
        $("#scheduler-job-manager-btn").addClass("active");
        $(".scheduler-display").hide();
        $("#scheduler-display-manager").show();
    })

})

function load()
{
    alert("Frame is loaded");
}
