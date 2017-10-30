package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.domain.PageModel;
import com.itheima.domain.Product;

public interface IProductDao {

	List<Product> getHots() throws SQLException;

	List<Product> getNews() throws SQLException;

	List<Product> getProductListByCid(String cid) throws SQLException;

	int getTotalRecords(String cid) throws SQLException;

	List<Product> getProductListByCidWithPage(String cid,PageModel pageModel) throws SQLException;

	Product getProductByPid(String pid) throws SQLException;

}
