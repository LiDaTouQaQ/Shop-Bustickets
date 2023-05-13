package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnect.DBConnection;
import vo.OrderVo;

public class OrdersDao {
		//变量声明
		private Connection connection;
		public String ordertip;
		public String refundtip;
		public OrderVo order=new OrderVo();
		public List<OrderVo> orders=new ArrayList<OrderVo>();
		
		//构造函数，初始化，获取数据库接口
	 	public OrdersDao() {
			try {
				connection = DBConnection.getConnection();
				}catch(SQLException e)
				{
					e.getStackTrace();
				}
		}	
	 	
	 	//订票：向订单表添加记录，返回添加成功或添加失败的信息
	 	public String InsertOrders(String id,String city,String station,String time,
	 			String price,String user,String tel,String num,
	 			String costtime,String ordertime){
	 		//清空String
	 		ordertip="";
	 		try {
	 			//id city station time price user tel num costtime ordertime 
	 			String sql="insert into orders values(?,?,?,?,?,?,?,?,?,?,?);";
	 			//执行SQL语句
	 			PreparedStatement pstmt = connection.prepareStatement(sql);
	 			pstmt.setString(1,id);
	 			pstmt.setString(2,city);
	 			pstmt.setString(3,station);
				pstmt.setString(4,time);
				pstmt.setString(5,price);
				pstmt.setString(6,user);
				pstmt.setString(7,tel);
				pstmt.setString(8, num);
				pstmt.setString(9, costtime);
				pstmt.setString(10, ordertime);
				pstmt.setString(11, "now");
				pstmt.executeUpdate();
				ordertip="success";//订票成功
				} catch (SQLException e) {
					ordertip="error";//订票失败
					e.printStackTrace();
				}
				return ordertip;
		}
	 	
	 	//根据手机号查看订单信息
	 	public List<OrderVo> findOrdersByTel2(String tel,String state) {
	 		//清空List
	 		orders.clear();
	 		try {
	 			String sql="select * from orders where tel=? and state=?;";
	 			//执行SQL语句
	 			PreparedStatement pstmt = connection.prepareStatement(sql);
	 			pstmt.setString(1,tel); 	
	 			pstmt.setString(2, state);
				//获取结果集
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) 
				{
					order=new OrderVo();
					order.setId(rs.getString("id"));
					order.setCity(rs.getString("city"));
					order.setStation(rs.getString("station"));	
					order.setTime(rs.getString("time"));
					order.setPrice(rs.getInt("price"));
					order.setUser(rs.getString("user"));
					order.setTel(rs.getString("tel"));
					order.setNum(rs.getInt("num"));	
					order.setCosttimme(rs.getString("costtime"));
					order.setOrdertime(rs.getString("ordertime"));
					order.setState(rs.getString("state"));
					System.out.println("查询循序");
					System.out.println(rs.getString("id"));
					orders.add(order);
				}
				} catch (SQLException e) {					
					e.printStackTrace();
				}	 		
	 		return orders;
	 	}
	 	
	 	//根据手机号查看订单信息
	 	public List<OrderVo> findOrdersByTel(String tel) {
	 		//清空List
	 		orders.clear();
	 		try {
	 			String sql="select * from orders where tel=?;";
	 			//执行SQL语句
	 			PreparedStatement pstmt = connection.prepareStatement(sql);
	 			pstmt.setString(1,tel); 	
				//获取结果集
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) 
				{
					order=new OrderVo();
					order.setId(rs.getString("id"));
					order.setCity(rs.getString("city"));
					order.setStation(rs.getString("station"));	
					order.setTime(rs.getString("time"));
					order.setPrice(rs.getInt("price"));
					order.setUser(rs.getString("user"));
					order.setTel(rs.getString("tel"));
					order.setNum(rs.getInt("num"));	
					order.setCosttimme(rs.getString("costtime"));
					order.setOrdertime(rs.getString("ordertime"));
					order.setState(rs.getString("state"));
					System.out.println("查询循序");
					System.out.println(rs.getString("id"));
					orders.add(order);
				}
				} catch (SQLException e) {					
					e.printStackTrace();
				}	 		
	 		return orders;
	 	}
	 	
	 	//根据订单号退票
	 	public String deleteOrder(String id) {
	 		try {
	 			String sql="delete from orders where id=?;";
	 			//执行SQL语句
	 			PreparedStatement pstmt = connection.prepareStatement(sql);
	 			pstmt.setString(1,id); 			
				pstmt.executeUpdate();
				refundtip="refundsuccess";
				} catch (SQLException e) {
					refundtip="refunderror";
					e.printStackTrace();
				}	 		
	 		return refundtip;
	 	}

	 	//end
}
