package lei.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
//import com.jspsmart.upload.File;
//import com.jspsmart.upload.Files;
//import com.jspsmart.upload.SmartUpload;
//import com.jspsmart.upload.SmartUploadException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lei.bean.User;
import lei.bean.admin;
import lei.bean.foods;
import lei.bean.jishu;
import lei.bean.orderDetails;
import lei.bean.orders;
import lei.bean.xiaoliang;
import lei.dao.backDao;



@WebServlet("/land")
public class land  extends HttpServlet{

	private static final long serialVersionUID = 1L;
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");
	backDao dao=new backDao();	
	HttpSession session=req.getSession();
	String action=req.getParameter("action");
	if (action.equals("exit")) {
		//退出清除session
		session.invalidate();
		resp.sendRedirect("before/denglu.jsp");
	}else if(action.equals("xiaoshou")){
		//查看菜品销售前三
		List<xiaoliang> list =new ArrayList<xiaoliang>();
	   list=dao.qiansan();
	   req.setAttribute("list", list);
	   req.getRequestDispatcher("before/tongji.jsp").forward(req, resp);
	}else if (action.equals("pb")) {
		//查询已屏蔽用户
		List<User> list =new ArrayList<User>();
		list=dao.shield();
		//Integer nima =new Integer(1);
		String nima="sb1";
		req.setAttribute("a1", nima);
		req.setAttribute("list", list);
		req.getRequestDispatcher("before/users.jsp").forward(req, resp);
	}else if (action.equals("all")) {
		//查询所有用户
		List<User> list =new ArrayList<User>();
		list=dao.findUserAll();
		int ren=dao.users();
		//Integer nima =new Integer(2);
		String nima="sb2";
		req.setAttribute("a1", nima);
		req.setAttribute("list", list);
		req.setAttribute("ren", ren);
		req.getRequestDispatcher("before/users.jsp").forward(req, resp);
		
	}else if (action.equals("caozuo")) {
		//修改用户状态
		int usersCon=Integer.parseInt(req.getParameter("userCon"));
		int usersId=Integer.parseInt(req.getParameter("usersId"));
		if (dao.updateUser(usersCon, usersId)) {
			String str="用户状态修改成功";
			req.setAttribute("a2", str);
			req.getRequestDispatcher("before/users.jsp").forward(req, resp);
		}else {
			String str="用户状态修改失败";
			req.setAttribute("a2", str);
			req.getRequestDispatcher("before/users.jsp").forward(req, resp);
		}
	}else if (action.equals("mon")) {
		//查看未付款订单
		List<orders> list=new ArrayList<orders>();
		list=dao.mon();
		String nima="sb1";
		req.setAttribute("a1", nima);
		req.setAttribute("list", list);
		req.getRequestDispatcher("before/dingdan.jsp").forward(req, resp);
	}else if (action.equals("moning")) {
		//查看已付款未配送的订单
		List<orders> list=new ArrayList<orders>();
		list=dao.moning();
		String nima="sb2";
		req.setAttribute("a1", nima);
		req.setAttribute("list", list);
		req.getRequestDispatcher("before/dingdan.jsp").forward(req, resp);
	}else if (action.equals("moned")) {
		//查看已完成的订单
		List<orders> list=new ArrayList<orders>();
		list=dao.moned();
		String nima="sb3";
		req.setAttribute("a1", nima);
		req.setAttribute("list", list);
		req.getRequestDispatcher("before/dingdan.jsp").forward(req, resp);
	}else if (action.equals("quxiao")) {
		//查看已取消订单
		List<orders> list=new ArrayList<orders>();
		list=dao.quxiao();
		String nima="sb4";
		req.setAttribute("a1", nima);
		req.setAttribute("list", list);
		req.getRequestDispatcher("before/dingdan.jsp").forward(req, resp);
	}else if (action.equals("monps")) {
		//查看已付款正在配送的订单
		List<orders> list=new ArrayList<orders>();
		list=dao.monps();
		String nima="sb7";
		req.setAttribute("a1", nima);
		req.setAttribute("list", list);
		req.getRequestDispatcher("before/dingdan.jsp").forward(req, resp);
	}else if (action.equals("mingxi")) {
		//查看订单明细
			int ordersId=Integer.parseInt(req.getParameter("ordersId"));
		List<orderDetails> list=new ArrayList<orderDetails>();
		list=dao.finDetails(ordersId);

		req.setAttribute("list", list);
		req.getRequestDispatcher("before/mingxi.jsp").forward(req, resp);
	}else if (action.equals("foodsAll")) {
		//查看所有菜品
	List<foods> list=new ArrayList<foods>();
	list=dao.foodsAll();
	req.setAttribute("list", list);
	req.getRequestDispatcher("before/caipin.jsp").forward(req, resp);
  }else if(action.equals("shangjia")){
	  //上架菜品
	 int foodsId=Integer.parseInt(req.getParameter("foodsId"));
	  dao.shangjia(foodsId);
	  
	 // List<foods> list=new ArrayList<foods>();
		//list=dao.foodsAll();
		//req.setAttribute("list", list);
		//req.getRequestDispatcher("before/caipin.jsp").forward(req, resp);
	    String  curr="nihao";
	    req.setAttribute("curr", curr);
	    req.getRequestDispatcher("ShowfoodsByPage").forward(req, resp);
    }else if(action.equals("xiajia")){
  	  //下架菜品
   	 int foodsId=Integer.parseInt(req.getParameter("foodsId"));
   	  dao.xiajia(foodsId);
   	  
   	 // List<foods> list=new ArrayList<foods>();
   		//list=dao.foodsAll();
   		//req.setAttribute("list", list);
   		//req.getRequestDispatcher("before/caipin.jsp").forward(req, resp);
   	     String  curr="nihao";
	    req.setAttribute("curr", curr);
	    req.getRequestDispatcher("ShowfoodsByPage").forward(req, resp);
      }else if(action.equals("foodOne")){
      	  //把被选中的菜品信息传递到修改页面
    	   	 int foodsId=Integer.parseInt(req.getParameter("foodsId"));
    	   	
    	   		foods food=dao.findOne(foodsId);
    	   		req.setAttribute("foods", food);
    	   		req.getRequestDispatcher("before/foodsUpd.jsp").forward(req, resp);
   }else if(action.equals("foodsUpd")){
		//修改菜品信息
		int foodsId=Integer.parseInt(req.getParameter("foodsId"));
		String foodsImage=(String)req.getParameter("foodsImage");
		String foodsName=(String)req.getParameter("foodsName");
		double foodsPrice=Double.parseDouble( req.getParameter("foodsPrice"));
		int  foodsHabitus=Integer.parseInt(req.getParameter("foodsHabitus")) ;
		String foodsRemark=(String)req.getParameter("foodsRemark");
		int  sortId=Integer.parseInt(req.getParameter("sortId")) ;
		foods food=new foods(foodsId,foodsImage,foodsName,foodsPrice,foodsHabitus,foodsRemark,sortId);
	if(dao.foodsUpd(food)){
		String updd="恭喜您修改成功";
		req.setAttribute("updd", updd);
		List<foods> list=new ArrayList<foods>();
		list=dao.foodsAll();
		String curr="curr";
		//req.setAttribute("list", list);
		req.setAttribute("curr", curr);
		req.getRequestDispatcher("ShowfoodsByPage").forward(req, resp);
	}
	}
	
}
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");
	String adminName = (String)req.getParameter("adminName");
	String adminPass =(String)req.getParameter("adminPass");
	String action=req.getParameter("action");
	backDao dao=new backDao();	
	admin admin=dao.landing(adminName, adminPass);
   //System.out.println(admin);
	HttpSession session=req.getSession();
	if(action==null){
	if (admin!=null) {		//检验管理员登陆
		session.setAttribute("adminId", admin.getAdminId());
		session.setAttribute("adminName", admin.getAdminName());
		//req.setAttribute("adminName", admin.getAdminName());
		req.getRequestDispatcher("before/zhuye.jsp").forward(req, resp);
	}else {
		req.setAttribute("cuowu", "用户名或密码有误");
		req.getRequestDispatcher("before/denglu.jsp").forward(req, resp);
	   }
	}else if(action.equals("uppsd")){
		//管理员修改密码
		int id=(Integer)session.getAttribute("adminId");
		String oldPass=req.getParameter("oldPass");
		String newPass=req.getParameter("newPass");
		if (dao.yanzheng(oldPass, id)) {
		
			if (dao.updatePass(newPass, id)) {
				req.setAttribute("cuowu", "修改成功，请重新登录！");
				req.getRequestDispatcher("before/denglu.jsp").forward(req, resp);;
		   }
		}else {
			req.setAttribute("notPass", "修改失败，原密码错误");
			req.getRequestDispatcher("before/password.jsp").forward(req, resp);
		   }
	}else if (action.equals("tongji")) {
		//统计订单量
		   int year=Integer.parseInt(req.getParameter("year"));
		   int month=Integer.parseInt(req.getParameter("month"));
	       int dingdan= dao.ddl(month, year);
	      // List<orders> list=new ArrayList<orders>();
	       //list=dao.ddl2(month, year);
	       List<orders> list2=new ArrayList<orders>();
	       list2=dao.nimabi(month, year);
	       
	       double kk=0;
	       for (orders or :list2) {
			kk+=or.getOrdersPrice();
		}
	       req.setAttribute("list", list2);
	       req.setAttribute("kk", kk);
	       req.setAttribute("dingdan", dingdan);
		   req.setAttribute("year", year);
		   req.setAttribute("month", month);
		   req.getRequestDispatcher("before/tongji2.jsp").forward(req, resp);
	   }else if (action.equals("ss")) {
		//按用户名搜索用户
		   String usersName=(String)req.getParameter("usersName");
		 User user=dao.findByName(usersName);
			//Integer nima =new Integer(3);
		 String nima="sb3";
		 req.setAttribute("a1", nima );
		 req.setAttribute("user", user);
		 req.getRequestDispatcher("before/users.jsp").forward(req, resp);
	     }else if (action.equals("ordss1")) {
	 		//按用户ID搜索订单
	    	 List<orders> list =new ArrayList<orders>();
			   int usersId=Integer.parseInt(req.getParameter("usersId"));
			 list=dao.findOrders(usersId);
				//Integer nima =new Integer(3);
			 String nima="sb5";
			 req.setAttribute("a1", nima );
			 req.setAttribute("list", list);
			 req.getRequestDispatcher("before/dingdan.jsp").forward(req, resp);
		     }else if (action.equals("ordss2")) {
			 		//按订单ID搜索订单
		    	 orders ord =new orders();
				   int ordersId=Integer.parseInt(req.getParameter("ordersId"));
				 ord=dao.findOrders2(ordersId);
					//Integer nima =new Integer(3);
				 String nima="sb6";
				 req.setAttribute("a1", nima );
				 req.setAttribute("ord", ord);
				 req.getRequestDispatcher("before/dingdan.jsp").forward(req, resp);
			     }else if (action.equals("xiugaid")) {
				 		//修改订单状态
			    	
			    	 int ordersCon=Integer.parseInt(req.getParameter("zhuangtai"));
					   int ordersId=Integer.parseInt(req.getParameter("ordersId"));
					 dao.updateOrders(ordersCon, ordersId);
						//Integer nima =new Integer(3);
					 String nima="修改成功";
					 req.setAttribute("a2", nima );
				
					 req.getRequestDispatcher("before/dingdan.jsp").forward(req, resp);
					
				     }else if (action.equals("findfood")) {
				    	 //按菜名搜索菜品信息
						List<foods> list=new ArrayList<foods>();
						String foodsName=(String)req.getParameter("foodsName");
						list=dao.findSingle(foodsName);
						System.out.println(list.toString());
						req.setAttribute("list", list);
						req.getRequestDispatcher("before/caipin.jsp").forward(req, resp);
					}else if (action.equals("caixi")) {
				    	 //按菜系查询菜品信息
						List<foods> list=new ArrayList<foods>();
						int sortId=Integer.parseInt(req.getParameter("sortId"));
						list=dao.findSort(sortId);
						req.setAttribute("list", list);
						req.getRequestDispatcher("before/caipin.jsp").forward(req, resp);
					}else if (action.equals("foodsAdd")) {
						//添加菜品
						String foodsImage=(String)req.getParameter("foodsImage");
						String foodsName=(String)req.getParameter("foodsName");
						double foodsPrice=Double.parseDouble( req.getParameter("foodsPrice"));
						int  foodsHabitus=Integer.parseInt(req.getParameter("foodsHabitus")) ;
						String foodsRemark=(String)req.getParameter("foodsRemark");
						int  sortId=Integer.parseInt(req.getParameter("sortId")) ;
						//String image=req.getParameter("image");
						//String image2=foodsImage.substring(foodsImage.lastIndexOf("/"));
						//System.out.println(image2);
						foods food=new foods(0,foodsImage,foodsName,foodsPrice,foodsHabitus,foodsRemark,sortId);
					if (dao.foodsAdd(food)){
					 /*SmartUpload su=new SmartUpload();
						//初始化上传对象
						su.initialize(this.getServletConfig(), req, resp);
						
						try {
							//将上传文件保存在服务器临时文件夹
							su.upload();
							//获取所有上传文件集合
							Files files=su.getFiles();
							//遍历集合
							for(int i=0;i<files.getCount();i++){
								File file=files.getFile(i);
								//获取images文件夹在服务器的绝对路径
								String path=this.getServletContext().getRealPath("images");
								String path="F:\\java2\\java9kj\\backstage\\WebRoot\\images";
								//获取文件名
								String filename=file.getFileName();
								String suffix=filename.substring(filename.lastIndexOf("."));
								//定义新文件名
								String newName=foodsImage+i+suffix;
								//定义文件保存路径
								path=path+"\\"+foodsImage;
								//将文件另存到指定位置
								file.saveAs(path);							
								
							}
							
						} catch (SmartUploadException e) {
							 TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						String pat="D:\\图片\\菜品图\\"+foodsImage;
						String pat2="D:\\Jiangbin\\Workspaces\\restaurant\\WebRoot\\food_img\\"+foodsImage;
						File file1=new File(pat);
						File file2=new File(pat2);
						FileInputStream fin=new FileInputStream(file1);
						FileOutputStream fot=new  FileOutputStream(file2);
						byte bt[]=new byte[(int) file1.length()];
						fin.read(bt);
						fot.write(bt);
						fot.close();
						fin.close();
						jishu.js();
						int tj2=jishu.getAa();
						String tj1="恭喜您成功添加了";
						String tj3="个菜品";
						req.setAttribute("tj1", tj1);
						req.setAttribute("tj2", tj2);
						req.setAttribute("tj3", tj3);
						req.getRequestDispatcher("before/foodsAdd.jsp").forward(req, resp);
					}else{
						String tj4="对不起，程序出错，添加失败！！！";
						req.setAttribute("tj4", tj4);
						req.getRequestDispatcher("before/foodsAdd.jsp").forward(req, resp);
					  }
					 
					}/*else if(action.equals("foodsUpd")){
						//修改菜品信息
						int foodsId=Integer.parseInt(req.getParameter("foodsId"));
						String foodsImage=(String)req.getParameter("foodsImage");
						String foodsName=(String)req.getParameter("foodsName");
						double foodsPrice=Double.parseDouble( req.getParameter("foodsPrice"));
						int  foodsHabitus=Integer.parseInt(req.getParameter("foodsHabitus")) ;
						String foodsRemark=(String)req.getParameter("foodsRemark");
						int  sortId=Integer.parseInt(req.getParameter("sortId")) ;
						foods food=new foods(foodsId,foodsImage,foodsName,foodsPrice,foodsHabitus,foodsRemark,sortId);
					if(dao.foodsUpd(food)){
						String updd="恭喜您修改成功";
						req.setAttribute("updd", updd);
						List<foods> list=new ArrayList<foods>();
						list=dao.foodsAll();
						String curr="curr";
						//req.setAttribute("list", list);
						req.setAttribute("curr", curr);
						req.getRequestDispatcher("ShowfoodsByPage").forward(req, resp);
					}
					}*/
	

    }

 }

