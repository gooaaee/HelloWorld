package com.itheima.service;

import com.itheima.domain.User;

public interface IUserService {

	void register(User user);

	int active(String code);

	User login(User user);

}
