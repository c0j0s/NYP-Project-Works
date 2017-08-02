<div>
	<h3>View By: <span id="tabName">Reported Post and Comments</span></h3>
	<hr>
	<div class="btn-group" role="group" aria-label="...">
  		<button type="button" class="btn btn-default admin-subpanel-btn" data-type="reported"><span class='glyphicon glyphicon-refresh'></span> Reported Posts and Comments</button>
 		<button type="button" class="btn btn-default admin-subpanel-btn" data-type="post"><span class='glyphicon glyphicon-refresh'></span> All Posts</button>
 		<button type="button" class="btn btn-default admin-subpanel-btn" data-type="comment"><span class='glyphicon glyphicon-refresh'></span> All Comments</button>
	</div>
	<hr>
	<table class="table table-hover" >
	<thead>
	<tr><th>Type</th><th>Title</th><th>Created On</th><th>Status</th><th>Report Count</th><th>Actions</th></tr>
	</thead>
	<tbody id="admin-forum-body">
		<tr class="loading"><td><img src="${pageContext.request.contextPath}/img/loading.gif"></td></tr>
	</tbody>
	</table>
</div>