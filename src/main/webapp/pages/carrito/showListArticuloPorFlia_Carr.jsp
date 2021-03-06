<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec" %>
<%@ page import="jmc.skweb.core.model.Items" %>
<%@ page import="java.util.List" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="jmc.skweb.core.model.Usuario"%>
<%@page import="jmc.skweb.util.PreferenciasUtil"%>
<%@page import="jmc.skweb.util.Constants"%>


<script src="<%=request.getContextPath()%>/js/jquery.prettyPhoto.js" type="text/javascript" charset="utf-8"></script>
 
<link href="<%=request.getContextPath()%>/css/hints.css" rel="stylesheet" type="text/css" />

<link href="<%=request.getContextPath()%>/css/carrito_ama.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/prettyPhoto.css" type="text/css" media="screen" charset="utf-8" />


<% 
Integer cant = 0;
if ((List<Items>)request.getSession().getAttribute("items_CARR") != null && ((List<Items>)request.getSession().getAttribute("items_CARR")).size() > 0){
	cant = ((List<Items>)request.getSession().getAttribute("items_CARR")).size();
}
%>
<input type="hidden" id="idCantidadCarrito" value = "<%= cant %>" />
<script type="text/javascript">

$(document).ready(function() {
	
	
	if ($("#idCantidadCarrito").val() > 0){
		$("#idCarritoLlenoDIV").show();
	}else{
		$("#idCarritoLlenoDIV").hide();
	}
	
	$("#idCloseView").click(function() {
		$.unblockUI();
	});
	
	$("#idCarritoLlenoDIV").click(function() {
		muestroItemsCarr();
	});
});
function muestroItemsCarr() {					
	$("#idTableItemsCarrito").html("<img alt='' src='images/general/loading1.gif'>");
	$.ajax({
			  type: "POST",
			  url: "getItemsCarrito",
			  dataType: "script",
			  beforeSend: function(objeto){   				
			  },
			  success: function(msg){
				$("#idTableItemsCarrito").html(msg);			
			  },
			  error: function(objeto, quepaso, otroobj){
				alert("Debe cerrar la sesi�n y volver a ingresar");
			  }
		
			});
		
	
	$.blockUI({
		message : $('#idDivMuestroCarrito'),
		opacity : .1,
		color : 'black',
		css : {
			top : ($(window).height() - 100) / 2 + 'px',
			left : ($(window).width() - 900) / 2 + 'px',
			width : '900px'
		}
});
	
	
}

function callGetStockColores(articuloClave, idStrC) {					
	var idStr = "#idTableStockColor" + idStrC;
	var idLoadStr = "#idImageLoadStockColor" + idStrC;
	$(idLoadStr).show();
	$(idStr).html("");	
	$("#idTableStockColor").show();
	$("#idImageLoadStockColor").show();
	$.ajax({
		type : "POST",
		url : "getStockPorPiezasList",
		data : {
			"q" : articuloClave,
			"carrito" : 1,
			"muestroBarraLateral" : true
			
		},
		success : function(msg) {
			$(idStr).show();
			$(idStr).html(msg);
			$(idLoadStr).hide();
		}
	});
}

function callGetStock(articuloClave, idStrC) {					
		var idStr = "#divIdArt" + idStrC;
		var idLoadStr = "#idLoadStock" + idStrC;
		$(idLoadStr).show();
		$(idStr).html("");
		$.ajax({
			type : "POST",
			url : "getFormulaStock",
			data : {
				"stock.clave" : articuloClave,
				"carrito" : 1,
				"muestroBarraLateral" : true
				
			},
			success : function(msg) {
				$(idStr).show();
				$(idStr).html(msg);				
				$(idLoadStr).hide();
			}
		});	
}

function verDispo_CARR(articuloClave, idStrC) {					
	var idStr = "#divIdDispo" + idStrC;
	var idCantStr = "#idTextCantStock" + idStrC;
	if ($("#ultimoIdGetDispo").val() != ''){
		var tempo = $("#ultimoIdGetDispo").val();
		$(tempo).hide();
	}
	//alert(idStr);
	$("div.ui").css({position:'absolute', left: ($(idCantStr).position().left +300), top: ($(idCantStr).position().top -100) });					
	
	$(idStr).html("<img alt='' src='images/general/loading1.gif'>");
	
	$.ajax({
		type : "POST",
		url : "getFormulaStock",
		data : {
			"stock.clave" : articuloClave,
			"carrito" : 3,
			"muestroBarraLateral" : true
		},
		success : function(msg) {
			$(idStr).show(); 
			$(idStr).html(msg);
			$("#ultimoIdGetDispo").val(idStr);
		}
	});	
}



function deleteItem(itemNr) {					
	$.ajax({
		type : "POST",
		url : "deleteItemCarr",
		data : {
			"items.id.itemNr" : itemNr
		},
		success : function(msg) {
			if (msg == '0'){
				$("#idCarritoLlenoDIV").hide();
			}
			muestroItemsCarr();
		}
	});	
}

function addItem_CARR(articuloClave, idStrC) {					
	var idStr = "#divIdArt" + idStrC;
	var idLoadStr = "#idLoadStock" + idStrC;
	var idTextCantStock = "#idTextCantStock" + idStrC;
	//Bloquear Pantalla con reloj de espera
	var cant = $(idTextCantStock).val();
	
	if (cant == "" ){
		 $.blockUI({ 
				message:  '<br><h>La cantidad debe ser mayor a 0</h><br><br>'		
	        }); 	 
	        setTimeout($.unblockUI, 1500); 
	 	
	}else{
		$.blockUI({ 
			message:  '<br><h1>Por Favor aguarde...</h1><br>'		
	    }); 
		$.ajax({
			type : "POST",
			url : "addItem_Carr",
			data : {
				"items.cant1" : cant,
				"items.articulo" : articuloClave,
				"items.clave" : articuloClave
			},
			success : function(msg) {
				//Saco el cartel de espera
				$.unblockUI();
				//Borro el valor cargado
				$(idTextCantStock).val('');
				//Muestro el carrito lleno
				$("#idCarritoLlenoDIV").show();
			}
		});	
	}
}
function addItemColor_CARR(articuloClave, idStrC, idColor) {					
	//alert(articuloClave);
	var idStr = "#divIdArt" + idStrC;
	var idLoadStr = "#idLoadStock" + idStrC;
	var idTextCantStock = "#cantCarr" + idStrC + idColor ;
	//Bloquear Pantalla con reloj de espera
	var cant = $(idTextCantStock).val();
	
	if (cant == "" ){
		 $.blockUI({ 
				message:  '<br><h>La cantidad debe ser mayor a 0</h><br><br>'		
	        }); 	 
	        setTimeout($.unblockUI, 1500); 
	 	
	}else{
		$.blockUI({ 
			message:  '<br><h1>Por Favor aguarde...</h1><br>'		
	    }); 
		$.ajax({
			type : "POST",
			url : "addItemColor_Carr",
			data : {
				"items.cant1" : cant,
				"items.articulo" : articuloClave,
				"items.clave" : articuloClave,
				"items.colores.nr" : idColor
			},
			success : function(msg) {
				//Saco el cartel de espera
				$.unblockUI();
				//Borro el valor cargado
				$(idTextCantStock).val('');
				//Muestro el carrito lleno
				$("#idCarritoLlenoDIV").show();
			}
		});	
	}
}

function detectkey(evt,obj,clave,claveSBarras) {
	//keycode = (evt.keyCode==0) ? evt.which : evt.keyCode;
	if (evt.keyCode == 13){
		//alert(obj.value + String.fromCharCode(keycode));
		//alert(clave);
		addItem_CARR(clave,claveSBarras);
		}
	}
	
</script>
<%
			//Tomar el usuario desde el Request
			Usuario usuarioActual = (Usuario) request.getSession().getAttribute("usuario");
			int logicaColor = 0;
			if (PreferenciasUtil.comparePreferencia(usuarioActual.getListPreferencias(), Constants.PREF_ID_USA_LOGICA_STOCK_COLOR) == -1){
				logicaColor = -1;
			}
%>
<div class="main">

<s:hidden id="ultimoIdGetDispo"/>

<div align="right">

	<a href="preparedFindArticuloPorFlia_Carr"> <img
	src="<%=request.getContextPath()%>/images/general/back.png" width="50px" alt="Volver" title="Volver"
	 border="0"></img> </a> 
	 
	<%if (usuarioActual.getRol() == Constants.ID_USR_CLIENTE){%>
	<a href="<%=request.getContextPath()%>/preparedFindAgendadoPorNombre_Tran"> <img
	src="<%=request.getContextPath()%>/images/general/icon_ir_a_transac.png" alt="Volver" title="Volver"
	border="0"></img> </a>
	<a href="<%=request.getContextPath()%>/adminComprob_Tran"> <img
	src="<%=request.getContextPath()%>/images/general/icon_finalizar_pedido.png" alt="Volver" title="Volver"
	 border="0"></img> </a>

	<%}else{ %>
	<a href="<%=request.getContextPath()%>/menuAgendado_Tran"> <img
	src="<%=request.getContextPath()%>/images/general/icon_ir_a_transac.png" alt="Volver" title="Volver"
	border="0"></img> </a>	
	<%} %>
	<a href="<%=request.getContextPath()%>/home"> <img
	src="<%=request.getContextPath()%>/images/general/homev1.png" width="50px" alt="Inicio" title="Inicio"
	border="0"></img> </a>
	<a id="idCarritoLlenoDIV"><img src="<%=request.getContextPath()%>/images/general/icon_carrito_lleno.png" width="50px" alt="Ver Carrito" title="Ver Carrito" border="0"></img></a>
</div>
			
	<br>	
		<div class="panel_100 tab">
	    	<div class="space"></div>
           	<fieldset class="defaultFieldset">
    			<legend><b>Carrito - Consulta de Articulos </b></legend>
    
    				
						<label for="familia" class="label_00 field">Familia:</label>	 					 					
	 					<b><s:label id="fam" name="fam.desfam" /></b>
	 					<br>
						<label for="subfamilia" class="label_00 field">SubFamilia:</label>	 					 					
	 					<b><s:label id="subFam" name="subFam.desubfa" /></b>
					
					<ec:table					
						tableId="notReg" 
				    	items="articuloList"
						var = "articulo"
						locale="es_ES"
						action="findArticuloPorFlia_Carr"
						imagePath="images/table/*.gif"
						width="100%"
						rowsDisplayed="16" 
						filterRowsCallback="limit"
				    	retrieveRowsCallback="limit"
						sortRowsCallback="limit"
				    	view="limit"
						>
						<ec:row>
								<ec:column width="auto" title=" " filterable="true" property="descripcion" sortable="false" alias="read">
	          						<div id = "divIdDispo${articulo.claveSBarras}" class="ui"></div>
	          						<div id="atfResults" class="list results">
										<div id="result_0" class="fstRow">
										    <div class="image">
										        <a><img src="downLoadImageArticulo?stock.clave=${articulo.clave}" class="productImage" alt="Product Details"> </a></div>
											<h3 class="newaps">
										    	<h class="linkGrande">${articulo.descripC}</h>
										    </h3>
										<ul class="rsltL">
										    <li>
										    	<a><span class="bld red lrg">${articulo.precioTempStr}</span></a>
										    </li>
										   	<%
													if (logicaColor != -1){
											%>
										    <li>
										    	<div style="display: none" id="idLoadStock${articulo.claveSBarras}"><img alt="" src="images/general/loading1.gif"></div>
										    	<div id = "divIdArt${articulo.claveSBarras}"/>
										    	<script type="text/javascript">
										    		var idStrC = '${articulo.claveSBarras}';
										    		callGetStock('${articulo.clave}', idStrC);
										    	</script>
										    </li>
										    <%
													}
											%>
										</ul>
										<ul class="rsltR dkGrey">
											<li>
												<span class="bold orng">Observaciones:</span><h>${articulo.descriAmpliada}</h>
											</li>
											<br clear="all">
											<br clear="all">
											<li>
												<%
													if (logicaColor == -1){
												%>	
													<div align="center" id="idImageLoadStockColor${articulo.claveSBarras}" style="display: none;">					
														<img alt=""	src="images/general/loading1.gif">
													</div>
													<div align="center" id="idTableStockColor${articulo.claveSBarras}" style="display: none;">					
													</div><script type="text/javascript">
										    			var idStrCA = '${articulo.claveSBarras}';
										    			callGetStockColores('${articulo.clave}', idStrCA);
										    		</script>
												<%
													}else{
												%>
												<span class="bold orng">Cantidad:</span>
												<input id="idTextCantStock${articulo.claveSBarras}" class="bold orng" type="text" size="5" onkeypress="detectkey(event,this,'${articulo.clave}','${articulo.claveSBarras}')"/><a onclick="addItem_CARR('${articulo.clave}','${articulo.claveSBarras}');">
												<%
													}
												%>
												<%
													if (logicaColor != -1){
												%>
												<img id = "idAgregarProductos" src="<%=request.getContextPath()%>/images/general/icon_agregar_al_carrito.png" alt="Agregar al Carrito" title="Agregar al Carrito" border="0"></img></a>
												<script type="text/javascript">
										    		var idStrC = '${articulo.claveSBarras}';
										    		callGetStock('${articulo.clave}', idStrC);
										    	</script>		
										    	
										    	<a onclick="verDispo_CARR('${articulo.clave}','${articulo.claveSBarras}');">
												<img id = "idVerDispo" src="<%=request.getContextPath()%>/images/general/lupa_estadistica.png" alt="Ver Disponibilidad" title="Ver Disponibilidad" border="0"></img></a>
										    	<%
													}
												%>								
											</li>
										</ul>
										
										
										<br clear="all">
										</div>
										
									</div>
	          					</ec:column>								
						</ec:row>
					</ec:table>
				<div class="space"></div> 
			</fieldset>		
		</div>	
</div>		

<div style="display: none" id = "idShowUploadDiv">
	<s:form action="uploadArticuloImage" id="idUploadArticuloImageForm" enctype="multipart/form-data" theme="simple" method="POST">
	<s:hidden key = "stock.clave"  id="idKeyArticuloHidden" />
	<s:hidden key = "subFam.nrsubfam"  id="idSubFamHidden" />
	<s:hidden key = "fam.nrfam"  id="idFamHidden" />
	<s:hidden id="idArrayImagenesHidden" />
	<s:hidden id="idArrayTitleHidden" />
	<s:hidden id="idArrayObsHidden" />
	
	<div class="space"></div>	
				    <table align="center">
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

<s:form action="showArticulo" id="showArticuloForm">
	<s:hidden key="stock.clave" id="idArticulo1"/>
</s:form>

<center>
<div style="display: none" align="center" id="idDivMuestroCarrito" >
		<div class="panel_100 tab">
	    	<div class="space"></div>
		<fieldset class="defaultFieldset">
		<div align="right" id="idCloseView"><img alt='' src='images/general/cerrarX_v1.png'></div>
		
    			<legend><b>Gesti�n del Carrito - Items cargados</b></legend>		
		<div id="idTableItemsCarrito"/>			
		</fieldset>		
		</div>	
</div>
</center>
