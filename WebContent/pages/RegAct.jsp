<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ page import="java.util.ArrayList,bean.*,database.*,java.text.DecimalFormat,common.*"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta charset="utf-8">
<%!Activity actf;%>
<%!ArrayList<Activity> actfl;%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='${pageContext.request.contextPath}/css/bootstrap.css' rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/bootstrap.custom.css' rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/master.css' rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/card-js.min.css' rel='stylesheet'>
<link rel='icon' href='favicon.ico' type='image/x-icon' />
<title>Register for Activity</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container">
		<jsp:include page="parts/page-header.jsp">
			<jsp:param value="activity" name="type" />
			<jsp:param value="Activities for families" name="title" />
			<jsp:param value="5" name="titleWidth" />
		</jsp:include>
		<div class="col-md-1 pull-left"></div>
		<div class=col-md-8>
			<%
				DecimalFormat df = new DecimalFormat("##.00");
				if (request.getParameter("activityId") == null) {
			%>
			<script>
				window.location.href = "activitypageerror.jsp"
			</script>
			<%
				} else {
					actfl = (ArrayList<Activity>) request.getAttribute("activityRegistration");
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
			<form action="${pageContext.request.contextPath}/RegisterActivity?activityId=<%=actf.getActivityId() %>"
				method="post">
				<p>
					Activity Fee Per Participant : $<span id="generate2"> <%=df.format(actf.getActivityFee())%></span>
				</p>

				<p>Your ID : ${user.accountId}</p>
				<p>Your Name : ${user.givenName}</p>

				<div class="clearfix">
					<p class="col-md-6">
						Number Of Participants : <select name="noOfParticipants" id="generate1"
							class="select form-control">

							<%
								for (int z = 0; z < 5; z++) {
							%>
								<option value="<%=z + 1%>"><%=z + 1%></option>
							<%
								}
							%>
						</select>
					</p>
					<p>
						<input type="hidden" id="total" name="total"
							 value="<%=df.format(actf.getActivityFee())%>">
							Total Price : <input type="text" id="total1" name="total"
							class="col-md-6" value="<%=df.format(actf.getActivityFee())%>"disabled>
					
					</p>
				</div>
				<input type="hidden" id="paytype" name="type" value="Cash">
				<ul class="nav nav-tabs">
					<li class="active" onclick="paytype('Cash')"><a
						data-toggle="tab" href="#cash">Pay By Cash</a></li>
					<li><a data-toggle="tab" href="#online"
						onclick="paytype('Online')">Online Payment</a></li>
				</ul>

				<div class="tab-content">
					<div id="cash" class="tab-pane fade in active">
						<h3>Pay By Cash</h3>
						<p>
							Go to the nearest 7-11 and request payment for Family For Life
							and present id number <%=request.getAttribute("registerId")%>
					</div>
					<br />
					<input type="hidden" value ="<%=request.getAttribute("registerId")%>" name ="registerId">
				<input type="hidden" value="<%=actf.getActivityId() %>" name="activityId">

				<div id="online" class="tab-pane fade">
					<h3>Online Payment</h3>
					<p>
						Supported Payment Types : <img src="img/payment.jpg">

					</p>
					<p>
						<div class="card-js">
							<input class="card-number my-custom-class" name="cardNumber">
							<input class="name" id="the-card-name-id" name="NameOfCardholder"
								placeholder="Name on card"> <input class="expiry-month"
								name="expiryMonth"> <input class="expiry-year"
								name="expiryYear"> <input class="cvc" name="cvc">
						</div>
						<br>
					</p>

				</div>
				</div>
		
			<div class="col-md-12">
				<button type="reset" class="col-md-5 btn btn-danger pull-left">Reset</button>
				<p class="col-md-2"></p>
				<button type="submit" class="col-md-5 btn btn-success  pull-right">Register
					For Activity</button>
			</div>
		</form>
	</div>
	<%}%>

	<div class="col-sm-12 col-md-3">
			<div class="sticky-sidebar">
				<div class="col-md-12 col-sm-4">
					<jsp:include page="parts/sidebar-account.jsp">
						<jsp:param value="activity" name="type" />
						<jsp:param value="ActFull?activityId=<%=actf.getActivityId()%>" name="url" />
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
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>