package com.newer.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newer.bean.Sort;
import com.newer.dao.SortDao;
@WebServlet("/sortservlet")
public class SortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		SortDao dao =new SortDao();
		List<Sort> sortList=dao.findAll();
		req.getSession().setAttribute("sortList", sortList);
		req.getRequestDispatcher("/jsps/left.jsp").forward(req, resp);
	}

}
