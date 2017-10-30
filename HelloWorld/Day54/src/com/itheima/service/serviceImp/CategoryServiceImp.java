package com.itheima.service.serviceImp;

import java.sql.SQLException;
import java.util.List;

import com.itheima.dao.ICategoryDao;
import com.itheima.dao.daoImp.CategoryDaoImp;
import com.itheima.domain.Category;
import com.itheima.service.ICategoryService;

public class CategoryServiceImp implements ICategoryService {

	//查询所有分类
	@Override
	public List<Category> findAllCategory(){
		ICategoryDao dao = new CategoryDaoImp();
		List<Category> list = null;
		try {
			list = dao.findAllCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


}
