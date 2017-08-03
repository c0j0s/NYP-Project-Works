<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='${pageContext.request.contextPath}/css/bootstrap.css' rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/bootstrap.custom.css' rel='stylesheet'>
<link href='${pageContext.request.contextPath}/css/master.css' rel='stylesheet'>
<link rel='icon' href='favicon.ico' type='image/x-icon' />
<title>Create Reward</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%-- end of header --%>

<div class="container">
	<jsp:include page="parts/page-header.jsp">
		<jsp:param value="Reward Creation" name="title"/>
		<jsp:param value="5" name="titleWidth"/>
	</jsp:include>
	<!-- end of page header -->
	<div class="col-md-9">
		<br> 
			<label for="rewTitle">Reward Title:</label>
			<input type="text" name="rewTitle" class="form-control" placeholder="Reward Title ">
			<br>
			<div class="vert">
				<div class="col-md-7">
					<br> <label for="imgurl">Reward
						Image: </label><input type="file" name="file" class="form-control"
						id="form-upload" accept="image/*" size="60"><br> <input
						type="hidden" name="imgurl" id="imgurl"
						data-imgfolder="reward/${user.accountId }" />
				</div>
				<div class="col-md-5">
					<img alt="" src="${pageContext.request.contextPath}/img/def.png" class="fullactpic"
						id="test-img-prev">
				</div>
			</div>
			<br> 
			<label for="">Reward Description:</label>
			<textarea class="form-control" rows="10" cols="50" name="rewDesc"></textarea>
			<br>
			<label for="Points">Points:</label> 
			<input type="text" name="Points" class="form-control" placeholder="Enter Points : "> 
			<br>  
			<label for="rewQuantity">Reward Quantity : </label>
			<br> 
			<input type="text" name="rewQuantity" class="form-control" placeholder="Enter Reward Quantity : ">
			<br> 
			<label for="itemCreatedOn">Item Created On: </label>
			<input type="date" name="itemCreatedOn" class="form-control">
			<br> 

			<button type="submit" class="btn btn-success">Create</button>
			<br>
		</form>
	</div>	
	<!-- end of form -->
	<div class="col-sm-12 col-md-3">
		<div class="sticky-sidebar">
			<div class="col-md-12 col-sm-4">
				<jsp:include page="parts/sidebar-account.jsp">
					<jsp:param value="RedemptionList" name="url" />
					<jsp:param value="Redemption" name="type" />
				</jsp:include>
			</div>
		</div>
	</div>
	<!-- end of sidebar -->
</div>
<br>

<%-- end of main container --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- end of footer --%>

</body>
</html>
