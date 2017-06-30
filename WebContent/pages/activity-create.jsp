<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%@ page import="java.util.ArrayList,bean.*,database.*" %>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='${pageContext.request.contextPath}/css/bootstrap.css' rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/bootstrap.custom.css' rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/master.css' rel='stylesheet'>
<link rel='icon' href='favicon.ico' type='image/x-icon' />
<title>Create Activity</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%-- end of header --%>

	<div class="container">
		<h1>Activity Creation Page</h1>
		<form action="../CreateActivity" method="post">
			<div class="col-md-6">
				<input type="file" name="actImg" class="form-control"
					accept="image/*">
				<label for="">Activity Description:</label>
				<textarea class="form-control" rows="10" cols="50" name="actDesc"></textarea>
			</div>
			<div class="col-md-6">
				<label for="actName">Activity Name : </label> 
				<input type="text" name="actName" class="form-control" placeholder="Enter Activity Name : "> 
				<label for="actCategory">Category : </label> 
				<input type="text" name="actCategory" class="form-control" placeholder="Enter Category : "> 
				<label for="actFee">Fee:</label> 
				<input type="text" name="actFee" class="form-control" placeholder="Enter Fee : "> 
				<label for="actPar">Participant Number:</label> 
				<input type="number	" name="actPar" class="form-control" placeholder="Enter Participant Number : "> 
				<label for="actLocation">Location: </label> 
				<input type="text" name="actLocation" class="form-control" placeholder="Enter Location : ">
				<label for="actDay">Activity Days : </label>
				<input type="text" name="actDay" class="form-control">
				<label for="actTime">Timing: </label> 
				<input type="text" name="actTime" class="form-control"><label for="actRegEnd">Registration End : </label> 
				<input type="date" name="actRegEnd" class="form-control"> 
				<label for="actStart">Activity Start Date : </label> 
				<input type="date" name="actStart" class="form-control"> 
				<label for="actEnd">Activity End Date : </label>
				<input type="date" name="actEnd" class="form-control">
			

				
			</div>
			<button type="submit" class="btn btn-success btn-block">Create</button> 
		</form>
	</div>
	</div>


	<%-- end of main container --%>
	<jsp:include page="footer.jsp"></jsp:include>
	<%-- end of footer --%>

</body>
</html>
