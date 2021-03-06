var createCom, openMessage,closeMessage,closeCommentBox ;
var postDeleteCancel,commentDeleteCancel;
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

    $(function() {
        $("#main-container").animate(
            {
            	top : "-88vh",
            	opacity:"1"
            }, 1000, function() {
            	
            }
        );
    });
    
    jQuery(function($) {
    	function fixDiv() {
    		var cache = $('.sticky-sidebar');
    		if($( window ).width() > 990){
    			if ($(window).scrollTop() > 300)
    				if($( window ).width() > 1200){
    					cache.css({
        					'position': 'fixed',
        					'width':'255px',
        					'top':'10px'
        				});
    				}else{
        				cache.css({
        					'position': 'fixed',
        					'width':cache.width(),
        					'top':'10px'
        				});
    				}
    			else
    				cache.css({
    					'position': 'relative',
    					'width':'auto',
						'top':'auto'
    				});
    		}else{
    			cache.css({
    				'position': 'relative',
					'width':'auto',
					'top':'auto'
				});
    		}
    	}
    	$(window).scroll(fixDiv);
    	$(window).resize(fixDiv);
    	fixDiv();
    });

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
	
	closeCommentBox = function(id){
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
		
		var span = 5;
		counter = setInterval(function(){
			$("#post-delete-countdown").html(span)
			console.log(span)
			if(span == 0){
				if($("#post-delete-message").html()){
					$.ajax({
						url: ContextPath + "/InvalidPost?postId=" + data.postid, 
						success: function(result){	
							location.assign(ContextPath + "/Forum")
						}
					});	
				span = ".....";
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
		$(".post-button-action-group").append("<button type='button' class='btn btn-success btn-block' disabled>Post Closed</button> ");
		$(this).parent().append('<button type="button" class="btn btn-warning col-sm-12" id="post-best-answer-badge" disabled><span class="glyphicon glyphicon-ok" aria-hidden="true"></span><br><hr><sapn>Best<br>Answer</sapn></button>');
		$(".post-best-answer-btn").each(function(){
			$(this).remove();
		});
	})
	
	$('.comment-delete').on('click',function(){
		var id = $(this).data().id
		$(".post-comment-" + id).css("display","none");
		$(".post-comment-" + id).after("<div id='comment-delete-message'><div class='panel panel-warning'><div class='panel-heading auto-overflow' >" +
				"<h4 class='col-sm-9'>Comment deleting in <span id='comment-delete-countdown'>5</span>s.</h4>" +
				"<button type='button' class='btn btn-info col-sm-3' id='post-delete-cancel' onclick='commentDeleteCancel(this)' data-id='"+ id+"'>Cancel</button>" +
				"</div></div></div>");
		
		var span = 5;
		counter = setInterval(function(){
			$("#comment-delete-countdown").html(span)
			if(span == 0){
				if($("#comment-delete-message").html()){
					$.ajax({
						url: ContextPath + "/InvalidComment",
						data:{'commentId':id},
						success: function(message){	
							popup('body',message)
							$(".post-comment-" + id).remove()
							$("#comment-delete-message").remove();
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
	
	commentDeleteCancel = function(e){
		var id = $(e).data().id;
		$(".post-comment-" + id).css("display","block");
		$("#comment-delete-message").remove();
		clearInterval(counter);
	}

	if($(".forum-sidebar-top-answerer").length){
		$(".forum-sidebar-top-answerer").ready(function(){
			console.log("load log");
			$.ajax({
				url: ContextPath + "/GetTopAnswerer",
				success:function(json){
					$(".forum-sidebar-top-answerer").empty();
					var item = JSON.parse(json);
					for(var i = 0; i < item.length; i++){
						var li = '<h4><img alt="Top Answerer profile image" src="' + item[i].imgUrl + '" class="img-circle profile-image-xsmall"/>'
						+ item[i].givenName + ' <span class="label label-danger label-sm pull-right">'
						+ '<span class="glyphicon glyphicon-fire" aria-hidden="true"></span>' 
						+ item[i].postsCounts + '</span></h4>'
						$(".forum-sidebar-top-answerer").append(li);
					}
				}
			})
		})
	}
	
	if($('.comments-comment').length){
		$('.comments-comment').each(function(){
			var item = $(this)
			var value = item.data()
			$.ajax({
				url: ContextPath + "/GetCommentsComment",
				data : value,
				success:function(json){
					item.empty()
					var obj = JSON.parse(json)
					for(var i = 0; i < obj.length; i++){
						item.append('<div class="row comment-under-comment"><div class="col-md-2 col-sm-3"><img src="'+obj[i].accountImgUrl+'" class="img-circle profile-image-xsmall">'+
								'<p>says: </p></div><div class="col-md-10 col-sm-9"><p>'+obj[i].commentContent+'</p></div></div>')
					}
				}
			})
		})
	}
	/**
	 *  method for meta value TODO update with real time database metavalue
	 */
	$(".meta-like-btn").each(function(){
		var item = $(this);
		var value = item.data()
		$.ajax({
			url: "CheckUserLiked",
			data:value,
			success:function(results){
				if(results === 'like'){
					item.removeAttr("disabled");
				}else if(results === 'dislike'){
					item.next().removeAttr("disabled");
				}else if(results === 'nil'){
					item.removeAttr("disabled");
					item.next().removeAttr("disabled");
				}
				item.removeClass("loading");
				item.next().removeClass("loading");
			}
		})
	})
	
	$(".meta-like-btn").on('click',function(){
		var mdata = $(this).data();
		var id = mdata.id;
		var colName = mdata.colName;
		var loadCount = mdata.count;
		var next = $(this).next();
		var operator;
		var displayCount = $(this).children(".meta-value-count");
		
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
				displayCount.html(result);
			}
		});

	})
	
	if($("#follow-post").length){
		$("#follow-post").ready(function(){
			var item = $("#follow-post")
			var value = item.data()
			$.ajax({
				url: "CheckUserFollowedPost",
				data:value,
				success:function(results){
					if(results === 'true'){
						item.addClass("hide")
						item.prev().removeClass("hide")
					}
					item.removeAttr("disabled")
					item.prev().removeAttr("disabled")
				}
			})
		})
	}
	$("#follow-post").on("click", function(){
		var btn = $(this);
		$.ajax({
			url: ContextPath + "/UpdateMetaValue?action=follow", 
			data: $(this).data(),
			success: function(result){	
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
				btn.addClass("hide");
				$("#follow-post").removeClass("hide");
			}
		});	
	})
	
	/**
	 * methods for file upload
	 */
	$("input:file").on("change",function(event){
		$("#test-img-prev").attr('src', URL.createObjectURL(event.target.files[0]));
	})
	
	$('#form-upload').submit( function(event) {
		var file = $("input:file").prop('files')[0];
		if(file != undefined){
		     var form = this;
		    event.preventDefault();
		    uploadFile(function(){
		    	form.submit();
		    	console.log("have")
		    })	
		}else{
			form.submit();
			console.log("no")
		}
	}); 
	
	function uploadFile(callback){
		var storageRef = firebase.storage().ref();
		var file = $("input:file").prop('files')[0];
		var uploadTask = storageRef.child($("#imgurl").data().imgfolder + "/" + file.name).put(file);
		uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED,
		  function(snapshot) {
		    var progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
		    console.log(progress)
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
	$(".actRank").on('click',function(){
		if($("#aMultiPlatformList").length){
			$("#aMultiPlatformList").empty();
			$.ajax({
				url : "actRankList",
				success : function(rankingList){
					var json = JSON.parse(rankingList);
					for(var i = 0; i< json.length; i++){
						var list = '<li class="list-group-item">'+(i+1)+'. <a href ="ActFull?activityId='+json[i].activityId+'">'+json[i].activityTitle+'</a><span class="badge">'+json[i].rankPoints+'</span></li>'
						$("#aMultiPlatformList").append(list);
					}}
			})
		}
	}); 
	 function calculatorzz(count) {
		 var num1 = count;
		var num2 = $('#generate2').html();
		var total = num1 *num2;
		   var finaltotal = format2(total, "$");
		$('#total').val(total);
		$('#total1').val(finaltotal);
		$('#countpay').val(count);
	};
	
	function format2(n, currency) {
	    return currency + " " + n.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,");
	}

	/**
	 * methods for notification
	 */
	
	$("#toogle-notification").on("click",function(){
		var panel = $("#notification-panel").css("display");
		if (panel === 'none') {
			$("#notification-panel").css("display","block");
			$.ajax({
				url: ContextPath + '/getNotifications?type=unread',
				success: function(result){
					if(result != undefined){
						var item = JSON.parse(result);
						$("#notification-body").empty(); 
						for(var i = 0; i< item.length; i++){
							var li = "<a class='list-group-item notification-list-item'>" +
									"<h4 class='list-group-item-heading'><span class='label label-warning'>"+ item[i].serviceType +"</span>&nbsp" + item[i].title +"<span onclick='openMessage(" + item[i].id + ")' class='glyphicon-" + item[i].id + " glyphicon glyphicon-menu-down pull-right'></span></h4>" +
									"<p class='list-group-item-text notification-list-item-text-"+item[i].id+"'>"+ item[i].message +"<br>"+ item[i].createdOn.substring(0,10) +"</p></a>";
							$("#notification-body").append(li);
							if(item[i].actionText != undefined){
								$(".notification-list-item-text-" + +item[i].id).append("<br><a class='btn btn-default' role='button' href='"+ item[i].actionUrl +"'>"+ item[i].actionText +"</a>");
							}
						}
					}
				}
			});
		}else{
			$("#notification-panel").css("display","none");
			$("#notification-body").empty();
		}
	});
	$(".close-notification").on("click",function(){
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
	
	setInterval(function(){
		if(login){
			$.ajax({
				url: ContextPath +'/getNotificationCount',
				success: function(result){
					$(".notification-count").each(function(){
						$(this).html(result);
					});
				}
			});
		}
	},10000);
	


	$("#toogle-allnotification").on("click",function(){
		var panel = $("#notification-panel").css("display");
		if (panel === 'block') {
			$("#notification-body").empty();
			$.ajax({
				url: ContextPath + '/getNotifications?type=all',
				success: function(result){
					if(result != undefined){
						var item = JSON.parse(result);
						$("#notification-body").empty();
						for(var i = 0; i< item.length; i++){
							var li = "<a class='list-group-item notification-list-item'>" +
									"<h4 class='list-group-item-heading'><span class='label label-warning'>"+ item[i].serviceType +"</span>&nbsp" + item[i].title +"<span onclick='openMessage(" + item[i].id + ")' class='glyphicon-" + item[i].id + " glyphicon glyphicon-menu-down pull-right'></span></h4>" +
									"<p class='list-group-item-text notification-list-item-text-"+item[i].id+"'>"+ item[i].message +"<br>"+ item[i].createdOn.substring(0,10) +"</p></a>";
							$("#notification-body").append(li);
							if(item[i].actionText != undefined){
								$(".notification-list-item-text-" + +item[i].id).append("<br><a class='btn btn-default' role='button' href='"+ item[i].actionUrl +"'>"+ item[i].actionText +"</a>");
							}
						}
					}
				}
			});
			$("#toogle-allnotification").remove();
		}
	});
	
	/**
	 * methods for reporting items
	 */
	$(".report-item").click(function(){
		var rdata = $(this).data();

		var text = $("#report-post-reason-" + rdata.itemid).val()
		if(rdata.type === 'account'){
			text = $("#report-user-reason-" + rdata.itemid).val()
		}
		$.ajax({
			url: ContextPath + "/ReportItem?reasons=" + text,
			data:rdata,
			success: function(success){
				popup('body',success)
				$('#reportUser-'+rdata.itemid).modal('hide')
				$('#reportItem-'+rdata.itemid).modal('hide')
			}
		})
	})
	/**
	 * for login/profile/account admin
	 */
	$("#addMember").on("click",function(){
		var item = '<input type="text" class="form-control" name="users">';
		$('#adduser').append(item);
	})
	
    $('#login').validate({ 
        rules: {
            email: {
                required: true,
                email: true
            },
            userPw: {
                required: true,
            }
        }
    });
	/**
	 * method for multi select plugin
	 */
	//$('#generate1').multiselect();
	var count =1;
	$('#drpdownlist').multiselect({            
		onChange: function(option, checked) {
			  var userId = [];
			    $('#dropdownlist :selected').each(function(i, selected){ 
			      userId[i] = $(selected).val(); 
			    });
			   count++
			;
		
                calculatorzz(count);
            }});
	
});

function paytype(type){
	switch(type) {
	case "Cash":
		type="Cash"
		$('#paytype').val(type)
		break;
	case "Online":
		type="Online"
		$('#paytype').val(type)
		break;

	}
}


function popup(id,message){
	$(id).append('<div style="position: fixed;bottom:0;right:0;margin: 0px;" class="alert alert-warning" role="alert" data-dismiss="alert">'+message+'</div>')
	setTimeout(function(){
		$('.alert.alert-warning').alert('close');
	},5000)
}
$(document).ready(function(){
    $('a[data-toggle="tab"]').on('shown.bs.tab', function(e){
        var activeTab = $(e.target).text(); // Get the name of active tab
        var previousTab = $(e.relatedTarget).text(); // Get the name of previous tab
        $(".active-tab span").html(activeTab);
        $(".previous-tab span").html(previousTab);
    });
});