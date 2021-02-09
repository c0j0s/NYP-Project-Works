// Copyright 2018 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

'use strict';

chrome.runtime.onMessage.addListener(
  function(request, sender, sendResponse) {
    if (request.method === 'open') {
      var url = request.url
      //console.log("opening "+url)
      chrome.tabs.create({ 'url': url });
      sendResponse("Done");
    }else if (request.method === 'save') {
      var data = request.data
      getGeoLocation(data['en'].name,function(text,api){
		var obj = JSON.parse(text)
		var geo = {
			'lat': '',
			'lng': ''
		}
		if(obj.results.length > 0){
			geo = obj.results[0].geometry.location
		}else{
			console.log("Google Map Error")
			console.log(api)
			console.log(obj)
		}
        data.geo_location = geo;

        var promises = [];
		promises.push(translateText(data['zh-cn'].summary));
		promises.push(translateText(data['zh-cn'].opening_hours));
		promises.push(translateText(data['zh-cn'].recommended_duration));
		promises.push(translateText(data['zh-cn'].ticket_info));
		promises.push(translateText(data['zh-cn'].transportation));

        Promise.all(promises).then(function(result) {
			// returned data is in arguments[0], arguments[1], ... arguments[n]
			data['en'].summary = parseArrayToText(JSON.parse(result[0])[0])			
			data['en'].opening_hours = parseArrayToText(JSON.parse(result[1])[0])
			data['en'].recommended_duration = parseArrayToText(JSON.parse(result[2])[0])
			data['en'].ticket_info = parseArrayToText(JSON.parse(result[3])[0])
			data['en'].transportation = parseArrayToText(JSON.parse(result[4])[0])

            sendResponse(data);
        }, function(err) {
            console.log(err)
        });
      })
    }else if (request.method === 'close') {
		chrome.tabs.remove(sender.tab.id, function() { });
	}else if (request.method === 'store') {
		chrome.storage.local.get(['data'], function(data) {
			var obj = []
			if (data.data !== undefined) {
				obj = data.data
			}

			var found = false;
			for(var i = 0; i < obj.length; i++) {
				if (obj[i].address == request.data.address) {
					found = true;
					break;
				}
			}
			// console.log(data)
			if (found === false) {
				obj.push(request.data)
				console.log(request.data)
			}
			
			chrome.storage.local.set({data:obj}, function(response) {
				sendResponse("Done");
			})
		})
	}else if (request.method === 'get') {
		chrome.storage.local.get(['data'], function(data) {
			sendResponse(data);
		})
	}
	else if (request.method === 'clear') {
		var data = []
		chrome.storage.local.set({data:data}, function(response) {
			sendResponse("Done");
		})
	}
    return true;
});

function getGeoLocation(address,callback){
	if (!address.toLowerCase().includes('singapore')) {
		address = "singapore " + address
	}
	var api = 'https://maps.googleapis.com/maps/api/geocode/json?address='+encodeURI(address.replace('(',"").replace(')',"").replace('-',"").replace('#',""))+'&key=[ACCESS_KEY]'
	var x = new XMLHttpRequest();
    x.open('GET', api);
    x.onload = function() {
        callback(x.responseText,api)
    };
	x.send();
	// callback("[]")
}

function translateText(text){
  return new Promise(function(resolve, reject) {
    var api = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=zh-cn&tl=en&dt=t&q=" +encodeURI(text)
    //console.log(api)
    var x = new XMLHttpRequest();
      x.open('GET', api);
      x.onload = function() {
        resolve(x.responseText)
	  };
	  
	x.send();
  });
}

function parseArrayToText(arr){
	var final = "";
	arr.forEach(element => {
		final = final + element[0]
	});
	return final
}