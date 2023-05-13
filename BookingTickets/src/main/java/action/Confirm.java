package action;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Svc;


@WebServlet("/Confirm")
public class Confirm extends HttpServlet {
	//变量声明
	private static final long serialVersionUID=1L;
	private Svc svc=new Svc();
	
	//doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			//获取前端数据			
			String id=request.getParameter("id");//订单id
			String city=request.getParameter("city");//出发地和目的地
			String station=request.getParameter("station");//始发站和终点站
			String time=request.getParameter("time");//出发日期和时间
			String price=request.getParameter("price");//单张票价
			String user=request.getParameter("user");//订票用户
			String tel=request.getParameter("tel");//手机号
			String num=request.getParameter("num");//车票张数
			String costtime=request.getParameter("costtime");//耗时		
			
			//用于页面迁移
			RequestDispatcher dispatcher=null;
			
			//重新编码，避免从前端获取的数据变成乱码
			byte i[]=id.getBytes("iso-8859-1");
			id=new String(i);
			byte c[]=city.getBytes("iso-8859-1");
			city=new String(c);
			byte s[]=station.getBytes("iso-8859-1");
			station=new String(s);
			byte t[]=time.getBytes("iso-8859-1");
			time=new String(t);
			byte p[]=price.getBytes("iso-8859-1");
			price=new String(p);
			byte u[]=user.getBytes("iso-8859-1");
			user=new String(u);
			byte te[]=tel.getBytes("iso-8859-1");
			tel=new String(te);
			byte n[]=num.getBytes("iso-8859-1");
			num=new String(n);
			byte ct[]=costtime.getBytes("iso-8859-1");
			costtime=new String(ct);
			
			//将信息整合写入订单表
			SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取当前时间
			String ordertip=svc.InsertOrders(id, city, station, time, price, user, tel, num,costtime,date.format(new Date()));
			
			//向前端反馈信息
			request.setAttribute("ordertip", ordertip);
			dispatcher=request.getServletContext().getRequestDispatcher("/index.jsp");		
			dispatcher.forward(request, response);	
			
			//控制台测试
			System.out.println(ordertip);
			System.out.println("添加成功");
			System.out.println(id+'\n'+city+'\n'+station+'\n'+time+'\n'+price);
			System.out.println(user+'\n'+tel+'\n'+num+'\n'+costtime+'\n'+date.format(new Date()));
			}
		
	//doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			doPost(request,response);
	}

}