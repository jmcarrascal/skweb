<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>


<link href="<%=request.getContextPath()%>/css/styles.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/blockuiv1.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/popup.css" rel="stylesheet" type="text/css" />

<script src="<%=request.getContextPath()%>/js/jquery.blockUI.js" type="text/javascript"></script>

<link href="<%=request.getContextPath()%>/css/jquery.autocomplete.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=request.getContextPath()%>/js/jquery.autocomplete.js"
	type="text/javascript"></script>


 
<script>	
	$(document).ready(function(){
		
		$("#proveedor").autocomplete('getAgendadoPorNombre', {
			minChars : 1,
			max : 200,
			onOpen : function() {
				
			},
			dataType : "json",
			parse : function(data) {

				return $.map(data, function(row) {
					return {
						data : row,
						value : row.valor,
						result : row.valor
					}
				});
			},
			formatItem : function(item) {
				return item.valor;
			}
		}).result(function(e, item) {
			//Cargar el ID
			$("#genteNrId").val(item.clave);
	   	});
		   	 	   				
		$("#idOperadorText").autocomplete('getOperadorPorNombre', {
			minChars : 1,
			max : 200,
			onOpen : function() {
				
			},
			dataType : "json",
			parse : function(data) {

				return $.map(data, function(row) {
					return {
						data : row,
						value : row.valor,
						result : "[" + row.clave + "] " + row.valor
					}
				});
			},
			formatItem : function(item) {
				return "[" + item.clave + "] " + item.valor;
			}
		}).result(function(e, item) {
			//Cargar el ID
			$("#idCodOperadorHidden").val(item.clave);
	   	});

		
		if ($('#rol').val() == '1'){
			$('#vendedoresDiv').show();
			$('#oficialCuentaDiv').show();	
		}
		
		if ($('#rol').val() != '10'){
			$('#empresaDiv').show();
		}
		if ($('#rol').val() == '2' || $('#rol').val() == '3'){				
			$('#idOperadorActualDiv').show();
			$('#operadoresDiv').show();						
		}
		if ($('#rol').val() == '4' || $('#rol').val() == '5'){				
			$('#idAgendadoActualDiv').show();
			$('#proveedoresDiv').show();						
		}
		
				
		$("#guardar").click(function (){
			window.scrollTo(0,0);
   			if (validarDatosPersonales()) {
   	   			$.ajax({
   	   			  type: "POST",
   	   			  url: "saveUsuario",
   	   			  data: $("#adminUsuarioForm").serialize(),
   	   			  dataType: "script",
   	   			  beforeSend: function(objeto){   				
   	   			  },
   	   			  success: function(msg){
   	   				  
   	   				document.getElementById("idMsgSave").innerHTML = msg;  
   	   			 	
   	   				saveMsg(msg);
   	   			  },
   	   			  error: function(objeto, quepaso, otroobj){
   	   				alert("Comuniquese con el Administrador del Sistema / error: "+ quepaso);
   	   			  }
   	   		
   	   			});
   			}

   		});
   				
				
   		$('#cancelar').click(function() {   			
   			$('#listUsuarioForm').submit();   		
   		});
   		
   	}); 
	
	
	function validarDatosPersonales() {
		var allFields = $([]).add($("#apellido").add($("#nombre").add($("#usuario").add($("#password").add($("#rol").add($("#genteNrId").add($("#idCodOperadorHidden"))))))));
		var bValid = true;
		allFields.removeClass('ui-state-error');
		bValid = bValid && checkNotEmpty($("#apellido"), "APELLIDO/S", $("#validateTips"));
		bValid = bValid && checkNotEmpty($("#nombre"), "NOMBRE COMPLETO", $("#validateTips"));
		bValid = bValid && checkNotEmpty($("#usuario"), "USUARIO", $("#validateTips"));
		bValid = bValid && checkNotEmpty($("#password"), "PASSWORD", $("#validateTips"));
		bValid = bValid && checkNotEmpty($("#rol"), "ROL", $("#validateTips"));
		bValid = bValid && checkVendedor($("#rol"), "Vendedor", $("#validateTips"));
		bValid = bValid && checkProveedor($("#genteNrId"), "Agendado", $("#validateTips"));
		bValid = bValid && checkProveedor($("#idCodOperadorHidden"), "Operador", $("#validateTips"));
		return bValid;
	}
	function checkNotEmpty(o, n, tipcontainer) {
	    if (o.val() != "") {
	        return true;
	    } else {
	        updateTips("El campo " + n + " es obligatorio!", tipcontainer);
	        return false;
	    }
	}
	
	function checkVendedor(o, n, tipcontainer) {
	    if (o.val() == '1') {
	    	if ($('#vendedor').val() != null){
	    		return true;	
	    	}else{
	    		updateTips("Cuando el rol es de tipo " + n + " es obligatorio seleccionar un Vendedor", tipcontainer);
	    		return false;
	    	}
	    	
	    } else {	        
	        return true;
	    }
	}
	function checkProveedor(o, n, tipcontainer) {
	    if ($("#rol").val() == '4') {
	    	if ($('#genteNrId').val() != null || $('#genteNrId').val() != ''){
	    		return true;	
	    	}else{
	    		updateTips("Cuando el rol es de tipo " + n + " es obligatorio seleccionar un Agendado", tipcontainer);
	    		return false;
	    	}
	    	
	    } else {	        
	        return true;
	    }
	}

	function checkCliente(o, n, tipcontainer) {
	    if ($("#rol").val() == '5') {
	    	if ($('#genteNrId').val() != null || $('#genteNrId').val() != ''){
	    		return true;	
	    	}else{
	    		updateTips("Cuando el rol es de tipo " + n + " es obligatorio seleccionar un Agendado", tipcontainer);
	    		return false;
	    	}
	    	
	    } else {	        
	        return true;
	    }
	}
	function checkOperador(o, n, tipcontainer) {
	    if ($("#rol").val() == '2') {
	    	if ($('#idCodOperadorHidden').val() != null || $('#idCodOperadorHidden').val() != ''){
	    		return true;	
	    	}else{
	    		updateTips("Cuando el rol es de tipo " + n + " es obligatorio seleccionar un Operador", tipcontainer);
	    		return false;
	    	}
	    	
	    } else {	        
	        return true;
	    }
	}

	
	function updateTips(t, tipcontainer) {
	    tipcontainer.text(t).effect("highlight",{},1500);
	}

	function cargaVendedores() {		
		//Rol 1 = Vendedor
		//Rol 2 = Gerente
		//Rol 4 = Proveedor
		//Rol 5 = Cliente
		//Rol 10 = Administrador
		
		switch ($('#rol').val()) {
		case '1':
			$('#vendedoresDiv').show();
			$('#oficialCuentaDiv').show();			
			$('#empresaDiv').show();
			$('#proveedoresDiv').hide();
			$('#operadoresDiv').hide();
			$('#idOperadorActualDiv').hide();			
			$('#genteNrId').val('');
			$('#proveedor').val('');						
			break;
		case '4':
			$('#proveedoresDiv').show();
			$('#empresaDiv').show();
			$('#vendedoresDiv').hide();
			$('#oficialCuentaDiv').hide();	
			$('#idOperadorActualDiv').hide();
			$('#operadoresDiv').hide();
			$('#proveedor').focus();			
			break;
		case '2':
			$('#operadoresDiv').show();
			$('#idOperadorActualDiv').show();
			$('#empresaDiv').show();
			$('#vendedoresDiv').hide();
			$('#oficialCuentaDiv').hide();	
			$('#proveedoresDiv').hide();
			$('#idOperadorText').focus();
			break;			
		case '10':
			$('#proveedoresDiv').hide();
			$('#genteNrId').val('');
			$('#proveedor').val('');
			$('#vendedor').val(null);
			$('#vendedoresDiv').hide();	
			$('#oficialCuentaDiv').hide();	
			$('#empresaDiv').hide();
			$('#operadoresDiv').hide();
			$('#idOperadorActualDiv').hide();
			$('#vendedoresDiv').hide();
			$('#oficialCuentaDiv').hide();	
			$('#proveedoresDiv').hide();
			break;
		case '5':
			$('#proveedoresDiv').show();
			$('#empresaDiv').show();
			$('#vendedoresDiv').hide();
			$('#oficialCuentaDiv').hide();	
			$('#proveedor').focus();			
			$('#idOperadorActualDiv').hide();
			$('#operadoresDiv').hide();
			break;
		default:
			$('#empresaDiv').show();
			break;
		}
	}
		

	function validarNumero(campo) {
		var filter = /^[0-9]*$/
		var valor = campo.value; 
		if (!(filter.test(valor))) {
			campo.value = valor.substring(0, valor.length-1);
		}
	}
	
	function validarFloat(campo) {
		var filter = /^[0-9]*,?[0-9]*$/
		var valor = campo.value; 
		if (!(filter.test(valor))) {
			campo.value = valor.substring(0, valor.length-1);
		}
	}
	
	function saveMsg(msg) {
		if (msg == 'OK'){		
			document.getElementById("idMsgSaveOK").innerHTML = 'Operación realizada con Exito!';
			$.blockUI({
				theme:   true, 
				title: 'Guardar Usuario',
		        message: $('#respuestaSaveOKForm'), 
		        opacity: .1, 
		        color: 'black'
			});
				setTimeout(function() { 
				            $.unblockUI({ 
				                onUnblock: function(){ $('#listUsuarioForm').submit(); } 
				            }); 
				        }, 2000);
			

		}else{						
			$.blockUI({
				theme:   true, 
				title: 'Guardar Usuario',
		        message: $('#respuestaSaveForm'), 
		        opacity: .1, 
		        color: 'black'
			});
		}
	}
	function closeBlock(){	
		$.unblockUI();
	}



</script>

		<fieldset class="defaultFieldset" >
			<legend><b>Gestión de Usuario - Carga de Datos</b></legend>

	<s:form id="adminUsuarioForm" action="saveUsuario" theme="simple" cssClass="inline" method="post" >
		<s:actionerror/>
		<s:actionmessage/>
		<p id="validateTips"></p>
        <s:hidden id="accion" name="adminUsuario"/>
		<s:hidden id="idUsuarioWeb" name="usuarioWeb.idUsuarioWeb"/>			
		<s:hidden id="genteNrId" name="usuarioWeb.genteNr"/>
		<s:hidden id="idCodOperadorHidden" name="usuarioWeb.operadorNr"/>
		
	    <table>
			<tr>
				<td><label for="apellido">Apellido/s:</label></td>
			   	<td><s:textfield id="apellido" name="usuarioWeb.apellido" size="50" maxlength="50" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all"/></td>
			   	
			   	<td><label for="nombre">Nombre completo:</label></td>
			   	<td><s:textfield id="nombre" name="usuarioWeb.nombre" size="50" maxlength="50" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all"/></td>
			</tr>
		</table>
	    <table>
			<tr>
				<td><label for="email">Email:</label></td>
			   	<td><s:textfield id="email" name="usuarioWeb.email" size="100" maxlength="100" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all"/></td>
			</tr>
		</table>		
	    <table>
			<tr>
				<td><label for="usuario">Nombre de Usuario:</label></td>
			   	<td><s:textfield id="usuario" name="usuarioWeb.usuario" size="10" maxlength="10" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all"/></td>
			   	
			   	<td><label for="password">Password:</label></td>
			   	<td><s:textfield id="password" name="usuarioWeb.password" size="10" maxlength="10" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all"/></td>
			</tr>
		</table>
		<table>
			<tr>
				<td><label for="rol">Rol:</label></td>
				<td><s:select id="rol" name="usuarioWeb.rol.idRol" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" list="%{#session['rolList']}" listKey="idRol" listValue="descrip" onchange="cargaVendedores();"/></td>
			</tr>
		</table>
		
		<div style="display: none" id = "empresaDiv">
		<table>
			<tr>
				<td><label for="empresa">Empresa:</label></td>
				<td><s:select id="empresa" name="usuarioWeb.empresaWeb.idEmpresaWeb" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" list="%{#session['empresaWebList']}" listKey="idEmpresaWeb" listValue="nombre" onchange="cargaVendedores();"/></td>
			</tr>
		</table>
		</div>
		
		
		<div style="display: none" id = "vendedoresDiv">		
			<table>
				<tr>
					<td><label for="vendedor">Vendedor:</label></td>
					<td><s:select id="vendedor" name="usuarioWeb.vendedorNr" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" list="%{#session['vendedorList']}" listKey="nr" listValue="descripC" onchange=""/></td>
				</tr>
			</table>
		</div>
		<div style="display: none" id = "idAgendadoActualDiv">					
			<table>
				<tr>
					<td><label class="label_33 field">Agendado Asignado:</label></td>	 				
 					<td align="left"><b><s:label id="idAgendadoActualLabel" name="usuarioWeb.operadorDescrip"/></b></td>
					</tr>
				</table>
		</div>

				
		<div style="display: none" id = "proveedoresDiv">		
			<table>
				<tr>
					<td><label for="proveedor1">Agendado:</label></td>
			   	<td><s:textfield id="proveedor" name="usuarioWeb.descripGente" size="100" maxlength="100" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all"/></td>
				</tr>
			</table>
		</div>

		<div style="display: none" id = "idOperadorActualDiv">					
			<table>
				<tr>
					<td><label class="label_33 field">Operador Asignado:</label></td>	 				
 					<td align="left"><b><s:label id="idOperadorActualLabel" name="usuarioWeb.operadorDescrip"/></b></td>
					</tr>
				</table>
		</div>


		<div style="display: none" id = "operadoresDiv">		
			<table>
				<tr>
					<td><label for="operador1">Nombre de Operador:</label></td>
			   		<td><s:textfield id="idOperadorText" name="usuarioWeb.descripGente" size="100" maxlength="100" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all"/></td>			   	
				</tr>
				
			</table>
		</div>		
				
			
		

		<div style="display: none" id = "oficialCuentaDiv">		
			<table>
				<tr>
					<td><label for="of1">Nombre de Oficial de Cuentas:</label></td>
			   	<td><s:select id="oficialCuenta" name="usuarioWeb.idOficialCuenta" cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" list="%{#session['usuarioWebList']}" listKey="idUsuarioWeb" listValue="descripC" /></td>
				</tr>
			</table>
		</div>

		<table>
		<tr>
			<td><label for="procesaTransac">Usa Transacciones</label></td>
			<td><s:checkbox name="usuarioWeb.permisoTransac" /></td>
		</tr>
		</table>
		<center>
			<div id="guardar" style="display:inline;">
					<img src="images/general/icon_guardar.png" alt="Guardar" border="0">		
				
			</div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<div id="cancelar" style="display:inline; align:top">
					<img src="images/general/icon_cancelar.png" alt="Cancelar" border="0">
				
			</div>
		</center>
	</s:form>	
</fieldset>
<div style="display: none" align="center" id="respuestaSaveForm">		
		<s:label for="idMsgSave" id="idMsgSave" />
		<div class="space"></div>
		<div class="space"></div>
		<div class="buttonPanel" id="buttonDown" align="center">
			<ul class="content_1x128">							
				<a href="#" onclick="closeBlock();">
					<img src="images/general/icon_cancelar.png" alt="Cerrar" border="0" style="float:center;margin-top:0px;"></img>
				</a>						
			</ul>
		</div>						
</div>

<div style="display: none" align="center" id="respuestaSaveOKForm">		
		<s:label for="idMsgSaveOk" id="idMsgSaveOK" />
		<div class="space"></div>
		<div class="space"></div>
		
</div>
<s:form id="listUsuarioForm" action="getUsuarios" theme="simple" cssClass="inline" method="post" >
</s:form>

