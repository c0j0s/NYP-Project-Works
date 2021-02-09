<button type="button"
	class="btn btn-default btn-sm btn-no-border meta-value meta-like-btn actRank loading"
	data-id="${param.Id}" disabled>
	<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
	<span class="meta-value-count">${param.likeCount}</span>
</button>
<button type="button" 
	class="btn btn-default btn-sm btn-no-border meta-value meta-dislike-btn actRank loading"
	data-id="${param.Id}" disabled>
	<span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
	<span class="meta-value-count" >${param.dislikeCount}</span>
</button>