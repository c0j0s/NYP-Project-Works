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
<link href='../css/bootstrap.css' rel='stylesheet'>
<link href='../css/bootstrap.custom.css' rel='stylesheet'>
<link href='../css/master.css' rel='stylesheet'>
<link rel='icon' href='favicon.ico' type='image/x-icon' />
<title>Posting a Question</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%-- end of header --%>

<div class="container">
	<jsp:include page="parts/page-header.jsp">
		<jsp:param value="forum" name="type"/>
	</jsp:include>
			<div class="col-sm-9">
			<div class="post post-orginal clearfix">
				<div class="text-center col-sm-2">
					<img alt="" src="../img/sample.jpg" class="img-circle profile-image-small">
					<p>${user.givenName}</p>
				</div>
				<div class="col-sm-8">
				<div class="panel panel-default">
					<div class="panel-body ">	
					<c:choose>
						<c:when test="${param.mode eq 'create' ? true : false }">
							<form action="${pageContext.request.contextPath}/CreatePost" method="post">
							 	<div class="form-group">
							   	 	<label for="postTitle">Question Title</label>
							  		<input type="text" class="form-control" name="postTitle" id="postTitle" required>
							 	</div>
							 	<div class="form-group">
							  		<label for="postContent">Detail descriptions</label>
							  		<textarea class="form-control" name="postContent" id="postContent" rows="5" required></textarea>
							 	</div>
							 	<div class="row">
								  	<div class="form-group col-md-2">
									    <label for="postPoints">Points</label>
									    <input type="number" class="form-control" name="postPoints" id="postPoints" value="0" min="0" max="100">
								 	 </div>
								 	 <div class="form-group col-md-4">
									    <label for="postCategory">Category</label>
									    <select class="form-control" name="postCategory" id="postCategory">
									    	<c:forEach items="${f:getCategoryList() }" var="cat" >
									    		<option value="${cat.key}">${cat.value}</option>
									    	</c:forEach>
										</select>
								 	 </div>
								 	 <div class="form-group col-md-6">
									    <label for="postTags">Tags <small>separate with a comma</small></label>
									    <input type="text" class="form-control" name="postTags" id="postTags" placeholder="family,communication" >
								 	 </div>
							 	</div>
								<div class="checkbox">
								  <label>
								    <input type="checkbox" name="hideId" value="Y">
								    Anonymous
								  </label>
								</div>
							<br>
							<button type="submit" class="btn btn-success btn-block">Post</button> 
							</form>
							<!-- end of create form -->
						</c:when>
						<c:when test="${param.mode eq 'edit' ? true : false }">
							<c:set var="postList" scope="request" value="${f:getPostById(param.postId) }"/>
							<c:forEach items="${postList }" var="post">
							<form action="${pageContext.request.contextPath}/EditPost" method="post">
							 	<div class="form-group">
							   	 	<label for="postTitle">Question Title</label>
							  		<input type="text" class="form-control" name="postTitle" id="postTitle" required value="${post.postTitle }">
							 	</div>
							 	<div class="form-group">
							  		<label for="postContent">Detail descriptions</label>
							  		<textarea class="form-control" name="postContent" id="postContent" rows="5" required>${post.postContent }</textarea>
							 	</div>
							 	<div class="row">
								  	<div class="form-group col-md-2">
									    <label for="postPoints">Points</label>
									    <input type="number" class="form-control" name="postPoints" id="postPoints" value="${post.points }" min="0" max="100">
								 	 </div>
								 	 <div class="form-group col-md-4">
									    <label for="postCategory">Category</label>
									    <select class="form-control" name="postCategory" id="postCategory">
									    	<c:forEach items="${f:getCategoryList() }" var="cat" >
									    		<c:choose>
									    			<c:when test="${cat.value eq post.postCategory ? true : false }">
									    				<option value="${cat.key}" selected>${cat.value}</option>
									    			</c:when>
									    			<c:otherwise>
									    				<option value="${cat.key}">${cat.value}</option>
									    			</c:otherwise>
									    		</c:choose>
									    	</c:forEach>
										</select>
								 	 </div>
								 	 <div class="form-group col-md-6">
									    <label for="postTags">Tags <small>separate with a comma</small></label>
									    <input type="text" class="form-control" name="postTags" id="postTags" placeholder="family,communication" value="${post.tagList }">
								 	 </div>
							 	</div>
								<div class="checkbox">
								  <label>
								    <input type="checkbox" name="hideId" value="Y">
								    Anonymous
								  </label>
								</div>
							<br>
							<button type="submit" class="btn btn-success btn-block">Post</button> 
							</form>
							<!-- end of edit form -->
							</c:forEach>
						</c:when>
						</c:choose>
						</div>
						</div>
					</div>
				</div>
			</div>
		<jsp:include page="parts/forum-sidebar.jsp">
			<jsp:param value="forum" name="type"/>
		</jsp:include>
</div>

<%-- end of main container --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>

</body>
</html>
