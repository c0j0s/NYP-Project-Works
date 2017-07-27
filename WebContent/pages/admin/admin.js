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
		$("#admin-forum-body").append();
	})
	
	function getReported(){
		console.log('reported')
	}
	function getAllPost(){
		console.log('post')
	}
	function getAllComment(){
		console.log('comment')
	}
})