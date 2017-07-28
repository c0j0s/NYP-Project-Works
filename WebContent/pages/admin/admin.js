var getReported;
$( document ).ready(function() {
	console.log("admin ready")
	
	$(".admin-subpanel-btn").on('click', function(){
		var type = $(this).data().type;
		var list;
		if(type === 'reported'){
			$("#tabName").html("Reported Post and Comments");
			list = getReported();
		}else if(type === 'post'){
			$("#tabName").html("All Posts");
			list = getAllPost();
		}else if(type === 'comment'){
			$("#tabName").html("All Comments");
			list = getAllComment();
		}
	})
	
	getReported = function(){
		console.log('reported')
		getList('AdminForum?get=reported')
		
	}
	function getAllPost(){
		console.log('post')
		getList('AdminForum?get=reported')
	}
	function getAllComment(){
		console.log('comment')
		getList('AdminForum?get=reported')
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
		$("#admin-forum-body").empty();
		var json = JSON.parse(results);
		console.log(json)
		for(var i = 0; i< json.length; i++){
			var tr = "<tr>" +
					"<td><img class='profile-image-xsmall' src='"+json[i].imgUrl+"'/> " + json[i].type +
					"</td><td>"+ json[i].title +"</td>" +
					"<td>"+ json[i].metadata.itemCreatedOn +"</td>" +
					"<td>"+ json[i].metadata.reporterAccountId +"</td>" +
					"<td class='tStatus'>Reported</td?" +
					"<td><div class='btn-group' role='group' aria-label='Actions'>" +
					"<button type='button' class='btn btn-success' id='removeReport' data-itemId='"+json[i].itemId+"'>Remove Report</botton>" +
					"<button type='button' class='btn btn-danger' id='inValidItem' data-itemId='"+json[i].itemId+"'>Delete Post</botton>" +
					"</div></td>" +
					"</tr>"
			$("#admin-forum-body").append(tr);
		}
	}
	$("#removeReport").on("click",function(){
		$(this).data("itemId");
		$(this).parent().parent().remove();
	})
	getReported()
})