$( document ).ready(function() {
    console.log( "ready!" );
});

function getURLParameter(name) {
	return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search) || [null, ''])[1].replace(/\+/g, '%20')) || null;
}

function createCom(buttomId,id,order,type){
	$("#"+buttomId).attr('disabled','disabled');
	var servletUrl;
	if(type === 'post'){
		servletUrl = "../CreateComment?action=open&postId=" + getURLParameter("postId");
	}else if(type==='comment'){
		servletUrl = "../CreateComment?action=open&postId=" + getURLParameter("postId") + "&commentId=" + id;
	}

	console.log(servletUrl);
	$.ajax({
		url: servletUrl, 
		success: function(result){
			if(order==='before'){
		        $("#"+id).prepend(result);
			}else{
				$("#"+id).after(result);
			}
    }});
}

function closeCommentBox(e){
	$("#"+buttomId).attr('disabled','enable');
//	$.ajax({
//		url: "../CreateComment?action=open&postId=" + getURLParameter("postId"), 
//		success: function(result){
//			if(order==='before'){
//		        $("#"+id).prepend(result);
//			}else{
//				$("#"+id).after(result);
//			}
//    }});
}
