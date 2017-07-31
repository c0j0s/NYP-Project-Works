<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-3 col-sm-12 forum-sidebar">
	<div class="sticky-sidebar">
	<div class="col-md-12 col-sm-4">
		<jsp:include page="sidebar-account.jsp">
			<jsp:param value="Forum" name="url"/>
		</jsp:include>
	</div>
	<div class="col-md-12 col-sm-4">
		<div class="panel panel-info">
			<div class="panel-heading ">
				<h3 class="panel-title">Top Answerer</h3>
			</div>
			<div class="panel-body">
				<ul class='list-group'>
				<c:forEach items="${topAnswerer }" var='answerer'>
					<h4>
					<img alt="Top Answerer profile image" src="${answerer.imgUrl }" class='img-circle profile-image-xsmall'/>
					${answerer.givenName }
					<span class="label label-danger label-sm pull-right">
					<span class="glyphicon glyphicon-fire" aria-hidden="true"></span>
					${answerer.postsCounts }
					</span>
					</h4>
				</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	</div>
</div>