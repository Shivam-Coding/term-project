package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AnnouncementDAO {

	public String addAnnouncement(String detail, String id, String category, String link,String email, String netid) throws SQLException{
		Connection con = DBConnection.getConnection();
		String msg = "";
		
		try{
			PreparedStatement ps = con.prepareStatement("delete from Announcement where id='"+id+"'"); 
			ps.executeUpdate();
		    ps = con.prepareStatement("insert into Announcement(detail, category, link, email, netid) values(?,?,?,?,?)"); 
			ps.setString(1, detail);
			ps.setString(2, category);
		    ps.setString(3, link);
		    ps.setString(4, email);
		    ps.setString(5, netid);
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
	
	public ArrayList<String[]> announcementList() throws SQLException{
		Connection con = DBConnection.getConnection();
		ArrayList<String[]> al = new ArrayList<String[]>();
		
		try{
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from Announcement order by id DESC");
		while(rs.next()){
			String[] d = new String[6];
			d[0] = rs.getInt("id")+"";
			d[1] = rs.getString("detail");
			d[2] = rs.getString("category");
			d[3] = rs.getString("link");
			d[4] = rs.getString("email");
			d[5] = rs.getString("netid");
			al.add(d);
		}
			
				
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return al;
	}
	
	
	public void deleteAnnouncement(String id, String netid) throws SQLException{
		Connection con = DBConnection.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement("delete from Announcement where id='"+id+"' and netid ='"+netid+"'"); 
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
	
	public String applyJob(String netid, String jobid) throws SQLException{
		Connection con = DBConnection.getConnection();
		String msg = null;
		try{
			
			PreparedStatement ps = con.prepareStatement("insert into ApplyJob values(?,?)"); 
			ps.setString(1, jobid);
			ps.setString(2, netid);
			if(ps.executeUpdate() !=0){
				msg = "Successfully applied";
			}else{
				msg="Failed to apply";
			}
		}catch(Exception e){
			e.printStackTrace();
			msg="Already applied";
		}finally{
			con.close();
		}
		
		return msg;
	}
	
}
