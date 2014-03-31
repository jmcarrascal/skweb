<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec" %>
 
<link href="<%=request.getContextPath()%>/css/hints.css" rel="stylesheet" type="text/css" />


<div class="main">
<jsp:include page="add_on/botones_basicos.jsp"/>
			
		   	<fieldset class="defaultFieldset">
    			<legend><b>Resumen Cuenta Corriente - Detalle </b></legend>
				<s:form action="downloadFactura" id="listFacturaForm" theme="simple" cssClass="inline" method="post" >			    			
    			<table>
					
				<tr>
					<td align="left">
	 					<label class="label_33 field">Agendado:</label>	 					 					
	 					<b><s:label id="gente.razonSocial" name="gente.descripC" /></b>					
					</td>	
									
				</tr>
				</table>
    			
	 					

	 					
			</s:form>
					<div class = "separator"></div>
					<ec:table					
						tableId="notReg" 
				    	items="transacList"
						var = "transac"
						locale="es_ES"
						action="resumen_CC_C"
						imagePath="images/table/*.gif"
						width="100%"
						rowsDisplayed="100" >						
						<ec:row>
								<ec:column width="40px" property="transacNr" title="TransacNr" sortable="false" filterable="false">
									<span style="white-space:nowrap;" >${transac.transacNr}</span>
								</ec:column>
								<ec:column width="80px" property="fecha" title="Fecha" viewsAllowed="html" sortable="true" filterable="false">
									<span style="white-space:nowrap;" >${transac.formattedDate}</span>
								</ec:column>
								<ec:column width="80px" property="formattedDate" title="Fecha" viewsDenied="html"/>

								<ec:column width="40px" property="tipoComprob.descripcion" title="TipoComprob" sortable="false" filterable="false">
									<span style="white-space:nowrap;" >${transac.tipoComprob.descripcion}</span>
								</ec:column>
								<ec:column width="20px" property="letra" title="Letra" sortable="false" filterable="false">
									<span style="white-space:nowrap;" >${transac.letra}</span>
								</ec:column>								
								<ec:column width="40px" property="prefijo" title="Prefijo" sortable="false" filterable="false">
									<span style="white-space:nowrap;" >${transac.prefijo}</span>
								</ec:column>								
								<ec:column width="60px" property="nrComprob" title="NrComprob" sortable="false" filterable="false">
									<span style="white-space:nowrap;" >${transac.nrComprob}</span>
								</ec:column>								
								<ec:column width="60px" property="totalCalculado" title="Total Comprob" sortable="false" filterable="false">
									<span style="white-space:nowrap;" >${transac.totalCalculado}</span>
								</ec:column>								
								<ec:column width="60px" property="saldoCalculado" title="Saldo Comprob" sortable="false" filterable="false">
									<span style="white-space:nowrap;" >${transac.saldoCalculado}</span>
								</ec:column>
								<ec:column width="60px" property="saldoAcumulado" title="Saldo Arrastre" sortable="false" filterable="false">
									<span style="white-space:nowrap;"  >${transac.saldoAcumulado}</span>
								</ec:column>
								<ec:column width="60px" property="shortObservaciones" title="Observaciones" sortable="false" filterable="false">
									<span style="white-space:nowrap;" >${transac.shortObservaciones}</span>
								</ec:column>								

								
																
								
						</ec:row>
					</ec:table>
				<div class="space"></div> 
				
			</fieldset>		
			
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