package com.newer.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newer.bean.Cart;
import com.newer.bean.CartItem;
import com.newer.bean.Foods;
import com.newer.bean.User;
import com.newer.dao.FoodsDao;

@WebServlet("/cartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		Cart cart = (Cart) req.getSession().getAttribute("foodsCart");
		User user=(User)req.getSession().getAttribute("user");
		
		if(user==null){
			req.setAttribute("msg", "您还未登录，请先等录或注册！");
			req.getRequestDispatcher("jsps/user/login.jsp").forward(req, resp);
			return;
		}
		// 添加菜品到购物车
		if ("add".equals(action)) {
			int fid = Integer.parseInt(req.getParameter("fid"));
			FoodsDao dao = new FoodsDao();
			Foods food = dao.findById(fid);

			int count = Integer.parseInt(req.getParameter("count"));
			CartItem cartItem = new CartItem();
			cartItem.setFood(food);
			cartItem.setCount(count);
			System.out.println(cart==null);
			cart.add(cartItem);

			req.getRequestDispatcher("jsps/cart/list.jsp").forward(req, resp);
			return;
		}
		// 删除购物车中的某项
		if ("delete".equals(action)) {
			int fid = Integer.parseInt(req.getParameter("fid"));
			cart.delete(fid);
			req.getRequestDispatcher("jsps/cart/list.jsp").forward(req, resp);
			return;
		}
		// 清空购物车
		if ("clear".equals(action)) {
			cart.clear();
			req.getRequestDispatcher("jsps/cart/list.jsp").forward(req, resp);

		}

	}

}
