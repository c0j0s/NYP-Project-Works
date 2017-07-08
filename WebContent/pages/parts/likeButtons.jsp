<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "../../WEB-INF/ffl.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:choose>
	<c:when test="${user eq null ? false:true }">
		<c:set var="likedisable" scope="session"
			value="${fn:contains(param.likeAccounts, user.accountId)}" />
		<c:set var="dislikedisable" scope="session"
			value="${fn:contains(param.dislikeAccounts, user.accountId)}" />
	</c:when>
	<c:otherwise>
		<c:set var="likedisable" scope="session"
			value="${true }" />
		<c:set var="dislikedisable" scope="session"
			value="${true }" />
	</c:otherwise>
</c:choose>

<button type="button"
	class="btn btn-default btn-sm btn-no-border meta-value"
	data-id="${param.Id}" data-action="like" data-colName="${param.colName}" ${dislikedisable eq true ? 'disabled':'' }>
	<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
	<span class="meta-value-count" data-count="${param.likeCount}">${param.likeCount}</span>
</button>
<button type="button"
	class="btn btn-default btn-sm btn-no-border meta-value"
	data-id="${param.Id}" data-action="dislike" data-colName="${param.colName}" ${likedisable eq true ? 'disabled':'' }>
	<span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
	<span class="meta-value-count" data-count="${param.dislikeCount}">${param.dislikeCount}</span>
</button>