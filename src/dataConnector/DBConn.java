package dataConnector;

import java.awt.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

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
		    Connection conn = DriverManager.getConnection(myUrl, "root", "");
		    
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
		    Connection conn = DriverManager.getConnection(myUrl, "root", "");
		    
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
		    Connection conn = DriverManager.getConnection(myUrl, "root", "");
		    
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
		    Connection conn = DriverManager.getConnection(myUrl, "root", "");
		    
		    
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
		    Connection conn = DriverManager.getConnection(myUrl, "root", "");
		    
		    
		    Statement st = conn.createStatement();
		    
		    st.executeUpdate("INSERT INTO items (code,name,price,stock) VALUES ('"+code+"','"+name+"','"+price+"','"+stock+"')");
		    
		    
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
	
	public static void UpdateTrxDB() {
		try {
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, "root", "");
		    
		    
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
		    	st.executeUpdate("INSERT INTO transactions (trxID, itemID, itemQTY, itemPRICE, sales) VALUES ('"+transaction.getId()+"','"+itemIDs+"','"+itemQTYs+"','"+itemPRICEs+"','"+transaction.getCashier().getUsername()+"')");
		    }
		    
		    
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
	
	public static boolean isMember(String uid){
		try {
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, "root", "");
		    
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
	};
	
	public static Member dataMember(String uid){
		try {
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, "root", "");
		    
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
	
	public static boolean isSPV(String spvid, String password){
		try {
			String encrypted = md5(password);
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, "root", "");
		    
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
	
	private static Item findItem(String id) {
		for(Item item : Container.items) {
			if (item.getCode().equals(id)) return item;
		}
		return null;
	}
	
	public static void addPurchases() {
		try {
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, "root", "");
		    
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
		    		System.out.println(itemPrices[i].toString() + "#" + itemQTYs[i].toString());
		    		trx.addItem(itemIDs[i].toString(), Integer.parseInt(itemPrices[i].toString()), Integer.parseInt(itemQTYs[i].toString()));
		    	}
		    	Container.transactionHistory.add(trx);
		    	
		    }
		    
		    
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}

	public DBConn() {
		 try
		    {
		      Class.forName(myDriver);
		      Connection conn = DriverManager.getConnection(myUrl, "root", "");
		      
		      // our SQL SELECT query. 
		      // if you only need a few columns, specify them by name instead of using "*"
		      String query = "SELECT * FROM rumah";
		      
		      // create the java statement
		      Statement st = conn.createStatement();
		      
//		      UNTUK INSERT
//		      st.executeUpdate("INSERT INTO rumah (kode, alamat) VALUES ('DOG-123','Jl. Alam Sungai Anjing no. 26')");
		      
		      // execute the query, and get a java resultset
		      ResultSet rs = st.executeQuery(query);
		      
		      // iterate through the java resultset
		      while (rs.next())
		      {
		        int id = rs.getInt("id");
		        String nama = rs.getString("kode");
		        String username = rs.getString("alamat");

		        
		        // print the results
		        System.out.format("%d, %s, %s\n", id, nama, username);
		      }
		      st.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
	}

}
