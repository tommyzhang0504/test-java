package com.tommy.codeblock;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * @Author: tommy.zhang
 * @Created_Date: 2018-07-18 10:08
 * @Class_Desp: 这是使用说明书，功能是查看静态代码块、构造代码块等执行的顺序
 */
public class TestTest {
    static{
        System.out.println("00:TestTest的静态代码块");
    }

    /**
     * @Author: tommy.zhang
     * Date: 2018-07-18_15:38
     * @param:  * @param null 
     * @param:  * @param null 
     * Method_Desc:  
     */
    public static void main(String[] args) {
        {
            System.out.println("01:TestTest main方法普通代码块");
        }
        TestCodeBlock tt1 = new TestCodeBlock();
        System.out.println("======");
        TestCodeBlock.main(args);
        System.out.println("======");
        TestCodeBlock tt2 = new TestCodeBlock();

    }
}
