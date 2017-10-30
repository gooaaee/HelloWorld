package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itheima.domain.Product;
import com.itheima.domain.User;
import com.itheima.service.IProductService;
import com.itheima.service.serviceImp.ProductServiceImp;
import com.itheima.web.base.BaseServlet;

public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		IProductService service = new ProductServiceImp();
		//��ȡ9��������Ʒ
		List<Product> list01 = service.getHots();
		//��ȡ9��������Ʒ
		List<Product> list02 = service.getNews();
		//�����������
		request.setAttribute("hots", list01);
		request.setAttribute("news", list02);
		return "/jsp/index.jsp";
	}
}