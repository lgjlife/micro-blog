package com.microblog.authorization.Filter;

public class UserContext {

    public static String MANAGER_TYPE = "manager-client";
    public static String NORMAL_TYPE = "normal-client";

    private static ThreadLocal<String> userType  = new ThreadLocal<>();


    public static void setUserType(String type){
        userType.set(type);
    }

    public static String getUserType(){
        String type = userType.get();
        userType.remove();

        return type;
    }




}
