package com.newer.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	// 订单id
	private int ordersId;
	// 订单生成时间
	private Date ordersDateTime;
	// 总价钱
	private double total;
	// 订单的状态(1未付款 ,2已付款但未发货 ,3已发货但未确认收货, 4已确认交易成功)
	private int ordersCon;
	// 订单所有者
	private User user;
	// 订单中所购买的所有菜品
	private List<OrderItem> orderItemList;

	public Order() {
		super();
	}

	public Order(int ordersId, Date  ordersDateTime, double total, int ordersCon, User user,
			List<OrderItem> orderItemList) {
		super();
		this.ordersId = ordersId;
		this.ordersDateTime = ordersDateTime;
		this.total = total;
		this.ordersCon = ordersCon;
		this.user = user;
		this.orderItemList = orderItemList;
	}

	public int getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(int ordersId) {
		this.ordersId = ordersId;
	}

	public Date getOrdersDateTime() {
		return ordersDateTime;
	}

	public void setOrdersDateTime(Date ordersDateTime) {
		this.ordersDateTime = ordersDateTime;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getOrdersCon() {
		return ordersCon;
	}

	public void setOrdersCon(int ordersCon) {
		this.ordersCon = ordersCon;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}


}
