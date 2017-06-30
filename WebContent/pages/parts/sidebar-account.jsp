<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel panel-primary forum-sidebar-account">
	<c:choose>
		<c:when test="${false}">
			<c:choose>
				<c:when test="${false}">

				</c:when>
				<c:when test="${false}">

				</c:when>
				<c:when test="${false}">

				</c:when>
				<c:when test="${false}">

				</c:when>
			</c:choose>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${param.type eq 'forum' ? true : false}">
					<div class="panel-heading ">
						<img alt="../img/sample.jpg" src="../img/sample.jpg"
							class="img-circle profile-image-medium">
						<h3 class="panel-title text-center">User Name</h3>
					</div>
					<div class="panel-body">
						<div>
							<div class="col-sm-4 text-center no-padding">
								<p>
									5<br> Questions
								</p>
							</div>
							<div class="col-sm-4 text-center no-padding">
								<p>
									5<br> Answered
								</p>
							</div>
							<div class="col-sm-4 text-center no-padding">
								<p>
									5<br> Best
								</p>
							</div>
						</div>
						<button type="button" class="btn btn-success btn-block"
							onclick="location.href='forum-edit.jsp?type=post&type=create'">Ask
							Something</button>
					</div>
				</c:when>
				<c:when test="${param.type eq 'profile' ? true : false}">
					<div>
						<div class="col-sm-4 text-center no-padding">
							<p>
								${param.points}<br> Points
							</p>
						</div>
						<div class="col-sm-4 text-center no-padding">
							<p>
								${param.creditLevel}<br> Credit Level
							</p>
						</div>
						<button type="button" class="btn btn-success btn-block"
							onclick="location.href=''">Rewards Page</button>
					</div>
				</c:when>
				<c:when test="${false}">

				</c:when>
				<c:when test="${false}">

				</c:when>
			</c:choose>
		</c:otherwise>
	</c:choose>
</div>