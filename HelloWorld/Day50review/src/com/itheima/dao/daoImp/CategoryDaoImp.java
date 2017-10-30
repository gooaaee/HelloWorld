package com.itheima.dao.daoImp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.dao.ICategoryDao;
import com.itheima.domain.Category;
import com.itheima.tools.JDBCUtils;

public class CategoryDaoImp implements ICategoryDao {

	@Override
	public List<Category> getAllCategory() throws SQLException{
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from category";
		List<Category> list = qr.query(sql, new BeanListHandler<Category>(Category.class));
		return list;
	}

}
