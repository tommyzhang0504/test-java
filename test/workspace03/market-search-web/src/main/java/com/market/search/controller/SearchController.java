package com.market.search.controller;


import com.market.pojo.SearchResult;
import com.market.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * SearchController
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;
    @Value("${ROWS_NUMBER}")
    private Integer ROWS_NUMBER;
    @RequestMapping("/search")
    public String search(@RequestParam("q") String queryString, @RequestParam(defaultValue = "1") Integer page,Model model) throws Exception {
        queryString = new String(queryString.getBytes("iso-8859-1"),"utf-8");
        //int i = 10/0; //测试全局异常处理器
        SearchResult searchResult = searchService.search(queryString, page, ROWS_NUMBER);
        model.addAttribute("query",queryString);
        model.addAttribute("totalPage",searchResult.getTotalPages());
        model.addAttribute("itemList",searchResult.getItemList());
        model.addAttribute("page",page);

        //返回逻辑视图
        return "search";
    }

}
