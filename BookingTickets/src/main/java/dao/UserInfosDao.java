package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dbconnect.DBConnection;
import vo.UserInfoVo;


public class UserInfosDao {
	//变量声明
	private Connection connection;
	public String tip;
	public UserInfoVo userinfo;

	//构造函数，初始化，获取数据库接口
 	public UserInfosDao() {
		try {
			connection = DBConnection.getConnection();
			}catch(SQLException e)
			{
				e.getStackTrace();
			}
	}		
		
	//注册：向用户信息表添加数据，返回添加成功或添加失败的信息
	public String InsertUserInfos(String name,String tel,String psw){
			//清空String
			tip="";
			try {
				String sql="insert into userinfos(tel,name,psw) values(?,?,?);";
				//执行SQL语句
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,tel);
				pstmt.setString(2,name);
				pstmt.setString(3,psw);
				pstmt.executeUpdate();
				tip="success";//注册成功
			} catch (SQLException e) {
				tip="error";//注册失败
				e.printStackTrace();
			}
			return tip;
	}
			
	//登录：根据手机号码和密码从用户信息表搜索数据，若找到对应记录则返回登录成功的信息，反之返回登录失败的信息
	public UserInfoVo SearchUserInfos(String tel,String psw){
			//清空userinfo
			userinfo=new UserInfoVo();
			try {
				String sql="select * from userinfos where tel=? and psw=?;";
				//执行SQL语句
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,tel);
				pstmt.setString(2,psw);
				ResultSet rs = pstmt.executeQuery();
				//若有结果（有且只有一条记录）
				if(rs.next()) 
				{
					tip="loginsuccess";
					userinfo.setTel(rs.getString("tel"));
					userinfo.setName(rs.getString("name"));
					userinfo.setPsw(rs.getString("psw"));
					userinfo.setState(tip);
				}
				else 
				{
					tip="loginerror";
					userinfo.setTel("");
					userinfo.setName("");
					userinfo.setPsw("");
					userinfo.setState(tip);
				}
				
			} catch (SQLException e) {
					e.printStackTrace();
			}
			return userinfo;
	}

	//订票：根据手机号码从用户信息表搜索数据，返回给OrderServlet
	public UserInfoVo SearchUserInfosByTel(String tel){
			//清空userinfo
			userinfo=new UserInfoVo();
			try {
					String sql="select tel,name from userinfos where tel=?;";
					//执行SQL语句
					PreparedStatement pstmt = connection.prepareStatement(sql);
					pstmt.setString(1,tel);					
					ResultSet rs = pstmt.executeQuery();
					//若有结果（有且只有一条记录）
					if(rs.next()) 
					{		
						userinfo.setState("");
						userinfo.setTel(rs.getString("tel"));
						userinfo.setName(rs.getString("name"));
						userinfo.setPsw("");
					}
					else
					{
						userinfo.setState("");
						userinfo.setTel("");
						userinfo.setName("");
						userinfo.setPsw("");
					}
				} catch (SQLException e) {
						e.printStackTrace();
				}
				return userinfo;
		}
	
	//修改密码：根据用户提供的手机号和新旧密码修改用户信息表，返回修改成功或失败的信息
		public String UpdateUserInfos(String tel,String psw,String newpsw){
				//清空String
				tip="";
				//变量声明
				int i=0;				
				try {
					String sql=" update userinfos set psw=? where tel=? and psw=?;";
					//执行SQL语句
					PreparedStatement pstmt = connection.prepareStatement(sql);
					pstmt.setString(1,newpsw);
					pstmt.setString(2,tel);
					pstmt.setString(3,psw);
					i=pstmt.executeUpdate();					
				} catch (SQLException e) {					
					e.printStackTrace();
				}
				if(i==1) 
				{
					tip="success";//修改成功
				}
				else
				{
					tip="error";//修改失败
				}
				
				return tip;
		}
		
		
	//end
}
