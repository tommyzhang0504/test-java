package com.tommy.java1;

/**
 * @Author: ${user}
 * @Despriction:
 * @Date:Created_in 6/29/2018 1:18 PM
 * @Modified_By:
 */
public class DoWhileDemo {
    public static void main(String[] args) {
        int i = 5;
        do {
            System.out.println(i++);
        } while (i<10);

        int j =5;
        //无条件先执行一次
        do {
            System.out.println(j++);
        } while (i<0);
    }
}
