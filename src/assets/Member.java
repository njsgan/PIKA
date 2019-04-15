package assets;

public class Member {
	
	private String name;
	private String UID;
	private String address;
	private String phone;
	private Integer point;
	
	public Member(String name, String uID, String address, String phone, Integer point) {
		super();
		this.name = name;
		UID = uID;
		this.address = address;
		this.phone = phone;
		this.point = point;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUID() {
		return UID;
	}

	public void setUID(String uID) {
		UID = uID;
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

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}
	
	

}
