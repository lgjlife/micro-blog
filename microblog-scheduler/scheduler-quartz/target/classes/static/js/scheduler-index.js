
var schedulerInfo=[{"name":"任务1","cron":"0/5 * * * * ?","jobClass":"com.cloud.frame.scheduler.quartz.job.HelloJob",
    "status":"停止","description":"这是一个打印任务","createTime":"2018-11-16 11：00","createBy":"张三"},
    {"name":"任务2","cron":"0/5 * * * * ?","jobClass":"com.cloud.frame.scheduler.quartz.job.HelloJob",
        "status":"停止","description":"这是一个打印任务","createTime":"2018-11-16 11：00","createBy":"张三"}];
var schedulerClass=[{"jobClass":"com.cloud.frame.scheduler.quartz.job.HelloJob"},
    {"jobClass":"com.cloud.frame.scheduler.quartz.job.HelloJob","description":"这是一个打印任务1"},
    {"jobClass":"com.cloud.frame.scheduler.quartz.job.HelloJob","description":"这是一个打印任务2"},
    {"jobClass":"com.cloud.frame.scheduler.quartz.job.HelloJob","description":"这是一个打印任务3"},
                    ];
var scheduler={

    "jobCommand":{
        "start":"start", //启动 任务
        "stop":"stop", //停止 任务
        "delete":"delete", //删除 任务
    },
    "url":{
        "requestJobClass": "/scheduler/jobclass",
        "requestJobInfo": "/scheduler/info",
        "requestJobHandle": "/scheduler/handle",
    },
    "request":{
        "requestJobInfo":function () {
            $.ajax({url:scheduler.url.requestJobInfo,success:function(result){
                    console.log("requestJobInfo 请求成功");
                    console.log("数据 len = " + result.object.length + " 数据 = " + result.object[0].name);
                    scheduler.schedulerTableDisplay(result.object);
                }});
        },

        "requestJobClass":function () {
            $.ajax({url:scheduler.url.requestJobClass,success:function(result){
                    console.log("requestJobClass 请求成功");
                    console.log("数据 len = " + result.object.length + " 数据 = " + result.object[0].description);
                    scheduler.schedulerJobClassDisplay(result.object);
                }});
        },

        "requestJobHandle":function (schedulerInfo) {
            console.log("requestJobCommand 请求执行......");
            $.ajax({url:scheduler.url.requestJobHandle,
                    type:"POST",
                    data:JSON.stringify(schedulerInfo),
                    contentType:"application/json;charset=utf-8",
                    success:function(result){
                    console.log("requestJobCommand 请求成功......");
                }});
        }


    },
    /**
     * 显示任务信息
     * @param infos
     */
    "schedulerTableDisplay":function (infos) {

        //console.log("length = " + infos.length);
        //console.log("infos name = " + infos[0].name);
        for(var i = 0 ; i < infos.length;i++){

            console.log("info name = " + infos[i].name);

            var text = "<tr><td><input type='checkbox'></td>"
                + "<td class='scheduler-table-name'>" + infos[i].name + "</td>"
                + "<td class='scheduler-table-cron'>" + infos[i].cron  + "</td>"
                + "<td class='scheduler-table-jobClass'>" + infos[i].jobClass + "</td>"
                + "<td class='scheduler-table-status'>" + infos[i].status + "</td>"
                + "<td class='scheduler-table-description'>" + infos[i].description + "</td>"
                + "<td class='scheduler-table-createTime'>" + infos[i].createTime + "</td>"
                + "<td class='scheduler-table-jobGroup' style='display: none'>" + infos[i].jobGroup + "</td>"
                + "<td class='scheduler-table-createBy'>" + infos[i].createBy + "</td>"
                + "<td>"
                + "<button class='scheduler-table-btn'>启动</button>"
                + "<button class='scheduler-table-btn'>删除</button>"
                + "<button class='scheduler-table-btn'>挂起</button>"
                + "<button class='scheduler-table-btn'>恢复</button>"
                + "</td> </tr>";


          //  console.log(text);

            $("#scheduler-table table tbody ").append(text);
        }

    },
    /**
     * 显示任务类的列表
     * @param infos
     */
    "schedulerJobClassDisplay":function (infos) {
        for(var i = 0 ; i < infos.length;i++ ){

         //   console.log("info class = " + infos[i].class + " desc = " +  infos[i].description);

            var text = "<tr>"
                + "<td>" + infos[i].jobClass + "</td>"
                + "<td>" + infos[i].description + "</td>"
                + "<td>"
                + "<button class='scheduler-table-btn'>选择</button>"
                + "</td> </tr>";
        //    console.log(text);
            $("#scheduler-add-window-class table ").append(text);
        }

    }

};

$(function () {
    $("#scheduler-table").on("click",".scheduler-table-btn",function (e) {
        console.log("button 按下 -- " + $(this).text());

        //div(#scheduler-table)-->table --> tbody-->tr-->td-->button
        //$(this) == button
/*        console.log("1 = "+$(this).parent().parent().find("td:nth-child(2)").text());
        console.log("2 = "+$(this).parent().parent().find("td:nth-child(3)").text());*/
        console.log("name = "+$(this).parent().parent().find(".scheduler-table-name").text());
        console.log("description = "+$(this).parent().parent().find(".scheduler-table-description").text());

        var schedulerInfo={"jobClass":"","jobGroup":"","jobCommand":""};

        schedulerInfo.jobClass = $(this).parent().parent().find(".scheduler-table-jobClass").text();
        schedulerInfo.jobGroup = $(this).parent().parent().find(".scheduler-table-jobGroup").text();
        schedulerInfo.jobCommand = $(this).text();

        scheduler.request.requestJobHandle(schedulerInfo);

    });




})


$(function () {

    console.log(schedulerInfo[0].name);
    scheduler.request.requestJobClass();
    scheduler.request.requestJobInfo();
   // scheduler.schedulerJobClassDisplay(schedulerClass);
  //  scheduler.schedulerTableDisplay(schedulerInfo);
})

/**
 * 新增任务操作
 */
$(function () {
    $("#scheduler-handle-add-btn").click(function () {

    })
})