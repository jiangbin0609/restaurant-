package com.newer.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.relation.Relation;

import com.newer.bean.Foods;
import com.newer.bean.Order;
import com.newer.bean.OrderItem;
import com.newer.bean.User;
import com.newer.util.DBUtil;

public class OrderDao implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Connection conn;
	private static PreparedStatement pre;
	private static ResultSet re;

	/**
	 * 获取序列值
	 * 
	 * @return
	 */
	public int getSequenceValue() {
		conn = DBUtil.getConnection();
		int value = 0;
		String sql = "select seq_ordersId.Nextval from dual";
		try {
			pre = conn.prepareStatement(sql);
			re = pre.executeQuery();
			if (re.next()) {
				value = re.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	public  int getSequenceValue1() {
		conn = DBUtil.getConnection();
		int value = 0;
		String sql = "select seq_orderDetailsId.Nextval from dual";
		try {
			pre = conn.prepareStatement(sql);
			re = pre.executeQuery();
			if (re.next()) {
				value = re.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(conn, pre, re);
		}
		return value;
	}

	/**
	 * 添加订单
	 * 
	 * @param order
	 */
	public void addOrder(Order order) {
		conn = DBUtil.getConnection();
		String sql = "insert into orders1 values (?,?,?,to_date(?,'yyyy/mm/dd hh24:mi:ss'),?)";

		try {

			pre = conn.prepareStatement(sql);
			pre.setInt(1, order.getOrdersId());
			pre.setInt(2, order.getUser().getUsersId());
			pre.setDouble(3, order.getTotal());
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			String time=sdf.format(order.getOrdersDateTime());
			
			pre.setString(4, time);
			pre.setInt(5, order.getOrdersCon());
			pre.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(conn, pre, re);
		}

	}

	/**
	 * 向订单明细表中天添加数据记录
	 * 
	 * @param orderItemList
	 */
	public void addOrderItemList(List<OrderItem> orderItemList) {
		conn = DBUtil.getConnection();
		String sql = "insert into orderDetails values(?,?,?,?,?,?)";
		try {
			for (OrderItem item : orderItemList) {
				pre = conn.prepareStatement(sql);
				
				pre.setInt(1, item.getIid());
				pre.setInt(2, item.getOrder().getOrdersId());
				pre.setInt(3, item.getFood().getFoodsId());
				pre.setString(4, item.getFood().getFoodsName());
				pre.setDouble(5,item.getFood().getFoodsPrice());
				pre.setInt(6, item.getCount());
				pre.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(conn, pre, re);
		}

	}

	/**
	 * 根据用户id查询订单
	 * 
	 * @param uid
	 * @return
	 */
	public List<Order> findByUid(int uid) {
		conn = DBUtil.getConnection();
		List<Order> list = new ArrayList<Order>();
		UserDao dao = new UserDao();
		String sql = "select * from orders1 where usersid=?";

		try {
			pre = conn.prepareStatement(sql);
			pre.setInt(1, uid);
			re = pre.executeQuery();
			while (re.next()) {
				Order order = new Order();
				order.setOrdersId(re.getInt(1));
				order.setUser(dao.finaById(re.getInt(2)));
				order.setTotal(re.getDouble(3));
				order.setOrdersDateTime(re.getDate(4));
				order.setOrdersCon(re.getInt(5));

				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(conn, pre, re);
		}
		return list;

	}

	/**
	 * 根据定单号查询订单
	 * 
	 * @param idm
	 * @return
	 */
	public Order findByOrderId(int id) {
		conn = DBUtil.getConnection();
		UserDao dao = new UserDao();
		Order order = null;
		String sql = "select ordersID,usersid,ordersprice,"
				+ "to_char(ordersdatetime,'yyyy-mm-dd hh:MM:ss'),orderscon from orders1 where ordersid=?";
		try {
			pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			re = pre.executeQuery();
			if (re.next()) {
				order = new Order();
				order.setOrdersId(re.getInt(1));
				order.setUser(dao.finaById(re.getInt(2)));
		
				order.setTotal(re.getDouble(3));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date =sdf.parse(re.getString(4));
				
				order.setOrdersDateTime(date);
				order.setOrdersCon(re.getInt(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch blockM
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(conn, pre, re);
		}
		return order;

	}

	
	/**
	 * 获取用户所有的订单
	 * @param userId
	 * @return
	 */
	public List<Order> loadOrders(User user) {
		Connection conn1 = DBUtil.getConnection();
		PreparedStatement pre1=null;
		ResultSet re1=null;
		
		List<Order> orders=new ArrayList<Order>();
		UserDao dao =new UserDao();
		
		String sql="select * from orders1 where usersid=? and orderscon not in (5)";
		try {
			pre1=conn1.prepareStatement(sql);
			pre1.setInt(1,user.getUsersId());
			re1=pre1.executeQuery();
			
			while(re1.next()){
				Order order=new Order();
				int orderId=re1.getInt(1);
				
				order.setOrdersId(orderId);
				order.setUser(user);
				order.setTotal(re1.getDouble(3));

				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date =sdf.parse(re1.getString(4));
				order.setOrdersDateTime(date);
				order.setOrdersCon(re1.getInt(5));
				order.setOrderItemList(loadOrderItems(orderId));
				
				orders.add(order);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(conn1, pre1, re1);
		}
		return orders;
		
	}
	
	
	/**
	 * 获取单一订单中所有的菜品信息
	 * @param ordersId
	 * @return
	 */
	public List<OrderItem> loadOrderItems(int orderId){
		
		Connection conn2=DBUtil.getConnection();
		ResultSet res2=null;
		PreparedStatement  pst2=null;
		
		FoodsDao foodsDao=new FoodsDao();
		List<OrderItem> orderItemList=new ArrayList<OrderItem>();
		
		String sql1="select * from orderDetails where ordersId=?";
		
		try {
			pst2=conn2.prepareStatement(sql1);
			pst2.setInt(1, orderId);
			res2=pst2.executeQuery();
			while (res2.next()) {
				OrderItem item=new OrderItem();
				item.setIid(res2.getInt(1));
				item.setOrder(findByOrderId(res2.getInt(2)));
				
				Foods food=foodsDao.findById(res2.getInt(3));
				item.setFood(food);
				
				int count = res2.getInt(6); 
				item.setCount(count);
				
				double total=food.getFoodsPrice()*count;
				item.setSubtotal(total);
				
				orderItemList.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(conn2, pst2, res2);
		}
		return orderItemList;
	}
	/**
	 * 查询订单状态
	 * @param orderId
	 * @return
	 */
	public int  findOrdersCon(int orderId){
		conn=DBUtil.getConnection();
		int ordersCon=-1;
		String sql="select orderscon from orders1 where ordersid=?";
		try {
			pre=conn.prepareStatement(sql);
			pre.setInt(1, orderId);
			re=pre.executeQuery();
			if(re.next()){
				ordersCon=re.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ordersCon;
	}
	/**
	 * 修改订单状态
	 * @param orderId
	 * @param orderCon
	 */
	public boolean updateOrderCon(int orderId,int orderCon){
		boolean flag=false;
		conn=DBUtil.getConnection();
		String sql="update orders1 set orderscon=? where ordersid=?";
		try {
			pre=conn.prepareStatement(sql);
			
			pre.setInt(1, orderCon);
			pre.setInt(2, orderId);
			if(pre.executeUpdate()>0){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(conn, pre, re);
		}
		return flag;
	}

}
