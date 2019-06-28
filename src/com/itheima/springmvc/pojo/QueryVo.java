package com.itheima.springmvc.pojo;

import java.io.Serializable;
import java.util.List;

public class QueryVo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Items items;
	
	private List<Items> itemslist;
	
	public List<Items> getItemslist() {
		return itemslist;
	}

	public void setItemslist(List<Items> itemslist) {
		this.itemslist = itemslist;
	}

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}
	
}
