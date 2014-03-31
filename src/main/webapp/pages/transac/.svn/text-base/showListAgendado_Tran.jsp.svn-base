<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec" %>
 
<link href="<%=request.getContextPath()%>/css/hints.css" rel="stylesheet" type="text/css" />


<div class="main">
			<jsp:include page="../add_on/botones_basicos.jsp"/>
	
           	<fieldset class="defaultFieldset">
    			<legend><b>Transacciones - Agendados</b></legend>
    			<div class="space"></div>
				<s:actionerror/>	
					<ec:table					
						tableId="notReg" 
				    	items="genteList"
						var = "gente"
						locale="es_ES"
						action="findAgendadoPorNombre_CR"
						imagePath="images/table/*.gif"
						width="100%"
						rowsDisplayed="100" >
						<ec:row>
								<ec:column width="40px" title=" " filterable="false" sortable="false" alias="read">
	          						<a href="#">
	          							<img src="images/general/view.png" alt="Cheques Rechazados - Movimientos" title="Orden de Pedido " onclick="viewOP('${gente.genteNr}')" border="0" id="read${articulo.clave}"></img>
									</a>
	          					</ec:column>								
								<ec:column width="40px" property="genteNr" title="Numero" sortable="false" filterable="false"/>
								<ec:column width="auto" property="razonSocial" title="Razon Social"/>
								<ec:column width="auto" property="cuit" title="Cuit"/>
						</ec:row>
					</ec:table>
				<div class="space"></div> 
			</fieldset>		
		
</div>		

<s:form action="adminOrdenPedido_OP" id="newOrdenPedidoForm">
	<s:hidden key="gente.genteNr" id="idGente"/>
</s:form>

<script type="text/javascript">
function viewOP(idGente){	
	document.getElementById("idGente").value=idGente;
	document.getElementById("newOrdenPedidoForm").submit();
}
</script>