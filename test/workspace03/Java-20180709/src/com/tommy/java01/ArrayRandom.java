package com.tommy.java01;

import com.tommy.Array.ArrayDemo;

import java.util.Random;
import java.util.Scanner;

/**
 * @Author: tommy.zhang
 * @Created_Date: 2018-07-09 17:16
 * @Despriction:
 */
public class ArrayRandom {
    public static void main(String[] args) {
        //定义一个数组，把班级中所有人的名字储存进去
        String[] array = new String[21];
        Random random = new Random();
        String temp = null;

        ArrayDemo arrayDemo = new ArrayDemo();
        arrayDemo.printArray(array);
    }

    /**
     * @Author: tommy.zhang
     * Date: 2018-07-09_17:24
     * Description: 定义一个添加数组数据的方法
     */
    public static String[] addNames(String[] names) {
        //{"张三","李四","王二麻子","余峻","小潘","莫工","学明","小周","小温","周康","小吴","小付",
        // "张羽","张宇","李成","苏约","费上","郑咏","老马","老李","老张","老杨"};
        names[0]="张三";
        names[1]="李四";
        names[2]="王二麻子";
        names[3]="余峻";
        names[4]="小潘";
        names[5]="莫工";
        names[6]="小周";
        names[7]="小温";
        names[8]="周康";
        names[9]="小吴";
        names[10]="小付";
        names[11]="张羽";
        names[12]="张宇";
        names[13]="李成";
        names[14]="费上";
        names[15]="苏约";
        names[16]="郑咏";
        names[17]="苏约";
        names[18]="老马";
        names[19]="老张";
        names[20]="老杨";
        return names;
    }

    /**
     * @Author: tommy.zhang
     * Date: 2018-07-09_17:24
     * Description: 定义方法，遍历数组并打印
     */
    public static void printArray(String[] strArray) {
        for (int i = 0; i < strArray.length; i++) {
            System.out.println(strArray[i]);
        }
    }

    /**
     * @Author: tommy.zhang
     * Date: 2018-07-09_17:27
     * Description: 随机点名的方法，打印名字出来
     */
    public static void callName(String[] strArray) {
        Random random = new Random();
        String temp = null;
        int count = strArray.length;
        System.out.println("数组长度为："+count);
        int k = 1;
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < strArray.length; i++) {
            System.out.println("输入任意数据开始下一次点名");
            scanner.next();
            int j = random.nextInt(count--);
            System.out.println(strArray[j]);
            temp = strArray[strArray.length-k];
            strArray[strArray.length-k++]=strArray[j];
            strArray[j]=temp;
        }
    }
}
