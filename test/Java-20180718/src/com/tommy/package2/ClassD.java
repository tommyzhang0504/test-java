package com.tommy.package2;

import com.tommy.package1.ClassA;

/**
 * @Author: tommy.zhang
 * @Created_Date: 2018-07-18 08:29
 * @Class_Desp:
 */
public class ClassD extends ClassA {
    ClassA classA = new ClassA();
    public void getA() {
        //classA.printA(); //default成员，出包无法使用
        //printA(); //default成员，出包无法使用
        printAa();  //protected成员，继承方法可以直接使用
        aa="你好啊"; //protected成员，继承方法可以直接使用
        //classA.printAa(); //new出来的对象，在包外，不可以调用protected方法
    }

}
