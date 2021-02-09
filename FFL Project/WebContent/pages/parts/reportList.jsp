<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${user ne null && user.accountId ne param.accountId }">
<button id="post-controls-dropdown" type="button"
	class="btn btn-default btn-sm btn-no-border dropdown-toggle"
	data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	<span class="glyphicon glyphicon-option-horizontal"
		aria-hidden="true"></span>
		<span class="caret"></span>
</button>
<ul class="dropdown-menu" aria-labelledby="post-controls-dropdown">
	<li><a data-toggle="modal" data-target="#reportItem-${param.itemId }" >Report post</a></li>
	<li><a data-toggle="modal" data-target="#reportUser-${param.accountId }">Report user</a></li>
</ul>
<div class="modal fade" id="reportItem-${param.itemId }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Confirm Report?</h4>
        <div>
        	<div class="form-group">
			    <label>Reasons for reporting</label>
			    <input type="text" class="form-control" id="report-post-reason-${param.itemId }"  required>
			</div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-default report-item" data-itemid='${param.itemId }' data-type='${param.type }'>Yes</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="reportUser-${param.accountId }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Confirm Report User?</h4>
        <div>
        	<div class="form-group">
			    <label>Reasons for reporting</label>
			    <input type="text" class="form-control report-reason" id="report-user-reason-${param.accountId }" required>
			</div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-default report-item" data-itemid='${param.accountId }' data-type='account'>Yes</button>
      </div>
    </div>
  </div>
</div>
</c:if>