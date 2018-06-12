package com.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.market.pojo.EasyUIDataGridResult;
import com.market.pojo.MarketResult;
import com.market.pojo.TbItem;
import com.market.service.ItemService;

/**
 * 商品Controller层
 * 
 * @author Administrator
 *
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) { // 如果参数名一样，不用写映射关系
		TbItem tbitem = itemService.getItemById(itemId);
		return tbitem;
	}

	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		return itemService.getItemList(page, rows);
	}

	@RequestMapping(value = "/item/save", method = RequestMethod.POST)
	@ResponseBody
	public MarketResult addItem(TbItem item, String desc) throws Exception {
		// MarketResult result = itemService.addItem( item, desc);
		return itemService.addItem(item, desc);
	}

}
