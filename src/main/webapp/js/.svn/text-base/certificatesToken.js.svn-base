function loadSelector(strNames)
{

/*	text='<form name="signerForm" action="abmDocumento.do?method=saveDocumentoPDF">'
	text+='<input type="hidden"  name="idProyecto" id="idProyecto" value="'+document.getElementById("idProyecto").value+'"/>'
	text+='<label class="label_20 field">Certificado:</label>'
			 +'<select styleClass="label_50" style="width:300px;"  name="certificado" id="certificado" style="float:left">'	
	*/	
	
	arrTmp = strNames.split("<<S0>>");	
	objDestino = document.getElementById("certificado")
	
	for(i = 0; i < arrTmp.length; i++)
	{
		
		
		var tmp = arrTmp[i];
		
	    objDestino.options[i]=new Option(tmp,i);
	//	text+='<option value="'+i+'" >'+tmp+'</option>'
		
	}
//	text+='</select>';
//	text+='</form>'	
	
	//showSignerSelectorMessage(text,"firmar()","mssId");
	//objDestino.style.display="";

//	document.getElementById("div_certificado").style.display="block";
//	document.getElementById('div_pin').style.display="none";
	
	
}

function addOpt(oCntrl, iPos, sTxt, sVal)
{
	var selOpcion=new Option(sTxt, sVal);
	eval(oCntrl.options[iPos]=selOpcion);	
}