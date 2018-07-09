package com.tommy.java1;

import java.util.Scanner;

/**
 * @Author: ${user}
 * @Despriction:
 * @Date:Created_in 6/29/2018 10:14 AM
 * @Modified_By:
 */
public class ScannerDemo {
    public static void main(String[] args) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            //int i = sc.nextInt();
            //System.out.println("你输入的整数是：" + i);
            String next = sc.next();
            System.out.println("你输入的内容是：" + next);
        }
    }
}
