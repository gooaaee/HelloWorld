package com.itheima.service.serviceImp;

import java.sql.SQLException;

import com.itheima.dao.IUserDao;
import com.itheima.dao.daoImp.UserDaoImp;
import com.itheima.domain.User;
import com.itheima.service.IUserService;

public class UserServiceImp implements IUserService {
	
	//µÇÂ¼ÑéÖ¤
	@Override
	public User findUser(User user) {
		IUserDao dao = new UserDaoImp();
		User user01 = null;
		try {
			user01 = dao.findUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user01;
	}

}
