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
	
	function getReported(){
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
//		var json = json.parse(list);
//		console.log(json)
		$("#admin-forum-body").empty();
		$("#admin-forum-body").append(results);
	}
})