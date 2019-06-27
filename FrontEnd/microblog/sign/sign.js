var sign={
    "retCode":{
       "success": 1,
       "fail": 0,
    },
    "displayDate": new Date(),
    "POINTS_ADD_TYPE_DAILY_SIGNATURE": 10,
    "requestUrl":{
        "signatureUrl":"/points/signature",
        "signhistoryUrl":"/points/signhistory",
    },
    "request":{
        
        //签到请求
        "requestSignature":function () {

            $.ajax({
                type: "post",
                url: sign.requestUrl.signatureUrl,
                data: {
                    "type": sign.POINTS_ADD_TYPE_DAILY_SIGNATURE,
                },
                success: function(data,status){

                    console.log("data.code = " + data.code);
                    if(data.code == sign.retCode.success){
                        alert("签到成功!!!")
                    }
                    else {
                        alert("签到失败,"+data.message)
                    }


                },
                error:function(data,status){
                    alert("签到失败")
                    console.log(status);
                    console.log(data.status);
                }

            });

        },
        "requestSignHistory":function (year,month) {
            $.ajax({
                type: "get",
                url: sign.requestUrl.signhistoryUrl,
                data: {
                    "year": sign.displayDate.getFullYear(),
                    "month": sign.displayDate.getMonth()+1,
                },
                success: function(data,status){
                   console.log("签到记录:"+data);

                    sign.display.displayCalendar(sign.displayDate,data);
                },
                error:function(data,status){
                    sign.display.displayCalendar(sign.displayDate,null);
                }

            });

        },
    },
    //日历相关
    "calendar":{
        //每个月的天数
        //平年
        "month": ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月",],
        "nomalYearMonthDays": [31,28,31,30,31,30,31,31,30,31,30,31],
        //润年
        "leapYearMonthDays": [31,29,31,30,31,30,31,31,30,31,30,31],
        //判断是否是润年 false 平年
        "isLeapYear":function (year) {
            if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                //是闰年
                return true;
            } else {
                //是平年
                return false;
            }
        },
        //获取当月的总天数
        "getMonthDays":function (year,month) {

            if(sign.calendar.isLeapYear(year)){
                return sign.calendar.leapYearMonthDays[month];
            }
            else {
                return sign.calendar.nomalYearMonthDays[month];
            }
        },
        // 返回1号前面的空白天数
        // 日 一 二 三 四 五 六
        //    1  1号在周一返回1
        //             1 周四返回4
        // weekOfDateOne 1号所在的星期
        // 周日为 0 周一为1
        "beforeWeekOfDateOne":function (weekOfDateOne) {
            var empty =[0,1,2,3,4,5,6];
            return empty[weekOfDateOne];
        }

    },
    
    "display":{
        //显示displayDate的日历
        //displayDate  Date
        //signDayArray  签到的日期  [1,,3,4]
        "displayCalendar":function (displayDate,signDayArray) {

            sign.display.displayYearMonth(displayDate.getFullYear(),
                sign.calendar.month[displayDate.getMonth()]);

            console.log("displayDate = " + displayDate.toLocaleDateString());

            var curDate;
            if(displayDate.getMonth() == new Date().getMonth()){
                curDate = new Date().getDate();
            }


            //求1号前面的空白天数
            displayDate.setDate(1);
            var weed  = displayDate.getDay();
            var emptyDays = sign.calendar.beforeWeekOfDateOne(weed);

            //空白天数li
            var emptyHtml = "";
            for(var i = 0 ; i < emptyDays;i++){
                emptyHtml += "<li>x</li>";

            }
            //
            var monthDays = sign.calendar.getMonthDays(displayDate.getFullYear(),displayDate.getMonth());

            var dayHtml = "";

            if( (signDayArray != null) && (signDayArray.length != 0)){
                //有签到数据
                for(var i = 1 ; i <=  monthDays ; i++){

                    if(curDate == i){
                        dayHtml += "<li class='active-day'>" + "<span class='active'>"+ i + "</span>" + "</li>"
                    }
                    else {
                        if(signDayArray.includes(i)){
                            dayHtml += "<li class='sign-day' >" + i + "</li>"
                        }
                        else {
                            dayHtml += "<li>" + i + "</li>"
                        }

                    }

                }
            }
            else {
                //无签到数据
                for(var i = 1 ; i <=  monthDays ; i++){

                    if(curDate == i){
                        dayHtml += "<li class='active-day'>" + "<span class='active'>"+ i + "</span>" + "</li>"
                    }
                    else {

                        dayHtml += "<li>" + i + "</li>"
                    }

                }
            }


            $("#days-display").html(emptyHtml+dayHtml);

            sign.bindOnActiveDay();

        },
        /*
        显示年和月
         */
        "displayYearMonth":function (year,month) {

            $("#display-year").html(year);
            $("#display-month").html(month);

        }
    },
    /*
    给当天日期绑定事件
     */
    "bindOnActiveDay":function () {
        $(".active-day").on("click", function() {
            sign.request.requestSignature();
        })
    }



}

// 初始化
$(function () {
    var signDayArray = [1,3,6,8,12,19];
    sign.request.requestSignHistory();
   // sign.display.displayCalendar(sign.displayDate,signDayArray);
})
//左 右
$(function () {




    //向前
    $("#prev-btn").click(function () {

        var curYear = sign.displayDate.getFullYear();
        var curMonth = sign.displayDate.getMonth();

        if(curMonth == 0){

            curMonth = 11;
            curYear = curYear-1;
        }
        else {
            curMonth--;
        }

        sign.displayDate.setFullYear(curYear);
        sign.displayDate.setMonth(curMonth);

        //sign.display.displayCalendar(sign.displayDate,null);
        sign.request.requestSignHistory();

    })
    //向后
    $("#next-btn").click(function () {
        var curYear = sign.displayDate.getFullYear();
        var curMonth = sign.displayDate.getMonth();

        if(curMonth == 11){
            curMonth = 0;
            curYear = curYear+1;

        }
        else {
            curMonth++;
        }
        sign.displayDate.setFullYear(curYear);
        sign.displayDate.setMonth(curMonth);

        sign.request.requestSignHistory();
       // sign.display.displayCalendar(sign.displayDate,null);
    })

})

//测试
$(function () {

/*    console.log("2008 是否是润年："+sign.calendar.isLeapYear(2008));
    console.log("2009 是否是润年："+sign.calendar.isLeapYear(2009));

    console.log("2019.2 的天数="+sign.calendar.getMonthDays(2019,1));
    console.log("2020.2 的天数="+sign.calendar.getMonthDays(2020,1));

    console.log("1号在周日，前面的空白天数="+sign.calendar.beforeWeekOfDateOne(0));*/

})
/**
 * 签到请求
 */
$(function () {

    $(".active-day").on("click", function() {
        alert("进行签到");
        sign.request.requestSignature();
    })
})

