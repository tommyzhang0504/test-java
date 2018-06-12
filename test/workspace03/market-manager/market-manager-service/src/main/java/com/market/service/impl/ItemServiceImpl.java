package com.market.service.impl;

import java.util.Date;
import java.util.List;

import com.market.jedis.JedisClient;
import com.market.jedis.JedisClientPool;
import com.market.utils.JsonUtils;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.market.dao.TbItemDao;
import com.market.dao.TbItemDescDao;
import com.market.pojo.EasyUIDataGridResult;
import com.market.pojo.MarketResult;
import com.market.pojo.TbItem;
import com.market.pojo.TbItemDesc;
import com.market.pojo.TbItemQuery;
import com.market.service.ItemService;
import com.market.utils.IDUtils;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * 商品管理Service
 *
 * @author Administrator
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemDao itemDao;
    @Autowired
    private TbItemDescDao itemDescDao;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Resource(name = "itemAddTopic")
    private Destination destination;
    @Autowired
    private JedisClient jedisClient;
    @Value("${ITEM_INFO}")
    private String ITEM_INFO;
    @Value("${ITEM_EXPIRE}")
    private int ITEM_EXPIRE;

    public TbItem getItemById(long itemId) {
        //查询之前先看缓存里是否有数据，没有就查询数据库，
        try {
            String json = jedisClient.get(ITEM_INFO+":"+itemId+":BASE");
            if(StringUtils.isNotBlank(json)){
                return JsonUtils.jsonToPojo(json,TbItem.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //查询数据库
        TbItem item = itemDao.selectByPrimaryKey(itemId);

        //查询完数据库，结果添加到缓存，同时设置过期时间，提高缓存利用率
        try {
            jedisClient.set(ITEM_INFO+":"+itemId+":BASE", JsonUtils.objectToJson(item));
            jedisClient.expire(ITEM_INFO+":"+itemId+":BASE",ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        //设置查询条件，通过拦截器进行拦截
        PageHelper.startPage(page, rows);
        //执行查询
        TbItemQuery itemQuery = new TbItemQuery();
        List<TbItem> list = itemDao.selectByExample(itemQuery);
        //取查询结果
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        EasyUIDataGridResult result = new EasyUIDataGridResult();

        result.setRows(list);
        result.setTotal(pageInfo.getTotal());

        // 返回结果
        return result;
    }

    @Override
    public MarketResult addItem(TbItem item, String desc) {
        // 生成商品Id
        final long itemId = IDUtils.genItemId();
        //补全item属性
        item.setId(itemId);
        //商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //向商品表插入数据
        itemDao.insert(item);
        //创建一个商品描述对应的pojo
        TbItemDesc itemDesc = new TbItemDesc();
        //补全pojo的属性
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDesc.setItemId(itemId);
        //向商品描述表插入数据
        itemDescDao.insert(itemDesc);

        //发送ActiveMQ通知消息
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(itemId + "");
                System.out.println("this is the sent itemID" + itemId);
                return textMessage;
            }
        });
        //返回结果
        return MarketResult.ok();
    }

    @Override
    public TbItemDesc getItemDescById(long itemId) {
        //查询之前先看缓存里是否有数据，没有就查询数据库，
        try {
            String json = jedisClient.get(ITEM_INFO+":"+itemId+":DESC");
            if(StringUtils.isNotBlank(json)){
                return JsonUtils.jsonToPojo(json,TbItemDesc.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItemDesc itemDesc = itemDescDao.selectByPrimaryKey(itemId);

        //查询完数据库，结果添加到缓存，同时设置过期时间，提高缓存利用率
        try {
            jedisClient.set(ITEM_INFO+":"+itemId+":DESC", JsonUtils.objectToJson(itemDesc));
            jedisClient.expire(ITEM_INFO+":"+itemId+":DESC",ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemDesc;
    }


}
