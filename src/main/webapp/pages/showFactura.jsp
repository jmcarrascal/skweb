<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec" %>
 
<link href="<%=request.getContextPath()%>/css/hints.css" rel="stylesheet" type="text/css" />


<div class="main">
	
	<jsp:include page="add_on/botones_basicos.jsp"/>
	<div class="space"></div>
	
	<div class="panel_100 tab">
		<div class="space"></div>
		
		<fieldset class="defaultFieldset" id="field_Reportes" >
			<legend>Facturas</legend>

			<s:form action="downloadFactura" id="listFacturaForm" theme="simple" cssClass="inline" method="post" >
						
 				<table>
 				
 					<tr>
	 					<td>
	 						<label class="label_33 field">Si no se ingresa el nro de Comprobante se obtiene el ultimo informado</label>
							<div class="space"></div>
			
	 					</td>
						<div class="separator"></div>
 					</tr>
				</table>
				<table>
 				<tr>
					<td>
	 					<label class="label_33 field">Punto de Venta:</label>	 				
	 					<input type="text" name="prefijo" class="label_10 inputData" maxlength="4" size="4" >					
		 			</td>
		 			<td>
	 					<label class="label_33 field">Tipo de Comprobante:</label>	 				
						<input type="text" name="tipoComprobante" class="label_10 inputData" maxlength="2" size = "2" >
					</td>					
	 				<td>
	 					<label class="label_33 field">Numero de Comprobante:</label>	 				
						<input type="text" name="nroComprobante" class="label_10 inputData" maxlength="8" size = "8" >
					</td>
					<td>					
						<div class="buttonPanel" style="width:15%; float:left;">
							<a href="#"  style="margin: 0px;" onclick="submitForm('listFacturaForm');"><span>Aceptar</span></a>
						</div>
					</td>
				</tr>
				</table>
			</s:form>
	
			<div class="space"></div>
						
			
			<div class="space"></div>
		</fieldset>

		<div class="space"></div>
	</div>
</div>

<script language="javascript">
function submitForm(formId) { 
	document.getElementById(formId).submit();
}
</script> 

