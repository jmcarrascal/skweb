<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ page import="jmc.skweb.core.model.Items"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="jmc.skweb.core.model.Usuario"%>
<%@page import="jmc.skweb.util.PreferenciasUtil"%>
<%@page import="jmc.skweb.util.Constants"%>
<%@ taglib uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec"%><%@ taglib
	uri="/WEB-INF/tlds/extremecomponents.tld" prefix="ec"%>
<link href="<%=request.getContextPath()%>/css/styles.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/blockuiv1.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/popup.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/jquery.autocomplete.css"
	rel="stylesheet" type="text/css" />

<script src="<%=request.getContextPath()%>/js/jquery.blockUI.js"
	type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/jquery.autocomplete.js"
	type="text/javascript"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.bgiframe.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.ajaxQueue.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/thickbox-compressed.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.alphanumeric.js?n=2"></script>
<script>
function callSelectList() {
	$("#codArt").val($("#idAutoCompleteManual").val());
	$("#idClave").val($("#idAutoCompleteManual").val());
	$("#descripArtID").text($("#idAutoCompleteManual option:selected").html());
	$("#descripArtNameId").val($("#idAutoCompleteManual option:selected").html());
	loadPrecio($("#idAutoCompleteManual").val());
	$("#listAutoComplete").hide();
	$("#cantidad").focus();		
}
function loadArtPorCod(articuloClave, cargoPrecio) {
	if(articuloClave != ''){
		$("#idLoadArt").show();			
		$.ajax({
			type : "POST",
			url : "getArticuloPorClave_OP",
			data : {
				"stock.clave" : articuloClave
			},
			success : function(msg) {
				$("#idLoadArt").hide();
				if(msg.length > 1) {
					text = msg.substring(0, 1);
					//alert(text);
					if (text == '['){
						//alert('entro de a uno');
						$("#idClave").val(articuloClave);						
						$("#articuloAuto").val('');
						if (cargoPrecio){
							loadPrecio(articuloClave);
						}
						$("#cantidad").focus();
						$("#descripArtID").text(msg);
						$("#descripArtNameId").val(msg);	
					}else{
						//alert(msg.length);
						$("#listAutoComplete").html(msg);
						$("#listAutoComplete").show();
						$("#idAutoCompleteManual").focus();
						$("#idAutoCompleteLoad").hide();
					}
				}
				
			}
		});
	}else{
		$("#articuloAuto").focus();
		
	}
	$("#idEstadoPantalla").val('1');
}

function guardar_Carr() {
	//Vamos a Guardar
	$("#guardarCarritoForm").submit();
}

function descartar_Carr() {
	//Vamos a Descartar
	$("#addProductosForm").submit();
}

function cancelar_Carr() {
	//Vamos a Cancelar
	$.unblockUI();
}


function loadPrecio(articuloClave) {
	if($("#idLogicaColor").val() == -1 ){				
		$("#idTableStockColor").show();
		$("#idImageLoadStockColor").show();
		$.ajax({
			type : "POST",
			url : "getStockPorPiezasList",
			data : {
				"q" : articuloClave,
				"muestroBarraLateral": true
			},
			success : function(msg) {
				$("#idTableStockColor").html(msg);
				$("#idImageLoadStockColor").hide();
			}
		});
	}
	if (articuloClave != null && articuloClave != ''){
		$("#idLoadPrecio").show();
		var genteNr = $("#idAgendado").val();
		$.ajax({
			type : "POST",
			url : "getPrecioPorArtAgendado",
			data : {
				"gente.genteNr" : genteNr,
				"stock.clave" : articuloClave
			},
			success : function(msg) {
				$("#idLoadPrecio").hide();
				$("#precio").val(msg);
			}
		});
		//callGetStock(articuloClave);
	}
}

function callStockList() {
	if ($("#articuloAuto").val() != ''){
	nombreStock = $("#articuloAuto").val();				
	$("#idAutoCompleteLoad").show();
	$.ajax({
		type : "POST",
		url : "getStockPorNombreList",
		data : {
			"q" : nombreStock
		},
		success : function(msg) {
			$("#listAutoComplete").html(msg);
			$("#listAutoComplete").show();
			$("#idAutoCompleteManual").focus();
			$("#idAutoCompleteLoad").hide();
						
		}
	});
	$("#idEstadoPantalla").val('1');
	}	
}

function loadPedidoVta() {						
	$("#idLoadPedidoVtaImg").show();
	$("div.uiComprob").css({position:'absolute', left: ($("#cantidad").position().left), top: ($("#cantidad").position().top + 20) });
	var nombreStock=$("#idClave").val();
	$.ajax({
		type : "POST",
		url : "getTablePedido",
		data : {
			"stock.clave" : nombreStock,
			"transac.tipoComprob.nr" : 8
		},
		success : function(msg) {
			$("#idTableComprobante").html(msg);
			$("#idTableComprobante").show();
			$("#idLoadPedidoVtaImg").hide();
		}
	});			
							
}

function loadPedidoCompra() {						
	$("#idLoadPedidoCompraImg").show();
	$("div.uiComprob").css({position:'absolute', left: ($("#cantidad").position().left), top: ($("#cantidad").position().top + 20) });
	var nombreStock=$("#idClave").val();
	$.ajax({
		type : "POST",
		url : "getTablePedido",
		data : {
			"stock.clave" : nombreStock,
			"transac.tipoComprob.nr" : 15
		},
		success : function(msg) {
			$("#idTableComprobante").html(msg);
			$("#idTableComprobante").show();
			$("#idLoadPedidoCompraImg").hide();
		}
	});		
							
}

function loadPedidoCompraResumen() {						
	$("#idLoadPedidoCompraImg").show();
	$("div.uiComprob").css({position:'absolute', left: ($("#cantidad").position().left), top: ($("#cantidad").position().top + 20) });
	var nombreStock=$("#idClave").val();
	$.ajax({
		type : "POST",
		url : "getTableResumenPedido",
		data : {
			"stock.clave" : nombreStock,
			"transac.tipoComprob.nr" : 15
		},
		success : function(msg) {
			$("#idTableComprobante").html(msg);
			$("#idTableComprobante").show();
			$("#idLoadPedidoCompraImg").hide();
		}
	});		
							
}



function CloseUiTableBlue() {					
	$("#idTableComprobante").html('');
	$("#idTableComprobante").hide();
	}	

function callGetStock(articuloClave) {					
	$("#idLoadStock").show();
	$("#idShowStock").html("");
	$.ajax({
		type : "POST",
		url : "getFormulaStock",
		data : {
			"stock.clave" : articuloClave,
			"muestroBarraLateral" : true
		},
		success : function(msg) {
			$("#idShowStock").html(msg);
			$("#idLoadStock").hide();
		}
	});	
	}	



	function operaEvento(evento) {
		//alert('pantalla: ' + $("#idEstadoPantalla").val())
		
		
	
		if ($("#idEstadoPantalla").val() == 0) {
			if (evento.keyCode == 13) {
				$("#idEstadoPantalla").val("1");
				agregarItem();
			}
		}else{
			if ($("#idEstadoPantalla").val() == 1) {
				//alert($("#idEstadoPantalla").val());
				if (evento.keyCode == 13) {					
					var idFocus =($("*:focus").attr("id"));
					//alert('idFocus ' + idFocus );
					switch (idFocus) {
					case "codArt":						
						$("div.ui").css({position:'absolute', left: ($("#codArt").position().left), top: ($("#codArt").position().top + 20) });
						loadArtPorCod($("#codArt").val(), true);	
						break;
					case "cantidad":
						//alert('entro a cantidad');
						if ($("#idRolHidden").val('5')){
							if (validarCampos()){
								$("#adminOrdenPedidoForm").submit();
							}								
						}else{
							$("#precio").focus();	
						}							
						break;
					case "precio":						
						$("#bonificacion").focus();	
						break;
					case "bonif1":						
						loadBonifCadena($("#bonif1").val(), $("#bonif2").val(), $("#bonif3").val(), $("#bonif4").val(), false);
						$("#bonif2").focus();	
						break;
					case "bonif2":	
						loadBonifCadena($("#bonif1").val(), $("#bonif2").val(), $("#bonif3").val(), $("#bonif4").val(), false);
						$("#bonif3").focus();	
						break;
					case "bonif3":		
						loadBonifCadena($("#bonif1").val(), $("#bonif2").val(), $("#bonif3").val(), $("#bonif4").val(), false);
						$("#bonif4").focus();	
						break;										
					case "bonif4":
						if ($("#idCierreCarga").val() == '1'){
							if (validarCampos()){
								$("#adminOrdenPedidoForm").submit();
							}
						}else{
							$("#cadenaBonifId").hide();
							loadBonifCadena($("#bonif1").val(), $("#bonif2").val(), $("#bonif3").val(), $("#bonif4").val(), false);
							$("#idCierreCarga").val('1');	
							break;						
						}
					case "bonif1_M":						
						$("#bonif2_M").focus();	
						break;
					case "bonif2_M":						
						$("#bonif3_M").focus();	
						break;
					case "bonif3_M":						
						$("#bonif4_M").focus();	
						break;
					case "bonif4_M":						
						if($("#idsItemsSeleccionados").val() == ''){
							alert('Debe seleccionar al menos un item');
						}else{
							document.getElementById("changeValorMasivoForm").submit();
							//$("#changeValorMasivoForm").submit();	
						}
						$("#idEstadoPantalla").val(0);	
						break;
					case "articuloAuto":						
						$("div.ui").css({position:'absolute', left: ($("#articuloAuto").position().left), top: ($("#articuloAuto").position().top + 20) });
						
						callStockList();				
						break;
					case "idAutoCompleteManual":						
						$("#codArt").val($("#idAutoCompleteManual").val());
						$("#idClave").val($("#idAutoCompleteManual").val());
						$("#descripArtID").text($("#idAutoCompleteManual option:selected").html());
						$("#descripArtNameId").val($("#idAutoCompleteManual option:selected").html());
						loadPrecio($("#idAutoCompleteManual").val());
						$("#listAutoComplete").hide();
						$("#cantidad").focus();												
						break;
					default:
						if (validarCampos()){
							$("#idEstadoPantalla").val("0");
							$("#adminOrdenPedidoForm").submit();	
						}else{
							$("#idEstadoPantalla").val("0");
						}
						break;
					}
				}else{
					if (evento.keyCode == 27) {
						
						$("#idEstadoPantalla").val("0");
					}
				}
			}
			
		}

	}
	function operaEventoTest(evento) {
		alert(evento.keyCode);
	}

	function updateTips(t, tipcontainer) {
	    tipcontainer.text(t).effect("highlight",{},1500);
	}

	
	function checkMayorCero(o, n, tipcontainer) {	    
		var cantV = o.val(); 
		if (cantV == "") {
			cantV = 0;
		}
			if (cantV > 0) {
		        return true;
		    } else {
	    		o.focus();
	        	o.addClass('ui-state-error');
		        updateTips("El campo " + n + " debe ser Numerico y Mayor a 0!", tipcontainer);
		        return false;
		    }
	}
	function checkMenorCien(o, n, tipcontainer) {	    
		var cantV = o.val(); 
		if (cantV == "") {
			cantV = 0;
		}
			if (cantV <= 100 && cantV >= 0) {
		        return true;
		    } else {
	    		o.focus();
	        	o.addClass('ui-state-error');
		        updateTips("El campo " + n + " debe ser positivo y menor igual a 100!", tipcontainer);
		        return false;
		    }
	}
	
	function checkNotEmpty(o, n, tipcontainer) {	    
		if (o.val() != "") {
	        return true;
	    } else {
    		o.focus();
        	o.addClass('ui-state-error');
	        updateTips("El campo " + n + " es obligatorio!", tipcontainer);
	        return false;
	    }
	}

	function validarCampos() {
			var allFields = $([]).add($("#idClave").add($("#cantidad")).add($("#bonificacion")).add($("#precio")));
			var bValid = true;
			allFields.removeClass('ui-state-error');
			bValid = bValid && checkNotEmpty($("#idClave"), "Articulo", $("#validateTips"));
			if ($("#idLogicaColor").val() != -1){
				bValid = bValid && checkMayorCero($("#cantidad"), "Cantidad", $("#validateTips"));
			}
			bValid = bValid && checkMenorCien($("#bonificacion"), "Bonificacion", $("#validateTips"));
			bValid = bValid && checkMayorCero($("#precio"), "Precio", $("#validateTips"));
			return bValid;
	}
	
	function agregarItem() {
		//alert('agregoItem');
		//Pregunto por logica color si es -1 no aparece la cantidad
		//alert($("#idLogicaColor").val());
		if ($("#idLogicaColor").val() == -1){
			$("#idDivCantidad").hide();
		}else{
			$("#idDivCantidad").show();
		}
		
		$("#validateTips").html("");
		$("#articuloAuto").val("");
		$("#cantidad").val("");
		$("#precio").val("");
		$("#codArt").val("");
		$("#bonif1").val("");
		$("#bonif2").val("");
		$("#bonif3").val("");
		$("#bonif4").val("");
		$("#idClave").val("");
		$("#idShowStock").html("");
		$("#descripArtID").html("");
		
		$("#idTituloItem").text('Agregar un nuevo Item');
		$("#idItemNr").val('');
		$("#articuloAuto").focus();
		$("#idEstadoPantalla").val(1);
		
		//updateTips('',$('#validateTips'));
		window.scrollTo(0, 0);
		$.blockUI({
			message : $('#agregarItemForm'),
			opacity : .1,
			color : 'black',
			css : {
				top : (10) / 2 + 'px',
				left : ($(window).width() - 720) / 2 + 'px',
				width : '720px'
			}
		});
	}

	function loadBonifCadena(bonif1, bonif2, bonif3, bonif4, ultimo) {
		$("#loadBonificacion").show();
		$.ajax({
			type : "POST",
			url : "getBonifPorCadena",
			data : {
				"bonif1" : bonif1,
				"bonif2" : bonif2,
				"bonif3" : bonif3,
				"bonif4" : bonif4				
			},
			success : function(msg) {
				$("#loadBonificacion").hide();
				$("#bonificacion").val(msg);
				if (ultimo){
					$("#bonificacion").focus();
				}
			}
		});
		if (ultimo){
			$("#bonificacion").focus();
		}
		
	}
	$(document).ready(function() {
		
		 
		
		if ($("#idRolHidden").val() == 5){			
			$("#precio").attr("disabled","disabled");	
			$("#bonificacion").attr("disabled","disabled");
			$("#addCadena").hide();
			$("#deleteCadena").hide();
			document.getElementById("precio").style.background="#C0C0A8";
			document.getElementById("bonificacion").style.background="#C0C0A8";
		}else{
			$("#precio").attr("disabled","");	
			$("#bonificacion").attr("disabled","");
			
		}
		
		$("#bonificacion").numeric();
		$("#precio").numeric();
		$("#cantidad").numeric();
		//$("#codArt").blur(function() {	  
		//	loadArtPorCod($("#codArt").val());	  
		//});
		$("#bonif1").blur(function() {	  
			loadBonifCadena($("#bonif1").val(), $("#bonif2").val(), $("#bonif3").val(), $("#bonif4").val(), false);	  
		});
		$("#bonif2").blur(function() {	  
			loadBonifCadena($("#bonif1").val(), $("#bonif2").val(), $("#bonif3").val(), $("#bonif4").val(), false);	  
		});
		$("#bonif3").blur(function() {	  
			loadBonifCadena($("#bonif1").val(), $("#bonif2").val(), $("#bonif3").val(), $("#bonif4").val(), false);	  
		});
		$("#bonif4").blur(function() {	  
			
			$("#cadenaBonifId").hide();
			
			loadBonifCadena($("#bonif1").val(), $("#bonif2").val(), $("#bonif3").val(), $("#bonif4").val(), true);
			//Cerrar Texto de cadena
						
			
		});

		$(document).keypress(operaEvento);
		$("#agregarItem").focus();
		
		
		$("#idFinTransacButton").click(function() {
			$("#idAgendadoFinCombrobTran").val($("#idAgendado").val());
			if ($("#idCountItemsHidden").val() == "0"){
				alert("Debe ingresar al menos un item");
			}else{
				$("#finComprobForm").submit();	
			}						
		});

		
		$("#idGuardarCambioCadena").click(function() {
			
			selectCambiaCadena();
		});
		$("#idGuardarCambioBonif").click(function() {
			//alert($("#idsItemsSeleccionados").val());
			if($("#idsItemsSeleccionados_B").val() == ''){
				alert('Debe seleccionar al menos un item');
			}else{
				document.getElementById("changeValorMasivo_BForm").submit();
				//$("#changeValorMasivoForm").submit();	
			}
			$("#idEstadoPantalla").val(0);
		});
		$("#idGuardarAddCadena").click(function() {
			//alert($("#idsItemsSeleccionados").val());
			if($("#idsItemsSeleccionados_A_C").val() == ''){
				alert('Debe seleccionar al menos un item');
			}else{
				document.getElementById("changeValorMasivo_A_CForm").submit();
				//$("#changeValorMasivoForm").submit();	
			}
			$("#idEstadoPantalla").val(0);
		});
		
		
		$("#idGuardarCambioPrecio").click(function() {
			//alert($("#idsItemsSeleccionados").val());
			if($("#idsItemsSeleccionados_P").val() == ''){
				alert('Debe seleccionar al menos un item');
			}else{
				document.getElementById("changeValorMasivo_PForm").submit();
				//$("#changeValorMasivoForm").submit();	
			}
			$("#idEstadoPantalla").val(0);
		});
		
		
	
		
		$("#idSelectUnSelect").click(function() {
			
			$("input[type=checkbox]").each(function() { 			        
				if ($(this).is(':checked') == true){
					$(this).attr('checked', false);	
				}else{
					$(this).attr('checked', true);
				}
				   
				
			 });
						
		});
		
		$("#idSelectItems").click(function() {
			$("#idEstadoPantalla").val(1);
			selectItems();
						
		});
		
		$("#idSelectAddCadenaItems").click(function() {
			$("#idEstadoPantalla").val(1);
			selectAddCadenaItems();
						
		});

		$("#idSelectBonifItems").click(function() {
			$("#idEstadoPantalla").val(1);
			selectBonifItems();						
		});
		
		$("#idSelectPrecioItems").click(function() {
			$("#idEstadoPantalla").val(1);
			selectPrecioItems();						
		});
		
		$("#idVaciarCarritoButton").click(function() {
			$.blockUI({
   				message : $('#idDivConfirmVaciarCarrito'),
   				opacity : .1,
   				color : 'black',
   				css : {
   					top : ($(window).height() - 100) / 2 + 'px',
   					left : ($(window).width() - 700) / 2 + 'px',
   					width : '700px'
   				}
		});
													
		});

		$("#agregarItem").click(function() {
			agregarItem();
		});
		
		function selectCambiaCadena(){
			//alert($("#idsItemsSeleccionados").val());
			if($("#idsItemsSeleccionados").val() == ''){
				alert('Debe seleccionar al menos un item');
			}else{
				document.getElementById("changeValorMasivoForm").submit();
				//$("#changeValorMasivoForm").submit();	
			}
			$("#idEstadoPantalla").val(0);
		}
		
		function selectItems(){
			
			$("#bonif1_M").val('');
			$("#bonif2_M").val('');
			$("#bonif3_M").val('');
			$("#bonif4_M").val('');
			 var stringSeleted = '';
			 $("input[type=checkbox]").each(function() { 			        
			            if ($(this).is(':checked') == true){
			            	if (stringSeleted == ''){
			            		stringSeleted = $(this).val();
			            	}else{
			            		stringSeleted = stringSeleted + ',' + $(this).val();
			            	}
			            }
			 });
			 if (stringSeleted == ''){
					alert('Debe seleccionar al menos un Item'); 
				 }else{

			 $("#idsItemsSeleccionados").val(stringSeleted);
			 $.blockUI({
					message : $('#idCambiaCadenaBonificaciones'),
					opacity : .1,
					color : 'black',
					css : {
						top : ($(window).height() - 100) / 2 + 'px',
						left : ($(window).width() - 720) / 2 + 'px',
						width : '720px'
					}
				});
				 }
		}
		
		function selectPrecioItems(){
			$("#valor_P").val('');
			 var stringSeleted = '';
			 $("input[type=checkbox]").each(function() { 			        
			            if ($(this).is(':checked') == true){
			            	if (stringSeleted == ''){
			            		stringSeleted = $(this).val();
			            	}else{
			            		stringSeleted = stringSeleted + ',' + $(this).val();
			            	}
			            }
			 });
			 //alert(stringSeleted);
			 if (stringSeleted == ''){
				alert('Debe seleccionar al menos un Item'); 
			 }else{
			 $("#idsItemsSeleccionados_P").val(stringSeleted);
			 $.blockUI({
					message : $('#idCambiaPrecio'),
					opacity : .1,
					color : 'black',
					css : {
						top : ($(window).height() - 100) / 2 + 'px',
						left : ($(window).width() - 720) / 2 + 'px',
						width : '720px'
					}
				});
			 }
			 
		}
		
		function selectAddCadenaItems(){
			$("#bonif_A_C").val('');
			 var stringSeleted = '';
			 $("input[type=checkbox]").each(function() { 			        
			            if ($(this).is(':checked') == true){
			            	if (stringSeleted == ''){
			            		stringSeleted = $(this).val();
			            	}else{
			            		stringSeleted = stringSeleted + ',' + $(this).val();
			            	}
			            }
			 });
			 //alert(stringSeleted);
			 if (stringSeleted == ''){
				alert('Debe seleccionar al menos un Item'); 
			 }else{
			 $("#idsItemsSeleccionados_A_C").val(stringSeleted);
			 $.blockUI({
					message : $('#idAddCadena'),
					opacity : .1,
					color : 'black',
					css : {
						top : ($(window).height() - 100) / 2 + 'px',
						left : ($(window).width() - 720) / 2 + 'px',
						width : '720px'
					}
				});
			 }
			 
		}
		function selectBonifItems(){
			$("#bonif_M").val('');
			 var stringSeleted = '';
			 $("input[type=checkbox]").each(function() { 			        
			            if ($(this).is(':checked') == true){
			            	if (stringSeleted == ''){
			            		stringSeleted = $(this).val();
			            	}else{
			            		stringSeleted = stringSeleted + ',' + $(this).val();
			            	}
			            }
			 });
			 //alert(stringSeleted);
			 if (stringSeleted == ''){
				alert('Debe seleccionar al menos un Item'); 
			 }else{
			 $("#idsItemsSeleccionados_B").val(stringSeleted);
			 $.blockUI({
					message : $('#idCambiaBonificaciones'),
					opacity : .1,
					color : 'black',
					css : {
						top : ($(window).height() - 100) / 2 + 'px',
						left : ($(window).width() - 720) / 2 + 'px',
						width : '720px'
					}
				});
			 }
			 
		}
		
		function saveComprobante() {		
			$.blockUI({         	                       	               
	              message:  "<img src=\"./images/icons/loadingGrayCircle.gif\">"
	    	});			
			$.ajax({
 	   			  type: "POST",
 	   			  url: "saveTransac",
 	   			  data: $("#cerrarTransacForm").serialize(),
 	   			  dataType: "script",
 	   			  beforeSend: function(objeto){   				
 	   			  },
 	   			  success: function(msg){
 	   					$("#idMsgSave").text(msg);
 	   					$.unblockUI();
 	   					$.blockUI({
	 	   				message : $('#resultCerrarTransacId'),
	 	   				opacity : .1,
	 	   				color : 'black',
	 	   				css : {
	 	   					top : ($(window).height() - 100) / 2 + 'px',
	 	   					left : ($(window).width() - 700) / 2 + 'px',
	 	   					width : '700px'
	 	   				}
	 	   				}); 	   				   	   				   	   				  
  	   			  },
 	   			  error: function(objeto, quepaso, otroobj){
 	   				alert("Comuniquese con el Administrador del Sistema : "+ quepaso);
 	   			  }
 	   		
 	   			});
		}

		function format(mail) {
			return mail.clave;
		}

		$("#idBuscarPorCodigoImage").click(function() {
			$("div.ui").css({position:'absolute', left: ($("#codArt").position().left), top: ($("#codArt").position().top + 20) });
			loadArtPorCod($("#codArt").val(), true);	
		});
		
		$("#idBuscarPorNombreImage").click(function() {
			$("div.ui").css({position:'absolute', left: ($("#articuloAuto").position().left), top: ($("#articuloAuto").position().top + 20) });			
			callStockList();
			
		});

		$("#addCadena").click(function() {
			$("#cadenaBonifId").show();
			$("#bonif1").focus();
		});

		$("#guardarItem").click(function() {
			
			if (validarCampos()){
				
				$("#adminOrdenPedidoForm").submit();
			}
		});

		$("#idCancelarItem").click(function() {
			$.unblockUI();
			$("#idEstadoPantalla").val("0");
		});
		$("#idCancelarCambioCadena").click(function() {
			$.unblockUI();
			
		});
		$("#idCancelarCambioBonif").click(function() {
			$.unblockUI();
			
		});
		$("#idCancelarAddCadena").click(function() {
			$.unblockUI();
			
		});
		
		
			
		$("#idCancelarCambioPrecio").click(function() {
			$.unblockUI();
			
		});
		
		
		
		$("#deleteCadena").click(function() {
			$("#bonificacion").focus();
			$("#cadenaBonifId").hide();
		});
		
		$("#idAgregarProductoButton").click(function() {
			//Preguntar si tiene quiere persistir los elementos en el carrito o descartarlos
			if ($("#idCantidadItemsCargados").val() > 0){ 
				$.blockUI({
		   				message : $('#idDivGuardoItems'),
		   				opacity : .1,
		   				color : 'black',
		   				css : {
		   					top : ($(window).height() - 100) / 2 + 'px',
		   					left : ($(window).width() - 700) / 2 + 'px',
		   					width : '700px'
		   				}
				});
			}else{
				$("#addProductosForm").submit();
			}
			
		});
		
		
		$("#bonificacion").val($("#idBonif").val())

	});
	
	function editItem(itemNr, bonif, bonif1, bonif2, bonif3, bonif4, descrip, clave, cant1, precio, colorDescrip, colorNr) {
		//Cargar los valores en el formulario
		$("#idDivCantidad").show();
		$("#idTableStockColor").hide();	
		$("#idItemsColorDescrip").val(colorDescrip);	
		$("#idItemsColor").val(colorNr);	
		$("#idItemNr").val(itemNr);
		$("#bonificacion").val(bonif);
		$("#bonif1").val(bonif1);
		$("#bonif2").val(bonif2);
		$("#bonif3").val(bonif3);
		$("#bonif4").val(bonif4);
		$("#descripArtNameId").val(descrip);
		$("#descripArtID").text(descrip);
		if($("#idLogicaColor").val() == -1 ){				
			$("#descripColorID").text('  Color: ' + colorDescrip);
		}
		$("#cantidad").val(cant1);
		$("#precio").val(precio);
		$("#idClave").val(clave);
		$("#codArt").val(clave);
		$("#cantidad").focus();
		$("#idTituloItem").text('Editando el item N°: ' + itemNr);
		loadArtPorCod($("#codArt").val(), false);	
		$.blockUI({
			message : $('#agregarItemForm'),
			opacity : .1,
			color : 'black',
			css : {
				top : ($(window).height() - 100) / 2 + 'px',
				left : ($(window).width() - 700) / 2 + 'px',
				width : '700px'
			}
		});		
	}

	function removeItems(itemNr) {
		//alert(itemNr)
		document.getElementById("idItem1").value = itemNr;
		document.getElementById("removeItemForm").submit();

	}
	

	function aceptarVaciarCarrito() {
		$("#vaciarCarritoForm").submit();	
	}
	
</script>
<%
	//Tomar el usuario desde el Request
	Usuario usuarioActual = (Usuario) request.getSession()
			.getAttribute("usuario");
	int logicaColor = 0;
	if (PreferenciasUtil.comparePreferencia(
			usuarioActual.getListPreferencias(),
			Constants.PREF_ID_USA_LOGICA_STOCK_COLOR) == -1) {
		logicaColor = -1;
	}
	int usaCambioMasivo = 0;
	if (PreferenciasUtil.comparePreferencia(
			usuarioActual.getListPreferencias(),
			Constants.PREF_ID_USA_CAMBIOS_MASIVO) == -1) {
		usaCambioMasivo = -1;
	}
	int usaCadenaBonif = 0;
	if (PreferenciasUtil.comparePreferencia(
			usuarioActual.getListPreferencias(),
			Constants.PREF_ID_USA_CADENA_BONIF) == -1) {
		usaCadenaBonif = -1;
	}
	if (usuarioActual.getRol() == 5) {
		usaCambioMasivo = 0;
	}
%>


<html>
<s:form action="finComprob_Tran" id="finComprobForm">
	<s:hidden key="gente.genteNr" id="idAgendadoFinCombrobTran" />
	<s:hidden key="countItems" id="idCountItemsHidden" />
	<s:hidden key="gente.idRol" id="idRolHidden" />
</s:form>

<s:form action="removeItem_OP" id="removeItemForm">
	<s:hidden key="items.id.itemNr" id="idItem1" />
</s:form>

<s:form action="vaciarCarrito_Carr" id="vaciarCarritoForm">
</s:form>

<s:form action="guardarCarrito_Carr" id="guardarCarritoForm">
	<s:hidden key="gente.genteNr" />
</s:form>

<s:form action="preparedFindArticuloPorFlia_Carr" id="addProductosForm">
	<s:hidden key="gente.genteNr" />
</s:form>



<div class="panel_100 tab">
	<div class="space"></div>


	<fieldset class="defaultFieldset">
		<legend>
			<b>Gestión de Comprobante - Ingreso de Items del Comprobante</b>
		</legend>

		<div align="right">
			<img src="<%=request.getContextPath()%>/images/general/Paso2_v1.png"
				alt="Paso2" title="Paso2" border="0"></img>
		</div>



		<input type="hidden" id="idEstadoPantalla" value="0" /> <input
			type="hidden" id="idLogicaColor" value="<%=logicaColor%>" /> <input
			type="hidden" id="idCantidadItemsCargados"
			value="<%=((List<Items>) request.getSession().getAttribute(
					"items_OP")).size()%>" />


		<div class="space"></div>
		<div align="left">
			<s:label id="idRazonSocial" name="gente.descripC"
				cssClass="entidadGrande" />
			<div class="separator"></div>

			<label class="label_00 field">Tipo de Comprobante:</label> <b><s:label
					name="transac.tipoComprob.descripcion" /></b>
		</div>
		<div class="space"></div>
		<%
			if (usaCambioMasivo == -1) {
		%>
		<div id="idSelectUnSelect" align="right" style="display: inline;">
			<img src="images/general/icon_selec-deseleccionar.png"
				alt="Seleccionar todos" border="0">
		</div>
		<%
			}
		%>
		<ec:table tableId="notReg" items="itemsList" var="items"
			locale="es_ES" action="listItems_OP" imagePath="images/table/*.gif"
			width="100%" rowsDisplayed="100" filterable="false"
			showPagination="false" showStatusBar="false" showTitle="false"
			showTooltips="false" showExports="false">
			<ec:row>
				<%
					if (usaCambioMasivo == -1) {
				%>
				<ec:column width="60" property="porcentajeImpuesto" title="Selec"
					sortable="false" filterable="false">
					<input type="checkbox" value="${items.id.itemNr}">
				</ec:column>
				<%
					}
				%>
				<ec:column width="40px" title=" " filterable="false"
					sortable="false" alias="read">
					<%
						if (logicaColor == -1) {
					%>
					<a href="#"> <img src="images/general/edit.png"
						alt="Editar Item" title=" Editar Item "
						onclick="editItem('${items.id.itemNr}', '${items.bonif}','${items.bonif1}','${items.bonif2}','${items.bonif3}','${items.bonif4}','','${items.clave}','${items.cant1}','${items.precio}','${items.colores.descrip}','${items.colores.nr}'  );"
						border="0"></img>
					</a>
					<%
						} else {
					%>
					<a href="#"> <img src="images/general/edit.png"
						alt="Editar Item" title=" Editar Item "
						onclick="editItem('${items.id.itemNr}', '${items.bonif}','${items.bonif1}','${items.bonif2}','${items.bonif3}','${items.bonif4}','','${items.clave}','${items.cant1}','${items.precio}','','0' );"
						border="0"></img>
					</a>

					<%
						}
					%>

					<a href="#"> <img src="images/general/remove.png"
						alt=" Eliminar Item " title=" Eliminar Item"
						onclick="removeItems('${items.id.itemNr}')" border="0"></img>
					</a>
				</ec:column>
				<ec:column width="60px" property="articulo"
					title="Clave" sortable="false" filterable="false" alias = "read1">
					<span style="white-space: nowrap;">${items.articulo}</span>
				</ec:column>
<%-- 				<ec:column width="80px" property="articulo" title="Clave" --%>
<%-- 					sortable="true" sortable="false" filterable="false" --%>
<%-- 					alias="articulo"> --%>
<%-- 				</ec:column> --%>
				<ec:column width="40px" property="id.itemNr" title="Nr"
					sortable="false" filterable="false">
				</ec:column>
				<ec:column width="60px" property="descrip"
					title="Articulo" sortable="false" filterable="false" alias = "read2">
					<span style="white-space: nowrap;">${items.descrip}</span>
				</ec:column>
<%-- 				<ec:column width="auto" property="descrip" title="Articulo" --%>
<%-- 					sortable="true" sortable="false" filterable="false" alias="descrip"> --%>
<%-- 				</ec:column> --%>
				<%
					if (logicaColor == -1) {
				%>
				<ec:column width="100" property="colores.descrip" title="Color"
					sortable="false" filterable="false">
				</ec:column>
				<%
					}
				%>
				<ec:column width="60" property="cant1" title="Cantidad"
					sortable="false" filterable="false">
				</ec:column>
				<ec:column width="60" property="precio" title="Precio Un"
					sortable="false" filterable="false">
				</ec:column>
				<ec:column width="60" property="bonif" title="Bonificacion"
					sortable="false" filterable="false">
				</ec:column>
				<%
					if (usaCadenaBonif == -1) {
				%>
				<ec:column width="60" property="obser" title="Cadena Bonif"
					sortable="false" filterable="false">
				</ec:column>
				<%
					}
				%>
				<ec:column width="60" property="totalItem" title="SubTotal"
					sortable="false" filterable="false">
				</ec:column>


			</ec:row>
		</ec:table>
		<%
			if (usaCambioMasivo == -1) {
				if (usaCadenaBonif == -1) {
		%>
		<div id="idSelectItems" align="right" style="display: inline;">
			<img src="images/general/icon_editar_cadena_bonif.png"
				alt="Editar Cadena de Modificación" border="0">
		</div>

		<div id="idSelectAddCadenaItems" align="right"
			style="display: inline;">
			<img src="images/general/icon_agregar_cadena.png"
				alt="Agregar Cadena de Bonificación" border="0">
		</div>
		<%
			} else {
		%>
		<div id="idSelectBonifItems" align="right" style="display: inline;">
			<img src="images/general/icon_editar_bonificacion.png"
				alt="Editar Bonificación" border="0">
		</div>
		<%
			}
		%>
		<div id="idSelectPrecioItems" align="right" style="display: inline;">
			<img src="images/general/icon_edita_precio.png" alt="Editar Precio"
				border="0">
		</div>
		<%
			}
		%>

		<div class="space"></div>
		<div align="center">
			<div>
				<b><label class="subTotalesTitle">SubTotal:</label></b>
				<s:label cssClass="subTotales" id="transac.netoGrav"
					name="transac.netoGrav" />
			</div>
			<div>
				<b><label class="subTotalesTitle">Impuestos:</label></b>
				<s:label cssClass="subTotales" id="transac.iva" name="transac.iva" />
			</div>
			<div>
				<b><label class="totalesTitle">Total:</label></b> <b><s:label
						cssClass="totales" id="transac.iva" name="transac.total" /></b>
			</div>
		</div>
		<div align="center">
			<%
				if ((List<Items>) request.getSession().getAttribute("items_CARR") != null
						&& ((List<Items>) request.getSession().getAttribute(
								"items_CARR")).size() > 0) {
			%>
			<div id="idVaciarCarritoButton" style="display: inline;">
				<img src="images/general/icon_usa_items_carrito.gif"
					alt="Usar Items del Carrito" border="0">
			</div>
			<%
				} else {
			%>
			<div id="idAgregarProductoButton" style="display: inline;">
				<img id="idAgregarProductos"
					src="<%=request.getContextPath()%>/images/general/icon_agregar_productos_btn.png"
					alt="Agregar Productos" title="Agregar Productos" border="0"></img>
			</div>
			<%
				}
			%>
			<div id="agregarItem" style="display: inline;">
				<img src="images/general/icon_nuevo_item.png" alt="Agregar Item"
					border="0">
			</div>

			<div id="idVolverImage" style="display: inline;">
				<a href="menuAgendado_Tran" onclick="this.blur();"> <img
					src="images/general/icon_volver.png" alt="Volver" border="0">
				</a>
			</div>

			<div id="idFinTransacButton" style="display: inline;">
				<img src="images/general/icon_continuar.png" alt="Continuar"
					border="0">
			</div>



		</div>
		</center>
	</fieldset>
</div>



<div style="display: none" align="center" id="resultCerrarTransacId">
	<h1 id="idMsgSave">Transacción</h1>
	<div id="buttonDown">
		<a href="preparedFindAgendadoPorNombre_OP" onclick="this.blur();">
			<img src="images/general/icon_nuevo_compro.png" alt="Nueva Solicitud"
			border="0" style="float: center; margin-top: 0px;"></img>
		</a> <a href="home" onclick="this.blur();"> <img
			src="images/general/icon_ir_menu.png" alt="Ir a Pendientes"
			border="0" style="float: center; margin-top: 0px;"></img>
		</a>
	</div>
</div>

<div style="display: none" id="idCambiaBonificaciones">

	<div class="panel_100 tab">
		<div class="space"></div>

		<fieldset class="defaultFieldset">
			<legend>
				<b id="idTituloItem">Modificar Bonificación</b>
			</legend>
			<s:form id="changeValorMasivo_BForm" action="changeValorMasivo"
				theme="simple" cssClass="inline" method="post" autocomplete="off">
				<s:hidden key="itemsSelecionados" id="idsItemsSeleccionados_B" />
				<s:hidden key="tipoValorMasivo" value="2" />


				<fieldset class="defaultFieldset">
					<legend></legend>
					<table>
						<tr>
							<td><label for="precio1">Bonificación:</label></td>
							<td><s:textfield id="bonif_M" name="items.bonif" size="5"
									maxlength="3" cssErrorClass="error"
									cssClass="ui-widget-content ui-corner-all" /></td>
						</tr>
					</table>
				</fieldset>




				<div align="center">
					<div id="idGuardarCambioBonif">
						<img src="images/general/icon_guardar.png" alt="Guardar Cambio"
							border="0">
					</div>
					<div id="idCancelarCambioBonif">
						<img src="images/general/icon_cancelar.png" alt="Cancelar Item"
							border="0">
					</div>
				</div>
			</s:form>
		</fieldset>
	</div>
</div>


<div style="display: none" id="idAddCadena">
	<div class="panel_100 tab">
		<div class="space"></div>

		<fieldset class="defaultFieldset">
			<legend>
				<b id="idTituloItem">Agregar bonificación a cadena</b>
			</legend>
			<s:form id="changeValorMasivo_A_CForm" action="changeValorMasivo"
				theme="simple" cssClass="inline" method="post" autocomplete="off">
				<s:hidden key="itemsSelecionados" id="idsItemsSeleccionados_A_C" />
				<s:hidden key="tipoValorMasivo" value="4" />


				<br>
				<table>
					<tr>
						<td><label for="precio1">Bonificación:</label></td>
						<td><s:textfield id="bonif_A_C" name="items.bonif" size="5"
								maxlength="3" cssErrorClass="error"
								cssClass="ui-widget-content ui-corner-all" /></td>
					</tr>
				</table>




				<div align="center">
					<div id="idGuardarAddCadena">
						<img src="images/general/icon_guardar.png" alt="Guardar Cambio"
							border="0">
					</div>
					<div id="idCancelarAddCadena">
						<img src="images/general/icon_cancelar.png" alt="Cancelar Item"
							border="0">
					</div>
				</div>
			</s:form>
		</fieldset>
	</div>
</div>



<div style="display: none" id="idCambiaPrecio">
	<div class="panel_100 tab">
		<div class="space"></div>

		<fieldset class="defaultFieldset">
			<legend>
				<b id="idTituloItem">Modificar Precio</b>
			</legend>
			<s:form id="changeValorMasivo_PForm" action="changeValorMasivo"
				theme="simple" cssClass="inline" method="post" autocomplete="off">
				<s:hidden key="itemsSelecionados" id="idsItemsSeleccionados_P" />
				<s:hidden key="tipoValorMasivo" value="3" />

				<table>
					<tr>
						<td><s:radio id="adi_valor_P"
								list="#{'1':'Adicionar importe al Precio','2':'Restar importe al Precio','3':'Adicionar % al Precio','4':'Restar % al Precio','5':'Sustituir precio'}"
								name="items.tipoVariacionPrecio" value="1" cssErrorClass="error"
								cssClass="ui-widget-content ui-corner-all" /></td>
						<br>
					</tr>
				</table>
				<br>
				<table align="center">
					<tr align="center">
						<td><label for="precio1"><b>Valor:</b></label></td>
						<td><s:textfield id="valor_P" name="items.variacionPrecio"
								size="10" maxlength="10" cssErrorClass="error"
								cssClass="ui-widget-content ui-corner-all" /></td>
					</tr>
				</table>




				<div align="center">
					<div id="idGuardarCambioPrecio">
						<img src="images/general/icon_guardar.png" alt="Guardar Cambio"
							border="0">
					</div>
					<div id="idCancelarCambioPrecio">
						<img src="images/general/icon_cancelar.png" alt="Cancelar Item"
							border="0">
					</div>
				</div>
			</s:form>
		</fieldset>
	</div>
</div>

<div style="display: none" id="idCambiaCadenaBonificaciones">
	<div class="panel_100 tab">
		<div class="space"></div>

		<fieldset class="defaultFieldset">
			<legend>
				<b id="idTituloItem">Modificar Cadena de Bonificaciones</b>
			</legend>
			<s:form id="changeValorMasivoForm" action="changeValorMasivo"
				theme="simple" cssClass="inline" method="post" autocomplete="off">
				<s:hidden key="itemsSelecionados" id="idsItemsSeleccionados" />
				<s:hidden key="tipoValorMasivo" value="1" />


				<fieldset class="defaultLegendChico">
					<legend></legend>
					<table>
						<tr>
							<td><label for="precio1">Bonif1:</label></td>
							<td><s:textfield id="bonif1_M" name="items.bonif1" size="5"
									maxlength="3" cssErrorClass="error"
									cssClass="ui-widget-content ui-corner-all" /></td>
							<td><label for="precio1">Bonif2:</label></td>
							<td><s:textfield id="bonif2_M" name="items.bonif2" size="5"
									maxlength="3" cssErrorClass="error"
									cssClass="ui-widget-content ui-corner-all" /></td>
							<td><label for="precio1">Bonif3:</label></td>
							<td><s:textfield id="bonif3_M" name="items.bonif3" size="5"
									maxlength="3" cssErrorClass="error"
									cssClass="ui-widget-content ui-corner-all" /></td>
							<td><label for="precio1">Bonif4:</label></td>
							<td><s:textfield id="bonif4_M" name="items.bonif4" size="5"
									maxlength="3" cssErrorClass="error"
									cssClass="ui-widget-content ui-corner-all" /></td>
						</tr>
					</table>
				</fieldset>




				<div align="center">
					<div id="idGuardarCambioCadena">
						<img src="images/general/icon_guardar.png" alt="Guardar Cambio"
							border="0">
					</div>
					<div id="idCancelarCambioCadena">
						<img src="images/general/icon_cancelar.png" alt="Cancelar Item"
							border="0">
					</div>
				</div>
			</s:form>
		</fieldset>
	</div>
</div>


<div style="display: none" align="left" id="agregarItemForm">
	<br>
	<div class="space"></div>
	<div class="panel_100 tab">
		<div class="space"></div>


		<fieldset class="defaultFieldset">
			<legend>
				<b id="idTituloItem">Agregar un nuevo Item</b>
			</legend>
			<p id="validateTips"></p>
			<s:form id="adminOrdenPedidoForm" action="addItem_OP" theme="simple"
				cssClass="inline" method="post" autocomplete="off">

				<s:hidden key="gente.genteNr" id="idAgendado" />
				<s:hidden key="gente.bonifStd" id="idBonif" />
				<s:hidden key="items.id.itemNr" id="idItemNr" />
				<s:hidden key="items.articulo" id="idClave" />
				<s:hidden key="items.descrip" id="descripArtNameId" />
				<s:hidden key="items.colores.nr" id="idItemsColor" />
				<s:hidden key="items.colores.descrip" id="idItemsColorDescrip" />
				<s:hidden id="idCierreCarga" />
				<br>
				<table>
					<tr>
						<td><label for="articuloAuto1">Cod. Art:</label></td>
						<td><s:textfield id="codArt" name="items.clave" size="20"
								maxlength="20" cssErrorClass="error"
								cssClass="ui-widget-content ui-corner-all" /></td>
						<td style="display: none" id="idLoadArt"><img alt=""
							src="images/general/loading1.gif"></td>
						<td style="display: inline" id="idBuscarPorCodigoImage"><img
							alt="" src="images/general/view.gif"></td>


						<td><label for="articuloAuto1">Articulo:</label></td>
						<td><s:textfield id="articuloAuto" size="60" maxlength="60"
								cssErrorClass="error" cssClass="ui-widget-content ui-corner-all" /></td>
						<div id="listAutoComplete" class="ui" style="display: none;" />
						<td style="display: none" id="idAutoCompleteLoad"><img alt=""
							src="images/general/loading1.gif"></td>
						<td style="display: inline" id="idBuscarPorNombreImage"><img
							alt="" src="images/general/view.gif"></td>

					</tr>
				</table>
				<table>
					<tr>
						<td><b for="descripArt" id="descripArtID" /></td>
						<%
							if (logicaColor == -1) {
						%>
						<td><b for="descripColor" id="descripColorID" /></td>
						<%
							}
						%>
					</tr>
				</table>
				<div id="idDivCantidad" style="display: none;">
					<table>
						<tr align="center">
							<td><label for="cantidad1">Cantidad:</label></td>
							<td><s:textfield id="cantidad" name="items.cant1" size="15"
									value="1" maxlength="15" cssErrorClass="error"
									cssClass="ui-widget-content ui-corner-all" /></td>
							<td style="display: none" id="idLoadStock"><img alt=""
								src="images/general/loading1.gif"></td>
							<td><div id="idShowStock" /></td>

						</tr>
					</table>
				</div>
				<table>
					<tr>
						<div align="center" id="idImageLoadStockColor"
							style="display: none;">
							<img alt="" src="images/general/loading1.gif">
						</div>
						<div align="center" id="idTableStockColor" style="display: none;">
						</div>
						<div id="idTableComprobante" class="uiComprob"
							style="display: none;" />
					</tr>
				</table>
				<table>
					<tr>
						<td><label for="precio1">Precio:</label></td>
						<td><s:textfield id="precio" name="items.precio" size="15"
								maxlength="15" cssErrorClass="error"
								cssClass="ui-widget-content ui-corner-all" disabled="true" /></td>
						<td style="display: none" id="idLoadPrecio"><img alt=""
							src="images/general/loading1.gif"></td>
					</tr>
				</table>
				<table>
					<tr>
						<td><label for="precio1">Bonificación:</label></td>
						<td><s:textfield id="bonificacion" name="items.bonif"
								size="8" maxlength="15" cssErrorClass="error"
								cssClass="ui-widget-content ui-corner-all" />
						<td style="display: none" id="loadBonificacion"><img alt=""
							src="images/general/loading1.gif"></td>
						<td id="addCadena"><img
							title="Mostrar Cadena de Bonificación"
							src="images/general/add.png"></td>
						<td id="deleteCadena"><img alt=""
							src="images/general/delete.png"
							title="Ocultar Cadena de Bonificación"></td>
					</tr>
				</table>

				<div style="display: none" align="left" id="cadenaBonifId">

					<fieldset class="defaultLegendChico">
						<legend>
							<b>Cadena de Bonificaciones</b>
						</legend>
						<table>
							<tr>
								<td><label for="precio1">Bonif1:</label></td>
								<td><s:textfield id="bonif1" name="items.bonif1" size="5"
										maxlength="3" cssErrorClass="error"
										cssClass="ui-widget-content ui-corner-all" /></td>
								<td><label for="precio1">Bonif2:</label></td>
								<td><s:textfield id="bonif2" name="items.bonif2" size="5"
										maxlength="3" cssErrorClass="error"
										cssClass="ui-widget-content ui-corner-all" /></td>
								<td><label for="precio1">Bonif3:</label></td>
								<td><s:textfield id="bonif3" name="items.bonif3" size="5"
										maxlength="3" cssErrorClass="error"
										cssClass="ui-widget-content ui-corner-all" /></td>
								<td><label for="precio1">Bonif4:</label></td>
								<td><s:textfield id="bonif4" name="items.bonif4" size="5"
										maxlength="3" cssErrorClass="error"
										cssClass="ui-widget-content ui-corner-all" /></td>
							</tr>
						</table>
					</fieldset>
				</div>


				<div align="center">
					<div id="guardarItem" style="display: inline;">
						<img src="images/general/icon_guardar.png" alt="Guardar Item"
							border="0" id="guardarItemImg">
					</div>
					<div id="idCancelarItem" style="display: inline;">
						<img src="images/general/icon_cancelar.png" alt="Cancelar Item"
							border="0">
					</div>
				</div>
			</s:form>
		</fieldset>
	</div>
</div>




<center>
	<div style="display: none" align="center" id="idDivGuardoItems">
		<h1 id="idMsgSave">Desea guardar los items ya seleccionados en
			el carrito?</h1>
		<div id="buttonDown">
			<a> <img onclick="guardar_Carr();"
				src="images/general/icon_guardar_v1.png" alt="Guardar" border="0"
				style="float: center; margin-top: 0px;"></img>
			</a> <a onclick="descartar_Carr();"> <img
				src="images/general/icon_descartar.png" alt="Descartar" border="0"
				style="float: center; margin-top: 0px;"></img>
			</a> <a onclick="cancelar_Carr();"> <img
				src="images/general/icon_cancelar_v1.png" alt="Cancelar" border="0"
				style="float: center; margin-top: 0px;"></img>
			</a>

		</div>
	</div>
</center>




<center>
	<div style="display: none" align="center"
		id="idDivConfirmVaciarCarrito">
		<h1 id="idMsgSave">Esta seguro que desea usar los items cargados
			al carrito?</h1>
		<div id="buttonDown">
			<a onclick="aceptarVaciarCarrito();"> <img
				src="images/general/icon_aceptar_v1.png" alt="Aceptar" border="0"
				style="float: center; margin-top: 0px;"></img>
			</a> <a onclick="cancelar_Carr();"> <img
				src="images/general/icon_cancelar_v1.png" alt="Cancelar" border="0"
				style="float: center; margin-top: 0px;"></img>
			</a>

		</div>
	</div>
</center>
</html>




