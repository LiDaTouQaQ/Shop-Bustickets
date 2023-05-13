package vo;

// id | fromCity | toCity | fromStation | toStation    
// fromDate   | strDate    | fromTime | costTime
public class BusTicketVo {
	//变量声明
	private int id;
	private String fromCity;
	private String toCity;
	private String fromStation;
	private String toStation;
	private String fromDate;
	private String strDate;
	private String fromTime;
	private String costTime;
	private int price;
	
	//构造函数
	public BusTicketVo() {}
	
	//get
	public int getId() {
		return id;
	}
	public String getFromCity() {
		return fromCity;
	}
	public String getToCity() {
		return toCity;
	}
	public String getFromStation() {
		return fromStation;
	}
	public String getToStation() {
		return toStation;
	}
	public String getFromDate() {
		return fromDate;
	}
	public String getStrDate() {
		return strDate;
	}
	public String getFromTime() {
		return fromTime;
	}
	public String getCostTime() {
		return costTime;
	}
	public int getPrice() {
		return price;
	}
	
	//set
	public void setId(int id) {
		this.id = id;
	}
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}
	public void setToStation(String toStation) {
		this.toStation = toStation;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public void setCostTime(String costTime) {
		this.costTime = costTime;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	//end
}
