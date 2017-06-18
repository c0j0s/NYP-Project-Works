<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid navbar-topbar">
			<div class="container">
				<ul class="topbar-nav">
					<li><a class="white">SIGN UP</a></li>
	        		<li><a class="white">CONTACT US</a></li>
				</ul>
			</div>
		</div>
		<div class="container nav-bottombar">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#ffl-navbar-collapse"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">FFL</a>
			</div>
			<div class="collapse navbar-collapse" id="ffl-navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="${pageContext.request.requestURI eq '/FFL/pages/index.jsp' ? ' active' : ''}"><a href="index.jsp">Home</a></li>
					<li class="${pageContext.request.requestURI eq '/FFL/pages/forum.jsp' ? ' active' : ''}"><a href="forum.jsp?category=1&page=1">Family Forum</a></li>
					<li class="${pageContext.request.requestURI eq '/FFL/pages/activity.jsp' ? ' active' : ''}"><a href="activityList.jsp">Family Activities</a></li>
					<li class="${pageContext.request.requestURI eq '/FFL/pages/redeem.jsp' ? ' active' : ''}"><a href="#">Reward Redemption</a></li>
				</ul>
				<form class="navbar-form navbar-right" role="search">
				  <div class="form-group">
				    <input type="text" class="form-control" name="globalSearch" placeholder="Search">
				  </div>
				  <button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</div>
	</nav>
</body>
</html>
