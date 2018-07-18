package com.tommy.package1;

/**
 * @Author: tommy.zhang
 * @Created_Date: 2018-07-18 08:28
 * @Class_Desp:
 */
public class ClassA {
    String a = "A的默认权限属性";
    protected String aa = "A的受保护权限属性";

    void printA() {
        System.out.println("A的默认权限方法");
        System.out.println(a);
    }

    protected void printAa() {
        System.out.println("Aa的受保护权限方法");
        System.out.println(aa);
    }
}
