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
<title>Template</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%-- end of header --%>

<div class="container">
	<jsp:include page="parts/page-header.jsp"></jsp:include>
	<form action ="${pageContext.request.contextPath}/test" method="post">
	<input type="hidden" value="30" name="add">
	<input type="hidden" value="ACC0000000" name="id">
	<input type="submit" value="click"></form>
	
	<div>
	<h1>Example for form with image to upload [firebase method, update 2]</h1>
	<c:choose>
		<c:when test="${requestScope.imgurl != null ? true : false }">
			<img alt="" src="${requestScope.imgurl}" id="test-img-done">
		</c:when>
		<c:otherwise>
		
			<!-- 			for previewing -->
			<img alt="" src="" id="test-img-prev">
		</c:otherwise>
	</c:choose>
	
    <form method="post" action="../test" id="form-upload">
        Select file to upload: 
        <input type="file" name="file" size="60"/>
        <input type="hidden" name="imgurl" id="imgurl" data-imgfolder="user/ACC0000000"/>
        <input type="submit" value="sendform">
    </form>
    <h3>steps 1: configuring your form</h3>
    <p>
    	1. add id = "form-upload" in your form <br>
    	2. obviously you will need a file input
    	<textarea><input type="file" name="file" size="60"/></textarea>
    	3. add a hidden input with the following parameter, change the value of <strong>data-imgfolder</strong> to your own file path
    	<textarea><input type="hidden" name="imgurl" id="imgurl" data-imgfolder="user/ACC0000000"/></textarea><br>
    </p>
    <h3>steps 2: configuring your servlet</h3>
    <p>
    	4. this will retrieve the img url return from hidden input
    	<textarea>String imgurl = request.getParameter("imgurl");</textarea>
    	5. store into bean which can be written to database
    </p>
    <h3>*examples can be found in test.jsp and servlet/test.java</h3>
	<hr>
	</div>

</div>

<%-- end of main container --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>

</body>
</html>
