package dataConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import assets.User;

public class DBConn {
	
	public static User login(String username, String password) {
		try {
			//create connection
			String myDriver = "com.mysql.jdbc.Driver";
		    String myUrl = "jdbc:mysql://localhost/PIKA";
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, "root", "");
		    
		    //query
		    String query = "SELECT * FROM user where user.username = username and user.password = password";
		    
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    String name;
		    String user_name;
		    String pass_word;
		    String address;
		    Integer age;
		    Integer status;
		    String telephone;
		    while(rs.next()) {
		    	name = rs.getString("name");
		    	user_name = rs.getString("username");
		    	pass_word = rs.getString("password");
		    	address = rs.getString("address");
		    	age = rs.getInt("age");
		    	status = rs.getInt("status");
		    	telephone = rs.getString("telephone");
		    }
		    return new User(name, address, telephone, age, status, user_name, pass_word);
		}
		return null;
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
