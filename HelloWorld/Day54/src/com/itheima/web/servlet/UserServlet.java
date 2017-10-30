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
	//��ת����¼ҳ��
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/login.jsp";
	}
	//��¼
	public String login(HttpServletRequest req, HttpServletResponse res) throws IOException{
		User user = MyBeanUtils.populate(User.class, req.getParameterMap());
		IUserService service = new UserServiceImp();
		User user01 = service.findUser(user);
		if(null != user01){//��¼�ɹ�
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", user01);
			res.sendRedirect(req.getContextPath());
			return null;
		}else{//��¼ʧ��
			req.setAttribute("errorMsg", "�ʺŻ��������");
			return "/jsp/login.jsp";
		}
	}
}