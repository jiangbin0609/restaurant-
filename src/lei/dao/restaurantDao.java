package lei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.newer.util.DBUtil;

import lei.bean.foods;



public class restaurantDao {
private static Connection con;
private static  PreparedStatement pst;
private static  ResultSet rs;
//添加菜品
public static boolean add(foods food) {
	boolean falg=false;
	con=DBUtil.getConnection();
    String  sql="insert into foods values(seq_foodsId.nextval,?,?,?,?,?,?)";
    try {
		pst=con.prepareStatement(sql);
		pst.setString(1, food.getFoodsName());
		pst.setDouble(2, food.getFoodsPrice());
		pst.setString(3, food.getFoodsImage());
		pst.setInt(4, food.getFoodsHabitus());
		pst.setString(5, food.getFoodsRemark());
		pst.setInt(6, food.getSortId());
		int t=pst.executeUpdate();
		if (t>0) {
			falg=true;
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{DBUtil.closeAll(con, pst, null);}
	return falg;
	
}
//修改菜品信息
public static boolean update( foods food) {
	boolean falg=false;	
	con=DBUtil.getConnection();
	String sql="update foods set foodsName=?,foodsImage=?,foodsPrice=?,"
			+ "foodsHabitus=?,foodsRemark=?,sortId=? where foodsId=?";
	try {
		pst=con.prepareStatement(sql);
		pst.setString(1, food.getFoodsName());
		pst.setString(2, food.getFoodsImage());
		pst.setDouble(3, food.getFoodsPrice());
		pst.setInt(4, food.getFoodsHabitus());
		pst.setString(5, food.getFoodsRemark());
		pst.setInt(6, food.getSortId());
		pst.setInt(7, food.getFoodsId());
		int t=pst.executeUpdate();
		if (t>0) {
			falg=true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{DBUtil.closeAll(con, pst, null);}
	
	return falg;
}

//按ID单个查询
public static foods findSingle(int foodsId) {
	foods food=null;
	con=DBUtil.getConnection();
	String sql="select * from foods where foodsId=?";
	try {
		pst=con.prepareStatement(sql);
		pst.setInt(1, foodsId);
		rs=pst.executeQuery();
		if (rs.next()) {
			food=new foods(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getDouble(4),
					rs.getInt(5),rs.getString(6),rs.getInt(7));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{DBUtil.closeAll(con, pst, rs);}
	
	return food;
}
//按名字模糊查询
public  List<foods> findName(String foodsName) {
	List<foods> list=new  ArrayList<foods>();
	con=DBUtil.getConnection();
	String sql="select * from foods where foodsName like '%"+foodsName+"%'";
	try {
		pst=con.prepareStatement(sql);
		//pst.setString(1, foodsName);
		rs=pst.executeQuery();
		while(rs.next()) {
		foods food=new foods(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getDouble(4),
					rs.getInt(5),rs.getString(6),rs.getInt(7));
		list.add(food);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{DBUtil.closeAll(con, pst, rs);}
	
	return list;
}
//查询所有菜品

public static List<foods> findAll() {
	List<foods> list=new  ArrayList<foods>();
	con=DBUtil.getConnection();
	String sql="select * from foods";
	try {
		pst=con.prepareStatement(sql);

		rs=pst.executeQuery();
		while(rs.next()) {
		foods food=new foods(rs.getInt(1),rs.getString(2) , rs.getString(3),rs.getDouble(4),
					rs.getInt(5),rs.getString(6),rs.getInt(7));
		list.add(food);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{DBUtil.closeAll(con, pst, rs);}
	
	return list;
}

/*
 * 分页查询单页菜品信息
 * @pageNo:当前页号
 * @pageSize:页大小（每页记录数）
 */
public List<foods> findByPage(int pageNo,int pageSize){
	List<foods> list=new ArrayList<foods>();
	
	String sql="select * from (select p.*,row_number() "
			+ "over (order by foodsId) rn from foods p ) "
			+ "where rn between ? and ?";	
	
	
	try {
		con=DBUtil.getConnection();
		pst=con.prepareStatement(sql);
		int start=(pageNo-1)*pageSize+1;//起始行号
		int end=pageNo*pageSize;//结束行号
		
		pst.setInt(1, start);
		pst.setInt(2, end);
		
		rs=pst.executeQuery();
		
		while(rs.next()){
			foods food=new foods(rs.getInt(1),rs.getString(2),  rs.getString(3),rs.getDouble(4),
					rs.getInt(5),rs.getString(6),rs.getInt(7));
			
			list.add(food);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		DBUtil.closeAll(con, pst, rs);
	}
	
	return list;
}


/*
 * 查询总记录条数
 */
public int getCount(){
	int count=0;
	String sql="select count(foodsId) from foods";
	
	try {
		con=DBUtil.getConnection();
		pst=con.prepareStatement(sql);
		
		rs=pst.executeQuery();
		
		if(rs.next()){
			count=rs.getInt(1);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		DBUtil.closeAll(con, pst, rs);
	}
	return count;
}
/*public static void main(String[] args) {
	foods food=new foods(4,"剁椒鱼头",189.0,1,"菜品色泽红亮、味浓、肉质细嫩。肥而不腻、口感软糯、鲜辣适口",1);
	//System.out.println(add(food));
	//System.out.println(update(food));
	//System.out.println(findSingle(1).toString());
	System.out.println(findName("东安").toString()); 
	System.out.println(findAll().toString()); 
	
 }*/
}
