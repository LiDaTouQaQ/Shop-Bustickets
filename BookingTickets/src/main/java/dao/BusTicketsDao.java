package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnect.DBConnection;
import vo.BusTicketVo;

//对表格BusTickets的查找
public class BusTicketsDao {
	//变量声明
	public List<BusTicketVo> bustickets=new ArrayList<BusTicketVo>();
	public BusTicketVo busticket=new BusTicketVo();
	private Connection connection;

	//构造函数，初始化，获取数据库接口
	public BusTicketsDao() {
		try {
			connection = DBConnection.getConnection();
			}catch(SQLException e)
			{
					e.getStackTrace();
			}
	}		
	
	//根据fromCity、toCity、fromDate查找车票信息
	public List<BusTicketVo> getBusTickets(String fc,String tc,String fd){
		//清空List
		bustickets.clear();
		try {
			String sql="select * from bustickets where fromCity=? and toCity=? and fromDate=?;";
			//执行SQL语句
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,fc);
			pstmt.setString(2,tc);
			pstmt.setString(3,fd);
			ResultSet rs = pstmt.executeQuery();
			//获取结果集
			while (rs.next()) {
				BusTicketVo busticket = new BusTicketVo();
				//和表格中的列名不一致时会导致flights长度为0即没有flight成功的添加进列表flights
				// id | fromCity | toCity | fromStation | toStation    
				// fromDate   | strDate    | fromTime | costTime
				busticket.setId(rs.getInt("id"));
				busticket.setFromCity(rs.getString("fromCity"));
				busticket.setToCity(rs.getString("toCity"));
				busticket.setFromStation(rs.getString("fromStation"));
				busticket.setToStation(rs.getString("toStation"));
				busticket.setFromDate(rs.getDate("fromDate").toString());
				busticket.setStrDate(rs.getDate("strDate").toString());
				busticket.setFromTime(rs.getTime("fromTime").toString());
				busticket.setCostTime(rs.getString("costTime"));
				busticket.setPrice(rs.getInt("price"));
				//将单条航班信息写入列表
				bustickets.add(busticket);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bustickets;
	}
		
	//查找所有车票信息
	public List<BusTicketVo> getAllBusTickets(){
			//清空List
			bustickets.clear();
			try {
				String sql="select * from bustickets;";
				//执行SQL语句
				PreparedStatement pstmt = connection.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				//获取结果集
				while (rs.next()) {
					BusTicketVo busticket = new BusTicketVo();
					//和表格中的列名不一致时会导致flights长度为0即没有flight成功的添加进列表flights
					// id | fromCity | toCity | fromStation | toStation    
					// fromDate   | strDate    | fromTime | costTime
					busticket.setId(rs.getInt("id"));
					busticket.setFromCity(rs.getString("fromCity"));
					busticket.setToCity(rs.getString("toCity"));
					busticket.setFromStation(rs.getString("fromStation"));
					busticket.setToStation(rs.getString("toStation"));
					busticket.setFromDate(rs.getDate("fromDate").toString());
					busticket.setStrDate(rs.getDate("strDate").toString());
					busticket.setFromTime(rs.getTime("fromTime").toString());
					busticket.setCostTime(rs.getString("costTime"));
					busticket.setPrice(rs.getInt("price"));
					//将单条航班信息写入列表
					bustickets.add(busticket);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return bustickets;
		}

	//订票：根据车票id从车票信息表搜索数据，返回给OrderServlet
	public BusTicketVo SearchBusticketsById(String id){			
			try {
				String sql="select * from bustickets where id=?;";
				//执行SQL语句
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,id);		
				ResultSet rs = pstmt.executeQuery();
				//获取结果集
				while (rs.next()) {					
					busticket.setId(rs.getInt("id"));
					busticket.setFromCity(rs.getString("fromCity"));
					busticket.setToCity(rs.getString("toCity"));
					busticket.setFromStation(rs.getString("fromStation"));
					busticket.setToStation(rs.getString("toStation"));
					busticket.setFromDate(rs.getDate("fromDate").toString());
					busticket.setStrDate("");
					busticket.setFromTime(rs.getTime("fromTime").toString());
					busticket.setCostTime(rs.getString("costTime"));
					busticket.setPrice(rs.getInt("price"));					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return busticket;
		}
			
	//end
}
