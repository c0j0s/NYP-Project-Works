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
<h2>Create New Family Group</h2>
<form action="${pageContext.request.contextPath}/CreateFamilyGrp" method="post" >
	<div class="form-group">Family Name:<input type="text" class="form-control" name="famName"></div>
	<div class="form-group">
		<label for="profilepic">Choose your profile picture:</label>
		<input type="file" name="file" size="60"/>
        <input type="hidden" name="imgurl" id="imgurl" data-imgfolder="user/ac.getAccountId"/>
        <img alt="" src="" id="test-img-prev">
	</div>
	<div class="form-group" id="adduser">Add users:<input type="text" class="form-control" name="users"></div>
	<button id="addMember">Add More</button>
	<button type="submit" class="btn btn-default" value="sendform">Submit</button>
</form>
</div>

<%-- end of main container --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>

</body>
</html>