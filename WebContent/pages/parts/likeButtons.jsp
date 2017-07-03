<button type="button"
	class="btn btn-default btn-sm btn-no-border meta-value"
	data-id="${param.Id}" data-action="like" data-colName="${param.colName}" ${user eq null ? 'disabled':'' }>
	<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
	<span class="meta-value-count" data-count="${param.likeCount}">${param.likeCount}</span>
</button>
<button type="button"
	class="btn btn-default btn-sm btn-no-border meta-value"
	data-id="${param.Id}" data-action="dislike" data-colName="${param.colName}" ${user eq null ? 'disabled':'' }>
	<span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
	<span class="meta-value-count" data-count="${param.dislikeCount}">${param.dislikeCount}</span>
</button>