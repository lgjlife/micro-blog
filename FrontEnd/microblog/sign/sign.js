var sign={
    "POINTS_ADD_TYPE_DAILY_SIGNATURE": 10,
    "requestUrl":{
        "signatureUrl":"/points/signature",
        
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
                    alert("签到成功!!!")

                },
                error:function(data,status){
                   // alert("签到失败")
                    console.log(status);
                    console.log(data.status);
                }

            });

        },
    },


}
var nowqd = true;
$(function () {

    $(".ggli").on("click", function() {
        if($(this).hasClass("nowDataDodui")) {
            document.getElementsByClassName("nowDataDodui")[0].setAttribute("style", "background-color: #289860;background-size: 48%;background-repeat: no-repeat;background-position: center;color: transparent;")
           // alert("进行签到")
            sign.request.requestSignature();

        } else {
            alert("只能签到当天")
        }

    })
})

