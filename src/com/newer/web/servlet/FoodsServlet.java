package com.newer.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newer.bean.Foods;
import com.newer.dao.FoodsDao;

import com.newer.bean.PageBean;
import lei.bean.foods;
import lei.dao.backDao;
import com.newer.web.servlet.*;;
@WebServlet("/foodsServlet")
public class FoodsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String action =req.getParameter("action");
		FoodsDao dao =new FoodsDao();
		List<Foods> list=null;
		Foods food=null;
		
		if("findAll".equals(action)){
			//list=dao.findAll();
			//1.获取第几页
			
			int currPage=Integer.parseInt(req.getParameter("currPage"));
			//固定每页显示的条数
			int pageSize=10;
			
			//2.调用service完成分页查询 返回值:pagebean
			
			//查询当前页数据 limit (当前页-1)*每页显示条数,每页显示条数;
			 list=dao.findByPage(currPage,pageSize);
			
			//查询总条数
			 PageBean<Foods> bean=null;
				try {
					bean = new FoodsByPage().foods(currPage,pageSize);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//3.将pagebean放入request域中,请求转发product_page.jsp
            //String bb="bb";
			
			//req.setAttribute("bb",bb);
			
			req.setAttribute("pb", bean);	
			req.getSession().setAttribute("foodList", list);
			req.getRequestDispatcher("jsps/food/list.jsp").forward(req, resp); 
			return;
		}
		if("findBySort".equals(action)){
			int id=Integer.parseInt(req.getParameter("cid"));
			
			
			int currPage=Integer.parseInt(req.getParameter("currPage"));
			int pageSize=10;
			list=dao.sortByPage(id, currPage, pageSize);
			 PageBean<Foods> bean=null;
				try {
					bean = new SortByPage().foods(id,currPage,pageSize);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String bb=null;
				if (id==1) {
					bb="xiangcai";
				}else if (id==2) {
					bb="chuancai";
				}else if (id==3) {
					bb="yuecai";
				}else if (id==4) {
					bb="lucai";
				}else if(id==5){
					bb="sucai";
				}
				
			req.setAttribute("bb", bb);
			req.getSession().setAttribute("foodList", list);
			req.setAttribute("pb", bean);
			req.getRequestDispatcher("jsps/food/list.jsp").forward(req, resp);
			return;
		}
		if("load".equals(action)){
			int id=Integer.parseInt(req.getParameter("fid"));
			food=dao.findById(id);
			req.setAttribute("food", food);
			
			req.getRequestDispatcher("jsps/food/desc.jsp").forward(req, resp);
			
		}
		
		
	}
	
	

}
