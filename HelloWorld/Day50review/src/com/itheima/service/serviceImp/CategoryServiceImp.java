package com.itheima.service.serviceImp;

import java.sql.SQLException;
import java.util.List;

import com.itheima.dao.ICategoryDao;
import com.itheima.dao.daoImp.CategoryDaoImp;
import com.itheima.domain.Category;
import com.itheima.service.ICategoryService;

public class CategoryServiceImp implements ICategoryService {
	@Override
	public List<Category> getAllCategory() {
		ICategoryDao dao = new CategoryDaoImp();
		List<Category> list = null;
		try {
			list = dao.getAllCategory();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
