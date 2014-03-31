<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>


<link href="<%=request.getContextPath()%>/css/styles.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/popup.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/js/popup.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/jquery.autocomplete.js" type="text/javascript"></script>
 
<script>
$(document).ready(function(){
	$("#consultar").click(function (){
		document.getElementById('findArtMadreForm').submit();
	});

});
</script>
<div class = "main">
<jsp:include page="add_on/botones_basicos.jsp"/>
	<s:form id="findArtMadreForm" action="findArtMadrePorNombre" theme="simple" cssClass="inline" method="post" >		
		<fieldset class="defaultFieldset" >
		<legend><b>Consulta de Stock - Busqueda de Articulo Madre por Nombre</b></legend>
		
		<div class = "space"></div>
		
	    <table>
			<tr>
				<td><label for="nombreArtMadre">Ingrese una porción del nombre del articulo madre a buscar:</label></td>
			   	<td><s:textfield id="nombreArtMadre" name="artMadre.descArtMad" size="50" maxlength="50" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all"/></td>
			</tr>
	    </table>
		<center>
			<div id="consultar" style="display:inline;">
					<img src="images/general/icon_buscar.png" alt="Consultar" border="0" >		
				
			</div>
		</center>
		</fieldset>
	</s:form>
</div>

