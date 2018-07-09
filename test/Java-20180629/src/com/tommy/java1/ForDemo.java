package com.tommy.java1;

/**
 * @Author: ${user}
 * @Despriction:
 * @Date:Created_in 6/29/2018 11:03 AM
 * @Modified_By:
 */
public class ForDemo {
    public static void main(String[] args) {
        /*int i = 1;

        for (int j = 0; j < 10; j++) {
            System.out.println(i++ + j);
        }
        //执行顺序：定义 int j = 0，只进行一次；判断条件true，执行循环体：如果是false，直接结束；执行j+=2进行自增；
        for (int j = 0; j < 20; j += 2) {
            System.out.println(j);
        }*/
        int i = addFunction();
        System.out.println(i);
    }

    //利用For循环计算1+2+3+4的结果
    public static int addFunction() {
        int sum = 0;
        for (int i = 1; i <=100 ; i++) {
            sum += i;
        }
        return sum;
    }
}
