<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Discussion</title>
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
                        Discussion
                    </a>
                </li>
                <%HttpSession se = request.getSession();
                String role = (String)se.getAttribute("role");
                if(!role.equals("student")){%>
                <li>
                    <a href="addTopic.jsp">Add Topic</a>
                </li>
                <li>
                    <a href="Discussion?form=mytopic">My Topics</a>
                </li>
                
                <%} %>
                
                <li>
                    <a href="Discussion?form=topics">View Topics</a>
                </li>
                
            </ul>
        </div>
<jsp:include page="menu.jsp"></jsp:include>
<div>
<center>
<h3>Topics</h3>
<% ArrayList<String[]> al = (ArrayList<String[]>)request.getAttribute("list"); 
if(al != null){ %>

<table width="80%">
<tr>
<th>Topics</th>
<th></th>
<th></th>
</tr>
<%	
for(int i =0;i<al.size();i++){
String d[] = al.get(i);
%>
<tr>
<td><a href="Discussion?form=topicDetail&topicid=<%=d[0] %>&name=<%=d[1]%>"><%=d[1] %></a></td>
<td><%=d[2] %></td>
<td>

<% if(!role.equals("student")){%>
<a href="Discussion?form=deletetopic&topicid=<%=d[0] %>">delete</a>

<%} %>

</td>

</tr>

<%}} %>


</table>
</center>
</div>

</div>
</body>
</html>