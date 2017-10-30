package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.domain.Category;

public interface ICategoryDao {

	List<Category> getAllCategory() throws SQLException;

}
