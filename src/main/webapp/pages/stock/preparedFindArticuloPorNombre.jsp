<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="jmc.skweb.core.model.Usuario"%>
<%@page import="jmc.skweb.util.PreferenciasUtil"%>
<%@page import="jmc.skweb.util.Constants"%>

<link href="<%=request.getContextPath()%>/css/styles.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/popup.css" rel="stylesheet" type="text/css" />

<link href="<%=request.getContextPath()%>/css/blockuiv1.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/jquery-ui.css"
	rel="stylesheet" type="text/css" />

<script src="<%=request.getContextPath()%>/js/jquery.blockUI.js"
	type="text/javascript"></script>

 
<script>

function validarCampos() {
	var allFields = $([]).add($("#idClave"));
	var bValid = true;
	allFields.removeClass('ui-state-error');
	bValid = bValid && checkNotEmpty($("#idClave"), "Articulo", $("#validateTips"));
	return bValid;
}
function checkNotEmpty(o, n, tipcontainer) {	    
	if (o.val() != "") {
        return true;
    } else {
		o.focus();
    	o.addClass('ui-state-error');
    	updateTips("Debe seleccionar un Articulo", tipcontainer);
        return false;
    }
}

function updateTips(t, tipcontainer) {
    tipcontainer.text(t).effect("highlight",{},1500);
}

function loadArtPorCod(articuloClave) {
	if(articuloClave != ''){
		$("#idLoadArt").show();		
		var articuloActivo = $("#idArticuloActivoCB:checked").val();
		$.ajax({
			type : "POST",
			url : "getArticuloPorClave_OP",
			data : {
				"stock.clave" : articuloClave,
				"articuloActivo" : articuloActivo
			},
			success : function(msg) {
				//alert(msg);
				//alert(msg.length);
				if(msg.length > 1) {
					text = msg.substring(0, 1);
					//alert(text);
					if (text == '['){
						//alert('entro de a uno');
						$("#idClave").val(articuloClave);						
						$("#articuloAuto").val('');
						$("#descripArtID").text(msg);
						$("#descripArtNameId").val(msg);	
					}else{
						//alert(msg.length);
						$("#listAutoComplete").html(msg);
						$("#listAutoComplete").show();
						$("#idAutoCompleteManual").focus();
						$("#idAutoCompleteLoad").hide();
					}
				}
				$("#idLoadArt").hide();	
							
			}
		});
		
		
	}else{
		$("#articuloAuto").focus();
		
	}
	$("#idEstadoPantalla").val('1');
}

function callStockList() {
	if ($("#articuloAuto").val() != ''){
	nombreStock = $("#articuloAuto").val();				
	$("#idAutoCompleteLoad").show();
	var articuloActivo = $("#idArticuloActivoCB:checked").val();	
	$.ajax({
		type : "POST",
		url : "getStockPorNombreList",
		data : {
			"q" : nombreStock,
			"articuloActivo" : articuloActivo
		},
		success : function(msg) {
			$("#listAutoComplete").html(msg);
			$("#listAutoComplete").show();
			$("#idAutoCompleteManual").focus();
			$("#idAutoCompleteLoad").hide();
						
		}
	});
	$("#idEstadoPantalla").val('1');
	}	
}



	function operaEvento(evento) {
			if (evento.keyCode == 13) {					
				var idFocus =($("*:focus").attr("id"));				
				switch (idFocus) {
				case "codArt":						
					$("div.ui").css({position:'absolute', left: ($("#codArt").position().left), top: ($("#codArt").position().top + 20) });					
					loadArtPorCod($("#codArt").val());	
					break;
				case "articuloAuto":						
					$("div.ui").css({position:'absolute', left: ($("#articuloAuto").position().left), top: ($("#articuloAuto").position().top + 20) });					
					callStockList();				
					break;
				case "idAutoCompleteManual":						
					$("#codArt").val($("#idAutoCompleteManual").val());
					$("#idClave").val($("#idAutoCompleteManual").val());
					$("#descripArtID").text($("#idAutoCompleteManual option:selected").html());
					$("#descripArtNameId").val($("#idAutoCompleteManual option:selected").html());					
					$("#listAutoComplete").hide();															
					break;
				default:
					break;
				}
		}
}


$(document).ready(function() {
	
	//$("#codArt").blur(function() {	  
	//	loadArtPorCod($("#codArt").val()); 
	//});
	
	$(document).keypress(operaEvento);
	
	$("#idBuscarPorCodigoImage").click(function() {
		$("div.ui").css({position:'absolute', left: ($("#codArt").position().left), top: ($("#codArt").position().top + 20) });			
		loadArtPorCod($("#codArt").val());	
	});
	$("#idBuscarPorNombreImage").click(function() {
		$("div.ui").css({position:'absolute', left: ($("#articuloAuto").position().left), top: ($("#articuloAuto").position().top + 20) });			
		callStockList();	
	});
	

	$("#idContinuarButton").click(function() {
		if(validarCampos()){
			$("#showArticuloForm").submit();
		}
	});
	
});

function callSelectList() {
	$("#codArt").val($("#idAutoCompleteManual").val());
	$("#idClave").val($("#idAutoCompleteManual").val());
	$("#descripArtID").text($("#idAutoCompleteManual option:selected").html());
	$("#descripArtNameId").val($("#idAutoCompleteManual option:selected").html());		
	$("#listAutoComplete").hide();				
}

</script>


<div class="main">

<div align="right"><a href="<%=request.getContextPath()%>/home"> <img
	src="<%=request.getContextPath()%>/images/general/back.png" width="50px" alt="Volver" title="Volver"
	 border="0"></img> </a> <a href="<%=request.getContextPath()%>/home"> <img
	src="<%=request.getContextPath()%>/images/general/homev1.png" width="50px" alt="Inicio" title="Inicio"
	border="0"></img> </a>
	</div>
<div class="space"></div>
		<div class="panel_100 tab">
	    	<div class="space"></div>
           	<fieldset class="defaultFieldset">
		<legend><b>Consulta de Stock - Busqueda de Articulo por Codigo/Nombre</b></legend>
	<div class="space"></div>
	<s:form id="showArticuloForm" action="showArticulo" theme="simple" cssClass="inline" method="post" >		
		<div class="space"></div>
		<s:hidden key="stock.clave" id="idClave" />
		<s:hidden key="items.descrip" id="descripArtNameId" />
		<p id="validateTips"></p>
	    <table>
		<tr>
			<td><label for="articuloAuto1">Ingrese el Cod. Articulo:</label></td>
			<td><s:textfield id="codArt" name="items.clave"
				size="20" maxlength="20" cssErrorClass="error"
				cssClass="ui-widget-content ui-corner-all" /></td>
			<td style="display: none" id="idLoadArt"><img alt=""
				src="images/general/loading1.gif"></td>
			<td style="display: inline" id="idBuscarPorCodigoImage"><img alt=""
					src="images/general/view.gif"></td>
				

			<td><label for="articuloAuto1">Ingrese una porción del nombre del Articulo:</label></td>
			<td><s:textfield id="articuloAuto" 
				size="60" maxlength="60" cssErrorClass="error"
				cssClass="ui-widget-content ui-corner-all" /></td>
				<div id="listAutoComplete" class="ui" style="display: none;"/>
			<td style="display: none" id="idAutoCompleteLoad"><img alt=""
				src="images/general/loading1.gif"></td>
			<td style="display: inline" id="idBuscarPorNombreImage"><img alt=""
					src="images/general/view.gif"></td>
					
			<%
			Usuario usuarioActual = (Usuario) request.getSession().getAttribute("usuario");
			if (usuarioActual.getRol() == Constants.ID_USR_GERENTE || usuarioActual.getRol() == Constants.ID_USR_OPERADOR ) {	
			%>			
				<td><label for="articuloAuto">Art. Activos</label></td>
				<td><s:checkbox name="articuloActivo" id = "idArticuloActivoCB" /></td>
			<%
			}else{
			%>
				<td><label for="articuloAuto">Art. Activos</label></td>
				<td><s:checkbox name="articuloActivo" id = "idArticuloActivoCB" disabled="true"/></td>			
			<%	
			}				
			%>		

		</tr>
		</table>
		<table>		
		<tr>			
			<td><s:label id="descripArtID"  cssClass="entidadGrande"/></td>
		</tr>
		</table>
		<div id="idContinuarButton" style="display: inline;"><img
			src="images/general/icon_continuar.png" alt="Continuar" border="0">
		</div>
		
	</s:form>
	</fieldset>
	</div>
</div>


