//缓存
var cache={

    "key":{
        //辅助
        "expiredTime": "EXPIRED:TIME",
        "expiredStartTime": "EXPIRED:START:TIME",
        //全局使用
        //用户信息
        "loginUserInfo": "USER:INFO",
        //搜索字段
        "searchString": "SEARCH:STRING",

    },
    
    "set":function (key,value,expiredTimeMS) {

        if((expiredTimeMS == 0 )  || (expiredTimeMS == null)){
            localStorage.setItem(key,value);
        }
        else {

            localStorage.setItem(key,JSON.stringify(value));
            localStorage.setItem(key+cache.key.expiredTime,expiredTimeMS);
            localStorage.setItem(key+cache.key.expiredStartTime,new Date().getTime());
        }


    },
    "get":function (key) {

        var expiredTimeMS = localStorage.getItem(key+cache.key.expiredTime);
        var expiredStartTime = localStorage.getItem(key+cache.key.expiredStartTime);
        var curTime = new Date().getTime();

        var sum = Number(expiredStartTime)  + Number(expiredTimeMS);

        if((sum) > curTime){
            console.log("cache-缓存["+key+"]存在！");
            return  JSON.parse(localStorage.getItem(key));
        }
        else {
            console.log("cache-缓存["+key+"]不存在！");
            return null;
        }



    },
    "remove":function (key) {
        localStorage.removeItem(key);
        localStorage.removeItem(key+cache.key.expiredTime);
        localStorage.removeItem(key+cache.key.expiredStartTime);
    },
    "expired":function (key,expiredTimeMS) {

        if(cache.get(key)!=null){
            localStorage.setItem(key+cache.key.expiredTime,expiredTimeMS);
        }

    }

}

