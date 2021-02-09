function writeUserData() {
		
		var database = firebase.database();
		var mName = document.getElementById('mName').value;
		var mEmail = document.getElementById('mEmail').value;
		var mMessage = document.getElementById('mMessage').value;
		
		if((mName != "") && (mEmail != "") && (mMessage != "")) {
			firebase.database().ref('EnquiryMessage/' + mName).set({
				Name: mName,
				Email: mEmail,
				Message: mMessage
			});
		}
		
		window.scrollTo(0);
	}