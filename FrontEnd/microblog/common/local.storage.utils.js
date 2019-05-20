
var storage={

    "constants":{
        //当前查询的字符串
        "currentQueryString":"currentQueryString",
    },
    "setItem":function (key,value) {
        localStorage.setItem(key,value);
    },
    "getItem":function (key) {
        return localStorage.getItem(key);
    },
    "removeItem":function (key) {
        localStorage.removeItem(key);
    }

}