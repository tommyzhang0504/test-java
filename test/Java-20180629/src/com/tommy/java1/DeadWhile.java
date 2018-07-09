package com.tommy.java1;

import sun.org.mozilla.javascript.internal.ast.ForInLoop;

/**
 * @Author: ${user}
 * @Despriction:
 * @Date:Created_in 6/29/2018 1:21 PM
 * @Modified_By:
 */
public class DeadWhile {
    public static void main(String[] args) {
        /*//while死循环
        int i = 0;
        while (true ) {
            System.out.println("你好。。"+i++);
        }*/

        //for的死循环，如果上面的不进行注释，程序就会报错，因为这条语句永远执行不到
        for (int j = 0; true; j++) {
            System.out.println(j);
        }
    }
}
