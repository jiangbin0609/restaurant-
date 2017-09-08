package com.newer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.newer.bean.Sort;
import com.newer.util.DBUtil;

public class SortDao {
	private static Connection conn;
	private static PreparedStatement pre;
	private static ResultSet re;
	
	/**
	 * 查询所有的菜系
	 */
	public List<Sort> findAll(){
		List<Sort> list =new ArrayList<Sort>();
		conn=DBUtil.getConnection();
		String sql="select * from sort1";
		try {
			pre=conn.prepareStatement(sql);
			re=pre.executeQuery();
			while(re.next()){
				Sort sort=new Sort();
				sort.setSortID(re.getInt(1));
				sort.setSortName(re.getString(2));
				list.add(sort);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(conn, pre, re);
		}
		return list;
		
	}

}
