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

@WebServlet("/Login")
public class Login extends HttpServlet {
	//变量声明
	private static final long serialVersionUID=1L;
	private Svc svc=new Svc();
	public UserInfoVo userinfo;
	
	//doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//变量声明，获取登录手机号和登录密码
		String tel=request.getParameter("logintel");
		String psw=request.getParameter("loginpsw");
		
		//用于页面迁移
		RequestDispatcher dispatcher=null;
		HttpSession session=request.getSession();
						
		//重新编码，避免从前端获取的数据变成乱码
			byte fc[]=tel.getBytes("iso-8859-1");
			tel=new String(fc);
			byte tc[]=psw.getBytes("iso-8859-1");
			psw=new String(tc);
		
		//根据手机号和密码查找数据库用户信息表，若有则登录成功返回用户信息，若没有则登录失败返回登录界面
			System.out.println(tel);
			System.out.println(psw);
		userinfo=svc.SearchUserInfos(tel, psw);
		
		//向前端返回数据
		request.setAttribute("login", userinfo.getState());
		if(userinfo.getState().equals("loginerror"))
		{
			session.setAttribute("islogin", null);//是否已登录
		}
		else
		{
			session.setAttribute("islogin", userinfo.getState());//是否已登录
		}
		
		session.setAttribute("usertel", userinfo.getTel());//手机号码
		session.setAttribute("userinfo", userinfo.getName());//用户名
		
		System.out.println(session.getAttribute("userinfo"));
		System.out.println(session.getAttribute("islogin"));
		
		//页面跳转		
		dispatcher=request.getServletContext().getRequestDispatcher("/index.jsp");		
     	dispatcher.forward(request, response);	
	}

	//doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		doPost(request,response);
	}
	
	//end
}
