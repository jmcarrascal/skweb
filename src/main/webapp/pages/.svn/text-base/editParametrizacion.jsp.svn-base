<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="main">
	
	<div class="panel_100 tab">
	    	
		<div class="space"></div>

		<fieldset class="defaultFieldset" >
			<legend>Editar Parametrización</legend>

				<s:form action="editParametrizacion" id="addParametrizacionForm" theme="simple" cssClass="inline" method="post" >
						
					<table>
						<tr>
		 					<td><label for="id">Id:</label></td>	 				
							<td><s:textfield id="idParametrizacion" name="parametrizacion.idParametrizacion" size="50" maxlength="50" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" readonly="true"/></td>
						<tr>
						<tr>
		 					<td><label for="id">Descripción:</label></td>	 				
							<td><s:textfield id="descrip" name="parametrizacion.descrip" size="50" maxlength="50" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all"/></td>
						<tr>
						<tr>
		 					<td><label for="id">* Valor:</label></td>	 				
							<td><s:textfield id="valor" name="parametrizacion.valor" size="50" maxlength="200" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all"/></td>
						<tr>

					</table>
								
					
	
					
				</s:form>
					<center>
						<div id="guardar" style="display:inline;">
								<img src="images/general/icon_guardar.png" alt="Guardar" border="0">									
						</div>
					</center>
			

			
		</fieldset>
	 			
	 	<div class="space"></div>
	 	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#guardar").click(function (){
		document.getElementById('addParametrizacionForm').submit();
	});
	
});

</script>