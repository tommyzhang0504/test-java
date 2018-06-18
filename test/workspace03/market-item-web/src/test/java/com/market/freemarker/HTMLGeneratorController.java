package com.market.freemarker;

import com.market.item.pojo.Item;
import com.market.pojo.TbItem;
import com.market.pojo.TbItemDesc;
import com.market.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.FileWriter;
import java.io.Writer;

/**
 * 网页静态化HTML文件生成controller
 */
@Controller
public class HTMLGeneratorController {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public String generateHTML(@PathVariable Long itemId) throws Exception {
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Template template = configuration.getTemplate("item.ftl");

        TbItem tbItem = itemService.getItemById(itemId);
        TbItemDesc tbItemDesc = itemService.getItemDescById(itemId);
        Item item  = new Item(tbItem);

        //Map data = new HashMap();
        //data.put("hello", "spring freemarker template");
        Writer out = new FileWriter("D:\\03Temp\\Develop\\temp\\hello.html");
        template.process(item, out);
        out.close();

        return "item";
    }


}
