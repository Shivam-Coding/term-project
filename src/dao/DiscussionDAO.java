package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DiscussionDAO {
	
	public String addTopic(String topic, String name, String netid) throws SQLException{
		Connection con = DBConnection.getConnection();
		String msg = "";
		try{
			
			PreparedStatement ps = con.prepareStatement("insert into DiscussionTopics(topic,name,netid) values(?,?,?)"); 
			ps.setString(1, topic);
			ps.setString(2, name);
			ps.setString(3, netid);
			
			if(ps.executeUpdate() !=0){
				msg = "Topic successfully added !!";
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
	
	
	public ArrayList<String[]> topics() throws SQLException{
		Connection con = DBConnection.getConnection();
		ArrayList<String[]> al = new ArrayList<String[]>();
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from DiscussionTopics");
			while(rs.next()){
				String[] d = new String[4];
				d[0] = rs.getInt("id")+"";
				d[1] = rs.getString("topic");
				d[2] = rs.getString("name");
				d[3] = rs.getString("netid");
				al.add(d);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return al;
	}
	
	public ArrayList<String[]> comment(String id) throws SQLException{
		Connection con = DBConnection.getConnection();
		ArrayList<String[]> al = new ArrayList<String[]>();
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Comment where topicid ='"+id+"'");
			while(rs.next()){
				String[] d = new String[3];
				d[0] = rs.getInt("id")+"";
				d[1] = rs.getString("comment");
				d[2] = rs.getInt("topicid")+"";
				al.add(d);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return al;
	}
	
	public ArrayList<String[]> reply(String id) throws SQLException{
		Connection con = DBConnection.getConnection();
		ArrayList<String[]> al = new ArrayList<String[]>();
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Reply where topicid ='"+id+"'");
			while(rs.next()){
				String[] d = new String[4];
				d[0] = rs.getInt("id")+"";
				d[1] = rs.getString("reply");
				d[2] = rs.getInt("topicid")+"";
				d[3] = rs.getInt("commentid")+"";
				al.add(d);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return al;
	}
	
	
	public void addComment(String id, String comment) throws SQLException{
		Connection con = DBConnection.getConnection();
		String msg = "";
		try{
			
			PreparedStatement ps = con.prepareStatement("insert into Comment(comment,topicid) values(?,?)"); 
			ps.setString(1, comment);
			ps.setString(2, id);
			
			
			if(ps.executeUpdate() !=0){
				msg = "Topic successfully added !!";
			}else{
				msg = "Failed to add !!";
			}
					
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}

	}
	
	
	public void addReply(String topicid, String commentid, String reply) throws SQLException{
		Connection con = DBConnection.getConnection();
		String msg = "";
		try{
			
			PreparedStatement ps = con.prepareStatement("insert into Reply(reply,topicid,commentid) values(?,?,?)"); 
			ps.setString(1, reply);
			ps.setString(2, topicid);
			ps.setString(3, commentid);
			if(ps.executeUpdate() !=0){
				msg = "Topic successfully added !!";
			}else{
				msg = "Failed to add !!";
			}
					
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}

	}
	
	
	public void deleteTopic(String id) throws SQLException{
		Connection con = DBConnection.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement("delete from DiscussionTopics where id='"+id+"'");
			ps.executeUpdate();
			ps = con.prepareStatement("delete from Comment where topicid='"+id+"'");
			ps.executeUpdate();
			ps = con.prepareStatement("delete from Reply where topicid='"+id+"'");
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
	
	public void deleteComment(String id) throws SQLException{
		Connection con = DBConnection.getConnection();
		try{
			
			PreparedStatement ps = con.prepareStatement("delete from Comment where id='"+id+"'");
			ps.executeUpdate();
			ps = con.prepareStatement("delete from Reply where commentid='"+id+"'");
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
	
	public void deleteReply(String id) throws SQLException{
		Connection con = DBConnection.getConnection();
		try{
		
			PreparedStatement ps = con.prepareStatement("delete from Reply where id='"+id+"'");
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
	
	public ArrayList<String[]> mytopics(String netid) throws SQLException{
		Connection con = DBConnection.getConnection();
		ArrayList<String[]> al = new ArrayList<String[]>();
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from DiscussionTopics where netid='"+netid+"'");
			while(rs.next()){
				String[] d = new String[4];
				d[0] = rs.getInt("id")+"";
				d[1] = rs.getString("topic");
				d[2] = rs.getString("name");
				d[3] = rs.getString("netid");
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
