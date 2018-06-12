package com.market.service;

import com.market.pojo.EasyUIDataGridResult;
import com.market.pojo.MarketResult;
import com.market.pojo.TbItem;
import com.market.pojo.TbItemDesc;

public interface ItemService {
	
	TbItem getItemById(long itemId);

	EasyUIDataGridResult getItemList( int page, int rows);
	
	MarketResult addItem(TbItem item, String itemDesc);

	TbItemDesc getItemDescById(long itemId);
}
