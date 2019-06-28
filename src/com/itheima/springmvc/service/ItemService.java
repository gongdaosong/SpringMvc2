package com.itheima.springmvc.service;

import java.util.List;

import com.itheima.springmvc.pojo.Items;

public interface ItemService {

	public List<Items> selectItemsList();
	public Items selectItemById(Integer id);
	public void updateItemsById(Items items);
}
