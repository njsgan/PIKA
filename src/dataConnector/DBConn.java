package dataConnector;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import assets.Item;
import assets.Member;
import assets.Company;
import assets.Purchase;
import assets.Transaction;
import assets.TransactionHistory;
import assets.User;
import assets.UserCashier;
import assets.UserSupervisor;
import dataContainer.Container;

public class DBConn {
	
	private static String myDriver = "com.mysql.jdbc.Driver";
    private static String myUrl = "jdbc:mysql://localhost/pikapos";
    private static String user = "root";
    private static String pass = "";
	
	 public static String md5(String password) {
	        final byte[] defaultBytes = password.getBytes();
	        try {
	            final MessageDigest md5MsgDigest = MessageDigest.getInstance("MD5");
	            md5MsgDigest.reset();
	            md5MsgDigest.update(defaultBytes);
	            final byte messageDigest[] = md5MsgDigest.digest();
	            final StringBuffer hexString = new StringBuffer();
	            for (final byte element : messageDigest) {
	                final String hex = Integer.toHexString(0xFF & element);
	                if (hex.length() == 1) {
	                    hexString.append('0');
	                }
	                hexString.append(hex);
	            }
	            password = hexString + "";
	        } catch (final NoSuchAlgorithmException nsae) {
	            nsae.printStackTrace();
	        }
	        return password;
	    }
	
	public static User login(String username, String password) {
		try {
			String encrypted = md5(password);
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, user, pass);
		    
		    //query
		    String query = "SELECT * FROM users where username = '"+username+"' and password = '"+encrypted+"'";
		    
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    while (rs.next()) {
		    	String fname = rs.getString("fname");
		    	String lname = rs.getString("lname");
		    	String user_name = rs.getString("username");
		    	String pass_word = rs.getString("password");
		    	String address = rs.getString("address");
		    	String phone = rs.getString("phone");
		    	Integer age = rs.getInt("age");
		    	Integer status = rs.getInt("status");
		    	if(status == 1) return new UserCashier(fname, lname, address, phone, age, status, user_name, pass_word);
		    	else if (status == 2) return new UserSupervisor(fname, lname, address, phone, age, status, user_name, pass_word);
		    }
		    return null;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
		return null;
	}
	
	public static Company readData(){
		try {

		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, user, pass);
		    
		    //query
		    String query = "SELECT * FROM company";
		    
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    while (rs.next()) {
		    	String name = rs.getString("name");
		    	String address = rs.getString("address");
		    	String logo = rs.getString("logo");
		    	String phone = rs.getString("phone");
		    	String fax = rs.getString("fax");
		    	return new Company(name, address, logo, phone, fax);
		    }
		    return null;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
		return null;
	}
	
	public static void addItems() {
		try {
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, user, pass);
		    
		    //query
		    String query = "SELECT * FROM items ";
		    
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    while(rs.next()) {
		    	String code = rs.getString("code");
		    	String name = rs.getString("name");
		    	Integer price = rs.getInt("price");
		    	Integer stock = rs.getInt("stock");
		    	Container.items.add(new Item(code, name, price, stock));
//		    	System.out.println(name);
		    }
		    
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
	
	public static void UpdateItemDB() {
		try {
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, user, pass);
		    
		    
		    Statement st = conn.createStatement();
		    
		    for(Item item : Container.items) {
		    	st.executeUpdate("UPDATE items set stock = '"+item.getStock()+"', name = '"+item.getName()+"', price = '"+item.getPrice()+"' WHERE code = '"+item.getCode()+"'");
		    }
		    
		    
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
	
	public static void InsertItemtoDB(String code, String name, Integer price, Integer stock) {
		try {
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, user, pass);
		    
		    
		    Statement st = conn.createStatement();
		    
		    st.executeUpdate("INSERT INTO items (code,name,price,stock) VALUES ('"+code+"','"+name+"','"+price+"','"+stock+"')");
		    
		    
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
	
	public static void UpdateTrxDB(boolean card, boolean member) {
		try {
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, user, pass);
		    
		    
		    Statement st = conn.createStatement();
		    
		    for(Transaction transaction : Container.transactions) {
		    	String itemIDs = "";
		    	String itemQTYs = "";
		    	String itemPRICEs = "";
		    	for(Purchase purchase : transaction.getPurchases()) {
		    		itemIDs = itemIDs + purchase.getItem().getCode()+"#";
		    		itemQTYs = itemQTYs + purchase.getQty()+"#";
		    		itemPRICEs = itemPRICEs + purchase.getItem().getPrice()+"#";
		    	}
		    	if(card && member) {
		    		st.executeUpdate("INSERT INTO transactions (trxID, itemID, itemQTY, itemPRICE, sales, member, cardNum, approvalCode) VALUES "
			    			+ "('"+transaction.getId()+"','"+itemIDs+"','"+itemQTYs+"','"+itemPRICEs+"','"+transaction.getCashier().getUsername()+"','"+"','"
					    			+transaction.getMemID()+"','"+transaction.getCardNum()+"','"+transaction.getApprovalCode()+"')");
		    	}
		    	
		    	else if(card && !member){
		    		st.executeUpdate("INSERT INTO transactions (trxID, itemID, itemQTY, itemPRICE, sales, cardNum, approvalCode) VALUES "
		    			+ "('"+transaction.getId()+"','"+itemIDs+"','"+itemQTYs+"','"+itemPRICEs+"','"+transaction.getCashier().getUsername()+"','"+"','"
				    			+transaction.getCardNum()+"','"+transaction.getApprovalCode()+"')");
		    	}
		    	
		    	else if(!card && member) {
		    		System.out.println("hit");
		    		st.executeUpdate("INSERT INTO transactions (trxID, itemID, itemQTY, itemPRICE, sales, member) VALUES "
			    			+ "('"+transaction.getId()+"','"+itemIDs+"','"+itemQTYs+"','"+itemPRICEs+"','"+transaction.getCashier().getUsername()+"','"
					    			+transaction.getMemID()+"')");
		    	}
		    	else{
		    		st.executeUpdate("INSERT INTO transactions (trxID, itemID, itemQTY, itemPRICE, sales) VALUES "
			    			+ "('"+transaction.getId()+"','"+itemIDs+"','"+itemQTYs+"','"+itemPRICEs+"','"+transaction.getCashier().getUsername()+"')");
		    	}
		    }
		    
		    
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
	
	public static boolean isMember(String uid){
		try {
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, user, pass);
		    
		    //query
		    String query = "SELECT * FROM member WHERE UID = '"+uid+"'";
		    
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    
		    while (rs.next()) {
		    	 return true;
		    }
		    return false;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
		return false;
	}
	
	
	public static Member dataMember(String uid){
		try {
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, user, pass);
		    
		    //query
		    String query = "SELECT * FROM member WHERE UID = '"+uid+"'";
		    
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    
		    while (rs.next()) {
		    	String name = rs.getString("name");
		    	String UID = rs.getString("UID");
		    	String address = rs.getString("address");;
		    	String phone = rs.getString("phone");
		    	Integer point = rs.getInt("point");
		    	return new Member(name, UID, address, phone, point);
		    }
		    return null;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
		return null;
	}
	
	public static void NewMember(Member member) {
		try {
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, user, pass);
		    
		    
		    Statement st = conn.createStatement();
		    
		    st.executeUpdate("INSERT INTO member (UID,name,address,phone,point) VALUES ('"+member.getUID()+"','"+member.getName()+"','"+member.getAddress()+"','"+member.getPhone()+"','"+member.getPoint()+"')");
		    
		    
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
	
	public static void UpdateMembers() {
		try {
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, user, pass);
		    
		    
		    Statement st = conn.createStatement();
		    
		    for(Member member : Container.memberList) {
		    	st.executeUpdate("UPDATE member set UID = '"+member.getUID()+"', name = '"+member.getName()+"', address = '"+member.getAddress()+"', phone = '"+member.getPhone()+"', point = '"+member.getPoint()+"'WHERE UID = '"+member.getUID()+"'");
		    }
		    
		    
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
	
	public static void UpdateMemberPoint(Integer point, String UID) {
		try {
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, user, pass);
		    
		    String query = "SELECT point FROM member where UID = '"+UID+"'";
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    
		    Integer oldPoint = 0;
		    while(rs.next()) {
		    	oldPoint = rs.getInt("point");
		    }
		    System.out.println(oldPoint);
		    System.out.println(point);
		    point+=oldPoint;
		    System.out.println(oldPoint);
		    st.executeUpdate("UPDATE member set point = '"+point+"' WHERE UID = '"+UID+"'");
		    
		    
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
	
	public static void addMembers() {
		try {
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, user, pass);
		    
		    //query
		    String query = "SELECT * FROM member";
		    
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    
		    while (rs.next()) {
		    	String name = rs.getString("name");
		    	String UID = rs.getString("UID");
		    	String address = rs.getString("address");;
		    	String phone = rs.getString("phone");
		    	Integer point = rs.getInt("point");
		    	Container.memberList.add(new Member(name, UID, address, phone, point));
		    }
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
	
	public static boolean isSPV(String spvid, String password){
		try {
			String encrypted = md5(password);
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, user, pass);
		    
		    //query
		    String query = "SELECT * FROM users WHERE UID = '"+spvid+"' AND password = '"+encrypted+"'AND status = 2";
		    
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    while (rs.next()) {
		    	return true;
		    }
		    return false;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
		return false;
	}
	
	public static String getItemNameFromDB(String code) {
		try {
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, user, pass);
		    
		    //query
		    String query = "SELECT name FROM items WHERE code = '"+code+"'";
		    
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    while (rs.next()) {
		    	return rs.getString("name");
		    }
		    return null;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
		return null;
	}
	
	public static void addPurchases() {
		try {
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, user, pass);
		    
		    String query = "SELECT * FROM transactions";
		    Statement st = conn.createStatement();
		    
		    ResultSet rs = st.executeQuery(query);
		    
		    while(rs.next()) {
		    	String trxID = rs.getString("trxID");
		    	String itemID = rs.getString("itemID");
		    	String itemQTY = rs.getString("itemQTY");
		    	String itemPrice = rs.getString("itemPRICE");
		    	String cashier = rs.getString("sales");
		    	
		    	String[] itemIDs = itemID.split("#");
		    	String[] itemQTYs = itemQTY.split("#");
		    	String[] itemPrices = itemPrice.split("#");
		    	
		    	TransactionHistory trx = new TransactionHistory(trxID, cashier);
		    	
		    	for(int i = 0; i<itemIDs.length; i++) {
		    		trx.addItem(itemIDs[i].toString(), Integer.parseInt(itemPrices[i].toString()), Integer.parseInt(itemQTYs[i].toString()));
		    	}
		    	Container.transactionHistory.add(trx);
		    	
		    }
		    
		    
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}

}
