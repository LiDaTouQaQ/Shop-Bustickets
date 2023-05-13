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
import javax.servlet.http.HttpSession;

import service.Svc;

@WebServlet("/ChangePsw")
public class ChangePsw extends HttpServlet{
		//变量声明
		private static final long serialVersionUID=1L;
		private Svc svc=new Svc();
		public String changetip="";
		
		//doPost
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException{
				//获取前端数据	
			    HttpSession session=request.getSession();
				String tel=(String)session.getAttribute("usertel");
				String psw=request.getParameter("psw");
				String newpsw=request.getParameter("newpsw");
				
				//用于页面迁移
				RequestDispatcher dispatcher=null;
				
				//重新编码，避免从前端获取的数据变成乱码
				byte fc[]=tel.getBytes("iso-8859-1");
				tel=new String(fc);
				byte tc[]=psw.getBytes("iso-8859-1");
				psw=new String(tc);
				byte n[]=newpsw.getBytes("iso-8859-1");
				newpsw=new String(n);
				
				//从后端获取信息
				changetip=svc.UpdateUserInfos(tel, psw, newpsw);
			
				//向前端反馈信息
				request.setAttribute("changetip", changetip);
				dispatcher=request.getServletContext().getRequestDispatcher("/person.jsp");		
				dispatcher.forward(request, response);	
				
				//控制台测试
				System.out.println(changetip);
				}
			
		//doGet
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException{
				doPost(request,response);
		}
		
}
