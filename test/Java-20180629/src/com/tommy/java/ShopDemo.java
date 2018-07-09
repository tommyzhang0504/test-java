package com.tommy.java;

import javax.swing.text.StyledEditorKit;

/**
 * @Author: ${user}
 * @Despriction:
 * @Date:Created_in 6/29/2018 9:19 AM
 * @Modified_By: 步骤：
 * 1，实现表头，直接输出
 * 2，表中数据，由于后面有计算总价的部分，使用变量进行赋值，总价通过变量进行计算
 * 3，表尾数据，部分固定，一部分经过计算获得
 */
public class ShopDemo {
    public static void main(String[] args) {
        System.out.println("----------------库存商品清单------------------");
        System.out.println("品牌型号\t 尺寸\t 价格\t 库存数");
        String p1 = "MacBookAir";
        String p2 = "ThinkPadT450";
        String p3 = "ASUS-FL5800";

        double size1 = 13.3;
        double size2 = 14.0;
        double size3 = 15.6;

        double price1 = 6988.88;
        double price2 = 5999.99;
        double price3 = 4999.5;

        int count1 = 5;
        int count2 = 10;
        int count3 = 18;
        System.out.println(p1 + "     " + size1 + "\t " + price1 + "\t" + count1);
        System.out.println(p2 + "   " + size2 + "\t " + price2 + "\t" + count2);
        System.out.println(p3 + "    " + size3 + "\t " + price3 + "\t    " + count3);
        System.out.println("总库存数：" + (count1 + count2 + count3));
        System.out.println("库存商品总金额：" + (price1 * count1 + price2 * count2 + price3 * count3));
    }
}
