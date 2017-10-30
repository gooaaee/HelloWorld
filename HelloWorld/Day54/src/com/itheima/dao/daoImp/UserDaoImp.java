package com.itheima.dao.daoImp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import com.itheima.tools.JDBCUtils;

public class UserDaoImp implements IUserDao {

	//µÇÂ¼ÑéÖ¤
	@Override
	public User findUser(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where username = ? and password = ?";
		Object[] params = {user.getUsername(),user.getPassword()};
		User user01 = qr.query(sql, new BeanHandler<User>(User.class),params);
		return user01;
	}

}
