package com.market.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.market.dao.TbItemCatDao;
import com.market.pojo.EasyUITreeNode;
import com.market.pojo.TbItemCat;
import com.market.pojo.TbItemCatQuery;
import com.market.pojo.TbItemCatQuery.Criteria;
import com.market.pojo.TbItemQuery;
import com.market.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatDao itemCatDao;

	public List<EasyUITreeNode> getItemCatList(long parentId) {
		// 根据父节点Id查询子节点列表 
		TbItemCatQuery example = new TbItemCatQuery();
		Criteria criteria = example.createCriteria();
		//设置parentId
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> tbItemList = itemCatDao.selectByExample(example);
		List<EasyUITreeNode> easyUITreeNodes = new ArrayList<>();
		for (TbItemCat tbItemCat : tbItemList) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			//如果是父节点就"closed",如果不是父节点，就显示"open"
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			easyUITreeNodes.add(node);
		}
		return easyUITreeNodes;
	}

}
