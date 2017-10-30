package com.itheima.service;

import java.util.List;

import com.itheima.domain.PageModel;
import com.itheima.domain.Product;

public interface IProductService {

	List<Product> getAllHots();

	List<Product> getAllNews();

	Product findProductByPid(String pid);

	List<Product> findCategoryProduct(String cid);

	PageModel getPageModel(int num, int pageSize,String cid);

	List<Product> getAllProduct();

	PageModel getPageModel(int num, int pageSize);

	void addProduct(Product pro);

}
