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
<h3>Resources</h3>
<% ArrayList<Resource> al = (ArrayList<Resource>)request.getAttribute("list");%>
<table width="60%">
<tr>
<th>Name</th>
<th>Type</th>
<th>Detail</th>
</tr>


<%  if(al != null){
Iterator<Resource> it = al.iterator();
while(it.hasNext()){
Resource resource = it.next();%>
<tr><td>
<a href = "Resource?form=reserveList&name=<%=resource.getName()%>"><%=resource.getName() %></a></td>
<td><%=resource.getType() %></td>
<td><%=resource.getDetails() %></td>
</tr>
<% }} %>
</table>
</center>
</div>
</div>

<script src="js/jquery.js"></script>
</body>
</html>