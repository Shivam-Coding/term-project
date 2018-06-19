package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection(){
		Connection conn = null;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/TermProject";
		String username = "root";
		String password = "";
        try{
        Class.forName(driver);
        
    conn = DriverManager.getConnection(url,username, password);
    }catch(Exception e){
        e.printStackTrace();
    }
        return conn;
	}
}
