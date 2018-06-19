package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import apple.laf.JRSUIConstants.State;

public class ExamDAO {
	
	public String createExam(String name, String date, String link) throws SQLException{
		Connection con = DBConnection.getConnection();
		String msg = "";
		try{
			
			PreparedStatement ps = con.prepareStatement("insert into Exam(name, date, link) values(?,?,?)"); 
			ps.setString(1, name);
			ps.setString(2, date);
			ps.setString(3, link);
		
			if(ps.executeUpdate() !=0){
				msg = "Exam successfully added !!";
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
	
	
	public String postResult(String name, String[] result, String netid) throws SQLException{
		Connection con = DBConnection.getConnection();
		String msg = "";
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select name from Exam where name= '"+name+"'");
			if(rs.next()){
			PreparedStatement ps = con.prepareStatement("insert into Result(name, studentid, result, netid) values(?,?,?,?)"); 
			for(int i =0; i<result.length;i++){
				String id[] = result[i].split(":");
				String results[] = result[i+=1].split(":");
			ps.setString(1, name);
			ps.setString(2, id[1]);
			ps.setString(3, results[1]);
			ps.setString(4, netid);
			
			if(ps.executeUpdate() !=0){
				msg = "Result successfully posted !!";
			}else{
				msg = "Failed to post !!";
			}
			
			}
			
			}else{
				msg = "Exam doesn't exit";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return msg;
	
	}
	
	
	
	public ArrayList<String[]> viewResults() throws SQLException{
		Connection con = DBConnection.getConnection();
		ArrayList<String[]> al = new ArrayList<String[]>();
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select Distinct name , netid from Result");
			while(rs.next()){
				String[] d = new String[2];
				
				d[0] = rs.getString("name");
				d[1] = rs.getString("netid");
				al.add(d);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return al;
	
	}
	
	
	public String updateResult( String[] result, String name, String netid) throws SQLException{
		Connection con = DBConnection.getConnection();
		String msg = "";
		try{
			
			PreparedStatement ps = con.prepareStatement("Delete from Result where name=?"); 
			ps.setString(1, name);
			ps.executeUpdate();
			ps = con.prepareStatement("insert into Result(name, studentid, result, netid) values(?,?,?,?)"); 
			for(int i =0; i<result.length;i++){
				String id[] = result[i].split(":");
				String results[] = result[i+=1].split(":");
			ps.setString(1, name);
			ps.setString(2, id[1].trim());
			ps.setString(3, results[1].trim());
			ps.setString(4, netid);
			
			
			if(ps.executeUpdate() !=0){
				msg = "Result successfully updated !!";
			}else{
				msg = "Failed to post !!";
			}
			
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return msg;
	
	}

	
	public void deleteResult(String name) throws SQLException{
		Connection con = DBConnection.getConnection();
		String msg = "";
		try{
			
			PreparedStatement ps = con.prepareStatement("Delete from Result where name=?"); 
			ps.setString(1, name);
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
	
	public ArrayList<String[]> viewExamResults(String name) throws SQLException{
		Connection con = DBConnection.getConnection();
		ArrayList<String[]> al = new ArrayList<String[]>();
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Result where name='"+name+"'");
			while(rs.next()){
				String[] d = new String[2];
				
				d[0] = rs.getString("studentid");
				d[1] = rs.getString("result");
				al.add(d);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return al;
	}
	
	public ArrayList<String[]> viewExam() throws SQLException{
		Connection con = DBConnection.getConnection();
		ArrayList<String[]> al = new ArrayList<String[]>();
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Exam");
			while(rs.next()){
				String[] d = new String[3];
				
				d[0] = rs.getString("name");
				d[1] = rs.getString("date");
				d[2] = rs.getString("link");
				al.add(d);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return al;
	}
	
	
	public ArrayList<String[]> viewExamStudent(String netid) throws SQLException{
		Connection con = DBConnection.getConnection();
		ArrayList<String[]> al = new ArrayList<String[]>();
		try{
			Statement st = con.createStatement();
			Statement stt = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Exam");
			while(rs.next()){
				String[] d = new String[4];
				ResultSet rss = stt.executeQuery("select * from RegisterExam where name= '"+rs.getString("name")+"' and netid ='"+netid+"'");   
				d[0] = rs.getString("name");
				d[1] = rs.getString("date");
				d[2] = rs.getString("link");
				if(rss.next()){
				d[3] = "yes";
				}else{
					d[3]="no";
				}
				al.add(d);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return al;
	}
	
	
	public void registerExam(String name, String netid) throws SQLException{
		Connection con = DBConnection.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement("insert into RegisterExam values(?,?)");
			ps.setString(1, name);
			ps.setString(2, netid);
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
	
	
	public ArrayList<String[]> viewResultStudent(String netid) throws SQLException{
		Connection con = DBConnection.getConnection();
		ArrayList<String[]> al = new ArrayList<String[]>();
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select Distinct name, netid from RegisterExam where netid= '"+netid+"'");
			while(rs.next()){
				String[] d = new String[2];
				
				d[0] = rs.getString("name");
				d[1] = "";
				al.add(d);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return al;
	
	}
	

}
