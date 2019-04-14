package dataConnector;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import assets.Item;
import assets.Company;
import assets.Purchase;
import assets.Transaction;
import assets.TransactionHistory;
import assets.User;
import assets.UserCashier;
import assets.UserSupervisor;
import dataContainer.Container;

public class DBConn {
	
	public static User login(String username, String password) {
		try {
			//create connection
			String myDriver = "com.mysql.jdbc.Driver";
		    String myUrl = "jdbc:mysql://localhost/pikapos";
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, "root", "");
		    
		    //query
		    String query = "SELECT * FROM users where username = '"+username+"' and password = '"+password+"'";
		    
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
			//create connection
			String myDriver = "com.mysql.jdbc.Driver";
		    String myUrl = "jdbc:mysql://localhost/pikapos";
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
			//create connection
			String myDriver = "com.mysql.jdbc.Driver";
		    String myUrl = "jdbc:mysql://localhost/pikapos";
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
			//create connection
			String myDriver = "com.mysql.jdbc.Driver";
		    String myUrl = "jdbc:mysql://localhost/pikapos";
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, "root", "");
		    
		    
		    Statement st = conn.createStatement();
		    
		    for(Item item : Container.items) {
		    	st.executeUpdate("UPDATE items set stock = '"+item.getStock()+"' WHERE code = '"+item.getCode()+"'");
		    }
		    
		    
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
	
	public static void UpdateTrxDB() {
		try {
			//create connection
			String myDriver = "com.mysql.jdbc.Driver";
		    String myUrl = "jdbc:mysql://localhost/pikapos";
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
	
	public static User findSpvID(String spvid){
		try {
			//create connection
			String myDriver = "com.mysql.jdbc.Driver";
		    String myUrl = "jdbc:mysql://localhost/pikapos";
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, "root", "");
		    
		    //query
		    String query = "SELECT * FROM users WHERE spvID = '"+spvid+"'";
		    
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    while (rs.next()) {
		    	// TODO : menentukan dimana ID ditaro, getnya name paling biar bisa keluar msgbox nama (ala ala aja)
		    }
		    return null;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
		return null;
	}
	
	private static Item findItem(String id) {
		for(Item item : Container.items) {
			if (item.getCode().equals(id)) return item;
		}
		return null;
	}
	
	public static void addPurchases() {
		try {
			//create connection
			String myDriver = "com.mysql.jdbc.Driver";
		    String myUrl = "jdbc:mysql://localhost/pikapos";
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
		    	
		    	for(int i = 0; i<itemIDs.length-1; i++) {
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
		      // create our mysql database connection
			  String myDriver = "com.mysql.jdbc.Driver";
		      String myUrl = "jdbc:mysql://localhost/PIKA";
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
