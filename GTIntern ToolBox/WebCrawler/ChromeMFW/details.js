console.log("wait first")
setTimeout(() => {
    var title = document.querySelectorAll('.title h1')[0].innerText
    var title_en = document.querySelectorAll('.title .en')[0].innerText

    var summary = ""
    var tel = ""
    var url = ""
    var recommendedDuration = ""
    var transport = ""
    var tickets = ""
    var openingHours = ""
    var address = ""

    if (document.querySelector('.summary') !== null) {
        summary = document.querySelector('.summary').innerText
    }else if(document.querySelector('.main-detail dd') !== null){
        summary = document.querySelector('.main-detail dd').innerText
    }else{
        summary = "";
    }

    if (document.querySelector('.tel .content') !== null) {
        tel = document.querySelector('.tel .content').innerText
    } else if(document.querySelector('.item-tel .content') !== null){
        tel = document.querySelector('.item-tel .content').innerText
    }else{
        tel = "";
    }

    if (document.querySelector('.item-site .content') !== null) {
        url = document.querySelector('.item-site .content').innerText
    } else {
        url = "";
    }

    if (document.querySelector('.item-time .content') !== null) {
        recommendedDuration = document.querySelector('.item-time .content').innerText
    }else {
        recommendedDuration = "";
    }

    var dtList = document.querySelectorAll('dl dt')
    for (let index = 0; index < dtList.length; index++) {
        const title = dtList[index].innerText;
        const value = document.querySelectorAll('dl dd ')[index].innerText
        if (title === "简介") {
            summary = value
        } else if(title === "交通"){
            transport = value
        } else if(title === "门票"){
            tickets = value
        } else if(title === "开放时间"){
            openingHours = value
        }
    }

    if (document.querySelector('.mod-location .sub') !== null) {
        address = document.querySelector('.mod-location .sub').innerText;
    } else if(document.querySelector('.address') !== null){
        address = document.querySelector('.address').innerText;
    }else{
        address = "";
    }

    var data = {
        'zh-cn':{
            'name':title,
            'summary':summary,
            'recommended_duration':recommendedDuration,
            'transportation':transport,
            'ticket_info': tickets,
            'opening_hours':openingHours,
        },
        'en':{
            'name':title_en,
            'summary':'',
            'recommended_duration':'',
            'transportation':'',
            'ticket_info': '',
            'opening_hours':'',
        },
        'address':address,
        'website':url,
        'telephone':tel,
        'country': 'Singapore',
        'city': 'Singapore',
        'geo_location':{
            'lat':'',
            'lng':'',
        }
    }
    chrome.runtime.sendMessage({method:'save',data: data}, function(response) {
        console.log('after');
        console.log(response);
        chrome.runtime.sendMessage({method:'store',data:response}, function(response) {
            console.log(response)
            chrome.runtime.sendMessage({method:'close'}, function(response) {
                
            })
        })
    });
}, 3000);
