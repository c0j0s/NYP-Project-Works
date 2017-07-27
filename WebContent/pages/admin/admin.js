$( document ).ready(function() {
	console.log("admin ready")
	$(".admin-subpanel-btn").on('click', function(){
		var type = $(this).data().type;
		var list;
		if(type === 'reported'){
			list = getReported();
		}else if(type === 'post'){
			list = getAllPost();
		}else if(type === 'comment'){
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