package assets;

import java.util.Random;

public class Member {
	
	private String name;
	private String UID;
	private String address;
	private String phone;
	private Integer point;
	
	Random rand = new Random();
	
	public Member(String name, String uID, String address, String phone, Integer point) {
		super();
		this.name = name;
		UID = uID;
		this.address = address;
		this.phone = phone;
		this.point = point;
	}
	
	public Member(String name, String address, String phone) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.point = 0;
		//generate UID
		String ID = "MEM";
		for(int i = 1; i<=12; i++) {
			ID+=rand.nextInt(9);
		}
		this.UID = ID;
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
