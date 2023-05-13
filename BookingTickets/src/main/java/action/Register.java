package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.Svc;
import vo.UserInfoVo;

@WebServlet("/Register")
public class Register extends HttpServlet {
	//变量声明
	private static final long serialVersionUID=1L;
	private Svc svc=new Svc();
	public UserInfoVo userinfo;
	
	//doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//变量声明，获取前端数据用户名、手机号码、密码、确认密码
		String name=request.getParameter("name");
		String tel=request.getParameter("tel");
		String psw=request.getParameter("psw");
		
		//用于页面迁移
		RequestDispatcher dispatcher=null;
		String tip;
		HttpSession session=request.getSession();
		
		//重新编码，避免从前端获取的数据变成乱码
		byte fc[]=name.getBytes("iso-8859-1");
		name=new String(fc);
		byte tc[]=tel.getBytes("iso-8859-1");
		tel=new String(tc);
		byte fd[]=psw.getBytes("iso-8859-1");
		psw=new String(fd);
		
		//向数据库的用户信息表添加数据，返回添加成功或添加失败的信息
		tip=svc.insertUserInfos(name, tel, psw);
		System.out.println(tip);
		
		//如果注册成功自动登录
		if(tip.equals("success"))
		{
			userinfo=svc.SearchUserInfos(tel, psw);
			//向前端返回数据
			request.setAttribute("tip", tip);
			session.setAttribute("usertel", userinfo.getTel());
			session.setAttribute("userinfo", userinfo.getName());
			session.setAttribute("islogin", userinfo.getState());
			System.out.println(session.getAttribute("userinfo"));
			System.out.println(session.getAttribute("islogin"));
		
			//页面跳转
			dispatcher=request.getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);	
		}
		
		//如果注册失败向前端返回提示信息
		if(tip.equals("error"))
		{
			//页面跳转
			request.setAttribute("tip", tip);		
			dispatcher=request.getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
	}

	//doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		doPost(request,response);
	}
	
	//end
}
