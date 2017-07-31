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
				var list = '<li class="list-group-item">'+json[i].activityId+json[i].activityTitle+'<span class="badge">'+json[i].rankPoints+'</span></li>'
			$("#admin-activity-body").append(list);
				console.log(":()()()()()()()()()()(");
				}}
		})
	})


	
})