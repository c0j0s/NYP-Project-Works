<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='${pageContext.request.contextPath}/css/bootstrap.css' rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/bootstrap.custom.css' rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/master.css' rel='stylesheet'>
<link rel='icon' href='favicon.ico' type='image/x-icon' />
<title>Sign Up</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%-- end of header --%>

<div class="container">
		<jsp:include page="parts/page-header.jsp">
			<jsp:param value="login" name="type" />
			<jsp:param value="Sign Up" name="title" />
			<jsp:param value="5" name="titleWidth" />
			<jsp:param value="Welcome to FamForLife" name="subTitle" />
		</jsp:include>
		<div class="col-md-12 login-main-container"> 
		<form action="${pageContext.request.contextPath}/signupServlet" method="post" id="form-upload">
			<div class="col-md-6 ">
				<h3>User Details</h3>
				<hr>
				<div class="form-group">
					<label for="firstName">First Name:</label> 
					<input type="text" class="form-control" name="firstName" placeholder="First Name">
				</div>
				<div class="form-group">
					<label for="lastName">Last Name:</label> 
					<input type="text" class="form-control" name="lastName" placeholder="Last Name">	
				</div>
				<div class="form-group">
					<label for="dob">Date of Birth</label> 
					<input type="date" class="form-control" name="dob">
				</div>
				<div class="form-group">
					<label for="gender">Gender:</label><br>
					<input type="radio" name="gender" value="male"> Male
	 				<input type="radio" name="gender" value="female"> Female<br>
				</div>
				<div class="form-group">
					<label for="address">Address: </label> 
					<input type="text" class="form-control" name="address" placeholder="Address">
				</div>
				<div class="form-group">
					<label for="mobileno">Mobile No.: </label> 
					<input type="text" class="form-control" name="mobileno">
				</div>
				<button type="submit" class="btn btn-success" value="sendform">Submit</button>
			</div>
			<div class="col-md-6">
				<h3>Account Details</h3>
				<hr>
				<div class="form-group">
					<label for="email">Email: </label> 
					<input type="text" class="form-control" name="email" placeholder="Email">
				</div>
				<div class="form-group">
					<label for="pw">Password: </label> 
					<input type="password" class="form-control" name="pw" placeholder="Password">
				</div>
				<div class="form-group">
					<label for="cpw">Password: </label> 
					<input type="password" class="form-control" name="cpw" placeholder="Confirm Password">
				</div>
				<div class="form-group">
					<label for="profilepic">Choose your profile picture:</label>
					<input type="file" name="file" size="60"/>
	        		<input type="hidden" name="imgurl" id="imgurl" data-imgfolder="user/${user.accountId }"/>
	        		<img alt="" src="" id="test-img-prev">
				</div>
			</div>
			
		</form>
		</div>
</div>

<%-- end of main container --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>

</body>
</html>
