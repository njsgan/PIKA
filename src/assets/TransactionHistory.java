package assets;

import java.util.ArrayList;

public class TransactionHistory {
	
	private String ID;
	private String cashier;
	private ArrayList<String> items = new ArrayList<String>();
	private ArrayList<Integer> prices = new ArrayList<Integer>();
	private ArrayList<Integer> qtys = new ArrayList<Integer>();
	
	public void addItem(String item, Integer price, Integer qty) {
		items.add(item);
		prices.add(price);
		qtys.add(qty);
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getCashier() {
		return cashier;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
	}

	public ArrayList<String> getItems() {
		return items;
	}

	public void setItems(ArrayList<String> items) {
		this.items = items;
	}

	public ArrayList<Integer> getPrices() {
		return prices;
	}

	public void setPrices(ArrayList<Integer> prices) {
		this.prices = prices;
	}

	public TransactionHistory(String iD, String cashier) {
		super();
		ID = iD;
		this.cashier = cashier;
	}
	
	public Integer getRevenue() {
		int total = 0;
		for(int i = 0; i<items.size(); i++) {
			total+= prices.get(i)*qtys.get(i);
		}
		return total;
	}

	public TransactionHistory() {
		// TODO Auto-generated constructor stub
	}

}
