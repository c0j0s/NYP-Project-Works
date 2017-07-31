<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%@ page
	import="java.util.ArrayList,bean.*,java.text.DecimalFormat"%>
<%!Activity actf;%>
<%!ArrayList<Activity> actfl;%>
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
<title>Template</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%-- end of header --%>

	<div class="container">


		<%
			DecimalFormat df = new DecimalFormat("##.00");
		%>

		<%
				actfl = (ArrayList<Activity>) request.getAttribute("activityFull");

				actf = actfl.get(0);
		%>
		<jsp:include page="parts/page-header.jsp">
			<jsp:param value="activityfull" name="type" />
			<jsp:param value="Activities For Family" name="title" />
			<jsp:param value="5" name="titleWidth" />
			<jsp:param value="subTitle" name="subTitle" />
		</jsp:include>
		<div>
			<div class="col-md-1 pull-left"></div>
			<div class="col-md-8">
				<p>
				<h3>

					<%=actf.getActivityTitle()%></p>
				</h3>
				<p>
					Activity Categories :
					<%=actf.getActivityCategory()%></p>
				<img src="<%=actf.getImgUrl()%>" id="factpic"><br> <br>
				<p>Activity Description :</p>
				<p style="border-style: solid;"><%=actf.getActivityDescription()%></p>



				<p>
					Organiser Id :
					<%=actf.getOrganiserId()%></p>
				<p>
					Slots Available:
					<%=actf.getParticipantNo()%></p>
				<p>
					Activity Fee : $
					<%=df.format(actf.getActivityFee())%></p>
				<p>
					Activity Time :
					<%=actf.getActivityTime()%></p>
				<p>
					Days Of Activity :
					<%=actf.getActivityDay()%></p>
				<p>
					Activity Location :
					<%=actf.getActivityLocation()%></p>
				<p>
					Activity Period :
					<%=actf.getActivityStartDate()%>
					to
					<%=actf.getActivityEndDate()%></p>
				<p></p>
				<p>
					Activity Post Date :
					<%=actf.getActivityPostDate()%></p>
				<p>

					Registration Closes On :
					<%=actf.getActivityRegistrationEnd()%></p>

				<div>
				<jsp:include page="parts/likeButtons.jsp">
						<jsp:param value="activity" name="table" />
						<jsp:param value="activityId" name="colName" />
						<jsp:param value="<%=actf.getLikeCount()%>" name="likeCount" />
						<jsp:param value="<%=actf.getDislikeCount()%>"
							name="dislikeCount" />
					</jsp:include>
					<%
						if(session.getAttribute("account")!=null){
							Account currentUser = (Account) session.getAttribute("account");
							if (currentUser.getAccountId().equals(actf.getOrganiserId())) {
					%>
					<span aria-hidden="true">
						<button class="btn btn-success"
							onclick="location.href = 'ActEdit?activityId=<%=actf.getActivityId()%>'">Edit
							Activity</button>
					</span>
					<span aria-hidden="true">
						<button class="btn btn-success"
							onclick="location.href = 'RegList?activityActivityId=<%=actf.getActivityId()%>&activityId=<%=actf.getActivityId()%>'">Participants List</button>
					</span>
					
					<%
						}}
					%><%
						if(session.getAttribute("account")!=null){
						
					%>
					<span aria-hidden="true">
						<button class="btn btn-success"
							onclick="location.href = 'ActReg?activityId=<%=actf.getActivityId()%>'">Register
							For Activity</button>
					</span><%} %>
				</div>
			</div>

		</div>
		<div class="col-sm-12 col-md-3">
			<div class="sticky-sidebar">
				<div class="col-md-12 col-sm-4">
					<jsp:include page="parts/sidebar-account.jsp">
						<jsp:param value="ActFull?activityId=<%=actf.getActivityId()%>"
							name="url" />
					</jsp:include>
				</div>
				<div class="col-md-12 col-sm-4">
					<ul class="list-group">
						<h4>Activity Popularity Ranking</h4>
						<%
							int z = 0;
							ArrayList<Activity> actRank = (ArrayList<Activity>) request.getAttribute("actRank");
							for (Activity act : actRank) {
						%>
						<li class="list-group-item"><%=z + 1%>. <a
							href='ActFull?activityId=<%=act.getActivityId()%>'><%=act.getActivityTitle()%>
						</a><span class="badge"><%=act.getRankPoints()%></span></li>
						<%
							z++;
							}
						%>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<%-- end of main container --%>
	<jsp:include page="footer.jsp"></jsp:include>
	<%-- end of footer --%>

</body>
</html>
