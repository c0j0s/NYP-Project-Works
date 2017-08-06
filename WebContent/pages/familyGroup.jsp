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
						<input type="hidden" name="userIdFg" value="${user.accountId}">

					</div>
					<button type="submit" class="btn btn-default"
						onclick="location.href='${pageContext.request.contextPath}/UpdateProfile'">Update
						Profile</button>
					<form action="${pageContext.request.contextPath}/CreateFamGroup">
						<input type="hidden" name="userIdFg" value="${user.accountId}">
						<button class="btn btn-success"
							onclick="location.href = 'CreateFamGroup'">Family Group</button>
					</form>
				</div>
			</div>
			<div class="col-md-9 col-sm-12">
				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="#groupInfo">Group
							Info</a></li>
					<li><a data-toggle="tab" href="#members">Members</a></li>
				</ul>
				<div class="tab-content">
					<div id="groupInfo" class="tab-pane fade in active ">
						<%
							ArrayList<FamilyGrp> fGrpList = (ArrayList<FamilyGrp>) request.getAttribute("fGroup");
							FamilyGrp fg = fGrpList.get(0);

							System.out.println("log j:" + fGrpList.size());
						%><br>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h2>
									Group Name :
									<%=fg.getGroupName() %>[<%=fg.getFamilyGroupId()%>]
								</h2>
							</div>
							<div class="panel-body">
								<%
							if (fg.getImgUrl().equals(null)) {
						%><img src="${pageContext.request.contextPath}/img/def.png"
									class="profile-image-largest text-center">
								<%
							} else {
						%>
								<img class="profile-image-largest text-center"
									src="<%=fg.getImgUrl()%>">
								<%
							}
						%>


								<p>
									Group Creation Date :
									<%=fg.getGroupCreationDate()%></p>

								<p>
									Group Owner :
									<%=fg.getGrpOwner()%></p>
						<%if(request.getAttribute("accountId").equals(fg.getGrpOwner())){%> <button type="submit" class="btn btn-danger"
						onclick="location.href='${pageContext.request.contextPath}/DeleteGroup?famGrpId=<%=fg.getFamilyGroupId() %>'">Delete Group</button>
						<%} %>	</div>
						</div>
					</div>
					<div id="members" class="tab-pane fade">
						<h3>Group Members</h3>
						<div class="table-responsive">
							<table class="table">
								<tr>
									<th>No.</th>
									<th>User Id</th>
									<th>Group Name</th>
								</tr>
								<%int z = 0;
									ArrayList<FamilyGrp> fMemList = (ArrayList<FamilyGrp>) request.getAttribute("members");
									for (FamilyGrp fgp : fMemList) {
										
								%>
								<tr>
									<td><%=z + 1%>.</td>
									<td><%=fgp.getAccountId()%></td>
									<td><%=fgp.getGivenName()%></td>
									<%if(request.getAttribute("accountId").equals(fgp.getGrpOwner())){  if(!(fgp.getAccountId().equals(fgp.getGrpOwner()))){  %>
									<td><span aria-hidden="true">
											<button class="btn btn-danger"
												onclick="location.href = 'MemberDelete?grpId=<%=fgp.getFamilyGroupId()%>&acctId=<%=fgp.getAccountId()%>'">Remove
												Member</button>
									</span></td>
									<%}} %>
								</tr>
								<%
									z++;
									}
								%>
							</table>
						</div>
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
