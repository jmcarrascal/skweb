<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="jmc.skweb.core.model.Usuario"%>
<%@page import="jmc.skweb.util.PreferenciasUtil"%>
<%@page import="jmc.skweb.util.Constants"%>


<link href="<%=request.getContextPath()%>/css/styles.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/popup.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/js/popup.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/jquery.autocomplete.js" type="text/javascript"></script>
 
<script>
$(document).ready(function(){
	getLoadSubFlia();
	
	$("#consultar").click(function (){
		document.getElementById('findArticuloForm').submit();
	});

	$("#consultarPorFam").click(function (){
		$("#idBusquedaHID").val(0);
		if ($("#fam").val() == 0 && $("#subFam").val() == 0){
			alert('Debe indicar al menos una familia o subfamilia');
		}else{
			document.getElementById('findArticuloPorFliaForm').submit();	
		}
		
	});	
	
	$("#consultarPorNom").click(function (){
		$("#idBusquedaHID").val(1);
		document.getElementById('findArticuloPorFliaForm').submit();
	});	
	$("#consultarPorCod").click(function (){
		$("#idBusquedaHID").val(2);
		document.getElementById('findArticuloPorFliaForm').submit();
	});	

});

function setFlia(){
	$("#idBusquedaHID").val(0);
}
function setNom(){
	$("#idBusquedaHID").val(1);
}
function setCod(){
	$("#idBusquedaHID").val(2);
}

function getLoadSubFlia(){
	
	var fam = $("#fam").val();
	$("#idLoadSubFam").show();
	$.ajax({
		type : "POST",
		url : "getOptionsSelect",
		data : {
			"idCombo" : 1,
			"usaSeleccion" : -1,
			"clave" : fam
		},
		success : function(msg) {
			$("#subFam").removeAttr('disabled');
			$("#idLoadSubFam").hide();
			
			$("#subFam").html(msg);
		}
	});
}
</script>
<div class = "main">
<div align="right"> 
<a href="<%=request.getContextPath()%>/menuAgendado_Tran"> <img
	src="<%=request.getContextPath()%>/images/general/icon_ir_a_transac.png" alt="Volver" title="Volver"
	 border="0"></img> </a><a href="<%=request.getContextPath()%>/home"> <img
	src="<%=request.getContextPath()%>/images/general/homev1.png" width="50px" alt="Inicio" title="Inicio"
	border="0"></img> </a>
	</div>

	<s:form id="findArticuloPorFliaForm" action="findArticuloPorFlia_Carr" theme="simple" cssClass="inline" method="post" >
	<s:hidden key="busquedaPorId" id = "idBusquedaHID"/>		
			<div class="panel_100 tab">
	    	<div class="space"></div>
		
		<fieldset class="defaultFieldset" >
		<legend><b>Gestión del Carrito - Busqueda de artículos</b></legend>
		<div class = "space"></div>		
	    
	    <table>
			<tr>				
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
	    
	    <fieldset class="defaultFieldsetChico" >
		<legend><b>Selección por Familia / SubFamilia</b></legend>
	    <table>
			<tr>
				<td><label for="desFam">Familia:</label></td>				
				<td><s:select id="fam" name="fam.nrfam" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" list="%{#session['listFam']}" listKey="nrfam" listValue="descripC" onchange="getLoadSubFlia();"/></td>
				
				<td><label for="desFam">SubFamilia:</label></td>				
				<td><s:select id="subFam" name="subFam.nrsubfam" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" list="%{#session['listSubFam']}" listKey="nrsubfam" listValue="descripC"/></td>
				<td style="display: none" id="idLoadSubFam"><img alt="" src="images/general/loading1.gif"></td>
			</tr>
	    </table>

		<center>
			<div id="consultarPorFam" style="display:inline;">
					<img src="images/general/icon_buscar.png" alt="Consultar por familia" border="0" >		
				
			</div>
		</center>
		</fieldset>
		
		<fieldset class="defaultFieldsetChico" >
		<legend><b>Selección por Código</b></legend>
		<div class = "space"></div>		
	    <table>
			<tr>
				<td><label for="codigoArticulo">Ingrese una porción del código del artículo:</label></td>
			   	<td><s:textfield id="codigoArticulo" name="stock.clave" size="13" maxlength="13" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" onchange="setCod();"/></td>
			</tr>
	    </table>

		<center>
			<div id="consultarPorCod" style="display:inline;">
					<img src="images/general/icon_buscar.png" alt="Consultar por Código" border="0" >		
				
			</div>
		</center>
		</fieldset>

		<fieldset class="defaultFieldsetChico" >
		<legend><b>Selección por Descripción</b></legend>
		<div class = "space"></div>		
	    <table>
			<tr>
				<td><label for="nombreArticulo">Ingrese una porción de la descripción del artículo:</label></td>
			   	<td><s:textfield id="nombreArticulo" name="stock.descripcion" size="50" maxlength="50" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" onchange="setNom();"/></td>
			</tr>
	    </table>

		<center>
			<div id="consultarPorNom" style="display:inline;">
					<img src="images/general/icon_buscar.png" alt="Consultar por Nombre" border="0" >		
				
			</div>
		</center>
		</fieldset>
</fieldset>
</div>
	</s:form>
</div>



