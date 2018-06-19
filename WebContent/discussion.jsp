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
<%String name = (String)request.getAttribute("name");
String topicid = (String)request.getAttribute("topicid");
%>

<center>
<h3><%=name %></h3>
<% ArrayList<String[]> al = (ArrayList<String[]>)request.getAttribute("list"); 
ArrayList<String[]> al1 = (ArrayList<String[]>)request.getAttribute("list1"); %>
<form action="Discussion?form=addcomment" method="post">
<input type="hidden" name="topicid" value="<%=topicid%>">
<input type="hidden" name="name" value="<%=name%>">
<table>
<tr>
<td>
<input name="comment" placeholder="comment">
</td>
<td>
<input type="submit" value="Comment">
</td>
</tr>




</table>
</form>

<% if(al != null){ %>

<table width="80%">
<%	
for(int i =0;i<al.size();i++){
String d[] = al.get(i);
%>
<tr>

<td> <b><%=d[1] %></b></td>

<td>


<% if(!role.equals("student")){%>
<a href="Discussion?form=deletecomment&commentid=<%=d[0]%>&topicid=<%=topicid%>&name=<%=name%>">delete</a>
<%} %>


</td>
</tr>

<%if(al1 !=null){
	for(int j=0;j<al1.size();j++){
		String[] s = al1.get(j); 
	   
		if(s[3].equals(d[0])){%>
		
	<tr>
	<td><%=s[1] %></td>
<td>

<% if(!role.equals("student")){%>

<a href="Discussion?form=deletereply&replyid=<%=s[0]%>&topicid=<%=topicid%>&name=<%=name%>">delete</a>

<%} %>

</td>
	</tr>	
		
<%		
	}} %>
<tr>	
<td>
	<form action="Discussion?form=addreply" method="post">
	<input type="hidden" name="topicid" value="<%=topicid%>">
    <input type="hidden" name="name" value="<%=name%>">
    <input type="hidden" name="commentid" value="<%=d[0]%>">
	<input name ="reply" placeholder="reply"></td>
	<td><input type="submit" value="Reply"></td>
	</form>
	</tr>
<% 	
} %>



<%}} %>


</table>
</center>
</div>

</div>
</body>
</html>