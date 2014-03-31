<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec" %>
 
<link href="<%=request.getContextPath()%>/css/hints.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/styles.css" rel="stylesheet" type="text/css" />






<div class="main">
<div align="right"><a href="preparedListRemitosPendientes"> <img
	src="<%=request.getContextPath()%>/images/general/back.png" width="50px" alt="Volver" title="Volver"
	 border="0"></img> </a> <a href="<%=request.getContextPath()%>/home"> <img
	src="<%=request.getContextPath()%>/images/general/homev1.png" width="50px" alt="Inicio" title="Inicio"
	border="0"></img> </a>
	</div>
			
		   	<fieldset class="defaultFieldset">
    			<legend><b>Recepcionar por Remito(SK) - Selección de Remito</b></legend>    			
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
						action="preparedListRemitosPendientes"
						imagePath="images/table/*.gif"
						width="100%"
						rowsDisplayed="100" >						
						<ec:exportPdf view="pdf" headerColor="white" headerTitle='<%=String.valueOf((request.getAttribute("grillaResult")))%>' fileName="soloImpagos.pdf" tooltip="Exportar PDF" />
						<ec:exportXls view="xls" text='<%=String.valueOf((request.getAttribute("grillaResult")))%>' fileName="soloImpagos.xls" tooltip="Exportar Excel"/>
						<ec:row>
								<ec:column width="30px" title=" " filterable="false" sortable="false" alias="read" viewsAllowed="html">
									<a href="#">
	          							<img src="images/general/view.png" alt="Medicamentos" title="Medicamentos" onclick="viewMedicamentos('${transac.transacNr}')" border="0" id="read${articulo.clave}"></img>
									</a>
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
								
						</ec:row>
					</ec:table>				
			</fieldset>					
</div>		

<s:form action="viewTrazaIngreso" id="showMedicamentosForm">
	<s:hidden key="gente.genteNr" />
	<s:hidden key="transac.transacNr" id="transacNr1"/>
</s:form>


<script type="text/javascript">


function viewMedicamentos(transacNr){	
	document.getElementById("transacNr1").value=transacNr;
	document.getElementById("showMedicamentosForm").submit();
}

</script>