function login(){

		$( "#errorMsg" ).empty();

		var username = document.getElementById('ic').value.toLowerCase();
		var pw = document.getElementById('password').value;

		return firebase.database().ref('user/' + username + '/AccountInfo').once('value').then(function(snapshot) {
			if(snapshot.val()== null){
				$( "#errorMsg" ).append( "<p style='color:red;'>Please sign up and try again</p>" );
			}

			else if(snapshot.val().password == pw) {

				if (typeof(Storage) !== "undefined") {
					// Store
					localStorage.setItem("name", snapshot.val().name);
					localStorage.setItem("email", snapshot.val().email);
					localStorage.setItem("dob", snapshot.val().dob);
					localStorage.setItem("address", snapshot.val().address);
					localStorage.setItem("gender", snapshot.val().gender);
					localStorage.setItem("number", snapshot.val().number);
					localStorage.setItem("profileImage", snapshot.val().profileImage);
					setIc(username);

					if(username == "s0000000g"){
						window.location.href= "admin.html";
					}
					else{
						window.location.href= "profile.html";
					}
				}
				else {
					alert( "Sorry, your browser does not support Web Storage...");
				}
			}
			else {
				$( "#errorMsg" ).append( "<p  style='color:red;'>Wrong IC Number or Password</p>" );
			}
		});
}

var nric = "";
var password = "";
function check() {

	$( ".errorNRIC" ).empty();
	$( ".errorPw" ).empty();

	if (document.getElementById('password1').value == document.getElementById('password2').value) {
				password = document.getElementById('password1').value;
				document.getElementById('password1').style.borderColor = "white";
				document.getElementById('password2').style.borderColor = "white";
	}
	else {
		$( ".errorPw" ).append("<p  style='color:red;'>Password Mismatch</p>");
		document.getElementById('password1').style.borderColor = "red";
		document.getElementById('password2').style.borderColor = "red";
		password = "";
	}

	return firebase.database().ref('user/' + document.getElementById('nric').value.toLowerCase()).once('value').then(function(snapshot) {
		if(snapshot.val()!= null){
			$( ".errorNRIC" ).append("<p  style='color:red;'>NRIC number had been registered</p>");
			nric = "";
			document.getElementById('nric').style.borderColor = "red";
		}
		else {

			nric = document.getElementById('nric').value.toLowerCase();
			document.getElementById('nric').style.borderColor = "white";
		}
	});
}


var storageRef;
var downloadURL ="";
function addUserImage() {
	console.log(nric);
	var selectedFile = document.getElementById('pImage').files[0];
	storageRef = firebase.storage().ref('User/' + nric + '/' + selectedFile.name);
	var uploadTask = storageRef.put(selectedFile);
	var progress = document.getElementById('loginprogress').value;
	uploadTask.on('state_changed', function(snapshot){

		progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
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
		}, function() {
			downloadURL = uploadTask.snapshot.downloadURL;
			addUser();
		});
}



function addUser(){

		var radios = document.getElementsByName('gender');
		var gender = "";
		for (var i = 0, length = radios.length; i < length; i++) {
			if (radios[i].checked) {
				gender = radios[i].value;
				break;
			}
		}

		var name = document.getElementById('name').value;
		var dob = document.getElementById('dob').value;
		var email = document.getElementById('email').value;
		var number = document.getElementById('number').value;
		var address = document.getElementById('address').value;
		var sQuestion = document.getElementById('sQuestion').value;
		var answer = document.getElementById('answer').value.toLowerCase();

		console.log(name,dob,email,number,address,gender,password,downloadURL,nric,sQuestion, answer);
	if((nric !== "") && (name !== "") && (dob !== "") && (email !== "") && (number !== "") && (address !== "") && (gender !== "") && (password !== "") && (sQuestion !== "") && (answer !== "") && (downloadURL !== "")) {
		firebase.database().ref('user/' + nric + '/AccountInfo').set({
				name: name,
				dob: dob,
				email: email,
				number: number,
				address: address,
				gender: gender,
				password: password,
				securityQuestion: sQuestion,
				answer: answer,
				profileImage: downloadURL
		});

		firebase.database().ref('user/' + nric + '/Redemption/RedemptionPoints/').set({
				Points: 200
		});


		document.getElementById('nric').style.borderColor = "white";
		document.getElementById('password1').style.borderColor = "white";
		document.getElementById('password2').style.borderColor = "white";
		$( ".errorNRIC" ).empty();
		document.getElementById("fSignup").reset();
	}
	createPopup('signupcomplete',"Successful",500);
	$('<h1>You will be directed to login page in 5 seconds</h1>').appendTo('.modal-content');
	setTimeout(function(){
		window.location.href="login.html";
	},5000);
}

function popup(){
     //para(id,title,width)
     createPopup("myModal","Forget password ?",500);
	 $("<div id='fNRIC'><label>Enter NRIC number:</label><br><input type='text' id='forgetNric' name='forgetNric' placeholder='E.g. S1234567A' required/><br><br><span class='errorNRIC'/><button style='float:right;' class = 'myBtn card-button-float' onclick='checkNRIC();'>Next</button></div>").appendTo( ".modal-content" );
	 $("<div id='cPw' style='display:none;'><form action='#' method='post' onsubmit='ansCheck(); return false;'><label id='qns'></label><br><input type='text' id='qnsAns' name='qnsAns' required/><br><span class='errorAns'></span><br><label>Password:</label><br><input type='password' id='nPassword1' name='nPassword1' pattern='(?=^.{6,}$)(?=.*\d)(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$'  title='Minimum 6 character with 1 lower case letter [a-z], 1 upper case letter [A-Z], 1 numeric character [0-9]' placeholder='E.g. P@ssw0rd' required/><br><span class='errorPw'></span><br><label>Re-type Password:</label><br><input type='password' id='nPassword2' name='nPassword2' pattern='(?=^.{6,}$)(?=.*\d)(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$'  title='Minimum 6 character with 1 lower case letter [a-z], 1 upper case letter [A-Z], 1 numeric character [0-9]' placeholder='E.g. P@ssw0rd' required/><br><span class='errorPw'></span><br><button style='float:right;'  class = 'myBtn card-button-float' onclick='ansCheck();'>Submit</button></form></div>").appendTo( ".modal-content" );
 }

 function checkNRIC(){
	$( ".errorNRIC" ).empty();

	var cNRIC = document.getElementById('forgetNric').value.toLowerCase();

	return firebase.database().ref('user/' + cNRIC).once('value').then(function(snapshot) {
		if(snapshot.val()== null){
			$( ".errorNRIC" ).append("<p  style='color:red;'>NRIC number not registered !!!</p>");
		}
		else if(cNRIC == "") {
			$( ".errorNRIC" ).append("<p  style='color:red;'>Please enter NRIC number</p>");
		}
		else {
			return firebase.database().ref('user/' + document.getElementById('forgetNric').value.toLowerCase() + '/AccountInfo').once('value').then(function(snapshot) {
				$("#qns").text(snapshot.val().securityQuestion);
				document.getElementById("fNRIC").style.display = "none";
				document.getElementById("cPw").style.display = "block";
			});
		}
	});
}

function ansCheck() {
	$( ".errorPw" ).empty();
	$( ".errorAns" ).empty();

	var ans = document.getElementById('qnsAns').value.toLowerCase();

	if(document.getElementById('nPassword1').value !== document.getElementById('nPassword2').value) {
		$( ".errorPw" ).append("<p  style='color:red;'>Password Mismatch</p>");
		if(ans == "") {
			$( ".errorAns" ).append("<p  style='color:red;'>Please enter your answer</p>");
		}
	}

	else if(document.getElementById('nPassword1').value == "" || document.getElementById('nPassword2').value == "") {
		$( ".errorPw" ).append("<p  style='color:red;'>Please enter new password</p>");
		if(ans == "") {
			$( ".errorAns" ).append("<p  style='color:red;'>Please enter your answer</p>");
		}
	}
	else {
		return firebase.database().ref('user/' + document.getElementById('forgetNric').value.toLowerCase() + '/AccountInfo/').once('value').then(function(snapshot) {
				if(ans == snapshot.val().answer){
					newPassword();
				}
				else {
					$( ".errorAns" ).append("<p  style='color:red;'>Wrong answer</p>");
				}
			});
	}
}

function newPassword() {
	firebase.database().ref('user/' + document.getElementById('forgetNric').value.toLowerCase() + '/AccountInfo').update({
		password: document.getElementById('nPassword1').value
	});
	alert("Sucessfully change");
	location.reload();
}
