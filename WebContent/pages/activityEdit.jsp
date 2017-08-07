<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ page import="java.util.ArrayList,bean.*,java.text.DecimalFormat"%>
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
<title>Create Activity</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%-- end of header --%>

	<div class="container">
		<%
			DecimalFormat df = new DecimalFormat("##.00");
			if (request.getParameter("activityId") == null) {
		%>
		<script language="javascript">
			window.location.href = "activitypageerror.jsp"
		</script>
		<%
			} else {
				actfl = (ArrayList<Activity>) request.getAttribute("activityEdit");
				actf = actfl.get(0);
		%>
		<jsp:include page="parts/page-header.jsp">
			<jsp:param value="activityfull" name="type" />
			<jsp:param value="Edit Activity" name="title" />
			<jsp:param value="5" name="titleWidth" />
		</jsp:include>

		<div class="col-md-2"></div>
		<div class="col-md-8">
			<form
				action="${pageContext.request.contextPath}/EditActivity?status=<%=actf.getStatus()%>"
				method="post" class="col-md-12" id="form-upload">
				<input type="hidden" value="<%=actf.getActivityId()%>"
					name="activityId"> <label for="actName">Activity
					Name : </label> <input type="text" name="actName" class="form-control"
					value="<%=actf.getActivityTitle()%>"><br> <label
					for="actCategory">Activity Category : </label><br> <select
					name="actCategory" class="select form-control">
					<option value="Leisure">Leisure</option>
					<option value="Exercise">Exercise</option>
					<option value="Sports">Sports</option>
					<option value="Educational">Educational</option>

				</select>
				<div class="vert">
					<div class="col-md-7">
						<br> <br> <br> <label for="imgurl">Activity
							Image: </label><input type="file" name="file" size="60"><br>
						<input type="hidden" name="imgurl" id="imgurl"
							data-imgfolder="activity/${user.accountId }" />
					</div>
					<div class="col-md-5">
						<img alt="" src="<%=actf.getImgUrl()%>" id="test-img-prev"
							class="fullactpic">
					</div>
				</div>
				<br> <br> <label for="">Activity Description:</label>
				<textarea class="form-control" rows="10" cols="50" name="actDesc"><%=actf.getActivityDescription()%></textarea>
				<br>
				<%
					if (actf.getStatus().equals("Draft")) {
				%><br> Activity Fee: <input type="text"
					value="<%=df.format(actf.getActivityFee())%>" name="actFee"><br>
				<label for="actPart">Participant Number:</label> <input
					type="number" name="actPart" class="form-control"
					value="<%=actf.getParticipantNo()%>"> <br> <label
					for="actLocation">Location: </label> <input type="text"
					name="actLocation" class="form-control"
					value="<%=actf.getActivityLocation()%>"><br>
				<div>
					<div class="col-md-5">
						<label for="actStart">Activity Start Date : </label> <input
							type="date" name="actStart" class="form-control"
							value="<%=actf.getActivityStartDate()%>">
					</div>
					<div class="col-md-2 text-center">
						<br>-
					</div>
					<div class="col-md-5">
						<label for="actEnd">Activity End Date : </label><br> <input
							type="date" name="actEnd" class="form-control"
							value="<%=actf.getActivityEndDate()%>"><br>
					</div>
					<br>
				</div><label
					for="activityDay">Activity Days : </label><br>
				<div class="text-center">
					<%
						String [] days = actf.getActivityDay().split(" ");
						for(int i=0; i < days.length; i++){
							%>
							
							<input type="checkbox" name="actDay" value="Monday"
								class="activitycheckbox" 
								<% 
								if(days[i].equals("Monday")){
									out.print("checked");
								}
								%>
								>&nbsp Monday &nbsp&nbsp&nbsp
							<input
								type="checkbox" name="actDay" value="Tuesday"
								class="activitycheckbox"
								<% 
								if(days[i].equals("Tuesday")){
									out.print("checked");
								}
								%>
								>&nbspTuesday &nbsp&nbsp&nbsp
							<input
								type="checkbox" name="actDay" value="Wednesday"
								class="activitycheckbox"
								<% 
								if(days[i].equals("Wednesday")){
									out.print("checked");
								}
								%>
								>&nbspWednesday &nbsp&nbsp&nbsp 
							<input
								type="checkbox" name="actDay" value="Thursday"
								class="activitycheckbox"
								<% 
								if(days[i].equals("Thursday")){
									out.print("checked");
								}
								%>
								>&nbspThursday &nbsp&nbsp&nbsp 
							<input
								type="checkbox" name="actDay" value="Friday"
								class="activitycheckbox"
								<% 
								if(days[i].equals("Friday")){
									out.print("checked");
								}
								%>
								>&nbspFriday &nbsp&nbsp&nbsp 
							<input
								type="checkbox" name="actDay" value="Saturday"
								class="activitycheckbox"
								<% 
								if(days[i].equals("Saturday")){
									out.print("checked");
								}
								%>
								> &nbspSaturday &nbsp&nbsp&nbsp
							<input
								type="checkbox" name="actDay" value="Sunday"
								class="activitycheckbox"
								<% 
								if(days[i].equals("Sunday")){
									out.print("checked");
								}
								%>
								>&nbsp Sunday &nbsp&nbsp&nbsp
							<% 
						}
					%>

				</div>
				 <br> <br> <br> <label
					for="actRegEnd">Registration End : </label> <br> <input
					type="date" name="actRegEnd" class="form-control"
					value="<%=actf.getActivityRegistrationEnd()%>" name="regEnd"><br>
				<br> <br>
				<%
					} else {
				%><br> Activity Fee: <input type="text"
					value="$<%=df.format(actf.getActivityFee())%>" disabled><br>

				<label for="actPart">Participant Number:</label> <input
					type="number" name="actPart" class="form-control"
					value="<%=actf.getParticipantNo()%>"> <br> <label
					for="actLocation">Location: </label> <input type="text"
					name="actLocation" class="form-control"
					value="<%=actf.getActivityLocation()%>"><br>
				<div class="col-md-5">
					<label for="actStart">Activity Start Date : </label> <input
						type="date" name="actStart" class="form-control"
						value="<%=actf.getActivityStartDate()%>" disabled>
				</div>
				<div class="col-md-2 text-center">
					<br>-
				</div>
				<div class="col-md-5">
					<label for="actEnd">Activity End Date : </label><br> <input
						type="date" name="actEnd" class="form-control"
						value="<%=actf.getActivityEndDate()%>" disabled><br>
				</div>
				Activity Days : <input type="text"
					value="<%=actf.getActivityDay()%>" disabled> <br> <br>
				<br> <label for="actRegEnd">Registration End : </label> <br>
				<input type="text" name="actRegEnd" class="form-control"
					value="<%=actf.getActivityRegistrationEnd()%>" disabled><br>
				<br> <br>
				<%
					}
				%>
				<div class="col-md-12">
					<button type="reset" class="col-md-5 btn btn-danger pull-left">Reset</button>
					<p class="col-md-2"></p>
					<button type="submit" class="col-md-5 btn btn-success  pull-right">Edit</button>
				</div>
				<br>
				<%
					}
				%>
			</form>
		</div>
		<div class="col-md-2"></div>
	</div>



	<%-- end of main container --%>
	<jsp:include page="footer.jsp"></jsp:include>
	<%-- end of footer --%>

</body>
</html>