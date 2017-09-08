package com.newer.bean;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


public class Cart {
	private Map<Integer , CartItem> map=new LinkedHashMap<Integer , CartItem>();
	
	/**计算购物车中所有菜品的总价钱
	 * @return
	 */
	public double getTotal(){
		double total=0;
		for (CartItem cartItem:map.values()) {
			total+=cartItem.getSubtotal();
		}
		return total;
	}
	
	/**
	 * 添加菜品到购物车
	 * @param cartItem
	 */
	public void  add(CartItem cartItem){
		if(map.containsKey(cartItem.getFood().getFoodsId())){
			CartItem oldCartItem=map.get(cartItem.getFood().getFoodsId());
			oldCartItem.setCount(oldCartItem.getCount()+cartItem.getCount());
			
		}else{
			map.put(cartItem.getFood().getFoodsId(), cartItem);
		
		}
		
	}
	
	/**
	 * 清空购物车
	 */
	public void clear(){
		map.clear();
		
	}
	
	/**
	 *删除购物车中指定菜品
	 * @param fid
	 */
	public void delete(int fid ){
		map.remove(fid);
	}
	
	/**
	 * 获取购物车中所有菜品信息
	 * @return
	 */
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
}
