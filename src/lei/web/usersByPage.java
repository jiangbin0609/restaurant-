package lei.web;

import java.sql.SQLException;
import java.util.List;

import lei.bean.*;
import lei.dao.backDao;

public class usersByPage {
	public PageBean<User> users(int currPage, int pageSize) throws SQLException {
		//查询当前页数据 limit (当前页-1)*每页显示条数,每页显示条数;
		backDao dao=new backDao();
		List<User> list=dao.usersByPage(currPage,pageSize);
		
		//查询总条数
		int totalCount=dao.usersCount();
		
		return new PageBean<>(list, currPage, pageSize, totalCount);
	}
}
