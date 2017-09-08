package com.newer.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.newer.bean.Foods;
import com.newer.util.DBUtil;

public class FoodsDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Connection con;
	private static PreparedStatement pst;
	private static ResultSet rs;

	// 添加菜品
	public static boolean add(Foods food) {
		boolean falg = false;
		con = DBUtil.getConnection();
		String sql = "insert into foods values(seq_foodsId.nextval,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, food.getFoodsImage());
			pst.setString(2, food.getFoodsName());
			pst.setDouble(3, food.getFoodsPrice());
			pst.setInt(4, food.getFoodsHabitus());
			pst.setString(5, food.getFoodsRemark());
			pst.setInt(6, food.getSortId());
			int t = pst.executeUpdate();
			if (t > 0) {
				falg = true;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(con, pst, null);
		}
		return falg;

	}

	// 修改菜品信息
	public static boolean update(Foods food) {
		boolean falg = false;
		con = DBUtil.getConnection();
		String sql = "update foods set foodsImage=? ,foodsName=?,foodsPrice=?,"
				+ "foodsHabitus=?,foodsRemark=?,sortId=? where foodsId=?";
		try {
			pst = con.prepareStatement(sql);
			
			pst.setString(1, food.getFoodsImage());
			pst.setString(2, food.getFoodsName());
			pst.setDouble(3, food.getFoodsPrice());
			pst.setInt(4, food.getFoodsHabitus());
			pst.setString(5, food.getFoodsRemark());
			pst.setInt(6, food.getSortId());
			int t = pst.executeUpdate();
			if (t > 0) {
				falg = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(con, pst, null);
		}

		return falg;
	}

	// 按ID单个查询
	public static Foods findById(int foodsId) {
		Foods food = null;
		con = DBUtil.getConnection();
		String sql = "select * from foods where foodsId=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, foodsId);
			rs = pst.executeQuery();
			if (rs.next()) {
				food = new Foods(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getString(6),
						rs.getInt(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(con, pst, rs);
		}

		return food;
	}
	

	// 按名字模糊查询
	public static List<Foods> findByName(String foodsName) {
		List<Foods> list = new ArrayList<Foods>();
		con = DBUtil.getConnection();
		String sql = "select * from foods where foodsName like '%" + foodsName + "%'";
		try {
			pst = con.prepareStatement(sql);
			// pst.setString(1, foodsName);
			rs = pst.executeQuery();
			while (rs.next()) {
				Foods food = new Foods(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getString(6),
						rs.getInt(7));
				list.add(food);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(con, pst, rs);
		}

		return list;
	}
	// 查询所有菜品

	public static List<Foods> findAll() {
		List<Foods> list = new ArrayList<Foods>();
		con = DBUtil.getConnection();
		String sql = "select * from foods where foodsHabitus=1 ";
		try {
			pst = con.prepareStatement(sql);

			rs = pst.executeQuery();
			while (rs.next()) {
				Foods food = new Foods(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getString(6),
						rs.getInt(7));
				list.add(food);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(con, pst, rs);
		}

		return list;
	}
	/**
	 * 根据菜系查询菜品
	 */
	public List<Foods> findBySort(int id){
		List<Foods> list =new ArrayList<Foods>();
		con=DBUtil.getConnection();
		String sql="select * from foods where sortid=? and foodsHabitus=1";
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			while(rs.next()){
				Foods food = new Foods(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getString(6),
						rs.getInt(7));
				
				list.add(food);
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
	 * 分页查询单页菜品信息
	 * 
	 * @pageNo:当前页号
	 * 
	 * @pageSize:页大小（每页记录数）
	 */
	public List<Foods> findByPage(int pageNo, int pageSize) {
		List<Foods> list = new ArrayList<Foods>();
		Connection conn1=DBUtil.getConnection();
		PreparedStatement pre1=null;
		ResultSet res1=null;

		String sql = "select * from (select p.*,row_number() over (order by foodsId) rn from foods p where foodsHabitus=1) "
				+ "where rn between ? and ? ";

		try {
			conn1 = DBUtil.getConnection();
			pre1 = conn1.prepareStatement(sql);
			int start = (pageNo - 1) * pageSize + 1;// 起始行号
			int end = pageNo * pageSize;// 结束行号

			pre1.setInt(1, start);
			pre1.setInt(2, end);

			res1 = pre1.executeQuery();

			while (res1.next()) {
				Foods food = new Foods(res1.getInt(1),res1.getString(2), res1.getString(3),res1.getDouble(4), res1.getInt(5), res1.getString(6),
						res1.getInt(7));

				list.add(food);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(conn1, pre1, res1);
		}

		return list;
	}

	/*
	 * 查询所有菜品总记录条数
	 */
	public int getCount() {
		int count = 0;
		String sql = "select count(foodsId) from foods where foodsHabitus=1";

		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);

			rs = pst.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(con, pst, rs);
		}
		return count;
	}
	/*
	 * 分页查询某类菜品信息
	 * 
	 * @pageNo:当前页号
	 * 
	 * @pageSize:页大小（每页记录数）
	 */
	public List<Foods> sortByPage(int sortId, int pageNo, int pageSize) {
		List<Foods> list = new ArrayList<Foods>();

		String sql = "select * from (select p.*,row_number() over (order by foodsId) rn from foods p where sortId=? and  foodsHabitus=1 ) "
				+ "where rn between ? and ?";

		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			int start = (pageNo - 1) * pageSize + 1;// 起始行号
			int end = pageNo * pageSize;// 结束行号
            pst.setInt(1, sortId);            
			pst.setInt(2, start);
			pst.setInt(3, end);

			rs = pst.executeQuery();

			while (rs.next()) {
				Foods food = new Foods(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getString(6),
						rs.getInt(7));

				list.add(food);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(con, pst, rs);
		}

		return list;
	}
	/*
	 * 查询某类菜品总记录条数
	 */
	public int sortCount( int sortId) {
		int count = 0;
		String sql = "select count(foodsId) from foods where sortId=? and foodsHabitus=1";

		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
            pst.setInt(1, sortId);
			rs = pst.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(con, pst, rs);
		}
		return count;
	}

}
