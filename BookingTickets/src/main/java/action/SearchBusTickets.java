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
import vo.BusTicketVo;

@WebServlet("/SearchBusTickets")
public class SearchBusTickets extends HttpServlet{
	//变量声明
	private static final long serialVersionUID=1L;
	private List<BusTicketVo> bustickets=new ArrayList<BusTicketVo>();
	private Svc svc=new Svc();
	
	//doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//获取前端数据（出发地、目的地、出发日期
		String fromCity=request.getParameter("select_from");
		String toCity=request.getParameter("select_to");
		String fromDate=request.getParameter("date");
		String isEmpty="";
		
		//用于页面迁移
		RequestDispatcher dispatcher=null;
		
		//重新编码，避免从前端获取的数据变成乱码
		byte fc[]=fromCity.getBytes("iso-8859-1");
		fromCity=new String(fc);
		byte tc[]=toCity.getBytes("iso-8859-1");
		toCity=new String(tc);
		byte fd[]=fromDate.getBytes("iso-8859-1");
		fromDate=new String(fd);
		
		//通过service获取数据库信息
		if(fromCity==""||toCity==""||fromDate=="")
		{
			//出发城市、到达城市或日期为空时显示所有车票数据（下拉列表形式内容不会为空
			bustickets=svc.getAllBusTickets();
		}
		else {
			bustickets=svc.getBusTickets(fromCity, toCity, fromDate);
		}
		
		
		//向前端返回数据
		if(bustickets.isEmpty()) 
		{
			isEmpty="empty";
		}
		request.setAttribute("empty", isEmpty);
		request.setAttribute("bustickets", bustickets);
		dispatcher=request.getServletContext().getRequestDispatcher("/search.jsp");
		dispatcher.forward(request, response);
		
	
	}
	
	//doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		doPost(request,response);
	}
}
