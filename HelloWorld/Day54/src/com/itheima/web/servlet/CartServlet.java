package com.itheima.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.itheima.domain.Cart;
import com.itheima.domain.CartItem;
import com.itheima.domain.Product;
import com.itheima.domain.User;
import com.itheima.service.serviceImp.ProductServiceImp;
import com.itheima.web.base.BaseServlet;

public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//ͨ��session��ȡ���ﳵ
	public Cart getCart(HttpServletRequest request){
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		if(null == cart){
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
	public String addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��һ���ȼ���û��Ƿ��¼,���δ��¼����ת����¼����login.jsp,�����¼���������ִ��
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		if(null == user){//�û�δ��¼
			request.setAttribute("errorMsg", "���¼����й��ﳵ���");
			return "/jsp/login.jsp";
		}
		//Ҫ���빺�ﳵ����Ʒpid
		String pid = request.getParameter("pid");
		String quantity = request.getParameter("quantity");
		//Ҫ���빺�ﳵ����Ʒ����
		int num = Integer.parseInt(quantity);
		//���ݻ�ȡ�����ݴ���һ��CartItem����
		CartItem cartItem = new CartItem();
		cartItem.setNum(num);
		Product product = new ProductServiceImp().findProductByPid(pid);
		cartItem.setProduct(product);
		//��ȡcart����
		Cart cart = this.getCart(request);
		//��cartitem�������cart������
		cart.addCartItem(cartItem);
		response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
		return null;
	}
	//clearCart
	public String clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart = this.getCart(request);
		cart.clearCart();
		response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
		return null;
	}
	public String removeCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		Cart cart = this.getCart(request);
		cart.removeCart(pid);
		response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
		return null;
	}
}