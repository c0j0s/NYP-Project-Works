<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
		<form action="../CreateActivity" method="post" class = "col-md-12" id="form-upload"
     >
			<div class="col-md-6">
			
			<label for="actName">Activity Name : </label> 
				<input type="text" name="actName" class="form-control" placeholder="Enter Activity Name : "> <br>
				<label for="">Activity Description:</label>
				<textarea class="form-control" rows="10" cols="50" name="actDesc"></textarea><br>
				
				<label for "actImg">Activity Image: </label><input type="file" name="file" class="form-control" id="form-upload"
					accept="image/* size="60"><br> <input type="hidden" name="imgurl" id="imgurl" data-imgfolder="activity/ACC0000000"/>
				
				
			</div>
			<div class="col-md-6">
				<label for="actFee">Activity Fee:</label> 
				<div>
					<div class ="col-md-1">$</div>
					<div class ="col-md-5">
						<input type="number" name="actFeeDollars" class="form-control" placeholder="Dollars " min="1" step= "any">
					</div>
					<div class ="col-md-1">.</div>
					<div class ="col-md-5"><input type="number" name="actFeeCents" class="form-control" placeholder="Cents" min="1" step= "any">
					</div>
				
				</div>
				
				<label for="actPart">Participant Number:</label> 
				<input type="text" name="actPart" class="form-control" placeholder="Enter Participant Number : "> <br>
				
				<label for="actLocation">Location: </label> 
				<input type="text" name="actLocation" class="form-control" placeholder="Enter Location : "><br>
				

				
				<label for="actDay">Activity Days : </label><br>
				<input type="checkbox" name="actDay" value="Monday "> Monday
				<input type="checkbox" name="actDay" value="Tuesday ">Tuesday
				<input type="checkbox" name="actDay" value="Wednesday ">Wednesday
				<input type="checkbox" name="actDay" value="Thursday ">Thursday
				<input type="checkbox" name="actDay" value="Friday ">Friday<br>
				<br>
				<label for="actTime">Timing: </label> <div>
				<div class = "col-md-5"><input type="number" name="actTimeHour" class="form-control"></div>
				<div class = "col-md-1">:</div>
				<div class = "col-md-5"><input type="number" name="actTimeMin" class="form-control"></div>
				 <div class = "col-md-1"> <select name="actTimeM" form="form-upload">
 					 <option value="AM">AM</option>
 					 <option value="PM">PM</option>
				  </select></div>
				<label for="actRegEnd">Registration End : </label> 
				<input type="date" name="actRegEnd" class="form-control"> <br>
				<div>
				<div class= "col-md-5">
				<label for ="actStart">Activity Start Date : </label>
				<input type="date" name="actStart" class="form-control">
				</div><div class = "col-md-2 text-center"><br>-</div>
				<div class= "col-md-5">
				<label for="actEnd">Activity End Date : </label>
				<input type="date" name="actEnd" class="form-control"><br>
				</div>
				</div>
				<label for="actCategory">Category : </label> 
				<input type="text" name="actCategory" class="form-control" placeholder="Enter Category : "> <br>
				
				
			

				
			</div>
			<div class= "col-md-12"><button type = "reset" class = "col-md-5 btn btn-danger pull-left">Reset</button><p class ="col-md-2"></p><button type="submit" class="col-md-5 btn btn-success  pull-right">Create</button></div><br>
		</form>
	</div>
	</div>


	<%-- end of main container --%>
	<jsp:include page="footer.jsp"></jsp:include>
	<%-- end of footer --%>

</body>
</html>
