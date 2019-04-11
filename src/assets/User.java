package assets;

public class User {
	private String fname;
	private String lname;
	private String address;
	private String phone;
	private Integer age;
	private Integer status;
	private String username;
	private String password;
	public User(String fname, String lname, String address, String phone, Integer age, Integer status, String username,
			String password) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.phone = phone;
		this.age = age;
		this.status = status;
		this.username = username;
		this.password = password;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
