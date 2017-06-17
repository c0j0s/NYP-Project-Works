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
<title>Template</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%-- end of header --%>

<div class="container">
	<jsp:include page="parts/forum-header.jsp"></jsp:include>
<% if(true){ //TODO check mode if is create post of edit post
	%>
		<div class="col-sm-9">
			<div class="post post-orginal clearfix">
				<div class="text-center col-sm-2">
					<img alt="" src="../img/sample.jpg" class="img-circle profile-image-small">
					<p>user name</p>
				</div>
				<div class="col-sm-8">
				<div class="panel panel-default">
					<div class="panel-body ">
						<form action="../createPost" method="post">
						 	<div class="form-group">
						   	 	<label for="postTitle">Title</label>
						  		<input type="text" class="form-control" name="postTitle" id="postTitle">
						 	</div>
						 	<div class="form-group">
						  		<label for="postTitle">Content</label>
						  		<textarea class="form-control" name="postContent" id="postContent" rows="5"></textarea>
						 	</div>
						 	<div class="row">
							  	<div class="form-group col-md-2">
								    <label for="postPoints">Points</label>
								    <input type="number" class="form-control" name="postPoints" id="postPoints" value="0" min="0" max="100">
							 	 </div>
							 	 <div class="form-group col-md-4">
								    <label for="postCategory">Category</label>
								    <select class="form-control" name="postCategory" id="postCategory">
									  <option value="1">1</option>
									  <option value="2">2</option>
									  <option value="3">3</option>
									  <option value="4">4</option>
									  <option value="5">5</option>
									</select>
							 	 </div>
							 	 <div class="form-group col-md-6">
								    <label for="postTags">Tags</label>
								    <input type="text" class="form-control" name="postTags" id="postTags" value="0" >
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
						<!-- end of form -->
						</div>
						</div>
					</div>
				</div>
			</div>
	<%
	}
%>
		<jsp:include page="parts/forum-sidebar.jsp"></jsp:include>
</div>

<%-- end of main container --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>

</body>
</html>
