<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resources</title>
<link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/simple-sidebar.css" rel="stylesheet">
</head>
<body>
<div id="wrapper">
<div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="#">
                        Resources
                    </a>
                </li>
                <%HttpSession se = request.getSession();
                String role = (String)se.getAttribute("role");
                if(role.equals("staff")){%>
                <li>
                    <a href="resource.jsp">Create Resource</a>
                </li>
                <%} %>
                <li>
                    <a href="Resource?form=viewList">View Resources</a>
                </li>
                <li>
                    <a href="Resource?form=myResources">My Resources</a>
                </li>
                
                
                
            </ul>
        </div>
<jsp:include page="menu.jsp"></jsp:include>
<div>
<center>
<h3>Create Resource</h3>
<%String msg = (String)request.getAttribute("msg"); 
if(msg != null){ %>

<p style="color:blue">
<%= msg %>

</p>
<% }
%>
<form action="Resource" method="post">
<input type="hidden" name="form" value="create">
<table>
<tr>
<td>
Resource Name: 
</td>
<td>
<input name="name" placeholder="Name">
</td>
</tr>
<tr>
<td>
Resource Type: 
</td>
<td>
<select name="resourceType">
  <option value="conference_room">Conference Room</option>
  <option value="projector">Projector</option>
  <option value="group_study">Group Study</option>
</select>
</td>
</tr>
<tr>
<td>
Resource Details:
</td>
<td>
<textarea col="30"  row="50" name="detail"></textarea>
</td>
</tr>
<tr>
<td colspan="2">
<input type="submit" value="Create">
</td>
</tr>
</table>
</form>

</center>
</div>
</div>
</body>
</html>