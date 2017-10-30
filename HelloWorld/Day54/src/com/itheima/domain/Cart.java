package com.itheima.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	//包含购物车中的所有购物项
	//包含商品总额
	//包含赠送积分
	//map中是<pid,cartitem>
	private Map<String,CartItem> map = new HashMap<String,CartItem>();//包含所有的购物项
	private double total;//总额
	//增加一个商品到购物车的功能
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
	//清空购物车方法
	public void clearCart(){
		map.clear();
	}
	//删除购物车一项的方法
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
