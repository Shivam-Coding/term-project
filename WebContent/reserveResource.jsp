<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="java.util.*" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resource</title>
	<link href="https://fonts.googleapis.com/css?family=Yanone+Kaffeesatz" rel="stylesheet" type="text/css">
	<style type="text/css">
		body, html {
			padding:0px;
			margin:0px;
			background: url('images/bg.jpg') center;
			background-size:cover;
			background-attachment: fixed;
			text-align:center;
			
			line-height: 1.4em;
			font-family: "Trebuchet MS", Helvetica, sans-serif;
		}
		body {
			padding:10vh 0;
		}
		h1 {
			font-family: 'Yanone Kaffeesatz', sans-serif;
			text-align: center;
			font-size: 77px;
			text-shadow: 0 0px 30px rgba(0, 0, 0, 0.2);
		}
		h2 {
			font-family: 'Yanone Kaffeesatz', sans-serif;
			font-size:30px;
			text-shadow: 0 0px 20px rgba(0, 0, 0, 0.3);
			color:#fff;
		}
		.monthly {
			box-shadow: 0 13px 40px rgba(0, 0, 0, 0.5);
		}

		input[type="text"] {
			padding: 15px;
			border-radius: 2px;
			font-size: 16px;
			outline: none;
			border: 2px solid rgba(255, 255, 255, 0.5);
			background: rgba(63, 78, 100, 0.27);
			color: #fff;
			width: 250px;
			box-sizing: border-box;
			font-family: "Trebuchet MS", Helvetica, sans-serif;
		}
		input[type="text"]:hover {
			border: 2px solid rgba(255, 255, 255, 0.7);
		}
		input[type="text"]:focus {
			border: 2px solid rgba(255, 255, 255, 1);
			background:#eee;
			color:#222;
		}

		.button {
			display: inline-block;
			padding: 15px 25px;
			margin: 25px 0 75px 0;
			border-radius: 3px;
			color: #fff;
			background: #000;
			letter-spacing: .4em;
			text-decoration: none;
			font-size: 13px;
		}
		.button:hover {
			background: #3b587a;
		}
		.desc {
			max-width: 250px;
			text-align: left;
			font-size:14px;
			padding-top:30px;
			line-height: 1.4em;
		}
		.resize {
			background: #222;
			display: inline-block;
			padding: 6px 15px;
			border-radius: 22px;
			font-size: 13px;
		}
		@media (max-height: 700px) {
			.sticky {
				position: relative;
			}
		}
		@media (max-width: 600px) {
			.resize {
				display: none;
			}
		}
	</style>
	<link rel="stylesheet" href="css/monthly.css">
	<link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/simple-sidebar.css" rel="stylesheet">
<body style="margin:0; padding:0">
<div id="wrapper">
<div id="sidebar-wrapper">
            <ul class="sidebar-nav" >
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
<h3><%String name = (String)request.getAttribute("name");
String date = (String)request.getAttribute("date");
out.print(name);%></h3>
<table width="50%">
<tr>
<th>Name</th>
<th>Date</th>
<th>Slot</th>
<th>User</th>
</tr>

<% ArrayList<String[]> al = (ArrayList<String[]>)request.getAttribute("list"); 

if(al!=null){
for(int i = 0;i<al.size(); i++){
	String d[] = al.get(i);

%>


<tr>
<td><%=d[0] %></td>
<td><%=d[1] %></td>
<td><%=d[2] %></td>
<td><%=d[3] %></td>

</tr>

<%} }%>
</table>

<br>
<div style="display:inline-block;">
<form action="Resource?form=reserveSlot" method="post" >
<input type="hidden" name="name" value="<%=name%>">

			<input type="text" id="mytarget" name="date" value="<% if(date != null){out.print(date);}else{out.print("Select Date");}%>">
			<input type="submit" value="Select">
			</form>
			<div class="monthly" id="mycalendar2"></div>
		</div>


<%ArrayList<String> slots = (ArrayList<String>)request.getAttribute("list1");
if(slots!=null){%>
<h3>Available Slots</h3>
<form action="Resource?form=reserveSlot1" method="post" onsubmit="return confirm('Do you want to book ?');">
<input type="hidden" name="date" value="<%=date%>">
<input type="hidden" name="name" value="<%=name%>">
<table width="17%">
<tr>
<th>Slots</th>
<th>Select</th>
</tr>

<% 

for(int i = 0;i<slots.size(); i++){
	String d = slots.get(i);

%>


<tr>
<td><%=d %></td>
<td><input type="checkbox" name="slots" value="<%=d%>"></td>

</tr>

<%} %>


</table>
<input type="submit" value="Book">
</form>
<%} %>
</center>
</div>
</div>

<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/monthly.js"></script>
<script type="text/javascript">
	$(window).load( function() {

		$('#mycalendar').monthly({
			mode: 'event',
			xmlUrl: 'events.xml'
		});

		$('#mycalendar2').monthly({
			mode: 'picker',
			target: '#mytarget',
			setWidth: '250px',
			startHidden: true,
			showTrigger: '#mytarget',
			stylePast: true,
			disablePast: true
		});

	switch(window.location.protocol) {
	case 'http:':
	case 'https:':
	// running on a server, should be good.
	break;
	case 'file:':
	alert('Just a heads-up, events will not work when run locally.');
	}

	});
</script>
</body>
</html>