package service;

import java.util.List;

import dao.BusTicketsDao;
import dao.OrdersDao;
import dao.UserInfosDao;
import vo.BusTicketVo;
import vo.OrderVo;
import vo.UserInfoVo;

//各个表格操作方法的接口（DAO层的简化）
public class Svc {
	//变量声明
	private BusTicketsDao busticketsDAO = new BusTicketsDao();
	private UserInfosDao userinfoDAO = new UserInfosDao();
	private OrdersDao orderDAO = new OrdersDao();
	public int i=0;
	
	//构造函数
	public Svc() {}
	
	//根据fromCity、toCity、fromDate查找车票信息(搜索）
	public List<BusTicketVo> getBusTickets(String fc,String tc,String fd) {
		return busticketsDAO.getBusTickets(fc,tc,fd);
	}
	
	//查找所有车票信息（搜索）
	public List<BusTicketVo> getAllBusTickets(){
		return busticketsDAO.getAllBusTickets();
	}
	
	//返回注册成功或失败的信息（注册）
	public String insertUserInfos(String name,String tel,String psw){
		return userinfoDAO.InsertUserInfos(name,tel,psw);
	}
	
	//返回登录成功或失败的信息（登录）
	public UserInfoVo SearchUserInfos(String tel,String psw){
		return userinfoDAO.SearchUserInfos(tel,psw);
	}
	
	//（订票）返回已登录用户的信息
	public UserInfoVo SearchOneUserInfo(String tel) {
		return userinfoDAO.SearchUserInfosByTel(tel);
	}
	
	//（订票）返回已登录用户的信息
	public BusTicketVo SearchOneBusticket(String id) {
		return busticketsDAO.SearchBusticketsById(id);
	}
	
	//（订票）返回订票成功或失败的信息
	public String InsertOrders(String id,String city,String station,String time,
			String price,String user,String tel,String num,String costtime,String date) {
		return orderDAO.InsertOrders(id, city, station, time, price, user, tel, num,costtime,date);
	}
	
	//查看订单信息
	public List<OrderVo> findOrdersByTel2(String tel,String state) {
		return orderDAO.findOrdersByTel2(tel,state);
	}
	
	//查看订单信息
	public List<OrderVo> findOrdersByTel(String tel) {
		return orderDAO.findOrdersByTel(tel);
	}
	
	//修改密码
	public String UpdateUserInfos(String tel,String psw,String newpsw){
		return userinfoDAO.UpdateUserInfos(tel, psw, newpsw);	
	}
		
	//退票
	public String RefundOrder(String id) {
		return orderDAO.deleteOrder(id);
	}
		
	//end
}
