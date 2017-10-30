package com.itheima.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
-- 4 ����������
CREATE TABLE `orders` (
  `oid` varchar(32) NOT NULL,
  `ordertime` datetime DEFAULT NULL,		#�µ�ʱ��
  `total` double DEFAULT NULL,				#�ܼ�
  `state` int(11) DEFAULT NULL,				#����״̬��1=δ����;2=�Ѹ���,δ����;3=�ѷ���,û�ջ�;4=�ջ�,��������
  `address` varchar(30) DEFAULT NULL,		#�ջ��ַ
  `name` varchar(20) DEFAULT NULL,			#�ջ���
  `telephone` varchar(20) DEFAULT NULL,		#�ջ��˵绰
  `uid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `order_fk_0001` (`uid`),
  CONSTRAINT `order_fk_0001` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ;
 */
public class Order {
	private String oid;//����id
	private Date orderTime;//�µ�ʱ��
	private double total;//�ܼ�
	private int state;//����״̬,1:δ����,2:�Ѹ���,δ����,3:�ѷ���,û�ջ�,4:�ջ�,��������
	private String address;//�ջ���ַ
	private String name;//�ջ���
	private String telephone;//�ջ��˵绰
	private User user;//uid
	
	private List<OrderItem> list = new ArrayList<OrderItem>();//�ö��������ж�����
	public List<OrderItem> getList() {
		return list;
	}
	public void setList(List<OrderItem> list) {
		this.list = list;
	}
	
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", orderTime=" + orderTime + ", total=" + total + ", state=" + state + ", address="
				+ address + ", name=" + name + ", telephone=" + telephone + ", user=" + user + ", list=" + list + "]";
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
