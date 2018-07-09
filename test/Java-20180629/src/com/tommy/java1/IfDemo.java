package com.tommy.java1;

import java.util.Random;

/**
 * @Author: ${user}
 * @Despriction:
 * @Date:Created_in 6/29/2018 10:33 AM
 * @Modified_By:
 */
public class IfDemo {
    public static void main(String[] args) {
        Random random = new Random();
        int i = random.nextInt(100);
        //多条件判断时，这种方式的执行效率高
        if (i >= 80) {
            System.out.println(i + "这个随机数是不小于80的数字");
        } else if (i < 80 && i >= 60) {
            System.out.println(i + "这个随机数是一个小于80但不小于60的数字");
        } else {
            System.out.println(i + "这个随机数是一个小于60的数字");
        }
    }
}
