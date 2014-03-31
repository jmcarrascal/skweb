<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec" %>
 
<link href="<%=request.getContextPath()%>/css/hints.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/styles.css" rel="stylesheet" type="text/css" />






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
    			<legend><b>Solo Impago - Detalle por Agendado</b></legend>
    			
	 						<table>					
				<tr>
					<td align="left">
	 					<s:label id="idRazonSocial" name="gente.descripC" cssClass="entidadGrande"/>		
					</td>										
				</tr>				

				</table>
					<div class = "separator"></div>
					<ec:table					
						tableId="notReg" 
				    	items="transacList"
						var = "transac"
						locale="es_ES"
						action="getSoloImpagosVentaPorGente_CC"
						imagePath="images/table/*.gif"
						width="100%"
						rowsDisplayed="100" >						
						<ec:exportPdf view="pdf" headerColor="white" headerTitle='<%=String.valueOf((request.getAttribute("grillaResult")))%>' fileName="soloImpagos.pdf" tooltip="Exportar PDF" />
						<ec:exportXls view="xls" text='<%=String.valueOf((request.getAttribute("grillaResult")))%>' fileName="soloImpagos.xls" tooltip="Exportar Excel"/>
						<ec:row>
								<ec:column width="50px" title=" " filterable="false" sortable="false" alias="read" viewsAllowed="html">
									<a href="#" id="items${transac.transacNr}><img src="images/general/apa rt.png" alt="Ver Items"  title="Ver Items" on></img></a>
									<script type="text/javascript">											
											$("#items${transac.transacNr}").bind("click", function() {
												viewItems('${transac.transacNr}');
											});
									</script>
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
								<ec:column width="80px" property="fecha" title="Fecha" viewsAllowed="html">
									<span style="white-space:nowrap;" >${transac.formattedDate}</span>
								</ec:column>
								<ec:column width="80px" property="formattedDate" title="Fecha" viewsDenied="html"/>

								<ec:column width="40px" property="tipoComprob.descripcion" title="TipoComprob">
									<span style="white-space:nowrap;" >${transac.tipoComprob.descripcion}</span>
								</ec:column>
								<ec:column width="20px" property="letra" title="Letra">
									<span style="white-space:nowrap;" >${transac.letra}</span>
								</ec:column>								
								<ec:column width="40px" property="prefijo" title="Prefijo">
									<span style="white-space:nowrap;" >${transac.prefijo}</span>
								</ec:column>								
								<ec:column width="60px" property="nrComprob" title="NrComprob">
									<span style="white-space:nowrap;" >${transac.nrComprob}</span>
								</ec:column>								
								<ec:column width="60px" property="totalCalculado" title="Total Comprob">
									<span style="white-space:nowrap;" >${transac.totalCalculado}</span>
								</ec:column>								
								<ec:column width="60px" property="saldoCalculado" title="Saldo Comprob">
									<span style="white-space:nowrap;" class="tableRow${transac.signo}">${transac.saldoCalculado}</span>
								</ec:column>
								<ec:column width="60px" property="saldoAcumulado" title="Saldo Arrastre">
									<span style="white-space:nowrap;" class="tableRow${transac.signoN}">${transac.saldoAcumulado}</span>
								</ec:column>
																
								
						</ec:row>
					</ec:table>
				<div class="space"></div> 
						<label for="totalSaldo" class="totales">Total Saldo:</label>	 					 					
	 					<b><s:label id="totalSaldo2" cssClass="totales" name="totalSaldo" /></b>
				
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