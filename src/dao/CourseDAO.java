package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CourseDAO {
	
	public String addCourse(String courseid, String name) throws SQLException{
		Connection con = DBConnection.getConnection();
		String msg = "";
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Courses where courseid= '"+courseid+"'");
			if(!rs.next()){
			PreparedStatement ps = con.prepareStatement("insert into Courses(courseid,coursename) values(?,?)"); 
			ps.setString(1, courseid);
			ps.setString(2, name);
			
			if(ps.executeUpdate() !=0){
				msg = "Course successfully added !!";
			}else{
				msg = "Failed to add !!";
			}
			}else{
				msg = "Course already exist !!";
			}
				
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return msg;
	}
	
	
	public ArrayList<String[]> allCourses() throws SQLException{
		ArrayList<String[]> al = new ArrayList<String[]>();
		Connection con = DBConnection.getConnection();
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Courses");
			while(rs.next()){
			 String d[] = new String[6];
			d[0]  = rs.getString("courseid");
			d[1] = rs.getString("coursename");
			d[2] = rs.getString("netid");
			d[3] = rs.getString("facultyname");
			d[4] = rs.getString("syllabus");
			d[5] = rs.getString("ta");
			al.add(d);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return al;
	}
	
	
	public void courseTeach(String name, String netid, String courseid) throws SQLException{
		Connection con = DBConnection.getConnection();
		try{
			
			PreparedStatement ps = con.prepareStatement("update Courses set netid = ?, facultyname=? where courseid=?"); 
			ps.setString(1, netid);
			ps.setString(2, name);
			ps.setString(3, courseid);
			ps.executeUpdate();
			
			
				
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
	
	
	public ArrayList<String[]> myCourses(String netid) throws SQLException{
		ArrayList<String[]> al = new ArrayList<String[]>();
		Connection con = DBConnection.getConnection();
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Courses where netid='"+netid+"'");
			while(rs.next()){
			 String d[] = new String[6];
			d[0]  = rs.getString("courseid");
			d[1] = rs.getString("coursename");
			d[2] = rs.getString("netid");
			d[3] = rs.getString("facultyname");
			d[4] = rs.getString("syllabus");
			d[5] = rs.getString("ta");
			al.add(d);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return al;
	}
	
	public void postDetails(String det, String courseid, String data, String email, String office) throws SQLException{
		Connection con = DBConnection.getConnection();
		String query;
		try{
		if(det.equals("ta")){
			query = "update Courses set ta=?,taemail=?, taoffice=? where courseid=?";

			PreparedStatement ps = con.prepareStatement(query); 
			ps.setString(1, data);
			ps.setString(4, courseid);
			ps.setString(2, email);
			ps.setString(3, office);
			ps.executeUpdate();
		}else{
			query = "update Courses set syllabus=? where courseid=?";

			PreparedStatement ps = con.prepareStatement(query); 
			ps.setString(1, data);
			ps.setString(2, courseid);
			ps.executeUpdate();
		}
					
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
	
	
	public String enroll(String name, String netid) throws SQLException{
		
		Connection con = DBConnection.getConnection();
		String msg = "";
		try{
			PreparedStatement ps = con.prepareStatement("insert into Enrollment values(?,?)");
			ps.setString(1, name);
			ps.setString(2, netid);
			if(ps.executeUpdate() !=0){
				msg = "Successfully enrolled";
			}else{
				msg = "Failed to enroll";
			}
		}catch(SQLException e){
			e.printStackTrace();
			msg ="Already enrolled";
		}finally{
			con.close();
		}
		
		return msg;
		
	}
	
	public ArrayList<String[]> viewMyCourse(String netid) throws SQLException{
		Connection con = DBConnection.getConnection();
		ArrayList<String[]> al = new ArrayList<String[]>();
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Enrollment, Courses where Enrollment.netid='"+netid+"' and Courses.coursename = Enrollment.coursename");  
			while(rs.next()){
				 String d[] = new String[6];
				d[0]  = rs.getString("courseid");
				d[1] = rs.getString("coursename");
				d[2] = rs.getString("netid");
				d[3] = rs.getString("facultyname");
				d[4] = rs.getString("syllabus");
				d[5] = rs.getString("ta");
				al.add(d);
				}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return al;
	}
	
	
	public ArrayList<String> displayDetail(String cid, String flag) throws SQLException{
		Connection con = DBConnection.getConnection();
		ArrayList<String> al = new ArrayList<String>();
		try{
			Statement st = con.createStatement();
		if(flag.equals("ta")){
			ResultSet rs = st.executeQuery("select ta, taemail, taoffice from Courses where courseid ='"+cid+"'");
			if(rs.next()){
				al.add(rs.getString("ta"));
				al.add(rs.getString("taemail"));
				al.add(rs.getString("taoffice"));
			}
		}else{
			ResultSet rss = st.executeQuery("select netid from Courses where courseid ='"+cid+"'");
			if(rss.next()){
			ResultSet rs1 = st.executeQuery("select firstname, lastname, officehour, email from Register where netid ='"+rss.getString("netid")+"'");
			if(rs1.next()){
				al.add(rs1.getString("firstname")+" "+rs1.getString("lastname"));
				al.add(rs1.getString("email"));
				al.add(rs1.getString("officehour"));
			}
			}
		}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return al;
	}
	
	

}
