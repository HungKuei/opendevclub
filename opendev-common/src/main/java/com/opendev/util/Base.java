package com.opendev.util;

class BaseClass {
    public BaseClass() {}
    {
        System.out.println("I’m BaseClass class");
    }
    static {
        System.out.println("static BaseClass");
    }
}
public class Base extends BaseClass {
    public Base() {}
    {
        System.out.println("I’m Base class");
    }
    static {
        System.out.println("static Base");
    }
    public static void main(String[] args) {
        new Base();
    }
}
