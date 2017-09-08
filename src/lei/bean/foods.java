package lei.bean;

import java.io.Serializable;

public class foods  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private int  foodsId;
	private String foodsImage;
	private String foodsName;
	private double foodsPrice;
	private int  foodsHabitus;
	private String foodsRemark;
	private int  sortId;
	public foods(int foodsId, String foodsImage,String foodsName, double foodsPrice, int foodsHabitus, String foodsRemark, int sortId) {
		super();
		this.foodsId = foodsId;
		this.foodsImage=foodsImage;
		this.foodsName = foodsName;
		this.foodsPrice = foodsPrice;
		this.foodsHabitus = foodsHabitus;
		this.foodsRemark = foodsRemark;
		this.sortId = sortId;
	}
	public String getFoodsImage() {
		return foodsImage;
	}
	public void setFoodsImage(String foodsImage) {
		this.foodsImage = foodsImage;
	}
	public foods() {
		super();
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
	

}
