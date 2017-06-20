<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='../css/bootstrap.css' rel='stylesheet'>
<link href='../css/bootstrap.custom.css' rel='stylesheet'>
<link href='../css/master.css' rel='stylesheet'>
<link rel='icon' href='favicon.ico' type='image/x-icon' />
<title>post</title>
<%@ page import="java.util.ArrayList,bean.*,database.*" %>
<%! Forum forum = new Forum(); %>
<%! CommentDB comdb = new CommentDB(); %>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%-- end of header --%>
<div class="container">
	<jsp:include page="parts/forum-header.jsp"></jsp:include>
<% 
ArrayList<Post> list = forum.getPostById(request.getParameter("postId"));
if(list.size() != 0){
	Post p = list.get(0);
	%>
	<div class="col-sm-9">
		<div class="post post-orginal clearfix">
			<div class="text-center col-sm-2">
			<img alt="" src="../img/sample.jpg" class="img-circle profile-image-small">
			<p>user name</p>
			</div>
			<jsp:include page="parts/forum-post.jsp">
				<jsp:param value="<%= p.getAccountId() %>" name="accountId"/>
				<jsp:param value="<%= p.getPostTitle() %>" name="postTitle"/>
				<jsp:param value="<%= p.getPostContent() %>" name="postContent"/>
				<jsp:param value="<%= p.getDate() %>" name="postDate"/>
				<jsp:param value="<%= p.getLikeCount() %>" name="postLikes"/>
				<jsp:param value="<%= p.getDislikeCount() %>" name="postDislikes"/>
				<jsp:param value="<%= p.getCommentCount() %>" name="commentCount"/>
			</jsp:include>
		</div>
		<!-- end of original post -->
		<hr>
		<% 
		ArrayList<Comment> comList = comdb.getCommentByPostId(request.getParameter("postId"), 0, 5);
		for(Comment c:comList){
		%>
		<div class="post post-comment-group clearfix" id="post-comment-container">		
			<div class="post-comment">
				<div class="col-sm-2"></div>
				<jsp:include page="parts/forum-post.jsp">
					<jsp:param value="<%= c.getAccountId() %>" name="accountId"/>
					<jsp:param value="<%= c.getCommentContent() %>" name="postContent"/>
					<jsp:param value="<%= c.getDate() %>" name="postDate"/>
					<jsp:param value="<%= c.getLikeCount() %>" name="postLikes"/>
					<jsp:param value="<%= c.getDislikeCount() %>" name="postDislikes"/>
				</jsp:include>
				<div class="text-center col-sm-2">
					<img alt="" src="../img/sample.jpg" class="img-circle profile-image-small">
					<p>user name</p>
				</div>
			</div>
		</div>
		<br>
		<% } %>
	</div>
	<jsp:include page="parts/forum-sidebar.jsp"></jsp:include>
		
		<% 
	}else{
		%>
		<div class="panel panel-default">
			<div class="panel-body ">
				<h4>No Post Found</h4>
			</div>
		</div>
		<%
	}
	%>
</div>

<%-- end of main container --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>

</body>
</html>
