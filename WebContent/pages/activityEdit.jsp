<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
<title>Create Activity</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%-- end of header --%>

	<div class="container">
	<form action="${pageContext.request.contextPath}/EditActivity"
				method="post" class="col-md-12" >
		<%
		
		DecimalFormat df = new DecimalFormat("##.00");
	%>
	<%
		if (request.getParameter("activityId") == null) {
			%>
			<script language="javascript">
				window.location.href = "activitypageerror.jsp"
			</script>
<%
			} else {
				actfl = (ArrayList<Activity>)request.getAttribute("activityEdit");

				actf = actfl.get(0);
		%>

		
		<h1>Activity Creation Page</h1>
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<form action="${pageContext.request.contextPath}/CreateActivity"
				method="post" class="col-md-12" >

<input type="hidden" value="<%=actf.getActivityId() %>" name="activityId">
				<label for="actName">Activity Name : </label> <input type="text"
					name="actName" class="form-control"
					value="<%=actf.getActivityTitle()%>"><br>
					 <label for="actCategory">Activity Category : </label><br>
				<select name="actCategory" 
					class="select form-control" >
					<option value="Leisure">Leisure</option>
					<option value="Exercise">Exercise</option>
					<option value="Sports">Sports</option>
					<option value="Educational">Educational</option>

				</select>
				<div class="vert">
					<div class="col-md-7">
						<br> <br> <br><label for="imgurl">Activity
							Image: </label><input type="file" name="file" 
							 size="60" ><br> <input
							type="hidden" name="imgurl" id="imgurl"
							data-imgfolder="activity/${user.accountId }" />
					</div>
					<div class="col-md-5">
						<img alt="" src="<%=actf.getImgUrl()%>" id="test-img-prev" class = "fullactpic"
							>
					</div>
				</div><br>
				<br> <label for="">Activity Description:</label>
				<textarea class="form-control" rows="10" cols="50" name="actDesc" ><%=actf.getActivityDescription()%></textarea><br>
				<br> Activity Fee: <input type="text" value="$<%=df.format(actf.getActivityFee())%>" disabled><br>

				<label for="actPart">Participant Number:</label> <input type="number"
					name="actPart" class="form-control" min="<%=actf.getParticipantNo()%>"
					value="<%=actf.getParticipantNo()%>"> <br> <label
					for="actLocation">Location: </label> <input type="text"
					name="actLocation" class="form-control"
					value="<%=actf.getActivityLocation()%>" disabled><br>
					<div>
					<div class="col-md-5">
						<label for="actStart">Activity Start Date : </label> <input
							type="date" name="actStart" class="form-control" value="<%=actf.getActivityStartDate()%>" disabled>
					</div>
					<div class="col-md-2 text-center">
						<br>-
					</div>
					<div class="col-md-5">
						<label for="actEnd">Activity End Date : </label><br> <input
							type="date" name="actEnd" class="form-control" value="<%=actf.getActivityEndDate() %>" disabled><br>
					</div>
				<br></div>Activity Days : 
				
					<input type="text" value="<%=actf.getActivityDay()%>" disabled>
				
				<br> <br>
				
				<br> <label for="actRegEnd">Registration End : </label> <br><input
					type="text" name="actRegEnd" class="form-control" value="<%=actf.getActivityRegistrationEnd()%>" disabled><br>
				
				<br><br>






				<div class="col-md-12">
					<button type="reset" class="col-md-5 btn btn-danger pull-left">Reset</button>
					<p class="col-md-2"></p>
					<button type="submit" class="col-md-5 btn btn-success  pull-right">Create</button>
				</div>
				<br>
			</form><%
				}
			%>
		</div></form>
		<div class="col-md-2"></div>
	</div>



	<%-- end of main container --%>
	<jsp:include page="footer.jsp"></jsp:include>
	<%-- end of footer --%>

</body>
</html>