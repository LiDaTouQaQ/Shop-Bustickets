package vo;

public class OrderVo {
	//变量声明,订单id，车票信息，用户信息
	private String id;
	private String city;	
	private String station;		
	private String time;	
	private int price;
	private String user;
	private String tel;
	private int num;
	private String costtime;
	private String ordertime;
	private String state;
	
	//构造函数
	public OrderVo() {}

	//get
	public String getId() {
		return id;
	}
	public String getCity() {
		return city;
	}
	public String getStation() {
		return station;
	}
	public String getTime() {
		return time;
	}
	public int getPrice() {
		return price;
	}
	public String getUser() {
		return user;
	}
	public String getTel() {
		return tel;
	}
	public int getNum() {
		return num;
	}
	public String getCosttime() {
		return costtime;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public String getState() {
		return state;
	}

	//set
	public void setId(String id) {
		this.id = id;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setCosttimme(String costtime) {
		this.costtime = costtime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public void setState(String state) {
		this.state = state;
	}

	//end
}
