package com.itheima.service.serviceImp;

import java.sql.SQLException;
import java.util.List;

import com.itheima.dao.IOrderDao;
import com.itheima.dao.daoImp.OrderDaoImp;
import com.itheima.domain.Order;
import com.itheima.domain.PageModel;
import com.itheima.service.IOrderService;
import com.itheima.tools.JDBCUtils;

public class OrderServiceImp implements IOrderService {
	//���¶���
	@Override
	public void updateOrder(Order order) {
		IOrderDao dao = new OrderDaoImp();
		try {
			dao.updateOrder(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//�洢order��itemOrder
	@Override
	public void save(Order order) {
		//���ڼ�Ҫ�洢order,��Ҫ�洢orderItem,����Ҫ��������,��֤�洢���̵�ԭ����
		try {
			//�ر��Զ��ύ,��������
			JDBCUtils.startTransaction();
			//dao�㴦��
			IOrderDao dao = new OrderDaoImp();
			dao.save(order);
			//�������,�ύ����
			JDBCUtils.commitAndClose();
		} catch (SQLException e) {
			//�����ж�,�ع�����
			JDBCUtils.rollbackAndClose();
			e.printStackTrace();
		}
	}
	//��ѯorder
	@Override
	public Order findOrderByOid(String oid) {
		IOrderDao dao = new OrderDaoImp();
		Order order = null;
		try {
			order = dao.findOrderByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}
	@Override
	public List<Order> findOrderByUid(String uid) {
		IOrderDao dao = new OrderDaoImp();
		List<Order> list = null;
		try {
			list = dao.findOrderByUid(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//��ȡpageModel����,new PageModel(num, totalRecords, pageSize)
	@Override
	public PageModel getPageModel(int num, int pageSize,String uid) {
		IOrderDao dao = new OrderDaoImp();
		PageModel pageModel = null;
		try {
			int totalRecords = dao.getTotalRecords(uid);
			pageModel = new PageModel(num,totalRecords,pageSize);
			List<Order> list = dao.findOrderByUidWithPage(pageModel,uid);
			pageModel.setRecords(list);
			String url = "OrderServlet?method=myOrderWithPage";
			pageModel.setUrl(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageModel;
	}

}
