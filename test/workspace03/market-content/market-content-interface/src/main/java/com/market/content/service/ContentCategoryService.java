
package com.market.content.service;

import java.util.List;

import com.market.pojo.EasyUIDataGridResult;
import com.market.pojo.EasyUITreeNode;
import com.market.pojo.MarketResult;

public interface ContentCategoryService {
	public abstract List<EasyUITreeNode> getContentCategoryList(long parentId);
	
	public abstract MarketResult addContentCat(long parentId, String name);
	
	public abstract MarketResult updateContentCat(long id, String name);
}
