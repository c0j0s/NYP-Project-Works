//Initialize Nav/Footer
function common(index){
	<!-- nav version 2.2  -->
	$('<div class="nav-content"><div id="navImg"><a href="index.html"><img src="image/websrc/LOGO.png" alt="LOGO" /></a></div><ul><li class = "navli"><a href="index.html" ><i class="material-icons md-dark md-36">home</i><p>HOME</p></a></li><li class = "navli"><a href="product.html"><i class="material-icons md-36 md-dark">book</i><p>PRODUCT</p></a></li><li class = "navli"><a href="magazine.html"><i class="material-icons md-dark md-36">import_contacts</i><p>E-MAGAZINE</p></a></li><li class = "navli"><a href="courselist.html"><i class="material-icons md-dark md-36">school</i><p>COURSES</p></a></li><li class = "navli"><a href="redemptionGuide.html"><i class="material-icons md-dark md-36">casino</i><p>REDEMPTION</p></a></li><li class = "navli"><a href="contact.html"><i class="material-icons md-dark md-36">domain</i><p>CONTACT</p></a></li></ul><ul class="loginbutton"><li onclick="islogin('+ "'login.html'" +')"  id="profileicon"></li></ul></div>').appendTo("nav");
	loadloginNav();

	index--;
	if(index >= 0){
		var navActive = document.getElementsByClassName("navli")[index];
		navActive.className += " nav-active";
	}

	<!-- Footer version 1 -->
	$('<div><a href="https://www.facebook.com/nanyangpoly/" target="_blank"><img src="image/websrc/fb.png" alt="Facebook" /></a><a href="https://twitter.com/nyptweets?lang=en" target="_blank"><img src="image/websrc/tw.png" alt="Twitter" /></a><a href="https://www.instagram.com/nanyangpoly/" target="_blank"><img src="image/websrc/insta.png" alt="Instagram" /></a></div><div><p>180 Ang Mo Kio Avenue 8 Singapore (569830) Tel: 64515115 </br>Copyright © 2016 NYP, Singapore. All rights reserved.</p></div><div><a href="index.html">Home</a><a href="product.html">E-MAG</a><a href="contact.html">Contact Us</a></div>').appendTo("footer")
	//accessability tools
	$("<access id='access'></access>").appendTo("body");
	$('<div id="accesstool"><i onclick = "acontrast()" id="access-contrast" class="material-icons md-36 tooltip ">font_download<span class="tooltiptext">High Contrast</span></i><br /><i onclick = "aintextsize()" id="access-intextsize" class="material-icons md-36 tooltip ">exposure_plus_1	<span class="tooltiptext">Increase Text Size</span></i><i onclick = "adetextsize()" id="access-detextsize()" class="material-icons md-36 tooltip ">exposure_neg_1<span class="tooltiptext">Decrease Text Size</span></i></div><i onclick = "atogglehide()" id="access-hide" class="material-icons md-36 tooltip ">expand_more<span class="tooltiptext">Hide</span></i>').appendTo("access");
	// Initialize Firebase
	var config = {
		apiKey: "AIzaSyA7m3NHUMaIt1d3ss_p9vXQfWP5WeBD6ms",
		authDomain: "e-mag-9646d.firebaseapp.com",
		databaseURL: "https://e-mag-9646d.firebaseio.com",
		storageBucket: "e-mag-9646d.appspot.com",
		messagingSenderId: "583636736084"
	};
	if(firebase.apps.length === 0) {
		firebase.initializeApp(config);
	}
	//init week number
	firebase.database().ref("WeekNumber/LatestWeek").on('value', function(snapshot) {
		localStorage.setItem("currentWeek",snapshot.val());
});
	//accessability initialize
	localStorage.setItem("acessExpand","true");
	accessbar = document.getElementById("accesstool");
	ahide = document.getElementById("access-hide");
	tooltips = document.getElementsByClassName("tooltiptext");

	//init Contrast
	if(localStorage.getItem('ContrastSetting')=='true'){
		acontrast();
	}
}
//for accessability
var accessbar;
var ahide;
var tooltips;


function atogglehide(){
	if(localStorage.getItem("acessExpand") === "true"){
		accessbar.style.height= "0px";
		accessbar.style.overflow= "hidden";
		ahide.style.transform= "rotate(180deg)";
		for(var i = 0; i<tooltips.length; i++){
			tooltips[i].style.display = "none"
		}
		localStorage.setItem("acessExpand","false");
	}else{
		accessbar.style.height= "168px";
		accessbar.style.overflow= "none";
		ahide.style.transform= "rotate(0deg)";
		setTimeout(function(){
			for(var i = 0; i<tooltips.length; i++){
				tooltips[i].style.display = "block"
			}
		},1500);
		localStorage.setItem("acessExpand","true");
	}
}
function acontrast(){
	var a = document.getElementById("mainCss");
  a.x = 'mainInvert' == a.x ? 'main' : 'mainInvert';
  a.href = "css/"+ a.x + '.css';
	if(a.x === 'main'){
		localStorage.setItem('ContrastSetting','false');
	}else{
		localStorage.setItem('ContrastSetting','true');
	}
}
function aintextsize(){
	var fonts = ["h1","h2","h3","h4","h5","h6","p","span"];
	for(var i = 0; i<fonts.length;i++){
		var newSize = parseInt($(fonts[i]).css("font-size")) + 10;
		$(fonts[i]).css("font-size",(newSize+"px"))
	}
}
function adetextsize(){
	var fonts = ["h1","h2","h3","h4","h5","h6","p","span"];
	for(var i = 0; i<fonts.length;i++){
		var newSize = parseInt($(fonts[i]).css("font-size")) - 10;
		$(fonts[i]).css("font-size",(newSize+"px"))
	}
}
//Check if login========================================================================================================
function islogin(href){
	if(href === "login.html"){
		if(getIc() == "" || getIc() == null){
			location.href = "login.html"
		}else{
			//login success
			if(getIc() == "s0000000g"){
				location.href = "admin.html"
			}else{
				//login success for non admin account
				location.href = "profile.html"
			}

			loginSuccess();
		}
	}else if(getIc() == "" || getIc() == null){
		createPopup("isLoginPane","Please Login before preceeding", 600);
		$("<h1>You will be directed to the login page in 5 seconds</h1>").appendTo(".modal-content");
		setTimeout(function(){
			localStorage.setItem("directAfterLogin",href);
			location.href = "login.html"
		},5000);
	}else{
		location.href = href;
	}
}
function loadloginNav(){
	if(getIc() == "" || getIc() == null){
		$("#profileicon").append('<i class="material-icons md-36 md-dark">account_circle</i><p>LOGIN</p>')
	}else{
		loginSuccess()
	}

}
function loginSuccess(){
	$("#profileicon").empty();
	var imgurl = localStorage.getItem("profileImage");
	var name = localStorage.getItem("name");
	$("#profileicon").append("<div id='afterloginicon'><img src="+ imgurl +"><div class='card' id='afterloginpane'><ul><li onclick='location.href = "+"'profile.html'"+"' ><a >My Profile</a></li><li onclick='logout()'><a  style='color:red'>Sign out</a></li></ul></div></div>");

}
function logout(){
	setIc("");
	localStorage.clear();
	localStorage.href = "index.html"
}
//expand search===============================================================
function expendsearch(){
	$("#searchInput").css("width","150px");
	$("#searchInput").focus();
}
function hidesearch(x){
	setTimeout(function(){
		$("#searchInput").css("width","0px");
	},2000)
}
//Read Database========================================================================================================

function retrieveFromDB(path) {

	var dbRef = firebase.database().ref(path);

	dbRef.once('value',
	snapshot => {
		snapshot.forEach(
			function(childSnapshot){
				var childData = childSnapshot.val();

				retrieved(childData);
			});
		});
	}

	//Write Database=======================================================================================================

	function writetoDB(path,postData) {
		var dbRef = firebase.database().ref(path);
		dbRef.push(postData);
	}
	//writr user data======================================================================================================
	function writeUserData(ic, name, email, imageUrl) {
		firebase.database().ref('Account/accountInfo' + ic).set({
			username: name,
			email: email,
			profile_picture : imageUrl
		});
	}
	//Store Ic for Auth====================================================================================================

	function setIc(ic){
		localStorage.removeItem("e-mag-9646d-ic");
		localStorage.setItem("e-mag-9646d-ic",ic);
	}
	function getIc(){
		return localStorage.getItem("e-mag-9646d-ic");
	}
	//Upload to Storage====================================================================================================

	function uploadtoStorage(file,progressbar,path){
		if(file == null){
			window.alert("Error: no file selected")
		}else{
			var storageRef = firebase.storage().ref(path + "/" + file.name);

			var task = storageRef.put(file);

			task.on('state_changed',
			function progress(snapshot){
				var percentage = (snapshot.bytesTransferred/snapshot.totalBytes) * 100;
				if(progressbar != null){
					progressbar.value = percentage;
				}else{
					console.log(percentage);
				}

			},

			function error(err){

			},

			function complete() {
				createPopup("uploadComplete","E-Mag",500);
				$("<h1>Upload Complete</h1>").appendTo(".modal-content");

			}
		)
	}
}

//download from Storage================================================================================================

function downloadfromStorage(display,path){
	var pathReference  = firebase.storage().ref(path);
	pathReference.getDownloadURL().then(function(url) {
		display.src=url;
	}).catch(function(error) {
		switch (error.code) {
			case 'storage/object_not_found':
			console.log("File doesn't exist")
			break;
			case 'storage/unauthorized':
			console.log("User doesn't have permission to access the object")
			break;
			case 'storage/canceled':
			console.log("User canceled the upload")
			break;
			case 'storage/unknown':
			console.log("Unknown error occurred, inspect the server response")
			break;
		}
	});
}

//generate Random Code=================================================================================================
function RandomId(type){
	var number = Math.floor(Math.random() * (1000000 - 100000) + 100000);

	if(type === "Invoice"){
		return "I" + number;
	}else if(type === "Redemption"){
		return "R" + number;
	}
}

//for switching tab=================================================================================================
function openTab(evt, tab) {
	var i, tabcontent, tablinks;

	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}

	tablinks = document.getElementsByClassName("tablinks");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace("active", "");
	}
	document.getElementById(tab).style.display = "block";
	evt.currentTarget.className += " active";
	$('input[type=file]').css('width','210px');
}

//Creating popup
//append content only to .modal-content class
function createPopup(id,title,x){
		$("<div id ='"+id+"' class='modal' style='display:block;'><div class='card modal-panel' style='display:block;width:"+x+"px;'><div class='modal-tab'><i class='material-icons md-36 md-light error'>error</i><h1>"+title+"</h1><i class='close material-icons md-36 md-light'>close</i></div><div class='modal-content'></div></div></div>").appendTo("body");

		var modal = document.getElementById(id);
		modal.style.display = "block";
		var span = document.getElementsByClassName("close")[0];

		span.onclick = function() {
			modal.style.display = "none";
			$("#"+id+"").remove();
		}

		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
				$("#"+id+"").remove();
			}
		}
}
//generateToday's date

function getDateAndTime(){
	var d = new Date();
	var x;
	var minute;

	switch(d.getDay()) {
	case	1 : x = "Monday"; break;
	case	2 : x = "Tuesday"; break;
	case	3 : x = "Wednesday"; break;
	case	4 : x = "Thursday"; break;
	case	5 : x = "Friday"; break;
	case	6 : x = "Saturday"; break;
	case	0 : x = "Sunday"; break;
	}
	if(d.getMinutes()<10){
		minute = "0" + d.getMinutes();
	}else{
		minute = d.getMinutes();
	}
	var t = d.getFullYear() + "/" + (d.getMonth()+1) + "/" + d.getDate() + "," + x +" "+d.getHours() + ":" + minute;

	return t;
}
