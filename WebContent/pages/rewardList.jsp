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
		<span aria-hidden="true">
								<button class="btn btn-success"
									onclick="location.href = 'claimReward?point=<%=rew.getPoints() %>'">Claim Reward</button>
							</span>
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
