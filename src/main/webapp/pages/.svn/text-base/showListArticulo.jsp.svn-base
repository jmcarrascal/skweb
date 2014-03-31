<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec" %>
 
<link href="<%=request.getContextPath()%>/css/hints.css" rel="stylesheet" type="text/css" />


<div class="main">
<jsp:include page="add_on/botones_basicos.jsp"/>
			
	<br>	
		<div class="panel_100 tab">
	    	<div class="space"></div>
           	<fieldset class="defaultFieldset">
    			<legend><b>Articulos</b></legend>
    			<div class="space"></div>
				<s:actionerror/>	
					<ec:table					
						tableId="notReg" 
				    	items="articuloList"
						var = "articulo"
						locale="es_ES"
						action="findArticuloPorNombre"
						imagePath="images/table/*.gif"
						width="100%"
						rowsDisplayed="100" >
						<ec:row>
								<ec:column width="40px" title=" " filterable="false" sortable="false" alias="read">
	          						<a href="#">
	          							<img src="images/general/view.png" alt="Ver Stock" title=" Ver Stock" onclick="viewArticulo('${articulo.clave}')" border="0" id="read${articulo.clave}"></img>
									</a>
	          					</ec:column>								
								<ec:column width="120px" filterable="true"  property="clave" title="Clave Articulo" sortable="true"  />
								<ec:column width="auto" filterable="true" property="descripcion" title="Descripción"/>
						</ec:row>
					</ec:table>
				<div class="space"></div> 
			</fieldset>		
		</div>	
</div>		

<s:form action="showArticulo" id="showArticuloForm">
	<s:hidden key="stock.clave" id="idArticulo1"/>
</s:form>

<script type="text/javascript">
function viewArticulo(idArt){	
	document.getElementById("idArticulo1").value=idArt;
	document.getElementById("showArticuloForm").submit();
}
</script>