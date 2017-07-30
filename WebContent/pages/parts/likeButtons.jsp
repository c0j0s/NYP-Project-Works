<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="../../WEB-INF/ffl.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:choose>
	<c:when test="${user eq null ? false:true }">
		<c:set var="likedisable" scope="request"
			value="${fn:contains(param.likeAccounts, user.accountId)}" />
		<c:set var="dislikedisable" scope="request"
			value="${fn:contains(param.dislikeAccounts, user.accountId)}" />
	</c:when>
	<c:otherwise>
		<c:set var="likedisable" scope="session" value="${true }" />
		<c:set var="dislikedisable" scope="session" value="${true }" />
	</c:otherwise>
</c:choose>

<button type="button"
	class="btn btn-default btn-sm btn-no-border meta-value meta-like-btn actRank"
	data-id="${param.Id}" 
	data-count="${param.likeCount}"
	${dislikedisable eq true ? 'disabled':'' }>
	<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
	<span class="meta-value-count">${param.likeCount}</span>
</button>
<button type="button" 
	class="btn btn-default btn-sm btn-no-border meta-value meta-dislike-btn actRank"
	data-id="${param.Id}" 
	data-count="${param.dislikeCount}"
	${likedisable eq true ? 'disabled':'' }>
	<span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
	<span class="meta-value-count" >${param.dislikeCount}</span>
</button>