<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='../css/bootstrap.css' rel='stylesheet'>
<link href='../css/bootstrap.custom.css' rel='stylesheet'>
<link href='../css/master.css' rel='stylesheet'>
<link rel='icon' href='favicon.ico' type='image/x-icon' />
<title>Create RewardList</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%-- end of header --%>

	<div class="container">
		<h1>Reward Creation Page</h1>
		<form>
			<div class="col-md-6">
				<input type="file" name="actImg" class="form-control"
					accept="image/*">
				<label for="">Reward Description:</label>
				<textarea class="form-control" rows="10" cols="50" name="actDesc"></textarea>
			</div>
			<div class="col-md-6">
				<label for="rewardID">Reward ID : </label> 
				<input type="text" name="rewardId" class="form-control" placeholder="Enter Reward ID : "> 
				<label for="rewardTitle">Reward Title : </label> 
				<input type="text" name="rewardTitle" class="form-control" placeholder="Enter Reward Title : "> 
				<label for="rewardDescription">Reward Description: </label> 
				<input type="text" name="rewardDescription" class="form-control" placeholder="Enter Reward Description : ">
				<label for="points">Points : </label> 
				<input type="text" name="points" class="form-control" placeholder="Enter points : "> 
				<label for="rewardAvailability">Reward Availability : </label> 
				<input type="rewardAvailability" name="rewardAvailability" class="form-control" placeholder="Enter Reward Availability : "> 
			    <label for="rewardQuantity">Reward Quantity : </label> 
				<input type="text" name="rewardQuantity" class="form-control" placeholder="Enter Reward Quantity : "> 
				<label for="valid">Valid : </label> 
				<input type="text" name="Valid" class="form-control" placeholder="Enter Valid : "> 
				<label for="itemCreateOn">Item Create On : </label> 
				<input type="text" name="itemCreateOn" class="form-control" placeholder="Enter Item Create On : "> 
			</div>
		</form>
	</div>
	</div>


	<%-- end of main container --%>
	<jsp:include page="footer.jsp"></jsp:include>
	<%-- end of footer --%>

</body>
</html>
