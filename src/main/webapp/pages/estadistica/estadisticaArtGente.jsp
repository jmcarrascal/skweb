<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec" %>
 
<link href="<%=request.getContextPath()%>/css/hints.css" rel="stylesheet" type="text/css" />


<div class="main">
<div align="right"><a href="menuAgendado_Tran"> <img
	src="<%=request.getContextPath()%>/images/general/back.png" width="50px" alt="Volver" title="Volver"
	 border="0"></img> </a> <a href="<%=request.getContextPath()%>/home"> <img
	src="<%=request.getContextPath()%>/images/general/homev1.png" width="50px" alt="Inicio" title="Inicio"
	border="0"></img> </a>
	</div>
			<div class="panel_100 tab">
	    	<div class="space"></div>
		
		   	<fieldset class="defaultFieldset">
    			<legend><b>Reporte estadistica por Articulo Agendado</b></legend>
				
					<div class = "separator"></div>
					<ec:table					
						tableId="notReg" 
				    	items="itemsList"
						var = "items"
						locale="es_ES"
						action="estadisticaArtGente"
						imagePath="images/table/*.gif"
						width="100%"
						rowsDisplayed="100" >						
						<ec:row>
						<ec:exportPdf view="pdf" headerColor="white" headerTitle='Reporte por Comprobante' fileName="reporteComprobante.pdf" tooltip="Exportar PDF" />
						<ec:exportXls view="xls"  fileName="reporteComprobante.xls" tooltip="Exportar Excel"/>
								<ec:column width="100px" property="clave" title="Clave" >
									<span style="white-space:nowrap;" >${items.clave}</span>
								</ec:column>
								<ec:column width="250px" property="descrip" title="Descripción" sortable="true" filterable="true">
									<span style="white-space:nowrap;" >${items.descrip}</span>
								</ec:column>
								<ec:column width="100px" property="cant1" title="Cantidad" sortable="false" filterable="false">
									<span style="white-space:nowrap;" >${items.cant1}</span>
								</ec:column>
								<ec:column width="20px" property="cant1entregado" title="Entregado" sortable="false" filterable="false">
									<span style="white-space:nowrap;" >${items.cant1entregado}</span>
								</ec:column>								
								<ec:column width="40px" property="saldo" title="Saldo" sortable="false" filterable="false">
									<span style="white-space:nowrap;" >${items.saldo}</span>
								</ec:column>								
													
						</ec:row>
					</ec:table>
				<div class="space"></div> 
				
			</fieldset>		
			</div>
			
</div>		

<s:form action="findArticuloPorProveedor" id="showArticuloForm">
	<s:hidden key="gente.genteNr" id="idGente"/>
</s:form>

<s:form action="downloadPdf"  id="descargaPdfForm">
	<s:hidden key="transac.transacNr" id="rutaPdf"/>
</s:form>

<script type="text/javascript">
function viewArticulos(idGente){	
	document.getElementById("idGente").value=idGente;
	document.getElementById("showArticuloForm").submit();
}

function downloadPdf(rutaPdf){		
	document.getElementById('rutaPdf').value = rutaPdf;
	$('#descargaPdfForm').submit();
}
</script>