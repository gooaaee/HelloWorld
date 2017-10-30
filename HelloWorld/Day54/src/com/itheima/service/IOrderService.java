package com.itheima.service;

import java.util.List;

import com.itheima.domain.Order;
import com.itheima.domain.PageModel;

public interface IOrderService {

	void save(Order order);

	Order findOrderByOid(String oid);

	List<Order> findOrderByUid(String uid);

	PageModel getPageModel(int num, int pageSize,String uid);

	void updateOrder(Order order);

}
