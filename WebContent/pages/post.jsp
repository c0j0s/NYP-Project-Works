<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix = "f" uri = "../WEB-INF/ffl.tld" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='${pageContext.request.contextPath}/css/bootstrap.css' rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/bootstrap.custom.css' rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/master.css'= rel='stylesheet'>
<link rel='icon' href='favicon.ico' type='image/x-icon' />
<title>${post.postTitle} | Post</title>
<%@ page import="java.util.ArrayList,bean.*,database.*"%>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%-- end of header --%>
	<div class="container">
		<jsp:include page="parts/page-header.jsp">
			<jsp:param value="forum" name="type" />
		</jsp:include>
		<c:choose>
			<c:when test="${post != null ? true : false}">
					<div class="col-md-9 col-sm-12" id="post-container">
						<div class="post post-orginal clearfix" id="post-${post.postId }">
							<div class="text-center col-sm-2">
								<jsp:include page="parts/forum-accountInfo.jsp">
									<jsp:param value="${post.accountId }" name="accountId"/>
									<jsp:param value="${post.hideId}" name="hideId"/>
									<jsp:param value="${post.accountName}" name="name"/>
									<jsp:param value="${post.accountImgUrl }" name="imgUrl"/>
								</jsp:include>
								<h3>
									<span class="label label-warning">
									<span class="glyphicon glyphicon-piggy-bank"></span>
									<span>${post.points }</span>
									</span>
								</h3>
							</div>
							<c:set var="post" scope="request" value="${post }" />
							<jsp:include page="parts/forum-post.jsp"></jsp:include>						
						</div>
						<!-- end of original post -->
						<hr>

						<div class="post post-comment-group clearfix"
							id="post-comment-container">
							<c:choose>
								<c:when test="${fn:length(commentList) gt 0 }">
									<c:forEach items="${commentList}" var="comment">
										<div class="post-comment post-comment-${comment.commentId } clearfix ${post.bestAnswer eq comment.commentId ? 'post-best-answer' : '' }"
											id="${comment.commentId}">
											<div class="col-sm-2" id="comment-best-answer">
												<c:if test="${user.accountId eq post.accountId}">
													<c:if test="${user != null ? true : false }">
														<c:if test="${post.bestAnswer == null ? true : false }">
															<button type="button" class="btn btn-success col-sm-12 post-best-answer-btn" data-postId="${post.postId }" data-commentId="${comment.commentId }">
															  <span class="glyphicon glyphicon-ok" aria-hidden="true"></span><br><hr>
															  <span>Best<br>Answer</span>
															</button>
														</c:if>
														<c:if test="${comment.commentId eq post.bestAnswer ? true : false }">
															<button type="button" class="btn btn-warning col-sm-12" id="post-best-answer-badge" disabled>
															  <span class="glyphicon glyphicon-ok" aria-hidden="true"></span><br><hr>
															  <span>Best<br>Answer</span>
															</button>
														</c:if>
													</c:if>
												</c:if>
											</div>
											<c:set var="comment" scope="request" value="${comment}" />
											<jsp:include page="parts/forum-comment.jsp"></jsp:include>
											<div class="text-center col-sm-2">
												<jsp:include page="parts/forum-accountInfo.jsp">
													<jsp:param value="${comment.accountId }" name="accountId"/>
													<jsp:param value="${comment.hideId}" name="hideId"/>
													<jsp:param value="${comment.accountName}" name="name"/>
													<jsp:param value="${comment.accountImgUrl }" name="imgUrl"/>
												</jsp:include>
											</div>
										</div>
										<br>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<div class="panel panel-default col-md-12">
										<div class="panel-body ">
											<h4>No replies yet.</h4>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
							<f:PostListPagination itemPerPage="5" pageCount="${post.commentCount }" currentPage="${page }" type="comment" postId="${post.postId}"/>
						</div>
					</div>
				</c:when>
			<c:otherwise>
				<div class="panel panel-default col-md-9">
					<div class="panel-body ">
						<h4>${message }</h4>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
		<jsp:include page="parts/forum-sidebar.jsp">
			<jsp:param value="forum" name="type" />
		</jsp:include>
	</div>

	<%-- end of main container --%>
	<jsp:include page="footer.jsp"></jsp:include>
	<%-- end of footer --%>

</body>
</html>
