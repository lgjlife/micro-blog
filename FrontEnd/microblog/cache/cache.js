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
        "searchType": "SEARCH:TYPR",

    },
    /**
     * 设置缓存
     * @param key
     * @param value
     * @param expiredTimeMS  过期时间，单位ms
     */
    "set":function (key,value,expiredTimeMS) {
        console.log("cache set: key="+key +  " value = " + value + " expiredTimeMS = " + expiredTimeMS)
        if((expiredTimeMS == 0 )  || (expiredTimeMS == null)){
            localStorage.setItem(key,value);
        }
        else {

            localStorage.setItem(key,JSON.stringify(value));
            localStorage.setItem(key+cache.key.expiredTime,expiredTimeMS);
            localStorage.setItem(key+cache.key.expiredStartTime,new Date().getTime());
        }

    },
    /**
     *  获取键
     * @param key
     * @returns {*} key存在，返回对象；不存在，返回null
     */
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
            cache.remove(key);
            return null;
        }



    },
    /**
     *  移除键
     * @param key
     */
    "remove":function (key) {
        localStorage.removeItem(key);
        localStorage.removeItem(key+cache.key.expiredTime);
        localStorage.removeItem(key+cache.key.expiredStartTime);
    },
    /**
     * 对键重新更新过期时间
     * @param key
     * @param expiredTimeMS  过期时间ms
     */
    "expired":function (key,expiredTimeMS) {

        if(cache.get(key)!=null){
            localStorage.setItem(key+cache.key.expiredTime,expiredTimeMS);
        }

    },
    /**
     * 清除所有缓存
     */
    "clear":function () {
        localStorage.clear();
    }

}

