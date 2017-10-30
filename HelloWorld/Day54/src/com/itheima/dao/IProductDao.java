package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.domain.PageModel;
import com.itheima.domain.Product;

public interface IProductDao {

	List<Product> getAllHots() throws Exception;

	List<Product> getAllNews()throws Exception;

	Product findProductByPid(String pid) throws SQLException;

	List<Product> findCategoryProduct(String cid) throws SQLException;

	int getTotalRecords(String cid) throws SQLException;

	List<Product> getRecordsByPageModel(PageModel pageModel,String cid)throws SQLException;

	List<Product> getAllProduct() throws SQLException;

	int getTotalRecords() throws SQLException;

	List<Product> getRecordsByPageModel(PageModel pageModel) throws SQLException;

	void addProduct(Product pro) throws SQLException;

}
