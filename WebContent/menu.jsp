<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Menu</title>
</head>
<body>
<%HttpSession se = request.getSession(false);
String name = (String)se.getAttribute("firstname");
if(name != null){
	
}else{
	name = "User";
}
%>
 <ul class="nav nav-tabs">
    <li><a href="home.jsp">Home</a></li>
    
    <%HttpSession se4 = request.getSession();
                String role = (String)se4.getAttribute("role");
                if(role.equals("faculty") || role.equals("student")){%>
                <li>
                    <li><a href="Course?form=viewallcourses">Course</a></li>
                </li>
                <%} %>
    
   <%  if(!role.equals("student")){%>
                <li>
                    <li><a href="PhdStatus?form=phdList">PhD Status</a></li>
                </li>
                <%} %>
    
      
    <li><a href="Discussion?form=topics">Discussion</a></li>
    <li><a href="AlumniServlet?form=alumniInfo">Alumni</a></li>
    <li><a href="Exam?form=viewExam">Exam</a></li>
    <li><a href="Resource?form=viewList">Resource</a></li>
    <li><a href="Announcement?form=announcement">Announcement</a></li>
    <li class="dropdown navbar-right">
    
    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%=name %> <span class="caret"></span></a>
     <ul class="dropdown-menu">
            <li><a href="profile.jsp">Edit Profile</a></li>
            <li><a href="Register?form=logout">Log Out</a></li>
            
          </ul>
    </li>
  </ul>
  
</body>
</html>