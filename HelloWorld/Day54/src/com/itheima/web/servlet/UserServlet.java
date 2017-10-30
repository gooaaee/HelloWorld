package com.itheima.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itheima.domain.User;
import com.itheima.service.IUserService;
import com.itheima.service.serviceImp.UserServiceImp;
import com.itheima.tools.MyBeanUtils;
import com.itheima.web.base.BaseServlet;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//Ìø×ªµ½µÇÂ¼Ò³Ãæ
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/login.jsp";
	}
	//µÇÂ¼
	public String login(HttpServletRequest req, HttpServletResponse res) throws IOException{
		User user = MyBeanUtils.populate(User.class, req.getParameterMap());
		IUserService service = new UserServiceImp();
		User user01 = service.findUser(user);
		if(null != user01){//µÇÂ¼³É¹¦
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", user01);
			res.sendRedirect(req.getContextPath());
			return null;
		}else{//µÇÂ¼Ê§°Ü
			req.setAttribute("errorMsg", "ÕÊºÅ»òÃÜÂë´íÎó");
			return "/jsp/login.jsp";
		}
	}
}