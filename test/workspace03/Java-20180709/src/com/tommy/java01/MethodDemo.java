package com.tommy.java01;

/**
 * @Author: tommy.zhang
 * @Created_Date: 2018-07-09 08:32
 * @Despriction: 计算一个长方形的面积，返回值类型：int，传入参数：int length, int wide
 */
public class MethodDemo {


    public static void main(String[] args) {
        int square = square(5, 6);
        System.out.println("面积是："+square);

    }


    public static int square(int length, int wide) {

        int square = length * wide;
        return square;
    }
}
