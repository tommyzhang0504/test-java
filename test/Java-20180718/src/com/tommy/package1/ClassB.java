package com.tommy.package1;

/**
 * @Author: tommy.zhang
 * @Created_Date: 2018-07-18 08:28
 * @Class_Desp:
 */
public class ClassB extends ClassA{
    public  void getA() {
        System.out.println(a="SonB change the a");
    }

    public static void main(String[] args) {
        new ClassB().getA();
        new ClassA().printA();

        new ClassA().printAa();
    }
}
