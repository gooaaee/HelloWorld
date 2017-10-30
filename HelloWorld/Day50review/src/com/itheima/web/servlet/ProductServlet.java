package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.PageModel;
import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import com.itheima.service.serviceImp.ProductServiceImp;
import com.itheima.web.base.BaseServlet;

public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//获取所属分类的所有的商品集合
	public String getProductListByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		IProductService service = new ProductServiceImp();	
		//查询cid对应的所有的商品
		List<Product> list = service.getProductListByCid(cid);
		request.setAttribute("proList", list);
		return "/jsp/product_list.jsp";
	}
	//查询cid对应数据库中的商品,使用分页,每页显示12条数据
	public String getProductListByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		IProductService service = new ProductServiceImp();	
		//初始化pageModel对象,PageModel(currentPageNum, totalRecords, pageSize)
		String numStr = request.getParameter("num");
		int num = 0;
		if(null != numStr){
			num = Integer.parseInt(numStr);
		}
		int pageSize = 12;
		PageModel pageModel = service.getPageModel(num,pageSize,cid);
		request.setAttribute("page", pageModel);
		return "/jsp/product_list.jsp";
	}
	public String getProductByPid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		IProductService service = new ProductServiceImp();
		Product pro = service.getProductByPid(pid);
		request.setAttribute("pro", pro);
		return "/jsp/product_info.jsp";
	}

}