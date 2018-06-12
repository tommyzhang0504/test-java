package com.market.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class TestFreeMarker {
    @Test
    public void testFreeMarker() throws Exception{
        //创建一个模板文件
        //创建一个configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //设置模板所在路径，及模板字符集，一般是utf-8
        configuration.setDirectoryForTemplateLoading(new File("D:/03Temp/Develop/IDEA/workspace03/market-item-web/src/main/webapp/WEB-INF/ftl"));
        configuration.setDefaultEncoding("utf-8");
        //使用configuration对象加载一个模板文件，需要指定模板文件名
        Template template = configuration.getTemplate("hello.ftl");
        //创建一个数据集，可以是pojo，也可以是map，推荐使用map
        Map data = new HashMap();
        data.put("hello", "hello freemarker");
        //创建一个writer对象，指定输出文件的路径及文件名
        Writer out = new FileWriter("D:\\03Temp\\Develop\\temp\\hello.html");
        //使用模板对象的process方法输出文件
        template.process(data,out);
        //关闭writer流
        out.close();
    }
}
