package com.itheima.domain;
/*
-- 5 �����������
CREATE TABLE `orderitem` (
  `itemid` varchar(32) NOT NULL,
  `quantity` int(11) DEFAULT NULL,			#��������
  `total` double DEFAULT NULL,			#С��
  `pid` varchar(32) DEFAULT NULL,		#������Ʒ��id
  `oid` varchar(32) DEFAULT NULL,		#���������ڶ���id
  PRIMARY KEY (`itemid`),
  KEY `order_item_fk_0001` (`pid`),
  KEY `order_item_fk_0002` (`oid`),
  CONSTRAINT `order_item_fk_0001` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`),
  CONSTRAINT `order_item_fk_0002` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`)
) ;
 */
public class OrderItem {
	private String itemid;//������id
	private int quantity;//����
	private double total;//С��
	private Product product;//pid
	private Order order;//oid

	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "OrderItem [itemId=" + itemid + ", quantity=" + quantity + ", total=" + total + ", product=" + product
				+ ", order=" + order + "]";
	}
	
}
