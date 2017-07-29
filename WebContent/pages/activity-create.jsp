<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
<title>Create Activity</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%-- end of header --%>

	<div class="container">
		<%
			DecimalFormat df = new DecimalFormat("00");
			DecimalFormat dt = new DecimalFormat("00");
		%>
		<h1>Activity Creation Page</h1>
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<form action="${pageContext.request.contextPath}/CreateActivity"
				method="post" class="col-md-12" id="form-upload">


				<label for="actName">Activity Name : </label> <input type="text"
					name="actName" class="form-control"
					placeholder="Enter Activity Name : "><br>
					 <label for="actCategory">Activity Category : </label><br>
				<select name="actCategory" form="form-upload"
					class="select form-control" placeholder="Pick Category">
					<option value="Leisure">Leisure</option>
					<option value="Exercise">Exercise</option>
					<option value="Sports">Sports</option>
					<option value="Educational">Educational</option>

				</select>
				<div class="vert">
					<div class="col-md-7">
						<br> <br> <br><label for="imgurl">Activity
							Image: </label><input type="file" name="file" class="form-control fullactpic"
							id="form-upload" accept="image/*" size="60"><br> <input
							type="hidden" name="imgurl" id="imgurl"
							data-imgfolder="activity/${user.accountId }" />
					</div>
					<div class="col-md-5">
						<img alt="" src="${pageContext.request.contextPath}/img/def.png" id="test-img-prev" class = "fullactpic"
							>
					</div>
				</div><br>
				<br> <label for="">Activity Description:</label>
				<textarea class="form-control" rows="10" cols="50" name="actDesc"></textarea><br>
				<br> <label for="actFee">Activity Fee:</label>
				<div>
					<div class="col-md-1">$</div>
					<div class="col-md-5">
						<input type="number" name="actFeeDollars" class="form-control"
							placeholder="Dollars " min="0" step="any">
					</div>
					<div class="col-md-1">.</div>
					<div class="col-md-5">
						<input type="number" name="actFeeCents" class="form-control"
							placeholder="Cents" min="0" step="any">
					</div>

				<br><br></div><br>

				<label for="actPart">Participant Number:</label> <input type="number"
					name="actPart" class="form-control" min="0"
					placeholder="Enter Participant Number : "> <br> <label
					for="actLocation">Location: </label> <input type="text"
					name="actLocation" class="form-control"
					placeholder="Enter Location : "><br>
					<div>
					<div class="col-md-5">
						<label for="actStart">Activity Start Date : </label> <input
							type="date" name="actStart" class="form-control" >
					</div>
					<div class="col-md-2 text-center">
						<br>-
					</div>
					<div class="col-md-5">
						<label for="actEnd">Activity End Date : </label><br> <input
							type="date" name="actEnd" class="form-control"><br>
					</div>
				<br></div> <label
					for="actDay">Activity Days : </label><br>
				<div class="text-center">
					<input type="checkbox" name="actDay" value="Monday "
						class="activitycheckbox">&nbsp Monday &nbsp&nbsp&nbsp<input
						type="checkbox" name="actDay" value="Tuesday "
						class="activitycheckbox">&nbspTuesday &nbsp&nbsp&nbsp<input
						type="checkbox" name="actDay" value="Wednesday "
						class="activitycheckbox"">&nbspWednesday &nbsp&nbsp&nbsp <input
						type="checkbox" name="actDay" value="Thursday "
						class="activitycheckbox">&nbspThursday &nbsp&nbsp&nbsp <input
						type="checkbox" name="actDay" value="Friday "
						class="activitycheckbox">&nbspFriday &nbsp&nbsp&nbsp <input
						type="checkbox" name="actDay" value="Saturday "
						class="activitycheckbox"> &nbspSaturday &nbsp&nbsp&nbsp<input
						type="checkbox" name="actDay" value="Sunday "
						class="activitycheckbox">&nbsp Sunday &nbsp&nbsp&nbsp
				</div>
				<br> <br>
				<div>
		
						 <label for="actTimeA">Start Time :  </label>
				<div>
					<div class="col-md-4">

						<select name="actTimeHourA" form="form-upload"
							class="select form-control" >
							<%
								for (int i = 0; i < 12; i++) {
							%>
							<option value="<%=i + 1%>"><%=i + 1%></option>
							<%
								}
							%>
						</select>

					</div>
					<div class="col-md-1 text-center">:</div>
					<div class="col-md-4">
						<select name="actTimeMinA" form="form-upload"
							class="select form-control">
							<%
								for (int i = 0; i < 60; i += 5) {
									String s = dt.format(i);
							%>
							<option value="<%=s%>">
							<%=dt.format(i)%></option>
							<%
								}
							%>
						</select>
					</div>
					<div class="col-md-3">
						<select name="actTimeMA" form="form-upload"
							class="select form-control">
							<option value="AM">AM</option>
							<option value="PM">PM</option>
						</select>
					</div>
				<br><br></div>	<br>
					
					
						 <label for="actTimeB">End Time: </label>
				<div>
					<div class="col-md-4">

						<select name="actTimeHourB" form="form-upload"
							class="select form-control" >
							<%
								for (int i = 0; i < 12; i++) {
							%>
							<option value="<%=i + 1%>"><%=i + 1%></option>
							<%
								}
							%>
						</select>

					</div>
					<div class="col-md-1 text-center">:</div>
					<div class="col-md-4">
						<select name="actTimeMinB" form="form-upload"
							class="select form-control" placeholder="Minute">
							<%
								for (int i = 0; i < 60; i += 5) {
									String s = dt.format(i);
							%>
							<option value="<%=s%>">
							<%=df.format(i)%></option>
							<%
								}
							%>
						</select>
					</div>
					<div class="col-md-3">
						<select name="actTimeMB" form="form-upload"
							class="select form-control">
							<option value="AM">AM</option>
							<option value="PM">PM</option>
						</select>
					</div>
				<br><br></div>
				</div>
				<br> <label for="actRegEnd">Registration End : </label> <br><input
					type="date" name="actRegEnd" class="form-control"><br>
				
				<br><br>






				<div class="col-md-12">
					<button type="reset" class="col-md-5 btn btn-danger pull-left">Reset</button>
					<p class="col-md-2"></p>
					<button type="submit" class="col-md-5 btn btn-success  pull-right">Create</button>
				</div>
				<br>
			</form>
		</div>
		<div class="col-md-2"></div>
	</div>



	<%-- end of main container --%>
	<jsp:include page="footer.jsp"></jsp:include>
	<%-- end of footer --%>

</body>
</html>
