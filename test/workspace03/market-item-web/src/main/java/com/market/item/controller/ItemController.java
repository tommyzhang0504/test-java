package com.market.item.controller;

import com.market.item.pojo.Item;
import com.market.pojo.TbItem;
import com.market.pojo.TbItemDesc;
import com.market.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品详情展示controller
 */

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("item/{itemId}")
    public String showItem(@PathVariable Long itemId, Model model){
        TbItem tbItem = itemService.getItemById(itemId);
        TbItemDesc tbItemDesc = itemService.getItemDescById(itemId);

        Item item = new Item(tbItem);
        model.addAttribute("item", item);
        model.addAttribute("itemDesc", tbItemDesc);
        return "item";
    }



}
