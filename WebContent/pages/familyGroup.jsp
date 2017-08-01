<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>

<%@ page import="java.util.ArrayList,bean.*,java.text.DecimalFormat"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='${pageContext.request.contextPath}/css/bootstrap.css'
	rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/bootstrap.custom.css'
	rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/master.css'
	rel='stylesheet'>
<link rel='icon' href='favicon.ico' type='image/x-icon' />
<title>My Profile</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%-- end of header --%>

	<div class="container">
		<jsp:include page="parts/page-header.jsp">
			<jsp:param value="activity" name="type" />
			<jsp:param value="Family Group" name="title" />
			<jsp:param value="5" name="titleWidth" />
		</jsp:include>
		<div class="col-md-12">
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
					<button
						onclick="location.href='${pageContext.request.contextPath}/CreateFamGroup'"">Create
						Family Group</button>
					<button
						onclick="location.href='${pageContext.request.contextPath}/DisplayFamGroup'"">View
						Family Group</button>
				</div>
			</div>
			<div class="col-md-9 col-sm-12">
			<div><%
					
						ArrayList<FamilyGrp> fGrpList = (ArrayList<FamilyGrp>) request.getAttribute("fGroup");
						System.out.println("log j:"+ fGrpList.size());	
						for (FamilyGrp fg : fGrpList) {
						%>
						<div>
						<p><%=fg.getFamilyGroupId()%></p>
							<p><%=fg.getGroupCreationDate()%></p>
							<p><%=fg.getGroupName()%></p>
						<p><%=fg.getGrpOwner()%></p>
						<% String imgUrl = fg.getImgUrl(); %>
						<%if (imgUrl.equals(null)){ %><img 
								src="${pageContext.request.contextPath}/img/def.png"
								 class="profile-image-largest text-center"><%}else{ %>
						<img src="<%=fg.getImgUrl()%>" ><%} %>
							
						</div>
						<%
						}
					
				%></div>	
			</div>
		</div>
	</div>
	<%-- end of main container --%>
	<jsp:include page="footer.jsp"></jsp:include>
	<%-- end of footer --%>

</body>
</html>
