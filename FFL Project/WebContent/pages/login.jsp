<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
<title>Login</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%-- end of header --%>

<div class="container">
		<jsp:include page="parts/page-header.jsp">
			<jsp:param value="login" name="type" />
			<jsp:param value="Login" name="title" />
			<jsp:param value="5" name="titleWidth" />
			<jsp:param value="Welcome to FamForLife" name="subTitle" />
		</jsp:include>
		<div class="login-main-container">
			<div class="col-md-5">
				<div class="panel panel-primary">
				  <div class="panel-heading">
				    <h3 class="panel-title">Sign in to FamForLife</h3>
				  </div>
				  <div class="panel-body">
				  	<form action="${pageContext.request.contextPath}/LoginServlet" method="post" id="login">
						<div class="form-group">Email:<input type="text" class="form-control" placeholder="email" name="email"></div>
			   			<div class="form-group">Password:<input type="password" class="form-control" placeholder="password" name="userPw"></div>
			   			<button type="submit" class="btn btn-default">Login</button>
			   			<a href="${pageContext.request.contextPath}/ForgetPw">Forget Password</a>
			   			<%if(request.getAttribute("message")!=null){%>
			   					<%=request.getAttribute("message") %>
			   			<%} %>
	   				</form>
				  </div>
				</div>
	 		</div>
			<!--  end of login panel -->
			<div class="col-md-7">
	  			<h3>No Account?</h3>
	  			<p>
	  				Clink the link below to create one now!
	  			</p>
	  			<button onclick='location.href="${pageContext.request.contextPath}/Signup"' class="btn btn-success">Sign up now!</button>
	  		</div>
  		</div>
</div>

<%-- end of main container --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>
</body>
</html>
