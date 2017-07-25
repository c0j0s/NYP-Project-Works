<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "../WEB-INF/ffl.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%-- end of header --%>
<div class="container">
	<jsp:include page="parts/page-header.jsp">
		<jsp:param value="forum" name="type"/>
	</jsp:include>
	
	<div class="col-md-9 col-sm-12 Forum-main">
		<div class="panel panel-default forum-main-trending">
		  <div class="panel-heading">
		    <h3 class="panel-title">Trending Topics</h3>
		  </div>
		  <div class="panel-body">
		  <jsp:include page="parts/forum-trendingPost.jsp"></jsp:include>
		  </div>
		</div>
		
		<!-- end of trending post panel -->
		
		<div class="Forum-main-posts">
		  <ul class="nav nav-tabs" role="tablist">
		  	<c:forEach items="general,parenting" var="listItem">
		  	<li role="presentation" class="${category eq listItem ? ' active' : ''}"><a class="tab-title" href="Forum?category=${listItem}&page=1" >${listItem}</a></li>
		  	</c:forEach>
		  </ul>
		
		  <div class="tab-content">
		  	<c:forEach items="general,parenting" var="postCatTab">
		    <div role="tabpanel" class="tab-pane panel-body ${category eq postCatTab ? ' active' : ''}" id="a">
		    	<c:forEach items="${postList}" var="post" begin="0" end="9" varStatus="loop">
		    		<c:set var="post" value="${post}" scope="request"/>
		    		<jsp:include page='parts/forum-postItem.jsp'>
		    			<jsp:param value="${post}" name="post"/>
		    		</jsp:include>
		    	</c:forEach>
		    	<f:PostListPagination pageCount="${postCount }" currentPage="${page }" category="${postCatTab }" itemPerPage="10" type="post"/>
		    </div>
		    </c:forEach>
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
