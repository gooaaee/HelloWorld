package com.itheima.dao.daoImp;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.dao.IOrderDao;
import com.itheima.domain.Order;
import com.itheima.domain.OrderItem;
import com.itheima.domain.PageModel;
import com.itheima.domain.Product;
import com.itheima.domain.User;
import com.itheima.tools.JDBCUtils;

public class OrderDaoImp implements IOrderDao {
	//���¶���
	@Override
	public void updateOrder(Order order) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "UPDATE orders SET ordertime=?,total=?,state=?,address=?,name=?,telephone=?,uid=? where oid=?";
		Object[] params = {order.getOrderTime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid(),order.getOid()};
		qr.update(sql, params);
	}

	//�洢order������orderItem�������,ע���ѿ�������
	@Override
	public void save(Order order) throws SQLException {
		QueryRunner qr = new QueryRunner();
		//��orders��洢����
		String sql = "insert into orders(oid,ordertime,total,state,uid) "
				+ " values(?,?,?,?,?)";
		Object[] params = {order.getOid(),order.getOrderTime(),order.getTotal(),order.getState(),order.getUser().getUid()};
		qr.update(JDBCUtils.getConnection(), sql, params);
		
		for(OrderItem orderItem:order.getList()){
			sql = "insert into orderitem(itemid,quantity,total,pid,oid) values(?,?,?,?,?)";
			Object[] params02 = {orderItem.getItemid(),orderItem.getQuantity(),orderItem.getTotal(),
					orderItem.getProduct().getPid(),orderItem.getOrder().getOid()};
			qr.update(JDBCUtils.getConnection(), sql, params02);
		}
	}

	@Override
	public Order findOrderByOid(String oid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from orders where oid = ?";
		Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
		//��װuser����
		sql = "select uid from orders where oid = ?";
		Object[] uidArr = qr.query(sql, new ArrayHandler(), oid);
		String uid = (String)uidArr[0];
		sql = "select * from user where uid = ?";
		User user = qr.query(sql, new BeanHandler<User>(User.class), uid);
		order.setUser(user);
		//��װList<OrderItem>
		List<OrderItem> list = getOrderItemsByOid(oid);
		for(OrderItem item : list){
			item.setOrder(order);
		}
		order.setList(list);
		return order;
	}
	//�����û�uid��ѯ�û����еĶ���,����ѯ�����еĶ���oidʱ����ʹ��findOrderByOid����
	@Override
	public List<Order> findOrderByUid(String uid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from orders where uid = ?";
		List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class), uid);
		List<Order> orderList02 = new ArrayList<Order>();
		for(Order order: orderList){
			String oid = order.getOid();
			Order order02 = findOrderByOid(oid);
			orderList02.add(order02);
		}
		return orderList02;
	}
	
	//ͨ��oid��ѯorderitem��,��ȡ�����Ĺ�������
	public List<OrderItem> getOrderItemsByOid(String oid) throws SQLException{
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from orderitem o,product p where o.pid=p.pid and oid=?";
		List<Map<String, Object>> tmpList = qr.query(sql, new MapListHandler(), oid);
		//��ʼ��list<OrderItem>����
		List<OrderItem> list = new ArrayList<OrderItem>();
		//new MapListHandler()��װ����������ѯ���ı�,��ʾ������,������¼���м���map,[map01,map02,map03,...],
		//ÿ��map<String,Object>����{itemid='15AE2433260E45CAA90B79037C6C619B',quantity=1,...�ֶ���=�ֶ�ֵ},
		//ʹ��beanutils��װ��¼,һ����¼����һ��javabean����,�������ı��а���orderitem�����product����ļ�¼
		for(Map<String,Object> map : tmpList){
			OrderItem orderItem = new OrderItem();
			Product product = new Product();
			try {
				BeanUtils.populate(orderItem, map);
				BeanUtils.populate(product, map);
				orderItem.setProduct(product);
				list.add(orderItem);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//��ȡ������ȫ����¼����
	@Override
	public int getTotalRecords(String uid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select count(*) from orders where uid=?";
		Long uidLong = (Long)qr.query(sql, new ScalarHandler(),uid);
		return uidLong.intValue();
	}
	//���ҵĶ�����ҳ
	@Override
	public List<Order> findOrderByUidWithPage(PageModel pageModel,String uid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		//SELECT * FROM orders WHERE uid = 'f55b7d3a352a4f0782c910b2c70f1ea4' LIMIT 0, 3
		String sql = "select * from orders where uid = ? limit ?, ?";
		Object[] params = {uid,pageModel.getStartIndex(),pageModel.getPageSize()};
		List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class), params);
		List<Order> orderList02 = new ArrayList<Order>();
		for(Order order: orderList){
			String oid = order.getOid();
			Order order02 = findOrderByOid(oid);
			orderList02.add(order02);
		}
		return orderList02;
	}
	
}
