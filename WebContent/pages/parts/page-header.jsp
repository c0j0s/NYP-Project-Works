<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-header">
	<c:choose>
		<c:when test="${param.type eq 'forum' ? true : false}">
			<h1 class="col-md-3 col-sm-5 pull-left">
				<a href="${pageContext.request.contextPath}/Forum"> Family Forum
					<br> <small class="page-header-subtitle">Everything
						about life</small>
				</a>
			</h1>
			<div class="col-sm-5 col-sm-6 input-group pull-left">
				<form action="${pageContext.request.contextPath}/Search" method="get">
					<input type="hidden" class="form-control" name="searchIn" value="post">
					<div class="input-group">
						<input type="text" class="form-control" name="globalSearch" placeholder="Search"> 
						<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="form.submit()">Go!</button>
						</span>
					</div>
				</form>
			</div>
		</c:when>
		<c:when test="${param.type eq 'activity' ? true : false}">
			<div>
				<h1>
					<a href="${pageContext.request.contextPath}/ActList"
						class="col-md-6">Activities For Families </a>
				</h1>
				<%if(session.getAttribute("account")!=null){%>
					<button onclick="location.href = 'CreateAct'"
					class="btn btn-primary col-md-2 pull-right">Create
					Activity</button>
				<%} %>
			</div>
		</c:when>
		<c:when test="${param.type eq 'activityfull' ? true : false}">
			<div>
				<h1 class="col-md-6">
					<a href="${pageContext.request.contextPath}/ActList" >Activities For Families </a>
				</h1>
				<div class="col-md-4 pull-right">
					<button onclick="location.href = '${pageContext.request.contextPath}/ActList'"
						class="btn btn-primary">View Activity List</button>
					<% if(session.getAttribute("account")!=null){%>
					<button onclick="location.href = '${pageContext.request.contextPath}/CreateAct'"
						class="btn btn-primary">Create Activity</button>
					<%} %>
				</div>
			</div>
		</c:when>
		<c:when test="${param.title == 'Redemption'? true:false}">
			<h1
				class="col-sm-${param.titleWidth eq null ? '3' : param.titleWidth } pull-left">
				<a href="${pageContext.request.contextPath}/ActList">${param.title }<br>
					<small class="page-header-subtitle">${param.subTitle }</small>
				</a>
			</h1>
			<button onclick="location.href='${pageContext.request.contextPath}/CreateRewardItem'">Create Reward Item</button>
		</c:when>
		<c:when test="${param.type == 'search'? true:false}">
			<h1 class="col-md-3 col-sm-5 pull-left">
				<a href="${pageContext.request.contextPath}/Search">${param.title }
				</a>
			</h1>
			<form action="Search" id="searchForm">
				<div class="col-sm-5 col-sm-6 row">
					<div class="col-sm-3">
						<div class="input-group">
							<select class="form-control" name="searchIn">
								<option value="all" ${searchIn eq 'all' ? 'selected' : ''}>All</option>
								<option value="post" ${searchIn eq 'post' ? 'selected' : ''}>Post</option>
								<option value="activity"
									${searchIn eq 'activity' ? 'selected' : ''}>Activity</option>
							</select>
						</div>
					</div>
					<div class="col-sm-9">
						<div class="input-group">
							<input type="text" class="form-control" name="globalSearch"
								placeholder="Search" value="${keyWord }"> <span
								class="input-group-btn">
								<button class="btn btn-default" type="button"
									onclick="this.form.submit()">Go!</button>
							</span>
						</div>
					</div>
				</div>
			</form>
		</c:when>
		<c:otherwise>
			<h1
				class="col-sm-${param.titleWidth eq null ? '3' : param.titleWidth } pull-left">
				<a >${param.title }<br>
					<small class="page-header-subtitle">${param.subTitle }</small>
				</a>
			</h1>

		</c:otherwise>
	</c:choose>

</div>
<!-- end of page header -->