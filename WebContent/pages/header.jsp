<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "../WEB-INF/ffl.tld" %>
<c:set var = "user" scope = "session" value = "${sessionScope.account}"></c:set>
<nav class="navbar navbar-default">
	<div class="container-fluid navbar-topbar">
		<div class="container">
			<ul class="topbar-nav">
				<c:choose>
					<c:when test="${user eq null ? false : true }">
						<li>
							<a class="white" href="${pageContext.request.contextPath}/MyProfile">
							<img alt="" src="${user.imgUrl}" class="img-circle profile-image-xxsmall">
							<span>${user.givenName}</span>
							</a>
						</li>
						<li>
							<span class="btn btn-warning btn-xs btn-no-border" onclick='location.href="RedemptionList"'>
								<span class="glyphicon glyphicon-piggy-bank" aria-hidden="true"></span>
								<span><jsp:include page="../getUserPoints"></jsp:include></span>
							</span>
						</li>
						<li  class="notification-li">
							<a class="white" id="toogle-notification">
								<span class="glyphicon glyphicon glyphicon-bell" aria-hidden="true"></span>
								<span class="label notification-count  label-danger"><jsp:include page="../getNotificationCount" /></span>
							</a>
							<div class='panel panel-info' id='notification-panel'>
								<div class='panel-heading'>
									You have 
									<span class='notification-count '>${notificationCount }</span>
									unread messages 
									<span class='close-notification glyphicon glyphicon glyphicon-remove pull-right' aria-hidden='true'></span>
								</div>
							<div class='panel-body list-group' id="notification-body">
							</div>
							<a class="btn btn-info btn-block" id="toogle-allnotification" role="button">view all past notifications</a>
							</div>
						</li>
						<li><a class="white" href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></li>
						<c:if test="${true }">
							<li><a class="white" href="${pageContext.request.contextPath}/AdminPanel">Admin Panel</a></li>
						</c:if>
					</c:when>
					<c:otherwise>
						<li><a class="white" href="${pageContext.request.contextPath}/Login">Login</a></li>
						<li><a class="white" href="${pageContext.request.contextPath}/Signup">Sign up</a></li>
					</c:otherwise>
				</c:choose>
						<li><a class="white">Contact us</a></li>
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
			<a class="logo-a" href="${pageContext.request.contextPath}/Index">
				<img alt="Logo" src="${pageContext.request.contextPath}/img/logo.png" class="logo"/>
				<p>FamForLife</p>
			</a>
		</div>
		<div class="collapse navbar-collapse" id="ffl-navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="${pageContext.request.requestURI eq '/FFL/pages/index.jsp' ? ' active' : ''}"><a href="${pageContext.request.contextPath}/Index">Home</a></li>
				<li class="${pageContext.request.requestURI eq '/FFL/pages/forum.jsp' ? ' active' : ''}"><a href="${pageContext.request.contextPath}/Forum">Family Forum</a></li>
				<li class="${pageContext.request.requestURI eq '/FFL/pages/activityList.jsp' ? ' active' : ''}"><a href="${pageContext.request.contextPath}/ActList">Family Activities</a></li>
				<li class="${pageContext.request.requestURI eq '/FFL/pages/redeem.jsp' ? ' active' : ''}"><a href="${pageContext.request.contextPath}/RedemptionList">Reward Redemption</a></li>
			</ul>
			<form class="navbar-form navbar-right" role="search" action="${pageContext.request.contextPath}/Search?">
			  <div class="form-group">
			    <input type="text" class="form-control" name="globalSearch" placeholder="Search">
			 	<input type="hidden" class="form-control" name="searchIn" value="all">
			  </div>
			  <button type="submit" class="btn btn-default">Submit</button>
			</form>
		</div>
	</div>
</nav>

