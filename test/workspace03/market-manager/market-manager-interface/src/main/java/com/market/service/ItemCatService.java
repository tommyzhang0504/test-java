package com.market.service;

import java.util.List;

import com.market.pojo.EasyUITreeNode;

public interface ItemCatService {
	List<EasyUITreeNode> getItemCatList(long parentId);

}
