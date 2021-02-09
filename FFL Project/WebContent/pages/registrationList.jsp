<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "../WEB-INF/ffl.tld" %>
<%@ page
	import="java.util.ArrayList,bean.*,java.text.DecimalFormat"%>
	<%!Activity actf;%>
<%!ArrayList<Activity> actfl;%>
<%!ArrayList<ActReg> arlist; %>
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
<title>Template</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%-- end of header --%>

<div class="container">
	<jsp:include page="parts/page-header.jsp"></jsp:include>
	<%
				DecimalFormat df = new DecimalFormat("##.00");
			%>
			<%
				actfl = (ArrayList<Activity>)request.getAttribute("activityRegistration");
				actf=actfl.get(0);
				arlist =(ArrayList<ActReg>)request.getAttribute("Registration");
			%>		<h2><%=actf.getActivityTitle() %></h2>
  <p>Organised By : <%=actf.getOrganiserId() %>, Activity Periods : <%=actf.getActivityStartDate()%>-<%=actf.getActivityEndDate() %></p><p> Activity Day : <%=actf.getActivityDay() %>, Activity Time : <%=actf.getActivityTime() %></p>            
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>No.</th>
        <th>Registration Id</th>
        <th>User Id</th>
        <th>Name</th>
        <th>Cash Or Online Payment</th>
      </tr>
    </thead><%int i =0; %>
    <tbody><% 	for(ActReg ar : arlist){ i++;
			%>
	
      <tr>
        <td><%=i %></td>
        <td><%=ar.getRegistrationId() %></td>
        <td><%=ar.getAccountId() %></td>
         <td><%=ar.getParticipantName() %></td>
        <td><%=ar.getCashOrBank() %></td>
       
        
      </tr>
      
<%} %></tbody></table>
</div>

<%-- end of main container --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>

</body>
</html>
