package com.newer.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newer.bean.Cart;
import com.newer.bean.CartItem;
import com.newer.bean.Order;
import com.newer.bean.OrderItem;
import com.newer.bean.User;
import com.newer.dao.OrderDao;
@WebServlet("/orderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String action=req.getParameter("action");
		OrderDao dao =new OrderDao();
		int ordersId=dao.getSequenceValue();

		//添加订单
		if("add".equals(action)){
			Cart cart=(Cart)req.getSession().getAttribute("foodsCart");
			
			Order order=new Order();
			order.setOrdersId(ordersId);//设置订单编号
			order.setOrdersCon(1);//设置订单状态。默认为1，未付款
			//-------------------------
			order.setOrdersDateTime(new Date());//设置下单时间
			User user=(User)req.getSession().getAttribute("user");//获取用户
			order.setUser(user);//设置订单所有者
			order.setTotal(cart.getTotal());//设置定订单合计
		
			
			//订单明细
			List<OrderItem> orderItemList=new ArrayList<OrderItem>();
			for(CartItem item:cart.getCartItems()){
				OrderItem oi=new OrderItem();
				 
				oi.setIid(dao.getSequenceValue1());//设置订单详细表的id
				oi.setCount(item.getCount());//设置菜品的数量
				oi.setFood(item.getFood());//设置购买的菜品
				oi.setSubtotal(item.getSubtotal());//设置购买菜品的小计
				oi.setOrder(order);//设置所属订单
				
				orderItemList.add(oi);
			}
			order.setOrderItemList(orderItemList);
			cart.clear(); 
	        dao.addOrder(order);
	        dao.addOrderItemList(orderItemList);
	        
			req.setAttribute("order", order);
			req.getRequestDispatcher("jsps/order/desc.jsp").forward(req, resp);
			return;
		}
		
		//查看我的订单
		if("myOrders".equals(action)){
			
			User user=(User)req.getSession().getAttribute("user");
			List<Order> orderlList=dao.loadOrders(user);
			
			req.setAttribute("orderList", orderlList);
			req.getRequestDispatcher("jsps/order/list.jsp").forward(req, resp);
			return;
		}
		
		
		//确认收货
		if("confirm".equals(action)){
			int orderId=Integer.parseInt(req.getParameter("oid"));
			
			if(dao.findOrdersCon(orderId)==3){
				dao.updateOrderCon(orderId, 4);
				req.setAttribute("msg", "交易成功，期待您的再次惠顾！");
			}
			req.getRequestDispatcher("jsps/msg.jsp").forward(req, resp);
			return;
		}
		if("confirm1".equals(action)){
			int orderId=Integer.parseInt(req.getParameter("oid"));
			
			if(dao.findOrdersCon(orderId)==1){
				dao.updateOrderCon(orderId, 5);
				
			}
			req.getRequestDispatcher("orderServlet?action=myOrders").forward(req, resp);
			return;
		}
		
		if("load".equals(action)){
			int orderId=Integer.parseInt(req.getParameter("oid"));
			
			Order order=dao.findByOrderId(orderId);
			req.setAttribute("order", order);
			
			List<OrderItem> orderItemList = dao.loadOrderItems(orderId);
			order.setOrderItemList(orderItemList);
			req.getRequestDispatcher("jsps/order/desc.jsp").forward(req, resp);
			return;
		}
		if("pay".equals(action)){
			int orderId=Integer.parseInt(req.getParameter("oid"));
		
			if(dao.updateOrderCon(orderId, 2)){
				
				req.setAttribute("msg", "付款成功，立即为您配送，请耐心等待！");
			}
			req.getRequestDispatcher("jsps/msg.jsp").forward(req, resp);
		}
	}
	
	
}
