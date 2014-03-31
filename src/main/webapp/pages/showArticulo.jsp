<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="jmc.skweb.core.model.Usuario"%>
<%@page import="jmc.skweb.util.PreferenciasUtil"%>
<%@page import="jmc.skweb.util.Constants"%>
 
<link href="<%=request.getContextPath()%>/css/hints.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function callGetStock1(articuloClave, idStrC) {						
	var idStr = "#divIdArt1" + idStrC;
	var idLoadStr = "#idLoadStock1" + idStrC;
	$(idLoadStr).show();
	$(idStr).html("");
	$.ajax({
		type : "POST",
		url : "getFormulaStock",
		data : {
			"stock.clave" : articuloClave,
			"carrito" : 1,
			"muestroBarraLateral" : false
		},
		success : function(msg) {
			$(idStr).show();
			$(idStr).html(msg);				
			$(idLoadStr).hide();
		}
	});	
}
</script>
<s:hidden id = "muestraCantidad" value="0"/>
<div class="main">

	<jsp:include page="add_on/botones_basicos.jsp"/>
		
		<%
			//Tomar el usuario desde el Request
			Usuario usuarioActual = (Usuario) request.getSession().getAttribute("usuario");
			Double logicaPiezas = PreferenciasUtil.comparePreferencia(usuarioActual.getListPreferencias(), Constants.PREF_ID_USA_LOGICA_STOCK_COLOR);
			int muestraC = 0;
			if (PreferenciasUtil.comparePreferencia(usuarioActual.getListPreferencias(), Constants.PREF_ID_MUESTRA_CANTIDAD) == -1){
			muestraC = -1; 
			
		%>
		<script type="text/javascript">
			$("#muestraCantidad").val(-1);
		</script>
		<%} %>
	
	
		<div class="panel_100 tab">
				<div class="space"></div>
		<fieldset class="defaultFieldset" >
			<legend><b>Descripción del Articulo</b></legend>

			<s:form action="downloadFactura" id="listFacturaForm" theme="simple" cssClass="inline" method="post" >						
				<s:hidden key = "stock.clave" id = "idClaveHidden"/>
 				<table> 				
 					<tr>
	 					<td>
	 					
	 					</td>
						
 					</tr>
				</table>
				<table>
					
 				<tr>
					<td>
	 					<label class="label_33 field">Clave N:</label>	 	 						 									
	 					<td align="left"><b><s:label id="idClave" name="stock.clave"/></b>					
		 			</td>
		 			<td>
	 					<label class="label_33 field">Nombre:</label>	 				
						<td align="left"><b><s:label id="idDescrip" name="stock.descripcion" /></b>					
					</td>				
				</tr>
 				<tr>
					<td>
	 					<label class="label_33 field">Familia:</label>	 				
	 					<td align="left"><b><s:label id="idFlia" name="stock.famStr" /></b>					
		 			</td>
		 			<td>
	 					<label class="label_33 field">Sub Familia:</label>	 				
						<td align="left"><b><s:label id="idSubFlia" name="stock.subFamStr" /></b>						
					</td>					
				</tr>
				</table>
			
			
	    	<div class="space"></div>
           	<%
           		if (logicaPiezas == 0){
           	%>
           	
    			<legend id="idCuadroLeg"><b>Existencias</b></legend>    			
				<s:actionerror/>	
					<ec:table											
						tableId="notReg" 
				    	items="exiArtList"
						var = "exiArt"
						locale="es_ES"
						action="showArticulo"
						imagePath="images/table/*.gif"
						width="100%"
						rowsDisplayed="100" 
						>
						<ec:row>
								<ec:column width="40px" property="id.existencias.nr" title="Nr Exi" sortable="false" filterable="false"/>
								<ec:column width="auto" property="id.existencias.descrip" title="Existencia" sortable="false" filterable="false"/>
								<% if (muestraC != -1){%>
								<ec:column width="200px" title=" " filterable="false" sortable="false" alias="st">
									<a href="#">
										    	<div style="display: none" id="idLoadStock1${exiArt.stock.claveSBarras}"><img alt="" src="images/general/loading1.gif"></div>
										    	<div id = "divIdArt1${exiArt.stock.claveSBarras}"/>
										    	<script type="text/javascript">
										    		var idStrC = '${exiArt.stock.claveSBarras}';
										    		callGetStock1('${exiArt.stock.clave}', idStrC);
										    	</script>
									</a>	    	
								</ec:column>
								<%}else{ %>
								<ec:column width="260px" property="cantidad1" title="Cantidad 1" sortable="false" filterable="false">
									<span style="white-space:nowrap;" class="tableRow${exiArt.signo}">${exiArt.cantidad1}</span>
								</ec:column>
								<%} %>																																						
						</ec:row>
					</ec:table>
				<div class="space" id = "idFinTabla"></div> 
			
			<%
           		}
			%>
			
		
		<table align="center">
			<tr>
				<td style="display: none" id="idLoadStock"><img alt="" src="images/general/loading1.gif"></td>
	 			<td><div id = "idShowStock"/></td>
 			</tr>
 		</table>
 		
		</s:form>
		<%
			//Tomar el usuario desde el Request
			if (usuarioActual.getRol() == Constants.ID_USR_ADMIN){
		%>
		<div align="center">
			<a href="#" onclick="uploadImageArticulo();">
				    				<img src="images/general/icon_ingresar_imagen.png" alt="Ingresar Imagen" border="0" ></img>
				    			</a>
				    			</div>
		<%} %>		    			
		<div class="space"></div>
		</fieldset>
		</div>

		<div class="space"></div>
	</div>
	
<div style="display: none" id = "idShowUploadDiv">
	<s:form action="uploadArticuloImage" id="idUploadArticuloImageForm" enctype="multipart/form-data" theme="simple" method="POST">
	<s:hidden key = "stock.clave"  id="idKeyArticuloHidden" />
	<div class="space"></div>	
				    <table align="center">
						<tr>
							<td><s:label id="idTitleUploadArticuloLabel"/></td>
						</tr>						
						<tr>
							<td><b><s:label id="idUploadArticuloLabel"/></b></td>
						</tr>						
						<tr>							
							<td><s:file id="idUploadArticuloFile" key="stock.imageFile" name="stock.imageFile"  cssErrorClass="error" cssClass="ui-widget-content ui-corner-all"/></td>
						</tr>
						<tr>
							<td>
								<div style="display: none" id="idUploadingDiv">
									<img src="images/general/uploading.gif" alt="Aguarde mientras se sube la imagen" border="0" ></img>
								</div>
							</td>
						</tr>
						</table>
						
						<table align="center">
						<tr>
							<td>
								<a href="#" onclick="uploadArticulo();">
				    				<img src="images/general/icon_ingresar_imagen.png" alt="Ingresar Imagen" border="0" ></img>
				    			</a>													
							</td>							
							<td>
								<a href="#" onclick="$.unblockUI();">
				    				<img src="images/general/icon_cancelar.png" alt="Cancelar" border="0" ></img>
				    			</a>													
							</td>							

						</tr>
					</table>
											
	</s:form>
	
	
							
</div>

<script language="javascript">

function uploadArticulo(){		
	$("#idUploadingDiv").show();
	$("#idUploadArticuloImageForm").submit();
}

function uploadImageArticulo(){	
	var idArt = $('#idClave').text();
	var nombreArt = $('#idDescrip').text();
	$('#idKeyArticuloHidden').val(idArt);	
	$('#idTitleUploadArticuloLabel').text("Seleccione la imagen del articulo: " );
	$('#idUploadArticuloLabel').text("["+idArt+"] " + nombreArt);
	$.blockUI({
		message : $('#idShowUploadDiv'),
		opacity : .1,
		color : 'black',
		css : {
			top : ($(window).height() - 100) / 2 + 'px',
			left : ($(window).width() - 400) / 2 + 'px',
			width : '400px'
		}
	});			
}

function CloseUiTableBlue() {					
	$("#idTableComprobante").html('');
	$("#idTableComprobante").hide();
	}	

function submitForm(formId) { 
	document.getElementById(formId).submit();
}

function loadPedidoVta() {						
	
	$("#idLoadPedidoVtaImg").show();
	$("div.uiComprob").css({position:'absolute', left: ($("#idFinTabla").position().left), top: ($("#idFinTabla").position().top + 40) });
	var nombreStock=$("#idClaveHidden").val();
	$.ajax({
		type : "POST",
		url : "getTablePedido",
		data : {
			"stock.clave" : nombreStock,
			"transac.tipoComprob.nr" : 8
		},
		success : function(msg) {
			$("#idTableComprobante").html(msg);
			$("#idTableComprobante").show();
			$("#idLoadPedidoVtaImg").hide();
		}
	});			
							
}

function loadPedidoCompra() {						
	$("#idLoadPedidoCompraImg").show();
	$("div.uiComprob").css({position:'absolute', left: ($("#idFinTabla").position().left), top: ($("#idFinTabla").position().top + 40) });
	var nombreStock=$("#idClaveHidden").val();
	$.ajax({
		type : "POST",
		url : "getTablePedido",
		data : {
			"stock.clave" : nombreStock,
			"transac.tipoComprob.nr" : 15
		},
		success : function(msg) {
			$("#idTableComprobante").html(msg);
			$("#idTableComprobante").show();
			$("#idLoadPedidoCompraImg").hide();
		}
	});		
							
}


function callGetStock(articuloClave) {					
	$("#idLoadStock").show();
	$("#idShowStock").html("");
	$.ajax({
		type : "POST",
		url : "getFormulaStock",
		data : {
			"stock.clave" : articuloClave,
			"muestroBarraLateral" : false
		},
		success : function(msg) {
			$("#idShowStock").html(msg);
			$("#idLoadStock").hide();
		}
	});	
}
$(document).ready(function() {	
	callGetStock($("#idClave").text());
	
});
</script> 

