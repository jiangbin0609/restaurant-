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

@WebServlet("/registServlet")
public class registServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		String action = req.getParameter("action");
		UserDao dao = new UserDao();
		
		if("validate".equals(action)){
			String name = req.getParameter("userName");
			if(dao.findByName(name)!=null){
				resp.getWriter().print("用户名已被注册！");
			}else{
				resp.getWriter().print("√可以使用！");
			}
			return;
		}

		if ("regist".equals(action)) {
		
			String name = req.getParameter("userName");
			String realName = req.getParameter("real_name");
			String password = req.getParameter("pwd");
			String phone = req.getParameter("phone");
			String address = req.getParameter("address");
			String email = req.getParameter("email");

			User user = new User();
			user.setUsersName(name);
			user.setUsersTrueName(realName);
			user.setUsersPass(password);
			user.setUsersPhone(phone);
			user.setUsersAddress(address);
			user.setUsersEmail(email);
		
			if (dao.findByName(name) != null) {
				req.setAttribute("user", user);
				req.setAttribute("msg", "该用户名已经被注册！");
				req.getRequestDispatcher("/jsps/user/regist.jsp").forward(req, resp);
			} else if (dao.addUser(user)) {
				req.getSession().setAttribute("user", user);
				// 初始化购物车并赋值给新注册用户
				req.getSession().setAttribute("foodsCart", new Cart());
				req.getRequestDispatcher("/jsps/main.jsp").forward(req, resp);
			}
			return;

		}

		if ("update".equals(action)) {
			String name = req.getParameter("userName");
			String realName = req.getParameter("real_name");
			String password = req.getParameter("pwd");
			String phone = req.getParameter("phone");
			String address = req.getParameter("address");
			String email = req.getParameter("email");

			User user = new User();
			int userId = Integer.parseInt(req.getParameter("userId"));
			user.setUsersId(userId);
			user.setUsersName(name);
			user.setUsersTrueName(realName);
			user.setUsersPass(password);
			user.setUsersPhone(phone);
			user.setUsersAddress(address);
			user.setUsersEmail(email);
			user.setUserCon(1);

			// 修改用户信息
			if (dao.updateUser(user)) {
				req.getSession().setAttribute("user", user);
				req.getRequestDispatcher("/jsps/main.jsp").forward(req, resp);
			} else {
				req.setAttribute("msg", "修改失败，请重试！");
				req.getRequestDispatcher("/jsps/user/userInfo.jsp").forward(req, resp);
			}

		}
		//传递用户Id到修改密码页面
		if (action.equals("password")) {
			int usersId=Integer.parseInt(req.getParameter("usersId"));
			req.setAttribute("usersId", usersId);
			req.getRequestDispatcher("/jsps/user/password.jsp").forward(req, resp);
		}
		//修改用户密码
		if (action.equals("uppassword")) {
		
			int usersId=Integer.parseInt(req.getParameter("usersId"));
			String oldpass=req.getParameter("oldPass");
			String newpass=req.getParameter("newPass");
			String notPass="修改失败，原密码错误！！！";
			if (dao.password(oldpass, usersId)) {
				dao.uppassword(newpass, usersId);
				req.setAttribute("msg", "修改成功，请重新登录！");
				req.getRequestDispatcher("/jsps/user/login.jsp").forward(req, resp);
			}else{
			req.setAttribute("notPass", notPass);
			req.getRequestDispatcher("/jsps/user/password.jsp").forward(req, resp);
			}
		}
	}

}
