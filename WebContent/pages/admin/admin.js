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
		console.log(list)
		var json = json.parse(list);
		console.log(json)
		$("#admin-forum-body").append();
	})
	
	function getReported(){
		console.log('reported')
		return getList('')
	}
	function getAllPost(){
		console.log('post')
		return getList('')
	}
	function getAllComment(){
		console.log('comment')
		return getList('')
	}
	
	function getList(servletUrl){
		$.ajax({
			url:servletUrl,
			success: function(results){
				return results;
			}
		})
	}
})