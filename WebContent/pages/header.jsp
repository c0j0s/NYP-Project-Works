<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "../WEB-INF/ffl.tld" %>
<c:set var = "user" scope = "session" value = "${sessionScope.account}"/>
<nav class="navbar navbar-default">
	<div class="container-fluid navbar-topbar">
		<div class="container">
			<ul class="topbar-nav">
<<<<<<< HEAD
				<li><a class="white" href="${pageContext.request.contextPath}/pages/login.jsp">LOGIN</a></li>
				<li><a class="white" href="${pageContext.request.contextPath}/pages/signup.jsp">SIGN UP</a></li>
        		<li><a class="white">CONTACT US</a></li>
=======
				<c:choose>
					<c:when test="${user eq null ? false : true }">
						<li><a class="white" href="${pageContext.request.contextPath}/pages/profile.jsp">
						<img alt="" src="" class="img-circle profile-image-xxsmall">
						<span>${user.givenName}</span>
						</a></li>
						<li><a class="white" href='logout.jsp'>Logout</a></li>
					</c:when>
					<c:otherwise>
						<li><a class="white" href="${pageContext.request.contextPath}/pages/login.jsp">Login</a></li>
						<li><a class="white">Sign up</a></li>
					</c:otherwise>
				</c:choose>
						<li><a class="white">Contact us</a></li>
>>>>>>> f6663ddbaaa4bbfa211cb7522473ee1247442a68
			</ul>
		</div>
	</div>
	<!-- end to top bar -->
	<div class="container nav-bottombar">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#ffl-navbar-collapse" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">FFL</a>
		</div>
		<div class="collapse navbar-collapse" id="ffl-navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="${pageContext.request.requestURI eq '/FFL/pages/index.jsp' ? ' active' : ''}"><a href="${pageContext.request.contextPath}/pages/index.jsp">Home</a></li>
				<li class="${pageContext.request.requestURI eq '/FFL/pages/forum.jsp' ? ' active' : ''}"><a href="${pageContext.request.contextPath}/pages/forum.jsp?category=general&page=1">Family Forum</a></li>
				<li class="${pageContext.request.requestURI eq '/FFL/pages/activityList.jsp' ? ' active' : ''}"><a href="${pageContext.request.contextPath}/pages/activityList.jsp">Family Activities</a></li>
				<li class="${pageContext.request.requestURI eq '/FFL/pages/redeem.jsp' ? ' active' : ''}"><a href="#">Reward Redemption</a></li>
				<li class="${pageContext.request.requestURI eq '/FFL/pages/redeem.jsp' ? ' active' : ''}"><a href="${pageContext.request.contextPath}/pages/test.jsp">component testing page</a></li>
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

