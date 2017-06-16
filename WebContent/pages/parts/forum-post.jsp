<div class="col-sm-8">
	<div class="panel panel-default">
		<div class="panel-body ">
			<div class="post-text-content">
				<h4>${param.postTitle}</h4>
				<p>${param.postContent}</p>
			</div>
			<div class="btn-toolbar" role="toolbar" aria-label="...">
				<div class="btn-group" role="group" aria-label="...">
					<button type="button" class="btn btn-default btn-sm btn-no-border"
						onclick="">
						<span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
						${param.postLikes}
					</button>
					<button type="button" class="btn btn-default btn-sm btn-no-border"
						onclick="">
						<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>
						${param.postDislikes}
					</button>
					<button type="button" class="btn btn-default btn-sm btn-no-border">
						<span class="glyphicon glyphicon-flag" aria-hidden="true"></span>
						Follow
					</button>
				</div>
				<div class="btn-group pull-right dropdown">
					<small class="pull-left post-date">Posted on: data and time</small>
					<button id="post-controls-dropdown" type="button"
						class="btn btn-default btn-sm btn-no-border dropdown-toggle pull-right"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<span class="glyphicon glyphicon-option-horizontal"
							aria-hidden="true"></span>&nbsp <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" aria-labelledby="post-controls-dropdown">
						<li><a href="#">Report post</a></li>
						<li><a href="#">Report user</a></li>
					</ul>
					<button type="button" class="btn btn-default btn-sm btn-no-border">
						<span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
						${param.commentCount}
					</button>
				</div>
			</div>
		</div>
	</div>
</div>
