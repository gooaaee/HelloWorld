package com.itheima.domain;

public class CartItem {
	private Product product;//携带了商品的信息,包括商品图片路径,商品名称,商品价格
	private int num;//用户购买的该个商品的个数
	private double subTotal;//小计
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getSubTotal() {
		return num*product.getShop_price();
	}
}
