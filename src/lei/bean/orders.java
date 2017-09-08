package lei.bean;

import java.io.Serializable;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;


public class orders implements Serializable{

	private static final long serialVersionUID = 1L;
	private  int ordersId;
	private  int usersId;
	private double ordersPrice;
	private String ordersDatetime;
	private int  ordersCon;
	
	
	public orders() {
		super();
	}
	public orders(int ordersId, int usersId, double ordersPrice, String  ordersDatetime, int ordersCon) {
		super();
		this.ordersId = ordersId;
		this.usersId = usersId;
		this.ordersPrice = ordersPrice;
		this.ordersDatetime = ordersDatetime;
		this.ordersCon = ordersCon;
	}
	public int getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(int ordersId) {
		this.ordersId = ordersId;
	}
	public int getUsersId() {
		return usersId;
	}
	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}
	public double getOrdersPrice() {
		return ordersPrice;
	}
	public void setOrdersPrice(double ordersPrice) {
		this.ordersPrice = ordersPrice;
	}
	public String getOrdersDatetime() {
		//DateFormat df = new SimpleDateFormat(
				//"yyyy-MM-dd hh:mm:ss");
		//System.out.println(df.format(ordersDatetime));
		return  ordersDatetime;//df.format(ordersDatetime);
	}
	public void setOrdersDatetime(String ordersDatetime) {
		this.ordersDatetime = ordersDatetime;
	}
	public int getOrdersCon() {
		return ordersCon;
	}
	public void setOrdersCon(int ordersCon) {
		this.ordersCon = ordersCon;
	}
	
		

	

}
