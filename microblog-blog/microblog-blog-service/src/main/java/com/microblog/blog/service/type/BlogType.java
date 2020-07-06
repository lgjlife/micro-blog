package com.microblog.blog.service.type;

public enum BlogType {

    ALL("所有类型的博文"),
    PRIVATE("私有类型的博文");

    private  String desc;

    BlogType(String desc) {
        this.desc = desc;
    }

    public static void main(String args[]){

        System.out.println(BlogType.ALL.name().equals("ALL"));
    }


}
