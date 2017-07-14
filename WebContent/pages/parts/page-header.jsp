<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-header">
	<c:choose>
		<c:when
			test="${param.type eq 'forum' ? true : false}">
			<h1 class="col-sm-3 pull-left">
				<a href="${pageContext.request.contextPath}/Forum">
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
				<a href="${pageContext.request.contextPath}/ActList" class="col-md-6">Activities For Families
				</a>
		<div class="col-md-4"> </div>
		<button onclick = "location.href = 'CreateAct'" class = "btn btn-primary col-md-2 pull-right">Create Activity</button>
		</h1></div>
			</c:when>
		<c:when test="${param.type eq 'activityfull' ? true : false}"><div><h1>
				<a href="${pageContext.request.contextPath}/ActList" class="col-md-6">Activities For Families
				</a>
				<div class="col-md-1"></div>
		<button onclick = "location.href = '${pageContext.request.contextPath}/ActList'" class = "btn btn-primary col-md-2">View Activity List</button>
		<div class="col-md-1"></div>
		<button onclick = "location.href = '${pageContext.request.contextPath}/CreateAct'" class = "btn btn-primary col-md-2 pull-right">Create Activity</button>
		</h1></div>
		</c:when>
		<c:when test="${false}">
			for other page
		</c:when>
		<c:otherwise>
			<h1
				class="col-sm-${param.titleWidth eq null ? '3' : param.titleWidth } pull-left">
				<a href="${pageContext.request.contextPath}/ActList">${param.title }<br>
					<small class="page-header-subtitle">${param.subTitle }</small>
				</a>
			</h1>

		</c:otherwise>
	</c:choose>

</div>
<!-- end of page header -->