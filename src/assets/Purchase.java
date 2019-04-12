package assets;

public class Purchase {
	
	private Item item;
	private Integer qty;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Purchase(Item item, Integer qty) {
		super();
		this.item = item;
		this.qty = qty;
	}

	public Purchase() {
		// TODO Auto-generated constructor stub
	}

}
