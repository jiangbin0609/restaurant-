package com.newer.bean;

public class OrderItem {
	private int iid;
	private int count;// 数量
	private double subtotal;// 单个购买项的总价钱
	private Order order;// 所属订单
	private Foods food;// 所购买的菜品	
	
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItem(int iid, int count, double subtotal, Order order, Foods food) {
		super();
		this.iid = iid;
		this.count = count;
		this.subtotal = subtotal;
		this.order = order;
		this.food = food;
	}

	public int getIid() {
		return iid;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Foods getFood() {
		return food;
	}

	public void setFood(Foods food) {
		this.food = food;
	}

	
	
}
