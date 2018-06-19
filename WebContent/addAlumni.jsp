<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alumni</title>
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
                        Alumni
                    </a>
                </li>
                <li>
                    <a href="addAlumni.jsp">Add Alumni</a>
                </li>
                <li>
                    <a href="AlumniServlet?form=alumniInfo">View Alumni</a>
                </li>
                
                
            </ul>
        </div>
<jsp:include page="menu.jsp"></jsp:include>
<div>
<center>
<h3>Alumni</h3>
<%String msg = (String)request.getAttribute("msg"); 
String id = (String)request.getParameter("id");
String name = (String)request.getParameter("name");
if(msg != null){ %>

<p style="color:blue">
<%= msg %>

</p>
<% }
%>
<form action="AlumniServlet" method="post">
<input type="hidden" name="form" value="add">
<input type="hidden" name="id" value = "<%=id%>">
<table>
<tr>
<td>
Name: 
</td>
<td>
<%if(id != null){ 
out.print(name); %>
<input type = "hidden" name="name" value="<%= name%>">
<% }else{ %>
<input name="name" placeholder="Name">
<%} %>
</td>
</tr>

<tr>
<td>
Details:
</td>
<td>
<textarea col="30"  row="50" name="detail"></textarea>
</td>
</tr>
<tr>
<td colspan="2" align="center">
<input type="submit" value="Submit">
</td>
</tr>
</table>
</form>

</center>
</div>
</div>
</body>
</html>