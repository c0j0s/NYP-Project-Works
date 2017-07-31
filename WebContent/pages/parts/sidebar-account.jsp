<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel panel-primary forum-sidebar-account">
	<c:choose>
		<c:when test="${user eq null ? true : false}">
			<div class="panel-heading ">
				<h3 class="panel-title text-center">Login</h3>
			</div>
			<div class="panel-body">
				<form action="${pageContext.request.contextPath}/LoginServlet?redirect=${param.url}"
					method="post" id="login">
					<div class="form-group">
					Email:
					<input type="text" class="form-control" placeholder="email" name="email">
					</div>
					<div class="form-group">
					Password:
					<input type="password" class="form-control" placeholder="password" name="userPw">
					</div>
					<button type="submit" class="btn btn-default">Login</button>
					<a href="${pageContext.request.contextPath}/ForgetPw">Forget
						Password</a>
					<%
						if (request.getAttribute("message") != null) {
					%>
					<%=request.getAttribute("message")%>
					<%} %>

				</form>
			</div>
		</c:when>
		<c:otherwise>
			<div class="panel-heading ">
					<img alt="../img/sample.jpg" src="${user.imgUrl }"
						class="img-circle profile-image-medium">
					<h3 class="panel-title text-center">${user.givenName}</h3>
			</div>
			<c:choose>
				<c:when test="${param.type eq 'forum' ? true : false}">
					<div class="panel-body">
						<ul class="list-group">
							<a class="list-group-item">Best Answer <span class="badge badge-info">${user.bestAnswerCount }</span></a>
							<a class="list-group-item">Questions Post <span class="badge badge-info">${user.postsCounts }</span></a>
							<a class="list-group-item">Questions Answered <span class="badge badge-info">${user.commentCounts }</span></a>
						</ul>
						<button type="button" class="btn btn-success btn-block"
							onclick="location.href='${pageContext.request.contextPath}/ForumEdit?type=post&mode=create'">Ask
							Something</button>
					</div>
				</c:when>
				<c:when test="${param.type eq 'profile' ? true : false}">
					<div class="panel-body">
						<div class="col-sm-4 text-center no-padding">
							<p>
								${param.points}<br> Points
							</p>
						</div>
						<div class="col-sm-4 text-center no-padding">
							<p>
								${param.creditLevel}<br> Credit Level
							</p>
						</div>
						<button type="button" class="btn btn-success btn-block"
							onclick="location.href=''">Rewards Page</button>
					</div>
				</c:when>
				<c:when test="${param.type eq 'admin' ? true : false}">
					<div class="panel-body">
						<span class="label label-info">Admin</span>
					</div>
				</c:when>
				<c:when test="${param.type eq 'activity' ? true : false}">
					<div class="panel-body">
						//for activity
					</div>
				</c:when>
			</c:choose>
		</c:otherwise>
	</c:choose>
</div>