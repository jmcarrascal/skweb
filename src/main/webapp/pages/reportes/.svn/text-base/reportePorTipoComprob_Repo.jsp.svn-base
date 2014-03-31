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
    			<legend><b>Reporte por Comprobante</b></legend>
				<s:form action="downloadFactura" id="listFacturaForm" theme="simple" cssClass="inline" method="post" >			    			
    			<table>					
				<tr>
					<td align="left">
	 					<s:label id="idRazonSocial" name="gente.descripC" cssClass="entidadGrande"/>		
					</td>										
				</tr>
				<tr>
					<td align="left">
	 					<label class="label_33 field">Tipo de Comprobante:</label>	 					 					
	 					<b><s:label name="transac.tipoComprob.descripcion" /></b>					
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
						action="reportePorTipoComprob_Repo"
						imagePath="images/table/*.gif"
						width="100%"
						rowsDisplayed="100" >						
						<ec:row>
						<ec:exportPdf view="pdf" headerColor="white" headerTitle='Reporte por Comprobante' fileName="reporteComprobante.pdf" tooltip="Exportar PDF" />
						<ec:exportXls view="xls"  fileName="reporteComprobante.xls" tooltip="Exportar Excel"/>
								<ec:column width="50px" title=" " filterable="false" sortable="false" alias="read" viewsAllowed="html">
									<a href="#" id="linkPdf${transac.transacNr}"><img src="images/general/downloadPdf${transac.tienePdf}.png" alt="Descarga Comprobante"  title="Descarga Comprobante"></img></a>
									<script type="text/javascript">											
										if (${transac.tienePdf}) {
											$("#linkPdf${transac.transacNr}").bind("click", function() {
												downloadPdf('${transac.transacNr}');
											});
										}
									</script>
									<a href="#" id="linkMail${transac.transacNr}"><img src="images/general/endProject${transac.tienePdf}.png" alt="Enviar el Comprobante por Correo al Cliente"  title="Enviar el Comprobante por Correo al Cliente"></img></a>
									<script type="text/javascript">											
										if (${transac.tienePdf}) {
											$("#linkMail${transac.transacNr}").bind("click", function() {
												sendComprobCliente('${transac.transacNr}');
											});
										}
									</script>
								</ec:column>
								
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
								<ec:column width="auto" property="observaciones" title="Observaciones" sortable="false" filterable="false">
									<span  >${transac.observaciones}</span>
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