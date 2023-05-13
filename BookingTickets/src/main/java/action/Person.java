package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Svc;
import vo.OrderVo;


@WebServlet("/Person")
public class Person extends HttpServlet {
	//变量声明
	private static final long serialVersionUID=1L;
	public List<OrderVo> orders=new ArrayList<OrderVo>();
	private Svc svc=new Svc();
	public String isEmpty="";
	
	//doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			//获取前端数据（出发地、目的地、出发日期			
			String username=request.getParameter("username");
			String islogin=request.getParameter("islogin");
			String tel=request.getParameter("usertel");
			
			System.out.println(username);
			System.out.println(islogin);
			
			//用于页面迁移
			RequestDispatcher dispatcher=null;
			
			//重新编码，避免从前端获取的数据变成乱码
			byte u[]=username.getBytes("iso-8859-1");
			username=new String(u);
			byte l[]=islogin.getBytes("iso-8859-1");
			islogin=new String(l);
			byte t[]=tel.getBytes("iso-8859-1");
			tel=new String(t);

			//获取订单信息
			if(!islogin.equals("loginsuccess"))
			{
				dispatcher=request.getServletContext().getRequestDispatcher("/index.jsp");				
			}
			if(islogin.equals("loginsuccess"))
			{
				orders=svc.findOrdersByTel(tel);
				if(orders.size()==0) {
					isEmpty="empty";
				}
				
				//返回数据
				request.setAttribute("empty", isEmpty);
				
				request.setAttribute("orders", orders);
				//request.setAttribute("username", username);
				dispatcher=request.getServletContext().getRequestDispatcher("/person.jsp");				
			}
				
				dispatcher.forward(request, response);
			}
		
	//doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			doPost(request,response);
	}

}
