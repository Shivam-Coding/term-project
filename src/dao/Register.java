package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Register {
	
	public String firstName ;
	public String lastName ;
	public String netid ;
	public String password ;
	public String role ;
	public String year ;
	public String program;
	public String major ;
	public String email;
	
	public String registerUser() throws SQLException{
		String msg = "";
		Connection con = DBConnection.getConnection();
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Register where netid= '"+netid+"'");
			if(!rs.next()){
			PreparedStatement ps = con.prepareStatement("insert into Register values(?,?,?,?,?,?,?,?,?,?)"); 
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, netid);
			ps.setString(4, password);
			ps.setString(5, role);
			ps.setString(6, year);
			ps.setString(7, program);
			ps.setString(8, major);
			ps.setString(9, "");
			ps.setString(10, email);
			if(ps.executeUpdate() !=0){
				msg = "success";
			}else{
				msg = "Failed to add !!";
			}
			}else{
				msg = "Name already exist !!";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return msg;
	}
	
	
	public String login(String netid, String password) throws SQLException{
		String msg = "";
		Connection con = DBConnection.getConnection();
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Register where netid= '"+netid+"'");
			if(rs.next()){
				msg = "success";
				firstName = rs.getString("firstname");
				role = rs.getString("role");
			}else{
				msg = "Invalid credentials !!";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return msg;
	}
	

	public String update() throws SQLException{
		String msg = "";
		Connection con = DBConnection.getConnection();
		try{
			if(firstName!=null && !firstName.isEmpty()){
			PreparedStatement ps = con.prepareStatement("update Register set firstname=? where netid=?");
			ps.setString(1, firstName);
			ps.setString(2, netid);
			if(ps.executeUpdate() !=0){
				msg = "success";
			}else{
				msg = "Failed to add !!";
			}
			}
			if(lastName!=null && !lastName.isEmpty()){
			PreparedStatement ps = con.prepareStatement("update Register set lastname=? where netid=?");
			ps.setString(1, lastName);
			ps.setString(2, netid);
			if(ps.executeUpdate() !=0){
				msg = "success";
			}else{
				msg = "Failed to add !!";
			}
			}
			if(password!=null && !password.isEmpty()){
			PreparedStatement ps = con.prepareStatement("update Register set password=? where netid=?");
			ps.setString(1, password);
			ps.setString(2, netid);
			if(ps.executeUpdate() !=0){
				msg = "success";
			}else{
				msg = "Failed to add !!";
			}
			}
			if(year!=null && !year.isEmpty()){
			PreparedStatement ps = con.prepareStatement("update Register set year=? where netid=?");
			ps.setString(1, year);
			ps.setString(2, netid);
			if(ps.executeUpdate() !=0){
				msg = "success";
			}else{
				msg = "Failed to add !!";
			}
			}
			if(program!=null && !program.isEmpty()){
			PreparedStatement ps = con.prepareStatement("update Register set program=? where netid=?");
			ps.setString(1, program);
			ps.setString(2, netid);
			if(ps.executeUpdate() !=0){
				msg = "success";
			}else{
				msg = "Failed to add !!";
			}
			}
			if(major!=null && !major.isEmpty()){
			PreparedStatement ps = con.prepareStatement("update Register set major=? where netid=?");
			ps.setString(1, major);
			ps.setString(2, netid);
			if(ps.executeUpdate() !=0){
				msg = "success";
			}else{
				msg = "Failed to add !!";
			}
			}
			if(email!=null && !email.isEmpty()){
			PreparedStatement ps = con.prepareStatement("update Register set email=? where netid=?");
			ps.setString(1, email);
			ps.setString(2, netid);
			if(ps.executeUpdate() !=0){
				msg = "success";
			}else{
				msg = "Failed to add !!";
			}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return msg;
	}
	
	
	public String updateOffice(String netid, String officehour) throws SQLException{
		String msg = "";
		Connection con = DBConnection.getConnection();
		try{
			
			PreparedStatement ps = con.prepareStatement("update Register set officehour=?  where netid=?"); 
			ps.setString(1, officehour);
			ps.setString(2, netid);
			if(ps.executeUpdate() !=0){
				msg = "success";
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
	
	public String officehour(String netid ) throws SQLException{
		String msg = "";
		Connection con = DBConnection.getConnection();
		try{
			
			PreparedStatement ps = con.prepareStatement("select officehour from Register where netid=?"); 
			ps.setString(1, netid);
		ResultSet rs = ps.executeQuery();
		rs.next();
				msg = rs.getString("officehour");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return msg;
	}


}
