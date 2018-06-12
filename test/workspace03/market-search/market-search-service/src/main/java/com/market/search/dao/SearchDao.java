
package com.market.search.dao;

import com.market.pojo.SearchItem;
import com.market.pojo.SearchResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 查询索引库商品dao
 */
@Repository
public class SearchDao {
    @Autowired
    private SolrServer solrServer;

    public SearchResult search(SolrQuery solrQuery) throws Exception{
        //根据查询条件进行查询
        QueryResponse response = solrServer.query(solrQuery);

        //取查询结果
        SolrDocumentList solrDocumentList = response.getResults();

        //取查询结果总记录数
        long numFound = solrDocumentList.getNumFound();

        //把查询结果封装到searchItem中
        List<SearchItem> searchItemList = new ArrayList<>();
        for (SolrDocument solrDocument : solrDocumentList) {
            SearchItem node = new SearchItem();
            node.setCategory_name((String) solrDocument.get("item_category_name"));
            node.setId((String) solrDocument.get("id"));
            //只取一张图片，页面最多显示一张
            String image =(String)solrDocument.get("item_image");
            if(StringUtils.isNotBlank(image)){
                image=image.split(",")[0];
            }
            node.setImage(image);
            node.setItem_desc((String) solrDocument.get("item_desc"));
            node.setPrice((Long) solrDocument.get("item_price"));
            node.setSell_point((String) solrDocument.get("item_sell_point"));

            //取高亮
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> highlightList = highlighting.get(solrDocument.get("id")).get("item_title");
            String itemTitle = "";
            if(highlightList != null && highlightList.size()>0){
                itemTitle=highlightList.get(0);
            }else{
                itemTitle=(String) solrDocument.get("item_title");
            }
            node.setTitle(itemTitle);
            searchItemList.add(node);
        }
        //把记录与searchItem封装到searchResult中
        SearchResult searchResult = new SearchResult();
        searchResult.setItemList(searchItemList);
        searchResult.setRecordCount(numFound);
        //searchResult.setTotalPages();

        //返回结果
        return searchResult;
    }
}
