package com.microblog.util.code;

public class Demo {

    public static void main(String args[]){




        Outer.Inner   inner = new Outer().new Inner();

        Outer.StaticInner  staticInner = new Outer.StaticInner();
    }
}
