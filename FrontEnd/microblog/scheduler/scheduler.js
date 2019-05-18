
var scheduler={

    "requestUrl":{

        "queryJobUrl": "/scheduler/scheduler/list",
        "queryJobClassUrl": "/scheduler/scheduler/job/class/list",
        "createJobUrl": "/scheduler/scheduler/job",
        "jobManagerUrl": "/scheduler/scheduler/job/manager",
    },

    /**
     *  返回值
     */
    "return":{
        "start":{
            "success":{
                "code":"1",
                "desc":"任务启动成功",
            },
            "fail":{
                "code":"2",
                "desc":"任务启动失败",
            }
        },
        "pause":{
            "success":{
                "code":"3",
                "desc":"任务暂停成功",
            },
            "fail":{
                "code":"4",
                "desc":"任务暂停失败",
            }
        },
        "delete":{
            "success":{
                "code":"5",
                "desc":"任务删除成功",
            },
            "fail":{
                "code":"6",
                "desc":"任务删除失败",
            }
        },
        "create":{
            "success":{
                "code":"7",
                "desc":"任务创建成功",
            },
            "fail":{
                "code":"8",
                "desc":"任务创建失败",
            }
        },
        "remove":{
            "success":{
                "code":"9",
                "desc":"任务移除成功",
            },
            "fail":{
                "code":"10",
                "desc":"任务移除失败",
            }
        }
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

                    if(data.code == scheduler.return.create.success.code){
                        //任务创建成功
                        //进入任务列表显示页面
                        $(".scheduler-job-nav").removeClass("active");
                        $("#scheduler-job-list-btn").addClass("active");
                        $(".scheduler-display").hide();
                        $("#scheduler-display-list").show();
                        scheduler.request.jobList();
                    }
                    if(data.code == scheduler.return.create.fail.code){
                        //任务创建失败
                        alert(data.message);

                    }
                },
                error(xhr,status,error){
                    alert("出现异常，请重试！");
                }

            })
        },
        /**
         * 任务管理请求　启动，暂停，删除，移除
         * @param jsonData
         */
        "jobManager":function(jsonData){
            $.ajax({
                url: scheduler.requestUrl.jobManagerUrl,
                type: 'POST',
                contentType : "application/json;charset=utf-8",
                dataType: "json",
                data: jsonData,
                success:function(data,status){
                    if((data.code == scheduler.return.start.success.code)
                    || (data.code == scheduler.return.pause.success.code)
                    || (data.code == scheduler.return.remove.success.code)
                    || (data.code == scheduler.return.delete.success.code)){
                        //任务操作成功
                        stateBtnColorSet(data.data);
                    }
                    else{
                        //任务操作失败
                        alert(data.message);

                    }
                },
                error(xhr,status,error){
                    alert("出现异常，请重试！");
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
     * 　任务管理选项按钮
     */
    $("#scheduler-job-manager-btn").click(function () {
        $(".scheduler-job-nav").removeClass("active");
        $("#scheduler-job-manager-btn").addClass("active");
        $(".scheduler-display").hide();
        $("#scheduler-display-manager").show();
    })

    /**
     * 选中任务，进入任务管流页面
     */
    $("body").on("click",".job-list-manager-btn",function () {

        var item = $(this).parent().parent().attr("item");
        var job = JSON.parse(item);
        stateBtnColorSet(job.status);
        $(".scheduler-job-nav").removeClass("active");
        $("#scheduler-job-manager-btn").addClass("active");
        $(".scheduler-display").hide();
        $("#scheduler-display-manager").show();
        scheduler.display.jobManager(job);
    })

})

/**
 *  任务管理页面设置状态按钮颜色
 * @param state
 */
function stateBtnColorSet(state){

    console.log("state = " + state)
    if(state == "NONE"){
        $("#job-state-btn").css("background","black");
    }
    else if(state == "NORMAL"){
        $("#job-state-btn").css("background","blue");
    }
    else if(state == "PAUSED"){
        $("#job-state-btn").css("background","red");
    }
    else{
        $("#job-state-btn").css("background","wheat");
    }
}
/**
 * 任务管理　页面操作
 */
$(function () {




    /**
     * 任务启动
     */
    $("#job-startup-btn").click(function () {
        scheduler.request.jobManager(buildMangerRequestData("startup"));
    })
    /**
     * 任务删除，删除数据库中的数据
     */
    $("#job-delete-btn").click(function () {
       var deleteEnable =  confirm("是否要删除任务?删除之后如果需要启动任务，需要重新添加！"
           + $("#scheduler-manager-display").attr("name"));
       if(deleteEnable == true){
           scheduler.request.jobManager(buildMangerRequestData("delete"));
       }
    })

    /**
     * 移除任务，不删除数据库中的数据
     */
    $("#job-remove-btn").click(function () {
        scheduler.request.jobManager(buildMangerRequestData("remove"));
    })

    /**
     *  任务暂停
     */
    $("#job-paused-btn").click(function () {
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
