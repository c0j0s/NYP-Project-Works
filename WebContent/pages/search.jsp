<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "../WEB-INF/ffl.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<title>Search</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%-- end of header --%>

<div class="container">
	<jsp:include page="parts/page-header.jsp">
		<jsp:param value="search" name="type"/>
		<jsp:param value="Search FFL" name="title"/>
	</jsp:include>
	<!-- end of page header -->
	<div>
		<c:choose>
			<c:when test="${resultList eq null ? false : true}">
				<div>
					<h4>
					<span class="label label-info">Keyword: " ${keyWord} "</span>
					<span class="label label-info">Search in: " ${searchIn} "</span>
					<span class="label label-info">Results Found:  ${fn:length(resultList)} </span>
					</h4>
				</div>
				<hr>
				<c:choose>
					<c:when test="${fn:length(resultList) gt 0}">
						<div class="list-group">
							<c:forEach items="${resultList}" var="result">
								  <a href="${pageContext.request.contextPath}/${result.url}" class="list-group-item  auto-overflow">
								    <div class="col-md-1 col-sm-2">
								    	<span class="glyphicon glyphicon-${result.type eq 'post' ? 'comment' : 'certificate' } btn-lg" aria-hidden="true"></span>
								    </div>
								    <div class="col-md-8 col-sm-6">
								   		<h4 class="list-group-item-heading">${result.title }</h4>
								    	<p class="list-group-item-text">${result.content }</p>
								    </div>
								  </a>
							</c:forEach>
						</div>
					</c:when>
					<c:otherwise>
						<div class="list-group">
						  <a class="list-group-item">
						    <h4 class="list-group-item-heading">No Results for keyword 
						    	<span class="alert-warning">" ${keyWord} "</span>
						    	<c:if test="${searchIn ne null ? true : false }">
						    		in
						    		<span class="alert-warning">" ${searchIn} "</span>
						    	</c:if>
						    </h4>
						  </a>
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
			
			</c:otherwise>
		</c:choose>
	</div>
</div>

	<%-- end of main container --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>

</body>
</html>
