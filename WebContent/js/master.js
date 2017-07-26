var createCom, openMessage,closeMessage;
var postDeleteCancel;
var counter;
$( document ).ready(function() {
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
	
	createCom = function(id,order,type){
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
	
	$("#post-delete").on("click", function(){
		var data = $(this).data();
		$("#post-" + data.postid).css("display","none");
		$(".post-comment-group").css("display","none");
		$("#post-container").prepend("<div id='post-delete-message'><div class='panel panel-warning'><div class='panel-heading auto-overflow' >" +
				"<h4 class='col-sm-9'>Post deleting in <span id='post-delete-countdown'>5</span>s. You will be directed to forum.</h4>" +
				"<button type='button' class='btn btn-info col-sm-3' id='post-delete-cancel' onclick='postDeleteCancel(this)' data-postId='"+ data.postid +"'>Cancel</button>" +
				"</div></div></div>");
		console.log("#post-" + data.postid);
		
		var span = 5;
		counter = setInterval(function(){
			$("#post-delete-countdown").html(span)
			console.log(span)
			if(span == 0){
				console.log(ContextPath + "/ForumEdit?type=post&mode=delete&postId=" + data.postid);
				console.log($("#post-delete-message").html());
				if($("#post-delete-message").html()){
					console.log("delete");
					$.ajax({
						url: ContextPath + "/ForumEdit?type=post&mode=delete&postId=" + data.postid, 
						success: function(result){	
							location.href= ContextPath + "/Forum";
						}
					});	
				span = 0;
				}else{
					clearInterval(counter)
				}
			}else{
				span = span - 1;
			}
		},1000)
		
	})
	
	postDeleteCancel = function(e){
		$("#post-delete-message").remove();
		var data = $(e).data();
		console.log(data);
		$("#post-" + data.postid).css("display","block");
		$(".post-comment-group").css("display","block");
		clearInterval(counter);
	}
	$(".post-best-answer-btn").on('click',function(){
		var data = $(this).data()
		$("#"+ data.commentid).addClass("post-best-answer");
		$.ajax({
			data: data,
			url: ContextPath + "/pickBestAnswer", 
			success:function(){
			}
		});
		$(".post-button-action-group").empty();
		$(".post-button-action-group").append("<button type='button' class='btn btn-success btn-block' disabled>Post Closed</button> ");
		$(this).parent().append('<button type="button" class="btn btn-warning col-sm-12" id="post-best-answer-badge" disabled><span class="glyphicon glyphicon-ok" aria-hidden="true"></span><br><hr><sapn>Best<br>Answer</sapn></button>');
		$(this).remove();
	})
	
	/**
	 *  method for meta value TODO update with real time database metavalue
	 */
	
	$(".meta-like-btn").on('click',function(){
		var mdata = $(this).data();
		var id = mdata.id;
		var colName = mdata.colName;
		var loadCount = mdata.count;
		var next = $(this).next();
		var operator;
		var displayCount = $(this).children(".meta-value-count");
		console.log(mdata);
		
		if(next.attr('disabled')){
			next.removeAttr("disabled");
			operator = 'subtract'
		}else{
			operator = 'add'
			next.attr("disabled","disabled");
		}
		
		$.ajax({
			url: ContextPath + "/UpdateMetaValue?action=like&operator="+operator, 
			data: mdata,
			success: function(result){
				console.log(result)
				displayCount.html(result);
			}
		});
		
	})
	
	$(".meta-dislike-btn").on('click',function(){
		var mdata = $(this).data();
		var id = mdata.id;
		var colName = mdata.colName;
		var loadCount = mdata.count;
		var prev = $(this).prev();
		var operator;
		var displayCount = $(this).children(".meta-value-count");
		console.log(mdata);

		if(prev.attr('disabled')){
			prev.removeAttr("disabled");
			operator = 'subtract';
		}else{
			operator = 'add';
			prev.attr("disabled","disabled");
		}

		$.ajax({
			url: ContextPath + "/UpdateMetaValue?action=dislike&operator="+operator, 
			data: mdata,
			success: function(result){
				console.log(result)
				displayCount.html(result);
			}
		});

	})
	
	$("#follow-post").on("click", function(){
		var btn = $(this);
		$.ajax({
			url: ContextPath + "/UpdateMetaValue?action=follow", 
			data: $(this).data(),
			success: function(result){	
				console.log("add success");
				btn.addClass("hide");
				$("#unfollow-post").removeClass("hide");
			}
		});	
	})
	
	$("#unfollow-post").on("click", function(){
		var btn = $(this);
		$.ajax({
			url: ContextPath + "/UpdateMetaValue?action=unfollow", 
			data: $(this).data(),
			success: function(result){	
				console.log("remove success");
				btn.addClass("hide");
				$("#follow-post").removeClass("hide");
			}
		});	
	})
	
	/**
	 * methods for file upload
	 */
	$("input:file").on("change",function(event){
		console.log("change " + URL.createObjectURL(event.target.files[0]))
		$("#test-img-prev").attr('src', URL.createObjectURL(event.target.files[0]));
	})
	
	$('#form-upload').submit( function(event) {
	     var form = this;
	    event.preventDefault();
	    uploadFile(function(){
	    	console.log("callback");
	    	form.submit();
	    })	
	}); 
	
	function uploadFile(callback){
		var storageRef = firebase.storage().ref();
		var file = $("input:file").prop('files')[0];
		var uploadTask = storageRef.child($("#imgurl").data().imgfolder + "/" + file.name).put(file);
		uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED,
		  function(snapshot) {
		    var progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
		    console.log('Upload is ' + progress + '% done');
		    switch (snapshot.state) {
		      case firebase.storage.TaskState.PAUSED: 
		        console.log('Upload is paused');
		        break;
		      case firebase.storage.TaskState.RUNNING: 
		        console.log('Upload is running');
		        break;
		    }
		  }, function(error) {

		  switch (error.code) {
		    case 'storage/unauthorized':
		      break;
		    case 'storage/canceled':
		      break;
		    case 'storage/unknown':
		      break;
		  }
		}, function() {
		  $("#imgurl").val(uploadTask.snapshot.downloadURL);
		  callback();
		});		
	}
	/**
	 * methods for activity
	 */
	$("#generate1,#generate2").on('change', function () {
		var num1 = $('#generate1').val();
		var num2 = $('#generate2').html();
		var total = num1 *num2;
		   var finaltotal = format2(total, "$")
		$('#total').val(finaltotal)
		$('#total1').val(finaltotal)
				$('#total2').val(finaltotal)
	    console.log(num1);
	    console.log(num2);
	});
	
	function format2(n, currency) {
	    return currency + " " + n.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,");
	}
	
	function paytype(type){
		switch(expression) {
	    case 1:
	     $('paytype').val(type)
	        break;
	    case 2:
	     $('paytype').val(type)
	        break;
		}
	}
	
	/**
	 * methods for notification
	 */
	
	$("#toogle-notification").on("click",function(){
		var panel = $("#notification-panel").css("display");
		if (panel === 'none') {
			$("#notification-panel").css("display","block");
			$.ajax({
				url: ContextPath + '/getNotifications',
				success: function(result){
					console.log("get notifications" + result)
					if(result != undefined){
						var item = JSON.parse(result);
						for(var i = 0; i< item.length; i++){
							var li = "<a class='list-group-item notification-list-item'>" +
									"<h4 class='list-group-item-heading'>" + item[i].title +"<span onclick='openMessage(" + item[i].id + ")' class='glyphicon-" + item[i].id + " glyphicon glyphicon-menu-down pull-right'></span></h4>" +
									"<p class='list-group-item-text notification-list-item-text-"+item[i].id+"'>"+ item[i].message +"<br><br> from "+ item[i].serviceType +"</p>" +
									"</a>"
							$("#notification-body").append(li);
						}
					}
				}
			});
		}else{
			console.log("close panel")
			$("#notification-panel").css("display","none");
			$("#notification-body").empty();
		}
	});
	$("#close-notification").on("click",function(){
		$("#notification-panel").css("display","none");
		$("#notification-body").empty();
	});
	
	openMessage = function(id){
		$(".notification-list-item-text-" + id).css('display','block');
		$(".glyphicon-" + id).attr('onclick','closeMessage('+id+')')
		$(".glyphicon-" + id).addClass('glyphicon-menu-up').removeClass('glyphicon-menu-down');
		$.ajax({
			url: ContextPath +'/setNotificationRead',
			data: {'id':id},
			success: function(result){
				$(".notification-count").each(function(){
					console.log("item read count left " + result)
					$(this).html(result);
				});
			}
		});
	}
	closeMessage = function(id){
		$(".glyphicon-" + id).attr('onclick','openMessage('+id+')')
		$(".glyphicon-" + id).addClass('glyphicon-menu-down').removeClass('glyphicon-menu-up');
		$(".notification-list-item-text-" + id).css('display','none');
	}

});
