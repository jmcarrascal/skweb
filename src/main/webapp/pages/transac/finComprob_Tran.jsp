<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="jmc.skweb.core.model.Usuario"%>
<%@page import="jmc.skweb.util.PreferenciasUtil"%>
<%@page import="jmc.skweb.util.Constants"%>

<%@ taglib uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec"%><%@ taglib
	uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec"%>
<link href="<%=request.getContextPath()%>/css/styles.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/blockuiv1.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/popup.css"
	rel="stylesheet" type="text/css" />

<link href="<%=request.getContextPath()%>/css/hints.css"
	rel="stylesheet" type="text/css" />

<script src="<%=request.getContextPath()%>/js/jquery.blockUI.js"
	type="text/javascript"></script>




<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.alphanumeric.js?n=2"></script>
<script>
$(document).ready(function() {
	
	jQuery(function($){
		   $.datepicker.regional['es'] = {
		      closeText: 'Cerrar',
		      prevText: '<Ant',
		      nextText: 'Sig>',
		      currentText: 'Hoy',
		      monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
		      monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
		      dayNames: ['Domingo', 'Lunes', 'Martes', 'Mi�rcoles', 'Jueves', 'Viernes', 'S�bado'],
		      dayNamesShort: ['Dom','Lun','Mar','Mi�','Juv','Vie','S�b'],
		      dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S�'],
		      weekHeader: 'Sm',
		      dateFormat: 'dd/mm/yy',
		      firstDay: 1,
		      isRTL: false,
		      showMonthAfterYear: false,
		      yearSuffix: ''};
		   $.datepicker.setDefaults($.datepicker.regional['es']);
		});


	
$("#idFechaEntregaDate").mask("99/99/9999");
$("#ordenCompraId").focus();

$("#cerrarTransac").click(function() {
	saveComprobante();
});

function saveComprobante() {		
	$.blockUI({         	                       	               
          message:  "<img src=\"./images/icons/loadingGrayCircle.gif\">"
	});
	$.ajax({
			  type: "POST",
			  url: "saveTransac",
			  data: $("#finComprobForm").serialize(),
			  dataType: "script",
			  beforeSend: function(objeto){   				
			  },
			  success: function(msg){
					$("#idMsgSave").text(msg);
					$.unblockUI();
					$.blockUI({
	   				message : $('#resultCerrarTransacId'),
	   				opacity : .1,
	   				color : 'black',
	   				css : {
	   					top : ($(window).height() - 100) / 2 + 'px',
	   					left : ($(window).width() - 700) / 2 + 'px',
	   					width : '700px'
	   				}
	   				}); 	   				   	   				   	   				  
 			  },
			  error: function(objeto, quepaso, otroobj){
				alert("Comuniquese con el Administrador del Sistema : "+ quepaso);
			  }
		
			});
}
});
</script>

<%
			//Tomar el usuario desde el Request
			Usuario usuarioActual = (Usuario) request.getSession().getAttribute("usuario");
			int logicaColor = 0;
			if (PreferenciasUtil.comparePreferencia(usuarioActual.getListPreferencias(), Constants.PREF_ID_USA_LOGICA_STOCK_COLOR) == -1){
				logicaColor = -1;
			}
%>
<div class = "main">
		<div class="panel_100 tab">
	    	<div class="space"></div>

<fieldset class="defaultFieldset"><legend><b>Gesti�n
de Comprobante - Finalizar Transacci�n</b></legend> 

<div align="right"><img	src="<%=request.getContextPath()%>/images/general/Paso3_v1.png"  alt="Paso3" title="Paso3" border="0"></img></div>


<div class="space"></div>
<div align="left">
<s:label id="idRazonSocial" name="gente.descripC" cssClass="entidadGrande"/>
	<div class="separator"></div>
<label class="label_00 field">Tipo de Comprobante:</label> <b><s:label
	name="transac.tipoComprob.descripcion" /></b>
	</div>
<div class="space"></div>
<ec:table tableId="notReg" items="itemsList" var="items" locale="es_ES"
	action="listItems_OP" imagePath="images/table/*.gif" width="100%"
	rowsDisplayed="100" showPagination="false" showStatusBar="false"
	showTitle="false" showTooltips="false" showExports="false">
	<ec:row>
		<ec:column width="60px" property="articulo"
					title="Clave" sortable="false" filterable="false" alias = "read1">
					<span style="white-space: nowrap;">${items.articulo}</span>
				</ec:column>
		<ec:column width="40px" property="id.itemNr" title="Nr" sortable="false"
			filterable="false" />
		<ec:column width="60px" property="descrip"
					title="Articulo" sortable="false" filterable="false" alias = "read2">
					<span style="white-space: nowrap;">${items.descrip}</span>
				</ec:column>
		<%
			if (logicaColor == -1){
		%>
		<ec:column width="100" property="colores.descrip" title="Color"
			sortable="false" filterable="false" />		
		<%
			}
		%>		
		<ec:column width="60" property="cant1" title="Cantidad"
			sortable="false" filterable="false" />
		<ec:column width="60" property="precio" title="Precio Un"
			sortable="false" filterable="false" />
		<ec:column width="60" property="bonif" title="Bonificacion"
			sortable="false" filterable="false" />
		<ec:column width="60" property="totalItem" title="Precio Item"
			sortable="false" filterable="false" />
		<ec:column width="60" property="porcentajeImpuesto" title="IVA"
			sortable="false" filterable="false" />
	</ec:row>
</ec:table>
<div class="space"></div>
<div align="center">
<div>
<b><label class="subTotalesTitle">SubTotal:</label></b>
<s:label cssClass="subTotales"  id="transac.netoGrav" name="transac.netoGrav" />
</div>
<div>
<b><label class="subTotalesTitle">Impuestos:</label></b>
<s:label cssClass="subTotales" id="transac.iva" name="transac.iva" />
</div>
<div>
<b><label class="totalesTitle">Total:</label></b>
<b><s:label cssClass="totales" id="transac.iva" name="transac.total" /></b>
</div>
</div>
<s:form action="finComprob_Tran" id="finComprobForm">
<s:hidden key="gente.genteNr" id="idAgendadoFinCombrobHidden" />
<div align="left">
<label for="forDomicilios">Seleccione el Domicilio de Entrega:</label>
<s:select id="idDomEntregaSelect" name="transac.nrDomicilioEnt" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" list="%{#session['domiciliosList']}" listKey="nr" listValue="descripC" onchange=""/>

<table>
<br>
<div align="left">
<label for="forCondi">Seleccione Condici�n de Pago:</label>
<s:select id="idCondiSelect" name="transac.condiciones" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" list="%{#session['condiList']}" listKey="nr" listValue="descrip" onchange=""/>
</div>
</tr>
</table>
<table>
	<tr>
	<br>
		<div align="left">
		<label for="forCompra">Orden de Compra:</label>
	   	<s:textfield id="ordenCompraId" name="transac.ordenCompra" size="15" maxlength="15" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all"/>
	   	</div>
	</tr>
</table>


<table>
<%if (usuarioActual.getRol() != Constants.ID_USR_CLIENTE){%>
<tr>
<br>
<td>
<sj:datepicker  label="Fecha de Entrega" id="idFechaEntregaDate" name="fechaEntrega" displayFormat="dd/mm/yy" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" buttonImageOnly="true" changeMonth="true" changeYear="true" readonly="false" zindex="2002" cssStyle="display:inline" showOn="button"/>
</td>
</tr>
<%} %>
<tr>
<td>
<s:textarea label="Observaciones" cols="50" rows="3" name="transac.observaciones"/>
</td>
</tr>
</table>
</s:form>
<div align="center">
<div id="idVolverImage" style="display: inline;" > 
	<a href="javascript:history.back(-1)"> <img
	src="<%=request.getContextPath()%>/images/general/icon_volver.png" alt="Volver" title="Volver"
	></img> </a>
</div>


<div id="idCancelar" style="display: inline;" > 
	<a href="menuAgendado_Tran"> <img
	src="<%=request.getContextPath()%>/images/general/icon_cancelar.png" alt="Cancelar" title="Cancelar"
	 border="0"></img> </a>
</div>
<%if (usuarioActual.getRol() != Constants.ID_USR_CLIENTE){%>
<div id="cerrarTransac" style="display: inline;"><img
	src="images/general/icon_grabaTransac.png" alt="Guardar" border="0">
</div>
<%}else{ %>
<div id="cerrarTransac" style="display: inline;"><img
	src="images/general/icon_finalizar_pedido.png" alt="Guardar" border="0">
</div>  
<%} %>
</div>


<div style="display: none" align="center" id="resultCerrarTransacId" >
		<h1 id="idMsgSave" >Transacci�n</h1>		
		<div id="buttonDown" >
				<a href="menuAgendado_Tran" onclick="this.blur();">
					<img src="images/general/icon_volver.png" alt="Volver al Agendado" border="0" style="float:center;margin-top:0px;"></img>
				</a>						
		</div>						
</div>
</center>
</fieldset>
</div>
</div>





