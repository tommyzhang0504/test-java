package com.market.search.listener;

import com.market.pojo.SearchItem;
import com.market.search.dao.SearchItemDao;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ItemAddMessageListener implements MessageListener {
    @Autowired
    private SearchItemDao searchItemDao;
    @Autowired
    private SolrServer solrServer;
    @Override
    public void onMessage(Message message) {
        try {
            //从消息中取出ItemId
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            long itemId = Long.parseLong(text);
            System.out.println("this is the itemID value:"+itemId);
            //通过itemId查询数据，取商品信息,查询之前，先等待一秒时间，保证事务提交成功
            Thread.sleep(1000);
            System.out.println("1000 millis later");
            SearchItem item = searchItemDao.getItemById(itemId);
            //创建文档对象
            SolrInputDocument solrInputDocument = new SolrInputDocument();
            //向文档中添加域
            solrInputDocument.addField("id", item.getId());
            solrInputDocument.addField("item_title", item.getTitle());
            solrInputDocument.addField("item_sell_point", item.getSell_point());
            solrInputDocument.addField("item_price", item.getPrice());
            solrInputDocument.addField("item_category_name", item.getCategory_name());
            solrInputDocument.addField("item_desc", item.getItem_desc());
            solrInputDocument.addField("item_image", item.getImage());
            //把文档对象写入索引库
            solrServer.add(solrInputDocument);
            solrServer.commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("there is an exception");
        }
    }
}
