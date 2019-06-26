var daydataBG_Dodui = document.getElementById("daydataBG_Dodui");
var htmlLi = "";
for(var i = 0; i < 42; i++) {
    htmlLi += "<li class='bgli'></li>";
}
daydataBG_Dodui.innerHTML += htmlLi;

var Dodui_Date = new Date();
var DoduiYear = Dodui_Date.getFullYear(); //年
var DoduiMonth = Dodui_Date.getMonth() + 1; //月
var DoduiDate = Dodui_Date.getDate(); //日
document.getElementById("year").innerHTML = DoduiYear;
document.getElementById("month").innerHTML = DoduiMonth;
//步骤1：获取年份，判断是否是闰年；
function isLeapYear(year) {
    if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
        //是闰年
    } else {
        //是平年
    }
}
//步骤2：获取当前初始月份，得到相应年月下的天数；
var d = new Date(DoduiYear, DoduiMonth, 0); //当前年月下的天数
console.log("当前月份下的天数：" + d.getDate())
//步骤3：当前月份天数下的1号是星期几,及本月有多少天
var date = new Date();
date.setDate(1);
var weekday = new Array(7);
weekday[0] = "星期日";
weekday[1] = "星期一";
weekday[2] = "星期二";
weekday[3] = "星期三";
weekday[4] = "星期四";
weekday[5] = "星期五";
weekday[6] = "星期六";
console.log("本月第一天是 " + weekday[date.getDay()]);
//date.setMonth(date.getMonth() + 1);
//var lastDate = new Date(date - 3600000*24);
//console.log("本月最后一天是 " + lastDate.getDate());
//初始化
isLeapYear(DoduiYear); //初始年
var dataNum = d.getDate(); //返回初始年月下的天数
var weekOne = weekday[date.getDay()]; //初始化本月的1号是星期几
console.log(weekOne)

//获取1号排列前的空白数
var emptyDivs;

function emptyDiv() {
    if(weekOne == "星期天") {
        emptyDivs = 0;
    } else if(weekOne == "星期一") {
        emptyDivs = 1;
    } else if(weekOne == "星期二") {
        emptyDivs = 2;
    } else if(weekOne == "星期三") {
        emptyDivs = 3;
    } else if(weekOne == "星期四") {
        emptyDivs = 4;
    } else if(weekOne == "星期五") {
        emptyDivs = 5;
    } else if(weekOne == "星期六") {
        emptyDivs = 6;
    }
    console.log("返回前期空白数：" + emptyDivs)
}
emptyDiv();

var emptyHtml1 = "";
for(var i = 0; i < emptyDivs; i++) {
    emptyHtml1 += "<li class='emptyli1 ggli'></li>";
}
document.getElementById("dataNums_Dodui").innerHTML += emptyHtml1;

var dayNumHtml1 = "";
for(var i = 1; i <= dataNum; i++) {
    dayNumHtml1 += "<li class='Numli1 ggli'>" + i + "</li>";
}
document.getElementById("dataNums_Dodui").innerHTML += dayNumHtml1;

//设置今天号数背景色为绿色
var num1 = (DoduiDate + emptyDivs);
console.log(num1)
document.getElementsByClassName("ggli")[num1 - 1].classList.add("nowDataDodui");
document.getElementsByClassName("ggli")[num1 - 1].setAttribute('style', 'background-color: #289860;color:#ffffff')

for(var i = 0; i < num1 - 1; i++) {
    document.getElementsByClassName("ggli")[i].setAttribute('style', 'color:#ccc')
}

//签到
/*
var nowqd = true;
$(".ggli").on("click", function() {
    if($(this).hasClass("nowDataDodui")) {
        document.getElementsByClassName("nowDataDodui")[0].setAttribute("style", "background-color: #289860;background-image:url(images/DODUI.png);background-size: 48%;background-repeat: no-repeat;background-position: center;color: transparent;")
        alert("签到成功天")

       // nowqd = false;
    } else {
        alert("只能签到当天")
    }

})*/
