Array.prototype.firstOccurrence=function(s){
  for(i=0;i<this .length;i++){
    if(s==this[i]) return i;
  }
  return -1;
}

Array.prototype.removeAll=function(s){
  for(i=0;i<this .length;i++){
    if(s==this[i]) this.splice(i, 1);
  }
}

var formChangeMonitor = new Array();
var obligatoryFields = new Array();
var onlyNumberFields = new Array();
var emailFields = new Array();
var dateFields = new Array();

function addFormChangeMonitor(idForm){
	var form=document.getElementById(idForm);
	for (var i=0; i < form.length; i++){
		var element = form.elements[i];
		var attrib = "changeHappen('"+idForm+"')" + ( (element.getAttributeNode("onchange")!=null)? element.getAttributeNode("onchange").value:"");
		if (window.execScript) {
    		element.onchange = new Function(attrib);//IE
  		}else{
			element.setAttribute("onchange",attrib);//FireFox
		}
	}
} 

function changeHappen(idForm){
	formChangeMonitor[formChangeMonitor.length] = idForm;
}

function resetChanges(){
	formChangeMonitor = new Array();
}

function hasChanged(idForm){
	return (formChangeMonitor.firstOccurrence(idForm) != -1);
}

function someHasChanged(){
	return (formChangeMonitor.length > 0);
}

function addUnmarkEvent(element){
	var attrib = "unMark(this);"+((element.getAttributeNode("onfocus")!=null)? element.getAttributeNode("onfocus").nodeValue:"");
	if (window.execScript) {
		if (attrib == null){
	    	attrib = element.onfocus;
	    	attrib = "unMark(this);"+attrib.replace(/function/g,"").replace(/anonymous\(\)/g,"").replace(/{/g,"").replace(/}/g,"");
		}
    	element.onfocus = new Function(attrib); //IE
  	}else{
		element.setAttribute("onfocus",attrib);//FireFox
	}
}

function createNode(formId,fieldId){
	var node = new Array(2);
	node[0] = formId;
	node[1] = fieldId;
	return node;
}

function addObligatoryField(formId,fieldId){
	obligatoryFields[obligatoryFields.length] = createNode(formId,fieldId);
	addUnmarkEvent(document.getElementById(fieldId));
}

function addOnlyNumberField(formId,fieldId){
	onlyNumberFields[onlyNumberFields.length] = createNode(formId,fieldId);
	addUnmarkEvent(document.getElementById(fieldId));
}

function addEMailField(formId,fieldId){
	emailFields[emailFields.length] = createNode(formId,fieldId);
	addUnmarkEvent(document.getElementById(fieldId));
}

function addDateField(formId,fieldId){
	dateFields[dateFields.length] = createNode(formId,fieldId);
	addUnmarkEvent(document.getElementById(fieldId));
}

function validateObligatoryFields(formId){
	some=false;
	for (i=0;i<obligatoryFields.length;i++){
		if (obligatoryFields[i][0] == formId){
			var inputElement = document.getElementById(obligatoryFields[i][1]);
			if( inputElement.value == null || inputElement.value.length == 0 || /^\s+$/.test(inputElement.value) ) {
				mark(inputElement);
				some=true;
			}
		}
	}
	return(!some);
}

function areOnlyDigits(num){
return (/^([0-9])*$/.test(num));
}

function isANumber(num){
return (isNaN(num));
}

function trim(myString)
{
	return myString.replace(/^\s+/g,'').replace(/\s+$/g,'')
}

function validateOnlyNumberFields(formId){
	some=false;
	for (i=0;i<onlyNumberFields.length;i++){
		if (onlyNumberFields[i][0] == formId){
			document.getElementById(onlyNumberFields[i][1]).value=trim(document.getElementById(onlyNumberFields[i][1]).value);
			var inputElement = document.getElementById(onlyNumberFields[i][1]);
			if (!areOnlyDigits(inputElement.value)){
				mark(inputElement);
				some=true;
			}
		}
	}
	return(!some);
}

function validateMailFields(formId){
	some=false;
	for (i=0;i<emailFields.length;i++){
		if (emailFields[i][0] == formId){
			var inputElement = document.getElementById(emailFields[i][1]);
			if( inputElement.value.length != 0 && 
				!(/\w{1,}[@][\w\-]{1,}([.]([\w\-]{1,})){1,3}$/.test(inputElement.value)) ) {
				mark(inputElement);
				some=true;
			}
		}	
	}
	return(!some);
}

function validateDateFields(formId){
	some=false;
	for (i=0;i<dateFields.length;i++){
		if (dateFields[i][0] == formId){
			var inputElement = document.getElementById(dateFields[i][1]);
			if( inputElement.value.length != 0 && !validDate(inputElement.value)) {
				mark(inputElement);
				some=true;
			}
		}	
	}
	return(!some);
}

function validateFieldsWithoutMessage(formId){
	isValid = validateObligatoryFields(formId) && validateOnlyNumberFields(formId);
	isValid = isValid && validateMailFields(formId) && validateDateFields(formId);
	return (isValid);
}

function validateFields(formId){
	var message = ""; 
	//La condición está separada para que evalúe todo
	isValid = validateObligatoryFields(formId);
	message = (!isValid)?missingFieldsMessage:"";
	isValid = validateOnlyNumberFields(formId);
	message = (!isValid && message == "")?onlyNumberFieldsMessage:((!isValid)?generalFillFieldsMessage:message);
	isValid = validateMailFields(formId);
	message = (!isValid && message == "")?emailFieldsMessage:((!isValid)?generalFillFieldsMessage:message);
	isValid = validateDateFields(formId);
	message = (!isValid && message == "")?dateFieldsMessage:((!isValid)?generalFillFieldsMessage:message);
	if (message!=""){
			var bc = new ButtonCollection();
			bc.addButton(buttonOk,"");
			var mss = new MessageBox("stop",bc);
			mss.showModal(message,"popup");
	}
	return(message=="");
}

function mark(element){
	element.style.background="#ff9999";
}

function unMark(element){
	element.style.background="#ffffff";
}

function unMarkAll(idForm){
	var form = document.getElementById(idForm);
	for (var i=0; i < form.length; i++){
		unMark(form.elements[i]);
	}
}

function setDarkBackground(bgId){
    var bd=document.getElementsByTagName("body");
	var newDiv=document.createElement("div");
	newDiv.className="darkBackground";
	if (navigator.appName=="Microsoft Internet Explorer" && navigator.appVersion=="4.0 (compatible; MSIE 6.0; Windows NT 5.1; InfoPath.2)")
		newDiv.className=newDiv.className+"IE6"
	newDiv.id=bgId;
	bd[0].appendChild(newDiv);
}

function startWaiting(){
	setDarkBackground("darkBackgroundW");

    var bd=document.getElementsByTagName("body");
    var mssDiv=document.createElement("div");
	mssDiv.id="waitingIcon";
	mssDiv.innerHTML = "waiting...";
	bd[0].appendChild(mssDiv);
}
function stopWaiting(){
	document.getElementById("darkBackgroundW").parentNode.removeChild(document.getElementById("darkBackgroundW"));
	document.getElementById("waitingIcon").parentNode.removeChild(document.getElementById("waitingIcon"));
}

function removeSelect(elem, index)
{
    if (elem.options.length>0)
    {
		for (i=elem.options.length-1;i>=index;i--)
		{
			elem.options[i]=null
		}
	}	
}

function enableLink(linkId,linkIdDis){
document.getElementById(linkId).style.display = "";
document.getElementById(linkIdDis).style.display = "none";
/*	var link=document.getElementById(linkId);
	var attrib = (link.getAttributeNode("onclick")!=null)? link.getAttributeNode("onclick").nodeValue:"";
	var newattrib = "";
	if (attrib != null)
		newattrib = attrib.replace(/this.blur\(\);return\(false\);/g,"");
	if (window.execScript) {
		if (attrib == null){
	    	attrib = link.onclick;
	    	attrib = ""+attrib.replace(/function/g,"").replace(/anonymous\(\)/g,"").replace(/{/g,"").replace(/}/g,"");
	    	newattrib = attrib.replace(/this.blur\(\);return\(false\);/g,"");
alert(newattrib);		
		}
    	link.onclick = new Function(newattrib);//IE
  	}else{
		link.setAttribute("onclick",newattrib);//FireFox
	}
	link.className="button";
	*/
}

function disableLink(linkId,linkIdDis){
document.getElementById(linkId).style.display = "none";
document.getElementById(linkIdDis).style.display = "";
/*
	var link=document.getElementById(linkId);
	var attrib = "this.blur();return(false);" + ( (link.getAttributeNode("onclick")!=null)? link.getAttributeNode("onclick").nodeValue:"");
alert(attrib);		
	if (window.execScript) {
		if (attrib == null){
	    	attrib = link.onclick;
	    	attrib = "this.blur();return(false);" + attrib.replace(/function/g,"").replace(/anonymous\(\)/g,"").replace(/{/g,"").replace(/}/g,"");
		}
    	link.onclick = new Function(attrib);//IE
  	}else{
		link.setAttribute("onclick",attrib);//FireFox
	}
	link.className="disabled_button";
alert(" --> " + ( (link.getAttributeNode("onclick")!=null)? link.getAttributeNode("onclick").nodeValue:""));
*/
}

function disableTableImageLink(linkId,disabledImage){
	var link=document.getElementById(linkId);
	var attrib = "return(false);";
	if (window.execScript) {
		if (attrib == null){
	    	attrib = element.onclick;
	    	attrib = "return(false);" + attrib.replace(/function/g,"").replace(/anonymous\(\)/g,"").replace(/{/g,"").replace(/}/g,"");
		}
    	link.onclick = new Function(attrib);//IE
  	}else{
		link.setAttribute("onclick",attrib);//FireFox
	}
	link.src=disabledImage;
}

function disabledElement(linkId){	
	var link=document.getElementById(linkId);
	link.disabled = true;
}

// funcion para comprobar si un año es bisiesto
function anyoBisiesto(anyo){
	if (anyo < 100)
		var fin = anyo + 1900;
	else
		var fin = anyo ;
	if (fin % 4 != 0)
		return false;
	else{
		if (fin % 100 == 0){
			if (fin % 400 == 0){
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
}

// funcion principal de validacion de la fecha
function validDate(a){ 
	var dia=a.split("/")[0];
	var mes=a.split("/")[1];
	var anyo=a.split("/")[2];
	if( (isNaN(dia)==true) || (isNaN(mes)==true) || (isNaN(anyo)==true) ){
//		alert("LA fecha introducida debe estar formada sólo por números");
		return false;
	}
	var febrero;
	if(anyoBisiesto(anyo))
		febrero=29;
	else
		febrero=28;
	if ((mes<1) || (mes>12)){
//		alert("El mes introducido no es valido. Por favor, introduzca un mes correcto");
		return false;
	}
	if ((mes==2) && ((dia<1) || (dia>febrero)))	{
//alert("El dia introducido no es valido. Por favor, introduzca un dia correcto");
		return false;
	}
	if (((mes==1) || (mes==3) || (mes==5) || (mes==7) || (mes==8) || (mes==10) || (mes==12)) && ((dia<1) || (dia>31))){
//alert("El dia introducido no es valido. Por favor, introduzca un dia correcto");
		return false;
	}
	if (((mes==4) || (mes==6) || (mes==9) || (mes==11)) && ((dia<1) || (dia>30))){
//alert("El dia introducido no es valido. Por favor, introduzca un dia correcto");
		return false;
	}
	return true;
} 