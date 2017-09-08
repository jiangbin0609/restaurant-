package lei.bean;

import java.io.Serializable;

public class orderDetails implements Serializable{

	private static final long serialVersionUID = 1L;
	private int orderDetailsId;
	private int  ordersId;	
	private int  foodsId;	
	private String  foodsName;
	private double  foodsPrice;
	private int foodsCount;
	public orderDetails(int orderDetailsId, int ordersId, int foodsId,String foodsName, double foodsPrice, int foodsCount) {
		super();
		this.orderDetailsId = orderDetailsId;
		this.ordersId = ordersId;
		this.foodsId=foodsId;
		this.foodsName = foodsName;
		this.foodsPrice = foodsPrice;
		this.foodsCount = foodsCount;
	}
	public int getFoodsId() {
		return foodsId;
	}
	public void setFoodsId(int foodsId) {
		this.foodsId = foodsId;
	}
	public orderDetails() {
		super();
	}
	public int getOrderDetailsId() {
		return orderDetailsId;
	}
	public void setOrderDetailsId(int orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}
	public int getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(int ordersId) {
		this.ordersId = ordersId;
	}
	public String getFoodsName() {
		return foodsName;
	}
	public void setFoodsName(String foodsName) {
		this.foodsName = foodsName;
	}
	public double getFoodsPrice() {
		return foodsPrice;
	}
	public void setFoodsPrcie(double foodsPrice) {
		this.foodsPrice = foodsPrice;
	}
	public int getFoodsCount() {
		return foodsCount;
	}
	public void setFoodsCount(int foodsCount) {
		this.foodsCount = foodsCount;
	}
	
		


}
