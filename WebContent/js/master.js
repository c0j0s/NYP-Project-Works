$( document ).ready(function() {s
	var config = {
		apiKey: "AIzaSyAIsMxruOQZcVlHGcpbJQ3c6CF9ZgpFMkQ",
		authDomain: "famforlife-accc8.firebaseapp.com",
		databaseURL: "https://famforlife-accc8.firebaseio.com",
		projectId: "famforlife-accc8",
		storageBucket: "famforlife-accc8.appspot.com",
		messagingSenderId: "1088530786881"
	};
	firebase.initializeApp(config);
    console.log( "ready!" );

	/**
	 * 
	 * method for forum post and comment
	 * 
	 */
	function getURLParameter(name) {
		return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search) || [null, ''])[1].replace(/\+/g, '%20')) || null;
	}
	
	function createCom(id,order,type){
		$(".addCom").each(function(){
			$(this).attr('disabled','disabled');
		});
		var servletUrl;
		if(type === 'post'){
			servletUrl = ContextPath + "/CreateComment?action=open&postId=" + getURLParameter("postId");
		}else if(type==='comment'){
			servletUrl = ContextPath + "/CreateComment?action=open&postId=" + getURLParameter("postId") + "&commentId=" + id;
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
	
	function closeCommentBox(id){
		$(".addCom").each(function(){
			$(this).removeAttr("disabled");
		});
		$("#comment-box-"+id).remove();
	}
	
	/**
	 *  method for meta value
	 */
	$(".meta-value").click(function(){
		var data = $(this).data();
		$(this).addClass("meta-clicked");
		$(this).removeClass("meta-value");
		var meta = $(this).children(".meta-value-count");
		var count = meta.data().count;
		
		if(data.action === "like"){
			if($(this).next().attr("disabled") === undefined){
				count = count + 1;
				$(this).next().attr("disabled","disabled");
				addMetaValue(data,function(){
					meta.html(count);
				});
			}else{
				$(this).next().removeAttr("disabled");
				removeMetaValue(data,function(){
					meta.html(count);
				});
			}
			
		}else if(data.action === "dislike"){
			if($(this).prev().attr("disabled") === undefined){
				count = count + 1;
				$(this).prev().attr("disabled","disabled");
				addMetaValue(data,function(){
					meta.html(count);
				});
			}else{
				$(this).prev().removeAttr("disabled");
				removeMetaValue(data,function(){
					meta.html(count);
				});
			}
		}else if(data.action === "follow"){
			
		}
		
		console.log(count);
	});
	
	function addMetaValue(metaData,callback){
		console.log(metaData);
		$.ajax({
			url: ContextPath + "/UpdateMetaValue?mode=add", 
			data: metaData,
			success: function(result){
				console.log("ajax"+ result)
				callback();
			}
		});
	}
	
	function removeMetaValue(metaData,callback){
		console.log(metaData);
		$.ajax({
			url: ContextPath + "/UpdateMetaValue?mode=remove", 
			data: metaData,
			success: function(result){
				console.log("ajax"+ result)
				callback();
			}
		});
	}
	
	/**
	 * methods for file 
	 */
	$("input:file").on("change",function(event){
		console.log("change " + URL.createObjectURL(event.target.files[0]))
		$("#test-img-prev").attr('src', URL.createObjectURL(event.target.files[0]));
	})


});