<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

<%@ page
	import="java.util.ArrayList,bean.*,database.*,java.text.DecimalFormat"%>
<%!ActivityDB actdb = new ActivityDB();%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='../css/bootstrap.css' rel='stylesheet'>
<link href='../css/bootstrap.custom.css' rel='stylesheet'>
<link href='../css/master.css' rel='stylesheet'>
<link rel='icon' href='favicon.ico' type='image/x-icon' />
<title>Activities for families</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%-- end of header --%>

	<div class="container">
		<jsp:include page="parts/page-header.jsp">
			<jsp:param value="Activities for families" name="title" />
			<jsp:param value="5" name="titleWidth" />
		</jsp:include>
		<br>
		<div class="col-md-9">
			<%
				DecimalFormat df = new DecimalFormat("##.00");
			%>
			<%
				ArrayList<Activity> actList = actdb.getActivity(null);
				for (Activity act : actList) {
			%>
			<div class="clearfix">
				<div class="col-md-4">
					<img class="activitypic" src="../img/sample.jpg" />
				</div>
				<div class="col-md-8">
					<h4><%=act.getActivityTitle()%></h4>
					<p>
						Location :
						<%=act.getActivityLocation()%></p>
					<p>
						Organiser :
						<%=act.getOrganiserId()%></p>
					<p>
						Slots Remaining :
						<%=act.getParticipantNo()%></p>
					<p>
						Fee : $
						<%=act.getActivityFee()%></p>
					<p>
						Date :
						<%=act.getActivityStartDate()%>
						to
						<%=act.getActivityEndDate()%></p>
					<P>
						Day :
						<%=act.getActivityId()%>
					<p>
						Timing :
						<%--! <%=act.getActivityTime()%> --%>
					</p>
					<p><div class="forum-post-control-grps">
					    	<button type="button"
							class="btn btn-default btn-sm btn-no-border" onclick="">
							<span class="glyphicon glyphicon-thumbs-up"></span>
						</button> <%=act.getActivityLikes()%>	<button type="button"
							class="btn btn-default btn-sm btn-no-border" onclick="">
							<span class="glyphicon glyphicon-thumbs-down"></span>
						</button> <%=act.getActivityDislikes()%><button type="button"
							class="btn btn-default btn-sm btn-no-border">
								<span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
						</button> <%="69"%>
							</div>
					</p> 
		</div>
		
	</div>
	 <br></br>
	<%
		}
	%>
		</div>
	<div class="col-md-3">
	<ul class="list-group">
	<h4> Activity Popularity Ranking</h4>
		<%
			for (int z = 0; z < 20; z++) {
		%>
		<li class="list-group-item">
		<%=z+1 %>Java
			<span class="badge"><%=z %></span>
		</li>
		<%} %>
	</ul>
		</div>
</div>

<%-- end of main container --%>

						<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>


					</body>
</html>
