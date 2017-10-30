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
	//通过session获取购物车
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
		//第一步先检查用户是否登录,如果未登录则跳转到登录界面login.jsp,如果登录则继续往下执行
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		if(null == user){//用户未登录
			request.setAttribute("errorMsg", "请登录后进行购物车添加");
			return "/jsp/login.jsp";
		}
		//要加入购物车的商品pid
		String pid = request.getParameter("pid");
		String quantity = request.getParameter("quantity");
		//要加入购物车的商品数量
		int num = Integer.parseInt(quantity);
		//根据获取的数据创建一个CartItem对象
		CartItem cartItem = new CartItem();
		cartItem.setNum(num);
		Product product = new ProductServiceImp().findProductByPid(pid);
		cartItem.setProduct(product);
		//获取cart对象
		Cart cart = this.getCart(request);
		//把cartitem对象放入cart对象中
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