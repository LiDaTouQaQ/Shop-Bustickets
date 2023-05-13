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


@WebServlet("/SearchOrders")
public class SearchOrders extends HttpServlet {
	//变量声明
	private static final long serialVersionUID=1L;
	public List<OrderVo> orders=new ArrayList<OrderVo>();
	private Svc svc=new Svc();
	public String isEmpty="";
	
	//doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			//获取前端数据		
			String state=request.getParameter("select");
			String tel=request.getParameter("usertel");
			System.out.println(state);
			System.out.println(tel);
			
			//用于页面迁移
			RequestDispatcher dispatcher=null;
			
			//重新编码，避免从前端获取的数据乱码
			byte s[]=state.getBytes("iso-8859-1");
			state=new String(s);
			byte t[]=tel.getBytes("iso-8859-1");
			tel=new String(t);
			
			//获取订单信息
			if(state.equals("now")||state.equals("done"))
			{
				orders=svc.findOrdersByTel2(tel, state);
				System.out.println("111111");
			}
			if(state.equals("all"))
			{
				orders=svc.findOrdersByTel(tel);
			}
			
			//向前端返回数据
			if(orders.size()==0) {
				isEmpty="empty";
			}
			if(state.equals("now")) {state="未完成订单";}
			if(state.equals("done")) {state="已完成订单";}
			if(state.equals("all")) {state="全部订单";}
			
			request.setAttribute("state", state);
			request.setAttribute("empty", isEmpty);
			request.setAttribute("orders", orders);
			System.out.println(isEmpty);
			System.out.println(orders.size());
			System.out.println(orders.get(0).getId());
			System.out.println(orders.get(1).getId());
			dispatcher=request.getServletContext().getRequestDispatcher("/orders.jsp");				
			dispatcher.forward(request, response);
	}
		
	//doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			doPost(request,response);
	}

}
