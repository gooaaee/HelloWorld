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
	//更新订单
	@Override
	public void updateOrder(Order order) {
		IOrderDao dao = new OrderDaoImp();
		try {
			dao.updateOrder(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//存储order及itemOrder
	@Override
	public void save(Order order) {
		//由于既要存储order,还要存储orderItem,所以要开启事务,保证存储过程的原子性
		try {
			//关闭自动提交,开启事务
			JDBCUtils.startTransaction();
			//dao层处理
			IOrderDao dao = new OrderDaoImp();
			dao.save(order);
			//事务完成,提交事务
			JDBCUtils.commitAndClose();
		} catch (SQLException e) {
			//事务中断,回滚事务
			JDBCUtils.rollbackAndClose();
			e.printStackTrace();
		}
	}
	//查询order
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
	//获取pageModel对象,new PageModel(num, totalRecords, pageSize)
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
