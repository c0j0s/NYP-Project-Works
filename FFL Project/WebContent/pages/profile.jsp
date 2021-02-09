<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ page import ="bean.*,java.util.ArrayList" %>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='${pageContext.request.contextPath}/css/bootstrap.css' rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/bootstrap.custom.css' rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/master.css' rel='stylesheet'>
<link rel='icon' href='favicon.ico' type='image/x-icon' />
<title>My Profile</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%-- end of header --%>

<div class="container">
	<jsp:include page="parts/page-header.jsp">
		<jsp:param value="profile" name="type" />
		<jsp:param value="My Profile" name="title" />
		<jsp:param value="5" name="titleWidth" />
	</jsp:include>
<div class="col-md-12" >
	<div class="col-md-3 col-sm-12">
		<div class="col-md-12">
			<jsp:include page="parts/sidebar-account.jsp">
				<jsp:param value="profile" name="type" />
				<jsp:param value="${user.points}" name="points" />
				<jsp:param value="${user.creditLevel}" name="creditLevel" />
			</jsp:include>
		</div>
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">My Profile</h3>
				</div>
				<div class="panel-body">
					<p>First Name:${user.givenName}</p>
					<p>Last Name:${user.surName}</p>
					<p>Date of Birth:${user.dob}</p>
					<p>Gender:${user.gender}</p>
					<p>Email:${user.email}</p>
					<p>Address:${user.address}</p>
					<p>Mobile No.:${user.mobileno}</p>
				</div>

			</div>
		</div>
	</div>
		<div class="col-md-9 col-sm-12">
			  <ul class="nav nav-tabs" role="tablist">
			    <li role="presentation" class="active"><a href="#Forum" aria-controls="Forum" role="tab" data-toggle="tab">My Forum Post</a></li>
			    <li role="presentation" class="${tab eq 'Activity' ? 'active' : '' }"><a href="#Activity" aria-controls="Activity" role="tab" data-toggle="tab">My Activities</a></li>
			  </ul>
			
			  <div class="tab-content">
			    <div role="tabpanel" class="tab-pane active" id="Forum">
			    <ul class="list-group">
			    	<%ArrayList<bean.Post> post = (ArrayList<bean.Post>) request.getAttribute("post"); 
			    	for(int i=0; i < post.size(); i++){
			    		Post pt = post.get(i);
			    		%>
			    		
  							<li class="list-group-item"><a href="Post?postId=<%=pt.getPostId()%>"><%=pt.getPostTitle() %></a></li>
			    		
			    	<%}%>
				</ul>
			    </div>
			    <div role="tabpanel" class="tab-pane ${tab eq 'Activity' ? 'active' : '' }"" id="Activity">
			    	<%ArrayList<bean.ActReg> act = (ArrayList<bean.ActReg>)request.getAttribute("ar");
			    	for (ActReg a : act){%>
			    		
			    		<li class="list-group-item"><a href="ActFull?activityId=<%=a.getActivityactivityId() %>"><%=a.getActivityTitle() %></a>&nbsp&nbsp<button class="btn btn-danger"
									onclick="location.href = 'DeleteRegistration?registrationId=<%=a.getRegistrationId() %>&activityId=<%=a.getActivityactivityId() %>'" class="pull-right">Delete Registration</button></li>
			    		
			    	<%} %>
			    	
			    </div>
			  </div>
		</div>
</div>
</div>
<%-- end of main container --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>

</body>
</html>