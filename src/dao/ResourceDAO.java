package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Resource;

public class ResourceDAO {
	
	public String insert(Resource resource, String netid) throws SQLException{
		String msg = "";
		Connection con = DBConnection.getConnection();
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Resources where name= '"+resource.getName()+"'");
			if(!rs.next()){
			PreparedStatement ps = con.prepareStatement("insert into Resources values(?,?,?,?)"); 
			ps.setString(1, resource.getName());
			ps.setString(2, resource.getType());
			ps.setString(3, resource.getDetails());
			ps.setString(4, netid);
			if(ps.executeUpdate() !=0){
				msg = "Resource successfully added !!";
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
	
	public ArrayList<Resource> select() throws SQLException{
		ArrayList<Resource> al = new ArrayList<Resource>();
		Connection con = DBConnection.getConnection();
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Resources");
			while(rs.next()){
			 Resource resource = new Resource();
			 resource.setDetails(rs.getString("details"));
			 resource.setName(rs.getString("name"));
			 resource.setType(rs.getString("type"));
			 al.add(resource);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return al;
	}
	
	
	public ArrayList<String[]> reserveResourceList(String name) throws SQLException{
		ArrayList<String[]> al = new ArrayList<String[]>();
		Connection con = DBConnection.getConnection();
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from ReserveResource where name = '"+name+"' ORDER BY id ASC limit 5");
			while(rs.next()){
			 String d[] = new String[4];
			d[0]  = rs.getString("name");
			d[1] = rs.getString("date");
			d[2] = rs.getString("slot");
			d[3] = rs.getString("user");
			al.add(d);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return al;
	}
	
	public ArrayList<String> reserveSlot1(String name, String date) throws SQLException{
		ArrayList<String> slot =  new ArrayList<String>();
		int t = 8;
		for(int i =1; i<=8;i++){
			slot.add(t+" - "+ ++t);
		}
		
		Connection con = DBConnection.getConnection();
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from ReserveResource where name = '"+name+"' and date = '"+date+"'");
			while(rs.next()){
		    
				slot.remove(rs.getString("slot"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return slot;
	}
	
	public void reserveSlot2(String name, String date, String[] slots, String user, String netid) throws SQLException{
		Connection con = DBConnection.getConnection();
		try{
			
			PreparedStatement ps = con.prepareStatement("insert into ReserveResource(name, date, slot, user, netid) values(?,?,?,?,?)"); 
			for(int i =0;i<slots.length;i++){
			ps.setString(1, name);
			ps.setString(2, date);
			ps.setString(3, slots[i]);
			ps.setString(4, user);
			ps.setString(5,netid);
			ps.executeUpdate();
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
	
	
	public ArrayList<String[]> myResourceList(String netid) throws SQLException{
		ArrayList<String[]> al = new ArrayList<String[]>();
		Connection con = DBConnection.getConnection();
		try{
			Statement st = con.createStatement();
			Statement stt = con.createStatement();
			ResultSet rs = st.executeQuery("select * from ReserveResource where netid = '"+netid+"'");
			while(rs.next()){
			 String d[] = new String[4];
			 
			d[0]  = rs.getString("name");
			ResultSet rss = stt.executeQuery("select type from Resources where name ='"+d[0]+"'");
			rss.next();
			d[1] = rs.getString("date");
			d[2] = rs.getString("slot");
			d[3] = rss.getString("type");
			al.add(d);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return al;
	}
	
	public void cancelReservation(String netid, String[] res) throws SQLException{
		
		Connection con = DBConnection.getConnection();
		try{
			String query = "delete from ReserveResource where name=? and date=? and slot=? and netid=?";
			PreparedStatement ps = con.prepareStatement(query);
			
			for(int i=0;i<res.length;i++){
				String[] d = res[i].split(";");
				ps.setString(1,d[0] );
				ps.setString(2, d[1]);
				ps.setString(3, d[2]);
				ps.setString(4,netid );
				ps.executeUpdate();
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
	}

}
