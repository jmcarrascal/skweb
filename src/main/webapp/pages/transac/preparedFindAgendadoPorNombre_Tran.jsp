<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>


<link href="<%=request.getContextPath()%>/css/styles.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/popup.css" rel="stylesheet" type="text/css" />

<link href="<%=request.getContextPath()%>/css/jquery.autocomplete.css"
	rel="stylesheet" type="text/css" />

<script src="<%=request.getContextPath()%>/js/popup.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/jquery.blockUI.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/jquery.autocomplete.js" type="text/javascript"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.alphanumeric.js?n=2"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.simplemodal.1.4.2.min.js"></script>
	
	


 
<script>
function callAgendadoList() {
	if ($("#idNombreAgendadoTextList").val() != ''){
	nombreAgendado = $("#idNombreAgendadoTextList").val();				
	$("#idAutoCompleteLoad").show();
	$.ajax({
		type : "POST",
		url : "getAgendadoPorNombreList_Tran",
		data : {
			"q" : nombreAgendado
		},
		success : function(msg) {
			$("#listAutoComplete").html(msg);
			$("#listAutoComplete").show();
			$("#idAutoCompleteManual").focus();
			$("#idAutoCompleteLoad").hide();
			
		}
	});
	}	
}

function operaEvento(evento) {
	
	
		if (evento.keyCode == 13) {
			var idFocus =($("*:focus").attr("id"));
			if (idFocus == "idAutoCompleteManual"){				
				$("#idNombreAgendadoLabel").text($("#idAutoCompleteManual option:selected").html());
				$("#idCodAgendadoHidden").val($("#idAutoCompleteManual").val());
				$("#listAutoComplete").hide();	
				$("#idNombreAgendadoTextList").val('');
				$("#idNombreAgendadoTextList").focus();
				$("#idCodAgendadoText").val('');
				
			}
			if (idFocus == "idNombreAgendadoTextList"){
				$("div.ui").css({position:'absolute', left: ($("#idNombreAgendadoTextList").position().left), top: ($("#idNombreAgendadoTextList").position().top +20) });
				callAgendadoList();				
			}
			if (idFocus == "idCodAgendadoText"){				
				loadAgendadoPorCod($("#idCodAgendadoText").val());	
				$("#idCodAgendadoText").focus();
			}
			
			
		}
	
}
function loadAgendadoPorCod(genteNr) {
	if ($("#idCodAgendadoText").val() != ''){
	$("#idLoadCodAgendadoImage").show();
	
	$.ajax({
		type : "POST",
		url : "getAgendadoPorCod_Tran",
		data : {
			"gente.genteNr" : genteNr
		},
		success : function(msg) {
			$("#idLoadCodAgendadoImage").hide();
			if (msg != ''){
				$("#idCodAgendadoHidden").val(genteNr);										
			}					
			$("#idNombreAgendadoLabel").text(msg);	
			//Busco el agendado para cargar el nuevo div
		}
	});
	}	
}

function callSelectList() {
	$("#idNombreAgendadoLabel").text($("#idAutoCompleteManual option:selected").html());
	$("#idCodAgendadoHidden").val($("#idAutoCompleteManual").val());
	$("#listAutoComplete").hide();	
	$("#idNombreAgendadoTextList").val('');
	$("#idNombreAgendadoTextList").focus();
	$("#idCodAgendadoText").val('');
}

$(document).ready(function(){
	
	$(document).keypress(operaEvento);
		
	
	

	$("#idBuscarPorCodigoImage").click(function (){	
		loadAgendadoPorCod($("#idCodAgendadoText").val());	
		$("#idCodAgendadoText").focus();
	});

	$("#idBuscarPorNombreImage").click(function (){	
		$("div.ui").css({position:'absolute', left: ($("#idNombreAgendadoTextList").position().left), top: ($("#idNombreAgendadoTextList").position().top +20) });
		callAgendadoList();				
	});

	$("#idContinuarButton").click(function (){	
		if (validarCampos()){				
			$("#menuAgendadoForm").submit();	
		}		
	});
		
			$("#idCodAgendadoText").focus();
	
			$("#idCodAgendadoText").numeric();	
	
	


	$("#idCodAgendadoText").blur(function() {	  
		loadAgendadoPorCod($("#idCodAgendadoText").val());	  
	});
		
	
	


	
});



function validarCampos() {
	var allFields = $([]).add($("#idCodAgendadoHidden"));
	var bValid = true;
	allFields.removeClass('ui-state-error');
	bValid = bValid && checkNotEmpty($("#idCodAgendadoHidden"), "Agendado", $("#validateTips"));
	return bValid;
}
function checkNotEmpty(o, n, tipcontainer) {	    
	if (o.val() != "") {
        return true;
    } else {
		o.focus();
    	o.addClass('ui-state-error');
    	updateTips("Debe seleccionar un Agendado", tipcontainer);
        return false;
    }
}

function updateTips(t, tipcontainer) {
    tipcontainer.text(t).effect("highlight",{},1500);
}
</script>

<div class = "main">

<jsp:include page="../add_on/botones_basicos.jsp"/>
		<div class="panel_100 tab">
	    	<div class="space"></div>
 				
		<fieldset class="defaultFieldset" >
		<legend><b>Agendados - Busqueda de Agendado</b></legend>
		<div class="space"></div>
		<s:form id="menuAgendadoForm" action="menuAgendado_Tran" theme="simple" cssClass="inline" method="post" autocomplete = "off">
		<s:hidden key="gente.genteNr" id="idCodAgendadoHidden" />
		<p id="validateTips"></p>
		<table>
			<tr>
				<td><label for="forCodAgendado">Ingrese el Cod. Agendado:</label></td>
				<td><s:textfield id="idCodAgendadoText" 
					size="20" maxlength="20" cssErrorClass="error"
					cssClass="ui-widget-content ui-corner-all" /></td>				
				<td style="display: none" id="idLoadCodAgendadoImage"><img alt=""
					src="images/general/loading1.gif"></td>
				<td style="display: inline" id="idBuscarPorCodigoImage"><img alt=""
					src="images/general/view.gif"></td>							
				<td><label for="forNombreAgendado">Ingrese una porción de la Razón Social:</label></td>
				<td><s:textfield id="idNombreAgendadoTextList" 
					size="60" maxlength="60" cssErrorClass="error"
					cssClass="ui-widget-content ui-corner-all" /></td>
				<td style="display: none" id="idAutoCompleteLoad"><img alt=""
					src="images/general/loading1.gif"></td>	
				<td style="display: inline" id="idBuscarPorNombreImage"><img alt=""
					src="images/general/view.gif"></td>	
			</tr>
			
		</table>
		<table>		
			<tr>
				<td><s:label id="idNombreAgendadoLabel"  cssClass="entidadGrande"/></td>
			</tr>
		</table>	
		<div id="idContinuarButton" style="display: inline;"><img
			src="images/general/icon_continuar.png" alt="Continuar" border="0">
		</div>
		<div id ="listAutoComplete" class="ui" style="display: none;"/>
		
	</s:form>
	</fieldset>
</div>
</div>
			
	
	

