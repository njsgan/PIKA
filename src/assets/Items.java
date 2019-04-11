package assets;

public class Items {
	private String code;
	private String name;
	private Integer price;
	private Integer stock;
	
	

	public Items(String code, String name, Integer price, Integer stock) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Integer getPrice() {
		return price;
	}



	public void setPrice(Integer price) {
		this.price = price;
	}



	public Integer getStock() {
		return stock;
	}



	public void setStock(Integer stock) {
		this.stock = stock;
	}



	public Items() {
		// TODO Auto-generated constructor stub
	}

}
