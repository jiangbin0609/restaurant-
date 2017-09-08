package lei.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class logenFilter  implements Filter{


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	 HttpServletRequest req=(HttpServletRequest)request;
	 HttpServletResponse resp=(HttpServletResponse)response;
		HttpSession session=req.getSession();
		String obj=(String)session.getAttribute("adminName");
		//检验用户是否登陆
		//System.out.println(obj);
		if (obj!=null) {
			chain.doFilter(req, resp);
			//resp.sendRedirect(req.getContextPath()+"/denglu.jsp");
			
		}if (obj==null) {
		
			req.getRequestDispatcher("denglu.jsp").forward(req, resp);
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}



}
