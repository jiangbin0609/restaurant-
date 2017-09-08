package com.newer.web.servlet;
import java.sql.SQLException;
import java.util.List;

import com.newer.bean.*;
import com.newer.dao.*;

public class FoodsByPage {
	public PageBean<Foods> foods(int currPage, int pageSize) throws SQLException {
		//查询当前页数据 limit (当前页-1)*每页显示条数,每页显示条数;
		FoodsDao dao=new FoodsDao();
		List<Foods> list=dao.findByPage(currPage,pageSize);
		
		//查询总条数
		int totalCount=dao.getCount();
		
		return new PageBean<>(list, currPage, pageSize, totalCount);
	}
}
