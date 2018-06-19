package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PhdStatus {
	
	public ArrayList<String[]> phdList() throws SQLException{
		ArrayList<String[]> al = new ArrayList<String[]>();
		Connection con = DBConnection.getConnection();
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Phd");
			while(rs.next()){
			 String d[] = new String[2];
			d[0]  = ""+rs.getInt("id");
			d[1] = rs.getString("name");
			
			al.add(d);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return al;
	}
	
	
	public ArrayList<String[]> phdDetail(String id) throws SQLException{
		ArrayList<String[]> al = new ArrayList<String[]>();
		Connection con = DBConnection.getConnection();
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Phd where id ='"+id+"'");
			while(rs.next()){
			 String d[] = new String[4];
			d[0]  = ""+rs.getInt("id");
			d[1] = rs.getString("name");
			d[2] = rs.getString("semester");
			d[3] = rs.getString("research");
			
			al.add(d);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return al;
	}
	
	
	
	public void phdUpdate(String id, String semester, String research) throws SQLException{
		Connection con = DBConnection.getConnection();
		try{
			
			PreparedStatement ps = con.prepareStatement("update Phd set semester=? , research=? where id=?");
			ps.setString(1, semester);
			ps.setString(2, research);
			ps.setInt(3, Integer.parseInt(id));
			ps.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
	
	}
	
	

}
