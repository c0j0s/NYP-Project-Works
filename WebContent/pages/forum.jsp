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
	<div class="col-lg-12 forum-header">
		<img class="col-sm-3" alt="" src="">
		<div class="col-sm-5 input-group">
	     	<input type="text" class="form-control" placeholder="Search for...">
	      	<span class="input-group-btn">
	        	<button class="btn btn-default" type="button">Go!</button>
	        </span>
        </div>
	</div>
	<div class="col-lg-9 forum-main">
		<div class="panel panel-default forum-main-trending">
		  <div class="panel-heading">
		    <h3 class="panel-title">Trending Topics</h3>
		  </div>
		  <div class="panel-body">
		    Panel content
		  </div>
		</div>
		<div class="forum-main-posts">
			<ul class="nav nav-tabs">
			  <li role="presentation" class="active"><a href="#">Category 1</a></li>
			  <li role="presentation"><a href="#">Category 2</a></li>
			  <li role="presentation"><a href="#">Category 3</a></li>
			</ul>
			
			<div class="forum-main-posts-category panel-body">
				latest post
			</div>
		</div>
	</div>
	<div class="col-lg-3 forum-sidebar">
		<div class="panel panel-primary forum-sidebar-account">
		  <div class="panel-heading ">
		  	<img alt="../img/sample.jpg" src="../img/sample.jpg" class="img-circle profile-image-medium">
		    <h3 class="panel-title text-center">User Name</h3>
		  </div>
		  <div class="panel-body">
		    body
		  </div>
		</div>
	</div>
</div>

<%-- end of main container --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>

</body>
</html>
