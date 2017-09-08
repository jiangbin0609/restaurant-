package com.newer.bean;

import java.io.Serializable;

public class Foods  implements Serializable{

	private static final long serialVersionUID = 1L;
	//菜品id
	private int  foodsId;
	//菜品对象的图片名
	private String foodsImage;
	//菜品名
	private String foodsName;
	//菜品价格
	private double foodsPrice;
	//菜品状态(0代表下架，1代表上架销售)
	private int  foodsHabitus;
	//菜品简介
	private String foodsRemark;
	//菜品所属菜系的id
	private int  sortId;
	
	public Foods() {
		super();
	}
	
	public Foods(int foodsId, String foodsImage, String foodsName, double foodsPrice, int foodsHabitus,
			String foodsRemark, int sortId) {
		super();
		this.foodsId = foodsId;
		this.foodsImage = foodsImage;
		this.foodsName = foodsName;
		this.foodsPrice = foodsPrice;
		this.foodsHabitus = foodsHabitus;
		this.foodsRemark = foodsRemark;
		this.sortId = sortId;
	}

	public int getFoodsId() {
		return foodsId;
	}
	public void setFoodsId(int foodsId) {
		this.foodsId = foodsId;
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
	public void setFoodsPrice(double foodsPrice) {
		this.foodsPrice = foodsPrice;
	}
	public int getFoodsHabitus() {
		return foodsHabitus;
	}
	public void setFoodsHabitus(int foodsHabitus) {
		this.foodsHabitus = foodsHabitus;
	}
	public String getFoodsRemark() {
		return foodsRemark;
	}
	public void setFoodsRemark(String foodsRemark) {
		this.foodsRemark = foodsRemark;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
	
	public String getFoodsImage() {
		return foodsImage;
	}

	public void setFoodsImage(String foodsImage) {
		this.foodsImage = foodsImage;
	}

	@Override
	public String toString() {
		return "foods [foodsId=" + foodsId + ", foodsName=" + foodsName + ", foodsPrice=" + foodsPrice
				+ ", foodsHabitus=" + foodsHabitus + ", foodsRemark=" + foodsRemark + ", sortId=" + sortId + "]";
	}

}
