package com.market.item.listener;

import com.market.item.pojo.Item;
import com.market.pojo.SearchItem;
import com.market.pojo.TbItem;
import com.market.pojo.TbItemDesc;
import com.market.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ItemAddMessageListener implements MessageListener {
    @Autowired
    private ItemService itemService;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Value("${HTML_OUT_PATH}")
    private String HTML_OUT_PATH;
    @Override
    public void onMessage(Message message) {
        //从消息中取出ItemId
        try {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            long itemId = Long.parseLong(text);
            //通过itemId查询数据，取商品信息,查询之前，先等待一秒时间，保证事务提交成功
            Thread.sleep(1000);
            TbItem tbItem = itemService.getItemById(itemId);
            TbItemDesc tbItemDesc = itemService.getItemDescById(itemId);
            Item item = new Item(tbItem);

            //使用freemarker生成静态页面
            //1.创建模板-改造原来的jsp文件成ftl文件
            //2.加载模板对象
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            Template template = configuration.getTemplate("item.ftl");
            //3.准备模板需要数据
            Map data = new HashMap();
            data.put("item", item);
            data.put("itemDesc", tbItemDesc);
            //4.指定输入的目录与文件名
            Writer out = new FileWriter(HTML_OUT_PATH+itemId+".html");
            //5.生成静态页面
            template.process(data,out);
            //关闭资源
            out.close();
        }catch (Exception e){
           e.printStackTrace();
        }

    }
}
