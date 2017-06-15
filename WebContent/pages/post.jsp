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
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%-- end of header --%>
<div class="container">
	<div class="page-header">
	  	<h1 class="pull-left">Family Forum <br><small class="page-header-subtitle">Everything about life</small></h1>
		<div class="col-sm-5 input-group pull-left">
	     	<input type="text" class="form-control" placeholder="Search forum...">
	      	<span class="input-group-btn">
	        	<button class="btn btn-default" type="button">Go!</button>
	        </span>
        </div>
	</div>

	<div class="col-sm-9">
		<div class="post-orginal">
			<div class="text-center col-sm-2">
			<img alt="" src="../img/sample.jpg" class="img-circle profile-image-small">
			<p>user name</p>
			</div>
			<div class="panel panel-default col-sm-10 relative">
			  <div class="panel-body ">
			  	<h4>Post title</h4>
			  	<p>
			  	post content
			  	<br>
			  	${param.postId eq null ? param.postId : 'no post found'}
			  	</p>    
				
			  </div>
			  <div class="btn-toolbar stick-bottom" role="toolbar" aria-label="...">
				  <div class="btn-group" role="group" aria-label="...">
					<button type="button" class="btn btn-default btn-sm btn-no-border" onclick="">
						  <span class="glyphicon glyphicon-heart" aria-hidden="true"></span> 10
						</button>
						<button type="button" class="btn btn-default btn-sm btn-no-border" onclick="">
						  <span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span> 10
						</button>
						<button type="button" class="btn btn-default btn-sm btn-no-border">
							<span class="glyphicon glyphicon-comment" aria-hidden="true"></span> 100
						</button>
					</div>
				  <div class="btn-group dropdown"  >
				    <button id="post-controls-dropdown" type="button" class="btn btn-default btn-sm btn-no-border dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				      <span class="glyphicon glyphicon-option-horizontal" aria-hidden="true"></span>&nbsp
				      <span class="caret"></span>
				    </button>
				    <ul class="dropdown-menu" aria-labelledby="post-controls-dropdown">
				      <li><a href="#">Report post</a></li>
				      <li><a href="#">Report user</a></li>
				    </ul>
				  </div>
				</div>
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
