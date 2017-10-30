package com.itheima.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itheima.domain.User;
import com.itheima.service.IUserService;
import com.itheima.service.serviceImp.UserServiceImp;
import com.itheima.tools.MyBeanUtils;
import com.itheima.tools.UUIDUtils;
import com.itheima.web.base.BaseServlet;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String registerUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/register.jsp";
	}
	public String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = MyBeanUtils.populate(User.class, request.getParameterMap());
		user.setCode(UUIDUtils.getUUID64());
		user.setState(0);
		user.setUid(UUIDUtils.getCode());
		IUserService service = new UserServiceImp();
		service.register(user);
		
		request.setAttribute("msg", "��ϲ��ע��ɹ�,�뵽������м���");
		return "/jsp/info.jsp";
	}
	public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		//��֤������,��ȷ����м���
		IUserService service = new UserServiceImp();
		int rows = service.active(code);
		if(0 == rows){//����ʧ��
			request.setAttribute("msg", "��������Ч");
			return "/jsp/info.jsp";
		}else{//����ɹ�
			request.setAttribute("msg", "����ʺż���ɹ�,���¼");
			return "/jsp/login.jsp";
		}
	}
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/login.jsp";
	}
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		IUserService service = new UserServiceImp();
		User user01 = service.login(user);
		if(null == user01){//��¼ʧ��
			request.setAttribute("msg", "�ʺŻ��������");
			return "/index.jsp";
		}else{//��¼�ɹ�
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user01);
			response.sendRedirect(request.getContextPath());
			return null;
		}
	}
	public String exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath());
		return null;
	}
}