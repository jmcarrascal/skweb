<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="jmc.skweb.core.model.Usuario"%>
<%@page import="jmc.skweb.util.PreferenciasUtil"%>
<%@page import="jmc.skweb.util.Constants"%>
<%@ taglib uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec" %>
 
<link href="<%=request.getContextPath()%>/css/hints.css" rel="stylesheet" type="text/css" />

<link href="<%=request.getContextPath()%>/css/styles.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/popup.css" rel="stylesheet" type="text/css" />

<link href="<%=request.getContextPath()%>/css/jquery.autocomplete.css"
	rel="stylesheet" type="text/css" />

<script src="<%=request.getContextPath()%>/js/popup.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/jquery.blockUI.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/jquery.autocomplete.js" type="text/javascript"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.alphanumeric.js?n=2"></script>

<%
			//Tomar el usuario desde el Request
			Usuario usuarioActual = (Usuario) request.getSession().getAttribute("usuario");
			int idRol = usuarioActual.getRol();
			
%>
<div class="main">
<div align="right"><a href="menuAgendado_Tran"> <img
	src="<%=request.getContextPath()%>/images/general/back.png" width="50px" alt="Volver" title="Volver"
	 border="0"></img> </a> <a href="<%=request.getContextPath()%>/home"> <img
	src="<%=request.getContextPath()%>/images/general/homev1.png" width="50px" alt="Inicio" title="Inicio"
	border="0"></img> </a>
	</div>
			<div class="panel_100 tab">
	    	<div class="space"></div>
		
		   	<fieldset class="defaultFieldset">
    			<legend><b>Estadisticas</b></legend>
				
				<s:form	id="generateEstadiReportForm" action="generateEstadiReport" theme="simple" cssClass="inline" method="post">
					<s:hidden key="gente.idRol" id="idRolHidden"/>
					<s:hidden key="gente.genteNr" id="idAgendadoHidden"/>
					<s:hidden id="idTipoCallComboHidden"/>
					<p id="validateTips"></p>
					<s:hidden key="datosReporte.formatoReporte" id="idFormatoReporte" />
					<table align="center">					
					<tr>
						<td><label for="forTipoReporte"><b>Seleccione el Tipo de Reporte:</b></label></td>
						<td><s:select id="idTipoReporteEstadisSelect" name="datosReporte.tipoReporte" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" list="%{#session['tipoReporteList']}" listKey="nr" listValue="descrip" onchange="getOrigen();"/></td>
						<td id = "idPedidosPendientesCheck" style="display: none" ><label for="forTipoReporte">Sólo Pedidos Pendientes?</label>
						<s:checkbox name="datosReporte.pedidosPendientes" checked="true"/></td>
										
					</tr>
					</table>
					<table align="center">
					<tr>
						<td><label for="forTipoReporte"><b>Seleccione el Origen:</b></label></td>
						<td><s:select id="idOrigenEstadisSelect" name="datosReporte.origen" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" list="%{#session['tipoReporteList']}" listKey="nr" listValue="descrip" onchange=""/></td>
						<td style="display: none" id="idLoadOrigen"><img alt="" src="images/general/loading1.gif"></td>					
					</tr>
					</table>
					<table align="center">
					<tr>
						<td><label for="forTipoReporte"><b>Seleccione el Reporte:</b></label></td>
						<td><s:select id="idReporteEstadisSelect" name="datosReporte.reporte" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" list="%{#session['tipoReporteList']}" listKey="nr" listValue="descrip" onchange=""/></td>
						<td style="display: none" id="idLoadReporte"><img alt="" src="images/general/loading1.gif"></td>					
					</tr>
					</table>
					<table>
						<tr align="left">
							<td><label for="fechaDesde">Fecha Desde:</label></td>
							<td><sj:datepicker name="datosReporte.fechaDesde" id="fechaDesde" displayFormat="dd/mm/yy" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" buttonImageOnly="true" changeMonth="true" changeYear="true" maxDate="0" readonly="false" zindex="2002" cssStyle="display:inline" yearRange="-100:100" showOn="button"/></td>
					   	<tr align="left">
							<td><label for="fechaDesde">Fecha Hasta:</label></td>
							<td><sj:datepicker name="datosReporte.fechaHasta" id="fechaHasta" displayFormat="dd/mm/yy" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" buttonImageOnly="true" changeMonth="true" changeYear="true" maxDate="0" readonly="false" cssStyle="display:inline" zindex="2008" yearRange="-100:100" showOn="button"/></td>
						</tr>
					</table>	
											
					
					<%
						if (idRol == Constants.ID_USR_VENDEDOR || idRol == Constants.ID_USR_CLIENTE ){
					%>
					<table>
					<tr>
						<td ><label for="forTipoReporte">Todos los clientes</label>
						<s:checkbox name="datosReporte.todosClientes" id="todosClientes" checked="false"/></td>
					</tr>
					<tr>
						<td><label for="forClientePorVendedor"><b>Seleccione el Cliente:</b></label></td>
						<td><s:select id="idAgendadoDesde" name="datosReporte.agendadoDesde" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" list="%{#session['clienteList']}" listKey="genteNr" listValue="descripC" onchange=""/></td>				
					</tr>	
					</table>				
					<%
						}else{
					%>
					<table style="display: none" id ="busquedaAgendadoDesdeId">
					<tr >
					<td><label for="forNombreAgendado"><b>Ingrese una porción de la Razón Social:</b></label></td>
					<td><s:textfield id="idNombreAgendadoTextList" 
						size="60" maxlength="60" cssErrorClass="error"
						cssClass="ui-widget-content ui-corner-all" /></td>
					<td style="display: none" id="idAutoCompleteLoad"><img alt=""
						src="images/general/loading1.gif"></td>	
					<td style="display: inline" id="idBuscarPorNombreImage"><img alt=""
						src="images/general/view.gif"></td>
					<td style="display: inline" id="idCancelaPorNombreImage"><img alt=""
						src="images/general/cancel.png"></td>	
					</tr>
					</table>
					<table>
					<tr align="left">
						<td><label for="agendadoD">Agendado Desde:</label></td>
						<td><s:textfield id="idAgendadoDesde" name="datosReporte.agendadoDesde" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" /></td>					
					</tr>
					</table>
					<table style="display: none" id ="busquedaAgendadoHastaId">
					<tr >
					<td><label for="forNombreAgendado"><b>Ingrese una porción de la Razón Social:</b></label></td>
					<td><s:textfield id="idNombreAgendadoHastaTextList" 
						size="60" maxlength="60" cssErrorClass="error"
						cssClass="ui-widget-content ui-corner-all" /></td>
					<td style="display: none" id="idAutoCompleteLoad"><img alt=""
						src="images/general/loading1.gif"></td>	
					<td style="display: inline" id="idBuscarPorNombreHastaImage"><img alt=""
						src="images/general/view.gif"></td>	
					<td style="display: inline" id="idCancelaPorNombreHastaImage"><img alt=""
						src="images/general/cancel.png"></td>	
						
					</tr>
					</table>
					<table>
					<tr align="left">
						<td><label for="agendadoH">Agendado Hasta:</label></td>
						<td><s:textfield id="idAgendadoHasta" name="datosReporte.agendadoHasta" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" /></td>					
					</tr>
					</table>

					<%
						}
					%>
					
					</table>
					<table style="display: none" id ="busquedaArtDesdeId">
						<tr>
							<td><label for="nombreArticulo"><b>Ingrese una porción de la descripción del artículo:</b></label></td>
						   	<td><s:textfield id="nombreArticuloDesdeId" name="stock.descripcion" size="50" maxlength="50" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" /></td>
						   	<td style="display: none" id="idAutoCompleteLoad"><img alt="" src="images/general/loading1.gif"></td>	
							<td id="idBuscarArtPorNombreDesdeImage"><img alt="" src="images/general/view.gif"></td>
							<td style="display: inline" id="idCancelaArtPorNombreDesdeImage"><img alt=""
						src="images/general/cancel.png"></td>	
						</tr>
				    </table>
					
					<table>
						<tr align="left">
							<td><label for="artDesde">Articulo Desde:</label></td>
							<td><s:textfield id="idArtDesde" name="datosReporte.artDesde" value = "0" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" /></td>
						</tr>
					</table>
					
					<table style="display: none" id ="busquedaArtHastaId">
						<tr>
							<td><label for="nombreArticulo"><b>Ingrese una porción de la descripción del artículo:</b></label></td>
						   	<td><s:textfield id="nombreArticuloHastaId" name="stock.descripcion" size="50" maxlength="50" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" /></td>
						   	<td style="display: none" id="idAutoCompleteLoad"><img alt="" src="images/general/loading1.gif"></td>	
							<td id="idBuscarArtPorNombreHastaImage"><img alt="" src="images/general/view.gif"></td>
							<td style="display: inline" id="idCancelaArtPorNombreHastaImage"><img alt=""
						src="images/general/cancel.png"></td>	
						</tr>
				    </table>
					<table>
					   	<tr align="left">
							<td><label for="artHasta">Articulo Hasta:</label></td>
							<td><s:textfield id="idArtHasta" name="datosReporte.artHasta" value = "ZZZZZZZZZZZZZ" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" /></td>
						</tr>
					</table>	
					
					<div id="idDownPdfImage" style="display: inline;"><img
						src="images/general/icon_descargar_reporte_pdf.png" alt="Descargar reporte en formato PDF" border="0">
					</div>
					<div id="idDownExcelImage" style="display: inline;"><img
						src="images/general/icon_descargar_reporte_excel.png" alt="Descargar reporte en formato Excel" border="0">
					</div>
				
				</s:form>
					
			</fieldset>		
			</div>
			
</div>		

<div id ="listAutoComplete" class="ui" style="display: none;"/>

<s:form action="findArticuloPorProveedor" id="showArticuloForm">
	<s:hidden key="gente.genteNr" id="idGente"/>
</s:form>

<s:form action="downloadPdf"  id="descargaPdfForm">
	<s:hidden key="transac.transacNr" id="rutaPdf"/>
</s:form>

<script type="text/javascript">

$(document).ready(function(){
	
	//alert($("#idRolHidden").val());
	
	$(document).keypress(operaEvento);
	
	if ($("#idRolHidden").val() == 5){
		$("#idAgendadoDesde").val($("#idAgendadoHidden").val());
		$("#idAgendadoHasta").val($("#idAgendadoHidden").val());
		$("#idAgendadoDesde").attr('disabled', 'disabled');
		$("#idAgendadoHasta").attr('disabled', 'disabled');
		
	}
	
	$("#idBuscarPorNombreHastaImage").click(function (){	
		$("div.ui").css({position:'absolute', left: ($("#idNombreAgendadoHastaTextList").position().left), top: ($("#idNombreAgendadoHastaTextList").position().top +20) });
		callAgendadoList();				
	});


	$("#idBuscarPorNombreImage").click(function (){	
		$("div.ui").css({position:'absolute', left: ($("#idNombreAgendadoTextList").position().left), top: ($("#idNombreAgendadoTextList").position().top +20) });
		callAgendadoList();				
	});

	$("#idBuscarArtPorNombreDesdeImage").click(function() {
		$("div.ui").css({position:'absolute', left: ($("#nombreArticuloDesdeId").position().left), top: ($("#nombreArticuloDesdeId").position().top + 20) });			
		callStockList();	
	});
	
	$("#idBuscarArtPorNombreHastaImage").click(function() {
		$("div.ui").css({position:'absolute', left: ($("#nombreArticuloHastaId").position().left), top: ($("#nombreArticuloHastaId").position().top + 20) });			
		callStockList();	
	});
	
	$("#idCancelaPorNombreImage").click(function (){	
		$('#busquedaAgendadoDesdeId').hide();
	});
	
	$("#idCancelaPorNombreHastaImage").click(function (){	
		$('#busquedaAgendadoHastaId').hide();
	});
	
	$("#idCancelaPorNombreImage").click(function (){	
		$('#busquedaAgendadoDesdeId').hide();
	});
	
	$("#idCancelaPorNombreHastaImage").click(function (){	
		$('#busquedaAgendadoHastaId').hide();
	});
	
	$("#idCancelaArtPorNombreDesdeImage").click(function (){	
		$('#busquedaArtDesdeId').hide();
	});
	
	$("#idCancelaArtPorNombreHastaImage").click(function (){	
		$('#busquedaArtHastaId').hide();
	});

	
	$("#idDownPdfImage").click(function() {
	
		if (validarCampos()){	
			$('#idFormatoReporte').val('P');
			$('#generateEstadiReportForm').submit();
		}	
	});
 	
	
	$("#idAgendadoDesde").dblclick(function() {
		$("#idTipoCallComboHidden").val('AgenDesde');
		$('#busquedaAgendadoHastaId').hide();
		$('#busquedaAgendadoDesdeId').show();
		$('#idNombreAgendadoTextList').val('');
		$('#idNombreAgendadoTextList').focus();
		//alert('JMC');
	});
 	
	$("#idAgendadoHasta").dblclick(function() {
		$("#idTipoCallComboHidden").val('AgenHasta');
		$('#busquedaAgendadoDesdeId').hide();
		$('#busquedaAgendadoHastaId').show();
		$('#idNombreAgendadoHastaTextList').val('');
		$('#idNombreAgendadoHastaTextList').focus();
		//alert('JMC');
	});
	
	$("#idArtDesde").dblclick(function() {
		$("#idTipoCallComboHidden").val('ArtDesde');
		$('#busquedaArtHastaId').hide();
		$('#busquedaArtDesdeId').show();
		$('#nombreArticuloDesdeId').val('');
		$('#nombreArticuloDesdeId').focus();
	});
 	
	$("#idArtHasta").dblclick(function() {
		$("#idTipoCallComboHidden").val('ArtHasta');
		$('#busquedaArtDesdeId').hide();
		$('#busquedaArtHastaId').show();
		$('#nombreArticuloHastaId').val('');
		$('#nombreArticuloHastaId').focus();
	});
	

	$("#idDownExcelImage").click(function() {
		if (validarCampos()){	
			$('#idFormatoReporte').val('X');
			$('#generateEstadiReportForm').submit();
		}
	});
 	
	
 	$("#fechaDesde").mask("99/99/9999");
 	$("#fechaHasta").mask("99/99/9999");
 	
 	
});

function callStockList() {
	var tipoCallCombo = $("#idTipoCallComboHidden").val();
	if (tipoCallCombo=='ArtDesde'){
		if ($("#nombreArticuloDesdeId").val() != ''){
		nombreStock = $("#nombreArticuloDesdeId").val();				
		$("#idAutoCompleteLoad").show();	
		$.ajax({
			type : "POST",
			url : "getStockPorNombreList",
			data : {
				"q" : nombreStock,
				"articuloActivo" : true
			},
			success : function(msg) {
				$("#listAutoComplete").html(msg);
				$("#listAutoComplete").show();
				$("#idAutoCompleteManual").focus();
				$("#idAutoCompleteLoad").hide();
							
			}
		});
		}	
	}else{
		if ($("#nombreArticuloHastaId").val() != ''){
			nombreStock = $("#nombreArticuloHastaId").val();				
			$("#idAutoCompleteLoad").show();	
			$.ajax({
				type : "POST",
				url : "getStockPorNombreList",
				data : {
					"q" : nombreStock,
					"articuloActivo" : true
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
}
function setNem() {
}
function callAgendadoList() {
	var tipoCallCombo = $("#idTipoCallComboHidden").val();
	if (tipoCallCombo=='AgenDesde'){
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
	}else{
		if ($("#idNombreAgendadoHastaTextList").val() != ''){
			nombreAgendado = $("#idNombreAgendadoHastaTextList").val();				
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
}

function operaEvento(evento) {
	
	
	if (evento.keyCode == 13) {
		var idFocus =($("*:focus").attr("id"));
		if (idFocus == "idNombreAgendadoTextList"){
			$("div.ui").css({position:'absolute', left: ($("#idNombreAgendadoTextList").position().left), top: ($("#idNombreAgendadoTextList").position().top +20) });
			callAgendadoList();				
		}
		if (idFocus == "idNombreAgendadoHastaTextList"){
			$("div.ui").css({position:'absolute', left: ($("#idNombreAgendadoHastaTextList").position().left), top: ($("#idNombreAgendadoHastaTextList").position().top +20) });
			callAgendadoList();				
		}
		if (idFocus == "nombreArticuloDesdeId"){
			$("div.ui").css({position:'absolute', left: ($("#nombreArticuloDesdeId").position().left), top: ($("#nombreArticuloDesdeId").position().top +20) });
			callStockList();				
		}
		if (idFocus == "nombreArticuloHastaId"){
			$("div.ui").css({position:'absolute', left: ($("#nombreArticuloHastaId").position().left), top: ($("#nombreArticuloHastaId").position().top +20) });
			callStockList();				
		}

	}

}

function callSelectList() {
	var tipoCallCombo = $("#idTipoCallComboHidden").val();
	if (tipoCallCombo=='AgenDesde'){
		$("#idAgendadoDesde").val($("#idAutoCompleteManual").val());
		$("#listAutoComplete").hide();
		$("#busquedaAgendadoDesdeId").hide();
		
	}
	if (tipoCallCombo=='AgenHasta'){
		$("#idAgendadoHasta").val($("#idAutoCompleteManual").val());
		$("#listAutoComplete").hide();
		$("#busquedaAgendadoHastaId").hide();
	}
	if (tipoCallCombo=='ArtDesde'){
		$("#idArtDesde").val($("#idAutoCompleteManual").val());
		$("#listAutoComplete").hide();
		$("#busquedaArtDesdeId").hide();
		
	}
	if (tipoCallCombo=='ArtHasta'){
		$("#idArtHasta").val($("#idAutoCompleteManual").val());
		$("#listAutoComplete").hide();
		$("#busquedaArtHastaId").hide();
	}
}


function updateTips(t, tipcontainer) {
    tipcontainer.text(t).effect("highlight",{},1500);
}

function checkMayorCero(o, n, tipcontainer) {	    
	var cantV = o.val(); 
	if (cantV == "") {
		cantV = 0;
	}
		if (cantV > 0) {
	        return true;
	    } else {
    		o.focus();
        	o.addClass('ui-state-error');
	        updateTips("El campo " + n + " es Obliglatorio y debe ser mayor a 0!", tipcontainer);
	        return false;
	    }
}

jQuery(function($){
	   $.datepicker.regional['es'] = {
	      closeText: 'Cerrar',
	      prevText: '<Ant',
	      nextText: 'Sig>',
	      currentText: 'Hoy',
	      monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
	      monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
	      dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
	      dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
	      dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sá'],
	      weekHeader: 'Sm',
	      dateFormat: 'dd/mm/yy',
	      firstDay: 1,
	      isRTL: false,
	      showMonthAfterYear: false,
	      yearSuffix: ''};
	   $.datepicker.setDefaults($.datepicker.regional['es']);
	});
function viewArticulos(idGente){	
	document.getElementById("idGente").value=idGente;
	document.getElementById("showArticuloForm").submit();
}

function downloadEstadiPdf(){		
	//Validar datos
	$('#generateEstadiReportForm').submit();
}

function validarCampos() {
	if ($("#idRolHidden").val() != 1 && $("#idRolHidden").val() != 5){
		var allFields = $([]).add($("#idTipoReporteEstadisSelect").add($("#idOrigenEstadisSelect")).add($("#idReporteEstadisSelect")).add($("#fechaDesde")).add($("#fechaHasta")).add($("#idAgendadoDesde")).add($("#idAgendadoHasta")).add($("#idArtDesde")).add($("#idArtHasta"))); 
	}else{
		
		var allFields = $([]).add($("#idTipoReporteEstadisSelect").add($("#idOrigenEstadisSelect")).add($("#idReporteEstadisSelect")).add($("#fechaDesde")).add($("#fechaHasta")));
	}
		var bValid = true;
	allFields.removeClass('ui-state-error');
	bValid = bValid && checkMayorCero($("#idTipoReporteEstadisSelect"), "Tipo de Reporte", $("#validateTips"));
	bValid = bValid && checkMayorCero($("#idOrigenEstadisSelect"), "Comprobantes de Origen", $("#validateTips"));
	bValid = bValid && checkMayorCero($("#idReporteEstadisSelect"), "Reporte", $("#validateTips"));
	bValid = bValid && checkNotEmpty($("#fechaDesde"), "Fecha Desde", $("#validateTips"));
	bValid = bValid && checkNotEmpty($("#fechaHasta"), "Fecha Hasta", $("#validateTips"));
	
	if ($("#idRolHidden").val() != 1 && $("#idRolHidden").val() != 5){	
		bValid = bValid && checkMayorCero($("#idAgendadoDesde"), "Agendado Desde", $("#validateTips"));
		bValid = bValid && checkMayorCero($("#idAgendadoHasta"), "Agendado Hasta", $("#validateTips"));
	}
	bValid = bValid && checkNotEmpty($("#idArtDesde"), "Articulo Desde", $("#validateTips"));
	bValid = bValid && checkNotEmpty($("#idArtHasta"), "Articulo Hasta", $("#validateTips"));
	return bValid;
}


function getOrigen(){
	
	var tipoReporte = $("#idTipoReporteEstadisSelect").val();
	$("#idLoadOrigen").show();
	$.ajax({
		type : "POST",
		url : "getOptionsSelect",
		data : {
			"idCombo" : 2,
			"usaSeleccion" : -1,
			"clave" : tipoReporte
		},
		success : function(msg) {
			$("#idOrigenEstadisSelect").removeAttr('disabled');
			$("#idLoadOrigen").hide();
			$("#idOrigenEstadisSelect").html(msg);
		}
	});
	if (tipoReporte == 1){
		$("#idPedidosPendientesCheck").show();
	}else{
		$("#idPedidosPendientesCheck").hide();
	}
	
	getReportes();
}
function checkNotEmpty(o, n, tipcontainer) {	    
	if (o.val() != "") {
        return true;
    } else {
		o.focus();
    	o.addClass('ui-state-error');
        updateTips("El campo " + n + " es obligatorio!", tipcontainer);
        return false;
    }
}
function getReportes(){
	
	var tipoReporte = $("#idTipoReporteEstadisSelect").val();
	$("#idLoadReporte").show();
	$.ajax({
		type : "POST",
		url : "getOptionsSelect",
		data : {
			"idCombo" : 3,
			"usaSeleccion" : -1,
			"clave" : tipoReporte
		},
		success : function(msg) {
			$("#idReporteEstadisSelect").removeAttr('disabled');
			$("#idLoadReporte").hide();
			$("#idReporteEstadisSelect").html(msg);
		}
	});
}
</script>