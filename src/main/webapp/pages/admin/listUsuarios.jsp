<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec" %>
 
<link href="<%=request.getContextPath()%>/css/hints.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/blockuiv1.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/popup.css" rel="stylesheet" type="text/css" />

<script src="<%=request.getContextPath()%>/js/jquery.blockUI.js" type="text/javascript"></script>



<div class="main">
			<jsp:include page="../add_on/botones_basicos.jsp"/>
	<br>	
		<div class="panel_100 tab">
	    	<div class="space"></div>
           	<fieldset class="defaultFieldset">
    			<legend><b>Usuarios Web</b></legend>
    			<div class="space"></div>
				<s:actionerror/>	
					<ec:table					
						tableId="notReg" 
				    	items="usuarioList"
						var = "usuario"
						locale="es_ES"
						action="getUsuarios"
						imagePath="images/table/*.gif"
						width="100%"
						rowsDisplayed="100" >
						<ec:row>
								<ec:column width="40px" title=" " filterable="false" sortable="false" alias="read">
	          						<a href="#">
	          							<img src="images/general/edit.png" alt="Editar Usuario" title=" Editar Usuario " onclick="editUser('${usuario.idUsuarioWeb}')" border="0" ></img>
									</a>
	          						<a href="#">
	          							<img src="images/general/remove.png" alt="Eliminar Usuario" title=" Eliminar Usuario " onclick="confirmRemoveUser(${usuario.idUsuarioWeb},'${usuario.usuario}')" border="0" ></img>
									</a>

	          					</ec:column>								
								<ec:column width="auto" property="usuario" title="UserName" >
									<span style="white-space:nowrap;" >${usuario.usuario}</span>
								</ec:column>

								<ec:column width="auto" property="nombre" title="Nombre" >
									<span style="white-space:nowrap;" >${usuario.nombre}</span>
								</ec:column>
								
								<ec:column width="auto" property="apellido" title="Apellido">
									<span style="white-space:nowrap;" >${usuario.apellido}</span>
								</ec:column>

								<ec:column width="auto" property="rol.descrip" title="Rol" filterCell="droplist">
									<span style="white-space:nowrap;" >${usuario.rol.descrip}</span>
								</ec:column>
								
								<ec:column width="auto" property="lastLogin" title="Ultimo Ingreso" filterable="false" sortable="true">
									<span style="white-space:nowrap;" >${usuario.lastLogin}</span>
								</ec:column>

								<ec:column width="auto" property="email" title="Email" filterable="true">
									<span style="white-space:nowrap;" >${usuario.email}</span>
								</ec:column>

								<ec:column width="auto" property="empresaWeb.nombre" title="Empresa" filterCell="droplist">
									<span style="white-space:nowrap;" >${usuario.empresaWeb.nombre}</span>
								</ec:column>
								
						</ec:row>
					</ec:table>
				<div class="space"></div> 
			</fieldset>		
		</div>	
</div>		

<s:form action="preparedEditUsuario" id="editUserForm">
	<s:hidden key="usuarioWeb.idUsuarioWeb" id="idUsuario"/>	
</s:form>

<div style="display: none" align="center" id="respuestaForm">		
		<s:label for="idMsgSave" id="idMsgSave" />
		<div class="space"></div>
		<div class="space"></div>
		<div class="buttonPanel" id="buttonDown" align="center">
			<ul class="content_1x128">							
				<a href="#" onclick="removeUser();">
					<img src="images/general/icon_guardar.png" alt="Aceptar" border="0" style="float:left;margin-top:0px;"></img>
				</a>						
			</ul>
		</div>						
		<div class="buttonPanel" id="buttonDown" align="center">
			<ul class="content_1x128">							
				<a href="#" onclick="closeBlock();">
					<img src="images/general/icon_cancelar.png" alt="Cancelar" border="0" style="float:right;margin-top:0px;"></img>
				</a>						
			</ul>
		</div>						
		
		
</div>

<s:form action="removeUsuario" id="removeUserForm">
	<s:hidden key="usuarioWeb.idUsuarioWeb" id="idUsuarioRemove"/>	
</s:form>

<script type="text/javascript">
function editUser(idUsuario){	
	document.getElementById("idUsuario").value=idUsuario;
	document.getElementById("editUserForm").submit();
}
function removeUser(){		
	document.getElementById("removeUserForm").submit();
}
function confirmRemoveUser(idUsuario, user){	
	var msg = 'Esta seguro de eliminar el usuario ' + user + '?'
	document.getElementById("idMsgSave").innerHTML = msg;
	$.blockUI({
		theme:   true, 
		title: 'Eliminar Usuario',
        message: $('#respuestaForm'), 
        opacity: .1, 
        color: 'black'
	});
	document.getElementById("idUsuarioRemove").value=idUsuario;	
}
function closeBlock(){	
	$.unblockUI();
}


</script>