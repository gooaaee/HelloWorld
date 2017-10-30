package com.itheima.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	//�������ﳵ�е����й�����
	//������Ʒ�ܶ�
	//�������ͻ���
	//map����<pid,cartitem>
	private Map<String,CartItem> map = new HashMap<String,CartItem>();//�������еĹ�����
	private double total;//�ܶ�
	//����һ����Ʒ�����ﳵ�Ĺ���
	public void addCartItem(CartItem item){
		String pid = item.getProduct().getPid();
		if(map.containsKey(pid)){
			for(String key : map.keySet()){
				if(key.equals(pid)){
					CartItem value = map.get(key);
					value.setNum(value.getNum()+item.getNum());
				}
			}
		}else{
			map.put(pid, item);
		}
	}
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	//��չ��ﳵ����
	public void clearCart(){
		map.clear();
	}
	//ɾ�����ﳵһ��ķ���
	public void removeCart(String pid){
		map.remove(pid);
	}
	public double getTotal(){
		total = 0;
		Collection<CartItem> values = map.values();
		for(CartItem item : values){
			total += item.getSubTotal();
		}
		return total;
	}
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
}
