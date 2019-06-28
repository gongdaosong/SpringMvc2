package com.itheima.springmvc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itheima.springmvc.dao.ItemsMapper;
import com.itheima.springmvc.pojo.Items;

/**
 * 
* @ClassName: ItemServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 龚道松 
* @date 2018年9月17日 下午3:11:05 
*
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemsMapper itemsMapper;
	//查询商品列表
	public List<Items> selectItemsList(){
		
		return itemsMapper.selectByExampleWithBLOBs(null);
	}
	
	public Items selectItemById(Integer id) {
		
		return itemsMapper.selectByPrimaryKey(id);
	}
	
	public void updateItemsById(Items items) {
		items.setCreatetime(new Date());
		itemsMapper.updateByPrimaryKeyWithBLOBs(items);
	}
}
