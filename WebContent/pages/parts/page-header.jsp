<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-header">
	<c:choose>
		<c:when
			test="${param.type eq 'forum' ? true : false}">
			<h1 class="col-sm-3 pull-left">
				<a href="${pageContext.request.contextPath}/pages/forum.jsp">
					Family Forum <br> <small class="page-header-subtitle">Everything
						about life</small>
				</a>
			</h1>
			<div class="col-sm-5 input-group pull-left">
				<input type="text" class="form-control"
					placeholder="Search forum..."> <span
					class="input-group-btn">
					<button class="btn btn-default" type="button">Go!</button>
				</span>
			</div>
		</c:when>
		<c:when test="${param.type eq 'activity' ? true : false}"><div>
		<h1>
				<a href="${pageContext.request.contextPath}/pages/activityList.jsp">${param.title }
				</a>
		
		<button onclick = "location.href = 'activity-create.jsp'" class = "btn btn-primary col-md-6 pull-right">Create Activity</button>
		</h1></div>
			</c:when>
		<c:when test="${false}">
			for other page
		</c:when>
		<c:when test="${false}">
			for other page
		</c:when>
		<c:otherwise>
			<h1
				class="col-sm-${param.titleWidth eq null ? '3' : param.titleWidth } pull-left">
				<a href="${pageContext.request.contextPath}/pages/activityList.jsp">${param.title }<br>
					<small class="page-header-subtitle">${param.subTitle }</small>
				</a>
			</h1>

		</c:otherwise>
	</c:choose>

</div>
<!-- end of page header -->