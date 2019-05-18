
var scheduler={

    "requestUrl":{

        "queryJobUrl": "/scheduler/scheduler/list",
        "queryJobClassUrl": "/scheduler/scheduler/job/class/list",
        "createJobUrl": "/scheduler/scheduler/job",
        "jobManagerUrl": "/scheduler/scheduler/job/manager",
    },

    "request":{

        /**
         * 获取所有的任务
         */
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

        /**
         * 获取任务的class
         */
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
        /**
         *  提交任务
         * @param jsonData
         */
        "addJobSubmit":function(jsonData){
            $.ajax({
                url: scheduler.requestUrl.createJobUrl,
                type: 'PUT',
                contentType : "application/json;charset=utf-8",
                dataType: "json",
                data: jsonData,
                success:function(data,status){
                    console.log("data = " + data);
                }

            })
        },
        "jobManager":function(jsonData){
            $.ajax({
                url: scheduler.requestUrl.jobManagerUrl,
                type: 'POST',
                contentType : "application/json;charset=utf-8",
                dataType: "json",
                data: jsonData,
                success:function(data,status){
                    console.log("data = " + data);
                }

            })
        },

    },
    /**
     *  显示，回写html页面
     */
    "display":{
        /**
         *  显示任务列表
         * @param result
         */
        "jobList":function (result) {

            if(result == null){
                return;
            }
            $(".scheduler-job-list").remove();
            for(var i = 0; i < result.length ;i++){

                console.log("name = " + result[i].name);

                var stateText;
               // <!-- NONE,NORMAL,PAUSED,COMPLETE,ERROR,BLOCKED;-->
                if(result[i].status == "NONE"){
                   stateText = "<td style='background-color: black;'>"+"</td>"
                }
                else if(result[i].status == "NORMAL"){
                    stateText = "<td style='background-color: blue;'>"+"</td>"
                }
                else if(result[i].status == "PAUSED"){
                    stateText = "<td style='background-color: red;'>"+"</td>"
                }
                else{
                    stateText = "<td style='background-color: wheat;'>"+"</td>"
                }

                createTime = timeStamp2String(result[i].createTime);
                startAt = timeStamp2String(result[i].startAt);
                endAt = timeStamp2String(result[i].endAt);
                console.log("createTime = " + result[i].createTime + "  startAt = " +result[i].startAt +  " endAt = " +result[i].endAt  )
                console.log("createTime = " + createTime + "  startAt = " +startAt +  " endAt = " +endAt  )

                var item = JSON.stringify(result[i]);
                console.log("item = " + item);
                var text = " <tr class='scheduler-job-list'" + " item='" + item + "' >"
                    + stateText
                    + "<td>" + "<a href='javascript:void(0)' class='job-list-manager-btn'>" + result[i].id + "</a>" + "</td>"
                    + "<td>" + result[i].name + "</td>"
                    + "<td>" + result[i].description + "</td>"
                    + "<td>" + result[i].cron + "</td>"
                    + "<td>" + result[i].jobGroup + "</td>"
                    + "<td>" + result[i].jobClass + "</td>"
                    + "<td>" + result[i].status + "</td>"
                    + "<td>" + result[i].createTime + "</td>"
                    + "<td>" + result[i].createBy + "</td>"
                    + "<td>" + result[i].startAt + "</td>"
                    + "<td>" + result[i].endAt + "</td>"
                    + "</tr>"

               // console.log(text);

                $("#scheduler-job-table").append(text);
            }
        },
        /**
         *  显示类列表
         * @param result
         */
        "jobClass":function (result) {

            if(result == null){
                return;
            }
            $(".job-class-list").remove();
            for(var i = 0; i < result.length ;i++){
                var text = "<li class='list-group-item job-class-list'>"
                    +　result[i]
                    +　"</li>";
                //console.log(text);
                $("#scheduler-display-add-class-ul").append(text);
            }
        },
        "jobManager":function (item) {

            var text = "<tr>"
                + "<td>"+ "任务ID" +"</td>"
                + "<td>"+ item.id +"</td>"
                + "</tr><tr>"
                + "<td>"+ "名称" +"</td>"
                + "<td>"+ item.name +"</td>"
                + "</tr><tr>"
                + "<td>"+ "描述" +"</td>"
                + "<td>"+ item.description +"</td>"
                + "</tr><tr>"
                + "<td>"+ "表达式" +"</td>"
                + "<td>"+ item.cron +"</td>"
                + "</tr><tr>"
                + "<td>"+ "任务组" +"</td>"
                + "<td>"+ item.jobGroup +"</td>"
                + "</tr><tr>"
                + "<td>"+ "任务类" +"</td>"
                + "<td>"+ item.jobClass +"</td>"
                + "</tr><tr>"
                + "<td>"+ "任务状态" +"</td>"
                + "<td>"+ item.status +"</td>"
                + "</tr><tr>"
                + "<td>"+ "创建时间" +"</td>"
                + "<td>"+ item.createTime +"</td>"
                + "</tr><tr>"
                + "<td>"+ "创建人" +"</td>"
                + "<td>"+ item.createBy +"</td>"
                + "</tr><tr>"
                + "<td>"+ "启动时间" +"</td>"
                + "<td>"+ item.startAt +"</td>"
                + "</tr><tr>"
                + "<td>"+ "结束时间" +"</td>"
                + "<td>"+ item.endAt +"</td>"
                + "</tr>"

            //console.log("job = " + item);
            $("#scheduler-manager-display").html("");
            $("#scheduler-manager-display").append(text);
            $("#scheduler-manager-display").attr("name",item.name);
            $("#scheduler-manager-display").attr("jobGroup",item.jobGroup);
            $("#scheduler-manager-display").attr("jobClass",item.jobClass);
        }
    }
}
function timeStamp2String(time){

    var d = new Date(time);

    var times=d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();


    /*var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;*/
}


/**
 *  任务添加
 */
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
        var requestData = {"name":"","description":"","cron":"","jobGroup":"","jobClass":"","startAt":"","endAt":""};
        requestData.name = $("#scheduler-add-name").val() ;
        requestData.description = $("#scheduler-add-desc").val();
        requestData.cron = $("#scheduler-add-cron").val();
        requestData.jobGroup = $("#scheduler-add-group").val();
        requestData.jobClass = $("#scheduler-add-jobClass").val();
        requestData.startAt = $("#scheduler-add-startup-time").val();
        requestData.endAt = $("#scheduler-add-endAt-time").val();

        if((requestData.name == "")
            || (requestData.description == "")
            || (requestData.cron == "")
            || (requestData.jobGroup == "")
            || (requestData.jobClass == "")
            || (requestData.startAt == "")
            || (requestData.endAt == "")){
            alert("输入不能为空,请输入所有数据!");
            return;
        }

        var jsonData = JSON.stringify(requestData);
        console.log(jsonData);

        scheduler.request.addJobSubmit(jsonData);
    })




})

/**
 * 选项按钮
 */
$(function () {

    /**
     *  任务列表选项按钮
     */
    $("#scheduler-job-list-btn").click(function () {
        $(".scheduler-job-nav").removeClass("active");
        $("#scheduler-job-list-btn").addClass("active");
        $(".scheduler-display").hide();
        $("#scheduler-display-list").show();
        scheduler.request.jobList();

    })
    /**
     * 添加任务选项按钮
     */
    $("#scheduler-job-add-btn").click(function () {
        $(".scheduler-job-nav").removeClass("active");
        $("#scheduler-job-add-btn").addClass("active");
        $(".scheduler-display").hide();
        $("#scheduler-display-add").show();

    })
    /**
     * 　任务操作选项按钮
     */
    $("#scheduler-job-manager-btn").click(function () {
        $(".scheduler-job-nav").removeClass("active");
        $("#scheduler-job-manager-btn").addClass("active");
        $(".scheduler-display").hide();
        $("#scheduler-display-manager").show();
    })

    /**
     * 管理任务按钮
     */
    $("body").on("click",".job-list-manager-btn",function () {

        var item = $(this).parent().parent().attr("item");
        var job = JSON.parse(item);

        $(".scheduler-job-nav").removeClass("active");
        $("#scheduler-job-manager-btn").addClass("active");
        $(".scheduler-display").hide();
        $("#scheduler-display-manager").show();
        scheduler.display.jobManager(job);
    })

})

/**
 * 任务管理
 */
$(function () {

    /**
     * 任务启动
     */
    $("#job-startup-btn").click(function () {
        $("#job-state-btn").css("background","#0000FF");
        scheduler.request.jobManager(buildMangerRequestData("startup"));
    })
    /**
     * 任务删除
     */
    $("#job-delete-btn").click(function () {
       var deleteEnable =  confirm("是否要删除任务:"
           + $("#scheduler-manager-display").attr("name"));
       if(deleteEnable == true){
           scheduler.request.jobManager(buildMangerRequestData("delete"));
       }
    })
    /**
     *  任务暂停
     */
    $("#job-paused-btn").click(function () {

        $("#job-state-btn").css("background","red");
        scheduler.request.jobManager(buildMangerRequestData("pause"));
    })
    function buildMangerRequestData(type) {
        var data = {"type":"","jobGroup":"","jobClass":""};
        data.type = type;
        data.jobGroup = $("#scheduler-manager-display").attr("jobGroup");
        data.jobClass = $("#scheduler-manager-display").attr("jobClass");

        return JSON.stringify(data);
    }
})

function load()
{
    alert("Frame is loaded");
}
