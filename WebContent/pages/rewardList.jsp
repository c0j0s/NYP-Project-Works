<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='../css/bootstrap.css' rel='stylesheet'>
<link href='../css/bootstrap.custom.css' rel='stylesheet'>
<link href='../css/master.css' rel='stylesheet'>
<link rel='icon' href='favicon.ico' type='image/x-icon' />
<title>Reward List</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%-- end of header --%>
 
<div class="container">
	<jsp:include page="parts/page-header.jsp">
		<jsp:param value="Reward List" name="title"/>
		<jsp:param value="5" name="titleWidth"/>
	</jsp:include>
<br>
<div class = "col-md-9">
<%for(int i =  0;i < 5;i++){ %>
	<div class = "clearfix">
		<div class="col-md-4"><img class = "activitypic" src = "../img/sample.jpg" /></div>
		<div class="col-md-8"><h4>Reward Item</h4>
		<p>Reward Title : Dining Voucher</p>
		<p>Reward Description : Voucher worth for $15</p>
		<p>Points : 20</p>
		<p>Reward Availability : Y </p>
		<p>Reward Quantity : 55</p>
		<p>Valid : Y</p>
		<p><span class="glyphicon glyphicon-thumbs-up"></span> 7	<span class="glyphicon glyphicon-thumbs-down"></span> 27</p> 
		</div>
		
	</div>
	 <br></br>
	<%}  %></div>
	<div class = "col-md-3">
	<ul class = "list-group">
	<h4>Reward List</h4>
		<%for(int z = 0;z<20;z++){ %>
		<li class="list-group-item">
		<%=z+1 %>. Java
			<span class = "badge"><%=z %></span>
			
		</li>
		<%} %>
	</ul></div>
</div>

<%-- end of main container --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>

</body>
</html>
