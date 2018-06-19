package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AlumniDAO {
	
	public String addAlumni(String name, String detail, String id) throws SQLException{
		Connection con = DBConnection.getConnection();
		String msg = "";
		
		try{
			PreparedStatement ps = con.prepareStatement("delete from Alumni where id='"+id+"'"); 
			ps.executeUpdate();
		    ps = con.prepareStatement("insert into Alumni(name, detail) values(?,?)"); 
			ps.setString(1, name);
			ps.setString(2, detail);
		
			
			if(ps.executeUpdate() !=0){
				msg = "Successfull !!";
			}else{
				msg = "Failed to add !!";
			}
			
				
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return msg;
	}
	
	
	public ArrayList<String[]> alumniInfo() throws SQLException {
		Connection con = DBConnection.getConnection();
		ArrayList<String[]> al =new ArrayList<String[]>();
		
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Alumni");
			while(rs.next()){
				String[] d = new String[3];
				d[0] = rs.getString("id");
				d[1] = rs.getString("name");
				d[2] = rs.getString("detail");
				al.add(d);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return al;
		
	}
	
	
}
