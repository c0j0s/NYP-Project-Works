<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%@ page
	import="java.util.ArrayList,bean.*,java.text.DecimalFormat"%>

<meta charset="utf-8">
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
<title class="col=-md-4">Activities for families</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%-- end of header --%>

	<div class="container">
		<jsp:include page="parts/page-header.jsp">
			<jsp:param value="activity" name="type" />
			<jsp:param value="Activities for families" name="title" />
			<jsp:param value="5" name="titleWidth" />
			<jsp:param value="subTitle" name="subTitle" />
		</jsp:include>
		<br>

		<div class="col-md-9">
			<%
				DecimalFormat df = new DecimalFormat("##.00");
			%>
			<%
				ArrayList<Activity> actList = (ArrayList<Activity>)request.getAttribute("activityList");
				for (Activity act : actList) {
			%>
			<div class="clearfix">
				<div class="col-md-4">

					<img id="actpic" src="<%=act.getImgUrl()%>" />

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
						<%=df.format(act.getActivityFee())%>
					</p>
					<p>
						Date :
						<%=act.getActivityStartDate()%>
						to
						<%=act.getActivityEndDate()%></p>
					<P>
						Day :
						<%=act.getActivityDay()%>
					<p>
						Timing :
						<%=act.getActivityTime()%>
					</p>
					<p><jsp:include page="parts/likeButtons.jsp">
							<jsp:param value="activity" name="table" />
							<jsp:param value="<%=act.getActivityId()%>" name="Id" />
							<jsp:param value="activityId" name="colName" />
							<jsp:param value="<%=act.getLikeCount()%>" name="likeCount" />
							<jsp:param value="<%=act.getDislikeCount()%>"
								name="dislikeCount" />
						</jsp:include>
						<span aria-hidden="true">
							<button
								onclick="location.href = 'ActFull?activityId=<%=act.getActivityId()%>'">More
								Info</button>
						</span> 
						<span aria-hidden="true">
							<button
								onclick="location.href = 'ActReg?activityId=<%=act.getActivityId()%>'">Register For Activity</button>
						</span> 
						

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
				<h4>Activity Popularity Ranking</h4>
				<%
					for (int z = 0; z < 20; z++) {
				%>
				<li class="list-group-item"><%=z + 1%>. Java <span class="badge"><%=z%></span>
				</li>
				<%
					}
				%>
			</ul>
		</div>
	</div>

	<%-- end of main container --%>

	<jsp:include page="footer.jsp"></jsp:include>
	<%-- end of footer --%>


</body>
</html>
