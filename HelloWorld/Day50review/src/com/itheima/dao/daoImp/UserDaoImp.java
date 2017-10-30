package com.itheima.dao.daoImp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import com.itheima.tools.JDBCUtils;

public class UserDaoImp implements IUserDao {

	@Override
	public void register(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into user(uid,username,password,name,email,telephone,birthday,sex,state,code) "
				+ " values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = { user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
				user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode() };
		qr.update(sql, params);
	}

	@Override
	public int active(String code) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update user set state=?,code=? where code=?";
		Object[] params = {1,null,code};
		int rows = qr.update(sql, params);
		return rows;
	}

	@Override
	public User login(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where username=? and password=?";
		User user01 = qr.query(sql, new BeanHandler<User>(User.class), user.getUsername(),user.getPassword());
		return user01;
	}

}
