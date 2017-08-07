<div class="panel panel-default Forum-card ${post.postStatus eq 'closed' ? 'post-closed':'' }">
	<div class="panel-body">
		<div class="col-sm-2 text-center">
			<jsp:include page="forum-accountInfo.jsp">
				<jsp:param value="${post.accountId }" name="accountId"/>
				<jsp:param value="${post.hideId}" name="hideId"/>
				<jsp:param value="${post.accountName}" name="name"/>
				<jsp:param value="${post.accountImgUrl }" name="imgUrl"/>
			</jsp:include>
		</div>
		<div class="col-sm-10">
			<div class="post-link"
				onclick="location.href='Post?postId=${post.postId}'">
				<h4>
					<span class="label label-warning">
					<span class="glyphicon glyphicon-piggy-bank"></span>
					<span>${post.points }</span>
					</span>
					&nbsp
					${post.postTitle}
				</h4>
				<small class="">${post.date}</small>&nbsp
				 ${post.postStatus eq 'closed' ? '<span class="badge"><span class="glyphicon glyphicon-star"></span> Answered</span>':'' }
			</div>
			<div class="Forum-post-control-grps">
				<div class="btn-toolbar" role="toolbar" aria-label="...">
					<div class="btn-group" role="group" aria-label="...">
						<jsp:include page="likeButtons.jsp">
							<jsp:param value="${post.postId }" name="Id"/>
							<jsp:param value="${post.likeCount}" name="likeCount"/>
							<jsp:param value="${post.dislikeCount}" name="dislikeCount"/>
						</jsp:include>
						<button type="button" class="btn btn-default btn-sm btn-no-border">
							<span class="glyphicon glyphicon-comment " aria-hidden="true"></span>
							<span class="meta-value-count"
								data-count="9">${post.commentCount}</span>
						</button>
					</div>
					<div class="btn-group dropdown">
						<jsp:include page="reportList.jsp">
							<jsp:param value="${post.postId }" name="itemId"/>
							<jsp:param value="${post.accountId }" name="accountId"/>
							<jsp:param value="post" name="type"/>
						</jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>