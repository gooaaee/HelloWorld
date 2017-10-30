package com.itheima.service.serviceImp;

import java.sql.SQLException;
import java.util.List;

import com.itheima.dao.IProductDao;
import com.itheima.dao.daoImp.ProductDaoImp;
import com.itheima.domain.PageModel;
import com.itheima.domain.Product;
import com.itheima.service.IProductService;

public class ProductServiceImp implements IProductService {

	@Override
	public List<Product> getAllProduct() {
		IProductDao dao = new ProductDaoImp();
		List<Product> list = null;
		try {
			list = dao.getAllProduct();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public PageModel getPageModel(int page, int pageSize) {
		IProductDao dao = new ProductDaoImp();
		int totalRecords = 0;
		List<Product> list = null;
		PageModel pageModel = null;
		try {
			totalRecords = dao.getTotalRecords();
			pageModel = new PageModel(page,totalRecords,pageSize);
			list = dao.getRecordsByPageModel(pageModel);
			pageModel.setRecords(list);
			//设置url
			pageModel.setUrl("ProductServlet?method=getAllProductWithPage");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageModel;
	}	
	@Override
	public List<Product> findCategoryProduct(String cid) {
		IProductDao dao = new ProductDaoImp();
		List<Product> list = null;
		try {
			list = dao.findCategoryProduct(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public void addProduct(Product pro) {
		IProductDao dao = new ProductDaoImp();
		try {
			dao.addProduct(pro);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Product findProductByPid(String pid) {
		IProductDao dao = new ProductDaoImp();
		Product product = null;
		try {
			product = dao.findProductByPid(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}
	@Override
	public PageModel getPageModel(int num, int pageSize,String cid) {
		IProductDao dao = new ProductDaoImp();
		int totalRecords = 0;
		List<Product> list = null;
		PageModel pageModel = null;
		try {
			totalRecords = dao.getTotalRecords(cid);
			pageModel = new PageModel(num,totalRecords,pageSize);
			list = dao.getRecordsByPageModel(pageModel,cid);
			pageModel.setRecords(list);
			//设置url
			pageModel.setUrl("ProductServlet?method=findCategoryProduct&cid="+cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageModel;
	}
	//获取排名前9的热门商品SELECT * FROM product  WHERE is_hot=1 ORDER BY pdate DESC  LIMIT 0,9
	@Override
	public List<Product> getAllHots() {
		IProductDao dao = new ProductDaoImp();
		List<Product> list = null;
		try {
			list = dao.getAllHots();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//获取前9的最新商品SELECT * FROM product ORDER BY pdate DESC LIMIT 0,9
	@Override
	public List<Product> getAllNews() {
		IProductDao dao = new ProductDaoImp();
		List<Product> list = null;
		try {
			list = dao.getAllNews();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
