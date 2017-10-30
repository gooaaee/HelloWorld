package com.itheima.dao;

import java.sql.SQLException;

import com.itheima.domain.User;

public interface IUserDao {

	void register(User user) throws SQLException;

	int active(String code) throws SQLException;

	User login(User user) throws SQLException;

}
