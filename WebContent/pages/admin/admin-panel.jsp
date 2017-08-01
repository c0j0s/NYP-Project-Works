<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "../../WEB-INF/ffl.tld" %>
<!DOCTYPE html>
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
<title>Admin Panel</title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<%-- end of header --%>

<div class="container">
	<c:if test="${user eq null }">
		<jsp:forward page="../Index"></jsp:forward>
	</c:if>
	<jsp:include page="../parts/page-header.jsp">
		<jsp:param value="Admin Panel" name="title"/>
	</jsp:include>
	<!-- 	header -->
	<div class="col-md-3 col-xs-12">
		<jsp:include page="admin-sidebar.jsp">
			<jsp:param value="admin" name="type"/>
		</jsp:include>
	</div>
	<!-- 	sidebar -->
	<div class="col-md-8 col-sm-12">
		  <ul class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="${tab eq 'Forum' ? 'active' : '' }"><a href="#Forum" aria-controls="Forum" role="tab" data-toggle="tab">Forum Management</a></li>
		    <li role="presentation" class="${tab eq 'Activity' ? 'active' : '' } admin-activity"><a href="#Activity" aria-controls="Activity" role="tab" data-toggle="tab">Activity Management</a></li>
		    <li role="presentation" class="${tab eq 'Account' ? 'active' : '' }"><a href="#Account" aria-controls="Account" role="tab" data-toggle="tab">Account Management</a></li>
		    <li role="presentation" class="${tab eq 'Others' ? 'active' : '' }"><a href="#Others" aria-controls="Others" role="tab" data-toggle="tab">Others</a></li>
		  </ul>
		
		  <div class="tab-content" style="min-height:50vh">
		    <div role="tabpanel" class="tab-pane ${tab eq 'Forum' ? 'active' : '' }" id="Forum">
		    	<jsp:include page="admin-forum.jsp"></jsp:include>
		    </div>
		    <div role="tabpanel" class="tab-pane ${tab eq 'Activity' ? 'active' : '' }"" id="Activity">
		    	<jsp:include page="admin-activity.jsp"></jsp:include>
		    </div>
		    <div role="tabpanel" class="tab-pane ${tab eq 'Account' ? 'active' : '' }" id="Account">
		    	<jsp:include page="admin-account.jsp"></jsp:include>
		    </div>
		    <div role="tabpanel" class="tab-pane ${tab eq 'Others' ? 'active' : '' }" id="Others">
		    	<jsp:include page="admin-others.jsp"></jsp:include>
		    </div>
		  </div>
	</div>
</div>

<%-- end of main container --%>
<jsp:include page="../footer.jsp"></jsp:include>
<%-- end of footer --%>
<script src="${pageContext.request.contextPath}/pages/admin/admin.js"></script>
</body>
</html>
