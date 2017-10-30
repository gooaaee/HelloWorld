package com.itheima.service;

import java.util.List;

import com.itheima.domain.PageModel;
import com.itheima.domain.Product;

public interface IProductService {

	List<Product> getHots();

	List<Product> getNews();

	List<Product> getProductListByCid(String cid);

	PageModel getPageModel(int num, int pageSize, String cid);

	Product getProductByPid(String pid);

}
