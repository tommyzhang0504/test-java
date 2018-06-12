package com.market.search.service.impl;

import com.market.pojo.SearchResult;
import com.market.search.dao.SearchDao;
import com.market.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;
    @Override
    public SearchResult search(String queryString, int page, int rows) throws Exception{
        //根据查询条件，拼装查询条件
        //创建一个SolrQuery对象
        SolrQuery solrQuery = new SolrQuery();
        //设置查询条件，分页条件，
        solrQuery.setQuery(queryString);
        if(page<1) page=1;
        solrQuery.setStart((page-1)*rows);
        if(rows<1)rows = 10;
        solrQuery.setRows(rows);
        //设置默认搜索域，设置高亮条件
        solrQuery.set("df","item_title");
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em color='red'>");
        solrQuery.setHighlightSimplePost("</em>");
        //调用dao进行查询
        SearchResult searchResult = searchDao.search(solrQuery);
        //通过总记录条数计算总页数
        long recordCount = searchResult.getRecordCount();
        int pages = (int)recordCount/rows;
        if(recordCount%rows>0)pages++;
        searchResult.setTotalPages(pages);

        //返回结果
        return searchResult;
    }
}
