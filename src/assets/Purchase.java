package assets;

public class Purchase {
	
	private Items item;
	private Integer qty;

	public Items getItem() {
		return item;
	}

	public void setItem(Items item) {
		this.item = item;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Purchase(Items item, Integer qty) {
		super();
		this.item = item;
		this.qty = qty;
	}

	public Purchase() {
		// TODO Auto-generated constructor stub
	}

}
