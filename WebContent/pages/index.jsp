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
<div id="header-carousel" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#header-carousel" data-slide-to="0" class="active"></li>
    <li data-target="#header-carousel" data-slide-to="1"></li>
    <li data-target="#header-carousel" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img src="../img/header.jpg" alt="...">
      <div class="carousel-caption">
      </div>
    </div>
    <div class="item">
      <img src="../img/header.jpg" alt="...">
      <div class="carousel-caption">
      </div>
    </div>
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#header-carousel" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#header-carousel" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>




<div class="container">
	<div class="section section-forum">
		<div class="col-md-3 section-intro">
			<img src="../img/sample.jpg" alt="sample" class="img-circle">
			<div class="section-intro-content">
			<h4>
				this is forum title
			</h4>
			<p>
				this is an intro paragraph
			</p>
			<button type="button" class="btn btn-primary">Explore</button>
			</div>
		</div>
		<div class="col-md-9 section-content">
			<div class="col-md-4 col-sm-4">t</div>
			<div class="col-md-4 col-sm-4">t</div>
			<div class="col-md-4 col-sm-4">t</div>
		</div>
	</div>
	
	<div class="section section-forum">
		<div class="col-md-3 section-intro">
			<img src="../img/sample.jpg" alt="sample" class="img-circle">
			<div class="section-intro-content">
			<h4>
				this is forum title
			</h4>
			<p>
				this is an intro paragraph
			</p>
			<button type="button" class="btn btn-primary">Explore</button>
			</div>
		</div>
		<div class="col-md-9 section-content">
			<div class="col-md-4 col-sm-4">t</div>
			<div class="col-md-4 col-sm-4">t</div>
			<div class="col-md-4 col-sm-4">t</div>
		</div>
	</div>
	
	<div class="section section-forum">
		<div class="col-md-3 section-intro">
			<img src="../img/sample.jpg" alt="sample" class="img-circle">
			<div class="section-intro-content">
			<h4>
				this is forum title
			</h4>
			<p>
				this is an intro paragraph
			</p>
			<button type="button" class="btn btn-primary">Explore</button>
			</div>
		</div>
		<div class="col-md-9 section-content">
			<div class="col-md-4 col-sm-4">t</div>
			<div class="col-md-4 col-sm-4">t</div>
			<div class="col-md-4 col-sm-4">t</div>
		</div>
	</div>
</div>

<%-- end of main container --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>

<script src='../js/jquery-3.2.1.js'></script>
<script src='../js/bootstrap.js'></script>
</body>
</html>
