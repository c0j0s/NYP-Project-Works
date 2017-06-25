<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="f" uri="../WEB-INF/ffl.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>post</title>
<%@ page import="java.util.ArrayList,bean.*,database.*"%>
<%!ForumDB forumdb = new ForumDB();%>
<%!CommentDB comdb = new CommentDB();%>
<% request.setAttribute("postList", forumdb.getPostById(request.getParameter("postId")));%>
<% request.setAttribute("commentList", comdb.getCommentByPostId(request.getParameter("postId"), 0, 5));%>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%-- end of header --%>
	<div class="container">
		<jsp:include page="parts/page-header.jsp">
			<jsp:param value="forum" name="type" />
		</jsp:include>
		<c:choose>
			<c:when test="${fn:length(postList) > 0 ? true : false}">
				<c:forEach items="${postList}" var="post">
					<div class="col-sm-9">
						<div class="post post-orginal clearfix">
							<div class="text-center col-sm-2">
								<img alt="" src="../img/sample.jpg"
									class="img-circle profile-image-small">
								<p>user name</p>
							</div>
							<jsp:include page="parts/forum-post.jsp">
								<jsp:param value="${post.postId }" name="postId" />
								<jsp:param value="${post.accountId }" name="accountId" />
								<jsp:param value="${post.postTitle }" name="postTitle" />
								<jsp:param value="${post.postContent }" name="postContent" />
								<jsp:param value="${post.date }" name="postDate" />
								<jsp:param value="${post.likeCount }" name="likeCount" />
								<jsp:param value="${post.dislikeCount }" name="dislikeCount" />
								<jsp:param value="${post.commentCount }" name="commentCount" />
							</jsp:include>
						</div>
						<!-- end of original post -->
						<hr>

						<div class="post post-comment-group clearfix"
							id="post-comment-container">
							<c:choose>
								<c:when test="${fn:length(commentList) gt 0 }">
									<c:forEach items="${commentList }" var="comment">
										<div class="post-comment  clearfix "
											id="${comment.commentId }">
											<div class="col-sm-2"></div>
											<jsp:include page="parts/forum-comment.jsp">
												<jsp:param value="${comment.commentId }" name="commentId" />
												<jsp:param value="${comment.accountId }" name="accountId" />
												<jsp:param value="${comment.commentContent }" name="postContent" />
												<jsp:param value="${comment.date }" name="postDate" />
												<jsp:param value="${comment.likeCount }" name="likeCount" />
												<jsp:param value="${comment.dislikeCount }" name="dislikeCount" />
												<jsp:param value="${comment.commentCount }" name="commentCount" />
											</jsp:include>
											<div class="text-center col-sm-2">
												<img alt="" src="../img/sample.jpg"
													class="img-circle profile-image-small">
												<p>user name</p>
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
						</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div class="panel panel-default col-md-9">
					<div class="panel-body ">
						<h4>No Post Found</h4>
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
