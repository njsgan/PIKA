package assets;

import java.util.ArrayList;

public class Transaction {
	private String id;
	private ArrayList<Purchase> purchases = new ArrayList<Purchase>();
	private Cashier cashier;
	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	public Transaction(String id, ArrayList<Purchase> purchases, Cashier cashier) {
		super();
		this.id = id;
		this.purchases = purchases;
		this.cashier = cashier;
	}
	
	

}
