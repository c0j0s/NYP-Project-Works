<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <c:forEach items="${trendingPost }" var="tPost">
   	<div class="col-sm-4 ">
		<div class="panel panel-default Forum-card forum-trending-card">
		  <div class="panel-body text-center">
		    <img alt="profile image" src="${tPost.accountImgUrl }" class="img-circle profile-image-medium">
		    <p>${tPost.postTitle }</p>
		    <jsp:include page="likeButtons.jsp">
				<jsp:param value="${tPost.likeAccounts }" name="likeAccounts"/>
				<jsp:param value="${tPost.dislikeAccounts }" name="dislikeAccounts"/>
				<jsp:param value="${tPost.postId }" name="Id"/>
				<jsp:param value="${tPost.likeCount }" name="likeCount"/>
				<jsp:param value="${tPost.dislikeCount }" name="dislikeCount"/>
			</jsp:include>
			<button type="button"
				class="btn btn-danger btn-sm btn-no-border">
				<span class="glyphicon glyphicon-fire" aria-hidden="true"></span>
				<span>${tPost.hitLevel}</span>
			</button>
			<br>
			<br>
		    <button type="button" onclick="location.href='${pageContext.request.contextPath}/Post?postId=${tPost.postId}'" class="btn btn-primary">Participate</button>
		  </div>
		</div>
	</div>
</c:forEach>