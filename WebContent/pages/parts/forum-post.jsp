<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "../../WEB-INF/ffl.tld" %>
<div class="col-sm-8">
	<div class="panel panel-default">
		<small class="pull-right post-date">${post.date}</small>
		<div class="panel-body ">
			<div class="post-text-content">
				<h4>${post.postTitle}</h4>
				<p>${post.postContent}</p>
			</div>
			<hr>
			<div class="post-button-group btn-toolbar clearfix" role="toolbar" aria-label="...">
				<div class="btn-group" role="group" aria-label="...">
					<jsp:include page="likeButtons.jsp">
						<jsp:param value="${post.postId }" name="Id"/>
						<jsp:param value="postId" name="colName"/>
						<jsp:param value="${post.likeCount }" name="likeCount"/>
						<jsp:param value="${post.dislikeCount }" name="dislikeCount"/>
					</jsp:include>
					<button type="button" class="btn btn-default btn-sm btn-no-border">
						<span class="glyphicon glyphicon-flag" aria-hidden="true"></span>
						Follow
					</button>
				</div>
				<div class="btn-group pull-right dropdown">
					<button id="post-controls-dropdown" type="button"
						class="btn btn-default btn-sm btn-no-border dropdown-toggle pull-right"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<span class="glyphicon glyphicon-option-horizontal"
							aria-hidden="true"></span>&nbsp <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" aria-labelledby="post-controls-dropdown">
						<li><a href="#">Report post</a></li>
						<li><a href="#">Report user</a></li>
					</ul>
					<button type="button" class="btn btn-default btn-sm btn-no-border">
						<span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
						<span class="meta-value-count" data-count="${post.commentCount}">${post.commentCount}</span>
					</button>
				</div>
				<br>
				<br>
				<c:choose>
					<c:when test="${user eq null ? 'false' : 'true' }">
						<c:choose>
							<c:when test="${user.accountId eq post.accountId ? false : true}">
								<button type="button" id="main-createComment" class="btn btn-success btn-block addCom" onclick="createCom('post-comment-container','before','post')">Give my answer</button> 
							</c:when>
							<c:otherwise>
							<p>
								<button type="button" class="btn btn-success col-sm-6" onclick="location.href='forum-edit.jsp?type=post&mode=edit&postId=${post.postId}'">Edit</button> 
								<button type="button" class="btn btn-danger col-sm-6" id="delete-post">Delete</button>
							</p>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-success btn-block" onclick="location.href='login.jsp'">Please Login to Reply</button> 
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>
