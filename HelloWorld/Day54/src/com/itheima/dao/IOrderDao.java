package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.domain.Order;
import com.itheima.domain.PageModel;

public interface IOrderDao {

	void save(Order order) throws SQLException;
	Order findOrderByOid(String oid) throws SQLException;
	List<Order> findOrderByUid(String uid) throws SQLException;
	int getTotalRecords(String uid)throws SQLException;
	List<Order> findOrderByUidWithPage(PageModel pageModel,String uid)throws SQLException;
	void updateOrder(Order order) throws SQLException;

}
