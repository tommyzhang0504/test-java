package com.market.pageHelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.market.dao.TbItemDao;
import com.market.pojo.TbItem;
import com.market.pojo.TbItemQuery;

public class TestPageHelper {

	@Test
	public void testPageHelper() throws Exception{
		//在mybatis配置文件中配置pagehelper插件
		//在查询之前设置分页条件
		PageHelper.startPage(1, 10);
		//执行查询 
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		TbItemDao itemDao = applicationContext.getBean(TbItemDao.class);
		//创建example对象
		TbItemQuery example = new TbItemQuery();
		//Criteria criteria = example.createCriteria();
		//criteria.and
		List<TbItem> list = itemDao.selectByExample(example);
		//4.取分页信息,使用PageInfo对象
		PageInfo<TbItem> pageinfo = new PageInfo<>(list);
		System.out.println("记录数："+pageinfo.getTotal());
		System.out.println("总页数："+pageinfo.getPages());
		System.out.println("页条数："+pageinfo.getPageSize());
		System.out.println("返回的记录数："+list.size());	
	}
}
