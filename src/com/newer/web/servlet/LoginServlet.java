package com.newer.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newer.bean.Cart;
import com.newer.bean.User;
import com.newer.dao.UserDao;
@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String userName=req.getParameter("username");
		String password=req.getParameter("userpwd");
		UserDao dao=new UserDao();
		User user=dao.findByName(userName);
		
		if (user==null) {
			req.setAttribute("msg", "用户名不存在！");
			//resp.sendRedirect("/jsps/user/login.jsp");
			req.getRequestDispatcher("/jsps/user/login.jsp").forward(req, resp);
			return;
		}
		if (!user.getUsersPass().equals(password)) {
			req.setAttribute("msg", "密码错误！");
			req.getRequestDispatcher("/jsps/user/login.jsp").forward(req, resp);
			return;
		}
		if(user.getUserCon()==0){
			req.setAttribute("msg", "对不起，您的账号被禁用！");
			req.getRequestDispatcher("/jsps/user/login.jsp").forward(req, resp);
			return;
		}
		req.getSession().setAttribute("user", user);
		//初始化登录用户的购物车
		req.getSession().setAttribute("foodsCart", new Cart());
		
		req.getRequestDispatcher("/jsps/main.jsp").forward(req, resp);
	}


}
