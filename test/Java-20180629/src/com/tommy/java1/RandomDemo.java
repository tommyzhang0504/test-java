package com.tommy.java1;

import java.util.Random;

/**
 * @Author: ${user}
 * @Despriction:
 * @Date:Created_in 6/29/2018 10:22 AM
 * @Modified_By:
 */
public class RandomDemo {
    public static void main(String[] args) {
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            int j = random.nextInt(10)+1;
            System.out.println(j);

        }

        for (int i = 0; i < 20; i++) {
            double nextDouble = random.nextDouble();
            System.out.println(nextDouble);

        }
    }
}
