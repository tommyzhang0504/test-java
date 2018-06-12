
package com.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.market.content.service.ContentService;
import com.market.pojo.MarketResult;
import com.market.pojo.TbContent;

@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;
	@RequestMapping("/content/save")
	@ResponseBody
	public MarketResult addContent(TbContent content){
		MarketResult result = contentService.addContent(content);
		return result;
	}
}
