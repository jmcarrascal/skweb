/***************************/
//@Author: Adrian "yEnS" Mato Gondelle
//@website: www.yensdesign.com
//@email: yensamg@gmail.com
//@license: Feel free to use it, but keep this credits please!					
/***************************/

//SETTING UP OUR POPUP
//0 means disabled; 1 means enabled;
var popupStatus = 0;

var popupStatusB = 0;

var popupStatusC = 0;
//loading popup with jQuery magic!
function loadPopup(){
	//loads popup only if it is disabled
	$("#menu").hide();
	if(popupStatus==0){
		$("#backgroundPopup").css({
			"opacity": "0.7"
		});
		$("#backgroundPopup").fadeIn("slow");
		$("#popupContact").fadeIn("slow");
		popupStatus = 1;
	}
}

//loading popup with jQuery magic!
function loadPopupB(){
	//loads popup only if it is disabled
	$("#menu").hide();
	if(popupStatusB==0){
		$("#backgroundPopupB").css({
			"opacity": "0.7"
		});
		$("#backgroundPopupB").fadeIn("slow");
		$("#popupContactB").fadeIn("slow");
		popupStatusB = 1;
	}
}

//loading popup with jQuery magic!
function loadPopupC(){
	//loads popup only if it is disabled
	$("#menu").hide();
	if(popupStatusC==0){
		$("#backgroundPopupC").css({
			"opacity": "0.7"
		});
		$("#backgroundPopupC").fadeIn("slow");
		$("#popupContactC").fadeIn("slow");
		popupStatusC = 1;
	}
}

function showPopup(nombrePopUp){
	this.nombrePopUp = nombrePopUp;
	centerPopup();
	//load popup
	loadPopup();
}

function showPopupB(){
	
	centerPopupB();
	//load popup
	loadPopupB();
}


function showPopupC(){
	
	centerPopupC();
	//load popup
	loadPopupC();
}

//disabling popup with jQuery magic!
function disablePopup(){
	$("#menu").show();
	//disables popup only if it is enabled
	if(popupStatus==1){
		$("#backgroundPopup").fadeOut("slow");
		$("#popupContact").fadeOut("slow");
		popupStatus = 0;
	}
}

function disablePopupB(){
	//disables popup only if it is enabled
	$("#menu").show();
	if(popupStatusB==1){
		$("#backgroundPopupB").fadeOut("slow");
		$("#popupContactB").fadeOut("slow");
		popupStatusB = 0;
	}
}

function disablePopupC(){
	//disables popup only if it is enabled
	$("#menu").show();
	if(popupStatusC==1){
		$("#backgroundPopupC").fadeOut("slow");
		$("#popupContactC").fadeOut("slow");
		popupStatusC = 0;
	}
}

//centering popup
function centerPopup(){
	//request data for centering
	var windowWidth = document.documentElement.clientWidth;
	var windowHeight = document.documentElement.clientHeight;
	var popupHeight = $("#popupContact").height();
	var popupWidth = $("#popupContact").width();
	//centering
	$("#popupContact").css({
		"position": "absolute",
		"z-index":"0",
		"top": windowHeight/2-popupHeight/2,
		"left": windowWidth/2-popupWidth/2
	});
	//only need force for IE6
	
	$("#backgroundPopup").css({
		"height": windowHeight
	});
	
}

function centerPopupB(){
	//request data for centering
	var windowWidth = document.documentElement.clientWidth;
	var windowHeight = document.documentElement.clientHeight;
	var popupHeight = $("#popupContactB").height();
	var popupWidth = $("#popupContactB").width();
	//centering
	$("#popupContactB").css({
		"position": "absolute",
		"top": windowHeight/2-popupHeight/2,
		"left": windowWidth/2-popupWidth/2
	});
	//only need force for IE6
	
	$("#backgroundPopupB").css({
		"height": windowHeight
	});
	
}

function centerPopupC(){
	//request data for centering
	var windowWidth = document.documentElement.clientWidth;
	var windowHeight = document.documentElement.clientHeight;
	var popupHeight = $("#popupContactC").height();
	var popupWidth = $("#popupContactC").width();
	//centering
	$("#popupContactC").css({
		"position": "absolute",
		"visibility":"visible z-index:1",
		"top": windowHeight/2-popupHeight/2,
		"left": windowWidth/2-popupWidth/2
	});
	//only need force for IE6
	
	$("#backgroundPopupC").css({
		"height": windowHeight
	});
	
}

//CONTROLLING EVENTS IN jQuery
$(document).ready(function(){
	
	//LOADING POPUP
	//Click the button event!
	$("#button").click(function(){
		//centering with css
		centerPopup();
		//load popup
		loadPopup();
	});
	
	$("#buttonB").click(function(){
		//centering with css
		centerPopupB();
		//load popup
		loadPopupB();
	});
				
	$("#buttonC").click(function(){
		//centering with css
		centerPopupC();
		//load popup
		loadPopupC();
	});

	//CLOSING POPUP
	//Click the x event!
	$("#popupContactClose").click(function(){
		disablePopup();
	});
	//CLOSING POPUP
	//Click the x event!
	$("#popupContactCloseB").click(function(){
		disablePopupB();
	});

	$("#popupContactCloseC").click(function(){
		disablePopupC();
	});

	//Click out event!
	$("#backgroundPopup").click(function(){
		disablePopup();
	});
	//Press Escape event!
	$(document).keypress(function(e){
		if(e.keyCode==27 && popupStatus==1){
			disablePopup();
		}
	});

	//Click out event!
	$("#backgroundPopupB").click(function(){
		disablePopupB();
	});

	$("#backgroundPopupC").click(function(){
		disablePopupC();
	});

});