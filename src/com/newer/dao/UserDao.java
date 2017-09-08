package com.newer.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.newer.bean.User;
import com.newer.util.DBUtil;

public class UserDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Connection conn = null;
	private static PreparedStatement pre = null;
	private static ResultSet re = null;

	/**
	 * 添加用户的方法
	 */
	public boolean addUser(User user) {
		boolean flag = false;
		conn = DBUtil.getConnection();
		String sql = "insert into users1(usersId,usersname,userspass,userstruename,usersaddress,usersphone,usersemail) "
				+ "values(seq_usersId.nextval,?,?,?,?,?,?)";
		try {
			pre = conn.prepareStatement(sql);

			pre.setString(1, user.getUsersName());
			pre.setString(2, user.getUsersPass());
			pre.setString(3, user.getUsersTrueName());
			pre.setString(4, user.getUsersAddress());
			pre.setString(5, user.getUsersPhone());
			pre.setString(6, user.getUsersEmail());

			if (pre.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(conn, pre, re);
		}

		return flag;

	}

	/*
	 * public static void main(String[] args) { UserDao dao=new UserDao(); User
	 * user=new User(29, "lisi", "123456", "lisi", "hunan", "152008",
	 * "54063020", 1); System.out.println(dao.updateUser(user));
	 * 
	 * }
	 */
	/**
	 * 修改用户信息方法
	 */
	public boolean updateUser(User user) {
		boolean flag = false;
		conn = DBUtil.getConnection();
		String sql = "update users1 set usersname=?,userspass=?, userstruename=?,usersaddress=?,"
				+ "usersphone=?,usersemail=?,userscon=? where usersid=?";
		try {
			pre = conn.prepareStatement(sql);
			pre.setInt(8, user.getUsersId());
			pre.setString(1, user.getUsersName());
			pre.setString(2, user.getUsersPass());
			pre.setString(3, user.getUsersTrueName());
			pre.setString(4, user.getUsersAddress());
			pre.setString(5, user.getUsersPhone());
			pre.setString(6, user.getUsersEmail());
			pre.setInt(7, user.getUserCon());
			if (pre.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(conn, pre, re);
		}
		return flag;
	}

	/**
	 * 查询所有的用户
	 */
	public List<User> fiandAll() {
		List<User> list = new ArrayList<User>();
		conn = DBUtil.getConnection();
		String sql = "select * from users1";
		try {
			pre = conn.prepareStatement(sql);
			re = pre.executeQuery();
			while (re.next()) {
				User user = new User();
				user.setUsersId(re.getInt(1));
				user.setUsersName(re.getString(2));
				user.setUsersPass(re.getString(3));
				user.setUsersTrueName(re.getString(4));
				user.setUsersAddress(re.getString(5));
				user.setUsersPhone(re.getString(6));
				user.setUsersEmail(re.getString(7));
				user.setUserCon(re.getInt(8));

				list.add(user);
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
	 * 使用用户名查询用户
	 */
	public User findByName(String name) {
		User user = null;
		conn = DBUtil.getConnection();
		String sql = "select * from users1 where usersname=?";
		try {
			pre = conn.prepareStatement(sql);
			pre.setString(1, name);
			re = pre.executeQuery();
			if (re.next()) {
				user = new User();
				user.setUsersId(re.getInt(1));
				user.setUsersName(re.getString(2));
				user.setUsersPass(re.getString(3));
				user.setUsersTrueName(re.getString(4));
				user.setUsersAddress(re.getString(5));
				user.setUsersPhone(re.getString(6));
				user.setUsersEmail(re.getString(7));
				user.setUserCon(re.getInt(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(conn, pre, re);
		}
		return user;

	}

	/**
	 * 插询单个用户信息
	 */
	public User finaById(int id) {
		User user = null;
		conn = DBUtil.getConnection();
		String sql = "select * from users1 where usersid=?";
		try {
			pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			re = pre.executeQuery();
			if (re.next()) {
				user = new User();
				user.setUsersId(re.getInt(1));
				user.setUsersName(re.getString(2));
				user.setUsersPass(re.getString(3));
				user.setUsersTrueName(re.getString(4));
				user.setUsersAddress(re.getString(5));
				user.setUsersPhone(re.getString(6));
				user.setUsersEmail(re.getString(7));
				user.setUserCon(re.getInt(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(conn, pre, re);
		}
		return user;
	}

	/**
	 * 修改用户状态
	 */
	public boolean updateUsersCon(int usersId, int usersCon) {
		boolean flag = false;
		conn = DBUtil.getConnection();
		String sql = "update users1 set userscon=? where usersid=?";
		try {
			pre = conn.prepareStatement(sql);
			pre.setInt(1, usersCon);
			pre.setInt(2, usersId);

			if (pre.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(conn, pre, re);
		}
		return flag;

	}

	// 检验用户原密码是否正确
	public boolean password(String password, int usersId) {
		boolean falg = false;
		conn = DBUtil.getConnection();
		String sql = "select * from users1 where usersId=? and usersPass=?";
		try {
			pre = conn.prepareStatement(sql);
			pre.setInt(1, usersId);
			pre.setString(2, password);
			re = pre.executeQuery();
			if (re.next()) {
				falg = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(conn, pre, re);
		}

		return falg;
	}

	// 修改用户密码
	public boolean uppassword(String password, int usersId) {
		boolean falg = false;
		conn = DBUtil.getConnection();
		String sql = " update users1 set usersPass=? where usersId=?";
		try {
			pre = conn.prepareStatement(sql);
			pre.setInt(2, usersId);
			pre.setString(1, password);
			int t = pre.executeUpdate();
			if (t > 0) {
				falg = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(conn, pre, null);
		}

		return falg;
	}

}
