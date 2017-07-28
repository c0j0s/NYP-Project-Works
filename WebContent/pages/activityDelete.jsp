<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "../WEB-INF/ffl.tld" %>
<%@ page
	import="java.util.ArrayList,bean.*,java.text.DecimalFormat"%>

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
	<form action="${pageContext.request.contextPath}/DeleteActivity"
				method="post" class="col-md-12" ><%int i =0; %>
<% ArrayList<Activity> actList = (ArrayList<Activity>)request.getAttribute("activityDelete");
				for (Activity act : actList) {%> 
				<div class="col-md-3"><%=act.getActivityId() %></div>
				<div class="col-md-3"><%=act.getActivityTitle() %></div>
				<div class="col-md-3"><%=act.getOrganiserId()%></div>
				<div class="col-md-3">
				<button type="submit" value="Delete" onclick="form.action='${pageContext.request.contextPath}/DeleteActivity?actId=<%=act.getActivityId()%>'">Delete Activity</button>
	
				</div>
			
<%i++;} %>
</form>
</div>

<%-- end of main container --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>

</body>
</html>
