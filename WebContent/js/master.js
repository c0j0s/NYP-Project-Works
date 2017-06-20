function createCom(id){
	$.ajax({
		url: "createComment?action=open", 
		success: function(result){
        $("#"+id).html(result);
    }});
}