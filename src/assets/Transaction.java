package assets;

import java.util.ArrayList;

public class Transaction {
	private String id;
	private ArrayList<Purchase> purchases = new ArrayList<Purchase>();
	private UserCashier cashier;
	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	public Transaction(String id, UserCashier cashier) {
		super();
		this.id = id;
		this.cashier = cashier;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<Purchase> getPurchases() {
		return purchases;
	}
	public void setPurchases(ArrayList<Purchase> purchases) {
		this.purchases = purchases;
	}
	public void addPurchase(Purchase purchase) {
		purchases.add(purchase);
	}
	public UserCashier getCashier() {
		return cashier;
	}
	public void setCashier(UserCashier cashier) {
		this.cashier = cashier;
	}
	public Integer getRevenue() {
		Integer total = 0;
		for(Purchase purchase : purchases) {
			total+=purchase.getTotal();
		}
		return total;
	}
	

}
