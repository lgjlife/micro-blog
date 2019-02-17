var regex={
    "isPhone":function(phone){
       var pattern = /^((13\d|14[57]|15[^4,\D]|17[678]|18\d)\d{8}|170[059]\d{7})$/;  
        return pattern.test(phone);
    },
    
    "isEmail":function(email){
        var pattern = /^\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]*\.)+[A-Za-z]{2,14}$/;
        return pattern.test(email);
    },
    /***
     * 密码强度校验
     * @returns  0：低等强度密码，单纯字母或者单纯特殊字符
     * 			 1：中等强度密码，数字/字母/特殊字符（\x21-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E）两两组合
     * 			 2：高等强度，包含三种
     */
    "checkPasswordStrength":function(password){
        /*字母构成*/
        var regex  = /^[a-zA-Z]+$/;
        var passwordStrength = null;

        if(regex.test(password)){
            passwordStrength = 0;
            console.log("全部为纯字母 passwordStrength = " + passwordStrength );
            return passwordStrength;
        }
        /*特殊字符构成*/

        var regex  = /^[\x21-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E\x3A-\x40\x5B-\x60\x7B-\x7E]+$/;
        if(regex.test(password)){
            passwordStrength = 0;
            console.log("全部为纯特殊字符 passwordStrength = " + passwordStrength );
            return passwordStrength;
        }

        var regex  = /^[\x21-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E]+$/;
        if(regex.test(password)){
            passwordStrength = 0;
            console.log("全部为纯特殊字符 passwordStrength = " + passwordStrength );
            return passwordStrength;
        }
        /*特殊字符和字母构成*/
        /*(?=.*?[a-z])是肯定型顺序环视，限定当前位置的后面，能匹配.*?[a-z]*/
        var regex1  =  /^(?=.*?[a-zA-Z])(?=.*?[\x21-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E])[a-zA-Z\x21-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E]+$/;
        /*特殊数字和字母构成*/
        var regex2  =  /^(?=.*?[a-zA-Z])(?=.*?[0-9])[a-zA-Z0-9]+$/;
        /*特殊数字和和字符构成*/
        var regex3  =  /^(?=.*?[0-9])(?=.*?[\x21-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E])[0-9\x21-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E]+$/;
        if(regex1.test(password)){
            passwordStrength = 1;
            console.log("特殊字符和字母构成 passwordStrength = " + passwordStrength );
            return passwordStrength;
        }
        if(regex2.test(password)){
            passwordStrength = 1;
            console.log("特殊数字和字母构成 passwordStrength = " + passwordStrength );
            return passwordStrength;
        }
        if(regex3.test(password)){
            passwordStrength = 1;
            console.log("特殊数字和和字符构成 passwordStrength = " + passwordStrength );
            return passwordStrength;
        }

        var regex  =  /^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[\x21-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E])[a-zA-Z0-9\x21-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E]+$/;
        if(regex.test(password)){
            passwordStrength = 2;
            console.log("特殊数字和和字符和字母构成 passwordStrength = " + passwordStrength );
            return passwordStrength;
        }
        return null;
    },

}


