<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%@ page
	import="java.util.ArrayList,bean.*,database.*,java.text.DecimalFormat"%>

<meta charset="utf-8">
<%!ActivityDB actdb = new ActivityDB();%>
<%!Activity actf;%>
<%!ArrayList<Activity> actfl;%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='${pageContext.request.contextPath}/css/bootstrap.css'
	rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/bootstrap.custom.css'
	rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/master.css'
	rel='stylesheet'>
<link rel='icon' href='favicon.ico' type='image/x-icon' />
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container">	<jsp:include page="parts/page-header.jsp">
			<jsp:param value="activity" name="type" />
			<jsp:param value="Activities for families" name="title" />
			<jsp:param value="5" name="titleWidth" />
			<jsp:param value="subTitle" name="subTitle" />
		</jsp:include>
		<div class="col-md-1 pull-left"></div>
		<div class=col-md-8>
			<%
				DecimalFormat df = new DecimalFormat("##.00");
			%>

			<%
				if (request.getParameter("actId") == null) {
			%>
			<script language="javascript">
				window.location.href = "activitypageerror.jsp"
			</script>

			<%
				} else {
					actfl = actdb.getActivityById(request.getParameter("actId"));

					actf = actfl.get(0);
			%>
		



			<h3>
				<%=actf.getActivityTitle()%></h3>
				<img src="<%=actf.getImgUrl()%>" id="factpic">
			<p>
				Activity Period :
				<%=actf.getActivityStartDate()%>
				to
				<%=actf.getActivityEndDate()%></p>
			<p>
				Days Of Activity :
				<%=actf.getActivityDay()%></p>
			<p>
				Activity Time :
				<%=actf.getActivityTime()%></p>
			<p>
				Activity Location :
				<%=actf.getActivityLocation()%></p>
			<p>
				Activity Fee : $
				<%=df.format(actf.getActivityFee())%></p>

			<p>
				<input type="text">
			</p>
		</div>
		<%
			}
		%>
	
	<div class="col-md-3">
		<ul class="list-group pull-right">
			<h4>Activity Popularity Ranking</h4>
			<%
				for (int z = 0; z < 20; z++) {
			%>
			<li class="list-group-item"><%=z + 1%>. Java <span class="badge"><%=z%></span></li>
			<%
				}
			%>
		</ul>
	</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>