$( document ).ready(function() {
    console.log( "ready!" );
});

function getURLParameter(name) {
	return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search) || [null, ''])[1].replace(/\+/g, '%20')) || null;
}

function createCom(id){
	$.ajax({
		url: "../CreateComment?action=open&postId=" + getURLParameter("postId"), 
		success: function(result){
        $("#"+id).prepend(result);
    }});
}
