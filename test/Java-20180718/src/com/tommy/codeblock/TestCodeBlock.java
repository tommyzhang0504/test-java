package com.tommy.codeblock;

/**
 * @Author: tommy.zhang
 * @Created_Date: 2018-07-18 10:00
 * @Class_Desp:
 */
public class TestCodeBlock {
    public TestCodeBlock() {
            int x = 13;
        System.out.println(x+":Test的构造代方法");
    }

    {
        int x = 12;
        System.out.println(x+":Test的构造代码块");
    }

    public static void main(String[] args) {
        {
            int x = 14;
            System.out.println(x+":Test main方法中的普通代码块");
        }

        System.out.println("------");
        CodeBlock codeBlock1 = new CodeBlock();
        System.out.println("------");
        CodeBlock codeBlock2 = new CodeBlock();
        System.out.println("------");
        CodeBlock cdeBlock3 = new CodeBlock();

    }

    static {
        int x = 11;
        System.out.println(x+":Test的静态代码块");
    }
}
