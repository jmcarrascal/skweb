function GetXmlHttpObject(){
	var xmlHttp;
	try {  // Firefox, Opera 8.0+, Safari
		xmlHttp=new XMLHttpRequest();
	}
	catch (e) {  // Internet Explorer
		try {
	    	xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
	    }
	  	catch (e) {
	    	try {
	    		xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	      	}
	    catch (e) {
	    	alert("Your browser does not support AJAX!");
	      	return null;
	     }
	  }
	}
	return xmlHttp;
}

function ajaxLoadInciso(action){
 	
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp == null ) 
		return false;
	xmlHttp.onreadystatechange=function() {
 		if(xmlHttp.readyState == 4){
			if (xmlHttp.status == 200) {
				
				var xmlDoc=xmlHttp.responseXML.documentElement;
				document.getElementById("inciso.idIncisoEdit").value = xmlDoc.getElementsByTagName("idInciso")[0].childNodes[0].nodeValue;
				document.getElementById("inciso.numeroEdit").innerHTML = xmlDoc.getElementsByTagName("numero")[0].childNodes[0].nodeValue;
				document.getElementById("inciso.cuerpoEdit").value = xmlDoc.getElementsByTagName("cuerpo")[0].childNodes[0].nodeValue;

			} else {
	       		alert("Problem: " + xmlHttp.statusText);
			}
  		}
 	}
	xmlHttp.open("POST",action,true);
	xmlHttp.send(null);
}

function ajaxLoadApartado(action){
 	
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp == null ) 
		return false;
	xmlHttp.onreadystatechange=function() {
 		if(xmlHttp.readyState == 4){
			if (xmlHttp.status == 200) {
				
				var xmlDoc=xmlHttp.responseXML.documentElement;
				document.getElementById("apartado.idApartadoEdit").value = xmlDoc.getElementsByTagName("idApartado")[0].childNodes[0].nodeValue;
				document.getElementById("apartado.letraEdit").innerHTML = xmlDoc.getElementsByTagName("letra")[0].childNodes[0].nodeValue;
				document.getElementById("apartado.cuerpoEdit").value = xmlDoc.getElementsByTagName("cuerpo")[0].childNodes[0].nodeValue;

			} else {
	       		alert("Problem: " + xmlHttp.statusText);
			}
  		}
 	}
	xmlHttp.open("POST",action,true);
	xmlHttp.send(null);
}

function ajaxGetOficinasDestino(action){
 	
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp == null ) 
		return false;
	xmlHttp.onreadystatechange=function() {
 		if(xmlHttp.readyState == 4){
			if (xmlHttp.status == 200) {

				var select = '<form action="workflowAction.do?method=sendProyecto" name="workflowActionForm" id="workflowActionForm" method="post" style="display:inline;">';
				//para no perder el token
				select = select + '<input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="'+ document.getElementsByName('org.apache.struts.taglib.html.TOKEN')[0].value +'">';		
				select = select + '<input type="hidden" name="idProyecto" value="'+ document.getElementById('idProyecto').value+'">';
				select = select + '<select name="idOficina" id="oficina.idOficina" style="width:75%">';

				var xmlDoc=xmlHttp.responseXML.documentElement;
				var list = xmlDoc.getElementsByTagName("oficina");
				for (i = 0; i < list.length; i++){
					var id = list[i].attributes[0].value;
					var name = list[i].childNodes[0].nodeValue;
					select = select + '<option value="' + id + '">' + name + '</option>';
				}
				select = select + '</select>';
				
				select = select + '<label class="label_50 field" style="text-align:left;" for="commentID">Comentario:</label><div class="separator"></div>';
				select = select + '<textarea name="comentario" id="commentID" class="label_100" rows="2"></textarea>';
				select = select + '</form>';
		
				showSelectMessage("Seleccione la oficina destino",select,"document.getElementById('workflowActionForm').submit();","mssId",600,220);
			} else {
	       		alert("Problem: " + xmlHttp.statusText);
			}
  		}
 	}
	xmlHttp.open("POST",action,true);
	xmlHttp.send(null);
}

function ajaxGetOficinasExpedienteDestino(action){
 	
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp == null ) 
		return false;
	xmlHttp.onreadystatechange=function() {
 		if(xmlHttp.readyState == 4){
			if (xmlHttp.status == 200) {

				var select = '<form action="workflowAction.do?method=sendProyecto" name="workflowActionForm" id="workflowActionForm" method="post" style="display:inline;">';
				//para no perder el token
				select = select + '<input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="'+ document.getElementsByName('org.apache.struts.taglib.html.TOKEN')[0].value +'">';		
				select = select + '<input type="hidden" name="idProyecto" value="'+ document.getElementById('idProyecto').value+'">';
				select = select + '<select name="idOficina" id="oficina.idOficina" style="width:75%" onChange="getPersonasPorOficina()">';

				var xmlDoc=xmlHttp.responseXML.documentElement;
				var list = xmlDoc.getElementsByTagName("oficina");
				select = select + '<option value="">--Seleccione una Oficina--</option>';
				for (i = 0; i < list.length; i++){
					var id = list[i].attributes[0].value;
					var name = list[i].childNodes[0].nodeValue;
					select = select + '<option value="' + id + '">' + name + '</option>';
				}
				select = select + '</select>';
				
				select = select + '<div class="separator"></div>';
				select = select + '<label class="label_50 field" style="text-align:left;" for="personasOficina">Operador sugerido:</label><div class="separator"></div>';
				select = select + '<select name="usuarioSugerido" id="personasOficina" style="width:68%" disabled></select>';
				
				select = select + "<script type=\"text/javascript\">" +
								  "var personasPorOficina = new AjaxJspTag.Select(" +
								  "\"ajaxBackAction.do?method=getPersonasPorOficina\", {" +
								  "source: \"oficina.idOficina\"," +
								  "target: \"personasOficina\"," +
								  "parameters: \"idOficina={oficina.idOficina}\"," +
								  "parser: new ResponseXmlParser()" +
								  "});" +
				                  "</script>";
				                
				select = select + '<label class="label_50 field" style="text-align:left;" for="commentID">Comentario:</label><div class="separator"></div>';
				select = select + '<textarea name="comentario" id="commentID" class="label_100" rows="2"></textarea>';
				select = select + '</form>';
		
				showSelectMessage("Seleccione la oficina destino",select,"if(validateFields('workflowActionForm')){document.getElementById('workflowActionForm').submit();};","mssIdObligatory",600,260);
				addObligatoryField("workflowActionForm","oficina.idOficina");
			} else {
	       		alert("Problem: " + xmlHttp.statusText);
			}
  		}
 	}
	xmlHttp.open("POST",action,true);
	xmlHttp.send(null);
}
         
function ajaxGetOficinasExpedienteDestinoMasiveProyect(action, idProyectos){ 	
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp == null ) 
		return false;
	xmlHttp.onreadystatechange=function() {
 		if(xmlHttp.readyState == 4){
			if (xmlHttp.status == 200) {

				var select = '<form action="workflowAction.do?method=sendProyectoMasiveProyect" name="workflowActionForm" id="workflowActionForm" method="post" style="display:inline;">';
				//para no perder el token
				select = select + '<input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="'+ document.getElementsByName('org.apache.struts.taglib.html.TOKEN')[0].value +'">';		
				select = select + '<input type="hidden" name="idProyectos" value="'+ idProyectos +'">';
				select = select + '<select name="idOficina" id="oficina.idOficina" style="width:75%" onChange="getPersonasPorOficina()">';

				var xmlDoc=xmlHttp.responseXML.documentElement;
				var list = xmlDoc.getElementsByTagName("oficina");
				select = select + '<option value="">--Seleccione una Oficina--</option>';
				for (i = 0; i < list.length; i++){
					var id = list[i].attributes[0].value;
					var name = list[i].childNodes[0].nodeValue;
					select = select + '<option value="' + id + '">' + name + '</option>';
				}
				select = select + '</select>';
				
				select = select + '<div class="separator"></div>';
				select = select + '<label class="label_50 field" style="text-align:left;" for="personasOficina">Operador sugerido:</label><div class="separator"></div>';
				select = select + '<select name="usuarioSugerido" id="personasOficina" style="width:68%" disabled></select>';
				
				select = select + "<script type=\"text/javascript\">" +
								  "var personasPorOficina = new AjaxJspTag.Select(" +
								  "\"ajaxBackAction.do?method=getPersonasPorOficina\", {" +
								  "source: \"oficina.idOficina\"," +
								  "target: \"personasOficina\"," +
								  "parameters: \"idOficina={oficina.idOficina}\"," +
								  "parser: new ResponseXmlParser()" +
								  "});" +
				                  "</script>";
				                
				select = select + '<label class="label_50 field" style="text-align:left;" for="commentID">Comentario:</label><div class="separator"></div>';
				select = select + '<textarea name="comentario" id="commentID" class="label_100" rows="2"></textarea>';
				select = select + '</form>';
		
				showSelectMessage("Seleccione la oficina destino",select,"if(validateFields('workflowActionForm')){document.getElementById('workflowActionForm').submit();};","mssIdObligatory",600,260);
				addObligatoryField("workflowActionForm","oficina.idOficina");
			} else {
	       		alert("Problem: " + xmlHttp.statusText);
			}
  		}
 	}
	xmlHttp.open("POST",action,true);
	xmlHttp.send(null);
}

function ajaxLoadComentario(action){
 	
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp == null ) 
		return false;
	xmlHttp.onreadystatechange=function() {
 		if(xmlHttp.readyState == 4){
			if (xmlHttp.status == 200) {
				
				var xmlDoc=xmlHttp.responseXML.documentElement;
				document.getElementById("comentario.cuerpoEdit").value = xmlDoc.getElementsByTagName("cuerpo")[0].childNodes[0].nodeValue;

			} else {
	       		alert("Problem: " + xmlHttp.statusText);
			}
  		}
 	}
	xmlHttp.open("POST",action,true);
	xmlHttp.send(null);
}

function insertOption(select,op){
	var y=document.createElement('option');
	y.text = op;
	y.value = op;
	try  {
  		select.add(y,null); // standards compliant
  	} catch(ex)	{
  		select.add(y); // IE only
   	}
}

function ajaxSetCapituloLimits(action,selectedValue){  //SelectedValue es opcional
 	
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp == null ) 
		return false;
	xmlHttp.onreadystatechange=function() {
 		if(xmlHttp.readyState == 4){
			if (xmlHttp.status == 200) {
				
				var xmlDoc=xmlHttp.responseXML.documentElement;
				//eliminar las opciones del select
				var sel = document.getElementById("articulo.numero");
				while (sel.length>0)
				  { sel.remove(sel.length-1);
				  }	
				//poner todos los valores desde el inicio al fin
				var inic = parseInt( xmlDoc.getElementsByTagName("minimo")[0].childNodes[0].nodeValue);
				var end = parseInt( xmlDoc.getElementsByTagName("maximo")[0].childNodes[0].nodeValue);
				var i;
				for (i = inic; i <= end; i++){
					insertOption(sel,i);
				}
				//Marco al último como seleccionado y pongo como default a insertar
				if (selectedValue > 0){
					document.getElementById("articulo.numero").value = selectedValue;
				} else {
					document.getElementById("articulo.numero").selectedIndex = document.getElementById("articulo.numero").length-1;
					numeroChanged();
				}
			} else {
	       		alert("Problem: " + xmlHttp.statusText);
			}
  		}
 	}
	xmlHttp.open("POST",action,true);
	xmlHttp.send(null);
}


function ajaxLoadAnexoName(action){
 	
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp == null ) 
		return false;
	xmlHttp.onreadystatechange=function() {
 		if(xmlHttp.readyState == 4){
			if (xmlHttp.status == 200) {
				
				var xmlDoc=xmlHttp.responseXML.documentElement;
				document.getElementById("anexo.idAnexoEdit").value = xmlDoc.getElementsByTagName("idAnexo")[0].childNodes[0].nodeValue;
				document.getElementById("anexo.nombreEdit").value = xmlDoc.getElementsByTagName("nombre")[0].childNodes[0].nodeValue;

			} else {
	       		alert("Problem: " + xmlHttp.statusText);
			}
  		}
 	}
	xmlHttp.open("POST",action,true);
	xmlHttp.send(null);
}


function ajaxGetUnreadProjects(action){
 	
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp == null ) 
		return false;
	xmlHttp.onreadystatechange=function() {
 		if(xmlHttp.readyState == 4){
			if (xmlHttp.status == 200) {
				
				var xmlDoc=xmlHttp.responseXML.documentElement;
				if (document.getElementById("recivedProjectsCountSpanId") !=null){
					var rec = document.getElementById("recivedProjectsCountSpanId").innerHTML;
					if (rec.indexOf("(") == -1){
						document.getElementById("recivedProjectsCountSpanId").innerHTML = rec + " <b>("+xmlDoc.getElementsByTagName("recivedCount")[0].childNodes[0].nodeValue+")</b>";
					} else {
						document.getElementById("recivedProjectsCountSpanId").innerHTML = rec.substr(0,rec.indexOf("(")) + " <b>("+xmlDoc.getElementsByTagName("recivedCount")[0].childNodes[0].nodeValue+")</b>";
					}					
				}

				if (document.getElementById("inProjectsCountSpanId") !=null){
					var unr = document.getElementById("inProjectsCountSpanId").innerHTML;
					if (unr.indexOf("(") == -1){
						document.getElementById("inProjectsCountSpanId").innerHTML = unr + " <b>("+xmlDoc.getElementsByTagName("unreadCount")[0].childNodes[0].nodeValue+")</b>";
					} else {
						document.getElementById("inProjectsCountSpanId").innerHTML = unr.substr(0,unr.indexOf("(")) + " <b>("+xmlDoc.getElementsByTagName("unreadCount")[0].childNodes[0].nodeValue+")</b>";
					}
				}
				
				if (document.getElementById("adjuntosCountSpanId") !=null){
					var unr = document.getElementById("adjuntosCountSpanId").innerHTML;
					if (unr.indexOf("(") == -1){
						document.getElementById("adjuntosCountSpanId").innerHTML = unr + " <b>("+xmlDoc.getElementsByTagName("adjuntoCount")[0].childNodes[0].nodeValue+")</b>";
					} else {
						document.getElementById("adjuntosCountSpanId").innerHTML = unr.substr(0,unr.indexOf("(")) + " <b>("+xmlDoc.getElementsByTagName("adjuntoCount")[0].childNodes[0].nodeValue+")</b>";
					}
				}	
		
		//alert("-> "+xmlDoc.getElementsByTagName("newRecivedCount")[0].childNodes[0].nodeValue );		
			   	if (parseInt(xmlDoc.getElementsByTagName("newRecivedCount")[0].childNodes[0].nodeValue) > 0){
				   	document.getElementById("alarmMessage").style.display="";
			   		var t=this.setTimeout("clearMessages('alarmMessage')",10000);	//queda visible por 10 segundos
			   	}
				
			} else {
	       		//alert("Problem: " + xmlHttp.statusText);
			}
  		}
 	}
	xmlHttp.open("POST",action,true);
	xmlHttp.send(null);
}

function ajaxGetUnreadProjectsPublished(action){

	xmlHttp=GetXmlHttpObject();
	if (xmlHttp == null ) 
		return false;
	xmlHttp.onreadystatechange=function() {
 		if(xmlHttp.readyState == 4){
			if (xmlHttp.status == 200) {
				
				var xmlDoc=xmlHttp.responseXML.documentElement;
				if (document.getElementById("notPublishedProjectsCountSpanId") !=null){
					var rec = document.getElementById("notPublishedProjectsCountSpanId").innerHTML;
					if (rec.indexOf("(") == -1){
						document.getElementById("notPublishedProjectsCountSpanId").innerHTML = rec + " <b>("+xmlDoc.getElementsByTagName("notPublishedCount")[0].childNodes[0].nodeValue+")</b>";
					} else {
						document.getElementById("notPublishedProjectsCountSpanId").innerHTML = rec.substr(0,rec.indexOf("(")) + " <b>("+xmlDoc.getElementsByTagName("notPublishedCount")[0].childNodes[0].nodeValue+")</b>";
					}					
				}

				if (document.getElementById("publishedProjectsCountSpanId") !=null){
					var unr = document.getElementById("publishedProjectsCountSpanId").innerHTML;
					if (unr.indexOf("(") == -1){
						document.getElementById("publishedProjectsCountSpanId").innerHTML = unr + " <b>("+xmlDoc.getElementsByTagName("publishedCount")[0].childNodes[0].nodeValue+")</b>";
					} else {
						document.getElementById("publishedProjectsCountSpanId").innerHTML = unr.substr(0,unr.indexOf("(")) + " <b>("+xmlDoc.getElementsByTagName("publishedCount")[0].childNodes[0].nodeValue+")</b>";
					}
				}			
						
			} else {
	       		//alert("Problem: " + xmlHttp.statusText);
			}
  		}
 	}
	xmlHttp.open("POST",action,true);
	xmlHttp.send(null);
}

function ajaxLoadAgregados(action){
 	
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp == null ) 
		return false;
	xmlHttp.onreadystatechange=function() {
		if(xmlHttp.readyState == 4){
			if (xmlHttp.status == 200) {
				var xmlDoc=xmlHttp.responseXML.documentElement;
			} else {
	       		alert("Problem: " + xmlHttp.statusText);
			}
  		}
 	}
	xmlHttp.open("POST",action,true);
	xmlHttp.send(null);
}


