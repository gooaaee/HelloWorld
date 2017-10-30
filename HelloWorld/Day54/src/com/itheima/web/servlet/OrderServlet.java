package com.itheima.web.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itheima.domain.Cart;
import com.itheima.domain.CartItem;
import com.itheima.domain.Order;
import com.itheima.domain.OrderItem;
import com.itheima.domain.PageModel;
import com.itheima.domain.User;
import com.itheima.service.IOrderService;
import com.itheima.service.serviceImp.OrderServiceImp;
import com.itheima.tools.PaymentUtil;
import com.itheima.tools.UUIDUtils;
import com.itheima.web.base.BaseServlet;

public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//从cart.jsp提交订单后请求转发到order_info.jsp页面
	public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取购物车
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		//获取用户
		User loginUser = (User)session.getAttribute("loginUser");
		if(null == loginUser){//用户未登录
			request.setAttribute("errorMsg", "请登录后进行购物车添加");
			return "/jsp/login.jsp";
		}
		if(0 != cart.getMap().size()){//购物车不为空
			//封装order对象
			Order order = new Order();
			order.setOid(UUIDUtils.getCode());
			order.setOrderTime(new Date());
			order.setUser(loginUser);
			order.setState(1);
			order.setTotal(cart.getTotal());
			for(CartItem cartItem:cart.getCartItems()){
				OrderItem orderItem = new OrderItem();
				orderItem.setItemid(UUIDUtils.getCode());
				orderItem.setOrder(order);
				orderItem.setProduct(cartItem.getProduct());
				orderItem.setQuantity(cartItem.getNum());
				orderItem.setTotal(cartItem.getSubTotal());
				order.getList().add(orderItem);
			}
			//业务层处理,把订单表order及订单项表itemOrder存储在数据库中
			IOrderService service = new OrderServiceImp();
			service.save(order);
			//清空购物车
			cart.clearCart();
			//添加order请求属性
			request.setAttribute("order", order);
			return "/jsp/order_info.jsp";
		}else{//购物车为空
			return "/jsp/order_info.jsp";
		}
	}
	
	//findOrderByOid,从order_list.jsp的付款链接转到order_info.jsp
	public String findOrderByOid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oid = request.getParameter("oid");
		//根据oid查询数据库中的orders表
		IOrderService service = new OrderServiceImp();
		Order order = service.findOrderByOid(oid);
		request.setAttribute("order", order);
		return "/jsp/order_info.jsp";
	}
	//myOrderWithPage
	public String myOrderWithPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		//初始化pageModel,new PageModel(num, totalRecords, pageSize)
		String nm = request.getParameter("num");
		int num = 1;
		if(null != nm){
			num = Integer.parseInt(nm);
		}
		int pageSize = 3;
		
		IOrderService service = new OrderServiceImp();
		PageModel pageModel = service.getPageModel(num,pageSize,user.getUid());
		request.setAttribute("page", pageModel);
		
//		获取数据库中的所有订单
//		List<Order> orderList = service.findOrderByUid(user.getUid());
//		request.setAttribute("orderList", orderList);
		
		return "/jsp/order_list.jsp";
	}
	public String payOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("111111111");
		//获取order_info.jsp用户确认订单的5个参数
		String oid = request.getParameter("oid");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");
		String pd_FrpId = request.getParameter("pd_FrpId");
		//根据oid查询数据库中的orders表
		IOrderService service = new OrderServiceImp();
		Order order = service.findOrderByOid(oid);
		//添加oid对应订单的收货人,收货地址,收货电话到数据库
		order.setName(name);
		order.setAddress(address);
		order.setTelephone(telephone);
		service.updateOrder(order);
		
		//下面的是使用易宝支付官方给的demo文档中的内容
		
		//复制testPay项目中的payServlet
		// 把付款所需要的参数准备好:
		String p0_Cmd = "Buy";
		//商户编号,程序员自己处理
		String p1_MerId = "10001126856";
		//订单编号,即从order_info.jsp中接收到的订单编号,程序员自己处理
		String p2_Order = oid;
		//金额,p3_Amt是支付0.01元,到时后程序员自己处理
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		//接受响应参数的Servlet,程序员自己处理
		String p8_Url = "http://localhost:8080/Day53/OrderServlet?method=callBack";
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		//公司的秘钥
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
			
		//调用易宝的加密算法,对所有数据进行加密,返回电子签名
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
				
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);

		System.out.println(sb.toString());
		//https://www.yeepay.com/app-merchant-proxy/node?p0_Cmd=Buy&p1_MerId=10001126856&p2_Order=12343234321432432&p3_Amt=0.01&p4_Cur=CNY&p5_Pid=&p6_Pcat=&p7_Pdesc=&p8_Url=http://localhost:8080/TestPay/CallBackServlet?method=callBack&p9_SAF=&pa_MP=&pd_FrpId=CMBCHINA-NET&pr_NeedResponse=1&hmac=f6c0c76d5a0e8bd5c1e9fbce74c264d6
		// 使用重定向：定向到易宝支付处理
		response.sendRedirect(sb.toString());

		return null;
	}
	public String callBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 验证请求来源和数据有效性
		// 阅读支付结果参数说明
		// System.out.println("==============================================");
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		String rb_BankId = request.getParameter("rb_BankId");
		String ro_BankOrderId = request.getParameter("ro_BankOrderId");
		String rp_PayDate = request.getParameter("rp_PayDate");
		String rq_CardNo = request.getParameter("rq_CardNo");
		String ru_Trxtime = request.getParameter("ru_Trxtime");

		// hmac
		String hmac = request.getParameter("hmac");
		// 利用本地密钥和加密算法 加密数据
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		if (isValid) {
			// 有效
			if (r9_BType.equals("1")) {
				// 浏览器重定向
				response.setContentType("text/html;charset=utf-8");
				//程序员自己处理
				//response.getWriter().println("支付成功！订单号：" + r6_Order + "金额：" + r3_Amt);
				IOrderService service = new OrderServiceImp();
				Order order = service.findOrderByOid(r6_Order);
				order.setState(2);
				service.updateOrder(order);
				request.setAttribute("msg", "支付成功！订单号：" + r6_Order + "金额：" + r3_Amt);
				return "/jsp/info.jsp";
				
			} else if (r9_BType.equals("2")) {
				// 修改订单状态:
				// 服务器点对点，来自于易宝的通知
				System.out.println("收到易宝通知，修改订单状态！");//
				// 回复给易宝success，如果不回复，易宝会一直通知
				response.getWriter().print("success");
			}

		} else {
//			throw new RuntimeException("数据被篡改！");
			request.setAttribute("msg", "数据被篡改");
			return "/jsp/info.jsp";
		}
		return null;
	}
}