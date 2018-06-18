
package com.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.market.content.service.ContentCategoryService;
import com.market.pojo.EasyUITreeNode;
import com.market.pojo.MarketResult;

/**
 * contentCatController
 * @author Administrator
 *
 */
@Controller
public class ContentCatController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value="id",defaultValue="0") Long parentId){
		List<EasyUITreeNode> list = contentCategoryService.getContentCategoryList(parentId);
		return list;
	}

	
	@RequestMapping("/content/category/create")
	@ResponseBody
	public MarketResult addContentCat(Long parentId, String name){
		MarketResult contentCat = contentCategoryService.addContentCat(parentId, name);
		return contentCat;
	}
	
	@RequestMapping("/content/category/update")
	@ResponseBody
	public MarketResult updateContenCat(Long id, String name){
		
		MarketResult result = contentCategoryService.updateContentCat(id, name);
		return result;
	}
}
