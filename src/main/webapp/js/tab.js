function viewTabProyecto(idProyecto,action){
	//form=document.createElement("Form");
//	form.setAttribute('action',action);
	inputId=document.createElement("input");
	inputId.type="hidden";
	inputId.value=idProyecto;
	inputId.name="idProyecto";
	document.getElementById("panelProyect").appendChild(inputId);
	document.getElementById("panelProyect").action=action;
	document.getElementById("panelProyect").submit();
}

function viewTabDocumento(idProyecto,action){
	//form=document.createElement("Form");
//	form.setAttribute('action',action);
	inputId=document.createElement("input");
	inputId.type="hidden";
	inputId.value=idProyecto;
	inputId.name="idProyecto";
	document.getElementById("panelProyect").appendChild(inputId);
	document.getElementById("panelProyect").action=action;
	document.getElementById("panelProyect").submit();
}

function viewTabArticulo(idProyecto,idArticulo,action){

	inputId=document.createElement("input");
	inputId.type="hidden";
	inputId.value=idArticulo;
	inputId.name="idArticulo";
	inputIdp=document.createElement("input");
	inputIdp.type="hidden";
	inputIdp.value=idProyecto;
	inputIdp.name="idProyecto";
	document.getElementById("panelArticulo").appendChild(inputId);
	document.getElementById("panelArticulo").appendChild(inputIdp);
	document.getElementById("panelArticulo").action=action;
	document.getElementById("panelArticulo").submit();
	
}
