package com.itheima.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
-- 4 创建订单表
CREATE TABLE `orders` (
  `oid` varchar(32) NOT NULL,
  `ordertime` datetime DEFAULT NULL,		#下单时间
  `total` double DEFAULT NULL,				#总价
  `state` int(11) DEFAULT NULL,				#订单状态：1=未付款;2=已付款,未发货;3=已发货,没收货;4=收货,订单结束
  `address` varchar(30) DEFAULT NULL,		#收获地址
  `name` varchar(20) DEFAULT NULL,			#收获人
  `telephone` varchar(20) DEFAULT NULL,		#收货人电话
  `uid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `order_fk_0001` (`uid`),
  CONSTRAINT `order_fk_0001` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ;
 */
public class Order {
	private String oid;//订单id
	private Date orderTime;//下单时间
	private double total;//总价
	private int state;//订单状态,1:未付款,2:已付款,未发货,3:已发货,没收货,4:收货,订单结束
	private String address;//收货地址
	private String name;//收货人
	private String telephone;//收货人电话
	private User user;//uid
	
	private List<OrderItem> list = new ArrayList<OrderItem>();//该订单中所有订单项
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
