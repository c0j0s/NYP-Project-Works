var getReported,itemAction;
$( document ).ready(function() {
	console.log("admin ready")
	
	$(".admin-subpanel-btn").on('click', function(){
		$("#admin-forum-body").empty();
		var type = $(this).data().type;
		console.log(type)
		if(type === 'reported'){
			$("#tabName").html("Reported Post and Comments");
			getReported();
		}else if(type === 'post'){
			$("#tabName").html("All Posts");
			getAllPost();
		}else if(type === 'comment'){
			$("#tabName").html("All Comments");
			getAllComment();
			}
	})
	
	
	getReported = function(){
		console.log('reported')
		getList('AdminForum?get=reported')
		$('#admin-forum-refresh').attr('data-type','reported')
	}
	getReported();
	function getAllPost(){
		console.log('post')
		getList('AdminForum?get=post')
		$('#admin-forum-refresh').attr('data-type','post')
	}
	
	function getAllComment(){
		console.log('comment')
		getList('AdminForum?get=comment')
		$('#admin-forum-refresh').attr('data-type','comment')
	}
	
	function getList(servletUrl){
		$.ajax({
			url:ContextPath + "/" +servletUrl,
			success: function(results){
				console.log("log results: " + results)
				updateTabContent(results)
			}
		})
	}
	
	function updateTabContent(results){
		var json = JSON.parse(results);
		console.log(json)
		for(var i = 0; i< json.length; i++){
			var cancel = '';
			var del = '';
			if(json[i].metadata.status === 'cancel'){
				cancel = 'disabled'
			}else if(json[i].metadata.status === 'deleted'){
				del = 'disabled'
			}
			
			var tr = "<tr id='item-"+json[i].itemId+"'>" +
					"<td><img class='profile-image-xsmall' src='"+json[i].imgUrl+"'/> " + json[i].type +
					"</td><td>"+ json[i].title +"</td>" +
					"<td>"+ json[i].metadata.itemCreatedOn.substr(0,10) +"</td>" +
					"<td class='tStatus'>"+ json[i].metadata.status +"</td>" +
					"<td>"+ json[i].metadata.reportCount +"</td>" +
					"<td><div class='btn-group' role='group' aria-label='Actions'>" +
					"<button type='button' class='btn btn-success' onclick='itemAction(this)' data-type='"+ json[i].type +"' data-action='cancel' data-itemId='"+json[i].itemId+"' "+cancel+">Remove Report</botton>" +
					"<button type='button' class='btn btn-danger' onclick='itemAction(this)' data-type='"+ json[i].type +"' data-action='deleted' data-itemId='"+json[i].itemId+"' "+del+">Delete Post</botton>" +
					"</div></td>" +
					"</tr>"
			$("#admin-forum-body").append(tr);
		}
	}
	
	itemAction = function(e){
		var tdata = $(e).data();
		if(tdata.action === 'cancel'){
			$(e).attr('disabled','disabled')
			$(e).next().removeAttr('disabled')
		}else if(tdata.action === 'deleted'){
			$(e).attr('disabled','disabled')
			$(e).prev().removeAttr('disabled')
		}
		$.ajax({
			url: ContextPath + '/AdminForumUpdate',
			data:tdata,
			success: function(){
				$("#item-" + tdata.itemid).children(".tStatus").html(tdata.action)
			}
		})
	}
	
	$(".admin-activity").on('click', function(){
		$("#admin-activity-body").empty();
		$.ajax({
			url : "AdminActivity",
			success : function(adminList){
				var json = JSON.parse(adminList);
				console.log(json)
				for(var i = 0; i< json.length; i++){
				var list = '<tr><td>'+json[i].activityId+'</td><td>'+json[i].activityTitle+'</td><td><span class="badge">'+json[i].rankPoints+'</span></td><td><button type="submit" class="btn-danger btn" name="buttonClickList" value="Delete" onclick="actDelete(this)" data-id="'+json[i].activityId+'" data-action="Invalid">Delete Activity</button></td><td><button type="submit" value="Restore" name="buttonClickList" class="btn-success btn" onclick="actDelete(this)" data-id="'+json[i].activityId+'" data-action="Valid">Restore Activity</button></td><td id="buttonValid-'+json[i].activityId+'">'+json[i].valid+'</td></tr>'
			$("#admin-activity-body").append(list);
				console.log(":()()()()()()()()()()(");
				}}
		})
	})


	$("#admin-others-send-message").on('click',function(){
		var message = $("#admin-others-input-message").val()
		var title = $("#admin-others-input-title").val()
		if(title != "" && message != ""){
			var json = {
				'message':message,
				'title':title
			}
			adminOthers("message",json)
		}else{
			$("#admin-others-input-message").css("border-color","red")
			$("#admin-others-input-title").css("border-color","red")
			popup('body','Please complete the fields.')
		}
	})
	$("#admin-others-add-category").on('click',function(){
		var value = $("#admin-others-input-category").val()
		if(value != ""){
			var json = {
					'categoryName':value
				}
			adminOthers("category",json)
		}else{
			 $("#admin-others-input-category").css("border-color","red")
			popup('body','Please complete the fields.')
		}
	})
	function adminOthers(type,value){
		$.ajax({
			url:ContextPath + "/AdminOthers?type=" + type,
			data:value,
			success:function(results){
				popup('body',results)
			}
		})
	}
})
function actDelete(e){
	var id = $(e).data().id
	var action = $(e).data().action
	console.log(id)
	$.ajax({
		url:ContextPath + "/DeleteActivity?actId="+id+"&action="+action,
		success:function(results){
			$("#buttonValid-"+id).html(results);
		}
	})
}