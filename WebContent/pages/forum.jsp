<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "../WEB-INF/ffl.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='${pageContext.request.contextPath}/css/bootstrap.css' rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/bootstrap.custom.css' rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/master.css' rel='stylesheet'>
<link rel='icon' href='favicon.ico' type='image/x-icon' />
<title>Family Forum</title>
<%	if(request.getParameter("category") == null) {%>
<jsp:forward page="forum.jsp?category=1&page=1"></jsp:forward>
<%}%>
<%@ page import="java.util.ArrayList,bean.*,database.*" %>
<%! ForumDB forumDB = new ForumDB(); %>
<%! Forum forum = forumDB.getPostAdvance(0,10);%>

</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%-- end of header --%>
<div class="container">
	<jsp:include page="parts/forum-header.jsp"></jsp:include>
	
	<div class="col-md-9 Forum-main">
		<div class="panel panel-default Forum-main-trending">
		  <div class="panel-heading">
		    <h3 class="panel-title">Trending Topics</h3>
		  </div>
		  <div class="panel-body">
		    <% 
		    //ArrayList<Post> postList = (ArrayList<Post>) session.getAttribute("ForumList");
		    for(int i = 0; i<3; i++){
		    	%>
		    	<div class="col-md-4 ">
					<div class="panel panel-default Forum-card Forum-trending-card">
					  <div class="panel-body text-center">
					    <img alt="profile image" src="../img/sample.jpg" class="img-circle profile-image-medium">
					    <p>this will be the title area</p>
					    <div class="Forum-post-control-grps">
					    	<button type="button" class="btn btn-default btn-sm btn-no-border" onclick="">
							  <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> 10
							</button>
							<button type="button" class="btn btn-default btn-sm btn-no-border" onclick="">
							  <span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span> 10
							</button>
							<button type="button" class="btn btn-default btn-sm btn-no-border">
								<span class="glyphicon glyphicon-comment" aria-hidden="true"></span> 100
							</button>
					    </div>
					    <button type="button" onclick="location.href='post.jsp?postId=000000'" class="btn btn-primary">Participate</button>
					  </div>
					</div>
				</div>
		    	<%
		    } %>
		  </div>
		</div>
		<!-- end of trending post panel -->
		<div class="Forum-main-posts">
		  <ul class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="${param.category eq '1' ? ' active' : ''}"><a href="#a" aria-controls="a" role="tab" data-toggle="tab">Category</a></li>
		    <li role="presentation" class="${param.category eq '2' ? ' active' : ''}"><a href="#b" aria-controls="b" role="tab" data-toggle="tab">Category</a></li>
		    <li role="presentation" class="${param.category eq '3' ? ' active' : ''}"><a href="#c" aria-controls="c" role="tab" data-toggle="tab">Category</a></li>
		    <li role="presentation" class="${param.category eq '4' ? ' active' : ''}"><a href="#d" aria-controls="d" role="tab" data-toggle="tab">Category</a></li>
		  </ul>
		
		  <div class="tab-content">
		    <div role="tabpanel" class="tab-pane panel-body ${param.category eq '1' ? ' active' : ''}" id="a">
		    	<f:getPostList start="${param.page eq 1 ? param.page - 1 : param.page*10 - 10}">
		    		<jsp:include page='parts/forum-postItem.jsp'>
		    			<jsp:param value="FFL:postId" name="postId"/>
						<jsp:param value="FFL:accountId" name="accountId"/>
						<jsp:param value="FFL:postTitle" name="postTitle"/>
						<jsp:param value="FFL:date" name="date"/>
						<jsp:param value="FFL:likeCount" name="likeCount"/>
						<jsp:param value="FFL:dislikeCount" name="dislikeCount"/>
						<jsp:param value="FFL:commentCount" name="commentCount"/>
		    		</jsp:include>
		    	</f:getPostList>

		    	<nav aria-label="Page navigation">
				  <ul class="pagination pagination-lg">
				  	<% if(!request.getParameter("page").equals("1")){				    	
				    	%>
					    <li>
					      <a href="?category=1&page=${param.page - 1}" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
				    	<%
				    }
				  	int count = forum.getPageCount();
				    for(int i = 1; i<=count; i++){ // TODO change pagination loop maximum
				    	%>
				    	<li><a href="?category=1&page=<%=i%>"><%=i%></a></li>
				    	<% 
				    }
				     
				    if(!request.getParameter("page").equals("5")){ // TODO hide if maximum page reach 
				    	%>
					    <li>
					      <a href="?category=1&page=${param.page + 1}" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
				    	<%
				    } %>
				  </ul>
				</nav>
		    </div>
		    <div role="tabpanel" class="tab-pane panel-body ${param.category eq '2' ? ' active' : ''}" id="b">2...</div>
		    <div role="tabpanel" class="tab-pane panel-body ${param.category eq '3' ? ' active' : ''}" id="c">3...</div>
		    <div role="tabpanel" class="tab-pane panel-body ${param.category eq '4' ? ' active' : ''}" id="d">4...</div>
		  </div>
		</div>
	</div>
	<jsp:include page="parts/forum-sidebar.jsp"></jsp:include>
</div>

<%-- end of main container --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>

</body>
</html>
