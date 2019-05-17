
var scheduler={

    "requestUrl":{

        "queryJobUrl": "/scheduler/scheduler/list"
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
    },
    "display":{
        "jobList":function (result) {

            if(result == null){
                return;
            }

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




        }
    }
}

$(function () {
    scheduler.request.jobList();
})

function load()
{
    alert("Frame is loaded");
}
