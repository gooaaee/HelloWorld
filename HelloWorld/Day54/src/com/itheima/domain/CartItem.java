package com.itheima.domain;

public class CartItem {
	private Product product;//Я������Ʒ����Ϣ,������ƷͼƬ·��,��Ʒ����,��Ʒ�۸�
	private int num;//�û�����ĸø���Ʒ�ĸ���
	private double subTotal;//С��
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
