package com.tommy.java1;

import java.util.Random;

/**
 * @Author: ${user}
 * @Despriction:
 * @Date:Created_in 6/29/2018 10:45 AM
 * @Modified_By:
 */
public class TernaryDemo {

    /**
     * 三元与if的区别：
     * 只有一个条件，而且必须有结果时，可以使用三元；
     * 多条件判断时，使用if，if可以不输出结果；
     */
    public static void main(String[] args) {
        Random random = new Random();
        int i = random.nextInt(100);
        int j = random.nextInt(100);

        if (i < j) {
            System.out.println(j + "是这两个随机数里较大的数，因为另外一个是" + i);
        } else if (i > j) {
            System.out.println(i + "是这两个随机数里较大的数，因为另外一个是" + j);
        } else {
            System.out.println(i + "与" + j + "是相等的，因为" + i + "=" + j);
        }

        int max = i > j ? i : j;
        System.out.println(max);
    }
}
