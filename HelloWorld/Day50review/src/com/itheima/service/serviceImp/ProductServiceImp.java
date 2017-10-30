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
	public Product getProductByPid(String pid) {
		IProductDao dao = new ProductDaoImp();
		Product pro = null;
		try {
			pro = dao.getProductByPid(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pro;
	}
	@Override
	public List<Product> getHots() {
		IProductDao dao = new ProductDaoImp();
		List<Product> list = null;
		try {
			list = dao.getHots();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//根据cid查询所有对应的商品
	@Override
	public List<Product> getProductListByCid(String cid) {
		IProductDao dao = new ProductDaoImp();
		List<Product> list = null;
		try {
			list = dao.getProductListByCid(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//获取pageModel对象,PageModel(currentPageNum, totalRecords, pageSize)
	@Override
	public PageModel getPageModel(int num, int pageSize, String cid) {
		IProductDao dao = new ProductDaoImp();
		int totalRecords=0;
		PageModel pageModel = null;
		try {
			totalRecords = dao.getTotalRecords(cid);
			pageModel = new PageModel(num, totalRecords, pageSize);
			//list是根据cid分页查询后获得的product的集合
			List<Product> list = dao.getProductListByCidWithPage(cid,pageModel);
			pageModel.setRecords(list);
			pageModel.setUrl("ProductServlet?method=getProductListByCidWithPage&cid="+cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageModel;
	}
	@Override
	public List<Product> getNews() {
		IProductDao dao = new ProductDaoImp();
		List<Product> list = null;
		try {
			list = dao.getNews();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
