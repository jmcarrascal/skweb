function ButtonCollection() {
	this.captions = new Array();
	this.actions = new Array();
};

ButtonCollection.prototype.addButton = function(caption,action){
	this.captions[this.captions.length] = caption;
	this.actions[this.actions.length] = action;
}

function MessageBox(type,buttons) {
	if (arguments.length == 0){
		this.type = null;
		this.buttons = null;
	}else{
		this.type = type;
		this.buttons = buttons;
	}
};

function closePopupWind(backId,popupId){  
	document.getElementById(backId).parentNode.removeChild(document.getElementById(backId));
	document.getElementById(popupId).parentNode.removeChild(document.getElementById(popupId));
	var elems=document.getElementsByTagName('applet');
	for (var i=0;i<elems.length;i++){
		elems[i].style.display='';	
	}
}

MessageBox.prototype.showMessage = function(text,mssId,width,height){
    var w = ( width != null )?Math.max(width,372):372;
    var h = ( height != null )?Math.max(height,160):160;
    var bd = document.getElementsByTagName("body");
   
    darkBackgroundID="darkBackground"+(Math.random()*100)
	setDarkBackground(darkBackgroundID);
//   inner ='<div id="darkBackground" class="darkBackground"></div>';
//   inner = inner + '<div id="'+mssId+'" class="popup" style="width:'+w+'px;height:'+h+'px;">';
   inner = '<span class="mss_ul"></span><span class="mss_u" style="width:'+(w-20)+'px;"></span><span class="mss_ur"></span><div class="mss_l" style="height:'+(h-20)+'px;"></div>';
   inner = inner + '<div class="mss_center" style="width:'+(w-20)+'px;height:'+(h-20)+'px;">';

   inner = inner + '<div><a href="#" onclick="closePopupWind(\''+darkBackgroundID+'\',\''+mssId+'\');" class="close_button" style="float:right;"></a></div>';
   if (this.type != null){
   		inner = inner + '<img src="images/messages/'+this.type+'.png" style="margin:12px 0 0 12px;float:left;"/>';
   	}
   inner = inner + '<div style="text-align:center;margin:12px auto 12px auto;"><br>'+ text +'<br></div>';
//   inner = inner + '<div class="separator"></div>'
   //buttons
   for (i = 0; i < this.buttons.captions.length; i++){
   		inner = inner + '<a href="#" onclick="'+this.buttons.actions[i]+';closePopupWind(\''+darkBackgroundID+'\',\''+mssId+'\');" class="button size64" style="width:25%;float:right;margin-right:5px;"><span>'+this.buttons.captions[i]+'</span></a>';
   }
// inner = inner + '<div class="separator"></div>'
   inner = inner + '</div>';
   
   inner = inner + '<div class="mss_r" style="height:'+(h-20)+'px;"></div><div class="mss_dl"></div><div class="mss_d" style="width:'+(w-20)+'px;"></div><div class="mss_dr"></div>';
//   inner = inner + '</div>';
	var mssDiv=document.createElement("div");
	mssDiv.className = "popup";
	if (navigator.appName=="Microsoft Internet Explorer" && navigator.appVersion=="4.0 (compatible; MSIE 6.0; Windows NT 5.1; InfoPath.2)")
		mssDiv.className=mssDiv.className+"IE6"
	mssDiv.id = mssId;
	mssDiv.style.width = w + "px";
	mssDiv.style.height = h + "px";
	mssDiv.style.marginLeft = "-"+ (w/2) + "px";
	mssDiv.style.marginTop = "-"+ (h/2) + "px";
	mssDiv.innerHTML = inner;
	bd[0].appendChild(mssDiv);
   
   //bd[0].innerHTML = inner + bd[0].innerHTML; 
}

MessageBox.prototype.showModal = function(text,mssId,width,height){

	var elems = document.getElementsByTagName('applet');
	for (var i=0;i<elems.length;i++){
		elems[i].style.display='none';	
	}

	this.showMessage(text,mssId,width,height);
}   

function showConfirmMessage(text,action,mssId,width,height){
	var bc = new ButtonCollection();
	bc.addButton(buttonNo,"");
	bc.addButton(buttonYes,action);
	var mss = new MessageBox("alert",bc);
	mss.showModal(text,mssId,width,height);
}

function showInfoMessage(text,action,mssId,width,height){
	var bc = new ButtonCollection();
	bc.addButton(buttonYes,action);
	var mss = new MessageBox("info",bc);
	mss.showModal(text,mssId,width,height);
}

function showConfirmMessage_TwoActions(text,actionYes,actionNo,mssId,width,height){
	var bc = new ButtonCollection();
	bc.addButton(buttonNo,actionNo);
	bc.addButton(buttonYes,actionYes);
	var mss = new MessageBox("alert",bc);
	mss.showModal(text,mssId,width,height);
}

function showSelectMessage(text, select,action,mssId,width,height){
	var bc = new ButtonCollection();
	bc.addButton(buttonCancel,"");
	bc.addButton(buttonOk,action);
	var mss = new MessageBox("question",bc);
	mss.showModal(text+'<br>'+select,mssId,width,height);
}


function showSignerPinMessage(text,action,mssId){
	var bc = new ButtonCollection();
	bc.addButton(buttonCancel,"");
	bc.addButton(buttonOk,action);
	var mss = new MessageBox("question",bc);
	mss.showModal(text,mssId);

}

function showSignerSelectorMessage(text,action,mssId){
	var bc = new ButtonCollection();
	bc.addButton(buttonCancel,"");
	bc.addButton(buttonOk,action);
	var mss = new MessageBox("question",bc);
	mss.showModal(text,mssId,480,150);

}

