package com.tommy.java1;

/**
 * @Author: tommy.zhang
 * @Date:Created_in 6/29/2018 1:25 PM
 * @Description:
 */
public class ForFor {
    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}
