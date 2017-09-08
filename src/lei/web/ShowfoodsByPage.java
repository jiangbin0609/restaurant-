package lei.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lei.bean.PageBean;
import lei.bean.foods;
import lei.dao.backDao;

@WebServlet("/ShowfoodsByPage")
public class ShowfoodsByPage  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//0.设置编码
	             
			//1.获取第几页
	         int currPage=1;
	         String curr= (String)req.getAttribute("curr");
	         if (curr==null) {
	        		currPage=Integer.parseInt(req.getParameter("currPage"));
			}else {
				String updd="恭喜您修改成功";
				req.setAttribute("updd",updd);
			}
		
			//固定每页显示的条数
			int pageSize=10;
			
			//2.调用service完成分页查询 返回值:pagebean
			List<foods> list=new ArrayList<foods>();;
			//查询当前页数据 limit (当前页-1)*每页显示条数,每页显示条数;
			backDao dao=new backDao();
			 list=dao.foodsByPage(currPage,pageSize);
			
			//查询总条数
			 PageBean<foods> bean=null;
				try {
					bean = new foodsByPage().foods(currPage,pageSize);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//3.将pagebean放入request域中,请求转发product_page.jsp
				
					
				
				
				
            String bb="bb";
			
			req.setAttribute("bb",bb);
			
			
			req.setAttribute("list", list);
			req.setAttribute("pb", bean);
			req.getRequestDispatcher("before/caipin.jsp").forward(req, resp);
			
}
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	super.doGet(req, resp);
}
}
