package com.itheima.service.serviceImp;

import java.sql.SQLException;

import javax.mail.MessagingException;

import com.itheima.dao.IUserDao;
import com.itheima.dao.daoImp.UserDaoImp;
import com.itheima.domain.User;
import com.itheima.service.IUserService;
import com.itheima.tools.MailUtils;

public class UserServiceImp implements IUserService {

	@Override
	public void register(User user) {
		IUserDao dao = new UserDaoImp();
		try {
			dao.register(user);
			MailUtils.sendMail(user.getEmail(), user.getCode());
		} catch (SQLException | MessagingException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int active(String code) {
		IUserDao dao = new UserDaoImp();
		int rows = 0;
		try {
			rows = dao.active(code);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public User login(User user) {
		IUserDao dao = new UserDaoImp();
		User user01 = null;
		try {
			user01 = dao.login(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user01;
	}

}
