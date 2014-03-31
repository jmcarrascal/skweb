<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec" %>
 
<link href="<%=request.getContextPath()%>/css/hints.css" rel="stylesheet" type="text/css" />


<div class="main">
<jsp:include page="add_on/botones_basicos.jsp"/>
			
	<br>	
		<div class="panel_100 tab">
	    	<div class="space"></div>
           	<fieldset class="defaultFieldset">
    			<legend><b>Articulos Madre</b></legend>
    			<div class="space"></div>
				<s:actionerror/>	
					<ec:table					
						tableId="notReg" 
				    	items="artMadreList"
						var = "artMadre"
						locale="es_ES"
						action="findArtMadrePorNombre"
						imagePath="images/table/*.gif"
						width="100%"
						rowsDisplayed="100" >
						<ec:row>
								<ec:column width="40px" title=" " filterable="false" sortable="false" alias="read">
	          						<a href="#">
	          							<img src="images/general/view.png" alt="Ver Articulos Hijos" title=" Ver Articulos Hijos " onclick="viewArticulo('${artMadre.codArtMad}')" border="0" ></img>
									</a>
	          					</ec:column>								
								<ec:column width="120px" filterable="true"  property="codArtMad" title="Clave Articulo" sortable="true"  />
								<ec:column width="auto" filterable="true" property="descArtMad" title="Descripción"/>
								<ec:column width="auto" filterable="true" property="obser" title="Observaciones"/>
						</ec:row>
					</ec:table>
				<div class="space"></div> 
			</fieldset>		
		</div>	
</div>		

<s:form action="showListArticulosPorArtMadre" id="showArticuloForm">
	<s:hidden key="artMadre.codArtMad" id="idArticuloMadre"/>
</s:form>

<script type="text/javascript">
function viewArticulo(idArt){	
	document.getElementById("idArticuloMadre").value=idArt;
	document.getElementById("showArticuloForm").submit();
}
</script>