import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.sun.codemodel.internal.JForEach;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class TestSolrJ {

    @Test
    public void testAddDocument() throws Exception {
        //创建一个SolrServer对象，实际上是HttpSolrServer
        //需要知道solr服务的url
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.128:8080/solr/collection1");
        //创建文档对象SolrInputDocument
        SolrInputDocument document = new SolrInputDocument();
        //向文档中添加域，必须有id域，域的名称必须在solr工程的schema.xml中定义，这个已经在solr服务器上定义好
        document.addField("id", "test1233");
        document.addField("item_title", "测试商品2");
        document.addField("item_sell_point", "商品卖点");
        document.addField("item_price", 1000);
        //把文档写入索引库
        solrServer.add(document);
        //提交
        solrServer.commit();

    }

    @Test
    public void testDeleteDocumentById() throws Exception {
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.128:8080/solr/collection1");
        solrServer.deleteById("test001");
        //提交
        solrServer.commit();
    }

    @Test
    public void deleteByQuery() throws Exception {
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.128:8080/solr/collection1");
        solrServer.deleteByQuery("*:*");
//        solrServer.deleteByQuery("id:123"); //根据id进行删除，与上面的deleteById是一样的，
//        根据title删除时，注意分词的内容搜到多少删除多少

        solrServer.commit();

    }

    @Test
    public void searchDocument() throws Exception {
        //创建一个查询solrServer对象
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.128:8080/solr/collection1");
        //创建一个solrQuery查询对象
        SolrQuery solrQuery = new SolrQuery();
        //设置查询条件：过滤条件，分布条件，排序条件，高亮
        //solrQuery.set("q","*:*"); //set是一个通用的方法
        solrQuery.setQuery("手机");
        solrQuery.setStart(20);
        solrQuery.setRows(60);
        //设置默认的搜索域
        solrQuery.set("df", "item_keywords");
        //设置高亮
        solrQuery.setHighlight(true);
        //设置高亮显示的域
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em>");
        solrQuery.setHighlightSimplePost("</em>");

        //执行查询
        QueryResponse response = solrServer.query(solrQuery);
        //取查询结果
        SolrDocumentList results = response.getResults();

        //总记录数
        System.out.println("查询结果总记录数："+results.getNumFound());
        for (SolrDocument result : results) {
            System.out.println(result.get("id"));
            //取高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> stringList = highlighting.get(result.get("id")).get("item_title");
            String itemTitle = "";
            if (stringList!=null && stringList.size()>0){
                itemTitle = stringList.get(0);
            }else{
                itemTitle= (String) result.get("item_title");
            }

            System.out.println(result.get(itemTitle));
            System.out.println(result.get("item_sell_point"));
            System.out.println(result.get("item_price"));
            System.out.println(result.get("item_image"));
            System.out.println(result.get("item_category_name"));
            System.out.println(result.get("============================="));
        }
    }


}
