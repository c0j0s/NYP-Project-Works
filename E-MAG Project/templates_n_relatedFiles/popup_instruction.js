//Example

//import common.js

//para(id,title,width)
createPopup("myModal","Confirmation",500);

//content appendTo
//append content only to .modal-content class
  //for css reason
$("<p>Confirm Redemption For "+ itemName[index] +" Voucher.</p>").appendTo( ".modal-content" );
$("<button class = 'myBtn card-button-float' >Yes</button>").appendTo( ".modal-content" );
$("<button class = 'myBtn card-button-float' >No</button>").appendTo( ".modal-content" );

//custom action for the content appended inside
var ybtn = document.getElementsByClassName("myBtn")[0];
var nbtn = document.getElementsByClassName("myBtn")[1];


ybtn.onclick = function(){
  calculate(Points,Cost);
  modal.style.display = "none";
  $("#myModal").remove();
}

nbtn.onclick = function(){
  modal.style.display = "none";
  $("#myModal").remove();
}

//tested with redemptionPage.html
