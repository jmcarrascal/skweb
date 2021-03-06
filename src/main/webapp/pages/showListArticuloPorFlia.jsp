<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="jmc.skweb.core.model.Usuario"%>
<%@page import="jmc.skweb.util.PreferenciasUtil"%>
<%@page import="jmc.skweb.util.Constants"%>

<script src="<%=request.getContextPath()%>/js/jquery.prettyPhoto.js" type="text/javascript" charset="utf-8"></script>
 
<link href="<%=request.getContextPath()%>/css/hints.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/prettyPhoto.css" type="text/css" media="screen" charset="utf-8" />

<script type="text/javascript">
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
			"muestroBarraLateral" : false
		},
		success : function(msg) {
			$(idStr).show();
			$(idStr).html(msg);				
			$(idLoadStr).hide();
		}
	});	
}
	
function viewArticulo(idArt){	
	document.getElementById("idArticulo1").value=idArt;
	document.getElementById("showArticuloForm").submit();
}

function uploadImageArticulo(idArt, nombreArt){	
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
function uploadArticulo(){		
	$("#idUploadingDiv").show();
	$("#idUploadArticuloImageForm").submit();
}


</script>
<s:hidden id = "muestraExistencias" value="0"/>
<div class="main">
<jsp:include page="add_on/botones_basicos.jsp"/>
	
		<%
			//Tomar el usuario desde el Request
			Usuario usuarioActual = (Usuario) request.getSession().getAttribute("usuario");
			if (PreferenciasUtil.comparePreferencia(usuarioActual.getListPreferencias(), Constants.PREF_ID_MUESTRA_EXISTENCIAS) == -1){
		%>
		<script type="text/javascript">
			$("#muestraExistencias").val(-1);
		</script>
		<%} %>
			
	<br>	
		<div class="panel_100 tab">
	    	<div class="space"></div>
           	<fieldset class="defaultFieldset">
    			<legend><b>Articulos por Familia - SubFamilia</b></legend>
    
    			<div class="space"></div>				
						<label for="familia" class="label_33 field">Familia:</label>	 					 					
	 					<b><s:label id="fam" name="fam.desfam" /></b>
	 					<br>
						<label for="subfamilia" class="label_33 field">SubFamilia:</label>	 					 					
	 					<b><s:label id="subFam" name="subFam.desubfa" /></b>
					
					<ec:table					
						tableId="notReg" 
				    	items="articuloList"
						var = "articulo"
						locale="es_ES"
						action="findArticuloPorFlia"
						imagePath="images/table/*.gif"
						width="100%"
						rowsDisplayed="50" >
						<ec:row>
								<ec:column width="40px" title=" " filterable="false" sortable="false" alias="read">      						
	          							<img src="images/general/view.png" alt="Ver Stock" title="Ver Stock" onclick="viewArticulo('${articulo.clave}')" border="0" id="read${articulo.claveSBarras}"></img>
										<script type="text/javascript">											
										if ($("#muestraExistencias").val() == 0){
											var t = "read${articulo.claveSBarras}";
											t = '#' + t;
											$(t).hide();
										}
									</script>
									</a>
	          					</ec:column>								
								<ec:column width="120px" filterable="true"  property="clave" title="Clave Articulo" sortable="true"  />
								<ec:column width="auto" filterable="true" property="descripcion" title="Descripción"/>
								
								<ec:column width="200px" title=" " filterable="false" sortable="false" alias="st">
									<a href="#">
										    	<div style="display: none" id="idLoadStock${articulo.claveSBarras}"><img alt="" src="images/general/loading1.gif"></div>
										    	<div id = "divIdArt${articulo.claveSBarras}"/>
										    	<script type="text/javascript">
										    		var idStrC = '${articulo.claveSBarras}';
										    		callGetStock('${articulo.clave}', idStrC);
										    	</script>
									</a>	    	
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

<s:form action="showArticulo" id="showArticuloForm">
	<s:hidden key="stock.clave" id="idArticulo1"/>
</s:form>

