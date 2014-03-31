<%@ taglib uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="main">

	<div class="space"></div>
	<div class="panel_100 tab">
    	
		<div class="space"></div>
    	
    	<fieldset class="defaultFieldset">
    	<legend>Lista de Parametrizacion</legend>
    	
					<ec:table 
						tableId="notReg"
				    	items="listParametrizacion"
						var = "parametrizacion"
						locale="es_ES"
						action="preparedListParametrizacion"
						imagePath="images/table/*.gif"
						width="100%"
						rowsDisplayed="100" >
						<ec:row>
								<ec:column width="40px" property="idParametrizacion" title="Id" filterable="false" />
								<ec:column width="200px" property="descrip" title="Descripción" filterable="true" />
								<ec:column width="200px" property="valor" title="Valor" filterable="true" />
								<ec:column width="40px" title="Edit" filterable="false" sortable="false" alias = "checkbox">
									<a href="#">
										<img src="images/general/view.gif" alt="Editar parametrizacion" border="0" onclick="editParametrizacion('${parametrizacion.idParametrizacion}')"></img>
									</a>
								</ec:column>
								
						</ec:row>
					</ec:table>
    	</fieldset>

		

	</div>
	
</div>

<s:form action="preparedEditParametrizacion" id="editParametrizacionForm">
	<s:hidden key="parametrizacion.idParametrizacion" id="idParametrizacionF"/>
</s:form>


 
<script type="text/javascript">

function editParametrizacion(idParametrizacion){
	document.getElementById("idParametrizacionF").value=idParametrizacion;
	document.getElementById("editParametrizacionForm").submit();
}

</script>