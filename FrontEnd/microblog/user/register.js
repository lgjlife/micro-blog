var register={
    //登录名称类型 phone / email
    "registerNameType":"phone",
    "return":{
        "ACCOUNT_EXIST":{
            "code":"1030",
            "message":"帐号注册失败,帐号已经存在",
        },
        "NAME_EXIST":{
            "code":"1031",
            "message":"该用户名已经存在",
        },
        "PHONE_NUM_EXIST":{
            "code":"1032",
            "message":"该手机号已经注册",
        },
        "EMAIL_EXIST":{
            "code":"1033",
            "message":"该邮箱已经注册",
        },
        "CAN_REGISTER":{
            "code":"1034",
            "message":"帐号未注册",
        },
        "REGISTER_SUCCESS":{
            "code":"1035",
            "message":"帐号注册成功",
        },
        "REGISTER_FAIL":{
            "code":"1036",
            "message":"帐号注册失败",
        },
        "REGISTER_GET_VALIDATE_CODE_SUCCESS":{
            "code":"1037",
            "message":"获取注册验证码成功",
        },
        "VALIDATE_CODE_CHECK_PASS":{
            "code":"102",
            "message":"验证码校验通过",
        },
        "VALIDATE_CODE_CHECK_FAIL":{
            "code":"103",
            "message":"验证码校验失败",
        },
        "IMG_VALIDATE_CODE_CHECK_PASS":{
            "code":"104",
            "message":"图片验证码校验通过",
        },
        "IMG_VALIDATE_CODE_CHECK_FAIL":{
            "code":"105",
            "message":"图片验证码校验失败",
        },  
    },
    "rsa":{
        "modulus":"",
        "exponent":"",
    },
    
    //请求url
    "requestUrl":{
        //注册提交
       "registerSubmitUrl":"/user/register",
        //获取手机或者邮箱验证码
       "getVerificationCodeUrl":"/user/verification/code",
       //获取 rsa modulus  exponent
        "requestmModulusAndExponentUrl":"/user/key",
    },
 
    //向服务端ajax请求
    "request":{
        //注册提交
        "registerSubmit":function(){
            
            var sendData ={"registerName":"","registerNameType":"","verificationCode":"","registerPassword":"","imgVerificationCode":""};
            sendData.registerName = $("#register-name-input").val(); 
            sendData.registerNameType = register.registerNameType;
            sendData.verificationCode = $("#register-verification-code-input").val(); 
            sendData.registerPassword = $("#register-password-input").val(); 
            sendData.imgVerificationCode = $("#register-img-verification-code-input").val(); 
            
            
            //加密
            if((register.rsa.exponent == null)
                || (register.rsa.modulus == null)){
                    register.request.requestmModulusAndExponent();
                    return;
            }
            console.log("开始进行加密......");
            setMaxDigits(130);
            var publicKey = new RSAKeyPair(register.rsa.exponent,"",register.rsa.modulus);            
            var password = encryptedString(publicKey, encodeURIComponent(sendData.registerPassword));
            console.log("加密的密码为：" + password);   
            sendData.registerPassword = password;
            
            
            var jsonData = JSON.stringify(sendData);
            console.log(jsonData);
            $.ajax({
               type: "post",
               url : register.requestUrl.registerSubmitUrl,
               contentType : "application/json;charset=utf-8",
                dataType: "json",
               //数据格式是json串,传进一个person
               data : jsonData,

               success:function(data,status){
                    console.log(data.message);
                   if(data.code == register.return.REGISTER_SUCCESS.code){
                       $("#register-table").hide();
                       $("#register-success-disp").show();
                   }
                   else if((data.code == register.return.ACCOUNT_EXIST.code)
                       ||(data.code == register.return.PHONE_NUM_EXIST.code)
                       ||(data.code == register.return.EMAIL_EXIST.code)
                       ||(data.code == register.return.VALIDATE_CODE_CHECK_FAIL.code)
                       ||(data.code == register.return.IMG_VALIDATE_CODE_CHECK_FAIL.code)
                       ||(data.code == register.return.REGISTER_FAIL.code)
                   ){
                       $("#register-warn").show();
                       $("#register-warn").text(data.message);
                   }
               }
            })    
        },
        //获取手机或者邮箱验证码
        "getVerificationCode":function(){
            console.log("get-verification-code click ")
            if(register.checkRegisterName() == false){
                return;
            }
            var sendData ={"registerName":"","registerNameType":""};
            sendData.registerName = $("#register-name-input").val(); 
            sendData.registerNameType = register["registerNameType"]; 
            var jsonData = JSON.stringify(sendData);
            console.log(jsonData);
            $.ajax({
               type: "post",
               url : register.requestUrl.getVerificationCodeUrl,
               contentType : "application/json;charset=utf-8",
               data : jsonData,
               dataType: "json",
               success:function(data,status){
                  console.log("data = " + data + "  status = " + status);
                  console.log("code  = " + data.code);
                  console.log("message  = " + data.message); 
               }
            })    
        },
        //获取 rsa modulus  exponent
        "requestmModulusAndExponent":function(){
            var sendData ={"name":""};
            sendData.name = $("#register-name-input").val();
            var jsonData = JSON.stringify(sendData);
            $.ajax({
               type: "get",
               url : register.requestUrl.requestmModulusAndExponentUrl,
               contentType : "application/json;charset=utf-8",
               data:  {
                   name: $("#register-name-input").val()
               },
               dataType: "json",
               success:function(data,status){
                   console.log("data = " + data.message + "  status = " + status);
                   console.log("modulus  = " + data.data.modulus);
                   console.log("exponent  = " + data.data.exponent);
                   
                   register.rsa.modulus = data.data.modulus;
                   register.rsa.exponent = data.data.exponent;
                   
               }
            })    
        },
        
    },
    /**
    * 检测注册的手机号或者邮箱是否错误
    * return: true: 正常
              false: 错误
    */
    "checkRegisterName":function(){
        
        var registerName = $("#register-name-input").val();
        
        console.log("registerNameType = " + register["registerNameType"]);
        
        if(register["registerNameType"] == "phone"){
            // not the phonr num
            console.log("isPhone = " + regex.isPhone(registerName));
            
           if(regex.isPhone(registerName) == false) {
               $("#name-input-warn").show();
               $("#name-input-warn-disp").text("请输入正确的电话号码！");
               console.log("请输入正确的电话号码！");
               return false;
           }
           else{
               $("#name-input-warn").hide();
           }
        }
        else if(register["registerNameType"] == "email"){
            console.log("isEmail = " + regex.isEmail(registerName));
             // not the email
           if(regex.isEmail(registerName) == false) {
               $("#name-input-warn").show();
               $("#name-input-warn-disp").text("请输入正确的邮件地址！");
               console.log("请输入正确的邮件地址！");
               return false;
           }
           else{
               $("#name-input-warn").hide();
           }
        }
        
        return true;
    },
    /**
    * 检测验证码是否输入
    * return: true: 正常
              false: 错误
    */
    "checkVerificationCode":function(){
       var  verificationCode = $("#register-verification-code-input").val();
       if(verificationCode.length != 6){
           $("#verification-code-input-warn").show();
           $("#verification-code-input-warn-disp").text("请输入验证码！");
           
           return false;
           
           
       }
        $("#verification-code-input-warn").hide();
        return true;
    },
    /**
    *检测两次密码输入是否一致 
    * return: true: 正常
              false: 错误
    */
    "checkPasswordEquals":function(){
        var password = $("#register-password-input").val();
        var passwordAgain = $("#register-password-input-again").val();
        console.log("password.length = " + password.length);
        console.log("passpasswordAgainword.length = " + passwordAgain.length);
        if((password.length == 0)
           ||(passwordAgain.length == 0)){
            $("#password-input-warn").show();
            $("#password-input-warn-disp").text("请输入密码！");
            return false;
        }
        if(password != passwordAgain){
            $("#password-input-warn").show();
            $("#password-input-warn-disp").text("两次密码输入不一致！");
            return false;
        }
        else{
           $("#password-input-warn").hide(); 
            console.log("两次密码输入一致")
            return true;
        }
        
    },
   /**
    * 检测图片验证码是否输入
    * return: true: 正常
              false: 错误
    */
    "checkImgVerificationCode":function(){
       var  verificationCode = $("#register-img-verification-code-input").val();
       if(verificationCode.length != 4){
           $("#register-img-verification-code-input-warn").show();
           $("#register-img-verification-code-input-warn-disp").text("请输入图片验证码！");
           console.log("请输入图片验证码！");
           return false;
           
       }
        $("#register-img-verification-code-input-warn").hide();
        return true;
    },
    
    /**
    * 检测所有的输入
    */
    "checkAllInput":function(){
        //检测手机号胡或者邮箱是否正确 
        if(register.checkRegisterName() == false){
            return false;
        }
        //检测验证码是否为6位
        if(register.checkVerificationCode() == false){
            return false;
        }
        //检测密码是否一致
        if(register.checkPasswordEquals() == false){
            return false;
        }
        //检测图片验证码是否输入
        if(register.checkImgVerificationCode() == false){
            return false;
        }
        console.log("+++所有输入正常++++")
        return true;
    },
    
    
};

$(function(){
    
   
    /**
    * 选择登录方式
    */
    $("#register-name-select").click(function(){
        console.log("register-name-select  click");
        if($("#register-name-lable").text() == "手机"){
            $("#register-name-select").text("手机登录");
            $("#register-name-lable").text("邮箱");
            register["registerNameType"]="email";
            
        }
        else if($("#register-name-lable").text() == "邮箱"){
            $("#register-name-select").text("邮箱登录");
            $("#register-name-lable").text("手机");
            register["registerNameType"]="phone";
            $("#register-name-input").attr("placeholder","请输入手机号码"); 
        }    
    });
    /**
    * 注册请求提交
    */
    $("#register-submit-btn").click(function(){
         //if(register.checkAllInput() == true)
         {
            register.request.registerSubmit(); 
         }
            
    });
    
    /**
    * 获取手机或者邮箱验证码
    */
    $("#get-verification-code").click(function(){
         register.request.getVerificationCode();   
         register.request.requestmModulusAndExponent();

         $("#register-kaptcha").click();
    });
    
     /**
    * 密码输入框字符改变时检测密码强度
    */
    $("#register-password-input").bind("input propertychang",function(event){
        var password =  $("#register-password-input").val();
        
        var strength = regex.checkPasswordStrength(password);
        console.log("当前密码 =  " + $("#password-strength").text("简单")+ password + " strength = " + strength);
        
        if(strength == 0){
            $("#password-strength").text("简单");
        }
        else if(strength == 1){
            $("#password-strength").text("中等");
        }
        else if(strength == 2){
            $("#password-strength").text("复杂");
        }
        else{
           $("#password-strength").text("") ;
        }
        
        
    });
    
    
});
