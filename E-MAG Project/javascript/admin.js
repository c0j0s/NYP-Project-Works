function addCourse() {

		var cName = document.getElementById('cName').value;
		var cDescription = document.getElementById('cDescription').value;
		var cDate = document.getElementById('cDate').value;
		var cLocation = document.getElementById('cLocation').value;
		var cFee = document.getElementById('cFee').value;
		var cVacancies = document.getElementById('cVacancies').value;
		var cContact = document.getElementById('cContact').value;

		var file = document.getElementById('file2').files[0];
		var progressbar = document.getElementById('progress2');
		var path = "CourseListImages";

		uploadtoStorage(file,progressbar,path);
		setTimeout(function(){
	 		firebase.storage().ref(path+"/"+file.name).getDownloadURL().then(function(url) {
				firebase.database().ref('CourseList/' + cName).set({
					 ContactNumber: cContact,
					 Coursename: cName,
					 Date: cDate,
					 Description: cDescription,
					 Fee: cFee,
					 Location: cLocation,
					 Vacancies: cVacancies,
					 url:url
			 });
			 });
		},5000)

}

function RetrieveData(){
	Week = localStorage.getItem('currentWeek');
	var magazine = firebase.database().ref("MagazineReleases");
	var redemption = firebase.database().ref("LuckyDrawPrize/"+Week);
	var course = firebase.database().ref("CourseList");
	var counter = 0;
	magazine.once('value',function(snapshot){
		snapshot.forEach(function(childsnaphot){
			var childData = childsnaphot.val();
			$("#manage-magazine").prepend("<li id='m"+counter+"'>"+childData.week+"<i onclick='mremove("+counter+',"'+childsnaphot.key+'"'+")' class='material-icons md-36'>close</i></li>");
			counter++;
		});
	});
	var counter1 = 0;
	redemption.once('value',function(snapshot){
		snapshot.forEach(function(childsnaphot){
			var childData = childsnaphot.val();

			$("#manage-redemption").prepend("<li id='r"+counter1+"'>"+childData.Name+"<i onclick='rremove("+counter1+',"'+childsnaphot.key+'"'+")' class='material-icons md-36'>close</i></li>");
			counter1++;
		});
	});
	var counter2 = 0;
	course.once('value',function(snapshot){
		snapshot.forEach(function(childsnaphot){
			var childData = childsnaphot.val();

			$("#manage-courses").prepend("<li id='c"+counter2+"'>"+childData.Coursename+"<i onclick='cremove("+counter2+',"'+childsnaphot.key+'"'+")' class='material-icons md-36'>close</i></li>");
			counter2++;
		});
	});
}
var adminConfirmDelete = false;
function mremove(index,key){
	removeAlert(function(){
		console.log(adminConfirmDelete)
		if(adminConfirmDelete){
			console.log(adminConfirmDelete)
			var path = "MagazineReleases/" + key;
			firebase.database().ref(path).remove();
			$("#m"+index).remove();
		}
	});
}
function rremove(index,key){
	removeAlert(function(){
		if(adminConfirmDelete){
			var path = "LuckyDrawPrize/week16/"+ key;
			firebase.database().ref(path).remove();
			$("#r"+index).remove();
		}
	});

}
function cremove(index,key){
	removeAlert(function(){
		if(adminConfirmDelete){
			var path = "CourseList/" + key;
			firebase.database().ref(path).remove();
			$("#c"+index).remove();
		}
	});
}
function removeAlert(callback){
	createPopup("adminAlert","Warning",500);
	$("<h1>Confirm Delete</h1><button class='left card-button-float' id='yesbtn'>Yes</button><button class='card-button-float' id='nobtn' false'>No</button>").appendTo(".modal-content");

	$("#yesbtn").click(function(){
		adminConfirmDelete=true;
		$("#adminAlert").remove();
		callback();
	});
	$("#nobtn").click(function(){
		$("#adminAlert").remove();
	});
}
// add magazine==========================================================
var Week;
var T;
var ST;
function uploadmaga(){
	validateWeekinput(function(){
		Week = "week" + document.getElementById("week").value;
 	 weekdata = "week " + document.getElementById("week").value;
 	 T = document.getElementById("title").value;
 	 ST = document.getElementById("subTitle").value;

 	var file = document.getElementById('file0').files[0];
 	var progressbar = document.getElementById('progress0');
 	var path = "MagazineReleasesImages/" + Week;

 	uploadtoStorage(file,progressbar,path);
 	setTimeout(function(){
  		firebase.storage().ref(path+"/"+file.name).getDownloadURL().then(function(url) {
 			 firebase.database().ref('MagazineReleases/' + Week).set({
 					 page1:url,
 					 subTitle:ST,
 					 title:T,
 					 week:weekdata
 			 });
 			 firebase.database().ref("WeekNumber").set({
 					 LatestWeek:Week
 			 });
 		 });
 	},5000)
	});
}
//add prizes==============================================================
function uploadprizeitem(){
	validateWeekinput(function(){
		Week = "week" + document.getElementById("week").value;
		var name = $("#iname").val();
		var desc = $("#idescription").val();
		var value = $("#ivalue").val();
		var code = $("#icode").val();
		var index = $( "#prize-type" ).val();

		var file = document.getElementById('file1').files[0];
		var progressbar = document.getElementById('progress1');
		var path = "RedemptionPrizesImages/LuckyDraw/" + Week;

		uploadtoStorage(file,progressbar,path);
		setTimeout(function(){
		firebase.storage().ref(path+"/"+file.name).getDownloadURL().then(function(url) {
			firebase.database().ref('LuckyDrawPrize/' + Week + "/" + index).set({
					Name:name,
					Description:desc,
					Url:url,
					Worth:value,
					code:code
			});
		});
	},5000);
});
}
function validateWeekinput(callback){
	try{
		if($("#week").val() == ""){
			throw "Please Enter Week Number";
		}
		else if($("#week").val() == currentWk){
			throw "Please Enter a valid Week Number";
		}else{
			callback();
		};
	}catch(err){
		createPopup("weekNo","Alert",500);
		$("<h1>"+ err +"</h1>").appendTo('.modal-content');
		setTimeout(function(){
			$("weekNo").remove();
		},5000)
	}
}
