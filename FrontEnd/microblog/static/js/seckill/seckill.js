

var seckill = {
   "listGoods":function(){
       console.log("获取商品列表");
       
       $.get(seckill.requestUrl.listGoodsUrl,function(data,status){
          console.log("status = " + status);
          console.log("data.length = " +  data.length);
  
          $("#seckill-list").text("");
          for(var i = 0 ; i < data.length ; i++){
           //   console.log(data[i])
             // console.log(data[i].seckillId + "  " + data[i].goodsName);
              
              
              var hour = 1;
              var minute = 20;
              
           //   console.log("finishTime = " + data[i].finishTime );
           //   console.log("finishTime = " + data[i].finishTime.fastTime );
              
              var finishTime = new Date(data[i].finishTime);
            //  console.log(finishTime);
               var curTime = new Date();
              var time = finishTime.getTime() - curTime.getTime();
           //    console.log("time = " + time);
              hour = time / (1000 * 60 * 60);
              minute =  time % (1000 * 60 * 60 ) / (1000 * 60 );
              
           //   console.log("hour = " + hour + "  minute = " + minute);
              
              var text = "<li class='list-group-item'>"
                    + "<div> "
                      +  "<img src='"  + data[i].imgPath  + "'/>"
                    + "</div> "
                    + "<div> "
                       + "<div> "
                           +" <span>" +data[i].goodsName +"</span>"
                        + "</div> "
                        + "<div> "
                           + "<span><a href='#'>商品详情</a></span>"
                        + "</div> "
                        + "<div> "
                           +"<span>抢购数量:</span><span>" + data[i].stockCount + "</span>"
                        + "</div> "
                        + "<div> "
                            +"<button><span>"+Math.floor(hour)+"</span>小时<span>"+Math.floor(minute)+"</span>分后进行抢购</button>"
                        + "</div> "
                    + "</div> "
                 "</li> "
              $("#seckill-list").append(text);
          }
           
       });
   },
    //请求的url
	"requestUrl":{
		//项目基础路径
		"listGoodsUrl": "/goods/list",
	},
}

$(function () {
    console.log("进入秒杀页面 ");
    
    seckill.listGoods();
})