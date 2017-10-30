package com.itheima.dao.daoImp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.dao.IProductDao;
import com.itheima.domain.PageModel;
import com.itheima.domain.Product;
import com.itheima.tools.JDBCUtils;

public class ProductDaoImp implements IProductDao {

	@Override
	public List<Product> getAllProduct() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}

	@Override
	public int getTotalRecords(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select count(*) from product where cid = ?";
		Long tmpCount = (Long)qr.query(sql, new ScalarHandler(), cid);
		int totalRecords = tmpCount.intValue();
		return totalRecords;
	}
	
	@Override
	public int getTotalRecords() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select count(*) from product";
		Long tmpCount = (Long)qr.query(sql, new ScalarHandler());
		return tmpCount.intValue();
	}

	@Override
	public List<Product> getRecordsByPageModel(PageModel pageModel) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product limit ?,?";
		Object[] params ={pageModel.getStartIndex(),pageModel.getPageSize()};
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class), params);
		return list;
	}

	@Override
	public void addProduct(Product pro) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into product(pid,pname,market_price,shop_price,pimage,pdate,is_hot,pdesc,pflag,cid) values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {pro.getPid(),pro.getPname(),pro.getMarket_price(),pro.getShop_price(),pro.getPimage(),pro.getPdate(),pro.getIs_hot(),pro.getPdesc(),pro.getPflag(),pro.getCid()};
		qr.update(sql, params);
	}

	//∑÷“≥≤È—Ø
	@Override
	public List<Product> getRecordsByPageModel(PageModel pageModel,String cid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where cid = ? limit ?,?";
		Object[] params ={cid,pageModel.getStartIndex(),pageModel.getPageSize()};
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class), params);
		return list;
	}

	@Override
	public List<Product> findCategoryProduct(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where cid = ?";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class), cid);
		return list;
	}

	//SELECT * FROM product  WHERE is_hot=1 ORDER BY pdate DESC  LIMIT 0,9
	@Override
	public List<Product> getAllHots() throws Exception {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT * FROM product  WHERE is_hot=1 ORDER BY pdate DESC  LIMIT 0,9";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}

	@Override
	public Product findProductByPid(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where pid = ?";
		Product product = qr.query(sql, new BeanHandler<Product>(Product.class), pid);
		return product;
	}

	//SELECT * FROM product ORDER BY pdate DESC LIMIT 0,9
	@Override
	public List<Product> getAllNews() throws Exception {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT * FROM product ORDER BY pdate DESC LIMIT 0,9";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}

}
