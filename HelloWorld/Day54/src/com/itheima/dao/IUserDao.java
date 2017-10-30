package com.itheima.dao;

import java.sql.SQLException;

import com.itheima.domain.User;

public interface IUserDao {

	User findUser(User user) throws SQLException;

}
