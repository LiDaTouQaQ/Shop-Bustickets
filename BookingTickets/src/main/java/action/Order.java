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
import vo.BusTicketVo;
import vo.OrderVo;
import vo.UserInfoVo;


@WebServlet("/Order")
public class Order extends HttpServlet {
	//变量声明
	private static final long serialVersionUID=1L;
	public BusTicketVo busticket=new BusTicketVo();
	public UserInfoVo userinfo=new UserInfoVo();
	public OrderVo order=new OrderVo();
	private Svc svc=new Svc();
	
	//doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			//变量声明，车票id，用户手机号
			String id=request.getParameter("id");
			String usertel=request.getParameter("usertel");
			String userlogin=request.getParameter("userislogin");
			String str="loginsuccess";
			//用于页面迁移
			RequestDispatcher dispatcher=null;
			
			//重新编码，避免从前端获取的数据变成乱码
			byte d[]=id.getBytes("iso-8859-1");
			id=new String(d);
			byte tel[]=usertel.getBytes("iso-8859-1");
			usertel=new String(tel);
			byte login[]=userlogin.getBytes("iso-8859-1");
			userlogin=new String(login);
			
			
			//向订单表写入
			//根据id查数据库返回车票信息，弹窗让用户填写手机号码表示乘车人，可以选择订票张数
			//根据车票张数实时生成价格进行支付（待定）可以只订票，乘车时再付款
			//（或者通过名字+身份证注册的时候可以添加）
//			System.out.println(id);
//			System.out.println(usertel);
//			System.out.println(userlogin);
//			System.out.println(busticket.getPrice());
//			System.out.println(busticket.getFromDate()+"/"+busticket.getFromTime());
//			System.out.println(busticket.getFromCity()+"-"+busticket.getToCity());

			//如果已经登录，则页面跳转
			if(userlogin.equals(str))
			{
				//从数据库获取车票和用户信息
				busticket=svc.SearchOneBusticket(id);
				userinfo=svc.SearchOneUserInfo(usertel);

				//赋值，为向前端返回数据准备
				String tmp=userinfo.getTel();//手机号
				tmp=tmp.substring(tmp.length()-4);
				order.setId("D"+busticket.getId()+tmp);//订单id:D+车票号+手机号后四位
				order.setCity(busticket.getFromCity()+"-"+busticket.getToCity());//出发地和目的地
				order.setStation(busticket.getFromStation()+"-"+busticket.getToStation());//始发站和终点站
				order.setTime(busticket.getFromDate()+"/"+busticket.getFromTime());//出发日期和时间
				order.setPrice(busticket.getPrice());//车票单价
				order.setUser(userinfo.getName());//用户名
				order.setTel(userinfo.getTel());//手机号码
				order.setNum(0);
				order.setCosttimme(busticket.getCostTime());
				//向前端返回数据
				request.setAttribute("orderid",order.getId());//不显示
				request.setAttribute("city",order.getCity());
				request.setAttribute("station",order.getStation());
				request.setAttribute("time",order.getTime());
				request.setAttribute("costtime",order.getCosttime());
				request.setAttribute("price",busticket.getPrice());
				request.setAttribute("user",userinfo.getName());
				request.setAttribute("tel",userinfo.getTel());
				System.out.println("order:");
				System.out.println(request.getAttribute("orderid"));
				System.out.println(request.getAttribute("city"));
				
				dispatcher=request.getServletContext().getRequestDispatcher("/order.jsp");
				dispatcher.forward(request, response);	
			}
			//如果未登录需要先登录
			if(!userlogin.equals(str))
			{
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

