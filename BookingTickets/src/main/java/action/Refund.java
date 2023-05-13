package action;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Svc;
import vo.BusTicketVo;
import vo.OrderVo;
import vo.UserInfoVo;


@WebServlet("/Refund")
public class Refund extends HttpServlet {
	//变量声明
	private static final long serialVersionUID=1L;
	private Svc svc=new Svc();
	
	//doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			//变量声明，车票id，用户手机号
			String id=request.getParameter("id");		
			
			//用于页面迁移
			RequestDispatcher dispatcher=null;
			String str="";
			
			//重新编码，避免从前端获取的数据变成乱码
			byte d[]=id.getBytes("iso-8859-1");
			id=new String(d);
					
			//返回退票成功或失败的信息
			str=svc.RefundOrder(id);

			//页面跳转
				request.setAttribute("refund",str);
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