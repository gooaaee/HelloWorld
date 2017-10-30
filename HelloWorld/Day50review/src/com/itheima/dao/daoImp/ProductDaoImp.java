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
	public Product getProductByPid(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where pid = ?";
		Product pro = qr.query(sql, new BeanHandler<Product>(Product.class), pid);
		return pro;
	}
	//根据cid查询product表中的所有对应的商品
	@Override
	public List<Product> getProductListByCid(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where cid = ?";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class), cid);
		return list;
	}
	//获取pageModel的总条数
	@Override
	public int getTotalRecords(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select count(*) from product where cid = ?";
		Long tmpRecords = (Long)qr.query(sql, new ScalarHandler(), cid);
		return tmpRecords.intValue();
	}
	//根据cid分页查询product集合
	@Override
	public List<Product> getProductListByCidWithPage(String cid,PageModel pageModel) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where cid = ? limit ?,?";
		Object[] params = {cid,pageModel.getStartIndex(),pageModel.getPageSize()};
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class), params);
		return list;
	}
	@Override
	public List<Product> getHots() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		//SELECT * FROM product WHERE is_hot=1 ORDER BY pdate LIMIT 0,9
		String sql = "SELECT * FROM product WHERE is_hot=1 ORDER BY pdate LIMIT 0,9";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}

	@Override
	public List<Product> getNews() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		//SELECT * FROM product ORDER BY pdate LIMIT 0,9
		String sql = "SELECT * FROM product ORDER BY pdate LIMIT 0,9";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}

}
