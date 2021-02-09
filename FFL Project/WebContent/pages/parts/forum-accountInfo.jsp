<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${param.hideId ne 'Y' ? true : false }">
		<img alt="profile image" src="${param.imgUrl }"
			class="img-circle profile-image-${param.size eq null ? 'small' : param.size }">
		<p>${param.name}</p>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${user.accountId eq param.accountId}">
				<img alt="profile image" src="${param.imgUrl }"
					class="img-circle profile-image-${param.size eq null ? 'small' : param.size }">
				<p>${param.name}</p>
				<span class="label label-primary">Anonymous</span>
			</c:when>
			<c:otherwise>
				<img alt="profile image" src="https://firebasestorage.googleapis.com/v0/b/famforlife-accc8.appspot.com/o/user%2Fimages.jpg?alt=media&token=299b1cd6-a8db-4652-870a-9d14e97ad10b"
					class="img-circle profile-image-${param.size eq null ? 'small' : param.size }">
				<p>Anonymous</p>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>
