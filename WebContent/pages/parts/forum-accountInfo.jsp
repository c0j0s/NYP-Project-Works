<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${param.hideId ne 'Y' ? true : false }">
		<img alt="profile image" src="${param.imgUrl }"
			class="img-circle profile-image-${param.size eq null ? 'small' : param.size }">
		<p>${param.name}</p>
	</c:when>
	<c:otherwise>
		<img alt="profile image" src="https://firebasestorage.googleapis.com/v0/b/famforlife-accc8.appspot.com/o/%252Fuser%252Fdefault%252F%2Fanonymous.png?alt=media&token=41c7c380-029e-4f20-86cd-ade552ad50c4"
			class="img-circle profile-image-${param.size eq null ? 'small' : param.size }">
		<p>Anonymous</p>
	</c:otherwise>
</c:choose>