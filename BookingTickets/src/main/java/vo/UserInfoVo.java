package vo;

public class UserInfoVo {
	//变量声明state(标识用户是否已经登录)，用户信息表属性 手机号码tel、用户名name、密码psw
	private String state;
	private String tel;
	private String name;
	private String psw;
	
	//构造函数
	public UserInfoVo() {}

	//get
	public String getState() {
		return state;
	}
	public String getTel() {
		return tel;
	}
	public String getName() {
		return name;
	}
	public String getPsw() {
		return psw;
	}
	
	//set
	public void setState(String state) {
		this.state = state;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	
	//end
}
