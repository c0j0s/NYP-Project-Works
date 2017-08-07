<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix = "f" uri = "../WEB-INF/ffl.tld" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ page import="java.util.ArrayList,bean.*,java.text.DecimalFormat"%>

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
	</jsp:include>
	<div class="col-md-9">
	<%
		DecimalFormat df = new DecimalFormat("##.00");
		ArrayList<Activity> actList = (ArrayList<Activity>)request.getAttribute("activityList");
		for (Activity act : actList) {
	%>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3><%=act.getActivityTitle()%></h3>
				</div>
				<div class="panel-body">
				<div class="panel-info">
				<%if (request.getAttribute("acctId").equals(act.getOrganiserId())){  %>
				 <div class="folded">
            <h2 style="background-color: #DB874A;">Your Activity</h2>
          </div><%} %>
					<div class="col-md-4">
						<img id="actpic" src="<%=act.getImgUrl()%>" />
					</div>
					<div class="col-md-8">
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
							<% for(String day:act.getActivityDay()){
								out.print(day + " ");
							}%>
						<p>
							Timing :
							<%=act.getActivityTime()%>
						</p>
						<p>
							<jsp:include page="parts/likeButtons.jsp">
								<jsp:param value="<%=act.getActivityId()%>" name="Id" />
								<jsp:param value="<%=act.getLikeCount()%>" name="likeCount" />
								<jsp:param value="<%=act.getDislikeCount()%>"
									name="dislikeCount" />
							</jsp:include>
							<span aria-hidden="true">
								<button class="btn btn-success"
									onclick="location.href = 'ActFull?activityId=<%=act.getActivityId()%>'">More
									Info</button>
							</span>
							<% if(session.getAttribute("account")!=null){ if (!(request.getAttribute("acctId").equals(act.getOrganiserId()))) { %>
							<span aria-hidden="true">
								<button class="btn btn-success"
									onclick="location.href = 'ActReg?activityId=<%=act.getActivityId()%>'">Register
									For Activity</button>
							</span><% }%></div>	</p>
						
					</div></div></div>
				
							<% }%>
					
			
			<%}%>
		<!-- Pagination using custom Tag -->
		<f:PostListPagination itemPerPage="5" pageCount="${actCount }" currentPage="${page }"  type="activity"/>
	</div>
	<%-- end of main list --%>
	<div class="col-sm-12 col-md-3">
		<div class="sticky-sidebar">
			<div class="col-md-12 col-sm-4">
				<jsp:include page="parts/sidebar-account.jsp">
					<jsp:param value="ActList" name="url" />
					<jsp:param value="activity" name="type" />
				</jsp:include>
			</div>
			<div class="col-md-12 col-sm-4">
				<ul class="list-group" id="aMultiPlatformList">
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
	<%-- end of sidebar --%>
</div>
<%-- end of main container --%>
	
<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>
</body>
</html>
