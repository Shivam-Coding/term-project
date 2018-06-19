<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Announcement</title>
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
                        Announcement
                    </a>
                </li>
                
                 <%HttpSession se1 = request.getSession();
                String role = (String)se1.getAttribute("role");
                if(!role.equals("student")){%>
                
                <li>
                    <a href="addAnnouncement.jsp">Post Announcement</a>
                </li>
                
                <%} %>
                
                <li>
                    <a href="Announcement?form=announcement">View Announcement</a>
                </li>
                
            </ul>
        </div>
<jsp:include page="menu.jsp"></jsp:include>
<div>
<center>
<h3>Announcement</h3>
<% ArrayList<String[]> al = (ArrayList<String[]>)request.getAttribute("list");
String msg = (String)request.getAttribute("msg");
if(msg!=null){ %>
	<p style="color:blue;"><%=msg %></p>
<% }
HttpSession se = request.getSession();
String netid = (String)se.getAttribute("netid");%>
<br>
<table>



<%  if(al != null){
Iterator<String[]> it = al.iterator();
while(it.hasNext()){
String[] d = it.next();%>
<tr>
<td>
<h4><%=d[2] %></h4></td><td></td>
<tr><td></td>
<td><%=d[1] %></td>
</tr>

<%if(d[4] != null && !d[4].isEmpty()){ %>

<tr><td>
email: </td>
<td>

<a href="mailto:<%=d[4] %>">Send Mail


</td></tr>

<%} if(d[3] != null && !d[3].isEmpty()){ %>

<tr><td>
link: </td><td><a href="<%=d[3] %>" target="_blank"><%=d[3] %></a>
</td>
</tr>
<%if(role.equals("student")){  %>
<tr><td><a href="Announcement?form=applyJob&id=<%=d[0]%>">Apply</a></td></tr>
<%} }
if(d[5].equals(netid)){%>
<tr><td>
<a href ="addAnnouncement.jsp?id=<%=d[0]%>&cat=<%=d[2]%>">edit</a> </td>
<td><a href="Announcement?form=delete&id=<%=d[0]%>">delete</a>
</td>
</tr>	
	
<% }%>



<%} } %>
</table>
</center>
</div>
</div>
</body>
</html>