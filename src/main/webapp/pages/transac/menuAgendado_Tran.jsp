<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="jmc.skweb.core.model.Usuario"%>
<%@page import="jmc.skweb.util.PreferenciasUtil"%>
<%@page import="jmc.skweb.util.Constants"%>


<link href="<%=request.getContextPath()%>/css/styles.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/popup.css" rel="stylesheet" type="text/css" />

<link href="<%=request.getContextPath()%>/css/jquery.autocomplete.css"
	rel="stylesheet" type="text/css" />

<script src="<%=request.getContextPath()%>/js/popup.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/jquery.blockUI.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/jquery.autocomplete.js" type="text/javascript"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.alphanumeric.js?n=2"></script>


 
<script>
$(document).ready(function(){
	
	 	//var param = $(document).getUrlParam('selectedTab');
	    
	 	var idTab = $('#idTabSelected').val();
	 	idTab = "#tabs-" + idTab;
	 	
	 	$('#tabs').tabs().tabs('select', idTab);
			 		
	    
		$('#tabs').tabs({
		    load: function(event, ui) {
		    	alert(selected);
		    }
		});
	    
	 	$("#fechaDesde").mask("99/99/9999");
	 	$("#fechaHasta").mask("99/99/9999");


		$("#idContinuarImage").click(function (){
			setTab();
			if (validarCampos()){				
				$("#adminComprobForm").submit();	
			}else{
				$("#idCodAgendadoText").focus();
			}
			
		});
				
		$("#idAgregarProductos").click(function (){
			setTab();
			$("#idGoCarritoForm").submit();			
		});
		
		$("#idContinuarRepoTesoreriaImage").click(function (){
			setTab();
			$("#idTipoReporteHidden").val($("#idRTTesoreriaSelect").val());
			callReporte();
		});
		
		
		
		$("#idEstadisContinuarImage").click(function (){
			setTab();
			if (validarCampos()){		
				//alert($("#idTipoReporteEstadisSelect").val());
				$("#idTipoReporteHidden").val($("#idTipoReporteEstadisSelect").val());
				//Cargo el tipo de reporte
				if ($("#idTipoReporteEstadisSelect").val() == 2 || $("#idTipoReporteEstadisSelect").val() == 4 || $("#idTipoReporteEstadisSelect").val() == 8){
					//llamar a cargar el div de fechas
					$.blockUI({
						theme:   true, 
						title:  "Reporte de Estadistica",
				        message: $('#idSelectFechasDiv'), 
				        opacity: .1, 
				        color: 'black' 			        
					});		
				}

			}else{
				$("#idCodAgendadoText").focus();
			}
			
		});

		$("#idRepoCCContinuarImage").click(function (){
			setTab();
			if (validarCampos()){		
				//alert($("#idTipoReporteSelect").val());
				$("#idTipoReporteHidden").val($("#idTipoReporteSelect").val());
				//Cargo el tipo de reporte
				if ($("#idTipoReporteSelect").val() == 2 || $("#idTipoReporteSelect").val() == 4){
					//llamar a cargar el div de fechas
					$.blockUI({
						theme:   true, 
						title:  "Reporte Cuenta Corriente",
				        message: $('#idSelectFechasDiv'), 
				        opacity: .1, 
				        color: 'black' 			        
					});		
				}
				if ($("#idTipoReporteSelect").val() == 1 || $("#idTipoReporteSelect").val() == 3 || $("#idTipoReporteSelect").val() == 7){				
					callReporte();					
				}

			}else{
				$("#idCodAgendadoText").focus();
			}
			
		});
		
		$("#idContinuarRepoComprobImage").click(function (){
			setTab();
			if (validarCampos()){				
				//Cargo el tipo de reporte
				$("#idTipoReporteHidden").val("0");
				//Cargo el tipo de reporte
				$("#idTipoComprobHidden").val($("#idTipoComprobSelect").val());				
				//llamar a cargar el div de fechas
				$.blockUI({
					theme:   true, 
					title:  "Reporte por Comprobante",
			        message: $('#idSelectFechasDiv'), 
			        opacity: .1, 
			        color: 'black' 			        
				});		
			}else{
				$("#idCodAgendadoText").focus();
			}
			
		});
		
		
		$("#idCodAgendadoText").focus();
	
	
	$("#idCodAgendadoText").numeric();	
	
	
	
	$(function() {
		$("#tabs").tabs();
	});
	
	$("#idNombreAgendadoText").autocomplete('getAgendadoPorNombre_Tran', {
		minChars : 2,
		max : 200,
		onOpen : function() {
			alert();
		},
		dataType : "json",
		parse : function(data) {

			return $.map(data, function(row) {
				return {
					data : row,
					value : row.valor,
					result : row.valor
				}
			});
		},
		formatItem : function(item) {
			return "[" + item.clave + "] " + item.valor;
		}
	}).result(function(e, item) {
		//Llamar a ajax para obtener el precio del producto
		$("#idNombreAgendadoLabel").text(item.valor);				
		$("#idCodAgendadoHidden").val(item.clave);
		$("#idCodAgendadoRepoComprobHidden").val(item.clave);		
		$("#idAutoCompleteLoad").hide();
	});


	$("#idCodAgendadoText").blur(function() {	  
		loadAgendadoPorCod($("#idCodAgendadoText").val());	  
	});
		
	function loadAgendadoPorCod(genteNr) {
		//alert($("#idCodAgendadoText").val());
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
					$("#idCodAgendadoRepoComprobHidden").val(genteNr);					
				}					
				$("#idNombreAgendadoLabel").text(msg);	
				$("#idNombreAgendadoText").val('');
			}
		});
		}
	}	
});

function setTab() {
	var tabSelected = $('#tabs').tabs().tabs('option', 'selected');
	$.ajax({
		type : "POST",
		url : "setTabSelected",
		data : {
			"tabSelected" : tabSelected
		},
		success : function(msg) {
		}
	});
	
}
	
function callReporte() {	
	var tipoReporte = $("#idTipoReporteHidden").val();
	//alert(tipoReporte);
	if (tipoReporte == "0"){
		//Cargo las variables al formulario
		$("#idRPGenteHidden").val($("#idCodAgendadoHidden").val());		
		$("#idRPTipoComprobHidden").val($("#idRPTipoComprobSelect").val());
		$("#idRPfechaDesdeHidden").val($("#fechaDesde").val());
		$("#idRPfechaHastaHidden").val($("#fechaHasta").val());
		$("#idReportePorTipoComprobForm").submit();	
	}
	if (tipoReporte == 3){
		$("#idRSPCGenteHidden").val($("#idCodAgendadoHidden").val());
		$("#idReporteSIComprasForm").submit();						
	}
	if (tipoReporte == 1){
		$("#idRSPVGenteHidden").val($("#idCodAgendadoHidden").val());
		$("#idReporteSIVentasForm").submit();						
	}
	if (tipoReporte == 7){
		
		$("#idRSPVGenteHidden").val($("#idCodAgendadoHidden").val());
		$("#idReportePedPendientesForm").submit();						
	}
	if (tipoReporte == 2){
		$("#idRRVGenteHidden").val($("#idCodAgendadoHidden").val());
		$("#idRRVfechaDesdeHidden").val($("#fechaDesde").val());
		$("#idRRVfechaHastaHidden").val($("#fechaHasta").val());
		$("#idReporteResumenVentasForm").submit();						
	}

	if (tipoReporte == 4){
		$("#idRRCGenteHidden").val($("#idCodAgendadoHidden").val());
		$("#idRRCfechaDesdeHidden").val($("#fechaDesde").val());
		$("#idRRCfechaHastaHidden").val($("#fechaHasta").val());
		$("#idReporteResumenComprasForm").submit();						
	}

	if (tipoReporte == 5){
		$("#idCodAgendadoRepoTesoreriaHidden").val($("#idCodAgendadoHidden").val());		
		$("#repoTesoreriaForm").submit();						
	}
	
	if (tipoReporte == 8){
		$("#idESTGenteHidden").val($("#idCodAgendadoHidden").val());
		$("#idESTfechaDesdeHidden").val($("#fechaDesde").val());
		$("#idESTfechaHastaHidden").val($("#fechaHasta").val());
		$("#idReporteEstadisticaArtForm").submit();						
	}

}

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
    	updateTips("El campo " + n + " es obligatorio!", tipcontainer);
        return false;
    }
}

function updateTips(t, tipcontainer) {
    tipcontainer.text(t).effect("highlight",{},1500);
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
function closeBlock(){	
	document.getElementById("fechaDesde").value = '';
	document.getElementById("fechaHasta").value = '';
	$.unblockUI();
}

</script>

<div class = "main">

<div align="right"><a href="<%=request.getContextPath()%>/preparedFindAgendadoPorNombre_Tran"> <img
	src="<%=request.getContextPath()%>/images/general/back.png" width="50px" alt="Volver" title="Volver"
	 border="0"></img> </a> <a href="<%=request.getContextPath()%>/home"> <img
	src="<%=request.getContextPath()%>/images/general/homev1.png" width="50px" alt="Inicio" title="Inicio"
	border="0"></img> </a>
	</div>
		<div class="panel_100 tab">
	    	<div class="space"></div>


	<s:form id="findAgendadoForm" action="findAgendadoPorNombre_Tran" theme="simple" cssClass="inline" method="post" >		
	<fieldset class="defaultFieldset" >
	<legend><b>Datos del Agendado</b></legend>		

 				<table>
 				<tr>
					<td>	 					 	 						 										 						 					
	 					<td align="left"><b><s:label id="idRazonSocial" name="gente.descripC" cssClass="entidadGrande"/></b>					
		 			</td>
		 							
				</tr>
				</table>
 				<table>
 				<tr>
		 			<td>
	 					<label class="label_33 field">Cuit:</label>	 				
						<td align="left"><b><s:label id="idCuit" name="gente.cuit" /></b>					
					</td>				
				</tr>
 				</table>
 				<table>
 				<tr>
					<td>
	 					<label class="label_33 field">Domicilio:</label>	 				
	 					<td align="left"><b><s:label id="idDomicilio" name="gente.domicilioPricipal.domicilio" /></b>					
		 			</td>
					<td>
	 					<label class="label_33 field">Localidad:</label>	 				
	 					<td align="left"><b><s:label id="idLocalidad" name="gente.domicilioPricipal.localidad" /></b>					
		 			</td>
					<td>
	 					<label class="label_33 field">Provincia:</label>	 				
	 					<td align="left"><b><s:label id="idProvincia" name="gente.domicilioPricipal.provincia" /></b>					
		 			</td>
					<td>
	 					<label class="label_33 field">Cod. Postal:</label>	 				
	 					<td align="left"><b><s:label id="idCodPostal" name="gente.domicilioPricipal.cp" /></b>					
		 			</td>		 								
				</tr>
				</table>
 				<table>
 				<tr>
					<td>
	 					<label class="label_33 field">Contacto:</label>	 				
	 					<td align="left"><b><s:label id="idContacto" name="gente.domicilioPricipal.contacto" /></b>					
		 			</td>
					<td>
	 					<label class="label_33 field">Telefono:</label>	 				
	 					<td align="left"><b><s:label id="idTelefono" name="gente.domicilioPricipal.teVoz" /></b>					
		 			</td>
					<td>
	 					<label class="label_33 field">Celular:</label>	 				
	 					<td align="left"><b><s:label id="idCelular" name="gente.domicilioPricipal.teMovil" /></b>					
		 			</td>
					<td>
	 					<label class="label_33 field">Email:</label>	 				
	 					<td align="left"><b><s:label id="idEmail" name="gente.domicilioPricipal.internet" /></b>					
		 			</td>
				</tr>
				<tr>
				<td>
	 					<label class="label_33 field">Observación:</label>	 				
	 					<td align="left"><b><s:label id="idObservacion" name="gente.observacion" /></b>					
		 			</td>		 								
				</tr>
				</table>
			
		</fieldset>
	</s:form>
	
	
		
	
	<div id="tabs">
	<ul>
		<%
			Usuario usuarioActual = (Usuario) request.getSession().getAttribute("usuario");
			if (usuarioActual.getPermisoTransac() != null && usuarioActual.getPermisoTransac()) {
				
		%>
		<li><a href="#tabs-0">Transacciones</a></li>
		<%} %>
		<li><a href="#tabs-1">Reportes</a></li>
		<%
			if (usuarioActual.getPermisoTransac() != null && usuarioActual.getPermisoTransac()) {
				
		%>
		<li><a href="#tabs-2">Carrito</a></li>	
		<%} %>		
		
	</ul>
	<%
			
			if (usuarioActual.getPermisoTransac() != null && usuarioActual.getPermisoTransac()) {
				
		%>
	
	<div id="tabs-0">
			
				<fieldset class="defaultLegendChico" >
				<legend><b>Nueva Transacción</b></legend>			
				<s:form	id="adminComprobForm" action="adminComprob_Tran" theme="simple" cssClass="inline" method="post">
					<s:hidden key="gente.genteNr" id="idCodAgendadoHidden" />	
					<s:hidden id="idTabSelected" name = "tabSelected"/>			
					<div align="right"><img	src="<%=request.getContextPath()%>/images/general/Paso1_v1.png"  alt="Paso1" title="Paso1" border="0"></img></div>
					<div align="left">
					<table>
					<tr>
						<td><label for="forTipoComprob">Seleccione el Tipo de Comprobante:</label></td>
						<td><s:select id="idTipoComprobSelect" name="transac.tipoComprob.nr" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" list="%{#session['tipoComrobList']}" listKey="nr" listValue="descripcion" onchange=""/></td>
					</tr>
					</table>
					</div>
					<div align="center">
						<img id = "idContinuarImage" src="<%=request.getContextPath()%>/images/general/icon_continuar.png" alt="Continuar" title="Continuar" border="0"></img> 
					  	
					</div>
				</s:form> 
				</fieldset>
				
	</div>	
	<%} %>
	<div id="tabs-1">
				<fieldset class="defaultLegendChico" >
				<legend><b>Reportes por Tipo de Comprobante</b></legend>				
				<s:form	id="adminComprobForm" action="adminComprob_Tran" theme="simple" cssClass="inline" method="post">
					<s:hidden key="gente.genteNr" id="idCodAgendadoRepoComprobHidden" />
					<table>					
					<tr>
						<td><label for="forTipoComprob">Seleccione el Tipo de Comprobante:</label></td>
						<td><s:select id="idRPTipoComprobSelect" name="transac.tipoComprob.nr" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" list="%{#session['tipoComprobAllList']}" listKey="nr" listValue="descripcion" onchange=""/></td>					
					<td>					
						<img id = "idContinuarRepoComprobImage" src="<%=request.getContextPath()%>/images/general/icon_continuar.png" alt="Continuar" title="Continuar" border="0"></img> 					
					</td>
					</tr>	 
					</table>

				</s:form> 
				</fieldset>
				<fieldset class="defaultLegendChico" >
				<legend><b>Reportes Cuenta Corriente</b></legend>				
								<s:form	id="adminComprobForm" action="adminComprob_Tran" theme="simple" cssClass="inline" method="post">
					<table>					
					<tr>
						<td><label for="forTipoReporte">Seleccione el Tipo de Reporte:</label></td>
						<td><s:select id="idTipoReporteSelect" name="tipoReporte" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" list="%{#session['tipoReporteList']}" listKey="nr" listValue="descripcion" onchange=""/></td>					
					<td>					
						<img id = "idRepoCCContinuarImage" src="<%=request.getContextPath()%>/images/general/icon_continuar.png" alt="Continuar" title="Continuar" border="0"></img> 					
					</td>
					</tr>	 
					</table>
				</s:form>				 
				</fieldset>
				<fieldset class="defaultLegendChico" >
				<legend><b>Reportes Tesorería</b></legend>				
				<s:form	id="repoTesoreriaForm" action="findPasebanPorAgendado_CR" theme="simple" cssClass="inline" method="post">
					<s:hidden key="gente.genteNr" id="idCodAgendadoRepoTesoreriaHidden" />
					<table>					
					<tr>
						<td><label for="forTipoReporte">Seleccione el Tipo de Reporte:</label></td>
						<td><s:select id="idRTTesoreriaSelect" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" list="%{#session['tipoRepoTesoList']}" listKey="nr" listValue="descripcion" onchange=""/></td>					
					<td>					
						<img id = "idContinuarRepoTesoreriaImage" src="<%=request.getContextPath()%>/images/general/icon_continuar.png" alt="Continuar" title="Continuar" border="0"></img> 					
					</td>
					</tr>	 
					</table>

				</s:form> 
				</fieldset>
				
	</div>
	<%
			if (usuarioActual.getPermisoTransac() != null && usuarioActual.getPermisoTransac()) {
				
		%>
	<div id="tabs-2">
				<fieldset class="defaultLegendChico" >
				<legend><b>Gestión del Carrito</b></legend>
					<div align="center">
						<img id = "idAgregarProductos" src="<%=request.getContextPath()%>/images/general/icon_agregar_productos.png" alt="Agregar Productos" title="Agregar Productos" border="0"></img> 					  	
					</div>
 
				</fieldset>
	</div>	
	<%} %>
	
</div>
	
</div>


<div style="display: none" align="center" id = "idSelectFechasDiv">
<s:form action="#" >
	<s:hidden id="idTipoReporteHidden"/>
	<s:hidden id="idTipoComprobHidden"/>
	
	<table align="center">
		<tr>
			<div class="space" ></div>
	   		<sj:datepicker  label="Fecha Desde" id="fechaDesde" displayFormat="dd/mm/yy" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" buttonImageOnly="true" changeMonth="true" changeYear="true" maxDate="0" readonly="false" zindex="2002" cssStyle="display:inline" yearRange="-100:100" showOn="button"/> 
	   		<sj:datepicker label="Fecha Hasta"  id="fechaHasta" displayFormat="dd/mm/yy" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" buttonImageOnly="true" changeMonth="true" changeYear="true" maxDate="0" readonly="false" cssStyle="display:inline" zindex="2008" yearRange="-100:100" showOn="button"/> 
		</tr>
	</table>
	
  	<div class="buttonPanel" id="buttonDown" align="center">
	<ul class="content_1x128">
		<img src="images/general/icon_buscar.png" alt="Buscar"
			border="0" style="float: center; margin-top: 0px;" onclick="callReporte();"/>
		<a href="#" onclick="closeBlock();"> <img
			src="images/general/icon_cancelar.png" alt="Cancelar"
			border="0" style="float: center; margin-top: 0px;"></img> </a>
	</ul>
	</div>
</s:form>
</div>
<s:form	id="idReportePorTipoComprobForm" action="reportePorTipoComprob_Repo">
	<s:hidden key="gente.genteNr" id="idRPGenteHidden"/>
	<s:hidden key="transac.tipoComprob.nr" id="idRPTipoComprobHidden"/>
	<s:hidden key="fechaDesde" id ="idRPfechaDesdeHidden"/>
	<s:hidden key="fechaHasta" id ="idRPfechaHastaHidden"/>	
</s:form>

<s:form	id="idReporteSIComprasForm" action="preparedFindGentePorNombre_CC_C">
	<s:hidden key="gente.genteNr" id="idRSPCGenteHidden"/>
</s:form>

<s:form	id="idReporteSIVentasForm" action="getSoloImpagosVentaPorGente_CC">
	<s:hidden key="gente.genteNr" id="idRSPVGenteHidden"/>
</s:form>

<s:form	id="idReportePedPendientesForm" action="getPedPendientesPorGente_CC">
	<s:hidden key="gente.genteNr" id="idRSPVGenteHidden"/>
</s:form>

<s:form	id="idGoCarritoForm" action="preparedFindArticuloPorFlia_Carr">
	<s:hidden key="gente.genteNr" id="idGoCarritoGenteHidden"/>
</s:form>

<s:form	id="idReporteResumenVentasForm" action="resumen_CC">
	<s:hidden key="gente.genteNr" id="idRRVGenteHidden"/>
	<s:hidden key="fechaDesde" id ="idRRVfechaDesdeHidden"/>
	<s:hidden key="fechaHasta" id ="idRRVfechaHastaHidden"/>		
</s:form>

<s:form	id="idReporteResumenComprasForm" action="resumen_CC_C">
	<s:hidden key="gente.genteNr" id="idRRCGenteHidden"/>
	<s:hidden key="fechaDesde" id ="idRRCfechaDesdeHidden"/>
	<s:hidden key="fechaHasta" id ="idRRCfechaHastaHidden"/>		
</s:form>

<s:form	id="idReporteEstadisticaArtForm" action="estadisticaArtGente">
	<s:hidden key="gente.genteNr" id="idESTGenteHidden"/>
	<s:hidden key="fechaDesde" id ="idESTfechaDesdeHidden"/>
	<s:hidden key="fechaHasta" id ="idESTfechaHastaHidden"/>		
</s:form>




