<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList, java.util.Iterator, model.Resource" %>
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
<h3>My Resources</h3>
<%String msg = (String)request.getAttribute("msg"); 
if(msg != null){ %>

<p style="color:blue">
<%= msg %>

</p>
<% }
%>
<% ArrayList<String[]> al = (ArrayList<String[]>)request.getAttribute("list");%>
<form action="Resource" method="post" onsubmit="return confirm('Do you want to cancel ?');">
<input type="hidden" name = "form" value="cancelReservation">
<table width="60%">
<tr>
<th>Name</th>
<th>Type</th>
<th>Date</th>
<th>Slots</th>
<th></th>
</tr>


<%  if(al != null){
Iterator<String[]> it = al.iterator();
while(it.hasNext()){
String[] d = it.next();%>
<tr>
<td><%=d[0] %></td>
<td><%=d[3] %></td>
<td><%=d[1] %></td>
<td><%=d[2] %></td>
<td><input type="checkbox" name="selectList" value="<%=d[0]+";"+d[1]+";"+d[2]%>"></td>
</tr>
<% } %>
<tr>
<td colspan="5" align="center"><input type="submit" value="Cancel" onclick="return ">
</tr>
<%
} %>
</table>
</form>
</center>
</div>
</div>

<script src="js/jquery.js"></script>
</body>
</html>