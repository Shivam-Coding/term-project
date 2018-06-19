<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList, java.util.Iterator, model.Resource" %>
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

<% ArrayList<String[]> al = (ArrayList<String[]>)request.getAttribute("list");%>
<table width="40%">
<tr>
<th>Name</th>
<th>Detail</th>
<th></th>
</tr>


<%  if(al != null){
Iterator<String[]> it = al.iterator();
while(it.hasNext()){
String[] d = it.next();%>
<tr><td>
<%=d[1] %></td>
<td><%=d[2] %></td>


<td><a href = "addAlumni.jsp?id=<%=d[0] %>&name=<%=d[1]%>">edit</a></td>



</tr>
<% }} %>
</table>



</center>
</div>
</div>
</body>
</html>