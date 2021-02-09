$(document).ready(function(){
    $("#mButton").click(function(){
        $("#mContent").show();
		$("#rContent").hide();
		$("#cContent").hide();
    });
	
    $("#rButton").click(function(){
        $("#rContent").show();
		$("#mContent").hide();
		$("#cContent").hide();
    });
	
	 $("#cButton").click(function(){
        $("#cContent").show();
		$("#mContent").hide();
		$("#rContent").hide();
    });
});