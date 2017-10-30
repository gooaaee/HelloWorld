package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import com.itheima.service.serviceImp.ProductServiceImp;
import com.itheima.web.base.BaseServlet;

public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IProductService service = new ProductServiceImp();
		//��ȡ����ǰ9��������ƷSELECT * FROM product  WHERE is_hot=1 ORDER BY pdate DESC  LIMIT 0,9
		List<Product> list01 = service.getAllHots();
		//��ȡǰ9��������ƷSELECT * FROM product ORDER BY pdate DESC LIMIT 0,9
		List<Product> list02 = service.getAllNews();
		//�������
		request.setAttribute("hots", list01);
		request.setAttribute("news", list02);
		return "/jsp/index.jsp";
	}
}