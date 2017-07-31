
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ page
	import="java.util.ArrayList,bean.*,database.*,java.text.DecimalFormat"%>
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
<title>Create Reward</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%-- end of header --%>

	<div class="container">
		<%
			DecimalFormat df = new DecimalFormat("00");
		%>
		<h1>Reward Creation Page</h1>
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<form action="${pageContext.request.contextPath}/CreateReward"
				method="post" class="col-md-12" id="form-upload">


				<label for="rewId">Reward Id : </label> <input type="text"
					name="rewId" class="form-control"> <br>
				<div class="vert">
					<div class="col-md-7">
						<br> <label for="imgurl">Reward
							Image: </label><input type="file" name="file" class="form-control"
							id="form-upload" accept="image/*" size="60"><br> <input
							type="hidden" name="imgurl" id="imgurl"
							data-imgfolder="reward/ACC0000000" />
					</div>
					<div class="col-md-5">
						<img alt="" src="../img/def.png" class="fullactpic"
							id="test-img-prev">
					</div>
				</div>
				<br> <label for="">Reward Description:</label>
				<textarea class="form-control" rows="10" cols="50" name="rewDesc"></textarea>
				<br> <label for="rewTitle">Reward Title:</label>
				
						<input type="text" name="rewTitle" class="form-control"
							placeholder="Reward Title ">
							
					      

				

				<label for="Points">Points:</label> <input type="text"
					name="Points" class="form-control"
					placeholder="Enter Points : "> <br> <label
					for="rewAvailability">Reward availability: </label> <input type="text"
					name="rewAvailability" class="form-control"
					placeholder="Enter Reward avaliability : "><br> <label
					for="rewQuantity">Reward Quantity : </label><br> <input type="text"
					name="rewQuantity" class="form-control"
					placeholder="Enter Reward Quantity : "><br> <label
					for="valid">Valid: </label> <input type="text"
					name="valid" class="form-control"
					placeholder="Enter valid : "><br> 
					<label for="itemCreatedOn">Item Created On: 
					 <input type="date"
					name="itemCreatedOn" class="form-control"><br> 
				
					
			
				
				<div class="col-md-12">
					<button type="submit" class="col-md-5 btn btn-success  pull-right">Create</button>
					<p class="col-md-2"></p>
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
