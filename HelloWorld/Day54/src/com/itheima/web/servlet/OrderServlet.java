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
	//��cart.jsp�ύ����������ת����order_info.jspҳ��
	public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ���ﳵ
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		//��ȡ�û�
		User loginUser = (User)session.getAttribute("loginUser");
		if(null == loginUser){//�û�δ��¼
			request.setAttribute("errorMsg", "���¼����й��ﳵ���");
			return "/jsp/login.jsp";
		}
		if(0 != cart.getMap().size()){//���ﳵ��Ϊ��
			//��װorder����
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
			//ҵ��㴦��,�Ѷ�����order���������itemOrder�洢�����ݿ���
			IOrderService service = new OrderServiceImp();
			service.save(order);
			//��չ��ﳵ
			cart.clearCart();
			//���order��������
			request.setAttribute("order", order);
			return "/jsp/order_info.jsp";
		}else{//���ﳵΪ��
			return "/jsp/order_info.jsp";
		}
	}
	
	//findOrderByOid,��order_list.jsp�ĸ�������ת��order_info.jsp
	public String findOrderByOid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oid = request.getParameter("oid");
		//����oid��ѯ���ݿ��е�orders��
		IOrderService service = new OrderServiceImp();
		Order order = service.findOrderByOid(oid);
		request.setAttribute("order", order);
		return "/jsp/order_info.jsp";
	}
	//myOrderWithPage
	public String myOrderWithPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		//��ʼ��pageModel,new PageModel(num, totalRecords, pageSize)
		String nm = request.getParameter("num");
		int num = 1;
		if(null != nm){
			num = Integer.parseInt(nm);
		}
		int pageSize = 3;
		
		IOrderService service = new OrderServiceImp();
		PageModel pageModel = service.getPageModel(num,pageSize,user.getUid());
		request.setAttribute("page", pageModel);
		
//		��ȡ���ݿ��е����ж���
//		List<Order> orderList = service.findOrderByUid(user.getUid());
//		request.setAttribute("orderList", orderList);
		
		return "/jsp/order_list.jsp";
	}
	public String payOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("111111111");
		//��ȡorder_info.jsp�û�ȷ�϶�����5������
		String oid = request.getParameter("oid");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");
		String pd_FrpId = request.getParameter("pd_FrpId");
		//����oid��ѯ���ݿ��е�orders��
		IOrderService service = new OrderServiceImp();
		Order order = service.findOrderByOid(oid);
		//���oid��Ӧ�������ջ���,�ջ���ַ,�ջ��绰�����ݿ�
		order.setName(name);
		order.setAddress(address);
		order.setTelephone(telephone);
		service.updateOrder(order);
		
		//�������ʹ���ױ�֧���ٷ�����demo�ĵ��е�����
		
		//����testPay��Ŀ�е�payServlet
		// �Ѹ�������Ҫ�Ĳ���׼����:
		String p0_Cmd = "Buy";
		//�̻����,����Ա�Լ�����
		String p1_MerId = "10001126856";
		//�������,����order_info.jsp�н��յ��Ķ������,����Ա�Լ�����
		String p2_Order = oid;
		//���,p3_Amt��֧��0.01Ԫ,��ʱ�����Ա�Լ�����
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		//������Ӧ������Servlet,����Ա�Լ�����
		String p8_Url = "http://localhost:8080/Day53/OrderServlet?method=callBack";
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		//��˾����Կ
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
			
		//�����ױ��ļ����㷨,���������ݽ��м���,���ص���ǩ��
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
		// ʹ���ض��򣺶����ױ�֧������
		response.sendRedirect(sb.toString());

		return null;
	}
	public String callBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��֤������Դ��������Ч��
		// �Ķ�֧���������˵��
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
		// ���ñ�����Կ�ͼ����㷨 ��������
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		if (isValid) {
			// ��Ч
			if (r9_BType.equals("1")) {
				// ������ض���
				response.setContentType("text/html;charset=utf-8");
				//����Ա�Լ�����
				//response.getWriter().println("֧���ɹ��������ţ�" + r6_Order + "��" + r3_Amt);
				IOrderService service = new OrderServiceImp();
				Order order = service.findOrderByOid(r6_Order);
				order.setState(2);
				service.updateOrder(order);
				request.setAttribute("msg", "֧���ɹ��������ţ�" + r6_Order + "��" + r3_Amt);
				return "/jsp/info.jsp";
				
			} else if (r9_BType.equals("2")) {
				// �޸Ķ���״̬:
				// ��������Ե㣬�������ױ���֪ͨ
				System.out.println("�յ��ױ�֪ͨ���޸Ķ���״̬��");//
				// �ظ����ױ�success��������ظ����ױ���һֱ֪ͨ
				response.getWriter().print("success");
			}

		} else {
//			throw new RuntimeException("���ݱ��۸ģ�");
			request.setAttribute("msg", "���ݱ��۸�");
			return "/jsp/info.jsp";
		}
		return null;
	}
}