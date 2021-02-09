// Copyright 2018 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

'use strict';

let changeColor = document.getElementById('exportFiles');
let clearbtn = document.getElementById('clear');
let preview = document.getElementById('preview');
let links_to_run = document.getElementById('urls');
let startAutomation = document.getElementById('startAutomation');
let status = document.getElementById('status');

chrome.runtime.sendMessage({method:'get'}, function(data) {
  console.log(data)
  changeColor.download = "output.json";
  changeColor.href = "data:text/json," + JSON.stringify(data.data, null, "\t");
  preview.innerHTML = JSON.stringify(data.data, null, "\t");
})

clearbtn.addEventListener('click',function(){
  chrome.runtime.sendMessage({method:'clear'}, function(data) {})
})

startAutomation.addEventListener('click',function(){
  var urls = links_to_run.value.replace(" ","")
  var list = urls.split('\n')
  console.log(list)

  for (let i=0; i<list.length; i++) {
    setTimeout( function timer(){
      chrome.runtime.sendMessage({method:'open',url: list[i]}, function(response) {
        status.innerHTML = "opened - " + list[i]
      });
      //status.innerHTML = "next - " + list[i]
    }, i*50000 + Math.floor((Math.random() * 40000) + 1) );
  }
})