var login={
    //登录名称类型 phone / email
    "loginNameType":"phone",
    "requestUrl":{
       "loginSubmitUrl":"/user/user/login",
        //获取 rsa modulus  exponent
        "requestmModulusAndExponentUrl":"/user/user/key",
    },
    "rsa":{
        "modulus":"",
        "exponent":"",
    },
    "return":{
        "LOGIN_SUCCESS":{
            "code":"1000",
            "message":"用户登录成功",
        },
    },
    "request":{
        //登录提交
        "loginSubmit":function(){
            
            var sendData ={"loginName":"","loginNameType":"","loginPassword":"","imgVerificationCode":""};
            sendData.loginName = $("#login-name-input").val(); 
            sendData.loginNameType = login.loginNameType;
            sendData.verificationCode = $("#login-verification-code-input").val(); 
            sendData.loginPassword = $("#login-password-input").val(); 
            sendData.imgVerificationCode = $("#login-img-verification-code-input").val(); 
            
             //加密
            if((login.rsa.exponent == null)
                || (login.rsa.modulus == null)){
                    login.request.requestmModulusAndExponent();
                    return;
            }
            console.log("开始进行加密......");
            setMaxDigits(130);
            var publicKey = new RSAKeyPair(login.rsa.exponent,"",login.rsa.modulus);            
            var password = encryptedString(publicKey, encodeURIComponent(sendData.loginPassword));
            console.log("加密的密码为：" + password);   
            sendData.loginPassword = password;
            
            var jsonData = JSON.stringify(sendData);
            console.log(jsonData);
            $.ajax({
               type: "post",
               url : login.requestUrl.loginSubmitUrl,
               contentType : "application/json;charset=utf-8",
               //数据格式是json串,传进一个person
               data : jsonData,
               dataType: "json",
               success:function(data,status){
                    console.log(data.message);
                    if(data.code == login.return.LOGIN_SUCCESS.code){
                        $("#login-table").hide();
                        $("#login-success-disp").show();
                        $("#login-warn").hide();
                        var tokenVal = data.data;

                        token.setToken(tokenVal);
                        console.log("token = " + token.getToken());

                    }
                    else{
                        $("#login-table").show();
                        $("#login-success-disp").hide();
                        $("#login-warn").show();
                        $("#login-warn").text(data.message);
                    }
               }
                
            
            });  
            
            
            
        },
        //获取 rsa modulus  exponent
        "requestmModulusAndExponent":function(){
            $.ajax({
               type: "get",
               url : login.requestUrl.requestmModulusAndExponentUrl,
               contentType : "application/json;charset=utf-8",      
               data:  {
                    name: $("#login-name-input").val()
                },
                async: false,
                dataType: "json",
                success:function(data,status){


                   console.log("data = " + data.message + "  status = " + status);
                   console.log("modulus  = " + data.data.modulus);
                   console.log("exponent  = " + data.data.exponent);
                   
                   login.rsa.modulus = data.data.modulus;
                   login.rsa.exponent = data.data.exponent;

                    login.request.loginSubmit();
                   
               }
                
                 
            })    
        },
    },
     /**
    * 检测注册的手机号或者邮箱是否错误
    * return: true: 正常
              false: 错误
    */
    "checkloginName":function(){
        
        var loginName = $("#login-name-input").val();
        
        console.log("loginNameType = " + login["loginNameType"]);
        
        if(login["loginNameType"] == "phone"){
            // not the phonr num
            console.log("isPhone = " + regex.isPhone(loginName));
            
           if(regex.isPhone(loginName) == false) {
               $("#name-input-warn").show();
               $("#name-input-warn-disp").text("请输入正确的电话号码！");
               console.log("请输入正确的电话号码！");
               return false;
           }
           else{
               $("#name-input-warn").hide();
           }
        }
        else if(login["loginNameType"] == "email"){
            console.log("isEmail = " + regex.isEmail(loginName));
             // not the email
           if(regex.isEmail(loginName) == false) {
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
    *检测密码输入
    * return: true: 正常
              false: 错误
    */
    "checkPassword":function(){
        var password = $("#login-password-input").val();
        
        console.log("password.length = " + password.length);

        if(password.length == 0){
            $("#login-password-input-warn").show();     
            $("#login-password-input-warn-disp").text("请输入密码！");
            return false;
        }
        $("#login-password-input-warn").hide();
        return true;
        
        
    },
   /**
    * 检测图片验证码是否输入
    * return: true: 正常
              false: 错误
    */
    "checkImgVerificationCode":function(){
       var  verificationCode = $("#login-img-verification-code-input").val();
       if(verificationCode.length != 4){
           $("#login-img-verification-code-input-warn").show();
           $("#login-img-verification-code-input-warn-disp").text("请输入图片验证码！");
           console.log("请输入图片验证码！");
           return false;
           
           
       }
        $("#login-img-verification-code-input-warn").hide();
        return true;
    },
    
    /**
    * 检测所有的输入
    */
    "checkAllInput":function(){
        //检测手机号胡或者邮箱是否正确 
        if(login.checkloginName() == false){
            return false;
        }
        //检测密码是否一致
        if(login.checkPassword() == false){
            return false;
        }
        //检测图片验证码是否输入
        if(login.checkImgVerificationCode() == false){
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
    $("#login-name-select").click(function(){
        console.log("login-name-select  click");
        if($("#login-name-lable").text() == "手机"){
            $("#login-name-select").text("手机登录");
            $("#login-name-lable").text("邮箱");
            login["login-name-type"]="email";
            $("#login-name-input").attr("placeholder","请输入邮件地址"); 
        }
        else if($("#login-name-lable").text() == "邮箱"){
            $("#login-name-select").text("邮箱登录");
            $("#login-name-lable").text("手机");
            login["login-name-type"]="phone";
            $("#login-name-input").attr("placeholder","请输入手机号码"); 
        }    
    });
    /**
    * 登录请求提交
    */
    $("#login-submit-btn").click(function(){
        console.log("login-submit-btn click")
        if(login.checkAllInput() == true){

            login.request.requestmModulusAndExponent();

        }
    });
});