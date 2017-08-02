<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="../../WEB-INF/ffl.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="col-sm-8 post-comment-content">
	<div class="panel panel-default">
		<small class="pull-right post-date">${comment.date}</small>
		<div class="panel-body ">
			<div class="post-text-content">
				<p>${comment.commentContent}</p>
			</div>
			<hr>
			<div class="post-button-group btn-toolbar clearfix" role="toolbar" aria-label="...">
				<div class="btn-group" role="group" aria-label="...">
					<jsp:include page="likeButtons.jsp">
						<jsp:param value="${comment.commentId }" name="Id"/>
						<jsp:param value="${comment.likeCount }" name="likeCount"/>
						<jsp:param value="${comment.dislikeCount }" name="dislikeCount"/>
					</jsp:include>
					<c:if test="${user.accountId eq comment.accountId }">
						<button type="button" class="btn btn-default btn-sm btn-no-border comment-delete" data-id="${comment.commentId }">
							<span class="glyphicon glyphicon-comment" aria-hidden="true"> </span>
							<span>Delete My Answer</span>
						</button>
					</c:if>
				</div>
				<div class="btn-group pull-right dropdown">
					<jsp:include page="reportList.jsp">
						<jsp:param value="${comment.commentId }" name="itemId"/>
						<jsp:param value="${comment.accountId }" name="accountId"/>
						<jsp:param value="comment" name="type"/>
					</jsp:include>
					<button type="button" class="btn btn-default btn-sm btn-no-border">
						<span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
						<span class="meta-value-count" data-count="${comment.commentCount}">${comment.commentCount}</span>
					</button>
				</div>
			</div>
			<div class="comments-comment" data-commentid="${comment.commentId }">
				<img src="${pageContext.request.contextPath}/img/loading.gif" class="profile-image-small">
			</div>
			<c:choose>
				<c:when test="${user eq null ? false : true }">
					<br>
					<div class="col-md-4 pull-right">
					<button type="button" id="createComment-${comment.commentCount }" class="btn btn-success btn-block addCom " onclick="createCom('${comment.commentId }','after','comment')">Reply</button> 
					</div>
				</c:when>
			</c:choose>
		</div>
	</div>
</div>