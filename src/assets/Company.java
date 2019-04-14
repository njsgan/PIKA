package assets;

public class Company {
	
	private String name;
	private String address;
	private String logo;
	private String phone;
	private String fax;

	public Company(String name, String address, String logo, String phone, String fax) {
		super();
		this.name = name;
		this.address = address;
		this.logo = logo;
		this.phone = phone;
		this.fax = fax;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	

}
