package assets;

import java.util.ArrayList;

public class Transaction {
	private String id;
	private ArrayList<Purchase> purchases = new ArrayList<Purchase>();
	private UserCashier cashier;
	private String cardNum;
	private String approvalCode;
	private String memID;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	public String getMemID() {
		return memID;
	}
	public void setMemID(String memID) {
		this.memID = memID;
	}
	public Transaction(String id, UserCashier cashier) {
		super();
		this.id = id;
		this.cashier = cashier;
	}
	
	public Transaction(String id, UserCashier cashier, String memID) {
		super();
		this.id = id;
		this.cashier = cashier;
		this.memID = memID;
	}
	public Transaction(String id, UserCashier cashier, String cardNum, String approvalCode, String memID) {
		super();
		this.id = id;
		this.cashier = cashier;
		this.cardNum = cardNum;
		this.approvalCode = approvalCode;
		this.memID = memID;
	}
	public Transaction(String id, UserCashier cashier, String cardNum, String approvalCode) {
		super();
		this.id = id;
		this.cashier = cashier;
		this.cardNum = cardNum;
		this.approvalCode = approvalCode;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getApprovalCode() {
		return approvalCode;
	}
	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
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
