package com.tommy.java1;


/**
 * @Author: tommy.zhang
 * @Date: 6/29/2018 2:12 PM
 * @Despriction: break作用于循环中，用于结束所处于的循环;
 * continue作用于循环中，用于跳出当前循环，开始下一次循环;
 */
public class BreakContinueDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print("j=" + j);
                if (j == 3) {
                    break;
                }
            }
            System.out.println("i=" + i);
        }

        A:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print("j=" + j);

                if (j==3) {
                    break A;
                }
            }
            System.out.println("i=" + i);
        }
    }
}
