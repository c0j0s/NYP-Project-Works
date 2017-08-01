<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="../WEB-INF/ffl.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>Template</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%-- end of header --%>

	<div class="container">
	<jsp:include page="parts/page-header.jsp">
		<jsp:param value="famGrp" name="type" />
		<jsp:param value="Family Group" name="title" />
		<jsp:param value="5" name="titleWidth" />
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
			<button onclick="location.href='${pageContext.request.contextPath}/CreateFamGroup'"">Create Family Group</button>
			<button onclick="location.href='${pageContext.request.contextPath}/DisplayFamGroup'"">View Family Group</button>
		</div>
		</div>
		<div class="col-md-9">
			<br>
			<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#createFamily">Create
						Family Group</a></li>
				<li><a data-toggle="tab" href="#joinFamily">Join Family
						Group</a></li>
				<%if(request.getAttribute("fGroup") !=null) {%><li><a data-toggle="tab" href="#viewFamily">View Family
						Group</a></li><%} %>

			</ul>


			<div class="tab-content">
				<div id="createFamily" class="tab-pane fade in active col-md-6">
					<form action="${pageContext.request.contextPath}/CreateFamilyGrp"
						method="post" id="form-upload">
						<h3>Create Family Group</h3>
						<input type="hidden" value="${user.accountId}" name="owner">
						<p>
							Group Name : <input type="text" name="grpName">
						</p>
						<p>
							Group Password : <input type="password" name="password">
						<p>
							Group Image: <input type="file" name="file"
								class="form-control fullactpic" id="form-upload"
								accept="image/*" size="60"><input type="hidden"
								name="imgurl" id="imgurl"
								data-imgfolder="activity/${user.accountId }" /><img alt=""
								src="${pageContext.request.contextPath}/img/def.png"
								id="test-img-prev" class="profile-image-largest text-center">
						<div class="col-md-12">
							<button type="reset" class="col-md-5 btn btn-danger pull-left">Reset</button>
							<p class="col-md-2"></p>
							<button type="submit"
								class="col-md-5 btn btn-success  pull-right">Create
								Family Group</button>
						</div>

					</form>
				</div>








				<div id="joinFamily" class="tab-pane fade">

					<h3>Join Family Group</h3>
					<form action="${pageContext.request.contextPath}/JoinFamilyGroup" method="post">
					<input type="hidden" value="${user.accountId}" name="owner">
					<p>
						Group Id: <input type="text" name="grpId">
					</p>
					<p>
						Password: <input type="password" name="grpPassword">
					</p>
					<button type="submit"
								class="col-md-5 btn btn-success  pull-right">Create
								Family Group</button>
				</form>


				</div>
			
				<div id="viewFamily" class="tab-pane fade">

					<%
					if(request.getAttribute("fGroup") != null){
						ArrayList<FamilyGrp> fGrpList = (ArrayList<FamilyGrp>) request.getAttribute("fGroup");
						System.out.println("log j:"+ fGrpList.size());	
						for (FamilyGrp fg : fGrpList) {
						%>
						<div>
							<%=fg.getFamilyGroupId()%>
							<%=fg.getGroupCreationDate()%>
							<%=fg.getGroupName()%>
							<%=fg.getGrpOwner()%>
							<%=fg.getImgUrl()%>
							<%=fg.getPassword()%>
						</div>
						<%
						}
					}
				%>
				</div>




		</div>
	</div>
	

	</div>



	<%-- end of main container --%>
	<jsp:include page="footer.jsp"></jsp:include>
	<%-- end of footer --%>

</body>
</html>