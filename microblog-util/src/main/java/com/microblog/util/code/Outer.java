package com.microblog.util.code;

public class Outer {

    int a;
    static int  b;
    public class Inner{

        void func(){
            a =1;
        }
    }

    public static  class StaticInner{

        public static int   c;
        public int d;
        void func(){
            b =1;
        }
    }

    static void func(){
        StaticInner.c = 1;
    }
}
