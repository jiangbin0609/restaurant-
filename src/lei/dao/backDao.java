package lei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.newer.util.DBUtil;

import lei.bean.*;



public class backDao {
private static Connection con;
private static PreparedStatement pst;
private static ResultSet rs;
//检查管理员登陆并拿到返回值
public admin landing(String str1,String str2) {
	admin ad=null;
	con=DBUtil.getConnection();
	String sql="select * from admin1 where adminName=? and adminPass=?";
	try {
		pst=con.prepareStatement(sql);
		pst.setString(1, str1);
		pst.setString(2, str2);
		rs=pst.executeQuery();
		if(rs.next()) {
			ad=new admin(rs.getInt(1),rs.getString(2),rs.getString(3));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{DBUtil.closeAll(con, pst, rs);}
	
	return ad;
}
//验证管理员旧密码
public boolean yanzheng(String oldPass, int id) {
	boolean falg=false;
	con =DBUtil.getConnection();
	String sql="select * from admin1 where adminPass=? and adminId=?";
	try {
		pst=con.prepareStatement(sql);
		pst.setString(1, oldPass);
		pst.setInt(2, id);
		rs=pst.executeQuery();
		if (rs.next()) {
			falg=true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.closeAll(con, pst, rs);
	}
	
	return falg;
}
//管理员修改密码

public boolean  updatePass(String pass,int id) {
	boolean falg=false;
	con=DBUtil.getConnection();
	String sql="update admin1 set adminPass=? where adminId=?";
	try {
		pst=con.prepareStatement(sql);
		pst.setString(1, pass);
		pst.setInt(2, id);
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
//添加菜品
public boolean foodsAdd(foods food) {
	boolean falg=false;
	con=DBUtil.getConnection();
    String  sql="insert into foods values(seq_foodsId.nextval,?,?,?,?,?,?)";
    try {
		pst=con.prepareStatement(sql);
		pst.setString(1, food.getFoodsImage());
		pst.setString(2, food.getFoodsName());
		pst.setDouble(3, food.getFoodsPrice());
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
public  boolean foodsUpd( foods food) {
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
//按名字查找菜品信息
public List<foods> findSingle(String foodsName) {
	List<foods> list=new ArrayList<foods>();
	con=DBUtil.getConnection();
	String sql="select * from foods where foodsName=?";
	try {
		pst=con.prepareStatement(sql);
		pst.setString(1, foodsName);
		rs=pst.executeQuery();
		while (rs.next()) {
			foods ad=new foods(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getDouble(4),
					rs.getInt(5),rs.getString(6),rs.getInt(7));
			list.add(ad);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{DBUtil.closeAll(con, pst, rs);}
	
	return list;
}
//按菜品ID查找单个菜品信息
public foods findOne(int foodsId) {
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
//按菜系查询菜品
public List<foods> findSort(int sortId) {
 List<foods> list =new ArrayList<foods>();
	con=DBUtil.getConnection();
	String sql="select * from foods where sortId=?";
	try {
		pst=con.prepareStatement(sql);
		pst.setInt(1, sortId);
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
public List<foods> foodsAll() {
List<foods> list =new ArrayList<foods>();
	con=DBUtil.getConnection();
	String sql="select * from foods ";
	try {
		pst=con.prepareStatement(sql);
		
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
//上架菜品
public void shangjia(int foodsId) {
	con=DBUtil.getConnection();
	String sql="update foods set foodsHabitus=1 where foodsId=?";
	try {
		pst=con.prepareStatement(sql);
		pst.setInt(1, foodsId);
		pst.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.closeAll(con, pst, null);
	}
	
}
//下架菜品
public void xiajia(int foodsId) {
	con=DBUtil.getConnection();
	String sql="update foods set foodsHabitus=0 where foodsId=?";
	try {
		pst=con.prepareStatement(sql);
		pst.setInt(1, foodsId);
		pst.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.closeAll(con, pst, null);
	}
	
}
//查询所有用户
public List<User> findUserAll() {
	List<User> list = new ArrayList<User>();
	con = DBUtil.getConnection();
	String sql = "select * from users1";
	try {
		pst = con.prepareStatement(sql);
		rs = pst.executeQuery();
		while (rs.next()) {
			User user = new User();
			user.setUsersId(rs.getInt(1));
			user.setUsersName(rs.getString(2));
			user.setUsersPass(rs.getString(3));
			user.setUsersTrueName(rs.getString(4));
			user.setUsersAddress(rs.getString(5));
			user.setUsersPhone(rs.getString(6));
			user.setUsersEmail(rs.getString(7));
			user.setUserCon(rs.getInt(8));

			list.add(user);
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		DBUtil.closeAll(con, pst, rs);
	}

	return list;
}
//查询被屏蔽的用户
public List<User>  shield() {
	List<User> list = new ArrayList<User>();
	con = DBUtil.getConnection();
	String sql = "select * from users1 where usersCon=0";
	try {
		pst = con.prepareStatement(sql);
		rs = pst.executeQuery();
		while (rs.next()) {
			User user = new User();
			user.setUsersId(rs.getInt(1));
			user.setUsersName(rs.getString(2));
			user.setUsersPass(rs.getString(3));
			user.setUsersTrueName(rs.getString(4));
			user.setUsersAddress(rs.getString(5));
			user.setUsersPhone(rs.getString(6));
			user.setUsersEmail(rs.getString(7));
			user.setUserCon(rs.getInt(8));

			list.add(user);
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		DBUtil.closeAll(con, pst, rs);
	}

	return list;
}

//按用户名查询单个用户
public User findByName(String usersName) {
	User user = null;
	con = DBUtil.getConnection();
	String sql = "select * from users1 where usersName=?";
	try {
		pst = con.prepareStatement(sql);
		pst.setString(1, usersName);
		rs = pst.executeQuery();
		if (rs.next()) {
			user = new User();
			user.setUsersId(rs.getInt(1));
			user.setUsersName(rs.getString(2));
			user.setUsersPass(rs.getString(3));
			user.setUsersTrueName(rs.getString(4));
			user.setUsersAddress(rs.getString(5));
			user.setUsersPhone(rs.getString(6));
			user.setUsersEmail(rs.getString(7));
			user.setUserCon(rs.getInt(8));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		DBUtil.closeAll(con, pst, rs);
		;
	}
	return user;
}

//修改用户状态
public boolean updateUser (int usersCon,int usersId ) {
	boolean falg=false;
	con =DBUtil.getConnection();
	String sql="update users1 set usersCon=? where usersId=?";
	try {
		pst=con.prepareStatement(sql);
		pst.setInt(1, usersCon);
		pst.setInt(2, usersId);
	int t =	pst.executeUpdate();
	if (t>0) {
	falg=true;	
	}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{DBUtil.closeAll(con, pst, null);}
	return falg;
}
//统计用户人数
public int users() {
	int it=0;
	con=DBUtil.getConnection();
	String sql="select count(usersId) from users1";
	try {
		pst=con.prepareStatement(sql);
		rs=pst.executeQuery();
		if (rs.next()) {
			it=rs.getInt(1);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.closeAll(con, pst, rs);
	}
	return it;
}
//查询所有订单
public  List<orders>  findOrdersAll () {
	List<orders> list =new ArrayList<orders>();
	con =DBUtil.getConnection();
	String sql= "select ordersId,usersId,ordersPrice,to_char(ordersDatetime,'yyyy-mm-dd hh:MM:ss'),ordersCon from orders1";
	try {
		pst=con.prepareStatement(sql);
		rs=pst.executeQuery();
		while (rs.next()) {
			orders od=new orders(rs.getInt(1),rs.getInt(2),rs.getDouble(3),
				rs.getString(4),rs.getInt(5));
			list.add(od);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.closeAll(con,pst, rs);
	}
	
	
	return list;
	
}
//查询未付款订单
public List<orders> mon(){
	List<orders> list=new ArrayList<orders>();
	con=DBUtil.getConnection();
	String sql="select * from orders1 where ordersCon=1";
	try {
		pst=con.prepareStatement(sql);
		rs=pst.executeQuery();
		while (rs.next()) {
			orders od=new orders(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getString(4),rs.getInt(5));
			list.add(od);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.closeAll(con,pst, rs);
	}
	
	return list;
	
	
}
//查询已付款未配送款订单
public List<orders> moning(){
	List<orders> list=new ArrayList<orders>();
	con=DBUtil.getConnection();
	String sql="select * from orders1 where ordersCon=2";
	try {
		pst=con.prepareStatement(sql);
		rs=pst.executeQuery();
		while (rs.next()) {
			orders od=new orders(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getString(4),rs.getInt(5));
			list.add(od);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.closeAll(con,pst, rs);
	}
	
	return list;
	
	
}
//查询已付款未配送款订单
public List<orders> monps(){
	List<orders> list=new ArrayList<orders>();
	con=DBUtil.getConnection();
	String sql="select * from orders1 where ordersCon=3";
	try {
		pst=con.prepareStatement(sql);
		rs=pst.executeQuery();
		while (rs.next()) {
			orders od=new orders(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getString(4),rs.getInt(5));
			list.add(od);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.closeAll(con,pst, rs);
	}
	
	return list;
	
	
}
//查询已完成的订单
public List<orders> moned(){
	List<orders> list=new ArrayList<orders>();
	con=DBUtil.getConnection();
	String sql="select * from orders1 where ordersCon=4";
	try {
		pst=con.prepareStatement(sql);
		rs=pst.executeQuery();
		while (rs.next()) {
			orders od=new orders(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getString(4),rs.getInt(5));
			list.add(od);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.closeAll(con,pst, rs);
	}
	
	return list;
	
	
}
//查询已取消的订单
public List<orders> quxiao(){
	List<orders> list=new ArrayList<orders>();
	con=DBUtil.getConnection();
	String sql="select * from orders1 where ordersCon=5";
	try {
		pst=con.prepareStatement(sql);
		rs=pst.executeQuery();
		while (rs.next()) {
			orders od=new orders(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getString(4),rs.getInt(5));
			list.add(od);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.closeAll(con,pst, rs);
	}
	
	return list;
	
	
}
//按用户Id查订单

public List<orders> findOrders(int usersId ) {
	List<orders> list =new ArrayList<orders>();
	con =DBUtil.getConnection();
	String sql= "select * from orders1 where usersId=?";
	try {
		pst=con.prepareStatement(sql);
		pst.setInt(1, usersId);
		rs=pst.executeQuery();
		while (rs.next()) {
			orders od=new orders(rs.getInt(1),rs.getInt(2),rs.getDouble(3),
				rs.getString(4),rs.getInt(5));
			list.add(od);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.closeAll(con,pst, rs);
	}
	
	
	return list;
}
//按订单ID查订单
public orders findOrders2(int ordersId ) {
	orders ord =null;
	con =DBUtil.getConnection();
	String sql= "select * from orders1 where ordersId=?";
	try {
		pst=con.prepareStatement(sql);
		pst.setInt(1, ordersId);
		rs=pst.executeQuery();
		while (rs.next()) {
			 ord=new orders(rs.getInt(1),rs.getInt(2),rs.getDouble(3),
				rs.getString(4),rs.getInt(5));
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.closeAll(con,pst, rs);
	}
	
	
	return ord;
}
//修改订单状态
public boolean updateOrders(int ordersCon,int ordersId) {
	boolean falg =true;
	con=DBUtil.getConnection();
	String sql="update orders1 set ordersCon=? where ordersId=?";
	try {
		pst=con.prepareStatement(sql);
		pst.setInt(1, ordersCon);
		pst.setInt(2, ordersId);
		int t=pst.executeUpdate();
		if (t>0) {
			falg=true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.closeAll(con, pst, null);
	}
	
	return falg;
}
//按订单ID查订单明细表
public List<orderDetails> finDetails(int ordersId) {
	List<orderDetails> list =new ArrayList<orderDetails>();
	 con=DBUtil.getConnection();
	 String sql="select * from orderDetails where ordersId=?";
	try {
		pst=con.prepareStatement(sql);
		pst.setInt(1, ordersId);
		rs=pst.executeQuery();
		while (rs.next()) {
		orderDetails ord=new orderDetails(rs.getInt(1),rs.getInt(2), rs.getInt(3),rs.getString(4),
				rs.getDouble(5),rs.getInt(6));
		list.add(ord);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.closeAll(con, pst, rs);
	}

	return list;
}
//统计某月订单量
public int ddl(int t,int y) {
	int tt=0;
	con=DBUtil.getConnection();
	String sql="select count(ordersId) from  orders1 where "
			+ "extract(MONTH from   ordersDatetime )=? and extract(YEAR from ordersDatetime)=? and orderscon=4";
	try {
		pst=con.prepareStatement(sql);
		pst.setInt(1, t);
		pst.setInt(2, y);
	    rs=pst.executeQuery();
		if (rs.next()) {
			tt=rs.getInt(1);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.closeAll(con, pst, rs);
	}
	
	return tt;
}
//查询某月订单
public List<orders> ddl2(int t,int y) {
	List<orders> list =new ArrayList<orders>();
	con=DBUtil.getConnection();
	String sql="select * "
			+ " from  orders1 where "
			+ "extract(MONTH from   ordersDatetime )=? and extract(YEAR from ordersDatetime)=?";
	try {
		pst=con.prepareStatement(sql);
		pst.setInt(1, t);
		pst.setInt(2, y);
	    rs=pst.executeQuery();
		while (rs.next()) {
			orders od=new orders(rs.getInt(1),rs.getInt(2),rs.getDouble(3),
				rs.getString(4),rs.getInt(5));
			list.add(od);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.closeAll(con,pst, rs);
	}
	
	
	return list;
}
//查询某月已完成订单
public List<orders> nimabi(int t,int y) {
	List<orders> list =new ArrayList<orders>();
	con=DBUtil.getConnection();
	String sql="select * "
			+ " from  orders1 where "
			+ "extract(MONTH from   ordersDatetime )=? and extract(YEAR from ordersDatetime)=? and orderscon=4";
	try {
		pst=con.prepareStatement(sql);
		pst.setInt(1, t);
		pst.setInt(2, y);
	    rs=pst.executeQuery();
		while (rs.next()) {
			orders od=new orders(rs.getInt(1),rs.getInt(2),rs.getDouble(3),
				rs.getString(4),rs.getInt(5));
			list.add(od);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.closeAll(con,pst, rs);
	}
	
	
	return list;
}
//统计总销量前三菜品
public List<xiaoliang> qiansan() {
	List<xiaoliang> list=new ArrayList<xiaoliang>();
	con=DBUtil.getConnection();
	String sql="select foodsName ,sa ,hg from (select sum(foodsCount) hg, rank() over ( order by sum(foodsCount) desc) sa,"
			+ "orderDetails.foodsname from orderDetails group by foodsname ) t　where  t.sa<=10 ";
	try {
		pst=con.prepareStatement(sql);
	    rs=pst.executeQuery();
		while (rs.next()) {
			xiaoliang xl=new xiaoliang(rs.getString(1),rs.getInt(2),rs.getInt(3));
			list.add(xl);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.closeAll(con, pst, rs);
	}
	
	return list;
}
/*
 * 分页查询单页订单信息
 * @pageNo:当前页号
 * @pageSize:页大小（每页记录数）
 */
public List<orders> findByPage(int pageNo,int pageSize){
	List<orders> list=new ArrayList<orders>();
	
	String sql="select * from (select p.*,row_number() "
			+ "over (order by ordersId) rn from orders1 p ) "
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
			orders ord=new orders(rs.getInt(1),rs.getInt(2),rs.getDouble(3),
					rs.getString(4),rs.getInt(5));
			list.add(ord);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		DBUtil.closeAll(con, pst, rs);
	}
	
	return list;
}
/*
 * 查询订单总记录条数
 */
public int getCount(){
	int count=0;
	String sql="select count(ordersId) from orders1";
	
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
///***********分页查询所有菜品*******************
public List<foods> foodsByPage(int pageNo,int pageSize){
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
			foods food=new foods(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getDouble(4),
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
 * 查询菜品总记录条数
 */
public int foodsCount(){
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
///***********分页查询所有用户*******************
public List<User> usersByPage(int pageNo,int pageSize){
	List<User> list=new ArrayList<User>();
	
	String sql="select * from (select p.*,row_number() "
			+ "over (order by usersId) rn from users1 p ) "
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
			User user = new User();
			user.setUsersId(rs.getInt(1));
			user.setUsersName(rs.getString(2));
			user.setUsersPass(rs.getString(3));
			user.setUsersTrueName(rs.getString(4));
			user.setUsersAddress(rs.getString(5));
			user.setUsersPhone(rs.getString(6));
			user.setUsersEmail(rs.getString(7));
			user.setUserCon(rs.getInt(8));

			list.add(user);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		DBUtil.closeAll(con, pst, rs);
	}
	
	return list;
}
/*
 * 查询菜品总记录条数
 */
public int usersCount(){
	int count=0;
	String sql="select count(usersId) from users1";
	
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
}
