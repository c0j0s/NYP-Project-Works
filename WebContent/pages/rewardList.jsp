<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ page import="java.util.ArrayList,bean.*"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='${pageContext.request.contextPath}/css/bootstrap.css' rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/bootstrap.custom.css' rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/master.css' rel='stylesheet'>
<link rel='icon' href='favicon.ico' type='image/x-icon' />
<title>Redemption Page</title>

</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%-- end of header --%>

<div class="container">
	<jsp:include page="parts/page-header.jsp">
		<jsp:param value="Redemption" name="title"/>
		<jsp:param value="5" name="titleWidth"/>
	</jsp:include>
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
			<button type="submit" class="btn btn-default"
				onclick="location.href='${pageContext.request.contextPath}/UpdateProfile'">Update
				Profile</button>
						<form action="${pageContext.request.contextPath}/CreateFamGroup" ><input type="hidden" name = "userIdFg" value ="${user.accountId}">	<button class="btn btn-success"
							onclick="location.href = 'CreateFamGroup'">Family Group</button></form></div>
			<button type="submit" value="invalid" onclick="location.href='${pageContext.request.contextPath}/InvalidateAccount'">Delete your account</button>
		</div>
	<div class="col-md-9">
	
		<%
			ArrayList<RewardItem> rewList = (ArrayList<RewardItem>) request.getAttribute("rewardList");
			for (RewardItem rew : rewList) {
		%>
		
		<div class="panel panel-default">
				<div class="panel-heading">
				<h3><%=rew.getRewardTitle()%></h3></div>
				<div class="panel-body">
			<div class="col-md-4">
				<img class="fullactpic" src="<%=rew.getImgUrl()%>" />
			</div>
			<div class="col-md-8">
				
				<p>
					Reward Description :<%=rew.getRewardDescription()%></p>
				<p>
					Points :
					<%=rew.getPoints()%></p>
				
				<p>
					Reward Quantity :
					<%=rew.getRewardQuantity()%></p>
				
			</div>
		</div></div>
		<br></br>
		<%}  %>
	</div>
	<!-- end of list -->
	<div class="col-sm-12 col-md-3">
		<div class="sticky-sidebar">
			<div class="col-md-12 col-sm-4">
				<jsp:include page="parts/sidebar-account.jsp">
					<jsp:param value="RedemptionList" name="url" />
					<jsp:param value="Redemption" name="type" />
				</jsp:include>
			</div>
		</div>
	</div>
	<!-- end of sidebar -->
</div>

<%-- end of main container --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>

</body>
</html>
