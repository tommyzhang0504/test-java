package com.market.search.service.impl;

import com.market.pojo.MarketResult;
import com.market.pojo.SearchItem;
import com.market.search.dao.SearchItemDao;
import com.market.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchItemServiceImpl implements SearchItemService {

    @Autowired
    private SearchItemDao searchItemDao;
    @Autowired
    private SolrServer solrServer;

    @Override
    public MarketResult importItemsToIndex(){
        try {
            //1.查询所有商品数据
            List<SearchItem> list = searchItemDao.getItemList();

            //2.对商品数据进行遍历，添加到索引库
            for (SearchItem item: list) {
                SolrInputDocument solrInputDocument = new SolrInputDocument();
                solrInputDocument.addField("id", item.getId());
                solrInputDocument.addField("item_title", item.getTitle());
                solrInputDocument.addField("item_sell_point", item.getSell_point());
                solrInputDocument.addField("item_price", item.getPrice());
                solrInputDocument.addField("item_category_name", item.getCategory_name());
                solrInputDocument.addField("item_desc", item.getItem_desc());
                solrInputDocument.addField("item_image", item.getImage());
                solrServer.add(solrInputDocument);
            }
            solrServer.commit();
        }catch (Exception e){
            e.printStackTrace();
            return MarketResult.build(500,"索引导入失败");
        }
        return MarketResult.ok();
    }
}
