<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>FamforLife</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%-- end of header --%>

<div id="header-carousel" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#header-carousel" data-slide-to="0" class="active"></li>
    <li data-target="#header-carousel" data-slide-to="1"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img src="${pageContext.request.contextPath}/img/header.jpg" alt="...">
      <div class="carousel-caption">
      </div>
    </div>
    <div class="item">
      <img src="${pageContext.request.contextPath}/img/header.jpg" alt="...">
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
<%-- end of carousel --%>

<div class="container">
	<div class="section section-forum">
		<hr>
		<h1 class='text-center'>Welcome to FamForLife
		</h1>
		<hr>
		<div class="row">
		  <div class="col-sm-6 col-md-4">
		    <div class="thumbnail">
		      <img src="${pageContext.request.contextPath}/img/sample.jpg" alt="sample" >
				<div class="caption">
		        <h3>Family Forum</h3>
		        <p>//desc</p>
		        <p><a href="Forum" class="btn btn-primary" role="button">Explore</a></p>
		      </div>
		    </div>
		  </div>
		  <div class="col-sm-6 col-md-4">
		    <div class="thumbnail">
		      <img src="${pageContext.request.contextPath}/img/sample.jpg" alt="sample" >
				<div class="caption">
		        <h3>ACtivities for families</h3>
		        <p>//desc</p>
		        <p><a href="ActList" class="btn btn-primary" role="button">Explore</a></p>
		      </div>
		    </div>
		  </div>
		  <div class="col-sm-6 col-md-4">
		    <div class="thumbnail">
		      <img src="${pageContext.request.contextPath}/img/sample.jpg" alt="sample" >
				<div class="caption">
		        <h3>Rewards for redemption</h3>
		        <p>//desc</p>
		        <p><a href="RedemptionList" class="btn btn-primary" role="button">Explore</a></p>
		      </div>
		    </div>
		  </div>
		</div>
	</div>
</div>
<%-- end of main container --%>

<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>

</body>
</html>
