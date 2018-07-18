package com.tommy.codeblock;

/**
 * @Author: tommy.zhang
 * @Created_Date: 2018-07-18 09:56
 * @Class_Desp:
 */
public class CodeBlock {
    /**
     * @Author: tommy.zhang     * Date: 2018-07-18_13:57
     * @2018-07-18
15:38* @return: Me[]od_Desc:
     */
    public CodeBlock() {

        int x = 23;
        System.out.println(x + ":CodeBlock的构造方法");
    }

    {
        int x = 22;
        System.out.println(x + ":CodeBlock的构造代码块");
    }

    static {
        int x = 21;
        System.out.println(x + ":CodeBlock的静态代码块");
    }

    /**
     * @Author: tommy.zhang     * Date: 2018-07-18_13:45
     * @params: [a, b]
     * @return: int
     * Method_Desc:
     */
    public int getSum(int a, int b) {

        int c = a + b;
        return c;
    }
}
