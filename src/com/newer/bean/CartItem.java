package com.newer.bean;

public class CartItem {
	private Foods food;// 菜品
	private int count;// 下单数量
	
	
	/**
	 * 计算菜品的小计
	 * @return
	 */
	public double getSubtotal(){
		return food.getFoodsPrice()*count;
	}

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItem(Foods food, int count) {
		super();
		this.food = food;
		this.count = count;
	}

	public Foods getFood() {
		return food;
	}

	public void setFood(Foods food) {
		this.food = food;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "CartItem [food=" + food + ", count=" + count + "]";
	}

}
