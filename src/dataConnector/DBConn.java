package dataConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import assets.Item;
import assets.User;
import assets.UserCashier;
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
